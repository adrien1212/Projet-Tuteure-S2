/*
 * Zone.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

import outil.OutilSaisie;

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
        trierCollecCoord(coordAAfficher);

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
            
            /* récupération de la coordonnée */
            zone.append(' ');
            if (coordAAfficher.size() == 0 || coordAAfficher.size() == indiceCoords) {
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
                    zone.append("flûte alors");
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
     * Trie la collection de coordonnée en ordre croissant trié par son indice
     * @param aParcourir collection à parcourir
     */
    public static void trierCollecCoord(ArrayList<Coordonnee> aParcourir) {
        Coordonnee aInserer; // Bateau à positionner à la bonne place
        int j;               // parcours de boucle dans le sens inverse
        
        for (int i = 1; i < aParcourir.size(); i++) {
            aInserer = aParcourir.get(i);
            j = i;
            while(j > 0 && aParcourir.get(j-1).getIndiceCoord() > aInserer.getIndiceCoord()) {
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
    public void ajouterCoordonnee(Bateau aAjouter) {
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
     * Ajoute une zone dans zoneContenu de this
     * @param aAjouter zone à ajouter à this
     */
    public void ajouterZone(Zone aAjouter) {
        this.zoneContenu.add(aAjouter);
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
     * @param zoneATester zone a tester avec this
     * @return vrai si collision entre deux zone
     */
    public boolean collision(Zone zoneATester) {
        /* récupération des zones pour les permuter */
        Zone zoneA = this;
        Zone zoneB = zoneATester;
        
        /* permutation */
        if (zoneA.getCoordDepart().getIndiceCoord() > 
            zoneB.getCoordDepart().getIndiceCoord()) {
            
            zoneA = zoneATester;
            zoneB = this;
        }
        
        /* vérification si le bateau entre en collision avec un autre */
        if (zoneA.getCoordArrivee().getIndiceCoord() >= 
            zoneB.getCoordDepart().getIndiceCoord()) {
            
            return true;
        }
        
        return false;
    }
    
    /**
     * TODO commentaire
     * @return vrai si un bateau est touché
     */
    public boolean coup() {
        Coordonnee coup;
        
        /* demande de la coordonnée à l'utilisateur */
        coup = OutilSaisie.saisieCoordonnee();
        
        /* ajout à la collection de coupJoue */
        Jeu.coupJoue.add(coup);
        
        for (Coordonnee coordATester : this.zoneCoord) {
            /* si la coordonnée et déjà présente dans la collection */
            if (coup.coordonneesEgales(coordATester)) {
                coordATester.setTouche(true);
                return true;
            }
        }
        
        /* sinon ajout de la coordonnée à la collection */
        coup.setTouche(true);
        this.zoneCoord.add(coup);
        
        /* ajout à la collection de coupJoue */
        Jeu.coupJoue.add(coup);
        
        return false;
    }
}
