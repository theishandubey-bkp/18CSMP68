package `in`.edu.cambridge.wallpaper

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var myWallpaperList = arrayOf(R.drawable.wallpaper1,R.drawable.wallpaper2,R.drawable.wallpaper3,R.drawable.wallpaper4,R.drawable.wallpaper5)
    private lateinit var changeWallpaper:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeWallpaper = findViewById(R.id.change_wallpaper)
        changeWallpaper.setOnClickListener { setWallpaper() }
    }

    fun setWallpaper() {
        Toast.makeText(this, "Setting Wallpaper please wait.", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            for(i in myWallpaperList) {
                val bitmap: Bitmap = BitmapFactory.decodeResource(resources, i)
                val wallpaperManager = WallpaperManager.getInstance(baseContext)
                wallpaperManager.setBitmap(bitmap)

            }
        }, 2000)
    }
}