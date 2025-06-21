package org.tesis.ie.ceramic.domain

import android.graphics.Bitmap
import org.tesis.ie.ceramic.domain.models.Classification

interface CeramicClassifier {

    fun classify(bitmap: Bitmap): List<Classification>

}