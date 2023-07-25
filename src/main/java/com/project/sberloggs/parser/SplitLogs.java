package com.project.sberloggs.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitLogs
{

    // Разбить логи по паттерну (Основной метод)
    static public List<List<String>> SplitLogsForPattern(List<String> logs, String inputPattern)
    {
        //Выходной список строк, состояхих из кусков(списка) строк
        List<List<String>> result = new ArrayList<>();

        // Список текущего паттерна
        List<OnePattern> listMyPatterns;

        // Распознаем входной паттерн
        listMyPatterns = RecognizePatternFromInputString(inputPattern);

        // Основной перебор всех строк
        MainEnumerationOfLogs(logs, result, listMyPatterns);

        return result;
    }

    // Вынесенный основной перебор строк и поиск частей логов
    private static void MainEnumerationOfLogs(List<String> logs, List<List<String>> result, List<OnePattern> listMyPatterns) {
        // Цикл по строкам логов
        for (String currentLog: logs)
        {
            // Текущая строка, разбитая на части, которая будет добавляться в выходной список
            List<String> currentListRow = new ArrayList<String>();

            // Копия строки лога
            String copyCurRow = currentLog;

            //
            int currentStart = 0;

            //-----------------------------
            //System.out.println("\n\n\n");
            //System.out.println(currentLog);
            //-----------------------------

            // Цикл по введенным паттернам
            for(OnePattern currentPattern: listMyPatterns)
            {
                // Получаем список всех регулярных выражений для текущего паттерна
                List<String> currentRegulars = currentPattern.GetListRegular();


                //-----------------------------
                //System.out.println(currentPattern.GetNamePattern());
                //-----------------------------
                // Цикл по всем регулярным выражения текущего паттерна
                for (String currentRegular: currentRegulars)
                {

                    Pattern pattern = Pattern.compile(currentRegular);
                    Matcher matcher = pattern.matcher(copyCurRow);


                    //-----------------------------
                    //System.out.println(currentRegular);
                    //-----------------------------

                    // Если нашли совпадение с шаблоном
                    if(matcher.find()) {
                        // Добавляем результат поиска в выходной список
                        currentListRow.add(matcher.group());

                        // Присваиваем конец найденной подстроки переменной
                        currentStart = matcher.end();

                        //-----------------------------
                        //System.out.println("V");
                        //-----------------------------

                        // Обрезаем строку, до последнего найденного символа
                        copyCurRow = copyCurRow.substring(currentStart);
                        break;
                    }
                }
            }
            // Добавляем разбитую строку лога в выходной список
            result.add(currentListRow);
        }
    }

    // Распознать список текущего паттерна из входной строки
    static public List<OnePattern> RecognizePatternFromInputString(String inputPattern)
    {
        // Создаем объект заранее заготовленных шаблонов паттерна
        LogsPattern logsPattern = new LogsPattern();

        // Список текущего паттерна
        List<OnePattern> listMyPatterns = new ArrayList<OnePattern>();

        // Определяем введенный паттерн
        listMyPatterns = logsPattern.SplitStringForPatterns(inputPattern);

        return listMyPatterns;
    }

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

    /** Сортировка распознаного списка по одному паттерну
     *
     * @param logs - Список логов
     * @return     - Отсортированный список логов
     */
    public static List<Log> SortRecognLogs(List<Log> logs, String sortedPattern)
    {
        List<Log> newList = logs;

        if(logs != null) {

            if (logs.get(0).getMyPatterns() != null && logs.get(0).getSortPattern()!= null) {

                // Список текущего паттерна
                List<OnePattern> listMyPatterns = logs.get(0).getMyPatterns();

                // Список сортируемого паттерна
                List<OnePattern> SortedPattern = SplitLogs.RecognizePatternFromInputString(sortedPattern);

                // Один сортируемый паттерн
                OnePattern onePatternSorted = SortedPattern.get(0);

                // Позиция сортируемого паттерна в изначальном паттерне логов
                int position = 0;
                for (position = 0; position < listMyPatterns.size(); position++) {
                    // Определяем позицию сортируемого паттерна в изначальном паттерне для всего лога
                    if (listMyPatterns.get(position).GetNamePattern().equals(onePatternSorted.GetNamePattern()))
                        break;
                }

                // Функция СОРТИРОВКИ ВСТАВКАМИ массива
                for (int i = 1; i < newList.size(); i++) {
                    newList.get(i).setSortPattern(onePatternSorted);
                    //str1.compareTo(str2)
                    for (int j = i; (j > 0) && (newList.get(j - 1).GetIndex(position).compareTo(newList.get(j).GetIndex(position)) > 0); j--) {
                        Collections.swap(newList, j - 1, j);
                    }
                }

                newList.get(0).setSortPattern(sortedPattern);
            }
            else
                System.out.println("Sort: Parameters of logs is empty");
        }
        else
            System.out.println("Sort: Logs is empty");

        return newList;
    }


    /**
     * Обработка всех строк из файла в список логов
     * @param logsInput             - Список строк из файла
     * @param inputStringPattern    - Введенный паттерн
     * @return                      - Новый список логов
     */
    static public List<Log> CreateAllLogs(List<String> logsInput,
                               String inputStringPattern)
    {
        List<Log> newList = new ArrayList<>();

        if (logsInput != null && inputStringPattern != null)
        {
            for(int i = 0; i < logsInput.size(); i++)
            {
                newList.add(new Log(logsInput.get(i), inputStringPattern));
            }
        }

        return newList;
    }
}
