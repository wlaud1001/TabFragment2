package com.example.user.tabfragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayPhoto extends AppCompatActivity implements View.OnClickListener {

    private Context mContext = null;
    private final int imgWidth = 500;
    private final int imgHeight = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_photo);
        mContext = this;

        /** 전송메시지 */
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String imgPath = extras.getString("filename");

        /**상단바*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String rawImgname = getIntent().getStringExtra("filename");
        int index = rawImgname.indexOf("Camera/");
        rawImgname = rawImgname.substring(index+7);
        Log.i("imgname",rawImgname);
        setTitle(rawImgname); //상단바 title변경

        /** 완성된 이미지 보여주기  */
        BitmapFactory.Options bfo = new BitmapFactory.Options();
        bfo.inSampleSize = 2;
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        Bitmap bm = BitmapFactory.decodeFile(imgPath, bfo);
        Bitmap resized = Bitmap.createScaledBitmap(bm, imgWidth, imgHeight, true);
        iv.setImageBitmap(resized);

        /** 리스트로 가기 버튼 */
        Button btn = (Button)findViewById(R.id.btn_back);
        btn.setOnClickListener(this);
    }
    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_back:
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("status",312);
                startActivity(intent);
                break;
        }
    }

}
