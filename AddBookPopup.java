import javaxx.swing.*;
import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


public class AddBookPopup {

    JScrollPane sp;
    JPanel panelHeader;
    JPanel panelEntry;
    JTextField[][] fields;
    JPanel buttonsPanel;
    JButton submitButton;
    JButton cancelButton;
    JPanel allPanels;

    public AddBookPopup() {

        sp = new JScrollPane();
        panelHeader = new JPanel();
        panelHeader.setLayout(new GridLayout(1,4));

        JLabel titleLabel = new JLabel("Title");
        JLabel firstLabel = new JLabel("Author First Name");
        JLabel lastLabel = new JLabel("Author Last Name");
        JLabel copiesLabel = new JLabel("Copies");

        panelHeader.add(titleLabel);
        panelHeader.add(firstLabel);
        panelHeader.add(lastLabel);
        panelHeader.add(copiesLabel);

        panelHeader.setVisible(true);

        panelEntry = new JPanel();
        GridLayout gl2 = new GridLayout(10, 4);
        panelEntry.setLayout(gl2);

        fields = new JTextField[10][3];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <3; j++) {
                fields [i][j] = new JTextField("");
                panelEntry.add(fields[i][j]);
                if (j == 2) {
                    panelEntry.add(new JSpinner(new SpinnerNumberModel(1, 0, 100, 1)));
                }
            }
        }

        panelEntry.setVisible(true);

        allPanels = new JPanel();
        allPanels.setLayout(new BoxLayout(allPanels, BoxLayout.Y_AXIS));
        allPanels.add(panelHeader);
        allPanels.add(panelEntry);

        sp.add(allPanels);
        sp.setVisible(true);
        sp.setViewportView(allPanels);

    }

    public JScrollPane get() {
        return this.sp;
    }


}