package com.gmail.skibinski.tomi.msbr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBWriter {
    
    private String classSeperator = "{";
    private String attributeSeperator = "[";

    public DBWriter() {

    }

    public void amendFile(Book book, File file) {
        List<Book> list = this.read(file.toPath());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(book.getId())) {
                list.get(i).setStudentFirstName(book.getStudentFirstName());
                list.get(i).setStudentLastName(book.getStudentLastName());
                list.get(i).setCheckoutDate(book.getCheckoutDate());
            }
        }
        try {
            FileWriter writer = new FileWriter(file, false);
            for (int j = 0; j < list.size(); j++) {
                String string = list.get(j).getId() + "!" + list.get(j).getTitle() + "!" + list.get(j).getAuthorFirstName() + "!" + list.get(j).getAuthorLastName() + "!" + list.get(j).getStudentFirstName() + " " + "!" + list.get(j).getStudentLastName() + " " + "!" + list.get(j).getCheckoutDate() + " " + "@" + "\n";
                writer.write(string);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void write(Book book, File file) {
        try {
        FileWriter writer = new FileWriter(file, true);
        String string = book.getId() + "!" + book.getTitle() + "!" + book.getAuthorFirstName() + "!" + book.getAuthorLastName() + "!" + book.getStudentFirstName() + " " + "!" + book.getStudentLastName() + " " + "!" + book.getCheckoutDate() + " " + "@" + "\n";
        writer.write(string);
        writer.close();
        } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

    public String getId(Path path) {
        String string = "";
        try {
            string = Files.readString(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String[] str = string.split("@", 0);
        int id = str.length;
        return String.valueOf(id);
    }

    public List<Book> read(Path path) {
        List<Book> list = new ArrayList<>();
        String string = "";
        try {
            string = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] str = string.split("@", 0);
        for (int i = 0; i < str.length-1; i++) {
            Book book = new Book();
            String[] bkStr = str[i].split("!", 0);
            book.setId(bkStr[0].stripLeading());
            book.setTitle(bkStr[1]);
            book.setAuthorFirstName(bkStr[2]);
            book.setAuthorLastName(bkStr[3]);
            book.setStudentFirstName(bkStr[4]);
            book.setStudentLastName(bkStr[5]);
            book.setCheckoutDate(bkStr[6]);
            list.add(book);
        }
        return list;
    }

    public Book checkout(Book book, File file) {
        Date date = new Date(); 
        book.setCheckoutDate(date.toString());
        this.amendFile(book, file);
        return book;
    }

}
