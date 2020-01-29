package coffeeshop.views;

import coffeeshop.Roasting;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddWindow extends JPanel {
    private final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private final JTextField nameField = new JTextField(5);
    private final JTextField dateField = new JTextField(5);
    private final JTextField typeField = new JTextField(5);
    private final JTextField weightField = new JTextField(5);
    private final JComboBox roastingBox = new JComboBox<>(Roasting.values());

    public AddWindow(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(new JLabel("Name:"));
        this.add(nameField);
        this.add(new JLabel("Date: dd/MM/yyyy"));
        Date date = new Date(System.currentTimeMillis());
        this.dateField.setText(format.format(date));
        this.add(dateField);
        this.add(new JLabel("Type:"));
        this.add(typeField);
        this.add(new JLabel("Weight:"));
        this.add(weightField);
        this.add(new JLabel("Roasting:"));
        this.add(roastingBox);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getDateField() {
        return dateField;
    }

    public JTextField getTypeField() {
        return typeField;
    }

    public JTextField getWeightField() {
        return weightField;
    }

    public JComboBox getRoastingBox() {
        return roastingBox;
    }
}
