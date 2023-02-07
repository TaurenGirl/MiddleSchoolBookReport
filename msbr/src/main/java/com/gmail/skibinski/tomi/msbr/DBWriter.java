package com.gmail.skibinski.tomi.msbr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DBWriter {
    
    private String classSeperator = "{";
    private String attributeSeperator = "[";

    public DBWriter() {

    }

    public void writeAll(List<Book> list, File file) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        for (int i = 0; i < list.size(); i++) {
            String string = "{[" + list.get(i).getId() + "[" + list.get(i).getTitle() + "[" + list.get(i).getAuthorFirstName() + "[" + list.get(i).getAuthorLastName() + "[" + list.get(i).getStudentFirstName() + "[" +list.get(i).getStudentLastName() + "[" + list.get(i).getCheckoutDate().toString() + "\n";
            try {
                writer.write(string);
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        writer.close();
    }

    public void write(Book book, File file) {
        try {
        FileWriter writer = new FileWriter(file, true);
        String string = "[" + book.getId() + "[" + book.getTitle() + "[" + book.getAuthorFirstName() + "[" + book.getAuthorLastName() + "[" + book.getStudentFirstName() + "[" + book.getStudentLastName() + "[" + book.getCheckoutDate() + "}" + "\n";
        writer.write(string);
        writer.close();
        } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

    public int getId(Path path) {
        String string = "";
        try {
            string = Files.readString(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String[] str = string.split("}", 0);
        return str.length;
    }

}
