package com.trevzhang.demo;

import java.util.*;

public class HashMapSorting {
    public static void main(String[] args) {
        HashMap<String, Object> input = new HashMap<>();
        //
        input.put("3", new HashMap<String,Object>() {{
            put("3","aaa");
            put("1","xxx");
            put("2","xxx");
        }});

        input.put("1", new HashMap<String,Object>() {{
            put("3","bbb");
            put("1","xxx");
            put("2","xxx");
        }});

        input.put("2", new HashMap<String,Object>() {{
            put("3","xxx");
            put("1","xxx");
            put("2","xxx");
        }});
        LinkedHashMap<String, Object> output = sortHashMap(input);
        System.out.println(output);
    }
    public static LinkedHashMap<String, Object> sortHashMap(HashMap<String, Object> input) {
        // 将输入的HashMap的entrySet转换为List，并按照键排序
        List<Map.Entry<String, Object>> entries = new ArrayList<>(input.entrySet());
        entries.sort(Map.Entry.comparingByKey());

        // 创建一个LinkedHashMap，用于存放排序后的结果
        LinkedHashMap<String, Object> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : entries) {
            Object value = entry.getValue();
            // 判断value是否为Map类型
            if (value instanceof Map) {
                // 如果是Map类型，递归调用sortHashMap进行排序
                sortedMap.put(entry.getKey(), sortHashMap((HashMap<String, Object>) value));
            } else {
                // 如果不是Map类型，则直接放入结果中
                sortedMap.put(entry.getKey(), value);
            }
        }
        return sortedMap;
    }
}

