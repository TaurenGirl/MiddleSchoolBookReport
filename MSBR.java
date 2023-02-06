import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;

public class MSBR {
    
    JFrame frame;
    JScrollPane listScrollPane;
    JTable listTable;
    JPanel dataPanel;

    JTextField firstTextField;
    JTextField lasTextField;
    JTextField checkoutTextField;

    String preferencesFileName = "preferences.txt";
    
    public MSBR() {
        //initialize JFrame
        frame = new JFrame();
        frame.setTitle("Middle School Book Report");
        frame.setSize(400, 500);
        frame.setLayout(null);

        //initialize table data and JTable for list of data
        String[][] listData = {{"1","Test","test", "Test","Hello","World!","Date"}, {"1","Test","Test", "Test","Hello","World!","Date"}};
        String[] listColumnNames = {"#", "Title", "Author First", "Author Last", "Student First", "Student Last", "Checkout"};
        listTable = new JTable(listData, listColumnNames);

        //set table model of JTable so that cells are not editable
        DefaultTableModel tableModel = new DefaultTableModel(listData, listColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //set properties of JTable for list of data
        listTable.setModel(tableModel);
        listTable.setBounds(0, 250, 400, 250);

        //initialize JScrollPane to hold list of data
        listScrollPane = new JScrollPane(listTable);
        listScrollPane.setBounds(0, 250, 400, 250);
        listScrollPane.setVisible(true);
        frame.add(listScrollPane);

        //initialize data panel
        dataPanel = new JPanel();
        GridLayout gl = new GridLayout(3, 4);
        dataPanel.setLayout(gl);
        dataPanel.setBounds(0, 500, 400, 250);

        //layout data panel
        JLabel titleLabel = new JLabel("");
        JLabel authorLabel = new JLabel("");
        dataPanel.add(titleLabel);
        dataPanel.add(authorLabel);
        JLabel firstLabel = new JLabel("First Name: ");
        firstTextField = new JTextField();
        JPanel firstPanel = new JPanel(new BorderLayout());
        firstPanel.add(firstLabel, BorderLayout.WEST);
        firstPanel.add(firstTextField, BorderLayout.CENTER);
        dataPanel.add(firstPanel);

        frame.add(dataPanel);

        listTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                titleLabel.setText("Title: " + listTable.getValueAt(listTable.getSelectedRow(), 1).toString());
                authorLabel.setText("Author: " + listTable.getValueAt(listTable.getSelectedRow(), 2).toString() + " " + listTable.getValueAt(listTable.getSelectedRow(), 3).toString());
            }
        });

        frame.setVisible(true);

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
        MSBR msbr = new MSBR();
        msbr.setName();
    }
}

