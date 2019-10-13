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

        JButton addButton = new JButton("Add");
        JButton filterButton = new JButton("Filter");
        JButton removeButton = new JButton("Remove");
        toolBar.add(addButton);
        toolBar.add(filterButton);
        toolBar.add(removeButton);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        //TODO add icons
        JMenuItem loadMenuItem = new JMenuItem("Load");
        fileMenu.add(loadMenuItem);
        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(saveMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(actionEvent -> System.exit(0));
        fileMenu.add(exitMenuItem);

        setJMenuBar(menuBar);
        pack();


    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() ->
                new MainWindow().setVisible(true));
    }
}
