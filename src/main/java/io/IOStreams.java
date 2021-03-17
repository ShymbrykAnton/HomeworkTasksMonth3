package io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOStreams {
    /**
     * 1) Для выполнения первого режима записи (Стереть все существующие данные и записать новые данные)
     * неободимо запустить метод cleanOrAddDataToAnotherFile(false,false)
     *
     * 2) Для выполнения второго режима записи (Записать данные в начало файла. Существующие данные не затираются)
     * неободимо запустить метод cleanOrAddDataToAnotherFile(true,false)
     *
     * 3) Для выполнения третьего режима записи (Записать данные в конец файла. Существующие данные не затираются)
     * неободимо запустить метод cleanOrAddDataToAnotherFile(false,true)
     */

    public static void main(String[] args) {
        IOStreams ioStreams = new IOStreams();
        try {
            ioStreams.cleanOrAddDataToAnotherFile(false,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cleanOrAddDataToAnotherFile(boolean cleanOrAddToEnd, boolean addToStart) throws IOException {
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        BufferedWriter bufferedCleaner;
        List<File> fileList = getFilesFromIoDir();

        File fileContainer = new File("src/main/java/io/Container.txt");
        List<String> strings = getListFromContainer(fileContainer);
        if (!fileContainer.exists()) {
            fileContainer.createNewFile();
        }

        createFiles();
        bufferedWriter = new BufferedWriter(new FileWriter(fileContainer,cleanOrAddToEnd));


            for (File file1 : fileList) {
                bufferedReader = new BufferedReader(new FileReader(file1));

                while (bufferedReader.ready()) {
                    bufferedWriter.write(bufferedReader.readLine());
                    bufferedWriter.newLine();
                }

                bufferedCleaner = new BufferedWriter(new FileWriter(file1));
                bufferedCleaner.close();
                bufferedReader.close();
        }

        bufferedWriter.flush();
        bufferedWriter.close();

        if (addToStart) {
            addToFileStart(strings,fileContainer);
        }
    }

    private void createFiles() {
        BufferedWriter bufferedWriter;
        for (int i = 1; i <= Byte.MAX_VALUE; i++) {
            File file = new File("src/main/java/io/files/File№" + i + ".txt");
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write("Это файл номер: " + i);
                bufferedWriter.newLine();
                bufferedWriter.write("Квадрат номера файла: " + (i * i));
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> getListFromContainer(File file) throws IOException {
        BufferedReader readFromContainer = new BufferedReader(new FileReader(file));
        List<String> strings = new ArrayList<>();
        while (readFromContainer.ready()) {
            strings.add(readFromContainer.readLine());
        }
        return strings;
    }

    private List<File> getFilesFromIoDir() {
        File[] files = new File("src/main/java/io/files/").listFiles();
        List<File> fileList = new ArrayList<>();
        if (files != null) {
            Stream<File> fileStream = Arrays.stream(files).sorted(new FileComparator());
            fileList = fileStream.collect(Collectors.toList());
        }
        return fileList;
    }

    private void addToFileStart(List<String> strings, File file) throws IOException {
        BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter(file,true));
        for (String str : strings) {
            bufferedWriter1.write(str);
            bufferedWriter1.newLine();
        }
        bufferedWriter1.flush();
        bufferedWriter1.close();
    }
}