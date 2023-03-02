import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.swing.JFrame;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.algorithm.Toolkit ;

public class Graphe 
{
    ArrayList<Sommet> alSommet;
    SingleGraph sg;
    FrameInformations frameInfos;
    FrameCalculs frmCalculs;

    public static final int GRAPHE_COURS = 4;
    public static final int GRAPHE_EX1   = 5;

    public Graphe(int nbSommets)
    {
        this.alSommet = new ArrayList<Sommet>();

        for (int cpt = 0; cpt < nbSommets; cpt++) {
            this.alSommet.add(new Sommet((char) ('A' + cpt) + ""));
        }

        this.frameInfos = new FrameInformations(this);
        this.frmCalculs = new FrameCalculs();
        this.sg = null;
    }

    public Graphe(int nbSommets, boolean source)
    {
        this.alSommet = new ArrayList<Sommet>();

        Sommet s = new Sommet("S");
        s.setCout(0);
        this.alSommet.add(s);

        for (int cpt = 0; cpt < nbSommets - 1; cpt++) {
            this.alSommet.add(new Sommet((char) ('A' + cpt) + ""));
        }

        this.frameInfos = new FrameInformations(this);
        this.frmCalculs = new FrameCalculs();

        this.sg = null;
    }


    public void setSource(String nomSommet)
    {
        Sommet source = getSommet(nomSommet);
        source.setCout(0);
    }
    
    public Sommet getSommet(String sSommet)
    {
        for ( Sommet s : this.alSommet )
        {
            if ( s.getNom().equals(sSommet) ) return s;
        }

        return null;
    }

    public int               size      () { return this.alSommet.size(); }
    public ArrayList<Sommet> getSommets() { return this.alSommet; }

    public void iteration(int iteration) 
    {
        this.frameInfos.iteration(iteration);
        this.frmCalculs.iteration();
    }

    
    public boolean creerArc(String sommet1, String sommet2, int cout)
    {
        Sommet s1 = null;
        Sommet s2 = null;
        
        for ( Sommet s : this.alSommet )
        {
            if (s.getNom().equals(sommet1) ) s1 = s;
            if (s.getNom().equals(sommet2) ) s2 = s;
        }

        if ( s1 == null || s2 == null) return false;
        if ( s1.getVoisins().contains(s2)) return false;
       
        s1.ajouterVoisins(s2, cout);

        return true;
    }

    
    public void creerGraphe()
    {
        this.sg = new SingleGraph("Graphe");
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        System.setProperty("org.graphstream.ui", "swing");
        
        for (Sommet s : this.alSommet )
        {
            Node n = this.sg.addNode(s.getNom());
            n.setAttribute("ui.style","fill-color:red;shape : circle; size : 30px;text-mode: normal;");
            n.addAttribute("ui.label", n.getId()); 
        }

        for( Sommet s : this.alSommet)
        {
            for (Sommet voisin : s.getVoisins()) {
                Edge e = this.sg.addEdge(s.getNom() + "/" + voisin.getNom() + ":" + s.getVoisins(voisin), s.getNom(),
                        voisin.getNom(), true);
                e.addAttribute("ui.style", "size: 2px; text-background-mode : plain;");
                e.addAttribute("ui.label", e.getId());

            }
        }

        this.frameInfos.setVisible(true);
        this.frmCalculs.setVisible(true);
       

        this.sg.display();
        /* 
        Viewer vu = new Viewer(this.sg, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        ViewPanel view = vu.addDefaultView(false);
        JFrame frmGraphe = new JFrame();
        frmGraphe.add(view);
        frmGraphe.setPreferredSize(view.getPreferredSize());
        frmGraphe.setAlwaysOnTop(true);
        frmGraphe.setVisible(true);
        */
    }


    public String afficher()
    {
        String sRet = "";


        for ( Sommet s : this.alSommet )
        {
            sRet += "" + s.getNom() + " cout : " + s.getCout() + "\n"; 
        }

        return sRet;
    }
    
    
    //------------------------------------------//
    //                 Algo                     //
    //------------------------------------------//

    public void ressoudreBF(String[][] ex)
    {
        for ( int iterations = 0; iterations < this.size() - 1; iterations++)
        {
            for ( int cpt = 0; cpt < ex.length; cpt++)
            {
                Sommet s1 = this.getSommet(ex[cpt][0]);
                Sommet s2 = this.getSommet(ex[cpt][1]);

                Integer i =s1.getVoisins(s2);
                //if ( i == null ) i = ;
                
                //debug(s1, s2, i);

                String coutS1 = s1.getCout() + "";
                String coutS2 = s2.getCout() + "";

                if (iterations == 0 && s1.getCout() == Integer.MAX_VALUE)
                {
                    s2.setCout(0 + i);
                } else if (s2.getCout() > (s1.getCout() + i))
                {
                    s2.setCout(s1.getCout() + i);
                }

                String coutFinalS2 = s2.getCout() + "";

                this.frmCalculs.ajouterCalcul(s1.getNom(), s2.getNom(), coutS2, coutS1, i +"", coutFinalS2 );
            }

            if ( iterations+1 < this.size() - 1 ) this.iteration(iterations);
            //System.out.println("-----------------\nITERATION " + (iterations+1) + "\n-----------------");
            //System.out.println(this.afficher());
        }
    }
    
    
    public void ressoudreDijikstra()
    {
        Stack<Sommet> stSommets = new Stack<Sommet>();

        for (Sommet s : this.alSommet)
        {
            stSommets.add(s);
        }
        ArrayList<Sommet> alVoisins = new ArrayList<Sommet>();
        ArrayList<Sommet> alVisite  = new ArrayList<Sommet>();
        alVisite.add(this.alSommet.get(0));

        for ( int cpt = 0; cpt < this.size(); cpt++ )
        {
            for ( Sommet sommet : alVisite )
            {
                stSommets.remove(sommet);

                for (Sommet voisin : sommet.getVoisins()) {
                    int coutTrajet = sommet.getVoisins(voisin);
                    if (voisin.getCout() > sommet.getCout() + coutTrajet) {
                        voisin.setCout(sommet.getCout() + coutTrajet);
                    }

                    if (!stSommets.contains(voisin))
                    {
                        alVoisins.add(voisin);
                    }
                }
            }

            alVisite = new ArrayList<Sommet>(alVoisins);
            Collections.sort(alVisite);
            alVoisins.removeAll(alVoisins);
        }
        
        this.iteration(0);
    }
    
    //------------------------------------------//
    //           Methodes statiques             //
    //------------------------------------------//

    public static void creerArcEx1(Graphe graphe)
    {
        graphe.creerArc("A", "B", 5);
        graphe.creerArc("A", "C", 1);
        graphe.creerArc("B", "D", 3);
        graphe.creerArc("C", "B", 5);
        graphe.creerArc("C", "A", 1);
        graphe.creerArc("C", "D", 5);
        graphe.creerArc("D", "B", 3);
        graphe.creerArc("D", "E", 3);
        graphe.creerArc("E", "A", 3);
        graphe.creerArc("E", "C", 5);
    }

    public static void creerArcCours(Graphe graphe)
    {
        graphe.creerArc("A", "B", 8);
        graphe.creerArc("A", "C", 6);
        graphe.creerArc("A", "D", 2);
        graphe.creerArc("D", "B", 5);
        graphe.creerArc("D", "C", 1);
        graphe.creerArc("C", "B", 3);
    }

    public static void creerArcExBonus(Graphe graphe)
    {
        graphe.creerArc("A", "B", 4);
        graphe.creerArc("A", "D", 2);
        graphe.creerArc("B", "C", 15);
        graphe.creerArc("B", "E", 4);
        graphe.creerArc("B", "D", -6);
        graphe.creerArc("D", "E", 2);
    }


    public static void BFdEx1(Graphe graphe)
    {
        String[][] ex = {
                            {"E", "A"},
                            {"E", "C"},
                            {"A", "C"},
                            {"C", "A"},
                            {"A", "B"},
                            {"C", "B"},
                            {"C", "D"},
                            {"D", "B"},
                            {"B", "D"},
                            {"D", "E"}
                        };

        graphe.ressoudreBF(ex);
    }

    public static void BFCours(Graphe graphe)
    {
        String[][] ex = {
                            {"A", "B"},
                            {"A", "D"},
                            {"A", "C"},
                            {"C", "B"},
                            {"D", "B"},
                            {"D", "C"}
                        };
        
        graphe.getSommet("A").setCout(0);

        graphe.ressoudreBF(ex);
    }

    public static void BFdExBonus(Graphe graphe)
    {
        String[][] ex = {
                            {"A", "B"},
                            {"A", "D"},
                            {"B", "C"},
                            {"B", "E"},
                            {"B", "D"},
                            {"D", "E"}
                        };
        
        graphe.getSommet("A").setCout(0);
        
        graphe.ressoudreBF(ex);
    }
    
    private static void debug(Sommet s1, Sommet s2, Integer i)
    {
        System.out.println(s1.getNom() + " : " + s1.getCout());
        System.out.println(s2.getNom() + " : " + s2.getCout());
        System.out.println(s2.getCout() + " > " + s1.getCout() + "+" + i);
        System.out.println();
    }
}
