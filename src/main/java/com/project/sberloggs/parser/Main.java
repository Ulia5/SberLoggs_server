package com.project.sberloggs.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Пример ввода данных:
        // D:\altstu\3kurs\LetPract\test1.txt   или   D:\altstu\3kurs\LetPract\log-example.txt
        // %data%level%logger%thread%message
        // Enter или %level

        // ПРОГРАММА
        try
        {
            // Вводим путь к файлу (если его не передают напрямую)
            System.out.print("Input full file path: ");

            // Создаем буффер для ввода строк (ДЛЯ ВВОДА ТЕКСТА В КОНСОЛИ: пути, паттернов)
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            // Читаем путь файла из буффера (С КОНСОЛИ)
            String inputPath = reader.readLine();

            // Открываем файл и читаем из него все строки (ПЕРЕДАВАЕМЫЙ ФАЙЛ ЧИТАЕМ КАК-ТО ПО ДРУГОМУ)
            List<String> listRows = ReadRowsFromTXT.GetRowsFromFile(inputPath);

            // Вводим паттерн (С КЛАВИАТУРЫ)
            System.out.print("Input Patterns: ");
            String inputPattern = reader.readLine();

            System.out.print("Input ONE SORTED Pattern or Enter: ");
            // Для ввода паттерна сортировки
            String sortedPattern = reader.readLine();

            // СОЗДАЕМ СПИСОК КЛАССОВ, В КОТОРЫХ ХРАНЯТСЯ КОНКРЕТНЫЕ АТРИБУТЫ
            List<NewLog> test;

            // Если ввели сортируемый паттерн
            if (sortedPattern.length() > 0)
            {
                // ОДИН СТАТИЧЕСКИЙ МЕТОТ - СОЗДАТЬ СПИСОК КЛАССОВ С ПОЛЯМИ АТРИБУТМИ
                test = NewLog.create(listRows, inputPattern, sortedPattern);
            }
            else
            {
                // ДРУГАЯ ВАРИАЦИЯ ТОГО ЖЕ СТАТИЧЕСКОГО МЕТОДА - СОЗДАТЬ СПИСОК КЛАССОВ С ПОЛЯМИ АТРИБУТМИ
                test = NewLog.create(listRows, inputPattern);
            }

            // ВЫВОД СПИСКА ВРУЧНУЮ, Т.К. У МЕНЯ НЕТ ОТДЕЛЬНОГ ВЫВОДА ДЛЯ ЭТОГО КЛАССА В КОНСОЛЬ,
            // ПОТОМУ ЧТО ОН ПРЕДУСМОТРЕН ДЛЯ БРАУЗЕРА, В КОТОРОМ БУДЕТ КАКОЙ-ТО СВОЙ ВЫВОД
            for(NewLog oneRow: test)
            {
                System.out.println(oneRow.getData() + "|" + oneRow.getLevel() + "|" + oneRow.getLogger() + "|" +
                                   oneRow.getThread() + "|" + oneRow.getMessage());
            }

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }
}