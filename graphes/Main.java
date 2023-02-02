public class Main 
{
    public static void main(String[] args)
    {
        Graphe graphe = new Graphe(Graphe.GRAPHE_COURS);
        
        Graphe.creerArcCours(graphe);
        Graphe.BFCours(graphe);

        graphe.creerGraphe();
    }
}
