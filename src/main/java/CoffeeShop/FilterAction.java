package CoffeeShop;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;

public class FilterAction extends AbstractAction {
    public TableRowSorter sorter;
    public FilterAction(TableRowSorter sorter){
        this.sorter=sorter;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JTextField filterText = new JTextField(5);
        JPanel filterOptionPanel = new JPanel();
        filterOptionPanel.add(new JLabel("Filter by Name:"));
        filterOptionPanel.add(filterText);
        try {
            int filterOptionDialog = JOptionPane.showConfirmDialog(null, filterOptionPanel, "Enter values", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); //displays dialog for creating new item

            if (filterOptionDialog == JOptionPane.OK_OPTION) {  //if ok button pressed
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
}
