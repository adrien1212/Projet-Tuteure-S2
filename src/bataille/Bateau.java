/*
 * Bateau.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * TODO commenter les responsabilit�s de cette classe
 * @author Groupe projet
 *
 */
public class Bateau extends Zone {

    /** Taille du bateau (�quivaut � tailleHorizontale ou tailleVerticale) */
    private int tailleBateau;
    
    /** Indice du bateau dans la collection de la flotte */
    private int indiceBateau;
    
    /** Indique si le bateau est coul� */
    private boolean coule;
    
    /**
     * Construit un bateau � partir d'une coordonn�e de d�part et d'arriv�e
     * @param coordDepart coordonn� de d�part du bateau
     * @param coordArrivee coordonn� d'arriv� du bateau
     * @param indiceBateau indice du bateau dans la collection de flotte
     * @param zoneContenant zone qui contiendra le bateau
     */
    public Bateau(Coordonnee coordDepart, Coordonnee coordArrivee, 
                  int indiceBateau, Zone zoneContenant) {
        super(coordDepart, coordArrivee);
        this.tailleBateau = getTailleHorizontale() == 1 ? getTailleVerticale() :
                                                          getTailleHorizontale();
        this.indiceBateau = indiceBateau;
        /* ajout de la zone d'abordage � la collection de zone du bateau */
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
     * Cr�er la zone d'abordage de this
     * @param zoneContenant zone qui contient la zone d'abordage (zone jeu)
     * @return la zone d'abordage du bateau
     */
    public Zone creerZoneAbordage(Zone zoneContenant) {
        Coordonnee coordDepartZone;  // coordonn�e de d�part de la zone 
        Coordonnee coordArriveeZone; // coordonn�e d'arriv�e de la zone

        int x, // abscisse de la coordonn�e de la nouvelle zone
            y; // ordonn�e de la coordonn�e de la nouvelle zone
    
        /* Calcul de la coordonn�e de d�part */
        x = this.getCoordDepart().getX();
        if (x != 0) {
            x--;
        }
        y = this.getCoordDepart().getY();
        if (y != 0) {
            y--;
        }
        
        coordDepartZone = new Coordonnee(x, y);
        
        /* Calcul de la coordonn�e d'arriv�e */
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
     * Calcul la coordonn�e d'arriv�e � partir de la coordonn�e de d�part
     * @param coordDepart coordonn�e de d�part du bateau
     * @param taille taille du bateau
     * @param vertical direction du bateau
     * @return la coordonn�e d'arriv�
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
     * Change l'�tat des coordonn�es du bateau this pour en faire un bateau
     */
    public void estBateau() {
        for (Coordonnee coordAChanger : this.getZoneCoord()) {
            coordAChanger.setIndiceBateau(this.getIndiceBateau());
            coordAChanger.setContientBateau(true);
        }
    }
    
    /**
     * Construit un bateau avec une taille (this) et une coordonn�e de d�part et 
     * une direction
     * @param coordDepart coordonn�e de d�part du bateau
     * @param indice indice du bateau dans la collection de flotte
     * @param vertical direction du bateau
     * @param zoneContenant zone contenant le bateau � construire
     * @return un bateau 
     */
    public Bateau constuireBateau(Coordonnee coordDepart, int indice,
                                  boolean vertical, Zone zoneContenant) {
        
        Coordonnee coordArrivee; // coordonn�e d'arriv� du bateau
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
     * V�rifie si on peut placer un bateau avec les param�tres donn�s
     * @param coordDepart coordonn�e de d�part du bateau � tester (this)
     * @param vertical direction du bateau
     * @param zoneOuPlacer zone o� placer le bateau
     * @return vrai si le placement est possible
     */
    public boolean placerBateau(Coordonnee coordDepart, boolean vertical, 
                                Zone zoneOuPlacer) {
        
        Bateau bateauAPlacer;    // bateau a placer sur la zone
        Coordonnee coordArrivee; // coordonn�e d'arriver du bateau
        
        ArrayList<Zone> collecZone; // collection de zone de zoneOuPlacer
        Zone zoneBateau;            // zone � l'indice i de collecZone
        
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
     * V�rifie si le bateau (this) est coul�
     * @return vrai si le bateau est coul�
     */
    public boolean bateauCoule() {
        int indice; // indice des coordonn�es dans la zoneCoord du bateau
        
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
