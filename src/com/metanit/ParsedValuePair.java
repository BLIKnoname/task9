package com.metanit;

import java.util.List;

public class ParsedValuePair {
    private final List<Integer> list;
    private final int n;

    public ParsedValuePair( List<Integer> list,int n){
        this.list = list;
        this.n = n;
    }

    public List<Integer> getList() {
        return list;
    }

    public int getN() {
        return n;
    }
}

