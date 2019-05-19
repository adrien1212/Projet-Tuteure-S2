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
     * @param tailleHorizontale nouvelle valeur de tailleHorizontale
     */
    public void setTailleHorizontale(int tailleHorizontale) {
        this.tailleHorizontale = tailleHorizontale;
    }

    /**
     * @return valeur de tailleVerticale
     */
    public int getTailleVerticale() {
        return tailleVerticale;
    }
    
    /**
     * @param tailleVerticale nouvelle valeur de tailleVerticale
     */
    public void setTailleVerticale(int tailleVerticale) {
        this.tailleVerticale = tailleVerticale;
    }
    
    /**
     * @return valeur de zoneCoord
     */
    public ArrayList<Coordonnee> getZoneCoord() {
        return this.zoneCoord;
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
        return "Zone [coordDepart=" + coordDepart 
                 + ", coordArrivee=" + coordArrivee 
                 + ", tailleHorizontale=" + tailleHorizontale 
                 + ", tailleVerticale=" + tailleVerticale + "]";
    }
    
    

    /**
     * Affiche les cases (présentations à l'écrans) de la zone
     */
    public void afficherZone() {
        
        int indiceCoords;     // indice dans l'arrayList zoneCoord
        Coordonnee aAfficher; // coordonnée à afficher
        
        StringBuilder zone = new StringBuilder(); // chaîne contenant le tableau
        
        /* récupération des coordonnées à afficher */
        ArrayList<Coordonnee> coordAAfficher = this.getZoneCoord();
        System.out.println("\nAvant tri : \n" + coordAAfficher);
        trierCollecCoord(coordAAfficher);
        System.out.println("\nApres tri : \n" + coordAAfficher);

        int numLigne; // numéro de la ligne de la zone

        /* affichage des lettres (A = 0) */
        zone.append("\n   ");
        for (int indice = 0; indice < tailleHorizontale; indice++) {
            zone.append((char) ('A' + indice));
            zone.append(' ');
        }
        
        indiceCoords = 0;
        numLigne = 1;
        // afficher du tableau
        for (int i = 0; i < tailleHorizontale * tailleVerticale; i++) {

            /* affichage des chiffres sur le coté (1 = 0) */
            if (i % tailleHorizontale == 0) {
                zone.append("\n");
                if (numLigne < 10) {
                    zone.append(' ');
                }
                zone.append(numLigne);
                numLigne++;
            }
            
            System.out.println("indice : " + i + " , idcoord : " + coordAAfficher.get(indiceCoords).getIndiceCoord());
            
            /* récupération de la coordonnée */
            zone.append(' ');
            if (coordAAfficher.size() == 0) {
                zone.append('.');
            } else if (coordAAfficher.get(indiceCoords).getIndiceCoord() == i) {
                aAfficher = coordAAfficher.get(indiceCoords);
                if (aAfficher.isBateauCoule()) {
                    zone.append('X');
                } else if (aAfficher.isContientBateau() && aAfficher.isTouche()) {
                    zone.append('x');
                } else if (aAfficher.isContientBateau()) {
                    zone.append('=');
                } else if (aAfficher.isTouche()) {
                    zone.append('o');
                } else {
                    zone.append("NIQUE TOI");
                }
                indiceCoords++;
            } else {
                zone.append('.');
            }
        }
        
        zone.append("\n");
        
        System.out.println(zone.toString());
    }
    

    
    /**
     * TODO commenter le rôle de cette méthode
     * @param aParcourir 
     */
    public static void trierCollecCoord(ArrayList<Coordonnee> aParcourir) {
        Coordonnee aInserer; // Bateau à positionner à la bonne place
        int j;               // parcours de boucle dans le sens inverse
        
        for (int i = 1; i < aParcourir.size(); i++) {
            aInserer = aParcourir.get(i);
            j = i;
            while(j > 0 && aParcourir.get(j-1).getIndiceCoord() < aInserer.getIndiceCoord()) {
                j--;
            }
            aParcourir.remove(i);
            aParcourir.add(j, aInserer);
        } 
    }
    
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
     * vérifie si deux zones entre en collision
     * @param zoneAtTester zone a tester avec this
     * @return vrai si collision entre deux zone
     */
    public boolean collision(Zone zoneAtTester) {
        /* récupération des zones pour les permuter */
        Zone zoneA = this;
        Zone zoneB = zoneAtTester;
        
        Coordonnee caseA = zoneA.getCoordDepart(); // case de départ de la zone A
        Coordonnee caseB = zoneB.getCoordDepart(); // case de départ de la zone B
        Coordonnee tmp; // stockage pour permutation

        /* permutation */
        if (caseA.getX() > caseB.getX() && caseA.getY() > caseB.getY()) {
            tmp = caseA;
            caseA = caseB;
            caseB = tmp;
            zoneA = zoneAtTester;
            zoneB = this;
        }

        if (caseB.getX() <= (caseA.getX() + zoneA.getTailleHorizontale())
                && caseB.getY() <= (caseA.getY() + zoneA.tailleVerticale)) {
            return true;
        }

        return false;
    }
    

}
