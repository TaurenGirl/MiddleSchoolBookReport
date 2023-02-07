package com.gmail.skibinski.tomi.msbr;

import java.util.Date;

public class Book {
    
    private int id;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String studentFirstName;
    private String studentLastName;
    private Date checkoutDate;

    public Book() {

    }

    public Book(String title, String authorFirstName, String authorLastName) {
        
    }

    public Book(int id, String title, String authorFirstName, String authorLastName, String studentFirstName, String studentLastName, Date checkoutDate) {
        this.id = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.checkoutDate = checkoutDate;
    }

    public int getId() {return this.id;}

    public void setId(int id) {this.id = id;}

    public String getTitle() {return this.title;}

    public void setTitle(String title) {this.title = title;}

    public String getAuthorFirstName() {return this.authorFirstName;}

    public void setAuthorFirstName(String authorFirstName) {this.authorFirstName = authorFirstName;}

    public String getAuthorLastName() {return this.authorLastName;}

    public void setAuthorLastName(String authorLastName) {this.authorLastName = authorLastName;}

    public String getStudentFirstName() {return this.studentFirstName;}

    public void setStudentFirstName(String studentFirstName) {this.studentFirstName = studentFirstName;}

    public String getStudentLastName() {return this.studentLastName;}

    public void setStudentLastName(String studentLastName) {this.studentLastName = studentLastName;}

    public Date getCheckoutDate() {return this.checkoutDate;}

    public void setCheckoutDate(Date checkoutDate) {this.checkoutDate = checkoutDate;}

}
