package org.example.main;


import org.example.main.other.ImmpSqlScript;
import org.example.main.other.Sorting;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        int[] arr = Sorting.randomArr(32);
        Sorting.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}
