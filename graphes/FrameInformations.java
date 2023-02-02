import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.graphstream.algorithm.Toolkit;

public class FrameInformations extends JFrame
{
    Graphe graphe;
    JPanel panelInfos;
    HashMap<Sommet, ArrayList<JLabel>> hsmSommetLbl;

    public FrameInformations(Graphe g)
    {
        this.setSize(new Dimension(300,400));
        Dimension dim = this.getToolkit().getScreenSize();
        int y = (int) ((dim.getHeight()/2) - 100);
        this.setLocation(200,y);

        this.graphe = g;
        int nbIterations = g.getSommets().size();

        this.panelInfos = new JPanel();
        this.panelInfos.setLayout(new GridLayout(nbIterations, nbIterations - 1 ));

        this.hsmSommetLbl = new HashMap<Sommet, ArrayList<JLabel>>();
        for ( Sommet s :this.graphe.getSommets())
        {
            ArrayList<JLabel> alLbl = new ArrayList<JLabel>();

            JLabel lblTemp = new JLabel(s.getNom(), JLabel.CENTER);
            lblTemp.setForeground(Color.RED);
            this.panelInfos.add(lblTemp);

            for ( int cpt = 0; cpt < nbIterations - 1; cpt++)
            {
                lblTemp = new JLabel("", JLabel.CENTER);
                alLbl.add(lblTemp);
                this.panelInfos.add(lblTemp);
            }

            this.hsmSommetLbl.put(s, alLbl);
        }

        this.add(this.panelInfos);

        this.setVisible(true);
    }

    public void iteration(int numeroIteration)
    {
        for ( Sommet s : graphe.getSommets())
        {
            JLabel lblTemp = this.hsmSommetLbl.get(s).get(numeroIteration);

            if ( numeroIteration != 0 )
            {
                int i = Integer.parseInt(this.hsmSommetLbl.get(s).get(numeroIteration-1).getText());
                if ( i != s.getCout() ) lblTemp.setForeground(Color.BLUE);
            }

            lblTemp.setText(s.getCout() + "");
        }
    }
}
