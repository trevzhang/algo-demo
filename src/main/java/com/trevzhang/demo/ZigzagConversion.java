package com.trevzhang.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Z字形变换
 */
public class ZigzagConversion {

    public static void main(String[] args) {
        // System.out.println("Hello LeetCoder");
        String input = "PAYPALISHIRING";

        String result = convert(input, 4);

        System.out.println(result);
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

}