public class Main 
{
    public static void main(String[] args)
    {
        Graphe graphe = new Graphe(Graphe.GRAPHE_EX1, true);
        
        Graphe.creerArcEx1(graphe);
        //Graphe.BFdEx1(graphe);
        graphe.ressoudreDijikstra();

        graphe.creerGraphe();
    }
}
