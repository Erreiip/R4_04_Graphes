public class Main 
{
    public static void main(String[] args)
    {
        Graphe graphe = new Graphe(Graphe.GRAPHE_EX1);
        
        Graphe.creerArcExBonus(graphe);
        Graphe.BFdExBonus(graphe);

        graphe.creerGraphe();
    }
}
