import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class GrilleResultatModel extends AbstractTableModel{
    private String[] enTete;
    private ArrayList<String[]> alResult;
    
    public GrilleResultatModel(){
        this.enTete = new String[]{"","","",""};
        this.alResult = new ArrayList<String[]>();
    }

    public int getColumnCount(){return this.enTete.length;}
    public int getRowCount   (){return this.alResult.size();}
    public String getColumnName(int col) {return "";}

    @Override
    public Object getValueAt(int row, int col){
        switch(col){
            case 0 : return this.alResult.get(row)[0];
            case 1 : return this.alResult.get(row)[1];
            case 2 : return this.alResult.get(row)[2];
            case 3 : return this.alResult.get(row)[3];
            default : return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        return getValueAt(0,columnIndex).getClass();
    }

    public void majTable(ArrayList<String[]> lstString){
        this.alResult = lstString;
        this.fireTableDataChanged();
    }

    public void ajouterCalcul(String[] tabResult){
        this.alResult.add(tabResult);
        this.fireTableDataChanged();
    }

    public void zizi(){
        this.alResult.clear();
        this.fireTableDataChanged();
    }
}
