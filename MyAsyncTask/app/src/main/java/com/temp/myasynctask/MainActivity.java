package com.temp.myasynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TEXT_STATE = "currentText";
    TextView mTextView ;
    ImageView img1 , img2, img3 ;
    ProgressBar progressBar;
    Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextView = findViewById( R.id.mTextView) ;

        img1 = findViewById( R.id.img1) ;
        img2 = findViewById( R.id.img2) ;
        img3 = findViewById( R.id.img3) ;
        button = findViewById( R.id.button) ;

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        if(savedInstanceState != null)
        {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    // EXECUTES ON BUTTON CLICK
    public void startTask(View view) throws MalformedURLException {
//        mTextView.setText("Button Pressed. AsyncTask Started ...");

        button.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
        img1.setImageDrawable(null);
        img2.setImageDrawable(null);
        img3.setImageDrawable(null);

        URL url = new URL("https://placeimg.com/640/480/any");
        AsyncTaskImgUpdate asyncTaskImgUpdate = new AsyncTaskImgUpdate();

        asyncTaskImgUpdate.setProgressBar( progressBar);
        asyncTaskImgUpdate.setImageView(img1 , img2,img3);
        asyncTaskImgUpdate.setButton(button);
        asyncTaskImgUpdate.execute(url, url, url );
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
}
