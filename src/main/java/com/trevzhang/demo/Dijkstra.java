package com.trevzhang.demo;

/**
 * Dijkstra算法
 *
 * @author trevor
 * @since 2023/11/22 22:03
 **/
import java.util.*;

public class Dijkstra {

    // 执行Dijkstra算法，返回源点到各个顶点的最短距离
    public static int[] dijkstra(int[][] graph, int startVertex) {
        int nVertices = graph.length;  // 图中顶点数
        int[] shortestDistances = new int[nVertices];  // 存储源点到各个顶点的最短距离
        boolean[] added = new boolean[nVertices];  // 标记顶点是否已被加入已解决问题的集合中

        // 初始化最短距离数组和标记数组
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // 将源点到自身的距离设为0
        shortestDistances[startVertex] = 0;

        // 执行n-1次迭代，每次找到一个顶点加入已解决问题的集合中
        for (int i = 1; i < nVertices; i++) {
            // 从未加入集合中的顶点中找到距离源点最近的顶点
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // 标记该顶点已加入集合中
            added[nearestVertex] = true;

            // 更新其他顶点到源点的距离
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                int edgeDistance = graph[nearestVertex][vertexIndex];
                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }

        return shortestDistances;
    }

    // 测试代码
    public static void main(String[] args) {
        int[][] graph = new int[][]{
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        int[] distances = dijkstra(graph, 0);

        System.out.println("最短距离：");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("从源点到顶点" + i + "的最短距离为：" + distances[i]);
        }
    }
}
