package com.radialo.topreddit.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

class ImageUrlReader {
    fun readImage(path : String): Bitmap? {
        return BitmapFactory.decodeStream(URL(path).openStream())
    }
}