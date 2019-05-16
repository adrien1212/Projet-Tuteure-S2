/*
 * Flotte.java                                                  15 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * Cr�er une flotte qui est compos�e de bateaux
 * @author Groupe projet
 */
public class Flotte {
    
    /** Collection des bateaux composant la flotte  */
    private ArrayList<Bateau> collectionBateau;
    
    /** Nombre de bateau non coul� dans la flotte */
    private int bateauRestant;
    
    
    
    /**
     * Construit une flotte par d�faut
     */
    public Flotte() {
        this.bateauRestant = 0;
        this.collectionBateau = new ArrayList<Bateau>();
    }
    
    
    
    /**
     * @return valeur de collectionBateau
     */
    public ArrayList<Bateau> getCollectionBateau() {
        return collectionBateau;
    }

    /**
     * @return valeur de bateauRestant
     */
    public int getBateauRestant() {
        return bateauRestant;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Flotte [collectionBateau=" + collectionBateau 
             + ", bateauRestant=" + bateauRestant + "]";
    }



    /**
     * Ajoute � la flotte le bateau param�tre
     * @param aAjouter bateau � ajouter
     */
    public void ajouterBateau(Bateau aAjouter) {
        this.getCollectionBateau().add(aAjouter);
        this.bateauRestant++;
    }
    
    /**
     * Retire un bateau � la flotte
     */
    public void bateauCoule() {
        bateauRestant--;
    }
}
