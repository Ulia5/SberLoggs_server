package com.project.sberloggs.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class DisplayLogs {

    // Красивый вывод на экран
    public static void DisplayLogsForPattern(List<List<String>> logs, String inputStringPattern/*, int lengthOnePartLog*/)
    {
        // Список текущего паттерна
        List<OnePattern> listMyPatterns = SplitLogs.RecognizePatternFromInputString(inputStringPattern);

        //int tmp = lengthOnePartLog + 4;
        int addLengthString = 3;

        //String line = "_".repeat(tmp * listMyPatterns.size() + listMyPatterns.size());
        String line = new String();

        //String format = "%" + String.valueOf(tmp) + "." + String.valueOf(lengthOnePartLog) + "s|";

        List<String> formats = new ArrayList<>();

        System.out.println("\n\n\n");
        for (OnePattern onePattern: listMyPatterns)
        {
            String format = "%" + String.valueOf(onePattern.GetLengthDisplayString() + addLengthString) + "."
                    + String.valueOf(onePattern.GetLengthDisplayString()) + "s|";

            formats.add(format);

            System.out.printf(format, onePattern.GetNamePattern());
            line += "_".repeat(onePattern.GetLengthDisplayString() + addLengthString);
        }
        line += "_".repeat(addLengthString);
        System.out.println("");
        System.out.println(line);

        for (List<String> oneRow: logs)
        {
            for (int i = 0; i < oneRow.size(); i++)
            {

                String format = "%" + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString() + addLengthString) + "."
                        + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString()) + "s|";

                System.out.printf(format, oneRow.get(i));
            }
            System.out.println("");
        }
    }


    // Красивый цветной вывод на экран
    public static void DisplayColorLogsForPattern(List<List<String>> logs,
                                                       String inputStringPattern, String sortedPattern)
    {
        // Список различных цветов
        List<String> listColorsStart = new ArrayList<>();
        String ColorsEnd = ((char)27 + "[0m");
        listColorsStart.add((char) 27 + "[31m");
        listColorsStart.add((char) 27 + "[32m");
        listColorsStart.add((char) 27 + "[33m");
        listColorsStart.add((char) 27 + "[34m");
        listColorsStart.add((char) 27 + "[35m");
        listColorsStart.add((char) 27 + "[36m");
        listColorsStart.add((char) 27 + "[37m");

        // Список текущего паттерна
        List<OnePattern> listMyPatterns = SplitLogs.RecognizePatternFromInputString(inputStringPattern);

        // Список сортируемого паттерна
        List<OnePattern> SortedPattern = SplitLogs.RecognizePatternFromInputString(sortedPattern);

        // Один сортируемый паттерн
        OnePattern onePatternSorted = SortedPattern.get(0);

        // Список различных вариаций строк столбца
        List<String> differentRows = new ArrayList<>();

        // Позиция сортируемого паттерна в изначальном паттерне логов
        int position = 0;
        for (position = 0; position < listMyPatterns.size(); position++)
        {
            // Определяем позицию сортируемого паттерна в изначальном паттерне для всего лога
            if (listMyPatterns.get(position).GetNamePattern().equals(onePatternSorted.GetNamePattern()))
                break;
        }

        // Создаем список уникальных записей столбца
        for (List<String> rows: logs)
        {
            //if (!(Arrays.asList(differentRows).contains(rows.get(position))))
            if (!(differentRows.contains(rows.get(position))))
                differentRows.add(rows.get(position));
        }

        System.out.println(differentRows);

        //int tmp = lengthOnePartLog + 4;
        int addLengthString = 3;

        //String line = "_".repeat(tmp * listMyPatterns.size() + listMyPatterns.size());
        String line = new String();

        //String format = "%" + String.valueOf(tmp) + "." + String.valueOf(lengthOnePartLog) + "s|";

        List<String> formats = new ArrayList<>();

        System.out.println("\n\n\n");
        for (OnePattern onePattern : listMyPatterns) {
            String format = "%" + String.valueOf(onePattern.GetLengthDisplayString() + addLengthString) + "."
                    + String.valueOf(onePattern.GetLengthDisplayString()) + "s|";

            formats.add(format);

            System.out.printf(format, onePattern.GetNamePattern());
            line += "_".repeat(onePattern.GetLengthDisplayString() + addLengthString);
        }
        line += "_".repeat(addLengthString);
        System.out.println("");
        System.out.println(line);

        for (List<String> oneRow : logs) {
            String format = new String();
            for (int i = 0; i < oneRow.size(); i++) {

                if (i == position) {
                    int tmpIndex = differentRows.indexOf(oneRow.get(position));
                    int useIndex = 0;

                    if (tmpIndex >= listColorsStart.size())
                    {
                        useIndex = tmpIndex % listColorsStart.size();
                        //System.out.println(useIndex);
                        //useIndex = listColorsStart.size() - 1;
                    }
                    else {
                        useIndex = tmpIndex;
                    }
                    format = listColorsStart.get(useIndex) + "%" + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString() + addLengthString) + "."
                            + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString()) + "s|" + ColorsEnd;
                }
                else
                {
                    format = "%" + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString() + addLengthString) + "."
                            + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString()) + "s|";
                }
                System.out.printf(format, oneRow.get(i));
            }
            System.out.println("");
        }

    }

    /*
    // Сортировка распознаного списка по одному паттерну
    public static List<List<String>> SortRecognLogsForOnePattern(List<List<String>> logs,
                                                                 String inputStringPattern, String sortedPattern)
    {
        List<List<String>> newList = logs;

        // Список текущего паттерна
        List<OnePattern> listMyPatterns = SplitLogs.RecognizePatternFromInputString(inputStringPattern);

        // Список сортируемого паттерна
        List<OnePattern> SortedPattern = SplitLogs.RecognizePatternFromInputString(sortedPattern);

        // Один сортируемый паттерн
        OnePattern onePatternSorted = SortedPattern.get(0);

        // Позиция сортируемого паттерна в изначальном паттерне логов
        int position = 0;
        for (position = 0; position < listMyPatterns.size(); position++)
        {
            // Определяем позицию сортируемого паттерна в изначальном паттерне для всего лога
            if (listMyPatterns.get(position).GetNamePattern().equals(onePatternSorted.GetNamePattern()))
                break;
        }

        // Функция СОРТИРОВКИ ВСТАВКАМИ массива
        for (int i = 1; i < newList.size(); i++)
        {
            //str1.compareTo(str2)
            for (int j = i; (j > 0) && (newList.get(j-1).get(position).compareTo(newList.get(j).get(position)) > 0); j--)
            {
                Collections.swap(newList, j-1, j);
            }
        }

        return newList;
    }
     */

    // Красивый вывод на экран
    public static void DisplayListLogs(List<Log> logs)
    {
        // Список текущего паттерна
        List<OnePattern> listMyPatterns = logs.get(0).getMyPatterns();

        //int tmp = lengthOnePartLog + 4;
        int addLengthString = 3;

        //String line = "_".repeat(tmp * listMyPatterns.size() + listMyPatterns.size());
        String line = new String();

        //String format = "%" + String.valueOf(tmp) + "." + String.valueOf(lengthOnePartLog) + "s|";

        List<String> formats = new ArrayList<>();

        System.out.println("\n\n\n");
        for (OnePattern onePattern: listMyPatterns)
        {
            String format = "%" + String.valueOf(onePattern.GetLengthDisplayString() + addLengthString) + "."
                    + String.valueOf(onePattern.GetLengthDisplayString()) + "s|";

            formats.add(format);

            System.out.printf(format, onePattern.GetNamePattern());
            line += "_".repeat(onePattern.GetLengthDisplayString() + addLengthString);
        }
        line += "_".repeat(addLengthString);
        System.out.println("");
        System.out.println(line);


        for (Log oneRow: logs)
        {
            for (int i = 0; i < oneRow.getOneLogRow().size(); i++)
            {

                String format = "%" + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString() + addLengthString) + "."
                        + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString()) + "s|";

                System.out.printf(format, oneRow.GetIndex(i));
            }
            System.out.println("");
        }
    }


    // Красивый цветной вывод на экран
    public static void DisplayColorListLogs(List<Log> logs)
    {
        // Список различных цветов
        List<String> listColorsStart = new ArrayList<>();
        String ColorsEnd = ((char)27 + "[0m");
        listColorsStart.add((char) 27 + "[31m");
        listColorsStart.add((char) 27 + "[32m");
        listColorsStart.add((char) 27 + "[33m");
        listColorsStart.add((char) 27 + "[34m");
        listColorsStart.add((char) 27 + "[35m");
        listColorsStart.add((char) 27 + "[36m");
        listColorsStart.add((char) 27 + "[37m");

        // Список текущего паттерна
        List<OnePattern> listMyPatterns = logs.get(0).getMyPatterns();

        // Один сортируемый паттерн
        OnePattern onePatternSorted = logs.get(0).getSortPattern();

        // Список различных вариаций строк столбца
        List<String> differentRows = new ArrayList<>();

        // Позиция сортируемого паттерна в изначальном паттерне логов
        int position = 0;
        for (position = 0; position < listMyPatterns.size(); position++)
        {
            // Определяем позицию сортируемого паттерна в изначальном паттерне для всего лога
            if (listMyPatterns.get(position).GetNamePattern().equals(onePatternSorted.GetNamePattern()))
                break;
        }

        // Создаем список уникальных записей столбца
        for (Log rows: logs)
        {
            //if (!(Arrays.asList(differentRows).contains(rows.get(position))))
            if (!(differentRows.contains(rows.GetIndex(position))))
                differentRows.add(rows.GetIndex(position));
        }

        // Вывод разных сортируемх строк
        //System.out.println(differentRows);

        //int tmp = lengthOnePartLog + 4;
        int addLengthString = 3;

        //String line = "_".repeat(tmp * listMyPatterns.size() + listMyPatterns.size());
        String line = new String();

        //String format = "%" + String.valueOf(tmp) + "." + String.valueOf(lengthOnePartLog) + "s|";

        List<String> formats = new ArrayList<>();

        System.out.println("\n\n\n");
        for (OnePattern onePattern : listMyPatterns) {
            String format = "%" + String.valueOf(onePattern.GetLengthDisplayString() + addLengthString) + "."
                    + String.valueOf(onePattern.GetLengthDisplayString()) + "s|";

            formats.add(format);

            System.out.printf(format, onePattern.GetNamePattern());
            line += "_".repeat(onePattern.GetLengthDisplayString() + addLengthString);
        }
        line += "_".repeat(addLengthString);
        System.out.println("");
        System.out.println(line);

        for (Log oneRow : logs) {
            String format = new String();
            for (int i = 0; i < oneRow.getOneLogRow().size(); i++) {

                if (i == position) {
                    int tmpIndex = differentRows.indexOf(oneRow.GetIndex(position));
                    int useIndex = 0;

                    if (tmpIndex >= listColorsStart.size())
                    {
                        useIndex = tmpIndex % listColorsStart.size();
                        //System.out.println(useIndex);
                        //useIndex = listColorsStart.size() - 1;
                    }
                    else {
                        useIndex = tmpIndex;
                    }
                    format = listColorsStart.get(useIndex) + "%" + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString() + addLengthString) + "."
                            + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString()) + "s|" + ColorsEnd;
                }
                else
                {
                    format = "%" + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString() + addLengthString) + "."
                            + String.valueOf(listMyPatterns.get(i).GetLengthDisplayString()) + "s|";
                }
                System.out.printf(format, oneRow.GetIndex(i));
            }
            System.out.println("");
        }

    }
}
