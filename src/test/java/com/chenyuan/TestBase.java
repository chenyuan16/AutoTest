package com.chenyuan;

import com.chenyuan.Utils.ReportUtil;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestBase {
    /**
     * 创建一个Map，作为公共的数据池（全局可用）
     * */
    private static Map<String,String> saveDatas = new HashMap<>();
    //替换符，如果从excle表中的数值，包含"${}"，则会被替换成公共数据池中存储的数据
    protected Pattern replaceParamPattern = Pattern.compile("\\$\\{(.*?)\\}");
    //截取自定义方法正则表达式：_xxx(ooo),暂时先不实现
    protected Pattern funPattern = Pattern.compile("\"__(\\\\w*?)\\\\((([\\\\w\\\\\\\\\\\\/:\\\\.\\\\$]*,?)*)\\\\)");
    //调用map对象的putAll(map)方法，将整个map放到公共的数池中
    protected void setSaveDates(Map<String, String> map) {
        poolDatas.putAll(map);
    }

    protected void savePreParam(String preParam) {
        // 通过';'分隔，将参数加入公共参数map中
        if (StringUtil.isEmpty(preParam)) {
            return;
        }
        String[] preParamArr = preParam.split(";");
        String key, value;
        for (String prepar : preParamArr) {
            if (StringUtil.isEmpty(prepar)) {
                continue;
            }
            key = prepar.split("=")[0];
            value = prepar.split("=")[1];
            ReportUtil.log(String.format("存储%s参数，值为：%s。", key, value));
            saveDatas.put(key, value);
        }
    }
    /**
     * 取公共参数 并替换参数
     *
     * @param param
     * @return
     */
    protected String getCommonParam(String param) {
        if (StringUtil.isEmpty(param)) {
            return "";
        }
        Matcher m = replaceParamPattern.matcher(param);// 取公共参数正则
        while (m.find()) {
            String replaceKey = m.group(1);
            String value;
            // 从公共参数池中获取值
            value = getSaveData(replaceKey);
            // 如果公共参数池中未能找到对应的值，该用例失败。
            Assert.assertNotNull(value,
                    String.format("格式化参数失败，公共参数中找不到%s。", replaceKey));
            param = param.replace(m.group(), value);
        }
        return param;
    }
    /**
     * 获取公共数据池中的数据
     *
     * @param key
     *            公共数据的key
     * @return 对应的value
     */
    protected String getSaveData(String key) {
        if ("".equals(key) || !saveDatas.containsKey(key)) {
            return null;
        } else {
            return saveDatas.get(key);
        }

    //读取Excle入口方法
    public List readExcleData(String exclePathArr,String [] sheetNameArr){
        //未完成
        List allExcelData = new ArrayList();// excel文件數組

        return allExcelData;
    }
}
