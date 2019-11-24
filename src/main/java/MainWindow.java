import com.google.common.collect.ImmutableList;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
            new Coffee("Mocha", new Date(40), "Arabica", 150, "Yemen", "Medium"),
            new Coffee("Robusta", new Date(4000), "Arabica", 50, "Yemen", "Medium"),
            new Coffee("Liberica", new Date(40000), "Arabica", 250, "Yemen", "Medium"),
            new Coffee("Robusta", new Date(405000), "Arabica", 150, "Yemen", "Medium")
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






        TableModel tableModel = new CoffeeTableModel(new ArrayList<>(TEST_DATA));
        TableRowSorter sorter = new TableRowSorter<CoffeeTableModel>((CoffeeTableModel) tableModel);

        JTable table = new JTable(tableModel);
        table.setRowHeight(20);
        table.setRowSorter(sorter);
        add(new JScrollPane(table));

        JTextField filterText = new JTextField(5);
        JPanel filterOptionPanel = new JPanel();
        filterOptionPanel.add(new JLabel("Filter by Name:"));
        filterOptionPanel.add(filterText);

        JButton filterButton = new JButton("Filter", new ImageIcon(MainWindow.class.getResource("filter-tool-black-shape.png")));
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int filterOptionDialog = JOptionPane.showConfirmDialog(null, filterOptionPanel,"Enter values",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); //displays dialog for creating new item

                    if (filterOptionDialog == JOptionPane.OK_OPTION){  //if ok button pressed
                        RowFilter<CoffeeTableModel, Object> rf = null;
                        //If current expression doesn't parse, don't update.
                        try {
                            rf = RowFilter.regexFilter(filterText.getText(), 0);
                        } catch (java.util.regex.PatternSyntaxException e) {
                            return;
                        }
                        sorter.setRowFilter(rf);

                    }
                } catch (Exception ex) {

                }
            }
        });
                
        toolBar.add(filterButton);

        JButton removeButton = new JButton("Remove", new ImageIcon(MainWindow.class.getResource("icons8-remove-30.png")));

        table.getSelectionModel().addListSelectionListener(e -> {
            boolean rowSelected = table.getSelectedRow() >= 0;
            removeButton.setEnabled(rowSelected);
        });

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        removeButton.addActionListener( e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow < 0 ){
                throw new IllegalStateException("No row selected");
            }
            int modelRow = table.convertRowIndexToModel(selectedRow);
            ((CoffeeTableModel) table.getModel()).deleteRow(modelRow);
        });

        //Simplistic dialog for creating new elements
        JTextField nameField = new JTextField(5);
        JTextField dateField = new JTextField(5);
        JTextField typeField = new JTextField(5);
        JTextField weightField = new JTextField(5);
        JTextField originField = new JTextField(5);
        JTextField bakingField = new JTextField(5);

        JPanel addOptionPanel = new JPanel();
        addOptionPanel.setLayout(new BoxLayout(addOptionPanel, BoxLayout.PAGE_AXIS));
        addOptionPanel.add(new JLabel("Name:"));
        addOptionPanel.add(nameField);
        addOptionPanel.add(new JLabel("Date:"));
        addOptionPanel.add(dateField);
        addOptionPanel.add(new JLabel("Type:"));
        addOptionPanel.add(typeField);
        addOptionPanel.add(new JLabel("Weight:"));
        addOptionPanel.add(weightField);
        addOptionPanel.add(new JLabel("Origin:"));
        addOptionPanel.add(originField);
        addOptionPanel.add(new JLabel("Roasting:"));
        addOptionPanel.add(bakingField);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int addOptionDialog = JOptionPane.showConfirmDialog(null, addOptionPanel,"Enter values",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); //displays dialog for creating new item

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

