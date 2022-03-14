package com.moataz.afternoonhadeeth.ui.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import coil.load
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.ActivityDisplayWallpaperImagesBinding
import com.moataz.afternoonhadeeth.utils.helper.Views.hideStatusBar
import com.moataz.afternoonhadeeth.utils.helper.Views.intiViews
import es.dmoral.toasty.Toasty

class DisplayWallpaperImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayWallpaperImagesBinding
    private val PERMISSION_WRITE = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayWallpaperImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeView()
        back()
        loadImage()
        makeAction()
    }

    private fun initializeView() {
        intiViews(window)
        hideStatusBar(window)
    }

    private fun back() {
        binding.buttonBackDowaa.setOnClickListener { finish() }
    }

    private fun makeAction() {
        binding.buttonShare.setOnClickListener {
            if (checkPermission()) {
                shareImage()
            }
        }
        setDownloadImage()
        setWallpaper()
    }

    private fun loadImage() {
        val intent = intent
        if (intent.hasExtra("imageUrl")) {
            val url = intent.getStringExtra("imageUrl")
            binding.imageDisplay.load(url) {
                placeholder(R.drawable.folder_loading_image)
            }
        }
    }

    private fun setDownloadImage() {
        binding.buttonDownload.setOnClickListener {
            if (checkPermission()) {
                shareImage()
                Toasty.normal(this, "تم حفظ الصورة بنجاح", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun shareImage() {
        val bitmapDrawable = binding.imageDisplay.drawable as BitmapDrawable
        val bitmap = bitmapDrawable.bitmap
        val bitmapPath = MediaStore.Images.Media.insertImage(
            contentResolver,
            bitmap,
            "حمل تطبيق حديث الغروب للمزيد من الأحاديث الشريفه",
            null
        )
        val bitmapUri = Uri.parse(bitmapPath)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/jpg/png"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "حمل تطبيق حديث الغروب للمزيد من الأحاديث الشريفه"
        )
        shareIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
        startActivity(Intent.createChooser(shareIntent, ""))
    }

    private fun setWallpaper() {
        binding.buttonWallpaper.setOnClickListener {
            if (checkPermission()) {
                val bitmapDrawable = binding.imageDisplay.drawable as BitmapDrawable
                val bitmap = bitmapDrawable.bitmap
                val bitmapPath =
                    MediaStore.Images.Media.insertImage(contentResolver, bitmap, "", null)
                val bitmapUri = Uri.parse(bitmapPath)
                val intent =
                    Intent(Intent.ACTION_ATTACH_DATA).setDataAndType(bitmapUri, "image/jpeg")
                        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        .putExtra("mimeType", "image/jpeg")
                startActivity(Intent.createChooser(intent, getString(R.string.background)))
            }
        }
    }

    private fun checkPermission(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_WRITE
            )
            false
        }
    }
}