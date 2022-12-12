package com.baiyu.notepad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    /**
     * 显示的内容
     */
    EditText editTextContent;

    /**
     * ListView 显示界面
     */
    ListView listView;
    /**
     * 数据库查询适配器
     */
    public CursorAdapter cursorAdapter;
    /**
     * 添加文章按钮
     */
    Button addArticleButton;
    /**
     * 数据库操作
     */
    public DBOpenHelper dbOpenHelper;
    /**
     * 日记数
     */
    static int count;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextContent = findViewById(R.id.content);
        addArticleButton = findViewById(R.id.adddailog);
        addArticleButton.setOnClickListener(this);
        dbOpenHelper = new DBOpenHelper(this);
//        dbOpenHelper.inserPage("tittle", "ceshicontent");
//        dbOpenHelper.inserPage("tittle1", "ceshicontent1");
        Cursor c = DBOpenHelper.queryPage();
//        while (c.moveToNext()) {
//
//            System.out.println("ceshi:"+c.getString(c.getColumnIndex("title")));
//        }
        cursorAdapter = new NoteListAdapter(this, c, true);


        listView = findViewById(R.id.listView);
        listView.setAdapter(cursorAdapter);
        listView.setOnItemClickListener(this);
    }


    /**
     * activity变为在屏幕上对用户可见时调用.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Cursor c = DBOpenHelper.queryPage();
        count = c.getCount();
        cursorAdapter.swapCursor(c);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Cursor c = dbOpenHelper.queryPage();
//        cursorAdapter.swapCursor(c);
//    }

//    /**
//     * @param adapterView 被点击的AdapterView 可以获取当前点击的AdapterView
//     * @param view        被点击的View 可以获取当前点击的哪个
//     * @param i           当前点击的行号
//     * @param l           获取当前点击的行id
//     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(this, WriteActivity.class);

        int currow= (int) l;
        int curtotal= (int) l;
        intent.putExtra("id", currow);
        intent.putExtra("count", count);
        System.out.println("tttttt:"+currow+"   "+count);
        startActivity(intent);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.adddailog:
                addArticle();
                break;


        }
    }

    /**
     * 添加文章方法
     */
    private void addArticle() {
        Intent intent = new Intent(this, AddArticle.class);
//        intent.putExtra("dbhelper", dbOpenHelper);
        startActivity(intent);
    }


}