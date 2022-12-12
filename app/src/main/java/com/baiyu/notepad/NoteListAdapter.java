package com.baiyu.notepad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class NoteListAdapter extends CursorAdapter {
    private LayoutInflater mInflater;
//    private View view;
    public NoteListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        mInflater = LayoutInflater.from(context);
//        view=mInflater.inflate(R.layout.list_item_note,null);
    }

    @SuppressLint("Range")
    @Override
        public void bindView(View view, Context context, Cursor cursor) {
        TextView title =  view.findViewById(R.id.title);
        TextView date =  view.findViewById(R.id.date);
        int _id=cursor.getInt(cursor.getColumnIndex("_id"));
//        System.out.println("shuchu:"+cursor.getString(cursor.getColumnIndex("title")));
        String titleString=cursor.getString(cursor.getColumnIndex("title"));
        String contentString =cursor.getString(cursor.getColumnIndex("content"));
        title.setText(titleString.length()>=30?"标题:"+titleString.substring(0,30)+"...":"标题:"+titleString);
        date.setText(contentString.length()>=30?"内容"+contentString.substring(0,30)+"...":"内容:"+contentString);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return mInflater.inflate(R.layout.list_item_note,viewGroup,false);
    }

    /**
     *重新加载视图，它会强制CursorAdapter重新查询数据库，并重新构建视图。
     */
//    @Override
//    public void notifyDataSetChanged() {
//        System.out.println("fafdafafa");
//    }



}