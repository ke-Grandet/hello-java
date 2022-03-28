package org.example.main.other;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 排序算法
 */
public class Sorting {

    /**
     * 洗牌：打乱数组顺序
     *
     * @param arr 目标数组
     */
    public static void randomSort(int[] arr) {
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            if (i != randomIndex) {
                int temp = arr[i];
                arr[i] = arr[randomIndex];
                arr[randomIndex] = temp;
            }
        }
    }

    /**
     * 获取指定长度的数组
     *
     * @param length         数组长度
     * @param allowDuplicate 允许重复元素
     * @return 顺序数组
     */
    public static int[] normalArr(int length, boolean allowDuplicate) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            if (allowDuplicate && Math.random() < 0.5) {
                arr[i] = (int) (Math.random() * length);
            } else {
                arr[i] = i;
            }
        }
        return arr;
    }

    /**
     * 获取指定长度的乱序数组
     *
     * @param length 数组长度
     * @return 乱序数组
     */
    public static int[] randomArr(int length) {
        int[] arr = normalArr(length, false);
        randomSort(arr);
        return arr;
    }

    /**
     * 快速排序：取一个标准值，将小于标准值和大于标准值的数归至数组两侧，再对两边递归
     *
     * @param arr   目标数组
     * @param start 开始位置的索引
     * @param end   结束位置的索引
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end)  // 首先判断开始位置小于结束位置
            return;
        int value = arr[start];  // 取数组第一个数作为基准值
        int left = start;  // 左索引初始值
        int right = end;  // 右索引初始值
        while (left < right) {  // 在索引相撞前保持循环
            while (left < right && arr[right] > value) {  // 索引发生过变化，因此每次都要判断 left < right
                right--;  // 每当大于等于基准值时索引左移一位。因为取左起第一个数作为基准值，所以从右边开始
            }
            arr[left] = arr[right];
            left++;
            while (left < right && arr[left] < value) {  // 索引发生过变化，因此每次都要判断 left < right
                left++;  // 每当小于标准值时索引右移一位
            }
            arr[right] = arr[left];
            right--;
        }
        arr[left] = value;
        // 结束循环后索引相撞，left = right，数组分为小于标准值的[start, left - 1]和大于标准值的[right + 1, end]两个半区
        if (left - 1 > start)
            quickSort(arr, start, left - 1);  // 对左半区递归
        if (right + 1 < end)
            quickSort(arr, right + 1, end);  // 对右半区递归
    }

    /**
     * 冒泡排序
     *
     * @param arr 目标数组
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr 目标数组
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minI = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minI] > arr[j]) {
                    minI = j;
                }
            }
            if (minI != i) {
                int temp = arr[i];
                arr[i] = arr[minI];
                arr[minI] = temp;
            }
        }
    }


    /**
     * 大整数算式：
     * a / (b + c) + b / (a + c) + c / (a + b) = 4 + 0 + 0 = 4
     */
    public static void bigInt() {
        BigDecimal a = new BigDecimal("154476802108746166441951315019919837485664325669565431700026634898253202035277999");
        BigDecimal b = new BigDecimal("36875131794129999827197811565225474825492979968971970996283137471637224634055579");
        BigDecimal c = new BigDecimal("4373612677928697257861252602371390152816537558161613618621437993378423467772036");
        BigDecimal x = a.divide(b.add(c), 6);
        BigDecimal y = b.divide(c.add(a), 6);
        BigDecimal z = c.divide(a.add(b), 6);

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println();
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("z: " + z);
        System.out.println("add: " + x.add(y).add(z));
    }

}
