package com.baiyu.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.icu.util.VersionInfo;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DBOpenHelper extends SQLiteOpenHelper  {

    private static final int DB_VERSION = 1;

    public static String getDbName() {
        return DB_NAME;
    }

    public static void setDbName(String dbName) {
        DB_NAME = dbName;
    }

    /**
     * 数据库名
     */
    private static String DB_NAME = "notepad.db";

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static void setTableName(String tableName) {
        TABLE_NAME = tableName;
    }

    /**
     * 数据库表名
     */
    public static String TABLE_NAME = "page";

    public static SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        DBOpenHelper.db = db;
    }

    /**
     *
     */
    static SQLiteDatabase db;




    public DBOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null,DB_VERSION);
        db = getWritableDatabase();
    }





    /**
     * 调用时刻：当数据库第1次创建时调用
     * 作用：创建数据库 表 & 初始化数据
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //创建数据库sql语句并执行
        String sql = "create table if NOT EXISTS " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,title text ,content text)";
        // 注：数据库实际上是没被创建 / 打开的（因该方法还没调用）
        // 直到getWritableDatabase() / getReadableDatabase() 第一次被调用时才会进行创建 / 打开
        db.execSQL(sql);

    }

    /**
     * TODO 更改数据库版本的操作
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入日记
     *
     * @param title
     * @param content
     */
    public static void inserPage(String title, String content) {
        String sql = "INSERT INTO " + TABLE_NAME + " (title, content) VALUES (?, ?)";
        Object[] bindArgs = new Object[]{title, content};
        db.execSQL(sql, bindArgs);
        System.out.println("zhixingconghi");
    }

    /**
     * 删除日记
     *
     * @param title
     */
    public static void deletePage(String title) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE　title=?";
        Object[] bindArgs = new Object[]{title};
        db.execSQL(sql, bindArgs);
    }


    /**
     * 查询所有文章
     *
     * @return
     */
    public static Cursor queryPage() {
        Cursor cursor = db.query(TABLE_NAME, new String[]{"_id", "title", "content"}, null, null, null, null, null);
        return cursor;
    }

    /**
     * 靠标题来查询
     *
     * @param title
     * @return
     */
    public static Cursor queryPageBytitle(String title) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE title=?";
        Cursor cursor = db.rawQuery(sql, new String[]{title});
        return cursor;
    }

    public static void updatePageTitleAndContent(String title, String content) {
//        String sql="UPDATE "+TABLE_NAME+" SET title=?,content=? WHERE title=?";
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        //表名 修改的值 包装在一个对象里面      条件  条件的值
        db.update(TABLE_NAME, values, "title=?", new String[]{title});
    }


    public static Cursor queryPageById(int id){
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE _id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        return cursor;

    }


//    private void writeObject(ObjectOutputStream out) throws IOException {
//        out.defaultWriteObject();
//        out.writeBytes(TABLE_NAME);
//        out.writeBytes(DB_NAME);
//        out.writeObject(db);
//    }
//
//    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//        in.defaultReadObject();
//        db = (SQLiteDatabase) in.readObject();
//        DB_NAME= String.valueOf(in.readByte());
//        TABLE_NAME= String.valueOf(in.readByte());
//    }



}
