import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameInformations extends JFrame
{
    Graphe graphe;
    JPanel panelInfos;
    HashMap<Sommet, ArrayList<JLabel>> hsmSommetLbl;

    public FrameInformations(Graphe g)
    {
        this.setSize(new Dimension(300,400));
        this.setLocation(0,0);

        this.graphe = g;
        int nbIterations = g.getSommets().size();

        this.panelInfos = new JPanel();
        this.panelInfos.setLayout(new GridLayout(nbIterations, nbIterations - 1 ));

        this.hsmSommetLbl = new HashMap<Sommet, ArrayList<JLabel>>();
        for ( Sommet s :this.graphe.getSommets())
        {
            ArrayList<JLabel> alLbl = new ArrayList<JLabel>();
            for ( int cpt = 0; cpt < nbIterations - 1; cpt++)
            {
                JLabel lblTemp = new JLabel("shesh");
                alLbl.add(lblTemp);
                this.panelInfos.add(lblTemp);
            }

            this.hsmSommetLbl.put(s, alLbl);
        }

        this.add(this.panelInfos);

        this.setVisible(true);
    }
}
