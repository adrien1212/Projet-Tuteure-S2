/*
 * Zone.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * TODO commenter les responsabilit�s de cette classe
 * @author Groupe Projet
 */
public class Zone {
    
    /** Taille maximale d'une zone */
    public static final int TAILLE_MAX = 26;
    
    /** Taille de la zone de jeu par d�faut TODO r�cup�ration auto */
    public static int tailleDefaut = 10;
    
    

    /** Coordonn�e de d�part de la zone */
    private Coordonnee coordDepart;
    
    /** Coordonn�e d'arriver de la zone */
    private Coordonnee coordArrivee;
    
    /** Taille horizontale (axe x) de la zone de jeu  */
    private int tailleHorizontale;
    
    /** Taille verticale (axe y) de la zone de jeu */
    private int tailleVerticale;
    
    /** Collection contenant les coordonn�es */
    private ArrayList<Coordonnee> zoneCoord;
    
    /** Collection contenant les zones */
    private ArrayList<Zone> zoneContenu;

    
    
    /**
     * Construit une zone par d�faut (pour l'application)
     */
    public Zone() {
        this.coordDepart = new Coordonnee(0, 0);
        this.tailleHorizontale = tailleDefaut;
        this.tailleVerticale = tailleDefaut;
        this.coordArrivee = new Coordonnee(tailleDefaut, tailleDefaut);
        this.zoneCoord = new ArrayList<Coordonnee>();
        this.zoneContenu = new ArrayList<Zone>();
    }
    
    /**
     * Construit une zone avec une coordonn�e d'arriv�e 
     * @param coordDepart coordonn�e o� commence la zone
     * @param coordArrivee coordonn�e o� la zone se fini
     */
    public Zone(Coordonnee coordDepart, Coordonnee coordArrivee) {
        this.coordDepart = coordDepart;
        this.coordArrivee = coordArrivee;
        this.tailleHorizontale = coordArrivee.getX() - coordDepart.getX() + 1;
        this.tailleVerticale = coordArrivee.getY() - coordDepart.getY() + 1;
        this.zoneCoord = new ArrayList<Coordonnee>();
        ajouterCoordonnee();
        this.zoneContenu = new ArrayList<Zone>();
    }
    
//    /**
//     * Construit une zone avec une coordonn�e de d�part 
//     * et les tailles verticale et horizontale
//     * @param coordDepart coordonn�e o� commence la zone
//     * @param tailleHorizontale taille horizontale de la zone (axe x)
//     * @param tailleVerticale taille verticale de la zone (axe y)
//     */
//    public Zone(Coordonnee coordDepart, 
//                int tailleHorizontale, int tailleVerticale) {
//        
//        this.coordDepart = coordDepart;
//        this.tailleHorizontale = tailleHorizontale;
//        this.tailleVerticale = tailleVerticale;
//        this.coordArrivee = this.calculCoordArrivee();
//    }
    
    

    /**
     * @return valeur de coordDepart
     */
    public Coordonnee getCoordDepart() {
        return coordDepart;
    }

    /**
     * @return valeur de coordArrivee
     */
    public Coordonnee getCoordArrivee() {
        return coordArrivee;
    }

    /**
     * @return valeur de tailleHorizontale
     */
    public int getTailleHorizontale() {
        return tailleHorizontale;
    }

    /**
     * @return valeur de tailleVerticale
     */
    public int getTailleVerticale() {
        return tailleVerticale;
    }
    
    /**
     * @return valeur de zoneCoord
     */
    public ArrayList<Coordonnee> getZoneCoord() {
        return zoneCoord;
    }

    /**
     * @return valeur de zoneContenu
     */
    public ArrayList<Zone> getZoneContenu() {
        return zoneContenu;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Zone [coordDepart=" + coordDepart + ", coordArrivee=" + coordArrivee + ", tailleHorizontale="
                + tailleHorizontale + ", tailleVerticale=" + tailleVerticale + ", zoneCoord=" + zoneCoord
                + ", zoneContenu=" + zoneContenu + "]";
    }
    
    

    /**
     * Affiche les cases (pr�sentations � l'�crans) de la zone
     */
    public void afficherZone() {
        
        int indiceCoords; // indice dans l'arrayList coordsInterressants
        
        StringBuilder zone = new StringBuilder(); // cha�ne contenant le tableau
        
        /* R�cup�re les coordonn�es int�ressantes c'est � dire les coordonn�es 
         * o� il y a un bateau OU o� il y a eu un tir
         */
        ArrayList<Coordonnee> collectionCoords = this.getZoneCoord();

        int numLigne; // num�ro de la ligne de la zone

        zone.append("\n   ");
        for (int indice = 0; indice < tailleHorizontale; indice++) {
            zone.append((char) ('A' + indice));
            zone.append(' ');
        }
        
        indiceCoords = 0;
        numLigne = 1;
        // afficher du tableau
        for (int i = 0; i < tailleHorizontale * tailleVerticale; i++) {

            if (i % tailleHorizontale == 0) {
                zone.append("\n");
                if (numLigne < 10) {
                    zone.append(' ');
                }
                zone.append(numLigne);
                numLigne++;
            }
            
            /* �tat de la case */
            zone.append(' ');
            if (collectionCoords.size() <= indiceCoords) {
                zone.append('.');
            } else if (i == collectionCoords.get(indiceCoords).getIndiceCoord()) {
                if (collectionCoords.get(indiceCoords).isBateauCoule()) {
                    zone.append('X');
                } else if(collectionCoords.get(indiceCoords).isContientBateau()) {
                    zone.append('=');
                } else if (collectionCoords.get(indiceCoords).isTouche() && 
                          !collectionCoords.get(indiceCoords).isBateauCoule()){
                    zone.append('x');
                } else if (collectionCoords.get(indiceCoords).isTouche()) {
                    zone.append('o');
                } else {
                    zone.append('.');
                }
                indiceCoords++;
            } else {
                zone.append('.');
            }
        }
        
        zone.append("\n");
        
        System.out.println(zone.toString());
    }
    
//    /**
//     * Calcul et construit la case d'arriv�e en fonction de la taille de la zone
//     * @return la coordonn�e d'arriv�e 
//     */
//    private Coordonnee calculCoordArrivee() {
//        int x; // abscisse de la coordonn�e d'arriv�e
//        int y; // ordonn�e de la coordonn�e d'arriv�
//        
//        x = this.coordDepart.getX() + this.tailleHorizontale;
//        y = this.coordDepart.getY() + this.tailleVerticale;
//        
//        return new Coordonnee(x, y);
//    }
    
    /**
     * Ajoute des coordonn�es dans la collection zoneCoord (de this)
     */
    private void ajouterCoordonnee() {
        int x; // abscisse de la coordonn�e
        int y; // ordonn�e de la coordonn�e
        
        y = getCoordDepart().getY();
        for (int vertical = 0; vertical < tailleVerticale; vertical++) {
            x = getCoordDepart().getX();
            for (int horizontal = 0; horizontal < tailleHorizontale; horizontal++) {
                zoneCoord.add(new Coordonnee(x, y));
                x++;
            }
            y++;
        }
    }
    
    /**
     * Ajoute les coordonn�es du bateau � la zone (this)
     * @param aAjouter bateau � ajouter � la zone (this)
     */
    private void ajouterCoordonnee(Bateau aAjouter) {
        int x; // abscisse de la coordonn�e
        int y; // ordonn�e de la coordonn�e
        
        y = aAjouter.getCoordDepart().getY();
        for (int vertical = 0; vertical < aAjouter.getTailleVerticale(); vertical++) {
            x = aAjouter.getCoordDepart().getX();
            for (int horizontal = 0; horizontal < aAjouter.getTailleHorizontale(); horizontal++) {
                zoneCoord.add(new Coordonnee(x, y, aAjouter.getIndiceBateau(), true));
                x++;
            }
            y++;
        }
    }
    
    /**
     * Ajoute une flotte sur la zone
     * @param aAjouter flotte � ajouter sur la zone (this)
     */
    public void ajouterFlotte(Flotte aAjouter) {
        for (Bateau bateau : aAjouter.getCollectionBateau()) {
            zoneContenu.add(bateau);
            ajouterCoordonnee(bateau);
        }
    }
    
    /**
     * V�rifie si une zone est en dehors de la zone contenant
     * @param zoneContenant zone contenant la zone
     * @return vrai si la zone � tester est en dehors de la zone contenant
     */
    public boolean horsZone(Zone zoneContenant) {
        Coordonnee coordZoneContenant; // stockage des coordonn�es de la zone
        
        coordZoneContenant = zoneContenant.getCoordDepart(); // coord d�part
        if (this.getCoordDepart().getX() < coordZoneContenant.getX() 
         || this.getCoordDepart().getY() < coordZoneContenant.getY()) {
            return true;
        }
        
        coordZoneContenant = zoneContenant.getCoordArrivee(); // coord arriv�e
        if (this.getCoordArrivee().getX() > coordZoneContenant.getX() 
         || this.getCoordArrivee().getY() > coordZoneContenant.getY()) {
            return true;
        }
        
        return false;
    }  
    
    /**
     * V�rifie si deux zones entrent en collision 
     * @param zoneATester zone � v�rifier
     * @return vrai si collision entre les deux zones
     */
    public boolean collision(Zone zoneATester) {
        Coordonnee caseA = this.getCoordDepart()
        Coordonnee caseB = zoneATester.getCaseDepart();
        Coordonnee tmp; // stockage pour permutation

        /* permutation */
        if (caseA.getX() > caseB.getX() && caseA.getY() > caseB.getY()) {
            tmp = caseA;
            caseA = caseB;
            caseB = tmp;
        }

        if (caseB.getX() <= (caseA.getX() + zoneA.getLongueur())
                && caseB.getY() <= (caseA.getY() + zoneA.getLargeur())) {
            return true;
        }

        return false;
    }
}
