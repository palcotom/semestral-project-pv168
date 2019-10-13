import javax.swing.*;
import java.awt.*;

/**
 * @author Oskar Spacek
 */
public class MainWindow extends JFrame {

    private MainWindow() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar toolBar = new JToolBar();
        add(toolBar, BorderLayout.BEFORE_FIRST_LINE);

        /**
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(actionEvent -> System.exit(0));
         */

        JButton addButton = new JButton("Add");
        JButton filterButton = new JButton("Filter");
        JButton removeButton = new JButton("Remove");

        //toolBar.add(exitButton);
        toolBar.add(addButton);
        toolBar.add(filterButton);
        toolBar.add(removeButton);

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() ->
                new MainWindow().setVisible(true));
    }
}
