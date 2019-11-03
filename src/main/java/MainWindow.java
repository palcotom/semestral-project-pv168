import com.google.common.collect.ImmutableList;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oskar Spacek
 */
public class MainWindow extends JFrame {
    private final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    private static final List<Coffee> TEST_DATA = ImmutableList.of(
            new Coffee("Mocha", new Date(11), "Arabica", 100, "Yemen", "Light"),
            new Coffee("Mocha", new Date(40), "Arabica", 150, "Yemen", "Medium")
    );

    private MainWindow() {
        try {
            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (UnsupportedLookAndFeelException e) {
        }
        catch (ClassNotFoundException e) {
        }
        catch (InstantiationException e) {
        }
        catch (IllegalAccessException e) {
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar toolBar = new JToolBar();
        add(toolBar, BorderLayout.BEFORE_FIRST_LINE);

        JButton addButton = new JButton("Add",new ImageIcon(MainWindow.class.getResource("icons8-windows-10-32.png")));
        toolBar.add(addButton);

        //Simplistic dialog for creating new elements
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
        myPanel.add(new JLabel("Roasting:"));
        myPanel.add(bakingField);




        TableModel tableModel = new CoffeeTableModel(new ArrayList<>(TEST_DATA));

        JTable table = new JTable(tableModel);
        table.setRowHeight(20);
        add(new JScrollPane(table));

        JButton filterButton = new JButton("Filter");
        toolBar.add(filterButton);

        JButton removeButton = new JButton("Remove", new ImageIcon(MainWindow.class.getResource("icons8-remove-30.png")));

        table.getSelectionModel().addListSelectionListener(e -> {
            boolean rowSelected = table.getSelectedRow() >= 0;
            removeButton.setEnabled(rowSelected);
        });

        //4
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        removeButton.addActionListener( e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow < 0 ){
                throw new IllegalStateException("No row selected");
            }
            int modelRow = table.convertRowIndexToModel(selectedRow);
            ((CoffeeTableModel) table.getModel()).deleteRow(modelRow);
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int addOptionDialog = JOptionPane.showConfirmDialog(null, myPanel,"Enter values",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); //displays dialog for creating new item

                    if (addOptionDialog == JOptionPane.OK_OPTION){  //if ok button pressed
                        java.util.Date textFieldAsDate = null;  //convert date in for mof dd/MM/yyyy to java.util.Date
                        try {
                            textFieldAsDate = format.parse(dateField.getText());
                        } catch (Exception e) {
                            // deal with ParseException
                        }
                        Coffee form_coffee = new Coffee(nameField.getText(), textFieldAsDate , typeField.getText(), Integer.parseInt(weightField.getText()), originField.getToolTipText(), bakingField.getText());
                        ((CoffeeTableModel) table.getModel()).addRow(form_coffee);
                    }
                } catch (Exception ex) {

                }
            }
        });

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

