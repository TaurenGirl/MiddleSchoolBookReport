import javafx.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class MSBR extends Application {

    String preferencesFileName = "preferences.txt";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       

    StackPane root = new StackPane();
    Scene scene = new Scene(root,600,400);
    primaryStage.setTitle("Middle School Book Report");
    primaryStage.setScene(scene);
    primaryStage.show();

    }

    public void setName() {
        String name = JOptionPane.showInputDialog("Please enter your name.");
        createFile(preferencesFileName);
        writeToFile(preferencesFileName, name);
    }

    public void createFile(String preferencesFileName) {
        try {
            File file = new File(preferencesFileName);
            if (file.createNewFile()) {
                System.out.println(preferencesFileName + " created.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile(String file, String text) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(text + "\n");
            writer.close();
            System.out.println("Wrote to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

