import java.util.ArrayList;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.algorithm.Toolkit ;

public class Graphe 
{
    ArrayList<Sommet> alSommet;
    SingleGraph sg;
    FrameInformations frameInfos;

    public static final int GRAPHE_COURS = 4;
    public static final int GRAPHE_EX1   = 5;

    public Graphe(int nbSommets)
    {
        this.alSommet = new ArrayList<Sommet>();

        for ( int cpt = 0; cpt < nbSommets; cpt++)
        {
            this.alSommet.add( new Sommet((char)('A'+cpt) + ""));
        }

        this.frameInfos = new FrameInformations(this);
        this.sg = null;
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

    public void iteration(int iteration) { this.frameInfos.iteration(iteration);}

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
            for ( Sommet voisin : s.getVoisins())
            {
                Edge e = this.sg.addEdge(s.getNom() + "/" + voisin.getNom() + ":" + s.getVoisins(voisin), s.getNom(), voisin.getNom(), true);
                e.addAttribute("ui.style", "size: 2px; text-background-mode : plain;");
                e.addAttribute("ui.label", e.getId()); 
                
            }
        }

        this.sg.display();
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

        Graphe.ressoudreBF(graphe, ex);
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

        Graphe.ressoudreBF(graphe, ex);
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
        
        Graphe.ressoudreBF(graphe, ex);
    }

    

    public static void ressoudreBF(Graphe graphe, String[][] ex)
    {
        for ( int iterations = 0; iterations < graphe.size() - 1; iterations++)
        {
            for ( int cpt = 0; cpt < ex.length; cpt++)
            {
                Sommet s1 = graphe.getSommet(ex[cpt][0]);
                Sommet s2 = graphe.getSommet(ex[cpt][1]);

                Integer i =null;// s1.getVoisinsCourt(s2);
                if ( i == null ) i = s1.getVoisins(s2);
                
                //debug(s1, s2, i);

                if (iterations == 0 && s1.getCout() == Integer.MAX_VALUE)
                {
                    s2.setCout(0 + i);
                } else if (s2.getCout() > (s1.getCout() + i))
                {
                    s2.setCout(s1.getCout() + i);
                }
                
                s1.ajouterVoisinsCourt(s2, s2.getCout() + i);
            }

            graphe.iteration(iterations);
            //System.out.println("-----------------\nITERATION " + (iterations+1) + "\n-----------------");
            //System.out.println(graphe.afficher());
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
