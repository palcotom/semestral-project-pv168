import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;

public class CoffeeTableModel extends AbstractTableModel {

    private final List<Coffee> coffees;

    public CoffeeTableModel(List<Coffee> coffees){
        this.coffees = coffees;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        return String.class;
    }
}