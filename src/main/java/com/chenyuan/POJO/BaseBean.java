package com.chenyuan.POJO;

public class BaseBean {
    private String excelName;
    private String sheetName;

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "excelName='" + excelName + '\'' +
                ", sheetName='" + sheetName + '\'' +
                '}';
    }
}
