package org.androidtown.cheerup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SingerAdapter adapter;
    SingerAdapter adapter2;

    boolean isSearchMenuOpen =false;
    boolean isCategoryMenuOpen=false;
    boolean isInsaCampus = false;
    boolean isJagaCampus = false;
    RelativeLayout SearchMenu;
    RelativeLayout CategoryMenu;
    ListView listView_Suwon;
    ListView listview_insa;

    EditText SearchV;
    ImageView mainLikeButton1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchMenu = (RelativeLayout) findViewById(R.id.searchMenu);
        CategoryMenu = (RelativeLayout) findViewById(R.id.CategoryMenu);

        mainLikeButton1 = (ImageView)findViewById(R.id.mainLikeButton1);


        //*****************************리스트뷰 시작*************************************
        listview_insa = (ListView) findViewById(R.id.listView_insa);
        listView_Suwon = (ListView) findViewById(R.id.listView_Suwon);


        adapter = new SingerAdapter();

        adapter.addItem(new SingerItem("HOPES", "2017.06.29", R.drawable.star_big_on));
        adapter.addItem(new SingerItem("삼일회계법인", "2017.06.29", R.drawable.star_big_on));
        adapter.addItem(new SingerItem("삼정회계법인", "2017.06.28", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("대한무역투자진흥공사", "2017.06.01", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("네이버(주)", "2017.05.24", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("존슨앤드존슨", "2017.05.17", R.drawable.star_big_off));

        listview_insa.setAdapter(adapter);


        adapter2 = new SingerAdapter();

        adapter2.addItem(new SingerItem("LG생활건강","2017.06.19", R.drawable.star_big_off));
        adapter2.addItem(new SingerItem("지에스이피에스 주식회사","2017.06.08", R.drawable.star_big_off));
        adapter2.addItem(new SingerItem("네이버(주)","2017.05.23", R.drawable.star_big_off));
        adapter2.addItem(new SingerItem("한국전력기술","2017.05.08", R.drawable.star_big_off));

        listView_Suwon.setAdapter(adapter2);

        //---리스트뷰 클릭-----

        listview_insa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem pos = (SingerItem) adapter.getItem(position);
                if(pos.getName()=="HOPES"){
                    Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),DetailActivity2.class);
                    startActivity(intent);
                }

            }
        });
        listView_Suwon.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),DetailActivity3.class);
                startActivity(intent);
            }
        });


        //*****************************리스트뷰 끝***************************************


        //*****************************좌측 메뉴이동 시작********************************
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
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
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
        SearchV = (EditText)findViewById(R.id.searchV);
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

        //*****************************인사자과선택*************************************
        RadioButton Insa = (RadioButton)findViewById(R.id.Insa);
        Insa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isInsaCampus){
                    if(isJagaCampus){
                        listView_Suwon.setVisibility(View.INVISIBLE);
                        isJagaCampus = false;
                    }
                    listview_insa.setVisibility(View.VISIBLE);
                    isInsaCampus=true;
                }
            }
        });
        RadioButton Jaga = (RadioButton)findViewById(R.id.Jaga);
        Jaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isJagaCampus){
                    if(isInsaCampus){
                        listview_insa.setVisibility(View.INVISIBLE);
                        isInsaCampus = false;
                    }
                    if(!isInsaCampus){
                        listview_insa.setVisibility(View.INVISIBLE);
                        isInsaCampus = false;
                    }
                    listView_Suwon.setVisibility(View.VISIBLE);
                    isJagaCampus=true;
                }
            }
        });
        //*****************************인사자과선택 끝*************************************

    }

    //*****************************리스트뷰 시작*************************************
    class SingerAdapter extends BaseAdapter{
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            SingerItemVIew view = new SingerItemVIew(getApplicationContext());
            SingerItem item = items.get(position);


            view.setDate(item.getDate());
            view.setName(item.getName());
            view.setImage(item.getResId());

            return view;
        }
    }
    //*****************************리스트뷰 끝***************************************

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



