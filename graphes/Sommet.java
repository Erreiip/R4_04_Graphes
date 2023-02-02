import java.util.HashMap;
import java.util.Set;


public class Sommet
{
    public String nom;
    public int cout;
    public HashMap<Sommet, Integer> voisins;
    public HashMap<Sommet, Integer> voisinsLePlusCourt;


    public Sommet(String nom)
    {
        this.nom = nom;
        this.cout = Integer.MAX_VALUE;
        this.voisins = new HashMap<Sommet, Integer>();
        this.voisinsLePlusCourt = new HashMap<Sommet, Integer>();
    }

    public void ajouterVoisins(Sommet s, int cout)
    {
        this.voisins.put(s, cout);
    }

    public void ajouterVoisinsCourt(Sommet s, int cout)
    {
        if (this.voisinsLePlusCourt.containsKey(s) )
        {
            if ( this.voisinsLePlusCourt.get(s) > cout )
            {
                this.voisinsLePlusCourt.replace(s, cout);
            }
            return;
        }
        
        this.voisinsLePlusCourt.put(s, cout);
    }

    public Integer getVoisins(Sommet s)
    {
        return this.voisins.get(s);
    }

    public Integer getVoisinsCourt(Sommet s)
    {
        return this.voisinsLePlusCourt.get(s);
    }

    public String getNom() { return this.nom; }
    public int getCout  () { return this.cout; }


    public Set<Sommet> getVoisins() { return this.voisins.keySet(); }
    public Set<Sommet> getVoisinsCourt() { return this.voisinsLePlusCourt.keySet(); }

    public void setCout(int i) { this.cout = i; }
}