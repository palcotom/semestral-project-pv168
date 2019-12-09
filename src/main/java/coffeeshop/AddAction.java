package coffeeshop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AddAction extends AbstractAction {
    private final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private final JTable table;
    private final JPanel addOptionPanel;
    private final JTextField nameField;
    private final JTextField dateField;
    private final JTextField typeField;
    private final JTextField weightField;
    private final JComboBox roastingBox;

    public AddAction(JTable table, JPanel addOptionPanel, JTextField nameField, JTextField dateField,
                     JTextField typeField, JTextField weightField, JComboBox roastingBox){
        this.table=table;
        this.addOptionPanel=addOptionPanel;
        this.nameField=nameField;
        this.dateField=dateField;
        this.typeField=typeField;
        this.weightField=weightField;
        this.roastingBox=roastingBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            int addOptionDialog = JOptionPane.showConfirmDialog(null, addOptionPanel,
                    "Enter values",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE); //displays dialog for creating new item

            if (addOptionDialog == JOptionPane.OK_OPTION) {  //if ok button pressed
                java.util.Date textFieldAsDate = null;  //convert date in for mof dd/MM/yyyy to java.util.Date
                //TODO offer todays date as preset
                try {
                    textFieldAsDate = format.parse(dateField.getText());
                } catch (Exception e) {
                    System.out.println(e);
                }
                /*
                aComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                // Combobox specific code
                }
                 */
                //TODO "java.lang.ClassCastException: javax.swing.JButton cannot be cast to javax.swing.JComboBox"
                JComboBox cb = (JComboBox)actionEvent.getSource();
                String selectedOption = (String)cb.getSelectedItem();
                Coffee form_coffee = new Coffee(
                        nameField.getText(),
                        textFieldAsDate,
                        typeField.getText(),
                        Integer.parseInt(weightField.getText()),
                        Roasting.fromString(selectedOption)
                );
                ((CoffeeTableModel) table.getModel()).addRow(form_coffee);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
