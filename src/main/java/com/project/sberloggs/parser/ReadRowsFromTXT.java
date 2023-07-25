package com.project.sberloggs.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class ReadRowsFromTXT {

    public static List<String> GetRowsFromFile(String filePath)
    {

        // Создаем выходной списаок строк
        List<String> resultRows = new ArrayList<>();


        File file = new File(filePath);

        if (isFileExists(file)) {
            System.out.println("File exists!!");
        }
        else {
            System.out.println("File doesn't exist or program doesn't have access " +
                    "to the file");
        }

        // Используем перехват исключений
        try {

            // Создадим буффер для чтения из входного потока
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Для чтения текущей строки
            String line;

            // Пока текущая строка не пустая, читаем
            while ((line = reader.readLine()) != null) {
                // Жобавляем строку в список
                resultRows.add(line);
            }
            // Закрываем буффер
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();


        }finally {
        }

        return resultRows;
    }

    // Метод проверки, существует ли файл и не является ли он каталогом
    public static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }


}
