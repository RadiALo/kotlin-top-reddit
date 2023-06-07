package com.radialo.topreddit

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream

class UpscaleActivity : AppCompatActivity() {
    private lateinit var upscaleImage : ImageView
    private lateinit var downloadButton : Button
    private lateinit var closeButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_upscale)
        upscaleImage  = findViewById(R.id.upscale_image)
        downloadButton  = findViewById(R.id.download_button)
        closeButton  = findViewById(R.id.close_button)
        // Set image
        Picasso.with(this)
            .load(intent.getStringExtra("upscale"))
            .into(upscaleImage)
        // Set actions on Buttons click
        downloadButton.setOnClickListener {
            val seconds = System.currentTimeMillis()
            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "post_$seconds.jpg")
            val bitmap = Bitmap.createBitmap(
                upscaleImage.width,
                upscaleImage.height,
                Bitmap.Config.ARGB_8888
            )
            upscaleImage.draw(Canvas(bitmap))
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            Snackbar.make(
                findViewById(R.id.upscale_layout),
                R.string.download_message,
                Snackbar.LENGTH_SHORT
            ).show()
        }
        closeButton.setOnClickListener {
            finish()
        }
    }
}