package org.androidtown.cheerup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Random;

/**
 * Created by USER on 2017-06-20.
 */

public class TestActivity extends AppCompatActivity {
    ListView listView;
    EditText editText;
    Button sendButton;
    String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        listView = (ListView) findViewById(R.id.test_listview);
        editText = (EditText) findViewById(R.id.test_eidtText);
        sendButton = (Button) findViewById(R.id.test_button);
        super.onCreate(savedInstanceState);

        userName = "user" + new Random().nextInt(10000);


    }
}
