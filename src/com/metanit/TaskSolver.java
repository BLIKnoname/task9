package com.metanit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskSolver {

    public static List<Integer> createNewList(List<Integer> list, int n) {
        Set<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i);
            if (countOfValue(list, x) >= n && !set.contains(x)) {
                set.add(x);
                result.add(x);
            }
        }
        return result;
    }

    private static int countOfValue(List<Integer> list, int x) {
        int cnt = 0;
        for (int element : list) {
            if (element == x) {
                cnt++;
            }
        }
        return cnt;
    }
}
