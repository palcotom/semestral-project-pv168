package coffeeshop;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class CoffeeTableModel extends AbstractTableModel {

    private enum Column {

        NAME("Name", String.class, Coffee::getName),
        DATE("Date",Date.class, Coffee::getDate),
        TYPE("Type", String.class, Coffee::getType),
        WEIGHT("Weight", Number.class, Coffee::getWeight),
        ROASTING("Roasting", Roasting.class, Coffee::getRoasting);

        private <T> Column(String name, Class<T> columnClass, Function<Coffee, T> extractor) {
            this.name = name;
            this.columnClass = columnClass;
            this.extractor = extractor;
        }

        private final String name;
        private final Class<?> columnClass;
        private final Function<Coffee,?> extractor;

    }

    private final List<Coffee> coffees;

    public CoffeeTableModel(List<Coffee> coffees){
        this.coffees = coffees;
    }

    @Override
    public int getRowCount() {
        return coffees.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Coffee coffee = coffees.get(rowIndex);
        return Column.values()[columnIndex].extractor.apply(coffee);
    }

    @Override
    public String getColumnName(int columnIndex) {

        return Column.values()[columnIndex].name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){

        return Column.values()[columnIndex].columnClass;
    }

    public void deleteRow(int index){
        coffees.remove(index);
        fireTableRowsDeleted(index,index);
    }

    public void addRow(Coffee coffee){
        coffees.add(coffee);
        fireTableRowsInserted(getRowCount()-1,getRowCount()-1);
    }
}