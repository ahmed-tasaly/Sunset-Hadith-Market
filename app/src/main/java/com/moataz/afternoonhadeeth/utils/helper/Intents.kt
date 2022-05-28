package com.moataz.afternoonhadeeth.utils.helper

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.hadith.Hadith
import com.moataz.afternoonhadeeth.data.model.home.blocks.DataList
import com.moataz.afternoonhadeeth.data.model.videos.top.Data
import es.dmoral.toasty.Toasty

object Intents {

    fun openUrl(view: AppCompatActivity, url: String) {
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        view.startActivity(intent)
    }

    fun sharedText(context: Context, sharedText: String, title: String, extraText: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "$extraText$sharedText\n\n$title"
        )
        context.startActivity(Intent.createChooser(intent, ""))
    }

    fun copyText(sharedText: String, context: Context, extraText: String) {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(
            "text",
            "$extraText$sharedText\n\nتم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ"
        )
        clipboardManager.setPrimaryClip(clipData)
    }

    fun shareTextSnackbar(view: View, text: String, sharedText: String, context: Context) {
        Toasty.normal(context, text, Toast.LENGTH_SHORT).show()
    }

    fun showToast(context: Context, text: String) {
        Toasty.normal(context, text, Toast.LENGTH_SHORT).show()
    }

    fun openNewActivity(context: Context, cls: Class<*>?) {
        val intent = Intent(context, cls)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        context.startActivity(intent)
    }

    fun openNewActivityWithInfo(
        context: Context,
        cls: Class<*>?,
        title: String,
        blocksInfoList: ArrayList<DataList>
    ) {
        val intent = Intent(context, cls)
        intent.putExtra("title", title)
        intent.putParcelableArrayListExtra("BlocksInfoList", blocksInfoList);
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        context.startActivity(intent)
    }

    fun openNewActivityWithVideos(
        context: Context,
        cls: Class<*>?,
        title: String,
        blocksInfoList: ArrayList<Data>
    ) {
        val intent = Intent(context, cls)
        intent.putExtra("topVideosTitle", title)
        intent.putParcelableArrayListExtra("topVideosBlocksList", blocksInfoList);
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        context.startActivity(intent)
    }

    fun openNewActivityWithHadith(
        context: Context,
        cls: Class<*>?,
        title: String,
        hadithList: ArrayList<Hadith>
    ) {
        val intent = Intent(context, cls)
        intent.putExtra("hadithTitle", title)
        intent.putParcelableArrayListExtra("hadithList", hadithList);
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        context.startActivity(intent)
    }

    /*
     * @function openNewActivityWithInfo
     * open url in custom tab with chrome
     * @function openTabUrl + openCustomTabs + isPackageInstalled
     */
    fun openTabUrl(context: Context, url: String) {
        val customTabIntent: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        customTabIntent.setToolbarColor(Color.parseColor("#FFF5E6"))
        customTabIntent.setStartAnimations(
            context,
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
        customTabIntent.setExitAnimations(
            context,
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
        customTabIntent.setShowTitle(true)
        openCustomTabs(
            context,
            customTabIntent.build(),
            Uri.parse(url)
        )
    }

    private fun openCustomTabs(activity: Context, customTabsIntent: CustomTabsIntent, uri: Uri) {
        val packageName = "com.android.chrome"
        customTabsIntent.intent.setPackage(packageName)
        customTabsIntent.launchUrl(activity, uri)
    }
}