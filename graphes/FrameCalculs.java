import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class FrameCalculs extends JFrame
{
    
    private GrilleResultatModel model;
    private JPanel panelCalculs;
    private JTable tblCalculs;
    
    public FrameCalculs()
    {
        this.setSize(new Dimension(600,400));
        Dimension dim = this.getToolkit().getScreenSize();
        int y = (int) ((dim.getHeight()/2)-200);
        this.setLocation(0,y-300);

        this.model = new GrilleResultatModel();
        this.panelCalculs = new JPanel(new BorderLayout());
        this.tblCalculs = new JTable(this.model);
        JScrollPane sclPane = new JScrollPane(this.tblCalculs);

        this.panelCalculs.add(sclPane, BorderLayout.CENTER);
        this.add(this.panelCalculs);
                
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setVisible(false);
    }

    public JComponent getComponent()
    {
        return this.panelCalculs;
    }

    public void ajouterCalcul(String nomS1, String nomS2, String coutS2, String coutS1, String coutTrajet, String coutFinalS2 )
    {
        String[] tabCalculs = new String[4];

        if (Integer.parseInt(coutS2) >= Integer.MAX_VALUE)
            coutS2 = "+∞";

        if (Integer.parseInt(coutS1) >= Integer.MAX_VALUE)
            coutS1 = "+∞";

        if ( Integer.parseInt(coutFinalS2) >= Integer.MAX_VALUE )
            coutFinalS2 = "+∞";

        tabCalculs[0] = "(" + nomS1 + "," + nomS2 + ")";
        tabCalculs[1] = "d(" + nomS2 + ")" + " > " + "d(" + nomS1 + ")" + " + " + "w" + tabCalculs[0];
        tabCalculs[2] = coutS2 + " > " + coutS1 + " + " + coutTrajet;
        tabCalculs[3] = "d(" + nomS2 + ")" + " = " + coutFinalS2;

        this.model.ajouterCalcul(tabCalculs);
    }

    public void iteration()
    {
        String[] tab = { "", "", "", "" };
        this.model.ajouterCalcul(tab);
    }

}
