import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        menu();
        /*/
        Graphe graphe = new Graphe(Graphe.GRAPHE_EX1, true);

        Graphe.creerArcEx1(graphe);
        Graphe.BFdEx1(graphe);

        graphe.creerGraphe();
        */
    }
    
    public static void menu()
    {
        System.out.println("Bienvenue dans Graphe.java");

        System.out.println();

        System.out.println("Menu");
        System.out.println("+---------------------------------------+");
        System.out.println("| " + String.format("%-37s", "R - Résoudre un graphe préenregistré") + " |");
        System.out.println("| " + String.format("%-37s", "C - Créer son graphe et le résoudre") + " |");
        System.out.println("+---------------------------------------+");

        String str = "";
        do {
            System.out.print("Votre choix [R-C] : ");
            str = sc.nextLine();
        } while (!str.equals("R") && !str.equals("C"));

        if (str.equals("R")) {
            graphePrengistre();
        }

        if (str.equals("C")) {
            creerGraphe();
        }

        sc.close();
    }
 
    public static void graphePrengistre()
    {
        System.out.println("je le fait un jour quand j'aurai un stage");

        //refaire le SOP pour
        //Graphe exo1
        //Graphe cours
        //Graphe exoBonus

        System.out.println("Menu");
        System.out.println("+-------------------------------------------+");
        System.out.println("| " + String.format("%-41s", "C  - Lancer le graphe du cours") + " |");
        System.out.println("| " + String.format("%-41s", "E1 - Lancer le graphe de l'exercice 1") + " |");
        System.out.println("| " + String.format("%-41s", "EB - Lancer le graphe de l'exercice bonus") + " |");
        System.out.println("+-------------------------------------------+");

        //gérer les cas
        String str = "";
        do {
            System.out.print("Votre choix [C-E1-EB] : ");
            str = sc.nextLine();
        } while (!str.equals("EB") && !str.equals("E1") && !str.equals("C"));

        if ( str.equals("E1"))
        {
            Graphe g = new Graphe(Graphe.GRAPHE_EX1);
            Graphe.creerArcEx1(g);
            Graphe.BFdEx1(g);
            g.creerGraphe();

        } else if ( str.equals("EB"))
        {
            Graphe g = new Graphe(Graphe.GRAPHE_EX1);
            Graphe.creerArcExBonus(g);
            Graphe.BFdExBonus(g);
            g.creerGraphe();

        }else if ( str.equals("C") )
        {
            Graphe g = new Graphe(Graphe.GRAPHE_COURS);
            Graphe.creerArcCours(g);
            Graphe.BFCours(g);
            g.creerGraphe();
        }
    }
    
    public static void creerGraphe()
    {
        System.out.println();

        System.out.print("Nombre de noeuds : ");
        
        String strNbNoeuds = sc.nextLine();
        int nbNoeuds = Integer.parseInt(strNbNoeuds);

        Graphe graphe = new Graphe(nbNoeuds);
        
        System.out.println();

        System.out.print("Noeuds disponible : [");
        for ( int cpt = 0; cpt < nbNoeuds; cpt++)
        {
            System.out.print((char) ('A' + cpt));

            if (cpt != (nbNoeuds - 1))
                System.out.print(", ");
        }
        System.out.println("]");

        System.out.println("Mode : Création d'arc (exemple de ligne : A B 5) ");

        String ligne;
        while ( !(ligne = sc.nextLine()).equals("") )
        {
            String[] tabLignes = ligne.split(" ");

            if ( !graphe.creerArc(tabLignes[0], tabLignes[1], Integer.parseInt(tabLignes[2])) )
                System.out.println("pas résussi");

            //System.out.println("Arc creé entre " + tabLignes[0] + " et " + tabLignes[1] + " avec un cout de " + tabLignes[2]);

        }

        System.out.print("Noeud source : ");
        graphe.setSource(sc.nextLine());

        System.out.println();
        System.out.println("Mode : Ordre de résolution (exemple de ligne : A B)");

        ArrayList<String> alLignesResolutions = new ArrayList<String>();
        while ( !(ligne = sc.nextLine()).equals("") )
        {
            alLignesResolutions.add(ligne);
        }

        String[][] resolution = new String[alLignesResolutions.size()][2];
        int cpt = 0;
        for (String s : alLignesResolutions)
        {
            String[] tabLignes = s.split(" ");
            resolution[cpt][0] = tabLignes[0];
            resolution[cpt][1] = tabLignes[1];
            cpt++;
        }

        if ( graphe.ressoudreBF(resolution) ) graphe.creerGraphe();
    }

}
