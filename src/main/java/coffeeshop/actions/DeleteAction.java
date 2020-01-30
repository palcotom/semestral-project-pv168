package coffeeshop.actions;

import coffeeshop.CoffeeException;
import coffeeshop.CoffeeTableModel;
import coffeeshop.Main;
import coffeeshop.views.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractAction {

    private final JTable table;
    final static Logger log = LoggerFactory.getLogger(Main.class);

    public DeleteAction(JTable table){
        super ("Delete");
        this.table = table;
    }

    //TODO add popup button for confirmation
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JPanel deleteOptionPanel = new JPanel();
        deleteOptionPanel.add(new JLabel("Are you sure you want to delete item?"));
        try {
            int deleteOptionDialog = JOptionPane.showConfirmDialog(null, deleteOptionPanel,
                    "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (deleteOptionDialog == JOptionPane.YES_OPTION) {  //if "ok" button pressed
                int selectedRow = table.getSelectedRow();
                if (selectedRow < 0 ){
                    throw new IllegalStateException("No row selected");
                }
                int modelRow = table.convertRowIndexToModel(selectedRow);
                try{
                    Long id = ((CoffeeTableModel) table.getModel()).getRow(modelRow).getId();
                    MainWindow.coffeeManager.deleteCoffee(id);
                }catch(CoffeeException e){
                    log.error("Delete action error",e);
                }
                ((CoffeeTableModel) table.getModel()).deleteRow(modelRow);
            }
        } catch (Exception ex) {
            log.error("Delete option dialog failed", ex);
        }
    }
}