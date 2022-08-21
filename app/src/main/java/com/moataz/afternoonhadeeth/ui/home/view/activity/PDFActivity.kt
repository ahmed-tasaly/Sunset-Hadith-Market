package com.moataz.afternoonhadeeth.ui.home.view.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.utils.helper.Views.hideStatusBar
import com.moataz.afternoonhadeeth.utils.helper.Views.intiViews
import com.shockwave.pdfium.PdfDocument.Bookmark

class PDFActivity : AppCompatActivity(), OnPageChangeListener, OnLoadCompleteListener {
    private var mCurrentPage = 0
    private var pdfView: PDFView? = null
    private var pdfFileName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCurrentPage = savedInstanceState?.getInt(KEY_CURRENT_PAGE) ?: -1
        setContentView(R.layout.actvity_pdf_view)
        pdfView = findViewById(R.id.pdfView)
        afterViews()
    }

    private fun afterViews() {
        pdfView!!.setBackgroundColor(Color.WHITE)
        displayFromAsset()
        title = pdfFileName
        intiViews(window)
        hideStatusBar(window)
    }

    private fun displayFromAsset() {
        pdfFileName = SAMPLE_FILE
        pdfView!!.fromAsset(SAMPLE_FILE)
            .defaultPage(mCurrentPage)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .password(null)
            .enableAntialiasing(true)
            .onPageChange(this)
            .enableAnnotationRendering(true)
            .onPageScroll { page: Int, positionOffset: Float ->
                Log.d(
                    TAG,
                    "onPageScrolled: page $page positionOffset $positionOffset"
                )
            }
            .onRender { nbPages: Int, pageWidth: Float, pageHeight: Float ->
                pdfView!!.fitToWidth(
                    mCurrentPage
                )
                nbPages.compareTo(mCurrentPage)
            }
            .onLoad { nbPages: Int -> Log.d(TAG, "loadComplete: totalPages $nbPages") }
            .onError { t: Throwable? -> Log.d(TAG, (t ?: " onError") as String) }
            .scrollHandle(DefaultScrollHandle(this))
            .spacing(2)
            .load()
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        mCurrentPage = page
        title = String.format("%s %s / %s", "Page Number", page + 1, pageCount)
    }

    override fun loadComplete(nbPages: Int) {
        if (mCurrentPage >= 0) {
            pdfView!!.jumpTo(mCurrentPage)
        }
        val meta = pdfView!!.documentMeta
        Log.e(TAG, "title = " + meta.title)
        Log.e(TAG, "author = " + meta.author)
        Log.e(TAG, "subject = " + meta.subject)
        Log.e(TAG, "keywords = " + meta.keywords)
        Log.e(TAG, "creator = " + meta.creator)
        Log.e(TAG, "producer = " + meta.producer)
        Log.e(TAG, "creationDate = " + meta.creationDate)
        Log.e(TAG, "modDate = " + meta.modDate)
        Log.d(TAG, "totalPages $nbPages")
        printBookmarksTree(pdfView!!.tableOfContents, "-")
    }

    private fun printBookmarksTree(tree: List<Bookmark>, sep: String) {
        for (b in tree) {
            Log.e(TAG, String.format("%s %s, p %d", sep, b.title, b.pageIdx))
            if (b.hasChildren()) {
                printBookmarksTree(b.children, "$sep-")
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mCurrentPage = savedInstanceState.getInt(KEY_CURRENT_PAGE)
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_CURRENT_PAGE, mCurrentPage)
    }

    companion object {
        private val TAG = PDFActivity::class.java.simpleName
        const val SAMPLE_FILE = "nabi_book.pdf"
        private const val KEY_CURRENT_PAGE = "current_page"
    }
}