package com.gmail.skibinski.tomi.msbr;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

import java.util.Date;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private MenuBar menuBar;
    private Group tableGroup;
    private TableView<Book> table;

    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();

        Menu editMenu = new Menu("Edit");
        
        MenuItem addBookMenuItem = new MenuItem("Add Book");

        editMenu.getItems().add(addBookMenuItem);

        menuBar = new MenuBar();
        menuBar.getMenus().add(editMenu);
        root.getChildren().add(menuBar);

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

        table.getItems().add(new Book(1, "Pride and Prejudice", "Jane", "Austin", "John", "Smith", new Date()));
        table.getItems().add(new Book(2, "The Hobbit", "J.R.R.", "Tolkein", "Jane", "Doe", new Date()));

        addBookMenuItem.setOnAction(e -> {
            table.getItems().add(addBookDialog().showAndWait().orElse(null));

        });

        tableGroup.getChildren().add(table);

        root.getChildren().add(tableGroup);

        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
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
                return new Book(1, title.getText(), firstName.getText(), lastName.getText(), "", "", new Date());
            }
            return null;
        });
        return dialog;

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