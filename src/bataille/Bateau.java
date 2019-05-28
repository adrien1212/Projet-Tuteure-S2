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
    
    /** Indique si le bateau est coulé */
    private boolean coule;
    
    /**
     * Construit un bateau à partir d'une coordonnée de départ et d'arrivée
     * @param coordDepart coordonné de départ du bateau
     * @param coordArrivee coordonné d'arrivé du bateau
     * @param indiceBateau indice du bateau dans la collection de flotte
     * @param zoneContenant zone qui contiendra le bateau
     */
    public Bateau(Coordonnee coordDepart, Coordonnee coordArrivee, 
                  int indiceBateau, Zone zoneContenant) {
        super(coordDepart, coordArrivee);
        this.tailleBateau = getTailleHorizontale() == 1 ? getTailleVerticale() :
                                                          getTailleHorizontale();
        this.indiceBateau = indiceBateau;
        /* ajout de la zone d'abordage à la collection de zone du bateau */
        this.getZoneContenu().add(this.creerZoneAbordage(zoneContenant));
        this.estBateau();
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

    /**
     * @param indiceBateau nouvelle valeur de indiceBateau
     */
    public void setIndiceBateau(int indiceBateau) {
        this.indiceBateau = indiceBateau;
        for (Coordonnee coordAChanger : this.getZoneCoord()) {
            coordAChanger.setIndiceBateau(indiceBateau);
        }
    }

    /**
     * @return valeur de coule
     */
    public boolean isCoule() {
        return coule;
    }
    
    /**
     * @param coule nouvelle valeur de coule
     */
    public void setCoule(boolean coule) {
        this.coule = coule;
        for (Coordonnee coordAChanger : this.getZoneCoord()) {
            coordAChanger.setBateauCoule(coule);
        }
    }



    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Taille du bateau : " + tailleBateau;
    }

    
    
    /**
     * Créer la zone d'abordage de this
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
    
    /**
     * Calcul la coordonnée d'arrivée à partir de la coordonnée de départ
     * @param coordDepart coordonnée de départ du bateau
     * @param taille taille du bateau
     * @param vertical direction du bateau
     * @return la coordonnée d'arrivé
     */
    //PUBLIC POUR TEST
    public static Coordonnee calculCoordArrivee(Coordonnee coordDepart, 
                                                 int taille, boolean vertical) {
        
        return vertical ? new Coordonnee(coordDepart.getX(), 
                                         coordDepart.getY() + taille-1) :
                          new Coordonnee(coordDepart.getX() + taille-1,
                                         coordDepart.getY());
    }
    
    /**
     * Change l'état des coordonnées du bateau this pour en faire un bateau
     */
    public void estBateau() {
        for (Coordonnee coordAChanger : this.getZoneCoord()) {
            coordAChanger.setIndiceBateau(this.getIndiceBateau());
            coordAChanger.setContientBateau(true);
        }
    }
    
    /**
     * Construit un bateau avec une taille (this) et une coordonnée de départ et 
     * une direction
     * @param coordDepart coordonnée de départ du bateau
     * @param indice indice du bateau dans la collection de flotte
     * @param vertical direction du bateau
     * @param zoneContenant zone contenant le bateau à construire
     * @return un bateau 
     */
    public Bateau constuireBateau(Coordonnee coordDepart, int indice,
                                  boolean vertical, Zone zoneContenant) {
        
        Coordonnee coordArrivee; // coordonnée d'arrivé du bateau
        coordArrivee = calculCoordArrivee(coordDepart, tailleBateau, vertical);
        
        if (vertical) {
            setTailleHorizontale(tailleBateau);
            setTailleVerticale(1);
        } else {
            setTailleHorizontale(1);
            setTailleHorizontale(tailleBateau);
        }
        
        return new Bateau(coordDepart, coordArrivee, indice, zoneContenant);
    }

    /**
     * Vérifie si on peut placer un bateau avec les paramètres donnés
     * @param coordDepart coordonnée de départ du bateau à tester (this)
     * @param vertical direction du bateau
     * @param zoneOuPlacer zone où placer le bateau
     * @return vrai si le placement est possible
     */
    public boolean placerBateau(Coordonnee coordDepart, boolean vertical, 
                                Zone zoneOuPlacer) {
        
        Bateau bateauAPlacer;    // bateau a placer sur la zone
        Coordonnee coordArrivee; // coordonnée d'arriver du bateau
        
        ArrayList<Zone> collecZone; // collection de zone de zoneOuPlacer
        Zone zoneBateau;            // zone à l'indice i de collecZone
        
        coordArrivee = calculCoordArrivee(coordDepart, tailleBateau, vertical);
        bateauAPlacer = new Bateau(coordDepart, coordArrivee, 0, zoneOuPlacer);
        
        collecZone = zoneOuPlacer.getZoneContenu();
        collecZone.add(bateauAPlacer);
        
        /* si il n'y a que cette zone dans zoneOuPlacer */
        if (collecZone.size() == 1) {
            return true;
        }
        
        /* verification si le bateau ne touche pas un autre bateau */        
        for (int i = 0; i < collecZone.size()-1; i++) {
            zoneBateau = collecZone.get(i);
            if (bateauAPlacer.getZoneContenu().get(0).collision(zoneBateau)) {
                collecZone.remove(collecZone.size()-1);
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Vérifie si le bateau (this) est coulé
     * @return vrai si le bateau est coulé
     */
    public boolean bateauCoule() {
        int indice; // indice des coordonnées dans la zoneCoord du bateau
        
        for (indice = 0; indice < this.getZoneCoord().size() 
        		&& this.getZoneCoord().get(indice).isTouche(); 
        		indice++) {
        	
        }

        if (indice == this.getZoneCoord().size()) {
        	this.setCoule(true);

        	return true;
        }

        return false;
    }
}
