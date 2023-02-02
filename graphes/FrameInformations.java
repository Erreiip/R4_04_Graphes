import java.awt.Dimension;
import java.awt.GridLayout;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameInformations extends JFrame
{
    Graphe graphe;
    JPanel panelInfos;

    public FrameInformations(Graphe g)
    {
        this.setSize(new Dimension(300,400));
        this.setLocation(0,0);

        this.graphe = g;
        this.panelInfos = new JPanel();
        this.panelInfos.setLayout(new GridLayout(g.getSommets().size(), g.getSommets().size() - 1));
        for ( Sommet s :this.graphe.getSommets())
        {
            new JLabel(s.getNom() + " : " + s.getCout() );
        }


        this.setVisible(true);
    }
}
