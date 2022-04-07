package com.metanit;

import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        if (args.length == 0) {
            new TableForm();
        } else {
            runInCommandLineMode(args);
        }

        Tests.testAll();
    }

    private static void runInCommandLineMode(String[] args) {
        InputArgs inputArgs = ArgsParser.parseCmdArgs(args);
        ParsedValuePair value = TaskManager.parseInputFile(inputArgs.getInput());
        List<Integer> resultList = TaskSolver.createNewList(value.getList(), value.getN());
        TaskManager.writeToOutputFile(inputArgs.getOutput(), resultList);
    }
}