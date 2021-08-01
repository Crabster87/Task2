import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task2 {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("src/task_2_addresses.txt");                                                          // Открываем или создаём файл с исходными данными
        File outputFile = getOutputFilePath();                                                                          // Записываем путь к новому файлу, для этого вызываем вспомогательный метод getOutputFilePath()
        List<Address> list = new ArrayList<>();                                                                         // Создаём коллекцию(список) куда данные будем записывать адреса в виде объектов класса 'Address'
        String line;                                                                                                    // Объявляем переменную, в которую будут записываться считываемые строки
        try (BufferedReader bufferedReader = new BufferedReader(                                                        // С помощью оператора 'try with resources' создаем безопасные поток ЧТЕНИЯ 'bufferedReader' целой строки в буфер
                                             new InputStreamReader(                                                     // В наш буфер для чтения обёрнут символьный поток данных 'InputStreamReader'
                                             new FileInputStream(inputFile), StandardCharsets.UTF_8));                  // Здесь создается байтовый входной поток данных в кодировке 'UTF_8' на основании прописанного пути
             BufferedWriter bufferedWriter = new BufferedWriter(                                                        // В том же блоке 'try with resources' создаем безопасные поток ЗАПИСИ 'bufferedWriter' целой строки
                                             new OutputStreamWriter(                                                    // В наш буфер для записи обёрнут символьный поток данных 'OutputStreamWriter'
                                             new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {              // Здесь создается байтовый вЫходной поток данных в кодировке 'UTF_8' на основании прописанного пути
            while ((line = bufferedReader.readLine()) != null) {                                                        // "До тех по пока строки из файла читаются (!=null)"
                String[] array = line.split("\\|");                                                                     // Разбиваем каждую прочитанную строку по разделителю '|' (именно этот символ разделяет данные в исходном файле)
                                                                                                                        // и записываем каждую часть поочереди в массив
                String house = array[0].trim();                                                                         // Адрес всегда попадает в ячейку [0]. Забираем его оттуда, обрезав пробелы с помощью trim().
                int population = Integer.parseInt(array[1].trim());                                                     // Число жителей всегда попадает в ячейку [1]. Забираем его оттуда, обрезав пробелы с помощью trim(). Далеем парсим строковые данные в целое число
                String level = array[2].trim();                                                                         // Уровень всегда попадает в ячейку [2]. Забираем его оттуда, обрезав пробелы с помощью trim().
                list.add(new Address(house, population, level));                                                        // Добавляем в коллекцию вновь созданный объект с параметрами, полученными выше
            }
            Collections.sort(list, Comparator.comparingInt(address -> address.population));                             // С помощью специального метода сортируем коллекцию, передав в метод саму коллекцию и специальный объект 'Comparator'
                                                                                                                        // Компаратору указываем, что сравнивать нужно целочисленную характеристику 'population' объекта 'Address'. Он по умолчанию сортирует по возрастанию
            for (Address address : list) {                                                                              // Перебиаем все элементы коллекции
                bufferedWriter.write(address.toString());                                                               // Помещаем каждый объект коллекции в поток записи, предварительно приведя его к строковому представлению,
            }                                                                                                           // для чего в классе Address я переопределил метод 'toString()'
            System.out.println("Адреса отсортированы по возрастанию числа жителей и записаны в этом порядке в файл");   // Просто отчёт, что метод отработал
        }
    }
    public static File getOutputFilePath() {                                                                            // Я до конца не понял, надо ли было это делать в рамках задания, или можно было тупо самому дату нарисовать...
        Date currentDate = new Date();                                                                                  // Создаём объект дата, чтобы получить точную дату и время
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_YYYY");                                                // Создаём шаблон формата, согласно которого нам нужно получить данные (день_месяц_год)
        String date = formatter.format(currentDate);                                                                    // Получаем строку с помошью шаблона, подставиви в него дату
        File filePath = new File("src/task_2_addresses_result_" + date + ".txt");                                       // Дописываем эту дату к уже почти сформированному пути
        return filePath;                                                                                                // Возвращаем эту строку, выше мы её используем при создании файла
    }

}
