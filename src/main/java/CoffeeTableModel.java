import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CoffeeTableModel extends AbstractTableModel {

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
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Coffee result;
        if (rowIndex > this.getRowCount() || columnIndex > this.getColumnCount()){
            return null;
        }
        result = coffees.get(rowIndex);
        if (columnIndex == 0){
            return result.getName();
        }else if(columnIndex == 1){
            return result.getDate();
        }else if(columnIndex == 2){
            return result.getType();
        }else if(columnIndex == 3){
            return result.getWeight();
        }else if(columnIndex == 4){
            return result.getOrigin();
        }else if(columnIndex == 5){
            return result.getRoasting();
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex){
            case 0:
                return "Name";
            case 1:
                return "Date";
            case 2:
                return "Type";
            case 3:
                return "Weight";
            case 4:
                return "Origin";
            case 5:
                return "Roasting";
            default :
                throw new IndexOutOfBoundsException("Invalid index"+columnIndex);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){

        switch(columnIndex){
            case 0:
                return String.class;
            case 1:
                return Date.class;
            case 2:
                return String.class;
            case 3:
                return Number.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            default :
                throw new IndexOutOfBoundsException("Invalid index"+columnIndex);
        }
    }

    public void deleteRow(int index){
        coffees.remove(index);
        fireTableRowsDeleted(index,index);
    }
}