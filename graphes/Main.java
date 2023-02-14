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
        System.out.println("| " + String.format("%-37s", "R-Résoudre un graphe préenregistré") + " |");
        System.out.println("| " + String.format("%-37s", "C-Créer son graphe et le résoudre") + " |");
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
    }
    
    public static void creerGraphe()
    {
        System.out.print("Nombre de noeuds : ");
        
        String strNbNoeuds = sc.nextLine();
        int nbNoeuds = Integer.parseInt(strNbNoeuds);

        Graphe graphe = new Graphe(nbNoeuds);
        
        System.out.println("Mode : Création de matrice ");

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
        System.out.println("Mode : Ordre de résolution");

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

        graphe.ressoudreBF(resolution);
        
        graphe.creerGraphe();
    }

}
