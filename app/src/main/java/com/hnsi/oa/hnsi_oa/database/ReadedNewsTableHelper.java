package com.hnsi.oa.hnsi_oa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Zheng on 2018/2/5.
 */

public class ReadedNewsTableHelper {

    private MYSQLiteOpenHelper mysqLiteOpenHelper= null;

    public ReadedNewsTableHelper(Context context){
        mysqLiteOpenHelper= new MYSQLiteOpenHelper(context);
    }

    /**
     * 插入一条新闻id
     * @param readedNewsId
     * @return
     */
    public synchronized boolean insert(int readedNewsId){
        if ( readedNewsId== 0 ){
            return false;
        }
        SQLiteDatabase writableDB= mysqLiteOpenHelper.getWritableDatabase();

        //判断是否已保存这一条新闻
        Cursor cursor= writableDB.query(MYSQLiteOpenHelper.DB_TABLE_READED_NEWS,
                null, MYSQLiteOpenHelper.TableReadedNews.NEWSID+ " = "+readedNewsId, null, null, null, null);
        boolean isExist= cursor.moveToFirst();
        cursor.close();

        long result= 0;
        //如果不存在
        if (!isExist){
            ContentValues values= new ContentValues();
            values.put(MYSQLiteOpenHelper.TableReadedNews.NEWSID, readedNewsId);
            result += writableDB.insert(MYSQLiteOpenHelper.DB_TABLE_READED_NEWS, null, values);
        }

        writableDB.close();

        return result>0;
    }

    /**
     * 这条新闻是否已读
     * @param newsId
     * @return
     */
    public boolean isNewsReaded(int newsId){
        if (newsId== 0){
            return false;
        }
        SQLiteDatabase readableDB= mysqLiteOpenHelper.getReadableDatabase();

        Cursor cursor= readableDB.rawQuery("select * from " + MYSQLiteOpenHelper.DB_TABLE_READED_NEWS + " where _newsid = "+ newsId, null);
        boolean result= false;
        if (cursor.moveToFirst()){
            result= true;
        }

        cursor.close();
        readableDB.close();

        return result;
    }

}
