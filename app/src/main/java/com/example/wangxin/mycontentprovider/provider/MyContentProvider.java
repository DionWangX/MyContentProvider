package com.example.wangxin.mycontentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    //静态常量，内容提供者存储数据需要的URL，就是AndroidManifest中设置的authorities
    public static final Uri URL = Uri.parse("content://com.example.wangxin.mycontentprovider");
    private SQLiteDatabase sqLiteDatabase;//数据库
    private int isOk = 0;

    @Override
    public boolean onCreate() {
        //打开或创建一个数据库，名字为data.db 私有的
        sqLiteDatabase = getContext().openOrCreateDatabase("data.db", Context.MODE_PRIVATE, null);
        //查找出这个数据库中所有的表
        Cursor cursor = sqLiteDatabase.rawQuery("select name from sqlite_master where type='table';", null);
        //将查询出的结果的指针移到第一个，然后循环
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            //遍历出表名
            String name = cursor.getString(0);
            System.out.println("存在表：" + name);
            //如果存在则不执行下面的if语句中的创建表代码
            if (name == "tab") {
                isOk = 1;
            }
        }
        //创建表代码
        if (isOk == 0)
            sqLiteDatabase.execSQL("create table tab (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL)");
        //这里要返回true
        return true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //查找tab中的数据
        Cursor cursor = sqLiteDatabase.query("tab",null,null,null,null,null,null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //添加数据到表tab中
        sqLiteDatabase.insert("tab","_id",values);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
