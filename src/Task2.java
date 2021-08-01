import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task2 {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("src/task_2_addresses.txt");                                                          // ��������� ��� ������ ���� � ��������� �������
        File outputFile = getOutputFilePath();                                                                          // ���������� ���� � ������ �����, ��� ����� �������� ��������������� ����� getOutputFilePath()
        List<Address> list = new ArrayList<>();                                                                         // ������ ���������(������) ���� ������ ����� ���������� ������ � ���� �������� ������ 'Address'
        String line;                                                                                                    // ��������� ����������, � ������� ����� ������������ ����������� ������
        try (BufferedReader bufferedReader = new BufferedReader(                                                        // � ������� ��������� 'try with resources' ������� ���������� ����� ������ 'bufferedReader' ����� ������ � �����
                                             new InputStreamReader(                                                     // � ��� ����� ��� ������ ������ ���������� ����� ������ 'InputStreamReader'
                                             new FileInputStream(inputFile), StandardCharsets.UTF_8));                  // ����� ��������� �������� ������� ����� ������ � ��������� 'UTF_8' �� ��������� ������������ ����
             BufferedWriter bufferedWriter = new BufferedWriter(                                                        // � ��� �� ����� 'try with resources' ������� ���������� ����� ������ 'bufferedWriter' ����� ������
                                             new OutputStreamWriter(                                                    // � ��� ����� ��� ������ ������ ���������� ����� ������ 'OutputStreamWriter'
                                             new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {              // ����� ��������� �������� �������� ����� ������ � ��������� 'UTF_8' �� ��������� ������������ ����
            while ((line = bufferedReader.readLine()) != null) {                                                        // "�� ��� �� ���� ������ �� ����� �������� (!=null)"
                String[] array = line.split("\\|");                                                                     // ��������� ������ ����������� ������ �� ����������� '|' (������ ���� ������ ��������� ������ � �������� �����)
                                                                                                                        // � ���������� ������ ����� ��������� � ������
                String house = array[0].trim();                                                                         // ����� ������ �������� � ������ [0]. �������� ��� ������, ������� ������� � ������� trim().
                int population = Integer.parseInt(array[1].trim());                                                     // ����� ������� ������ �������� � ������ [1]. �������� ��� ������, ������� ������� � ������� trim(). ������ ������ ��������� ������ � ����� �����
                String level = array[2].trim();                                                                         // ������� ������ �������� � ������ [2]. �������� ��� ������, ������� ������� � ������� trim().
                list.add(new Address(house, population, level));                                                        // ��������� � ��������� ����� ��������� ������ � �����������, ����������� ����
            }
            Collections.sort(list, Comparator.comparingInt(address -> address.population));                             // � ������� ������������ ������ ��������� ���������, ������� � ����� ���� ��������� � ����������� ������ 'Comparator'
                                                                                                                        // ����������� ���������, ��� ���������� ����� ������������� �������������� 'population' ������� 'Address'. �� �� ��������� ��������� �� �����������
            for (Address address : list) {                                                                              // ��������� ��� �������� ���������
                bufferedWriter.write(address.toString());                                                               // �������� ������ ������ ��������� � ����� ������, �������������� ������� ��� � ���������� �������������,
            }                                                                                                           // ��� ���� � ������ Address � ������������� ����� 'toString()'
            System.out.println("������ ������������� �� ����������� ����� ������� � �������� � ���� ������� � ����");   // ������ �����, ��� ����� ���������
        }
    }
    public static File getOutputFilePath() {                                                                            // � �� ����� �� �����, ���� �� ���� ��� ������ � ������ �������, ��� ����� ���� ���� ������ ���� ����������...
        Date currentDate = new Date();                                                                                  // ������ ������ ����, ����� �������� ������ ���� � �����
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_YYYY");                                                // ������ ������ �������, �������� �������� ��� ����� �������� ������ (����_�����_���)
        String date = formatter.format(currentDate);                                                                    // �������� ������ � ������� �������, ���������� � ���� ����
        File filePath = new File("src/task_2_addresses_result_" + date + ".txt");                                       // ���������� ��� ���� � ��� ����� ��������������� ����
        return filePath;                                                                                                // ���������� ��� ������, ���� �� � ���������� ��� �������� �����
    }

}
