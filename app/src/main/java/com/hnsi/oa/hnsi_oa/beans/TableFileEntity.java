package com.hnsi.oa.hnsi_oa.beans;

/**
 * Created by Zheng on 2018/2/9.
 */

public class TableFileEntity {
    private String fileNum= "";
    private String fileName= "";
    private String fileTime= "";
    private String filePath= "";
    private String fileSize= "";

    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "TableFileEntity{" +
                "fileNum='" + fileNum + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileTime='" + fileTime + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }
}
