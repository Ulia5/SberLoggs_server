package com.project.sberloggs.parser;

import java.util.ArrayList;
import java.util.List;

public class Log {

    // Список частей строк разбитых по паттерну
    private List<String> oneLogRow;

    // Список текущего паттерна
    private List<OnePattern> myPatterns;

    // Сортируемый паттерн
    private OnePattern sortPattern;


    public Log()
    {
        oneLogRow = new ArrayList<>();
        myPatterns = new ArrayList<>();
    }

    // Кончтруктор

    /**
     * Конструктор с параметрами
     * @param oneRow                    - Строка лога
     * @param inputStringWithPattern    - Строка паттерна
     */
    public Log(String oneRow, String inputStringWithPattern)
    {
        oneLogRow = new ArrayList<>();
        myPatterns = new ArrayList<>();

        RecognOneRow(oneRow, inputStringWithPattern);

        myPatterns = SplitLogs.RecognizePatternFromInputString(inputStringWithPattern);

        if(myPatterns != null)
            if(myPatterns.size() > 0)
                sortPattern = myPatterns.get(0);
    }


    /**
     * Конструктор с параметрами
     * @param oneRow                    - Строка лога
     * @param inputStringWithPattern    - Строка паттерна
     * @param sortedPattern             - Паттерн для сортировки
     */
    public Log(String oneRow, String inputStringWithPattern, String sortedPattern)
    {
        oneLogRow = new ArrayList<>();
        myPatterns = new ArrayList<>();

        myPatterns = SplitLogs.RecognizePatternFromInputString(inputStringWithPattern);

        // Список сортируемого паттерна
        List<OnePattern> SortedPattern = SplitLogs.RecognizePatternFromInputString(sortedPattern);

        // Один сортируемый паттерн
        sortPattern = SortedPattern.get(0);

        // Распознать строку по паттерну
        RecognOneRow(oneRow, inputStringWithPattern);
    }



    /**
     * Распознать одну строку
     * @param oneRow                    - Строка лога
     * @param inputStringWithPattern    - Строка паттерна
     */
    public List<String> RecognOneRow(String oneRow, String inputStringWithPattern)
    {
        List<String> Row = new ArrayList<>();
        Row.add(oneRow);

        List<List<String>> temp = SplitLogs.SplitLogsForPattern(Row, inputStringWithPattern);
        oneLogRow = temp.get(0);

        return oneLogRow;
    }

    // Получить часть строки по индексу
    public String GetIndex(int index)
    {
        String res = "";

        if (index < oneLogRow.size() && index > -1)
            res = oneLogRow.get(index);
        return res;
    }


    public List<String> getOneLogRow() {
        return oneLogRow;
    }

    public void setOneLogRow(List<String> oneLogRow) {
        this.oneLogRow = oneLogRow;
    }

    public List<OnePattern> getMyPatterns() {
        return myPatterns;
    }

    public void setMyPatterns(List<OnePattern> myPatterns) {
        this.myPatterns = myPatterns;
    }

    public void setMyPatterns(String inputPatternString) {
        this.myPatterns = SplitLogs.RecognizePatternFromInputString(inputPatternString);
    }

    public OnePattern getSortPattern() {
        return sortPattern;
    }

    public void setSortPattern(OnePattern sortPattern) {
        this.sortPattern = sortPattern;
    }

    public void setSortPattern(String sortPattern) {
        this.sortPattern = SplitLogs.RecognizePatternFromInputString(sortPattern).get(0);
    }


}
