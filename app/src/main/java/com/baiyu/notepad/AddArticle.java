package com.baiyu.notepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import androidx.annotation.Nullable;

public class AddArticle extends Activity {
    EditText title;
    EditText content;

    DBOpenHelper dbOpenHelper;

    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_article);
        title=findViewById(R.id.add_title);
        content=findViewById(R.id.add_content);
//        intent=getIntent();
        dbOpenHelper= new DBOpenHelper(this);

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //监听返回事件
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            String ti=this.title.getText().toString();
            String con=this.content.getText().toString();
            System.out.println("ccccccccc:"+title + " " +content);
            if(ti.length()==0&&con.length()==0){

            }


            saveArticle();
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 返回自动保存文章
     */
    private void saveArticle() {
        String ti=title.getText().toString();
        String con=content.getText().toString();
        dbOpenHelper.inserPage(ti,con);
    }


}
