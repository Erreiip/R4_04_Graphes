import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

public class FrameCalculs extends JFrame
{
    
    private GrilleResultatModel model;
    private JPanel panelCalculs;
    private JTable tblCalculs;
    
    public FrameCalculs()
    {
        this.setSize(new Dimension(300,400));
        Dimension dim = this.getToolkit().getScreenSize();
        int y = (int) ((dim.getHeight()/2) - 100);
        this.setLocation(200,y-300);

        this.model = new GrilleResultatModel();
        this.panelCalculs = new JPanel(new BorderLayout());
        this.tblCalculs   = new JTable(this.model);

        this.panelCalculs.add(this.tblCalculs, BorderLayout.CENTER);
        this.add(this.panelCalculs);
                
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JComponent getComponent()
    {
        return this.panelCalculs;
    }

    public void ajouterCalcul(String[] tabCalculs)
    {
        this.model.ajouterCalcul(tabCalculs);
    }

    public static void main(String[] args)
    {
        
        FrameCalculs frm = new FrameCalculs();

        String[] ligne1 = new String[]{"(a,b)","d(b) > d(a) + w(a,b)","+∞ > 0 + 7","d(b) = 7"};
        String[] ligne2 = new String[]{"(b,c)","d(c) > d(b) + w(b,c)","+∞ > 0 + 8","d(c) = 8"};

        frm.ajouterCalcul(ligne1);
        frm.ajouterCalcul(ligne2);
    }

}
