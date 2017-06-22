package org.androidtown.cheerup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DetailActivity3 extends AppCompatActivity {

    ScrollView scrollView;
    boolean isSearchMenuOpen =false;
    boolean isCategoryMenuOpen=false;
    RelativeLayout SearchMenu;
    RelativeLayout CategoryMenu;

    EditText SearchV;

    //*************firebase를 이용한 댓글*******************
    // DB에 저장시킬 데이터를 입력받는 EditText
    private EditText reply_text;

    // 입력받은 데이터를 저장시킬 버튼
    Button reply_input;

    // 댓글갯수 입력하는 textview
    TextView replies;
    // DB 데이터를 보여줄 ListView
    ListView Reply_listview;

    ArrayAdapter<String> dataAdapter;

    // DB 관련 변수
    private FirebaseDatabase database3;
    private DatabaseReference myRef3;

    String userName;



    InputMethodManager imm;
    int cnt =0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout3);

        replies = (TextView)findViewById(R.id.Replies);

        SearchMenu = (RelativeLayout) findViewById(R.id.searchMenu);
        CategoryMenu = (RelativeLayout) findViewById(R.id.CategoryMenu);


        //firebase 변수 초기화
        reply_text = (EditText)findViewById(R.id.reply_text);
        reply_input = (Button)findViewById(R.id.reply_input);
        Reply_listview = (ListView)findViewById(R.id.Reply_listview);

        final ArrayList<String> list = new ArrayList<String>();

        userName = "user"+ + new Random().nextInt(10000);

        // DB 관련 변수 초기화
        database3 = FirebaseDatabase.getInstance();
        // message Reference가 없어도 상관 x
        myRef3 = database3.getReference("message");

        // ListView에 출력할 데이터 초기화
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());

        // ListView에 Adapter 붙여줌
        Reply_listview.setAdapter(dataAdapter);
        setListViewHeightBasedOnChildren(Reply_listview);

        //댓글 갯수 표시
        replies.setText("댓글("+dataAdapter.getCount()+")");


        // 자신이 얻은 Reference에 이벤트를 붙여줌
        // 데이터의 변화가 있을 때 출력해옴
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // 데이터를 읽어올 때 모든 데이터를 읽어오기때문에 List 를 초기화해주는 작업이 필요하다.
                dataAdapter.clear();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg = messageData.getValue().toString();
                    dataAdapter.add(msg);
                }
                // notifyDataSetChanged를 안해주면 ListView 갱신이 안됨
                dataAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(Reply_listview);
                replies.setText("댓글("+dataAdapter.getCount()+")");
                // ListView 의 위치를 마지막으로 보내주기 위함
                Reply_listview.setSelection(dataAdapter.getCount() - 1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // 버튼 리스너 정의
        // 클릭시 EditText의 내용이 DB에 저장
        reply_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String replyDate = getDateString();
                String str = reply_text.getText().toString().trim();
                str = "<"+userName +">" + "  "+ replyDate +  "\n" + str ;

                myRef3.push().setValue(str);
                // EditText 초기화
                reply_text.setText("");
                dataAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(Reply_listview);
            }
        });




        //*****************************좌측 메뉴이동 시작*************************************
        Button Setting = (Button)findViewById(R.id.Setting);
        Setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(intent);
            }
        });
        Button FavoriteButton = (Button)findViewById(R.id.FavoriteButton);
        FavoriteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FavoriteActivity.class);
                startActivity(intent);
            }
        });
        Button GotoMain = (Button)findViewById(R.id.GotoMain);
        GotoMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        Button Finisehd = (Button)findViewById(R.id.Finished);
        Finisehd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FinishedActivity.class);
                startActivity(intent);
            }
        });

        //*****************************메뉴이동 끝 *************************************

        //*****************************우측 검색 및 필터 시작***************************

        Button SearchBtn = (Button)findViewById(R.id.CompanySearch);
        SearchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                intent.putExtra("SearchValue",SearchV.getText().toString());
                startActivity(intent);
            }
        });
        Button FilterBtn = (Button)findViewById(R.id.CompanyFilter);
        FilterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FilterActivity.class);
                startActivity(intent);
            }
        });

        //****체크박스****
        final CheckBox cbAll = (CheckBox)findViewById(R.id.cbAll);
        final CheckBox cb1 = (CheckBox)findViewById(R.id.cb1);
        final CheckBox cb2 = (CheckBox)findViewById(R.id.cb2);
        final CheckBox cb3 = (CheckBox)findViewById(R.id.cb3);
        final CheckBox cb4 = (CheckBox)findViewById(R.id.cb4);
        final CheckBox cb5 = (CheckBox)findViewById(R.id.cb5);
        final CheckBox cb6 = (CheckBox)findViewById(R.id.cb6);
        final CheckBox cb7 = (CheckBox)findViewById(R.id.cb7);
        final CheckBox cb8 = (CheckBox)findViewById(R.id.cb8);
        final CheckBox cb9 = (CheckBox)findViewById(R.id.cb9);
        final CheckBox cb10 = (CheckBox)findViewById(R.id.cb10);
        final CheckBox cb11 = (CheckBox)findViewById(R.id.cb11);
        final CheckBox cb12 = (CheckBox)findViewById(R.id.cb12);

        cbAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(cbAll.isChecked()){
                    cb1.setChecked(true);
                    cb2.setChecked(true);
                    cb3.setChecked(true);
                    cb4.setChecked(true);
                    cb5.setChecked(true);
                    cb6.setChecked(true);
                    cb7.setChecked(true);
                    cb8.setChecked(true);
                    cb9.setChecked(true);
                    cb10.setChecked(true);
                    cb11.setChecked(true);
                    cb12.setChecked(true);

                }
                else{
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                    cb5.setChecked(false);
                    cb6.setChecked(false);
                    cb7.setChecked(false);
                    cb8.setChecked(false);
                    cb9.setChecked(false);
                    cb10.setChecked(false);
                    cb11.setChecked(false);
                    cb12.setChecked(false);
                }
            }
        });
        //*****************************우측 검색 및 필터 끝*****************************
    }

    //댓글 리스트뷰 길이를 설정해주는 method
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public String getDateString()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String str_date = df.format(new Date());

        return str_date;
    }

    //*****************************액션바 시작******************************************

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.actionbar, null);
        actionBar.setCustomView(actionbar);

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,85,129,57)));

        ImageView HomeButton = (ImageView)findViewById(R.id.HomeButton);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return true;


    }
    //*****************************액션바 끝******************************************

    //*****************************메뉴바 열기 시작***************************************
    public void onSearchMenuClicked(View v){
        openSearchMenu();
    }

    public void openSearchMenu(){
        if(!isSearchMenuOpen) {
            if(isCategoryMenuOpen){
                CategoryMenu.setVisibility(View.INVISIBLE);
                isCategoryMenuOpen = false;
            }
            SearchMenu.setVisibility(View.VISIBLE);
            isSearchMenuOpen = true;
        }
        else if(isSearchMenuOpen){
            SearchMenu.setVisibility(View.INVISIBLE);
            isSearchMenuOpen = false;
        }
    }

    public void onCategoryMenuClicked(View v){
        openCategoryMenu();
    }
    public void openCategoryMenu(){
        if(!isCategoryMenuOpen) {
            if(isSearchMenuOpen){
                SearchMenu.setVisibility(View.INVISIBLE);
                isSearchMenuOpen = false;
            }
            CategoryMenu.setVisibility(View.VISIBLE);
            isCategoryMenuOpen = true;
        }
        else if(isCategoryMenuOpen){
            CategoryMenu.setVisibility(View.INVISIBLE);
            isCategoryMenuOpen = false;
        }
    }

    //*****************************메뉴바 열기 끝***************************************
}
