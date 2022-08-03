package com.ys.example.algorithm.dataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/16 14:52
 * @Version 1.0
 **/
public class Graph {
    /**
     * 存储定点的集合
     */
    private ArrayList<String> vertexList;
    /**
     * 创建图对应的领结矩阵
     */
    private int[][] edges;
    /**
     * 表示边的数目
     */
    private int numOfEdges;
    /**
     * 记录某个顶点是否被访问过
     */
    private boolean[] isVisited;


    public static void main(String[] args) {
        int n = 8; //5个顶点
//        String[] vertexs = {"A", "B", "C", "D", "E"};
        String[] vertexs = {"1", "2", "3", "4", "5","6","7","8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        //添加边
        /*graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);*/

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);


        //显示一把邻接矩阵
        graph.showGraph();
        //测试深度遍历dfs
        System.out.println("深度优先遍历～");
        graph.dfs(); // 1 2 4 8 5 3 6 7
        System.out.println();

        System.out.println("广度优先遍历～");
        graph.bfs(); // 1 2 3 4 5 6 7 8

    }


    public Graph(int n) {
        //根据顶点数n 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     * 得到第一个邻接节点的下标
     * 如果存在，返回对应下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标得到下一个邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历算法
     * i第一次就是0
     */
    public void dfs(boolean[] isVisited, int i) {
        //首先我们访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设置成已访问
        isVisited[i] = true;
        //查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);

        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w已经被访问过,查询下一个邻接节点
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 对dfs进行一个重载，遍历所有的顶点，并进行dfs
     */
    private void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }

    /**
     * @Description: 对一个节点进行广度优先遍历
     * @Author: 杨帅
     * @Date: 2022/6/16
     * @param isVisited
     * @param i
     */
    private void bfs(boolean[] isVisited, int i){
        int u;//表示队列的头节点对应下标
        int w;//邻接节点w
        //队列，记录节点访问的顺序
        LinkedList queue = new LinkedList();
        //访问该节点
        System.out.print(getValueByIndex(i )+ "->");
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);

        while(!queue.isEmpty()){
            //取出队列的头节点下标
            u= (int)queue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while(w!=-1){
                //找到
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+ "->");
                    //标记以访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接节点
                w = getNextNeighbor(u,w); //体现我们的广度优先
            }
        }
    }

    /**
     * @Description: 对bfs进行一个重载，遍历所有的顶点，并进行bfs
     * @Author: 杨帅
     * @Date: 2022/6/16
     * @param
     */
    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }


    /**
     * 得到顶点个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 得到边的个数
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回顶点i(下标)对应的数据
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回v1 v2对应的权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示图对应的邻接矩阵
     */
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 插入节点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * v1 , v2 表示点的下标，即第几个顶点，对应邻接矩阵的行和列
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


}
