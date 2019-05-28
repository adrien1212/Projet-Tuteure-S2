/*
 * Zone.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

import outil.OutilSaisie;

/**
 * TODO commenter les responsabilit�s de cette classe
 * @author Groupe Projet
 */
public class Zone {
    
    /** Taille maximale d'une zone */
    public static final int TAILLE_MAX = 26;
    
    /** Taille de la zone de jeu par d�faut TODO r�cup�ration auto */
    public static int tailleDefaut = 12;
    
    

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
    
    /**
     * @param coordDepart
     * @param tailleHorizontale
     * @param tailleVerticale
     */
    public Zone(Coordonnee coordDepart, int tailleHorizontale, int tailleVerticale) {
        this.coordDepart = coordDepart;
        this.tailleHorizontale = tailleHorizontale;
        this.tailleVerticale = tailleVerticale;
        this.zoneCoord = new ArrayList<Coordonnee>();
        this.zoneContenu = new ArrayList<Zone>();
    }
    
    
    /**
     * TODO commenter le r�le de cette m�thode
     * @param taille
     */
    public static void setTailleDefaut(int taille) {
        tailleDefaut = taille;
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
     * Affiche les cases (pr�sentations � l'�crans) de la zone
     */
    public void afficherZone() {
        
        int indiceCoords;     // indice dans l'arrayList zoneCoord
        Coordonnee aAfficher; // coordonn�e � afficher
        
        StringBuilder zone = new StringBuilder(); // cha�ne contenant le tableau
        
        /* r�cup�ration des coordonn�es � afficher */
        ArrayList<Coordonnee> coordAAfficher = this.getZoneCoord();
        trierCollecCoord(coordAAfficher);

        int numLigne; // num�ro de la ligne de la zone

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

            /* affichage des chiffres sur le cot� (1 = 0) */
            if (i % tailleHorizontale == 0) {
                zone.append("\n");
                if (numLigne < 10) {
                    zone.append(' ');
                }
                zone.append(numLigne);
                numLigne++;
            }
            
            /* r�cup�ration de la coordonn�e */
            zone.append(' ');
            if (coordAAfficher.size() == 0 || coordAAfficher.size() == indiceCoords) {
                zone.append('.');
            } else if (coordAAfficher.get(indiceCoords).getIndiceCoord() == i) {
                aAfficher = coordAAfficher.get(indiceCoords);
                if (aAfficher.isBateauCoule()) {
                    zone.append('X');
                } else if (aAfficher.isContientBateau() && aAfficher.isTouche()) {
                    zone.append('x');
                }/* else if (aAfficher.isContientBateau()) {
                    zone.append('=');
                }*/ else if (aAfficher.isTouche()) {
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
    

    
    /**
     * Trie la collection de coordonn�e en ordre croissant tri� par son indice
     * @param aParcourir collection � parcourir
     */
    public static void trierCollecCoord(ArrayList<Coordonnee> aParcourir) {
        Coordonnee aInserer; // Bateau � positionner � la bonne place
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
     * Ajoute des coordonn�es dans la collection zoneCoord (de this)
     */
    public void ajouterCoordonnee() {
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
    public void ajouterCoordonnee(Bateau aAjouter) {
        for (Coordonnee coordAAjouter : aAjouter.getZoneCoord()) {
            zoneCoord.add(coordAAjouter);
        }
    }
    
    /**
     * Ajoute une zone dans zoneContenu de this
     * @param aAjouter zone � ajouter � this
     */
    public void ajouterZone(Zone aAjouter) {
        this.zoneContenu.add(aAjouter);
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
     * Retire la zone du bateau et ses coordonn�es des collections de this
     * @param indice indice du bateau � retirer
     */
    public void retirerBateau(int indice) {
        Coordonnee coordAChanger; // coordonn�e balayer dans la boucle
        
        /* retirer les coordonn�es du bateau */
        for (int i = 0; i < zoneCoord.size(); i++) {
            coordAChanger = zoneCoord.get(i);
            if (coordAChanger.getIndiceBateau() == indice) {
                zoneCoord.remove(i);
            }
        }
        
        /* retire la zone du bateau */
        this.getZoneContenu().remove(indice);
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
     * v�rifie si deux zones entre en collision
     * @param zoneATester zone a tester avec this
     * @return vrai si collision entre deux zone
     */
    public boolean collision(Zone zoneATester) {
        /* r�cup�ration des zones pour les permuter */
        Zone zoneA = this;
        Zone zoneB = zoneATester;
        
        /* permutation */
        if (zoneA.getCoordDepart().getIndiceCoord() > 
            zoneB.getCoordDepart().getIndiceCoord()) {
            
            zoneA = zoneATester;
            zoneB = this;
        }
        
        /* v�rification si le bateau entre en collision avec un autre */
        if (zoneA.getCoordArrivee().getIndiceCoord() >= 
            zoneB.getCoordDepart().getIndiceCoord()) {
            
            return true;
        }
        
        return false;
    }
    
    /**
     * v�rifie si une zone est un point ne sont pas en colision
     * @param x
     * @param y
     * @return vrai si il y a une collision entre deux zone
     */
    public boolean collision( int x, int y) { 
        boolean test =false;  
        ArrayList<Coordonnee> CollectionCaseZone = this.getZoneCoord(); //on charge tout les point de la zone
        
        for(int indexPointZone=0; indexPointZone<CollectionCaseZone.size(); 
                indexPointZone++) {
            
            if (CollectionCaseZone.get(indexPointZone).getX()==x && 
                    CollectionCaseZone.get(indexPointZone).getY()==y) {
                test=true;
            }
        }
        
        
        return test;
    }
    
    /**
     * Demande � l'utilisateur une coordonn�e o� tirer un coup et change l'etat
     * de la coordonn�e touch�
     * @param collecCoup collection des coup jou�s
     * @param flotte la flotte des gens sur qui ont tire
     * @return l'indice du bateau touch�, -1 si aucun
     */
    public int coup(ArrayList<Coordonnee> collecCoup, Flotte flotte) {
        Coordonnee coup;
        do {
            /* demande de la coordonn�e � l'utilisateur */
            coup = OutilSaisie.saisieCoordonnee();
            if (coup.coupNonPertinent(collecCoup, flotte)) {
            	System.out.println("coup non pertinent veuillez r�essayer ");
            }
        }while(coup.coupNonPertinent(collecCoup, flotte));


        /* ajout � la collection de coupJoue */
        collecCoup.add(coup);
        
        for (Coordonnee coordATester : this.zoneCoord) {
            /* si la coordonn�e et d�j� pr�sente dans la collection */
            if (coup.coordonneesEgales(coordATester)) {
                coordATester.setTouche(true);
                return coordATester.getIndiceBateau();
            }
        }
        
        /* sinon ajout de la coordonn�e � la collection */
        coup.setTouche(true);
        this.zoneCoord.add(coup);
        
        return -1;
    }
}
