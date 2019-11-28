package com.temp.myasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

public class AsyncTaskImgUpdate extends AsyncTask<URL, Integer, Bitmap[]> {
    private WeakReference<TextView> mTextView ;
    ProgressBar progressBar;
    ImageView img1 , img2 ,img3 ;
    Button button;

    public void setButton(Button button)
    {
        this.button = button;
    }
    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
    public void setImageView( ImageView img1 , ImageView img2 , ImageView img3  )
    {
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;

    }

    public AsyncTaskImgUpdate() {

    }

    @Override
    protected Bitmap[] doInBackground(URL... urls) {
        Bitmap[] bitmaps = new Bitmap[urls.length];
        for (int i = 0; i < urls.length; i++) {
            try {
                int percentProgress = (int) (((i+1) / (float) urls.length -.1f) * 100);
                publishProgress(percentProgress);
                bitmaps[i] = BitmapFactory.decodeStream(urls[i].openConnection().getInputStream());
            } catch (IOException e) {
            }
        }
        return bitmaps;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        progressBar.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(Bitmap[] bitmaps) {
        progressBar.setVisibility(View.GONE);
        button.setEnabled(true);
        img1.setImageBitmap( bitmaps[0]);
        img2.setImageBitmap( bitmaps[1]);
        img3.setImageBitmap( bitmaps[2]);
    }
}
