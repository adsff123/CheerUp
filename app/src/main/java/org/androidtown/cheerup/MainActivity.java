package org.androidtown.cheerup;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        adapter = new SingerAdapter();

        adapter.addItem(new SingerItem("금융감독원", "2017.05.18", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("(주)한국존슨앤드존슨", "2017.05.20", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("한국주택금융공사", "2017.05.25", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("한영회계법인", "2017.05.27", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("삼천리", "2017.06.02", R.drawable.star_big_off));
        adapter.addItem(new SingerItem("(주)롯데그룹", "2017.06.08", R.drawable.star_big_off));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : " + item.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

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
            view.setName(item.getName());
            view.setDate(item.getDate());
            view.setImage(item.getResId());

            return view;
        }
    }

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
        return true;
    }
}



