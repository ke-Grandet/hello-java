package org.example.main.other;

import java.math.BigDecimal;

// 快速排序示例类
public class FastSorting {

    // 执行快速排序
    public static void startSort(int[] arr) {
        if (arr.length == 0)
            arr = new int[]{72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        int right = arr.length - 1;
        // 调用排序方法
        mySort(0, right, arr);
        // 输出排序后数组
        for (int i = 0; i < right; i++) {
            System.out.print((i + 1) + "\t");
        }
        System.out.println();
        for (int value : arr) {
            System.out.print(value + "\t");
        }
        System.out.println();
    }

    // 核心排序方法，参数为左边界（数组起始索引），右边界（数组末尾索引），数组
    private static void mySort(int left, int right, int[] arr) {
        if (left >= right) return;  // 首先判断左边界不可大于右边界
        int i = left;  // 初始化左索引
        int j = right;  // 初始化右索引
        int x = arr[left];  // 初始化标准值（默认取数组第一个数）
        while (i < j) {  // 在索引相撞前保持循环
            // 因为取数组第一个数作为标准值，从数组右侧启动排序
            while (i < j && arr[j] >= x) {  // 循环内索引变化，因此每次都要判断i < j
                j--;  // 若右索引上的值大于标准值则不断向内移动右索引
            }
            if (i < j) {
                arr[i] = arr[j];  // 将发现的小于标准值大小的值移动到左索引处
                i++;  // 因为获得一个数字，左索引移动一格
            }
            while (i < j && arr[i] <= x) {  // 循环内索引变化，因此每次都要判断i < j
                i++;  // 若右索引上的值大于标准值则不断向内移动右索引
            }
            if (i < j) {
                arr[j] = arr[i];  // 将发现的大于标准值大小的值移动到右索引处
                j--;  // 因为获得一个数字，左索引移动一格
            }
        }
        arr[i] = x;  // 左右半区归分完成后，在最后的空位置插入标准值
        mySort(left, i, arr);  // 对左半区递归
        mySort(i + 1, right, arr);  // 对右半区递归
    }


    // 执行大整数
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
