import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TransportTable extends AbstractTableModel {
    private static final ArrayList<Transport> allTransport = new ArrayList<>();
    public void changeTable() {
        allTransport.clear();
        allTransport.addAll(Transport_company.getCars());
        allTransport.addAll(Transport_company.getTrains());
        allTransport.addAll(Transport_company.getExpresses());
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return allTransport.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "№";
            case 1:
                return "Тип";
            case 2:
                return "Имя";
            case 3:
                return "Состояние";
            default:return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return allTransport.get(rowIndex).getType();
            case 2:
                return allTransport.get(rowIndex).getName();
            case 3:
                return allTransport.get(rowIndex).getState();
            default:
                return "-";
        }
    }

    public void addTransport(int object, String name) {
        Transport_company.add_transport(object, name);
        this.changeTable();
    }

    public void removeTransport(String name) {
        Transport_company.remove_transport(name);
        this.changeTable();
    }

    public static void filterTransport() {

    }
}

