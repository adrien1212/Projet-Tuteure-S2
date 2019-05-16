/*
 * Zone.java                                                  14 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;
import java.util.Random;

/**
 * Créer des zones qui sont composées de coordonnées, 
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
    
    /** Coordonnée de départ de la zone */
    private Coordonnee coordDepart;
    
    /** Collection contenant les coordonnées présentes dans la zone */
    private ArrayList<Coordonnee> zone;

    
    
    /**
     * Construit une zone par défaut (pour l'application)
     */
    public Zone() {
        this.longueur = LONGUEUR;
        this.largeur = LARGEUR;
        this.coordDepart = new Coordonnee(0, 0);
        this.zone = this.creerZoneDefaut();
    }
    
    /**
     * Construit une zone à partir d'une autre zone
     * @param longueur longueur de la zone à construire
     * @param largeur largeur de la zone à construire
     * @param indice indice du bateau dans la flotte
     * @param coordDepart coordonnée ou commencer la nouvelle zone
     */
    public Zone(int longueur, int largeur, int indice, Coordonnee coordDepart) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.coordDepart = coordDepart;
        this.zone = creerZoneBateau(ZONE_JEU, indice);
    }

    /**
     * Construit une zone à partir d'un bateau (zone d'abordage)
     * @param bateau bateau où construire la zone 
     */
    public Zone(Bateau bateau) {
        this.longueur = bateau.getLongueur();
        this.largeur = bateau.getLargeur();
        this.coordDepart = verifZone(ZONE_JEU, bateau);
        this.zone = creerZoneAbordage(ZONE_JEU, bateau.getIndiceBateau());
    }
    
    /**
     * Créer une zone pour stocker les valeurs
     * @param longueur longueur de la zone
     * @param largeur largeur de la zone
     */
    public Zone(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
    }

    
    
    /**
     * Vérifie si la zone d'abordage rentre dans la zone de départ
     * sinon redéfini la coordonnée de départ de la zone à créer
     * @param zoneDepart zone ou placer la nouvelle zone
     * @param bateau zone à encadrer par la nouvelle zone
     * @return la coordonnée de départ de la zone d'abordage
     */
    private Coordonnee verifZone(Zone zoneDepart, Zone bateau) {
        
        /* récupération des coordonnées de départ du bateau */
        int x = bateau.getCoordDepart().getX(),
            y = bateau.getCoordDepart().getY();
        
        /* changement de la taille de la zone */
        longueur = longueur + 2;
        largeur = largeur + 2;
        
        /* déplacement de la case si possible */
        if (x != 0) {
            x--;
        }
        if (y != 0) {
            y--;
        }
        
        /* vérification de la taille de la zone */
        if ( x == 0 || zoneDepart.getLongueur()-1 < x + longueur) {
            longueur--;
        }
        if (y == 0 || zoneDepart.getLargeur()-1 < y + largeur) {
            largeur--;
        }
        
        return new Coordonnee(x, y);
    }

    /**
     * Créer la zone par défaut pour l'application
     * @return la zone contenant les coordonnées (collection de coordonnée)
     */
    private ArrayList<Coordonnee> creerZoneDefaut() {
        int indice; // indice de la case
        
        /* collection à retourner */
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
     * Créer une zone à partir d'une autre zone (copie des références)
     * @param zoneDepart zone ou prendre les coordonnées
     * @param indice indice du bateau dans la flotte
     * @return la zone du bateau (collection de coordonnée)
     * @throws IllegalArgumentException 
     */
    private ArrayList<Coordonnee> creerZoneBateau(Zone zoneDepart, int indice) 
            throws IllegalArgumentException {
        
        /* récupération des coordonnées de la zone de jeu */
        ArrayList<Coordonnee> zoneJeu;
        /* nouvelle zone à retourner */
        ArrayList<Coordonnee> nouvelleZone;
        
        Coordonnee coordAModifier; // coordonnée à modifier
        
        /* longueur et largeur de la zone de départ */
        int longueurZone = zoneDepart.getLongueur(),
            largeurZone = zoneDepart.getLargeur();
        
        /* abscisses et ordonné de la case de départ */
        int x = coordDepart.getX(),
            y = coordDepart.getY(); 
        
        /* verification */
        if (longueurZone < x || largeurZone < y) {
            throw new IllegalArgumentException("Coordonnée en dehors de la " 
                                             + "zone de jeu");
        } else if (longueurZone < x + longueur || largeurZone < y + largeur) {
            throw new IllegalArgumentException("Bateau en dehors de la zone");
        }
        
        /* récupération des coordonnées de la zone de jeu */
        zoneJeu = zoneDepart.getZone();
        /* création de la nouvelle zone */
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
     * Créer une zone à partir d'une autre (copie des références)
     * @param zoneDepart zone ou prendre les coordonnées
     * @param indice indice du bateau dans la flotte
     * @return la zone d'abordage du bateau (collection de coordonnée)
     */
    private ArrayList<Coordonnee> creerZoneAbordage(Zone zoneDepart, int indice) {
        
        // TODO remove
        System.out.println("-------- resume ---------\n"
                + " longueur : " + longueur + "\n"
                + " largeur  : " + largeur + "\n");
        
        
        /* récupération des coordonnées de la zone de jeu */
        ArrayList<Coordonnee> zoneJeu = zoneDepart.getZone();
        /* création de la nouvelle zone */
        ArrayList<Coordonnee> nouvelleZone = new ArrayList<Coordonnee>();
        
        Coordonnee coordAModifier; // coordonnée à modifier
        
        /* longueur et largeur de la zone de départ */
        int longueurZone = zoneDepart.getLongueur();
        
        /* abscisses et ordonné de la case de départ */
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
     * Affiche les cases (présentations à l'écrans) de la zone
     */
    public void afficherZone() {
        StringBuilder zone = new StringBuilder(); // chaîne contenant le tableau
        ArrayList<Coordonnee> tabCoord = this.getZone(); // tableau a afficher
        Coordonnee coord; // case à l'indice donné du tableau

        int nbLigne; // numéro de la ligne de la zone

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
            /* signe en fonction de l'état de la case */
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
     * Vérifie si la zone touche un bateau
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
     * Vérifie si deux zones entrent en collision 
     * @param zoneATester zone à tester avec this
     * @return vrai si les zones entrent en collision
     */
    public boolean collision(Zone zoneATester) {
        /* récupération des deux coordonnées de départ des deux zones */
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
     * Change l'état touche de la coordonnée cible 
     * @param coordCoup coordonnée du coup
     * @return vrai si un bateau est touché
     */
    public boolean coup(Coordonnee coordCoup) {
        /* récupération de la case à modifier */
        Coordonnee aModifier = this.getZone().get(coordCoup.getY() 
                                                  * this.getLongueur() 
                                                  + coordCoup.getX());
        aModifier.setTouche(true);
        
        return aModifier.isContientBateau();
    }
    
    
    
    
    /**
     * Placement aléatoire des bateau dans la zone de jeu
     * @param flotteAPlacer flotte contenant les bateaux à placer
     */
    public void placementAleatoireZone(Flotte flotteAPlacer) {
        int compteurBouclage;   // nombre de coûts pour placer un bateau    
        int nombreAleatoire;    // nombre aléatoire qui donne l'indice de la 
                                // caseXY dans l'arrayList
        
        boolean bateauVertical; // vrai si le bateau est à la vertical
        boolean placementOk;    // vrai si placement du bateau possible
        
        Bateau aPlacer;         // bateau à placer dans la zone de jeu
        
        // TODO Verifier que tous les bateaux peuvent être placés
        
        /* Récupérer toutes les cases de la grille */
        ArrayList<Coordonnee> coordGrille = new ArrayList<Coordonnee>();
        /* Récupération de la collection de bateau */
        ArrayList<Bateau> collecBateau = flotteAPlacer.getCollectionBateau();
        
        for (Coordonnee aRecuperer : this.getZone()) {
            coordGrille.add(aRecuperer);
        }
        
        /* Récupérer la flotte du jeu */
        for (int indice = 0; indice < collecBateau.size(); indice++) {
            aPlacer = collecBateau.get(indice);
            placementOk = false;
            
            /* vertical ou non */
            nombreAleatoire = (int) (Math.random() * 100);
            bateauVertical = nombreAleatoire < 50 ? true : false;
            
            compteurBouclage = 0;
            /* Tester si le bateau rentrera */
            do {
                /* Nombre qui donne l'indice de la caseXY à aller chercher dans
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
            
            /* re-exécution des placements du bateau depuis le début */
            if (compteurBouclage == 15) {
                placementAleatoireZone(flotteAPlacer);
            }
            
            /* placement du bateau */
            aPlacer = new Bateau(getLongueur(), getLargeur(), indice, coordGrille.get(nombreAleatoire));
            
            //flotteAPlacer.ajouterBateau(aPlacer);
            
            /* Supprimer les coordonnée utilisées par le bateau créer */
            for (Coordonnee coordSuppression : aPlacer.getAbordage().getZone()) {
                coordGrille.remove(coordSuppression);
            }
        }
    }
   
    
    
    
    /**
     * @param index 
     * @return la référence de la coodonnée demandé
     */
    public Coordonnee getCoordonneeZone(int index) {
        return zone.get(index);
    }
    
    /**
     * Méthode créant une zone à partir de coordonée
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
     * Determine si 2 point sont cote à cote avec leur coordonée 
     * @param pointA 
     * @param pointB  
     * @return un boolean à true si les points sont à cotés 
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
     * vérifie si une zone est un point ne sont pas en colision
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
     * Méthode permetant de dtecter si un coup est non pertinent en renvoyant un
     * boolean à true si le coup est non pertinent 
     * @param xCoup la coordonnée x du coup
     * @param yCoup la coordonnée y du coup
     * @param zoneJeux zone de jeux 
     * @param flotteBateau flotte de la partie
     * @return un boolean à true si le coup est non pertinent
     */
    public static boolean coupNonPertinent(int xCoup,int yCoup, Zone zoneJeux, Flotte flotteBateau) {
        boolean coupNonPertinent = false;
        int index,
        longueurZone;
        ArrayList<Bateau> flotte;


        flotte=flotteBateau.getCollectionBateau();
        longueurZone = zoneJeux.getLongueur();
        index = yCoup * longueurZone + xCoup;

        /* detection si le coup à déjà été joué */
        if (zoneJeux.getCoordonneeZone(index).isTouche()) {
            return true;// on évite de passer dans les autres boucles
        }


        /*
         * test avec des bateau touchés mini 2 element touchés du bateau mais 
         * pas coulé
         */

        /*
         * on créer 2 zones distinct autour du point l'une englobant l'ensemble
         * des point à 1 case du point et une autre englobant tout les point à 2
         * de distance du point 
         */
        Zone zone1 = zoneJeux.zoneAutourPoint(xCoup, yCoup, 1);
        Zone zone2 = zoneJeux.zoneAutourPoint(xCoup, yCoup, 2);

        // on selectionne tous les points touché avec un bateau sur eux non coulé de la zone 1
        for (int index1=0; index1<zone1.getZone().size(); index1 ++){
            for (int index2=0; index2<zone2.getZone().size() &&
                    zone1.getCoordonneeZone(index1).isCoule(); index2 ++) {

                //on selectionne tous les points touché avec un bateau sur eux non coulé de la zone 2 
                if (zone2.getCoordonneeZone(index2).isCoule() &&
                        zone2.getCoordonneeZone(index2)
                        != zone1.getCoordonneeZone(index1)) {

                    //on regarde si un élement de la zone 1 et à coté de la zone 2
                    if (estProche(zone2.getCoordonneeZone(index2),
                            zone1.getCoordonneeZone(index1))) {
                        coupNonPertinent = true ;
                    }
                }
            }
        }

        /* detection si le coup est impossible en raison des
         * zone anti Abordage avec de bateau coulé
         */
        for (int indexBateau=0; indexBateau<flotte.size() ; indexBateau++) {
            if (flotte.get(indexBateau).isCoule()) { 
                // on recupére la zone d'abordage du bateau            
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
     * Méthode d'aide de jeux, cette méthode conseillera le joueur sur un des
     * meilleurs coup à faire
     * @param zoneJeux zone de jeux de la partie
     * @param flotteJeux flotte présente dans la partie
     * @return un tableau de int conenant les coordonnee de la case à tirer sous 
     * la forme x,y
     */
    /*public static int[] meilleurCoup(Zone zoneJeux, Flotte flotteJeux) {
        int[] coordonneeXY = {-1,-1};
        
        // récupération de la flotte
        ArrayList<Bateau> flotteRecupere = flotteJeux.getCollectionBateau();
        
        //on regarde si une case de la zone est touché
        for (int index= 0 ;index< zoneJeux.getZone().size() ; index++) {
            if (zoneJeux.getCoordonneeZone(index).isCoule() ) {
                //récupérer tous les bateaux

                for (Bateau bateauRecuperer: flotteRecupere) {
                    if (bateauRecuperer.ge)
                }
                
                // regarder si un bateau a plus de 2 cases touchés
                   // si oui jouer dessus
                   // donner le prochain cout à jouer dans la direction 
                   // /!\ vérifier qu'une case ne bloque + CASE jouée
                   //       0XXX---
                
                // regarder si un bateau a plus de 1 cases jouée
                   // Si oui, regarder les cases autour si elle sont jouées
                
                
                // donner une direction aléatoire à l'utilisateur pertinante
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
