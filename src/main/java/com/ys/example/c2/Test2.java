package com.ys.example.c2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/2/26 17:04
 * @Version 1.0
 **/
@Slf4j
public class Test2 {

    private static Object lock = new Object();
    public static void main(String[] args) {

        /*
            1)
                  [
                    {
                        "case":"x<1",
                        "then":{
                            "int":"-1",
                            "decimals":"=2D"
                        }
                    },
                    {
                        "case":"x>=1",
                        "then":{
                            "int":"-1",
                            "decimals":"=3S"
                        }
                    }
                ]


            2)
                     {
                        "case":"-1",
                        "then":{
                            "int":"-1",
                            "decimals":"=2D"
                        }
                    }

            3)
                    {
                        "case":"-1",
                        "then":{
                            "int":"-1",
                            "decimals":"=3S"
                        }
                    }

            4)
                    {
                        "case":"-1",
                        "then":{
                            "int":"<=3S",
                            "decimals":"-1"
                        }
                    }

            5)
                    {
                        "case":"-1",
                        "then":{
                            "int":"-1",
                            "decimals":"<4D,<=3S"
                        }
                    }
         */
        String s = manageList("[{\n" +
                "    \"case\":\"-1\",\n" +
                "    \"then\":{\n" +
                "        \"int\":\"-1\",\n" +
                "        \"decimals\":\"<4D,<=3S\"\n" +
                "    }\n" +
                "}]", "13592.1683",4);

        log.debug("{}",s);
    }


    private static List<Map<String, Object>> getMaps(String roundDesc) {
        List<Map<String,Object>> list = new LinkedList<>();
        JSONArray jsonArray = JSONArray.parseArray(roundDesc);
        for (int i = 0; i <jsonArray.size(); i++) {
            Object o = jsonArray.get(i);
            Map<String,Object>map = new LinkedHashMap<>();
            map.put("case",JSONObject.parseObject(o.toString()).get("case").toString());
            map.put("int",JSONObject.parseObject(JSONObject.parseObject(o.toString()).get("then").toString()).get("int").toString());
            map.put("decimals",JSONObject.parseObject(JSONObject.parseObject(o.toString()).get("then").toString()).get("decimals").toString());
            list.add(map);
        }
        return list;
    }

    private static String manageList(String roundDesc,String result ,int someWay){
        String caseStr = "";
        String intStr = "";
        String decimalsStr = "";
        BigDecimal res = new BigDecimal(result);
        List<Map<String, Object>> list = getMaps(roundDesc);
        Map<String, Object> map0 = list.get(0);
        String case0 = map0.get("case").toString();
        if(!isMinusOneOrNot(case0)){
            String targetStr = case0.substring(case0.length()-1);
            BigDecimal target = new BigDecimal(targetStr);
            int value = getCompareToRes(res, target);
            if(value == -1){
                caseStr = case0;
                intStr =map0.get("int").toString();
                decimalsStr = map0.get("decimals").toString();
            }else{
                Map<String, Object> map1 = list.get(1);
                caseStr = map1.get("case").toString();
                intStr = map1.get("int").toString();
                decimalsStr = map1.get("decimals").toString();
            }
        }else{
            caseStr = case0;
            intStr = map0.get("int").toString();
            decimalsStr = map0.get("decimals").toString();
        }


        //如果不是 -1 ，即需要做 处理
        if(!isMinusOneOrNot(caseStr)){
            //先假设临界值为 1
            String targetStr = caseStr.substring(caseStr.length()-1);
            BigDecimal target = new BigDecimal(targetStr);
            int value = getCompareToRes(res, target);
            synchronized (lock){
                //计算结果 < 临界值
                if(value == -1){
                    //此时num默认为小数位数值
                    int num = gainNum(decimalsStr);
                    return res.setScale(num,someWay).toString();
                }else{
                    //计算结果 >= 临界值
                    //此时的num默认为 有效数字值
                    int num = gainNum(decimalsStr);
                    int validSize = gainNumValidSize(result);
                    int intValidSize = gainIntValidSize(result);
                    if(validSize!=num){
                        return res.setScale(num-intValidSize,someWay).toString();
                    }else{
                        return res.toString();
                    }
                }
            }
        }else{
            //整数位数不为-1  小数位数-1
            if(!isMinusOneOrNot(intStr) && isMinusOneOrNot(decimalsStr)){
                int num = gainNum(intStr);
                int intValidSize = gainIntValidSize(result);
                if(intValidSize<=num){
                    return res.setScale(0,someWay).toString();
                }else{
                    return transferStr(result,num);

                }
            }
            // 整数位 -1  小数位数 不为-1
            if(isMinusOneOrNot(intStr) && !isMinusOneOrNot(decimalsStr)){
                String[] split = decimalsStr.split(",");
                for (String s : split) {
                    String caseOperator = s.substring(0, 2);
                    if("D".equals(gainLastChar(s))){
                        //获取 原始计算结果 . 后的 值  如2.713 ->713
                        int dInt = result.substring(result.lastIndexOf(".") +1).length();
                        int allowDint = gainNum(s);
                        if ("<=".equals(caseOperator)) {
                            if(check(dInt,allowDint,true)){
                                return res.toString();
                            }else{
                                return res.setScale(allowDint,someWay).toString();
                            }
                        }else if(caseOperator.contains("<")){
                            if(check(dInt,allowDint,false)){
                                return res.toString();
                            }else{
                                return res.setScale(allowDint-1,someWay).toString();
                            }
                        }else{
                            return res.setScale(allowDint,someWay).toString();
                        }
                    }else{
                        int num = gainNum(s);
                        int validSize = gainNumValidSize(result);
                        int intValidSize = gainIntValidSize(result);
                        if ("<=".equals(caseOperator)) {
                            if(check(validSize,num,true)){
                                return res.toString();
                            }else{
                                return res.setScale(num-intValidSize,someWay).toString();
                            }
                        }else if(caseOperator.contains("<")){
                            if(check(validSize,num,false)){
                                return res.toString();
                            }else{
                                return res.setScale(num-intValidSize-1,someWay).toString();
                            }
                        }else{
                            return res.setScale(num-intValidSize,someWay).toString();
                        }
                    }
                }

            }
        }
        return res.toString();
    }

    private static boolean check(int a,int b,boolean isContainEqual){
        if(isContainEqual){
            return a<=b;
        }else{
            return a<b;
        }

    }

    private static boolean isMinusOneOrNot(String str){
        return "-1".equals(str);
    }


    private static int getCompareToRes(BigDecimal a,BigDecimal b){
        return a.compareTo(b);
    }

    //获取字符串倒数第一位 字符
    private static String gainLastChar(String str) {
        return String.valueOf(str.charAt(str.length() - 1));
    }


    //获取字符串倒数第二位 字符
    private static int gainNum(String str) {
        return Integer.parseInt(String.valueOf(str.charAt(str.length() - 2)));
    }

    //获取 原始计算结果 整数位 有效数字
    private static int gainIntValidSize(String str){
        String substring = str.substring(0, str.lastIndexOf("."));
        if("0".equals(substring)){
            return 0;
        }else{
            return substring.length();
        }
    }

    //获取原始计算结果 有效数字
    private synchronized static int gainNumValidSize(String str){
        int count = 0;
        String substring1 = str.substring(0, str.lastIndexOf("."));
        String substring2 = str.substring(str.lastIndexOf(".") + 1);
        if(!"0".equals(substring1)){
            count+=substring1.length();
        }
        count+=substring2.length();
        return count;
    }


    public static String transferStr(String str,int offset){
        String substring = str.substring(0, str.lastIndexOf("."));

        char[] chars = substring.toCharArray();
        char[] newChar = new char[chars.length+1];
        for (int i = 0; i < newChar.length; i++) {
            if(i == offset+1){
                if(chars[i-1]>='5'){
                    newChar[i-1]=(char)((int)newChar[i-1]+1);
                    newChar[i] = chars[i-1];
                }
            }else if(i == 0){
                newChar[i] = chars[0];
            }else if(i == 1){
                newChar[i]='.';
            }else{
                newChar[i] = chars[i-1];
            }
        }
        String s = new String(newChar);
        return s.substring(0,offset+1)+"e"+(chars.length-1);
    }

}

