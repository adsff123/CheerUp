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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchMenu = (RelativeLayout) findViewById(R.id.searchMenu);
        CategoryMenu = (RelativeLayout) findViewById(R.id.CategoryMenu);


        //*****************************리스트뷰 시작*************************************
        listview_insa = (ListView) findViewById(R.id.listView_insa);
        listView_Suwon = (ListView) findViewById(R.id.listView_Suwon);

        adapter = new SingerAdapter();

        adapter.addItem(new SingerItem("금융감독원", "2017.05.18", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("(주)한국존슨앤드존슨", "2017.05.20", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("한국주택금융공사", "2017.05.25", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("한영회계법인", "2017.05.27", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("삼천리", "2017.06.02", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("(주)롯데그룹", "2017.06.08", R.drawable.star_big_off));

        listview_insa.setAdapter(adapter);


        adapter2 = new SingerAdapter();

        adapter2.addItem(new SingerItem("자과캠1","2017.05.05", R.drawable.star_big_off));
        adapter2.addItem(new SingerItem("자과캠2","2017.05.05", R.drawable.star_big_off));
        adapter2.addItem(new SingerItem("자과캠3","2017.05.05", R.drawable.star_big_off));
        adapter2.addItem(new SingerItem("자과캠4","2017.05.05", R.drawable.star_big_off));

        listView_Suwon.setAdapter(adapter2);

        //---리스트뷰 클릭-----

        listview_insa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_LONG).show();
            }
        });

        //*****************************리스트뷰 끝***************************************


        //*****************************좌측 메뉴이동 시작*************************************
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

        //*****************************메뉴이동 끝 *************************************

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



