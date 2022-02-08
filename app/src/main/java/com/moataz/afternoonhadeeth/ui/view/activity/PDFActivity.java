package com.moataz.afternoonhadeeth.ui.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.utils.helper.Views;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

public class PDFActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {
    private static final String TAG = PDFActivity.class.getSimpleName();
    public static final String SAMPLE_FILE = "nabi_book.pdf";
    private int mCurrentPage = 0;
    private final static String KEY_CURRENT_PAGE = "current_page";
    PDFView pdfView;
    String pdfFileName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mCurrentPage = savedInstanceState.getInt(KEY_CURRENT_PAGE);
        } else {
            mCurrentPage = -1;
        }
        setContentView(R.layout.actvity_pdf_view);
        pdfView = findViewById(R.id.pdfView);
        afterViews();
    }

    void afterViews() {
        pdfView.setBackgroundColor(Color.WHITE);
        displayFromAsset();
        setTitle(pdfFileName);
        Views.INSTANCE.intiViews(getWindow());
        Views.INSTANCE.hideStatusBar(getWindow());
    }

    private void displayFromAsset() {
        pdfFileName = PDFActivity.SAMPLE_FILE;
        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(mCurrentPage)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .password(null)
                .enableAntialiasing(true)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onPageScroll((page, positionOffset) -> Log.d(TAG, "onPageScrolled: page " + page + " positionOffset " + positionOffset))
                .onRender((nbPages, pageWidth, pageHeight) -> pdfView.fitToWidth(mCurrentPage))
                .onLoad(nbPages -> Log.d(TAG, "loadComplete: totalPages " + nbPages))
                .onError(t -> Log.d(TAG, " onError"))
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(2)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        mCurrentPage = page;
        setTitle(String.format("%s %s / %s", "Page Number", page + 1, pageCount));
    }

    @Override
    public void loadComplete(int nbPages) {
        if (mCurrentPage >= 0) {
            pdfView.jumpTo(mCurrentPage);
        }
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());
        Log.d(TAG, "totalPages " + nbPages);
        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {
            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentPage = savedInstanceState.getInt(KEY_CURRENT_PAGE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_PAGE, mCurrentPage);
    }
}
