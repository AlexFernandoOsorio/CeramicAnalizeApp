package org.tesis.ie.ceramic.data

import android.content.Context
import android.graphics.Bitmap
import android.view.Surface
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier
import org.tesis.ie.ceramic.domain.CeramicClassifier
import org.tesis.ie.ceramic.domain.models.Classification
import java.nio.ByteBuffer
import java.nio.ByteOrder

class TFLITECeramicModel(context: Context) : CeramicClassifier {

    private var interpreter: Interpreter

    init {
        val assetFileDescriptor = context.assets.openFd("modelococina.tflite")
        val fileInputStream = assetFileDescriptor.createInputStream()
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        val modelBuffer = fileChannel.map(java.nio.channels.FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)

        interpreter = Interpreter(modelBuffer)
    }

    override fun classify(bitmap: Bitmap): List<Classification> {
        // Preprocesar la imagen: 224x224 RGB normalizada
        val inputImage = Bitmap.createScaledBitmap(bitmap, 224, 224, true)
        /*val tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(inputImage)
        val buffer = tensorImage.buffer

        // Asegurar que los valores estén normalizados [0,1]
        val normalizedInput = normalizeInput(buffer)

        */
        val tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(inputImage)
        // Crear buffer de salida
        val outputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, 6), DataType.FLOAT32)

        // Ejecutar predicción
        interpreter.run(tensorImage.buffer, outputBuffer.buffer.rewind())

        // Obtener resultados
        val scores = outputBuffer.floatArray

        return scores.mapIndexed { index, score ->
            Classification(name = "Clase $index", score = score)
        }.sortedByDescending { it.score }
    }

    private fun normalizeInput(buffer: ByteBuffer): ByteBuffer {
        val floatBuffer = ByteBuffer.allocateDirect(1 * 224 * 224 * 3 * 4)
        floatBuffer.order(ByteOrder.nativeOrder())
        buffer.rewind()
        while (buffer.hasRemaining()) {
            val unsigned = buffer.get().toInt() and 0xFF
            floatBuffer.putFloat(unsigned / 255.0f)
        }
        return floatBuffer
    }
}