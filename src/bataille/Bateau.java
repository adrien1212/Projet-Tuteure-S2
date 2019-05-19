/*
 * Bateau.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

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
        /* ajout de la zone d'abordage � la zone du bateau */
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
     * Cr�er la zone d'abordage
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
     * TODO commenter le r�le de cette m�thode
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
     * @param indice indice du bateau dans la collection de flotte
     * @param vertical direction du bateau
     * @param zoneOuPlacer zone o� placer le bateau
     * @return vrai si le placement est possible
     */
    public boolean placerBateau(Coordonnee coordDepart, int indice, 
                                boolean vertical, Zone zoneOuPlacer) {
        
        Zone bateauAPlacer;
        Coordonnee coordArrivee;
        
        /* v�rification si le bateau n'est pas hors zone */
        if (Zone.tailleDefaut <= tailleBateau + coordDepart.getX() ||
            Zone.tailleDefaut <= tailleBateau + coordDepart.getY()) {
            return false;
        }
        
        coordArrivee = calculCoordArrivee(coordDepart, tailleBateau, vertical);
        bateauAPlacer = new Zone(coordDepart, coordArrivee);
        
        /* verification si le bateau ne touche pas un autre bateau */
        for (Zone zoneBateau : zoneOuPlacer.getZoneContenu()) {
            if (bateauAPlacer.collision(zoneBateau)) {
                return false;
            }
        }
        
        return true;
    }
}
