package com.ys.example.algorithm.dijkstra;

import java.util.Arrays;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/21 15:07
 * @Version 1.0
 **/
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        //创建一个Graph对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();

        graph.dsj(2);
        graph.showDijkstra();

    }
}

class Graph {
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;//已经访问的顶点的集合

    //构造器

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }
    //显示图

    public void showGraph() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * @param index 出发顶点对应的下标
     * @Description: 迪杰斯科拉算法
     * @Author: 杨帅
     * @Date: 2022/6/21
     */
    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);//更新index下标顶点到周围顶点的距离和前驱顶点
        for (int j = 1; j < vertex.length; j++) {
            index =  vv.updateArr();//选择并返回新的访问节点
            update(index);//更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    public void update(int index) {
        int len = 0;
        //根据遍历我们的邻接矩阵的matrix[index]行
        for (int j = 0; j < matrix[index].length; j++) {
            //len含义：出发顶点到j顶点的总计距离
            len = vv.getDis(index) + matrix[index][j];
            //若j顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index);//更新J顶点的前驱为index
                vv.updateDis(j, len);//更新出发顶点到j顶点的距离为len
            }
        }
    }

    public void showDijkstra(){
        vv.show();
    }
}

//已访问节顶点集合
class VisitedVertex {
    //记录各个顶点是否访问过
    public int[] alreadyArr;
    //每个下标对应的值为前一个顶点下标，会动态更新
    public int[] preVisited;
    //记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离
    //会动态更新，求得最短距离就会放入dis
    public int[] dis;

    /**
     * @param length 顶点的个数
     * @param index  出发顶点对应的下标
     * @Description: 构造器
     * @Author: 杨帅
     * @Date: 2022/6/21
     * @return: null
     */
    public VisitedVertex(int length, int index) {
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis, 65535);
        //设置index下标的对应的顶点已访问
        alreadyArr[index] = 1;
        this.dis[index] = 0;//设置出发顶点的访问距离为0
    }

    /**
     * @param index
     * @Description: 判断index顶点是否被访问过
     * @Author: 杨帅
     * @Date: 2022/6/21
     * @return: boolean
     */
    public boolean in(int index) {
        return alreadyArr[index] == 1;
    }

    //更新出发顶点到index顶点的距离
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    //更新pre顶点的前驱为index顶点
    public void updatePre(int pre, int index) {
        preVisited[pre] = index;
    }

    //返回出发顶点到index顶点的距离
    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的访问顶点，比如这里的G完后，就是A点作为新的访问节点（注意不是出发节点）
    public int updateArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if(alreadyArr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //更新index节点被访问过
        alreadyArr[index] = 1;
        return index;
    }

    //显示最后的结果
    //即将三个数组的情况输出
    public void show(){
        System.out.println("===============");
        for (int i : alreadyArr) {
            System.out.print(i+" ");
        }
        System.out.println();
        for (int i : preVisited) {
            System.out.print(i+" ");
        }
        System.out.println();
        for (int i : dis) {
            System.out.print(i+" ");
        }

        System.out.println();
        //为了方便观察，稍作调整
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            System.out.print(vertex[count++] + "("+i+") ");
        }
    }
}
