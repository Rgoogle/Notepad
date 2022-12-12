package com.baiyu.notepad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class WriteActivity extends Activity implements View.OnClickListener {
    /**
     * 下一章
     */
    Button nextPage;

    /**
     * 上一张
     */
    Button prePage;
    DBOpenHelper db;


    EditText writerTitle;
    EditText writerContent;

    Intent intent;

    int id;
    int count;
    @SuppressLint("Range")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_page);
        nextPage = findViewById(R.id.next);
        prePage = findViewById(R.id.previous);
        nextPage.setOnClickListener(this);
        prePage.setOnClickListener(this);
        writerTitle = findViewById(R.id.write_title);
        writerContent = findViewById(R.id.write_content);
        intent = getIntent();
        db = new DBOpenHelper(this);
        id = intent.getIntExtra("id", 0);
        count=intent.getIntExtra("count",0);
  
        Cursor cursor = db.queryPageById(id);
//        cursor.moveToNext();

        writerTitle.setText(cursor.getString(cursor.getColumnIndex("title")));
        writerContent.setText(cursor.getString(cursor.getColumnIndex("content")));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:

                nextPage();
                break;
            case R.id.previous:
                previousPage();
                break;
        }
    }

    private void previousPage() {
        if(id==0){
            Toast.makeText(this,"这已经是第一章了",Toast.LENGTH_SHORT).show();
            return;
        }
        id--;
        Cursor cursor = db.queryPageById(id);

    }

    private void nextPage() {

        Cursor cursor = db.queryPageById(id);
//        cursor.get
    }


    /**
     * 返回保存文章
     *
     * @param keyCode
     * @param event
     * @return
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //监听返回事件
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            addArticle();
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void addArticle() {
        String title = writerTitle.getText().toString();
        String content = writerContent.getText().toString();
        db.inserPage(title, content);
    }


}
