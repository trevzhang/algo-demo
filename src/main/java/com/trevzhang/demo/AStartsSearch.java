package com.trevzhang.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author trevor
 * @since 2023/11/24 13:10
 **/
public class AStartsSearch {

    private static final int[][] DIRECTIONS = {{0,1},{1,0},{0,-1},{-1,0}};

    static class Node implements Comparable<Node> {
        int x, y, cost, h;
        Node parent;

        public Node(int x, int y, int cost, int h, Node parent) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.h = h;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node o) {
            return (this.cost + this.h) - (o.cost + o.h);
        }
    }

    public static List<int[]> aStartSearch(int[][] grid, int[] start, int[] end) {
        // 优先队列，待探索节点
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        // 初始节点
        openSet.offer(new Node(start[0], start[1], 0, h(start[0],start[1],end),null));
        // 记录已经探索的节点
        boolean[][] closedSet = new boolean[grid.length][grid[0].length];

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            // 到达终点
            if (current.x== end[0] && current.y == end[1]) {
                return buildPath(current);
            }

            closedSet[current.x][current.y] = true;

            for (int[] direction: DIRECTIONS) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];

                if (isValid(grid,newX,newY) && !closedSet[newX][newY]) {
                    int newCost = current.cost + 1;

                    Node adjacent = new Node(newX,newY,newCost,h(newX,newY,end),current);

                    openSet.offer(adjacent);
                }
            }
        }
        return null;
    }

    // 出租车函数，水平和垂直的绝对值距离和
    private static int h(int x, int y, int[] end) {
        return Math.abs(x-end[0]) + Math.abs(y-end[1]);
    }

    // 构建从起点到终点的路径（逆向构建）
    private static List<int[]> buildPath(Node node) {
        LinkedList<int[]> path = new LinkedList<>();

        while (node != null) {
            path.addFirst(new int[]{node.x,node.y});
            node = node.parent;
        }
        return path;
    }

    // 验证新节点是否有效（在网格内 && 为0可通过）
    private static boolean isValid(int[][] grid, int x,int y) {
        return x>=0 && y>=0 && x<grid.length && y<grid[x].length && grid[x][y]==0;
    }


}
