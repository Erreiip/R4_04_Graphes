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
        this.setSize(new Dimension(600,400));
        Dimension dim = this.getToolkit().getScreenSize();
        int y = (int) ((dim.getHeight()/2));
        this.setLocation(0,y);

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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setVisible(false);
    }

    public void iteration(int numeroIteration)
    {
        for ( Sommet s : graphe.getSommets())
        {
            JLabel lblTemp = this.hsmSommetLbl.get(s).get(numeroIteration);

            if ( numeroIteration != 0 )
            {
                String cout = this.hsmSommetLbl.get(s).get(numeroIteration-1).getText();
                if ( cout.equals( "+∞")) cout = Integer.MAX_VALUE + "";
                int i = Integer.parseInt(cout);
                if ( i != s.getCout() ) lblTemp.setForeground(Color.BLUE);
            }

            String cout = s.getCout() + "";
            if ( s.getCout() == Integer.MAX_VALUE) cout = "+∞";
            lblTemp.setText(cout);
        }
    }
}
