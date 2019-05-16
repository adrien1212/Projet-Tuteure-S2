/*
 * Flotte.java                                                  15 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * Créer une flotte qui est composée de bateaux
 * @author Groupe projet
 */
public class Flotte {
    
    /** Collection des bateaux composant la flotte  */
    private ArrayList<Bateau> collectionBateau;
    
    /** Nombre de bateau non coulé dans la flotte */
    private int bateauRestant;
    
    
    
    /**
     * Construit une flotte par défaut
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
     * Ajoute à la flotte le bateau paramètre
     * @param aAjouter bateau à ajouter
     */
    public void ajouterBateau(Bateau aAjouter) {
        this.getCollectionBateau().add(aAjouter);
        this.bateauRestant++;
    }
    
    /**
     * Retire un bateau à la flotte
     */
    public void bateauCoule() {
        bateauRestant--;
    }
}
