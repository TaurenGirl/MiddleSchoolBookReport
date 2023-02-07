package com.gmail.skibinski.tomi.msbr;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;

import java.util.Date;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private MenuBar menuBar;
    private Group tableGroup;
    private TableView<Book> table;
    private Label idLabel;
    private Label titleLabel;
    private Label authorLabel;
    private Label studentLabel;
    private Label checkoutLabel;

    private DBWriter dbWriter;
    private File db = new File("MSBRDatabase.txt");

    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        root.setStyle("-fx-background-color: Beige;");

        Menu editMenu = new Menu("Edit");
        
        MenuItem addBookMenuItem = new MenuItem("Add Book");

        editMenu.getItems().add(addBookMenuItem);

        menuBar = new MenuBar();
        menuBar.getMenus().add(editMenu);
        root.getChildren().add(menuBar);

        root.getChildren().add(addSearchBar());


        tableGroup = new Group();
        table = new TableView<>();
        table.setEditable(false);
        table.setPrefSize(1280, 480);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Book, Integer> idCol = new TableColumn<>("id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setMinWidth(100);
        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setMinWidth(100);
        TableColumn<Book, String> afCol = new TableColumn<>("Author First Name");
        afCol.setCellValueFactory(new PropertyValueFactory<>("authorFirstName"));
        afCol.setMinWidth(100);
        TableColumn<Book, String> alCol = new TableColumn<>("Author Last Name");
        alCol.setCellValueFactory(new PropertyValueFactory<>("authorLastName"));
        alCol.setMinWidth(100);
        TableColumn<Book, String> sfCol = new TableColumn<>("Student First Name");
        sfCol.setCellValueFactory(new PropertyValueFactory<>("studentFirstName"));
        sfCol.setMinWidth(100);
        TableColumn<Book, String> slCol = new TableColumn<>("Student Last Name");
        slCol.setCellValueFactory(new PropertyValueFactory<>("studentLastName"));
        slCol.setMinWidth(100);
        TableColumn<Book, Date> checkCol = new TableColumn<>("Checkout Date");
        checkCol.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        checkCol.setMinWidth(100);

        table.getColumns().addAll(idCol,titleCol,afCol,alCol,sfCol,slCol,checkCol);

        //table.getItems().add(new Book(1, "Pride and Prejudice", "Jane", "Austin", "John", "Smith", new Date()));
        //table.getItems().add(new Book(2, "The Hobbit", "J.R.R.", "Tolkein", "Jane", "Doe", new Date()));

        GridPane dataPane = new GridPane();
        dataPane.setHgap(10);
        dataPane.setVgap(2);
        dataPane.setPadding(new Insets(10, 10, 10, 10));
        dataPane.setStyle("-fx-background-color: Beige;");
        Font font = new Font(36);
        idLabel = new Label("id: ");
        idLabel.setFont(font);
        titleLabel = new Label("Title: ");
        titleLabel.setFont(font);
        authorLabel = new Label("Author: ");
        authorLabel.setFont(font);
        studentLabel = new Label("Student: ");
        studentLabel.setFont(font);
        checkoutLabel = new Label("Date: ");
        checkoutLabel.setFont(font);

        Button checkoutButton = new Button("checkout");
        checkoutButton.setMinWidth(128);
        checkoutButton.setPrefHeight(48);
        checkoutButton.setFont(font);
        checkoutButton.setStyle("-fx-background-radius: 24px; -fx-background-color: darkslateblue; -fx-text-fill: white;");

        dataPane.add(idLabel,0,0);
        dataPane.add(titleLabel,0,1);
        dataPane.add(authorLabel,0,2);
        dataPane.add(studentLabel,0,3);
        dataPane.add(checkoutLabel,0,4);
        dataPane.add(checkoutButton,0,5);

        addBookMenuItem.setOnAction(e -> {
            dbWriter = new DBWriter();
            Book book = addBookDialog().showAndWait().orElse(null);
            book.setId(dbWriter.getId(db.toPath()));
            dbWriter.write(book, db);
            //table.getItems().add(addBookDialog().showAndWait().orElse(null));

        });

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            idLabel.setText("id: " + newValue.getId());
            titleLabel.setText("Title: " + newValue.getTitle());
            authorLabel.setText("Author: " + newValue.getAuthorFirstName() + " " + newValue.getAuthorLastName());
            studentLabel.setText("Student: " + newValue.getStudentFirstName() + " " + newValue.getStudentLastName());
            checkoutLabel.setText("Date: " + newValue.getCheckoutDate());
        });

        tableGroup.getChildren().add(table);
        ScrollPane sp = new ScrollPane();
        sp.setContent(tableGroup);

        root.getChildren().add(sp);
        root.getChildren().add(dataPane);

        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Middle School Book Report");
        stage.show();
    }

    private Dialog<Book> addBookDialog() {
        //create custom dialog
        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Add Book");
        dialog.setHeaderText("Add Book to Database.");

        //set button types
        ButtonType addButtonType = new ButtonType("Add", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        //create Book details labels and fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        TextField title = new TextField();
        title.setPromptText("Title");
        TextField firstName = new TextField();
        firstName.setPromptText("Author First Name");
        TextField lastName = new TextField();
        lastName.setPromptText("Author Last Name");

        grid.add(new Label("Title:"),0,0);
        grid.add(title, 1, 0);
        grid.add(new Label("Author First Name:"),0,1);
        grid.add(firstName,1,1);
        grid.add(new Label("Author Last Name:"),0,2);
        grid.add(lastName,1,2);

        //enable/disable add button
        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        //validate input
        title.textProperty().addListener((observabe, oldValue, newValue) -> {
            if (!title.textProperty().get().trim().isEmpty() && !firstName.textProperty().get().trim().isEmpty()
                    && !lastName.textProperty().get().trim().isEmpty()) {
                addButton.setDisable(false);
            } else {
                addButton.setDisable(true);
            }
        });
        firstName.textProperty().addListener((observabe, oldValue, newValue) -> {
            if (!title.textProperty().get().trim().isEmpty() && !firstName.textProperty().get().trim().isEmpty()
                    && !lastName.textProperty().get().trim().isEmpty()) {
                addButton.setDisable(false);
            } else {
                addButton.setDisable(true);
            }
        });
        lastName.textProperty().addListener((observabe, oldValue, newValue) -> {
            if (!title.textProperty().get().trim().isEmpty() && !firstName.textProperty().get().trim().isEmpty()
                    && !lastName.textProperty().get().trim().isEmpty()) {
                addButton.setDisable(false);
            } else {
                addButton.setDisable(true);
            }
        });

        dialog.getDialogPane().setContent(grid);

        //request focus on the title field by default
        Platform.runLater(() -> title.requestFocus());

        //convert input to Book
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Book(0, title.getText(), firstName.getText(), lastName.getText(), "", "", null);
            }
            return null;
        });
        return dialog;

    }

    public GridPane addSearchBar() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(2);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField searchTF = new TextField();
        searchTF.setPromptText("search");
        CheckBox idBox = new CheckBox("id");
        idBox.setSelected(true);;
        CheckBox titleBox = new CheckBox("Title");
        titleBox.setSelected(true);
        CheckBox authorBox = new CheckBox("Author");
        authorBox.setSelected(true);
        CheckBox studentBox = new CheckBox("Student");
        studentBox.setSelected(true);
        Button searchButton = new Button("search");
        searchButton.setStyle("-fx-background-radius: 9px; -fx-background-color: darkslateblue; -fx-text-fill: white;");

        grid.add(searchTF,0,0);
        GridPane.setColumnSpan(searchTF, 4);
        grid.add(searchButton,4,0);
        grid.add(idBox,0,1);
        grid.add(titleBox,1,1);
        grid.add(authorBox,2,1);
        grid.add(studentBox,3,1);

        return grid;
    }


    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}