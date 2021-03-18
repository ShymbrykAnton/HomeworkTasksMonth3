package io;

import java.io.File;
import java.util.Comparator;

public class FileComparator implements Comparator<File> {

    @Override
    public int compare(File o1, File o2) {
        String fileName1 = o1.getName();
        String fileName2 = o2.getName();
        int fileNumber1 = Integer.parseInt(fileName1.substring(fileName1.lastIndexOf("№") + 1, fileName1.lastIndexOf(".")));
        int fileNumber2 = Integer.parseInt(fileName2.substring(fileName2.lastIndexOf("№") + 1, fileName2.lastIndexOf(".")));
        return fileNumber1 > fileNumber2 ? 1 : -1;
    }
}
