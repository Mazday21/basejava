package ru.basejava;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    static void list(String dir) {
        File f = new File(dir);
        String[] dirList = f.list();
        int i;
        for (i = 0; i < Objects.requireNonNull(dirList).length; i++) {
            File f1 = new File(dir +
                    File.separator + dirList[i]);

            if (f1.isFile())
                System.out.println(dir +
                        File.separator + dirList[i]);
            else {
                list(dir +
                        File.separator + dirList[i]);
            }
        }
    }

    public static void main(String[] args) {
        String filePath = ".\\.gitignore";
        System.out.println();
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./basejava/src");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }
        list(dir.getAbsolutePath());

//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

}
