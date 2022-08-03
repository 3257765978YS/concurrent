package com.ys.example.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  -Xmx10m -XX:-UseGCOverheadLimit
 * @Author 杨帅
 * @Date 2022/6/4 17:28
 * @Version 1.0
 **/
public class Demo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        try {
            for (int j = 0; j < 260000; j++) {
                list.add(String.valueOf(j).intern());
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(i);
        }
    }
}
