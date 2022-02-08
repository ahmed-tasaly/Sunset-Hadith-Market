package com.moataz.afternoonhadeeth.utils.helper

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.moataz.afternoonhadeeth.data.model.home.blocks.DataList
import es.dmoral.toasty.Toasty
import java.util.ArrayList


object Intents {

    fun openUrl(view: AppCompatActivity, url: String): Unit? {
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        return if (intent.resolveActivity(view.packageManager) != null) {
            view.startActivity(intent)
        } else null
    }

    fun sharedText(context: Context, sharedText: String, title: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "$sharedText\n\n$title"
        )
        context.startActivity(Intent.createChooser(intent, ""))
    }

    fun copyText(sharedText: String, context: Context) {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(
            "text",
            "$sharedText\n\nتم الإرسال من تطبيق حديث الغروب: لحياة النبي ﷺ"
        )
        clipboardManager.setPrimaryClip(clipData)
    }

    fun shareTextSnackbar(view: View, text: String, sharedText: String, context: Context) {
        Toasty.normal(context, text, Toast.LENGTH_SHORT).show()
    }

    fun openNewActivity(context: Context, cls: Class<*>?) {
        val intent = Intent(context, cls)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        context.startActivity(intent)
    }

    fun openNewActivityWithInfo(context: Context, cls: Class<*>?, title: String, blocksInfoList: ArrayList<DataList>) {
        val intent = Intent(context, cls)
        intent.putExtra("title", title)
        intent.putParcelableArrayListExtra("BlocksInfoList", blocksInfoList);
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        context.startActivity(intent)
    }
}