package com.trevzhang.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * 多多订阅了很多渠道的消息,每一条消息有到 达时间、类别和是否重要等属性。为了避免消 息打扰,多多创建了一个消息拦截器。拦截器 规则如下:
 *
 * 1. 对于重要消息不拦截
 * 2. 对于非重要消息,如果10个单位时间内 (开区间)多多接收过其他消息,则拦 截
 * 3. 对于非重要消息,如果30个单位时间内 (开区间)多多接收过其他同类别的消 息,则拦截。
 *    提示:3条规则同时生效;如果一条消息被拦 截了,则不算收到该条消息;重要消息也是消 息,收到重要消息也会对后面的消息产生影 响。
 *    输入描述:
 *    第一行1个数字N,表示一共有N条消息, 1 <= N <= 100000 接下来N行,每行3个数字分 别表示:
 * 4. 消息到达的时间t(1 <= t <= 100000,消 息枝先后顺序到达,后一条消息到达时间 >=前一条消息) 2. 消息类别 m (1 <= m <= 10000), 3. 是否重要i(0: 不重要,1:重要)
 *    输出描述:
 *    一个整数,表示多多一共收到了几条消息
 *    要求:时间复杂度O(n)
 *
 * 示例:
 * 输入
 * 3 110 121 0 33 10
 * 输出
 * 2
 *
 * 输入
 * 3
 * 110
 * 220 12 2 0
 * 输出
 * 2
 *
 * 输入
 * 2 110
 * 111
 * 输出
 * 2
 *
 * @author kanda
 */
public class MessageInterceptor {

    public static void main(String[] args) {
        int n = 3;
        int[][] msgs = {{1, 1, 0}, {12, 1, 0}, {33, 1, 0}};

        System.out.println(count(n, msgs));
    }

    private static int count(int n, int[][] msgs) {
        // 收到的消息数量
        int received = 0;
        // 任意消息的最后接收时间，初始化为-11确保首条消息不被拦截
        int lastTimeAny = -11;
        // 每个类别消息的最后接收时间
        Map<Integer, Integer> lastTimeCategory = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int t = msgs[i][0]; // 消息到达时间
            int m = msgs[i][1]; // 消息类别
            int important = msgs[i][2]; // 是否重要

            if (important == 1) { // 重要消息不拦截
                received++;
                lastTimeAny = t;
                lastTimeCategory.put(m, t);
            } else {
                Integer lastTimeCat = lastTimeCategory.getOrDefault(m, -31);
                if (t - lastTimeAny > 10 && t - lastTimeCat > 30) { // 不在拦截条件内
                    received++;
                    lastTimeAny = t;
                    lastTimeCategory.put(m, t);
                }
            }
        }

        return received; // 返回收到的消息数量
    }
}

