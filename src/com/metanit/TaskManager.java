package com.metanit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    public static ParsedValuePair parseInputFile(String inputFilePath) {
        try (Scanner scanner = new Scanner(new File(inputFilePath))) {
            String str;
            int countOfNumbers = -1;

            if (scanner.hasNext()) {
                str = scanner.nextLine();
                if (scanner.hasNext()) {
                    countOfNumbers = scanner.nextInt();
                } else {
                    throw new RuntimeException("you must enter N");
                }
            } else {
                throw new RuntimeException("empty file");
            }

            String[] line = str.split("\\s+");
            int m = line.length;

            List<Integer> result = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                int number = Integer.parseInt(line[i]);
                result.add(number);
            }
            if (countOfNumbers < -1) {
                throw new IllegalArgumentException("n must be > 0");
            } else {
                return new ParsedValuePair(result, countOfNumbers);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void writeToOutputFile(String outputFilePath, List<Integer> result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            System.out.print("ответ: ");
            for (int x : result) {
                writer.write(x + " ");
                System.out.print(x + " ");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}