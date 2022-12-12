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
        id = intent.getIntExtra("id", -1);
        count=intent.getIntExtra("count",-1);

        Cursor cursor = DBOpenHelper.queryPageById(id);

        System.out.println("sssssss:"+id+"   "+count);
//        Cursor c1=DBOpenHelper.queryPageById(1);
//        System.out.println("cccc:"+c.getCount());
        cursor.moveToNext();

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

    @SuppressLint("Range")
    private void previousPage() {

        if(id==1){
            Toast.makeText(this,"这已经是第一章了",Toast.LENGTH_SHORT).show();
            return;
        }
        id--;
        Cursor cursor = DBOpenHelper.queryPageById(id);

        cursor.moveToNext();

        writerTitle.setText(cursor.getString(cursor.getColumnIndex("title")));
        writerContent.setText(cursor.getString(cursor.getColumnIndex("content")));
    }

    @SuppressLint("Range")
    private void nextPage() {
        if(id==count){
            Toast.makeText(this,"这是最后一章了",Toast.LENGTH_SHORT).show();
            return;
        }
        id++;
        Cursor cursor = DBOpenHelper.queryPageById(id);

        cursor.moveToNext();

        writerTitle.setText(cursor.getString(cursor.getColumnIndex("title")));
        writerContent.setText(cursor.getString(cursor.getColumnIndex("content")));

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
        DBOpenHelper.inserPage(title, content);
    }


}
