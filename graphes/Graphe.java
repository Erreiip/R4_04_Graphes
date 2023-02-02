import java.util.ArrayList;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.algorithm.Toolkit ;

public class Graphe 
{
    ArrayList<Sommet> alSommet;

    public Graphe(int nbSommets)
    {
        this.alSommet = new ArrayList<Sommet>();

        for ( int cpt = 0; cpt < nbSommets; cpt++)
        {
            this.alSommet.add( new Sommet((char)('A'+cpt) + ""));
        }
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
        SingleGraph sg = new SingleGraph("Graphe");
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        System.setProperty("org.graphstream.ui", "swing");
        
        for (Sommet s : this.alSommet )
        {
            Node n = sg.addNode(s.getNom());
            n.setAttribute("ui.style","fill-color:red;shape : circle; size : 30px;text-mode: normal;");
            n.addAttribute("ui.label", n.getId()); 
        }

        for( Sommet s : this.alSommet)
        {
            for ( Sommet voisin : s.getVoisins())
            {
                Edge e = sg.addEdge(s.getNom() + "/" + voisin.getNom() + ":" + s.getVoisins(voisin), s.getNom(), voisin.getNom(), true);
                e.addAttribute("ui.style", "size: 2px; text-background-mode : plain;");
                e.addAttribute("ui.label", e.getId()); 
                
            }
        }

        sg.display();
        new FrameInformations(this);
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

    public static void main(String[] args)
    {
        Graphe graphe = new Graphe(5);
        
        Graphe.creerArcEx1(graphe);
        Graphe.BellmanFordEx1(graphe);

        graphe.creerGraphe();
    }

    public static void BellmanFordEx1(Graphe graphe)
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

        for ( int iterations = 0; iterations < graphe.size() - 1; iterations++)
        {
            for ( int cpt = 0; cpt < ex.length; cpt++)
            {
                Sommet s1 = graphe.getSommet(ex[cpt][0]);
                Sommet s2 = graphe.getSommet(ex[cpt][1]);

                Integer i = s1.getVoisinsCourt(s2);
                if ( i == null ) i = s1.getVoisins(s2);
                
                //debug(s1, s2, i);

                if (iterations == 0 && s1.getCout() > 100)
                {
                    s2.setCout(0 + i);
                } else if (s2.getCout() > (s1.getCout() + i))
                {
                    s2.setCout(s1.getCout() + i);
                }
                
                s1.ajouterVoisinsCourt(s2, s2.getCout() + i);
            }

            System.out.println("-----------------\nITERATION " + (iterations+1) + "\n-----------------");
            System.out.println(graphe.afficher());
        }
    }
    
    private static void debug(Sommet s1, Sommet s2, int i)
    {
        System.out.println(s1.getNom() + " : " + s1.getCout());
        System.out.println(s2.getNom() + " : " + s2.getCout());
        System.out.println(s2.getCout() + " > " + s1.getCout() + "+" + i);
        System.out.println();
    }
}
