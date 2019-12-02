package coffeeshop;

import com.google.common.collect.ImmutableList;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oskar Spacek
 */
public class MainWindow extends JFrame {
    private final JTextField nameField = new JTextField(5);
    private final JTextField dateField = new JTextField(5);
    private final JTextField typeField = new JTextField(5);
    private final JTextField weightField = new JTextField(5);
    private final JTextField originField = new JTextField(5);
    private final JTextField bakingField = new JTextField(5);

    private static final List<Coffee> TEST_DATA = ImmutableList.of(
            new Coffee("Mocha", new Date(11), "Arabica", 100, Roasting.LIGHT),
            new Coffee("Mocha", new Date(40), "Arabica", 150, Roasting.MEDIUM),
            new Coffee("Robusta", new Date(4000), "Arabica", 50, Roasting.MEDIUM),
            new Coffee("Liberica", new Date(40000), "Arabica", 250,  Roasting.MEDIUM),
            new Coffee("Robusta", new Date(405000), "Arabica", 150,  Roasting.MEDIUM)
    );

    private MainWindow() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }

        TableModel tableModel = new CoffeeTableModel(new ArrayList<>(TEST_DATA));
        TableRowSorter sorter = new TableRowSorter<CoffeeTableModel>((CoffeeTableModel) tableModel);
        JTable table = new JTable(tableModel);
        table.setRowHeight(20);
        table.setRowSorter(sorter);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JToolBar toolBar = new JToolBar();
        add(toolBar, BorderLayout.BEFORE_FIRST_LINE);

        add(new JScrollPane(table));
        JPanel addOptionPanel = new JPanel();
        addOptionWindow(addOptionPanel);

        JButton addButton = new JButton("Add", new ImageIcon(MainWindow.class.getResource("../icons8-windows-10-32.png")));
        toolBar.add(addButton);
        addButton.addActionListener(new AddAction(table, addOptionPanel, nameField, dateField, typeField, weightField, bakingField));

        JButton filterButton = new JButton("Filter", new ImageIcon(MainWindow.class.getResource("../filter-tool-black-shape.png")));
        filterButton.addActionListener(new FilterAction(sorter));
        toolBar.add(filterButton);

        JButton removeButton = new JButton("Remove", new ImageIcon(MainWindow.class.getResource("../icons8-remove-30.png")));
        removeButton.addActionListener(new DeleteAction(table));
        toolBar.add(removeButton);

        table.getSelectionModel().addListSelectionListener(e -> {
            boolean rowSelected = table.getSelectedRow() >= 0;
            removeButton.setEnabled(rowSelected);
        });
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);
        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(saveMenuItem);
        Action exitAction = new ExitAction();
        fileMenu.add(exitAction);
        setJMenuBar(menuBar);
        pack();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() ->
                new MainWindow().setVisible(true));
    }

    public void addOptionWindow(JPanel addOptionPanel) {

        addOptionPanel.setLayout(new BoxLayout(addOptionPanel, BoxLayout.PAGE_AXIS));
        addOptionPanel.add(new JLabel("Name:"));
        addOptionPanel.add(nameField);
        addOptionPanel.add(new JLabel("Date: dd/mm/yyyy"));
        addOptionPanel.add(dateField);
        addOptionPanel.add(new JLabel("Type:"));
        addOptionPanel.add(typeField);
        addOptionPanel.add(new JLabel("Weight:"));
        addOptionPanel.add(weightField);
        addOptionPanel.add(new JLabel("Roasting:"));
        addOptionPanel.add(bakingField);
    }


}

