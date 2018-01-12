package com.hnsi.oa.hnsi_oa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hnsi.oa.hnsi_oa.beans.FlowNameEntity;

import java.util.List;

/**
 * 联系人信息表数据库助手类
 * Created by Zheng on 2016/9/6.
 */
public class FlowListTableHelper {

    private MYSQLiteOpenHelper mySQLiteOpenHelper=null;

    public FlowListTableHelper(Context context){
        mySQLiteOpenHelper=new MYSQLiteOpenHelper(context);
    }

    /**
     * 插入一批数据
     * @param names
     * @return 成功的条目数
     */
    public synchronized long insertAll(List<FlowNameEntity> names){
        if (names==null || names.size() == 0){
            return 0;
        }
        SQLiteDatabase writableDB = mySQLiteOpenHelper.getWritableDatabase();
        long result = 0;
        // 开启事务
        writableDB.beginTransaction();
        for (FlowNameEntity flow : names) {

            // selection 首先判断是否存在
            Cursor cursor = writableDB.query(MYSQLiteOpenHelper.DB_TABLE_FLOW_LIST,
                    null, MYSQLiteOpenHelper.TableFlowList.LABEL+" = '" + flow.getLable() + "'", null, null, null, null);
            int n = cursor.getCount();
            cursor.close();
            if (n > 0){
                continue;
            }

            ContentValues values = new ContentValues();
            values.put(MYSQLiteOpenHelper.TableFlowList.TITLE, flow.getTitle());
            values.put(MYSQLiteOpenHelper.TableFlowList.LABEL, flow.getLable());
            result += writableDB.insert(MYSQLiteOpenHelper.DB_TABLE_FLOW_LIST, null, values);
        }
        // 设置事务成功
        writableDB.setTransactionSuccessful();
        // 关闭事务
        writableDB.endTransaction();
        writableDB.close();
        return result;
    }

    /**
     * 清空流程列表
     * @return
     */
    public synchronized boolean deleteAllFlows(){
        int result = 0;
        SQLiteDatabase writableDB = mySQLiteOpenHelper.getWritableDatabase();

        result = writableDB.delete(MYSQLiteOpenHelper.DB_TABLE_FLOW_LIST,
                MYSQLiteOpenHelper.TableFlowList.ID + " > 0 ", null);

        writableDB.close();
        return result > 0;
    }

    /**
     * 根据title查找流程名称
     * @param title
     * @return
     */
    public String getFlowName(String title){
        SQLiteDatabase readableDB= mySQLiteOpenHelper.getReadableDatabase();
        Cursor cursor= readableDB.rawQuery("select * from " + MYSQLiteOpenHelper.DB_TABLE_FLOW_LIST + " where _title = '" + title + "'", null);

        String result= "";

        if (cursor.moveToFirst()){
            result= cursor.getString(cursor.getColumnIndex(MYSQLiteOpenHelper.TableFlowList.LABEL));
        }

        cursor.close();
        readableDB.close();

        return result;
    }

}
