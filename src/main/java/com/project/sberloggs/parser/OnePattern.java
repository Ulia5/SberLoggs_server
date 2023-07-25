package com.project.sberloggs.parser;

import java.util.ArrayList;
import java.util.List;

public class OnePattern {

    // Паттерн
    private String pattern;

    // Список регулярных выражений
    private List<String> regular;

    // Отображаемая длина паттерна
    private Integer lengthDisplayString;

    // Конструктор с параметром строки паттерна
    public OnePattern(String namePattern, Integer lengthDisplayString1)
    {
        pattern = namePattern;
        regular = new ArrayList<String>();
        lengthDisplayString = lengthDisplayString1;
    }

    // Вернуть длину выводимой строки
    public Integer GetLengthDisplayString()
    {
        return lengthDisplayString;
    }

    // Вернуть имя паттрена
    public String GetNamePattern()
    {
        if (pattern != null)
            return pattern;
        return null;
    }

    // Сравнить передаваемую строку с паттерном
    public boolean EqualsStringWithPattern(String youString)
    {
        if (pattern.equalsIgnoreCase(youString))
            return true;
        return false;
    }

    // Добавить строку с регулярным выражением
    public void AddRegular(String regular)
    {
        this.regular.add(regular);
    }

    // Вернуть список регулярных выражений
    public ArrayList<String> GetListRegular()
    {
        return new ArrayList<String>(regular);
    }
}
