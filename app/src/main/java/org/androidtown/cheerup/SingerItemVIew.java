package org.androidtown.cheerup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemVIew extends LinearLayout {
    TextView textView;
    EditText editText;
    ImageView imageView;

    public SingerItemVIew(Context context){
        super(context);
        init(context);
    }
    public SingerItemVIew(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        textView = (TextView) findViewById(R.id.mainTextVIew1);
        editText = (EditText) findViewById(R.id.mainTextDate1);
        imageView = (ImageView) findViewById(R.id.mainLikeButton1);
    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setDate(String date){
        editText.setText(date);
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }


}
