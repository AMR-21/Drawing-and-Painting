package GUI;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProgramUIFactory {

    // 1 jpanel 2 menu item 3 combo box <String> 4 label 5 text field 6 menu 7 check box 8 menu bar 9 menu item 10 button   
    private static final ProgramUIFactory factory = new ProgramUIFactory();

    private ProgramUIFactory() {
    }

    public static ProgramUIFactory getInstance() {
        return factory;
    }

    public JComponent inject(int mode) {
        switch (mode) {
            case 1:
                return new JPanel();
            case 2:
                return new JMenuItem();
            case 3:
                return new JComboBox<>();
            case 4:
                return new JLabel();
            case 5:
                return new JTextField();
            case 6:
                return new JMenu();
            case 7:
                return new JCheckBox();
            case 8:
                return new JMenuBar();
            case 9:
                return new JMenuItem();
            case 10:
                return new JButton();
        }
        return null;
    }

}
