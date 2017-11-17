package com.hnsi.oa.hnsi_oa.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Xml;

import com.hnsi.oa.hnsi_oa.beans.UpdateInfoEntity;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

/**
 * Created by Zheng on 2017/10/18.
 */

public class Tools {

    /**
     * 用pull解析器解析服务器返回的xml文件 (xml封装了版本号)
     * @param is 服务器读取输入流
     * @return 版本更新信息实体
     * @throws Exception
     */
    public static UpdateInfoEntity updateInfoParser(InputStream is) throws Exception{
        XmlPullParser parser= Xml.newPullParser();
        parser.setInput(is, "utf-8");
        int type= parser.getEventType();
        UpdateInfoEntity entity= new UpdateInfoEntity();
        while (type!= XmlPullParser.END_DOCUMENT){
            if (XmlPullParser.START_TAG== type){
                if("version".equals(parser.getName())){
                    entity.setVersion(parser.nextText());	//获取版本号
                }else if ("description".equals(parser.getName())){
                    entity.setDescription(parser.nextText());	//获取该文件的信息
                }else if ("url".equals(parser.getName())){
                    entity.setUrl(parser.nextText());	//获取要升级的APK文件
                }
            }
            type= parser.next();
        }
        return entity;
    }

    /**
     * Get current VersionName.
     * @param context
     * @return
     * @throws Exception
     */
    private static String getVersion(Context context) throws Exception{
        PackageManager packageManager= context.getPackageManager();
        PackageInfo packageInfo= packageManager.getPackageInfo(context.getPackageName(),0);
        return  packageInfo.versionName;
    }

    /**
     * 拼接IP地址的方法，将用户输入的ip地址和端口号拼接在一起
     * @return
     */
    public static String jointIpAddress(){
        StringBuilder builder=new StringBuilder();
        builder.append("http://");
//        builder.append((String) SharedPrefUtils.get(MyApplication.getSingleton(), AppConstants.SP_IPADDRESS, "192.168.1.68"));
        builder.append(":");
//        builder.append((String)SharedPrefUtils.get(MyApplication.getSingleton(), AppConstants.SP_PORTNUM,"80"));
        return builder.toString();
    }

}
