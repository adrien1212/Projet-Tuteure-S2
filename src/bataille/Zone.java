/*
 * Zone.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * TODO commenter les responsabilités de cette classe
 * @author Groupe Projet
 */
public class Zone {
    
    /** Taille maximale d'une zone */
    public static final int TAILLE_MAX = 26;
    
    /** Taille de la zone de jeu par défaut TODO récupération auto */
    public static int tailleDefaut = 10;
    
    

    /** Coordonnée de départ de la zone */
    private Coordonnee coordDepart;
    
    /** Coordonnée d'arriver de la zone */
    private Coordonnee coordArrivee;
    
    /** Taille horizontale (axe x) de la zone de jeu  */
    private int tailleHorizontale;
    
    /** Taille verticale (axe y) de la zone de jeu */
    private int tailleVerticale;
    
    /** Collection contenant les coordonnées */
    private ArrayList<Coordonnee> zoneCoord;
    
    /** Collection contenant les zones */
    private ArrayList<Zone> zoneContenu;

    
    
    /**
     * Construit une zone par défaut (pour l'application)
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
     * Construit une zone avec une coordonnée d'arrivée 
     * @param coordDepart coordonnée où commence la zone
     * @param coordArrivee coordonnée où la zone se fini
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
//     * Construit une zone avec une coordonnée de départ 
//     * et les tailles verticale et horizontale
//     * @param coordDepart coordonnée où commence la zone
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
     * Affiche les cases (présentations à l'écrans) de la zone
     */
    public void afficherZone() {
        
        int indiceCoords; // indice dans l'arrayList coordsInterressants
        
        StringBuilder zone = new StringBuilder(); // chaîne contenant le tableau
        
        /* Récupère les coordonnées intéressantes c'est à dire les coordonnées 
         * où il y a un bateau OU où il y a eu un tir
         */
        ArrayList<Coordonnee> collectionCoords = this.getZoneCoord();

        int numLigne; // numéro de la ligne de la zone

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
            
            /* État de la case */
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
//     * Calcul et construit la case d'arrivée en fonction de la taille de la zone
//     * @return la coordonnée d'arrivée 
//     */
//    private Coordonnee calculCoordArrivee() {
//        int x; // abscisse de la coordonnée d'arrivée
//        int y; // ordonnée de la coordonnée d'arrivé
//        
//        x = this.coordDepart.getX() + this.tailleHorizontale;
//        y = this.coordDepart.getY() + this.tailleVerticale;
//        
//        return new Coordonnee(x, y);
//    }
    
    /**
     * Ajoute des coordonnées dans la collection zoneCoord (de this)
     */
    private void ajouterCoordonnee() {
        int x; // abscisse de la coordonnée
        int y; // ordonnée de la coordonnée
        
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
     * Ajoute les coordonnées du bateau à la zone (this)
     * @param aAjouter bateau à ajouter à la zone (this)
     */
    private void ajouterCoordonnee(Bateau aAjouter) {
        int x; // abscisse de la coordonnée
        int y; // ordonnée de la coordonnée
        
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
     * @param aAjouter flotte à ajouter sur la zone (this)
     */
    public void ajouterFlotte(Flotte aAjouter) {
        for (Bateau bateau : aAjouter.getCollectionBateau()) {
            zoneContenu.add(bateau);
            ajouterCoordonnee(bateau);
        }
    }
    
    /**
     * Vérifie si une zone est en dehors de la zone contenant
     * @param zoneContenant zone contenant la zone
     * @return vrai si la zone à tester est en dehors de la zone contenant
     */
    public boolean horsZone(Zone zoneContenant) {
        Coordonnee coordZoneContenant; // stockage des coordonnées de la zone
        
        coordZoneContenant = zoneContenant.getCoordDepart(); // coord départ
        if (this.getCoordDepart().getX() < coordZoneContenant.getX() 
         || this.getCoordDepart().getY() < coordZoneContenant.getY()) {
            return true;
        }
        
        coordZoneContenant = zoneContenant.getCoordArrivee(); // coord arrivée
        if (this.getCoordArrivee().getX() > coordZoneContenant.getX() 
         || this.getCoordArrivee().getY() > coordZoneContenant.getY()) {
            return true;
        }
        
        return false;
    }  
    
    /**
     * Vérifie si deux zones entrent en collision 
     * @param zoneATester zone à vérifier
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
