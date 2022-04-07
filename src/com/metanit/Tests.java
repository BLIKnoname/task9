package com.metanit;

import java.util.Arrays;
import java.util.List;

public class Tests {
    public static void testAll() {
        testCase1();
        testCase2();
        testCase3();
    }

    public static void testCase1() {
        int n = 2;
        List<Integer> inputList = Arrays.asList(2, 2, 2, 1, 1, 4, 3, 6, 5, 3);
        List<Integer> expected = Arrays.asList(2, 1, 3);

        List<Integer> outputList = TaskSolver.createNewList(inputList, n);

        assert expected.equals(outputList);
    }

    public static void testCase2() {
        int n = 3;
        List<Integer> inputList = Arrays.asList(1, 2, 3, 3, 3, 3, 4, 5, 6, 7, 7, 7);
        List<Integer> expected = Arrays.asList(3, 7);

        List<Integer> outputList = TaskSolver.createNewList(inputList, n);

        assert expected.equals(outputList);
    }

    public static void testCase3() {
        int n = 1;
        List<Integer> inputList = Arrays.asList(1, 10, 1223, 212);
        List<Integer> expected = Arrays.asList(1, 23);

        List<Integer> outputList = TaskSolver.createNewList(inputList, n);

        assert !expected.equals(outputList);
    }
}
