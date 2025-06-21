package org.tesis.ie.ceramic.ui.search

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import org.tesis.ie.ceramic.domain.CeramicClassifier
import org.tesis.ie.ceramic.domain.models.Classification

class CeramicImageAnalyzer (
    private val classifier: CeramicClassifier,
    private val onResults: (List<Classification>) -> Unit
): ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if(frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(244, 244)

            val results = classifier.classify(bitmap)
            onResults(results)
        }
        frameSkipCounter++

        image.close()
    }
}
//  1 , 244 , 244 , 1
//float