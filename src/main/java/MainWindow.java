import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oskar Spacek
 */
public class MainWindow extends JFrame {

    private MainWindow() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar toolBar = new JToolBar();
        add(toolBar, BorderLayout.BEFORE_FIRST_LINE);

        //TODO new window popup with template
        JButton addButton = new JButton("Add");
        toolBar.add(addButton);

        JTextField nameField = new JTextField(5);
        JTextField dateField = new JTextField(5);
        JTextField typeField = new JTextField(5);
        JTextField weightField = new JTextField(5);
        JTextField originField = new JTextField(5);
        JTextField bakingField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
        myPanel.add(new JLabel("Name:"));
        myPanel.add(nameField);
        myPanel.add(new JLabel("Date:"));
        myPanel.add(dateField);
        myPanel.add(new JLabel("Type:"));
        myPanel.add(typeField);
        myPanel.add(new JLabel("Weight:"));
        myPanel.add(weightField);
        myPanel.add(new JLabel("Origin:"));
        myPanel.add(originField);
        myPanel.add(new JLabel("Type of Baking:"));
        myPanel.add(bakingField);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int name = JOptionPane.showConfirmDialog(null, myPanel,"Enter values",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {

                }
            }
        });

        JButton filterButton = new JButton("Filter");
        toolBar.add(filterButton);

        
        JButton removeButton = new JButton("Remove");
        toolBar.add(removeButton);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        //TODO add icons
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);

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

