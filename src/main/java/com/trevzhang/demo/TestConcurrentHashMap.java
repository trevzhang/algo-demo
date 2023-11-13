package com.trevzhang.demo;

/**
 * @author trevor
 * @since 2023/11/13 0:20
 **/
public class TestConcurrentHashMap {

    public static void main(String[] args) {
        int n = 128;
        // sizeCtl下一次扩容阈值（即扩容因子0.75）
        //  192     =  256     -  64      = 0.75 * 256
        int sizeCtl = (n << 1) - (n >>> 1);
        System.out.println(sizeCtl);
    }
}
