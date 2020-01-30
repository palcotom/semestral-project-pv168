package coffeeshop.views;

import coffeeshop.*;
import coffeeshop.actions.AddAction;
import coffeeshop.actions.DeleteAction;
import coffeeshop.actions.ExitAction;
import coffeeshop.actions.FilterAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;


/**
 * @author Oskar Spacek, Tomas Palco
 */
public class MainWindow extends JFrame {

    final static Logger log = LoggerFactory.getLogger(Main.class);

    public static CoffeeManager coffeeManager;

    public MainWindow() throws CoffeeException {
        try {
            DataSource dataSource = Main.getDataSource();
            coffeeManager = new CoffeeManager(dataSource);
        } catch (IOException e) {
            log.error("Coffee manager initialization error", e);
        }
        List<Coffee> coffees = coffeeManager.getCoffees();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.error("Look and feel error", e);
        }
        TableModel tableModel = new CoffeeTableModel(new ArrayList<>(coffees));
        MainWindowBuilder(tableModel);
        pack();
    }


    void MainWindowBuilder(TableModel tableModel){
        TableRowSorter sorter = new TableRowSorter<CoffeeTableModel>((CoffeeTableModel) tableModel);
        JTable table = new JTable(tableModel);
        table.setRowHeight(20);
        table.setRowSorter(sorter);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JToolBar toolBar = new JToolBar();
        add(toolBar, BorderLayout.BEFORE_FIRST_LINE);

        add(new JScrollPane(table));
        AddWindow addOptionPanel = new AddWindow();

        JButton addButton = new JButton("Add",
                new ImageIcon(MainWindow.class.getResource("../../icons8-windows-10-32.png"))
        );
        toolBar.add(addButton);
        addButton.addActionListener(new AddAction(table, addOptionPanel, addOptionPanel.getNameField(),
                addOptionPanel.getDateField(), addOptionPanel.getTypeField(), addOptionPanel.getWeightField(),
                addOptionPanel.getRoastingBox()));

        JButton filterButton = new JButton("Filter",
                new ImageIcon(MainWindow.class.getResource("../../filter-tool-black-shape.png"))
        );
        filterButton.addActionListener(new FilterAction(sorter));
        toolBar.add(filterButton);

        JButton removeButton = new JButton("Remove",
                new ImageIcon(MainWindow.class.getResource("../../icons8-remove-30.png")));
        removeButton.addActionListener(new DeleteAction(table)
        );
        toolBar.add(removeButton);

        table.getSelectionModel().addListSelectionListener(e -> {
            boolean rowSelected = table.getSelectedRow() >= 0;
            removeButton.setEnabled(rowSelected);
        });
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        Action exitAction = new ExitAction();
        fileMenu.add(exitAction);
        setJMenuBar(menuBar);
    }


}

