package com.ys.example.algorithm.greedy;

import java.util.*;

/**
 * @Description 贪心算法
 * @Author 杨帅
 * @Date 2022/6/20 10:23
 * @Version 1.0
 **/
public class GreedyAlgorithm {
    public static void main(String[] args) {

        //创建广播电台
        Map<String, Set<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        Set<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        Set<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        Set<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        Set<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        Set<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入到map
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);
        //allAreas 存放所有的地区
        Set<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        //创建ArrayList，存放选择的电台集合
        List<String> selects = new ArrayList<>();
        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和没有覆盖的地区的交集
        Set<String> tempSet = new HashSet<>();

        //定义个maxKey。保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果maxKey不为Null,则会加入到selects中
        String maxKey = null;
        //如果allAreas的大小不为0，则表示还没有覆盖到所有的地区
        while(allAreas.size()!=0){
            //每进行一次while循环，maxKey要置空
            maxKey = null;
            //遍历broadcasts，取出对应的key
            for (String key : broadcasts.keySet()) {
                //每进行一次for，都要清空一次tempSet
                tempSet.clear();
                //当前key能够覆盖的地区
                tempSet.addAll(broadcasts.get(key));
                //取tempSet和allAreas集合的交集
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多，就需要重置maxKey
                if(tempSet.size() > 0 && (maxKey==null || tempSet.size()> broadcasts.get(maxKey).size())){
                    maxKey = key; //k1
                }
            }
            //maxKey!=null,就应该将maxKey加入到selects中
            if(maxKey!=null){
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区，从allAreas去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果是"+selects);

    }
}
