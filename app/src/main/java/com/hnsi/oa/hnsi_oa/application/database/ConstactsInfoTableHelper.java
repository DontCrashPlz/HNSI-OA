package com.hnsi.oa.hnsi_oa.application.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hnsi.oa.hnsi_oa.application.beans.PersonEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人信息表数据库助手类
 * Created by Zheng on 2016/9/6.
 */
public class ConstactsInfoTableHelper {

    private MYSQLiteOpenHelper mySQLiteOpenHelper=null;

    public ConstactsInfoTableHelper(Context context){
        mySQLiteOpenHelper=new MYSQLiteOpenHelper(context);
    }

    /**
     * 插入一批数据
     * @param contacts
     * @return 成功的条目数
     */
    public synchronized long insertAll(List<PersonEntity> contacts){
        if (contacts==null || contacts.size() == 0){
            return 0;
        }
        SQLiteDatabase writableDB = mySQLiteOpenHelper.getWritableDatabase();
        long result = 0;
        // 开启事务
        writableDB.beginTransaction();
        for (PersonEntity contact : contacts) {

            // selection 首先判断是否存在
            Cursor cursor = writableDB.query(MYSQLiteOpenHelper.DB_TABLE_CONTACTS_INFO,
                    null, MYSQLiteOpenHelper.TableContactsInfo.EMPID+" = "+contact.getEmpid(), null, null, null, null);
            int n = cursor.getCount();
            cursor.close();
            if (n > 0){//该条数据已存在
                continue;
            }

            final ContentValues values = new ContentValues();
            values.put(MYSQLiteOpenHelper.TableContactsInfo.EMPNAME,contact.getEmpname());
            values.put(MYSQLiteOpenHelper.TableContactsInfo.MOBILENO,contact.getMobileno());
            values.put(MYSQLiteOpenHelper.TableContactsInfo.OTEL,contact.getOtel());
            values.put(MYSQLiteOpenHelper.TableContactsInfo.OEMAIL,contact.getOemail());
            values.put(MYSQLiteOpenHelper.TableContactsInfo.SEX,contact.getSex());
            values.put(MYSQLiteOpenHelper.TableContactsInfo.ORGID,contact.getOrgid());
            values.put(MYSQLiteOpenHelper.TableContactsInfo.ORGNAME,contact.getOrgname());
            values.put(MYSQLiteOpenHelper.TableContactsInfo.POSINAME,contact.getPosiname());
            values.put(MYSQLiteOpenHelper.TableContactsInfo.EMPID,contact.getEmpid());
            values.put(MYSQLiteOpenHelper.TableContactsInfo.HEADIMG,contact.getHeadimg());
            result += writableDB.insert(MYSQLiteOpenHelper.DB_TABLE_CONTACTS_INFO, null, values);
        }
        // 设置事务成功
        writableDB.setTransactionSuccessful();
        // 关闭事务
        writableDB.endTransaction();
        writableDB.close();
        return result;
    }

    /**
     * 删除所有通讯录纪录
     * @return
     */
    public synchronized boolean deleteAllContacts(){
        int result = 0;
        SQLiteDatabase writableDB = mySQLiteOpenHelper.getWritableDatabase();

        result = writableDB.delete(MYSQLiteOpenHelper.DB_TABLE_CONTACTS_INFO,
                MYSQLiteOpenHelper.TableContactsInfo.EMPID + " > 0 ", null);

        writableDB.close();
        return result > 0;
    }

    /**
     * 获取部门中的所有人员
     * @param orgid
     * @return
     */
    public ArrayList<PersonEntity> queryAllOrgidContacts(int orgid){
        SQLiteDatabase readableDB = mySQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = readableDB.rawQuery("select * from " + MYSQLiteOpenHelper.DB_TABLE_CONTACTS_INFO + " where _orgid = " + orgid, null);

        ArrayList<PersonEntity> mContactsList = new ArrayList<>();
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                final PersonEntity entity = createDepartmentDetailEntity(cursor);
                if (entity != null){
                    mContactsList.add(entity);
                }
                cursor.moveToNext();
            }
        }

        cursor.close();
        readableDB.close();

        return mContactsList;
    }

//    /**
//     * 获取部门中的所有人员
//     * @param orgid
//     * @return
//     */
//    public ArrayList<ItemData> queryAllOrgidItemDatas(int orgid){
//        SQLiteDatabase readableDB = mySQLiteOpenHelper.getReadableDatabase();
//        Cursor cursor = readableDB.rawQuery("select * from " + MYSQLiteOpenHelper.DB_TABLE_CONTACTS_INFO + " where _orgid = " + orgid, null);
//
//        ArrayList<ItemData> mContactsList = new ArrayList<>();
//        if (cursor.moveToFirst()){
//            while(!cursor.isAfterLast()){
//                final ItemData entity = createItemData(cursor);
//                if (entity != null){
//                    mContactsList.add(entity);
//                }
//                cursor.moveToNext();
//            }
//        }
//
//        cursor.close();
//        readableDB.close();
//
//        return mContactsList;
//    }

    /**
     * 获取部门中的人数
     * @param orgid
     * @return
     */
    public int queryContactNumByOrgid(int orgid){
        SQLiteDatabase readableDB = mySQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = readableDB.rawQuery("select * from " + MYSQLiteOpenHelper.DB_TABLE_CONTACTS_INFO + " where _orgid = " + orgid, null);

        int result=cursor.getCount();

        cursor.close();
        readableDB.close();

        return result;
    }

    /**
     * 生成联系人对象
     * @param cursor
     * @return
     */
    protected PersonEntity createDepartmentDetailEntity(Cursor cursor){
        PersonEntity entity = null;
        if (cursor != null){
            entity = new PersonEntity();
            try {
                entity.setEmpname(cursor.getString(cursor.getColumnIndex(MYSQLiteOpenHelper.TableContactsInfo.EMPNAME)));
                entity.setMobileno(cursor.getString(cursor.getColumnIndex(MYSQLiteOpenHelper.TableContactsInfo.MOBILENO)));
                entity.setOtel(cursor.getString(cursor.getColumnIndex(MYSQLiteOpenHelper.TableContactsInfo.OTEL)));
                entity.setOemail(cursor.getString(cursor.getColumnIndex(MYSQLiteOpenHelper.TableContactsInfo.OEMAIL)));
                entity.setSex(cursor.getString(cursor.getColumnIndex(MYSQLiteOpenHelper.TableContactsInfo.SEX)));
                entity.setOrgid(cursor.getInt(cursor.getColumnIndex(MYSQLiteOpenHelper.TableContactsInfo.ORGID)));
                entity.setOrgname(cursor.getString(cursor.getColumnIndex(MYSQLiteOpenHelper.TableContactsInfo.ORGNAME)));
                entity.setPosiname(cursor.getString(cursor.getColumnIndex(MYSQLiteOpenHelper.TableContactsInfo.POSINAME)));
                entity.setEmpid(cursor.getInt(cursor.getColumnIndex(MYSQLiteOpenHelper.TableContactsInfo.EMPID)));
                entity.setHeadimg(cursor.getString(cursor.getColumnIndex(MYSQLiteOpenHelper.TableContactsInfo.HEADIMG)));
            } catch (Exception e) {
            }
        }
        return entity;
    }

    /**
     * 判断数据库是否有效
     * @return
     */
    public boolean isDatabaseValid(){
        SQLiteDatabase readableDB = mySQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = readableDB.rawQuery("select * from " + MYSQLiteOpenHelper.DB_TABLE_CONTACTS_INFO + " where _empid > " + 0, null);

        boolean result= false;

        if (cursor.moveToFirst()){
            result= true;
        }

        cursor.close();
        readableDB.close();
        return result;
    }

    /**
     * 根据名字模糊搜索联系人
     * @param name
     * @return 联系人列表
     */
    public ArrayList<PersonEntity> searchByName(String name){

        ArrayList<PersonEntity> resultList=new ArrayList<>();

        SQLiteDatabase readableDB = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor=readableDB.rawQuery("select * from " + MYSQLiteOpenHelper.DB_TABLE_CONTACTS_INFO + " where _empname like '%" + name + "%'", null);

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                final PersonEntity entity = createDepartmentDetailEntity(cursor);
                if (entity != null){
                    resultList.add(entity);
                }
                cursor.moveToNext();
            }
        }

        cursor.close();
        readableDB.close();

        return resultList;
    }

    /**
     * 根据号码搜索联系人
     * @param tel
     * @return 联系人列表
     */
    @Deprecated
    public List<PersonEntity> searchByTel(String tel){

        List<PersonEntity> resultList=new ArrayList<>();

        SQLiteDatabase readableDB = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor=readableDB.rawQuery("select * from " + MYSQLiteOpenHelper.DB_TABLE_CONTACTS_INFO + " where _mobileno like ? or _otel like ? " ,
                new String[]{"%"+tel+"%","%"+tel+"%"});

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                final PersonEntity entity = createDepartmentDetailEntity(cursor);
                if (entity != null){
                    resultList.add(entity);
                }
                cursor.moveToNext();
            }
        }

        cursor.close();
        readableDB.close();

        return resultList;
    }

    /**
     * 根据id搜索联系人
     * @param empid
     * @return
     */
    public PersonEntity searchByEmpid(int empid){

        SQLiteDatabase readableDB = mySQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = readableDB.rawQuery("select * from " + MYSQLiteOpenHelper.DB_TABLE_CONTACTS_INFO + " where _empid = " + empid, null);

        PersonEntity entity=null;
        if (cursor.moveToFirst()){
            entity=createDepartmentDetailEntity(cursor);
        }

        cursor.close();
        readableDB.close();

        return entity;
    }

}
