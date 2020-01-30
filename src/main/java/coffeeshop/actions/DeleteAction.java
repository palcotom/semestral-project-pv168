package coffeeshop.actions;

import coffeeshop.CoffeeException;
import coffeeshop.CoffeeTableModel;
import coffeeshop.views.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractAction {

    private final JTable table;

    public DeleteAction(JTable table){
        super ("Delete");
        this.table = table;
    }

    //TODO add popup button for confirmation
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0 ){
            throw new IllegalStateException("No row selected");
        }
        int modelRow = table.convertRowIndexToModel(selectedRow);
        try{
            Long id = ((CoffeeTableModel) table.getModel()).getRow(modelRow).getId();
            MainWindow.coffeeManager.deleteCoffee(id);
            MainWindow.coffeeManager.printDB();
        }catch(CoffeeException e){

        }
        ((CoffeeTableModel) table.getModel()).deleteRow(modelRow);

    }
}