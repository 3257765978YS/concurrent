package com.ys.example.algorithm.horseChessBoard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Description 马踏棋盘
 * @Author 杨帅
 * @Date 2022/6/21 21:01
 * @Version 1.0
 **/
public class HorseChessBoard {
    //定义属性
    private final static int X = 6; //表示col
    private final static int Y = 6; //表示row
    private static int[][] chessBoard = new int[Y][X];
    private static boolean[] visited = new boolean[X * Y];
    private static boolean finished = false;

    public static void main(String[] args) {

        int row = 2;
        int col = 2;
        //起始时间
        long start = System.currentTimeMillis();
        //测试
        traversalChessBoard(chessBoard,row-1,col-1,1);
        //终止时间
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start)+"ms");

        //输出当前棋盘的情况
        for (int[] rows : chessBoard) {
            for (int step : rows) { //step表示该位置是马 应该走的第几步
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    //编写核心方法，遍历棋盘，若遍历成功，就将finished置为true
    //并将马儿走的每一步记录在chessBoard中
    public static void traversalChessBoard(int[][] chessBoard, int row,int col,int step) {//row表示行，col表示列
        //先把step记录到chessBoard中
        chessBoard[row][col] = step;
        //把这个位置，设置为已经访问
        visited[row * X + col] = true;
        //获取当前位置下一步的可走位置
        ArrayList<Point> ps = next(new Point(col, row));
        sort(ps);
        //遍历
        while (!ps.isEmpty()){
            Point point = ps.remove(0);
            //判断该位置是否走过，如果没有走过，我们就递归遍历
            if(!visited[point.y * X + point.x]){
                traversalChessBoard(chessBoard,point.y,point.x,step+1);
            }
        }

        //当退出while,看看是否遍历成功，如果没有成功，就重置相应的值，然后执行回溯
        if(step < X *Y && !finished){
            //重置
            chessBoard[row][col] = 0;
            visited[row * X +col] = false;
        }else {
            finished = true;
        }
    }



    //编写方法，可以获取当前位置下一步的可走位置（Point表示x,y）
    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<>();

        //创建一个Point对象，准备放入到ps
        Point p1 = new Point();

        //判断在curPoint是否可以走如下位置，如果可以走，就将该店（Point）放入到ps

        //判断是否可以走5位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0){
            ps.add(new Point(p1));
        }
        //判断是否可以走6位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0){
            ps.add(new Point(p1));
        }
        //判断是否可以走7位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0){
            ps.add(new Point(p1));
        }
        //判断是否可以走0位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0){
            ps.add(new Point(p1));
        }
        //判断是否可以走1位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }
        //判断是否可以走2位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }
        //判断是否可以走3位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }
        //判断是否可以走4位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }
        return ps;
    }

    //优化：  写一个方法，对当前位置的可走位置进行排序，依据是可走位置的可走位置的数量，要求从小到大排序
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }
}
