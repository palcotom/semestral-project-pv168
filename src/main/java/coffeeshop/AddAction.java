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
    private final JTextField roastingField;

    public AddAction(JTable table, JPanel addOptionPanel, JTextField nameField, JTextField dateField, JTextField typeField,JTextField weightField, JTextField bakingField){
        this.table=table;
        this.addOptionPanel=addOptionPanel;
        this.nameField=nameField;
        this.dateField=dateField;
        this.typeField=typeField;
        this.weightField=weightField;
        this.roastingField =bakingField;
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
                try {
                    textFieldAsDate = format.parse(dateField.getText());
                } catch (Exception e) {
                    System.out.println(e);
                }
                Coffee form_coffee = new Coffee(
                        nameField.getText(),
                        textFieldAsDate,
                        typeField.getText(),
                        Integer.parseInt(weightField.getText()),
                        Roasting.fromString(roastingField.getText())
                );
                ((CoffeeTableModel) table.getModel()).addRow(form_coffee);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
