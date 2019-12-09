package coffeeshop;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;

public class FilterAction extends AbstractAction {
    public TableRowSorter sorter;
    public FilterAction(TableRowSorter sorter){
        this.sorter=sorter;
    }

    /**
     *
     * @param jText that is being normalized
     * @return String that consists of first letter in upper case and the rest is in lower case
     */
    private String caseNormalization(JTextField jText) {
        String text = jText.getText().toLowerCase();
        text = text.replace(text.charAt(0), text.toUpperCase().charAt(0));
        return text;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JTextField filterText = new JTextField(5);
        JPanel filterOptionPanel = new JPanel();
        filterOptionPanel.add(new JLabel("Filter by Name:"));
        //TODO other options for filtering
        filterOptionPanel.add(filterText);
        try {
            int filterOptionDialog = JOptionPane.showConfirmDialog(null, filterOptionPanel, "Enter values", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); //displays dialog for creating new item

            if (filterOptionDialog == JOptionPane.OK_OPTION) {  //if ok button pressed
                RowFilter<CoffeeTableModel, Object> rf = null;
                //If current expression doesn't parse, don't update.
                try {
                    rf = RowFilter.regexFilter(caseNormalization(filterText), 0);
                } catch (java.util.regex.PatternSyntaxException e) {
                    return;
                }
                sorter.setRowFilter(rf);
            }
        } catch (Exception ex) {

        }
    }
}
