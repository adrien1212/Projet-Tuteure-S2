/*
 * Zone.java                                                  14 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;
import java.util.Random;

/**
 * Cr�er des zones qui sont compos�es de coordonn�es, 
 * @author Groupe projet
 */
public class Zone {

    /** Nombre maximal de boucle pour essayer de placer un bateau */
    private final int NB_BOUCLE_PLACEMENT_MAX = 15;
    
    /** Largeur de la zone de jeu (TODO recup auto) */
    private final int LARGEUR = 10;
    
    /** Longueur de la zone de jeu (TODO recup auto) */
    private final int LONGUEUR = 10;
    
    /** Zone de jeu de l'application */
    protected static Zone ZONE_JEU = new Zone();
    
    
    
    /** Largeur de la zone */
    private int largeur;
    
    /** Longueur de la zone */
    private int longueur;
    
    /** Coordonn�e de d�part de la zone */
    private Coordonnee coordDepart;
    
    /** Collection contenant les coordonn�es pr�sentes dans la zone */
    private ArrayList<Coordonnee> zone;

    
    
    /**
     * Construit une zone par d�faut (pour l'application)
     */
    public Zone() {
        this.longueur = LONGUEUR;
        this.largeur = LARGEUR;
        this.coordDepart = new Coordonnee(0, 0);
        this.zone = this.creerZoneDefaut();
    }
    
    /**
     * Construit une zone � partir d'une autre zone
     * @param longueur longueur de la zone � construire
     * @param largeur largeur de la zone � construire
     * @param indice indice du bateau dans la flotte
     * @param coordDepart coordonn�e ou commencer la nouvelle zone
     */
    public Zone(int longueur, int largeur, int indice, Coordonnee coordDepart) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.coordDepart = coordDepart;
        this.zone = creerZoneBateau(ZONE_JEU, indice);
    }

    /**
     * Construit une zone � partir d'un bateau (zone d'abordage)
     * @param bateau bateau o� construire la zone 
     */
    public Zone(Bateau bateau) {
        this.longueur = bateau.getLongueur();
        this.largeur = bateau.getLargeur();
        this.coordDepart = verifZone(ZONE_JEU, bateau);
        this.zone = creerZoneAbordage(ZONE_JEU, bateau.getIndiceBateau());
    }
    
    /**
     * Cr�er une zone pour stocker les valeurs
     * @param longueur longueur de la zone
     * @param largeur largeur de la zone
     */
    public Zone(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
    }

    
    
    /**
     * V�rifie si la zone d'abordage rentre dans la zone de d�part
     * sinon red�fini la coordonn�e de d�part de la zone � cr�er
     * @param zoneDepart zone ou placer la nouvelle zone
     * @param bateau zone � encadrer par la nouvelle zone
     * @return la coordonn�e de d�part de la zone d'abordage
     */
    private Coordonnee verifZone(Zone zoneDepart, Zone bateau) {
        
        /* r�cup�ration des coordonn�es de d�part du bateau */
        int x = bateau.getCoordDepart().getX(),
            y = bateau.getCoordDepart().getY();
        
        /* changement de la taille de la zone */
        longueur = longueur + 2;
        largeur = largeur + 2;
        
        /* d�placement de la case si possible */
        if (x != 0) {
            x--;
        }
        if (y != 0) {
            y--;
        }
        
        /* v�rification de la taille de la zone */
        if ( x == 0 || zoneDepart.getLongueur()-1 < x + longueur) {
            longueur--;
        }
        if (y == 0 || zoneDepart.getLargeur()-1 < y + largeur) {
            largeur--;
        }
        
        return new Coordonnee(x, y);
    }

    /**
     * Cr�er la zone par d�faut pour l'application
     * @return la zone contenant les coordonn�es (collection de coordonn�e)
     */
    private ArrayList<Coordonnee> creerZoneDefaut() {
        int indice; // indice de la case
        
        /* collection � retourner */
        ArrayList<Coordonnee> zone = new ArrayList<Coordonnee>();
        
        indice = 0;
        for (int longueur = 0; longueur < LONGUEUR; longueur++) {
            for (int largeur = 0; largeur < LARGEUR; largeur++) {
                zone.add(new Coordonnee(largeur, longueur, indice));
            }
        }
        return zone;
    }
    
    /**
     * Cr�er une zone � partir d'une autre zone (copie des r�f�rences)
     * @param zoneDepart zone ou prendre les coordonn�es
     * @param indice indice du bateau dans la flotte
     * @return la zone du bateau (collection de coordonn�e)
     * @throws IllegalArgumentException 
     */
    private ArrayList<Coordonnee> creerZoneBateau(Zone zoneDepart, int indice) 
            throws IllegalArgumentException {
        
        /* r�cup�ration des coordonn�es de la zone de jeu */
        ArrayList<Coordonnee> zoneJeu;
        /* nouvelle zone � retourner */
        ArrayList<Coordonnee> nouvelleZone;
        
        Coordonnee coordAModifier; // coordonn�e � modifier
        
        /* longueur et largeur de la zone de d�part */
        int longueurZone = zoneDepart.getLongueur(),
            largeurZone = zoneDepart.getLargeur();
        
        /* abscisses et ordonn� de la case de d�part */
        int x = coordDepart.getX(),
            y = coordDepart.getY(); 
        
        /* verification */
        if (longueurZone < x || largeurZone < y) {
            throw new IllegalArgumentException("Coordonn�e en dehors de la " 
                                             + "zone de jeu");
        } else if (longueurZone < x + longueur || largeurZone < y + largeur) {
            throw new IllegalArgumentException("Bateau en dehors de la zone");
        }
        
        /* r�cup�ration des coordonn�es de la zone de jeu */
        zoneJeu = zoneDepart.getZone();
        /* cr�ation de la nouvelle zone */
        nouvelleZone = new ArrayList<Coordonnee>();
        
        for (int largeur = 0; largeur < this.largeur; largeur++) {
            x = coordDepart.getX();
            for (int longueur = 0; longueur < this.longueur; longueur++) {
                coordAModifier = zoneJeu.get(y * longueurZone + x);
                coordAModifier.setContientBateau(true);
                nouvelleZone.add(coordAModifier);
                x++;
            }
            y++;
        }
        
        return nouvelleZone;
    }

    /**
     * Cr�er une zone � partir d'une autre (copie des r�f�rences)
     * @param zoneDepart zone ou prendre les coordonn�es
     * @param indice indice du bateau dans la flotte
     * @return la zone d'abordage du bateau (collection de coordonn�e)
     */
    private ArrayList<Coordonnee> creerZoneAbordage(Zone zoneDepart, int indice) {
        
        // TODO remove
        System.out.println("-------- resume ---------\n"
                + " longueur : " + longueur + "\n"
                + " largeur  : " + largeur + "\n");
        
        
        /* r�cup�ration des coordonn�es de la zone de jeu */
        ArrayList<Coordonnee> zoneJeu = zoneDepart.getZone();
        /* cr�ation de la nouvelle zone */
        ArrayList<Coordonnee> nouvelleZone = new ArrayList<Coordonnee>();
        
        Coordonnee coordAModifier; // coordonn�e � modifier
        
        /* longueur et largeur de la zone de d�part */
        int longueurZone = zoneDepart.getLongueur();
        
        /* abscisses et ordonn� de la case de d�part */
        int x,
            y = coordDepart.getY(); 
        
        for (int largeur = 0; largeur < this.largeur; largeur++) {
            x = coordDepart.getX();
            for (int longueur = 0; longueur < this.longueur; longueur++) {
                coordAModifier = zoneJeu.get(y * longueurZone + x);
                coordAModifier.setIndiceBateau(indice);
                nouvelleZone.add(coordAModifier);
                x++;
            }
            y++;
        }
        
        return nouvelleZone;
    }
    
    
    
    /**
     * @return valeur de largeur
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * @return valeur de longueur
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * @return valeur de coordDepart
     */
    public Coordonnee getCoordDepart() {
        return coordDepart;
    }

    /**
     * @param coordDepart nouvelle valeur de coordDepart
     */
    public void setCoordDepart(Coordonnee coordDepart) {
        this.coordDepart = coordDepart;
    }

    /**
     * @return valeur de zone
     */
    public ArrayList<Coordonnee> getZone() {
        return zone;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Zone [largeur=" + largeur + ", longueur=" + longueur 
             + ", coordDepart=" + coordDepart + ", zone=" + zone
             + "]";
    }
    
    
    
    /**
     * Affiche les cases (pr�sentations � l'�crans) de la zone
     */
    public void afficherZone() {
        StringBuilder zone = new StringBuilder(); // cha�ne contenant le tableau
        ArrayList<Coordonnee> tabCoord = this.getZone(); // tableau a afficher
        Coordonnee coord; // case � l'indice donn� du tableau

        int nbLigne; // num�ro de la ligne de la zone

        zone.append("\n   ");
        for (int indice = 0; indice < longueur; indice++) {
            zone.append((char) ('A' + indice));
            zone.append(' ');
        }

        nbLigne = 1;
        // affichage du tableau
        for (int indice = 0; indice < tabCoord.size(); indice++) {
            coord = tabCoord.get(indice);

            if (indice % longueur == 0) {
                zone.append("\n");
                if (nbLigne < 10) {
                    zone.append(' ');
                }
                zone.append(nbLigne);
                nbLigne++;
            }

            zone.append(' ');
            /* signe en fonction de l'�tat de la case */
            if (coord.getIndiceBateau() >= 0 && coord.isTouche() 
             && !coord.isCoule()) {
                zone.append('x');
            } else if (coord.isCoule()) {
                zone.append('X');
            } else if (coord.isTouche()) {
                zone.append('O');
            } else if (coord.isContientBateau()) {
                zone.append('=');
            } else if (coord.getIndiceBateau() >= 0) {
                zone.append('~');
            } else {
                zone.append('.');
            }
        }

        zone.append("\n");
        
        System.out.println(zone.toString());
    }
    
    
    
    /**
     * V�rifie si la zone touche un bateau
     * @return vrai si la zone touche un bateau
     */
    public boolean collision() {
        for (Coordonnee coordATester : this.getZone()) {
            if (coordATester.isContientBateau()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * V�rifie si deux zones entrent en collision 
     * @param zoneATester zone � tester avec this
     * @return vrai si les zones entrent en collision
     */
    public boolean collision(Zone zoneATester) {
        /* r�cup�ration des deux coordonn�es de d�part des deux zones */
        Coordonnee coordA = this.getCoordDepart();
        Coordonnee coordB = zoneATester.getCoordDepart();
        Coordonnee tmp; // stockage pour permutation

        /* permutation */
        if ( coordB.getX() < coordA.getX() && coordB.getY() < coordA.getY()) {
            tmp = coordA;
            coordA = coordB;
            coordB = tmp;
        }

        if (coordB.getX() <= (coordA.getX() + zoneATester.getLongueur())
         && coordB.getY() <= (coordA.getY() + zoneATester.getLargeur())) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Change l'�tat touche de la coordonn�e cible 
     * @param coordCoup coordonn�e du coup
     * @return vrai si un bateau est touch�
     */
    public boolean coup(Coordonnee coordCoup) {
        /* r�cup�ration de la case � modifier */
        Coordonnee aModifier = this.getZone().get(coordCoup.getY() 
                                                  * this.getLongueur() 
                                                  + coordCoup.getX());
        aModifier.setTouche(true);
        
        return aModifier.isContientBateau();
    }
    
    
    
    
    /**
     * Placement al�atoire des bateau dans la zone de jeu
     * @param flotteAPlacer flotte contenant les bateaux � placer
     */
    public void placementAleatoireZone(Flotte flotteAPlacer) {
        int compteurBouclage;   // nombre de co�ts pour placer un bateau    
        int nombreAleatoire;    // nombre al�atoire qui donne l'indice de la 
                                // caseXY dans l'arrayList
        
        boolean bateauVertical; // vrai si le bateau est � la vertical
        boolean placementOk;    // vrai si placement du bateau possible
        
        Bateau aPlacer;         // bateau � placer dans la zone de jeu
        
        // TODO Verifier que tous les bateaux peuvent �tre plac�s
        
        /* R�cup�rer toutes les cases de la grille */
        ArrayList<Coordonnee> coordGrille = new ArrayList<Coordonnee>();
        /* R�cup�ration de la collection de bateau */
        ArrayList<Bateau> collecBateau = flotteAPlacer.getCollectionBateau();
        
        for (Coordonnee aRecuperer : this.getZone()) {
            coordGrille.add(aRecuperer);
        }
        
        /* R�cup�rer la flotte du jeu */
        for (int indice = 0; indice < collecBateau.size(); indice++) {
            aPlacer = collecBateau.get(indice);
            placementOk = false;
            
            /* vertical ou non */
            nombreAleatoire = (int) (Math.random() * 100);
            bateauVertical = nombreAleatoire < 50 ? true : false;
            
            compteurBouclage = 0;
            /* Tester si le bateau rentrera */
            do {
                /* Nombre qui donne l'indice de la caseXY � aller chercher dans
                 * l'ArrayList
                 */
                Random nbAlea = new Random();
                nombreAleatoire = nbAlea.nextInt(coordGrille.size());
                
                aPlacer = Bateau.construireBateau(aPlacer.getTaille(), 
                                                  bateauVertical, indice);
                
                placementOk = Bateau.placerBateau(aPlacer,
                                              coordGrille.get(nombreAleatoire));
                
                compteurBouclage++;
            } while (!placementOk 
                  && compteurBouclage <= NB_BOUCLE_PLACEMENT_MAX);
            
            /* re-ex�cution des placements du bateau depuis le d�but */
            if (compteurBouclage == 15) {
                placementAleatoireZone(flotteAPlacer);
            }
            
            /* placement du bateau */
            aPlacer = new Bateau(getLongueur(), getLargeur(), indice, coordGrille.get(nombreAleatoire));
            
            //flotteAPlacer.ajouterBateau(aPlacer);
            
            /* Supprimer les coordonn�e utilis�es par le bateau cr�er */
            for (Coordonnee coordSuppression : aPlacer.getAbordage().getZone()) {
                coordGrille.remove(coordSuppression);
            }
        }
    }
   
    
    
    
    /**
     * @param index 
     * @return la r�f�rence de la coodonn�e demand�
     */
    public Coordonnee getCoordonneeZone(int index) {
        return zone.get(index);
    }
    
    /**
     * M�thode cr�ant une zone � partir de coordon�e
     * qui en sera sont centre et d'un espacement
     * @param x emplacement x du point 
     * @param y emplacement y du point 
     * @param distance en le point centrale et les point 
     * @return la zone creer
     */
    public Zone zoneAutourPoint(int x, int y, int distance) {
        return null;
        
    }
    
    /**
     * Determine si 2 point sont cote � cote avec leur coordon�e 
     * @param pointA 
     * @param pointB  
     * @return un boolean � true si les points sont � cot�s 
     */
    public static boolean estProche(Coordonnee pointA, Coordonnee pointB) {
        int xPointA,
            xPointB,
            yPointA,
            yPointB,
            calculX,
            calculY;
            
 
        xPointA=pointA.getX();
        xPointB=pointB.getX();
        yPointA=pointA.getY();
        yPointB=pointB.getY();
        
        calculX=xPointA-xPointB;
        calculY=yPointA-yPointB;
        
        if (calculX<=1 && calculX>=-1 && calculY<=1 && calculY>=-1){
            return true;
        }
        
        return false;
    }
    
    /**
     * v�rifie si une zone est un point ne sont pas en colision
     * @param zoneA 
     * @param x
     * @param y
     * @return vrai si il y a une collision entre deux zone
     */
    public static boolean collision(Zone zoneA, int x, int y) { 
        boolean test =false;  
        ArrayList<Coordonnee> CollectionCaseZone = zoneA.getZone(); //on charge tout les point de la zone
        
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
     * M�thode permetant de dtecter si un coup est non pertinent en renvoyant un
     * boolean � true si le coup est non pertinent 
     * @param xCoup la coordonn�e x du coup
     * @param yCoup la coordonn�e y du coup
     * @param zoneJeux zone de jeux 
     * @param flotteBateau flotte de la partie
     * @return un boolean � true si le coup est non pertinent
     */
    public static boolean coupNonPertinent(int xCoup,int yCoup, Zone zoneJeux, Flotte flotteBateau) {
        boolean coupNonPertinent = false;
        int index,
        longueurZone;
        ArrayList<Bateau> flotte;


        flotte=flotteBateau.getCollectionBateau();
        longueurZone = zoneJeux.getLongueur();
        index = yCoup * longueurZone + xCoup;

        /* detection si le coup � d�j� �t� jou� */
        if (zoneJeux.getCoordonneeZone(index).isTouche()) {
            return true;// on �vite de passer dans les autres boucles
        }


        /*
         * test avec des bateau touch�s mini 2 element touch�s du bateau mais 
         * pas coul�
         */

        /*
         * on cr�er 2 zones distinct autour du point l'une englobant l'ensemble
         * des point � 1 case du point et une autre englobant tout les point � 2
         * de distance du point 
         */
        Zone zone1 = zoneJeux.zoneAutourPoint(xCoup, yCoup, 1);
        Zone zone2 = zoneJeux.zoneAutourPoint(xCoup, yCoup, 2);

        // on selectionne tous les points touch� avec un bateau sur eux non coul� de la zone 1
        for (int index1=0; index1<zone1.getZone().size(); index1 ++){
            for (int index2=0; index2<zone2.getZone().size() &&
                    zone1.getCoordonneeZone(index1).isCoule(); index2 ++) {

                //on selectionne tous les points touch� avec un bateau sur eux non coul� de la zone 2 
                if (zone2.getCoordonneeZone(index2).isCoule() &&
                        zone2.getCoordonneeZone(index2)
                        != zone1.getCoordonneeZone(index1)) {

                    //on regarde si un �lement de la zone 1 et � cot� de la zone 2
                    if (estProche(zone2.getCoordonneeZone(index2),
                            zone1.getCoordonneeZone(index1))) {
                        coupNonPertinent = true ;
                    }
                }
            }
        }

        /* detection si le coup est impossible en raison des
         * zone anti Abordage avec de bateau coul�
         */
        for (int indexBateau=0; indexBateau<flotte.size() ; indexBateau++) {
            if (flotte.get(indexBateau).isCoule()) { 
                // on recup�re la zone d'abordage du bateau            
                Zone aVerifier= flotte.get(indexBateau).getAbordage();

                // on verifie si il n'y a pas une colisions entre la zone d'abordage et le point
                if (collision(aVerifier, xCoup, yCoup)) {
                    coupNonPertinent = true ;
                }
            }
        }

        return coupNonPertinent;
    }

    /**
     * M�thode d'aide de jeux, cette m�thode conseillera le joueur sur un des
     * meilleurs coup � faire
     * @param zoneJeux zone de jeux de la partie
     * @param flotteJeux flotte pr�sente dans la partie
     * @return un tableau de int conenant les coordonnee de la case � tirer sous 
     * la forme x,y
     */
    /*public static int[] meilleurCoup(Zone zoneJeux, Flotte flotteJeux) {
        int[] coordonneeXY = {-1,-1};
        
        // r�cup�ration de la flotte
        ArrayList<Bateau> flotteRecupere = flotteJeux.getCollectionBateau();
        
        //on regarde si une case de la zone est touch�
        for (int index= 0 ;index< zoneJeux.getZone().size() ; index++) {
            if (zoneJeux.getCoordonneeZone(index).isCoule() ) {
                //r�cup�rer tous les bateaux

                for (Bateau bateauRecuperer: flotteRecupere) {
                    if (bateauRecuperer.ge)
                }
                
                // regarder si un bateau a plus de 2 cases touch�s
                   // si oui jouer dessus
                   // donner le prochain cout � jouer dans la direction 
                   // /!\ v�rifier qu'une case ne bloque + CASE jou�e
                   //       0XXX---
                
                // regarder si un bateau a plus de 1 cases jou�e
                   // Si oui, regarder les cases autour si elle sont jou�es
                
                
                // donner une direction al�atoire � l'utilisateur pertinante
                do {
                    
                    coordonneeXY[0] = (int) (Math.random() * zoneJeux.LONGUEUR );
                    coordonneeXY[1] = (int) (Math.random() * zoneJeux.LARGEUR );
                    
                }while (coupNonPertinent(coordonneeXY[0],coordonneeXY[1],
                        zoneJeux, flotteJeux));
            }
        }


        return null;
    }*/
}
