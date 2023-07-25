package com.project.sberloggs.parser;

import java.util.ArrayList;
import java.util.List;

public class NewLog {

    //****************************************************************
    // Поле для хранения даты
    private String data;

    // Поле для хранения потока
    private String thread;

    // Поле для хранения уровня
    private String level;

    // Поле для хранения логгера - тот, кто отправляет логи
    private String logger;

    // Поле для хранения сообщения - весь текст до конца строки
    private String message;
    //****************************************************************


    public NewLog() {
        this.data = "";
        this.thread = "";
        this.level = "";
        this.logger = "";
        this.message = "";
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if(message.length() > 2){
            this.message = message.substring(2, message.length());
        } else {
            this.message = "";
        }
    }

    /**
     *
     * @param rowsFromInputFile     - Список строк из файла
     * @param inputPattern          - Введенная строка с паттернами
     * @return                      - Список классов LogForYoula
     */
    static public List<NewLog> create(List<String> rowsFromInputFile, String inputPattern)
    {
        // Возвращаемое из функции значение
        List<NewLog> result = new ArrayList<>();

        // Промежуточный список
        List<Log> temp = SplitLogs.CreateAllLogs(rowsFromInputFile, inputPattern);

        // Перебор промежуточных значений для создания результирующего
        for (int i = 0; i < temp.size(); i++)
        {
            NewLog oneLog = new NewLog();

            for(int j = 0; j < temp.get(i).getOneLogRow().size(); j++)
            {
                if(temp.get(i).getMyPatterns().get(j).GetNamePattern().equals("data"))
                    oneLog.setData(temp.get(i).GetIndex(j));
                if(temp.get(i).getMyPatterns().get(j).GetNamePattern().equals("thread"))
                    oneLog.setThread(temp.get(i).GetIndex(j));
                if(temp.get(i).getMyPatterns().get(j).GetNamePattern().equals("level"))
                    oneLog.setLevel(temp.get(i).GetIndex(j));
                if(temp.get(i).getMyPatterns().get(j).GetNamePattern().equals("logger"))
                    oneLog.setLogger(temp.get(i).GetIndex(j));
                if(temp.get(i).getMyPatterns().get(j).GetNamePattern().equals("message"))
                    oneLog.setMessage(temp.get(i).GetIndex(j));
            }
            result.add(oneLog);
        }

        return result;
    }

    /**
     *
     * @param rowsFromInputFile     - Список строк из файла
     * @param inputPattern          - Введенная строка с паттернами
     * @param patternForSort        - Введенная строка с одним паттерном для сортировки
     * @return                      - Список классов LogForYoula
     */
    static public List<NewLog> create(List<String> rowsFromInputFile, String inputPattern, String patternForSort)
    {
        List<NewLog> result = new ArrayList<>();

        // Промежуточный список
        List<Log> temp = SplitLogs.CreateAllLogs(rowsFromInputFile, inputPattern);

        temp = SplitLogs.SortRecognLogs(temp, patternForSort);

        // Перебор промежуточных значений для создания результирующего
        for (int i = 0; i < temp.size(); i++)
        {
            NewLog oneLog = new NewLog();

            for(int j = 0; j < temp.get(i).getOneLogRow().size(); j++)
            {
                if(temp.get(i).getMyPatterns().get(j).GetNamePattern().equals("data"))
                    oneLog.setData(temp.get(i).GetIndex(j));
                if(temp.get(i).getMyPatterns().get(j).GetNamePattern().equals("thread"))
                    oneLog.setThread(temp.get(i).GetIndex(j));
                if(temp.get(i).getMyPatterns().get(j).GetNamePattern().equals("level"))
                    oneLog.setLevel(temp.get(i).GetIndex(j));
                if(temp.get(i).getMyPatterns().get(j).GetNamePattern().equals("logger"))
                    oneLog.setLogger(temp.get(i).GetIndex(j));
                if(temp.get(i).getMyPatterns().get(j).GetNamePattern().equals("message"))
                    oneLog.setMessage(temp.get(i).GetIndex(j));
            }
            result.add(oneLog);
        }

        return result;
    }
}
