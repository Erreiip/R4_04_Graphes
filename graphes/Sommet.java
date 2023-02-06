import java.util.HashMap;
import java.util.Set;


public class Sommet implements Comparable<Sommet>
{
    public String nom;
    public int cout;
    public HashMap<Sommet, Integer> voisins;


    public Sommet(String nom)
    {
        this.nom = nom;
        this.cout = Integer.MAX_VALUE;
        this.voisins = new HashMap<Sommet, Integer>();
    }

    public void ajouterVoisins(Sommet s, int cout)
    {
        this.voisins.put(s, cout);
    }

    public Integer getVoisins(Sommet s)
    {
        return this.voisins.get(s);
    }

    public String getNom() { return this.nom; }
    public int getCout  () { return this.cout; }


    public Set<Sommet> getVoisins() { return this.voisins.keySet(); }

    public void setCout(int i) { this.cout = i; }

    @Override
    public int compareTo(Sommet o) {
        return this.cout - o.cout;
    }
}