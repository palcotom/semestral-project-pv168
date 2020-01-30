package coffeeshop.actions;

import coffeeshop.Coffee;
import coffeeshop.CoffeeTableModel;
import coffeeshop.Main;
import coffeeshop.Roasting;
import coffeeshop.views.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AddAction extends AbstractAction {
    final static Logger log = LoggerFactory.getLogger(Main.class);
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
        this.weightField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateJOptionPane(e);
            }
        });
        this.roastingBox=roastingBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            int addOptionDialog = JOptionPane.showConfirmDialog(null, addOptionPanel,
                    "Enter values",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE); //displays dialog for creating new item
            if (addOptionDialog == JOptionPane.OK_OPTION ) {  //if ok button pressed
                java.util.Date textFieldAsDate = null;  //convert date in for mof dd/MM/yyyy to java.util.Date
                try {
                    textFieldAsDate = format.parse(dateField.getText());
                } catch (Exception e) {
                    log.error("Failed to parse date", e);
                }
                java.sql.Date date = new java.sql.Date(textFieldAsDate.getTime()); //convert java.util.date to java.sql.date
                Roasting selectedOption = (Roasting) roastingBox.getSelectedItem();
                Coffee form_coffee = new Coffee(
                        nameField.getText(),
                        date,
                        typeField.getText(),
                        Integer.parseInt(weightField.getText()),
                        selectedOption
                );
                MainWindow.coffeeManager.addCoffee(form_coffee);
                ((CoffeeTableModel) table.getModel()).addRow(form_coffee);
                clearDialog();
            }
        } catch (Exception ex) {
           log.error("Add option dialog failed", ex);
        }
    }

    public boolean validateJOptionPane(KeyEvent key){
                if (Character.isDigit(key.getKeyChar())){
                    return true;
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter only numbers in Weight");
                    this.weightField.setText("");
                    return false;
                }
    }

    private void clearDialog(){
        this.nameField.setText("");
        this.typeField.setText("");
        this.weightField.setText("");
    }
}
