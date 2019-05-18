/*
 * Bateau.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * TODO commenter les responsabilités de cette classe
 * @author Groupe projet
 *
 */
public class Bateau extends Zone {

    /** Taille du bateau (équivaut à tailleHorizontale ou tailleVerticale) */
    private int tailleBateau;
    
    /** Indice du bateau dans la collection de la flotte */
    private int indiceBateau;
    
    /**
     * Construit un bateau à partir d'une coordonnée de départ et d'arrivée
     * @param coordDepart coordonné de départ du bateau
     * @param coordArrivee coordonné d'arrivé du bateau
     * @param indiceBateau indice du bateau dans la collection de flotte
     * @param zoneContenant zone qui contiendra le bateau
     */
    public Bateau(Coordonnee coordDepart, Coordonnee coordArrivee, int indiceBateau,
                  Zone zoneContenant) {
        super(coordDepart, coordArrivee);
        this.tailleBateau = getTailleHorizontale() == 1 ? getTailleVerticale() :
                                                          getTailleHorizontale();
        this.indiceBateau = indiceBateau;
        /* ajout de la zone d'abordage à la zone du bateau */
        this.getZoneContenu().add(this.creerZoneAbordage(zoneContenant));
    }
   
    

    /**
     * Construit un bateau pour le stockage de la taille
     * @param tailleBateau
     */
    public Bateau(int tailleBateau) {
        this.tailleBateau = tailleBateau;
    }



    /**
     * @return valeur de tailleBateau
     */
    public int getTailleBateau() {
        return tailleBateau;
    }

    /**
     * @return valeur de indiceBateau
     */
    public int getIndiceBateau() {
        return indiceBateau;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Bateau [tailleBateau = " + tailleBateau
             + ", zoneBateau = " + getZoneCoord()
             + ", zoneAbordage = " + getZoneContenu() + "]";
    }

    
    
    /**
     * Créer la zone d'abordage
     * @param zoneContenant zone qui contient la zone d'abordage (zone jeu)
     * @return la zone d'abordage du bateau
     */
    public Zone creerZoneAbordage(Zone zoneContenant) {
        Coordonnee coordDepartZone;  // coordonnée de départ de la zone 
        Coordonnee coordArriveeZone; // coordonnée d'arrivée de la zone

        int x, // abscisse de la coordonnée de la nouvelle zone
            y; // ordonnée de la coordonnée de la nouvelle zone
    
        /* Calcul de la coordonnée de départ */
        x = this.getCoordDepart().getX();
        if (x != 0) {
            x--;
        }
        y = this.getCoordDepart().getY();
        if (y != 0) {
            y--;
        }
        
        coordDepartZone = new Coordonnee(x, y);
        
        /* Calcul de la coordonnée d'arrivée */
        x = this.getCoordArrivee().getX();
        if (x != zoneContenant.getTailleHorizontale()-1) {
            x++;
        }
        y = this.getCoordArrivee().getY();
        if (y != zoneContenant.getTailleVerticale()-1) {
            y++;
        }
    
        coordArriveeZone = new Coordonnee(x, y);
        
        return new Zone(coordDepartZone, coordArriveeZone);
    }
}
