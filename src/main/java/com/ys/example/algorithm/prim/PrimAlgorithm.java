package com.ys.example.algorithm.prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 普利母算法
 * @Author 杨帅
 * @Date 2022/6/20 17:07
 * @Version 1.0
 **/
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系用二维数组表示,10000表示两个点不连通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };


        //创建MGraph对象
        MGraph mGraph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        //输出
        minTree.showGraph(mGraph);

        //普利姆算法测试
        minTree.prim(mGraph, 0);
    }


}

//创建最小生成树->村庄的图
class MinTree {

    /**
     * @param graph  图对象
     * @param verxs  图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     * @Description: 创建图的邻接矩阵
     * @Author: 杨帅
     * @Date: 2022/6/20
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] ints : graph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * @param graph 图
     * @param v     表示从图的第几个顶点开始生成 'A'->0 'B'->1...
     * @Description: 编写prim方法，得到最小生成树
     * @Author: 杨帅
     * @Date: 2022/6/20
     */
    public void prim(MGraph graph, int v) {
        //visited表示顶点是否被访问过,默认都为0，表示未被访问
        int[] visited = new int[graph.verxs];
        //标记当前节点为已访问
        visited[v] = 1;
        //h1，h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//将minWeight初始成一个大数，后面在遍历过程中，会被替换
        for (int k = 1; k < graph.verxs; k++) {//因为最终要产生 graph.verxs-1条边
            //确实每一次生成的子图，和哪个节点的距离最近
            for (int i = 0; i < graph.verxs; i++) { //i为访问过的节点
                for (int j = 0; j < graph.verxs; j++) { //j为还没有访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条最小边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            //将当前节点标记为已经访问
            visited[h2] = 1;
            //重置minWeight
            minWeight = 10000;
        }
    }
}


class MGraph {
    int verxs;//表示节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，就是我们的邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
