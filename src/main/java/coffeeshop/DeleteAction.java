package coffeeshop;

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
        ((CoffeeTableModel) table.getModel()).deleteRow(modelRow);
    }
}