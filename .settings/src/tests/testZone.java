/*
 * testZone.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package tests;


import java.util.ArrayList;

import bataille.Bateau;
import bataille.Coordonnee;
import bataille.Zone;

/**
 * Test unitaire de Zone
 * @author Adrien
 *
 */
public class testZone {

    /** Zone de jeu par default pour les tests */
    private static Zone zoneJeu = new Zone();
    
    /** Coordonnées de zone de départ */
    private static  Coordonnee[] CoordonneeDepart =  {
            new Coordonnee(0, 0),
            new Coordonnee(-0, -0),
            new Coordonnee(3, 6),
            new Coordonnee(3, 8),
            new Coordonnee(1, 1),
            new Coordonnee(1, 1),
            new Coordonnee(0, 8),
            new Coordonnee(1, 9),
            new Coordonnee(2, 0),
            new Coordonnee(9,9)      // mettre taille defaut
    };
    
    /** Bateaux */
    private static Bateau[] bateauPourTest = {
        new Bateau(new Coordonnee(0, 0), new Coordonnee(3, 0), 0, zoneJeu),
        new Bateau(new Coordonnee(8, 7), new Coordonnee(8, 7), 1, zoneJeu),
        new Bateau(new Coordonnee(3, 2), new Coordonnee(3, 5), 2, zoneJeu),
    };
    
//    /**
//     * Tester si une zone se crée correctement
//     * On vérifie si la coordonnée d'arrivé de la zone est correcte  
//     */
//    public static void testCreationZone() {
//        int nbEchecs;
//        boolean coordArriveeValide; // indique si la coordonnée d'arrivée est valide
//
//        /* Taille horizontale pour la zone de test */
//        int[] tailleHorizontale = {
//            0, 8, -2, 6, 100, 9999999, -9654, -0,    -2, 1
//        };
//        
//        /* Taille verticale pour la zone de test */
//        int [] tailleVerticale = {
//            0, -9, 8, 6, 7,    100,     6565, -9896, -0, 1
//        };
//        
//        /* Coordonnée horizontale (abscisse) attendu pour la zone de test
//         * si -1 alors aucune coordonnée attendue
//         */
//        int [] coordArriveXAttendue = {
//            0, -1, 1, 9, -1, -1, -1, -1, 0, 10  
//        };
//        
//        /* Coordonnée verticale (ordonnée) attendu pour la zone de test
//         * si -1 alors aucune coordonnée attendue
//         */
//        int [] coordArriveYAttendue = {
//            0, -1, 14, 14, -1, -1, -1, -1, 0, 10
//        };
//        
//        /* Résultat attendu pour des tests */
//        boolean[] resultatAttendu = {
//            true, false, true, true, false, false, false, false, true, true
//        };
//        
//        nbEchecs = 0;
//        System.out.println("*** Test de création de zone ***");
//        for (int i = 0; i < CoordonneeDepart.length; i++) {
//            try {
//                Zone zoneATester = new Zone(CoordonneeDepart[i], tailleHorizontale[i], tailleVerticale[i]);
//                coordArriveeValide = true;
//                if(coordArriveeValide) {
//                    if (zoneATester.getCoordArrivee().getX() != coordArriveXAttendue[i] 
//                            || zoneATester.getCoordArrivee().getY() != coordArriveYAttendue[i]) {
//                        nbEchecs++;
//                    }
//                }
//            } catch (IllegalArgumentException coordonneesNegative) {
//                coordArriveeValide = false; 
//            }
//  
//                 
//        }
//        System.out.println("Nb echecs Creation Zone: " + nbEchecs);
//    }
//    
//    
//    /**
//     * Test unitaire de la méthode horsZone
//     * @param zoneContenant zone contenant la zone
//     */
//    public static void testHorsZone(Zone zoneContenant) {
//        int nbEchecs;  // nombre d'echecs des tests
//        
//        /* Taille horizontale pour la zone de test */
//        int[] tailleHorizontale = {
//                0, 3, 3, 0, 10, 11, 20, 20
//            };
//          
//        /* Taille verticale pour la zone de test */
//        int [] tailleVerticale = {
//                0, 3, 0, 3, 10, 11,  0,  0
//            };
//
//        /* Résultat attendu pour des tests */
//        boolean[][] resulatAttendu = {
//                {false, false, false, false, false, true, true, true},
//                {false, false, false, false, true, true, true, true }
//        };
//
//        nbEchecs = 0;
//        System.out.println("*** Test de horsZone ***");
//        for (int i = 0 ; i < tailleHorizontale.length; i++) {
//            Zone zoneATester = new Zone(CoordonneeDepart[0], tailleHorizontale[i],
//                                     tailleVerticale[i]);
//
//            if (zoneATester.horsZone(zoneJeu) != resulatAttendu[0][i]) {
//                nbEchecs++;
//            }
//        }
//        
//        for (int i = 0 ; i < tailleHorizontale.length; i++) {
//            Zone zoneATester = new Zone(CoordonneeDepart[2], tailleHorizontale[i],
//                                     tailleVerticale[i]);
//
//            if (zoneATester.horsZone(zoneJeu) != resulatAttendu[1][i]) {
//                nbEchecs++;
//            }
//        }
//        System.out.println("Nb echecs horsZone " + nbEchecs);
//  
//    }
//    
//    
//    /**
//     * Test unitaire de ajouterCoordonnee
//     */
//    public static void testAjouterCoordonnee() {
//        ArrayList<Coordonnee> zoneCoord2; // liste où seront ajouter les coordonnées
//        
//        /* Coordonnées à ajouter à la liste */
//        
//        
//    }
//    
//    
//    /** 
//     * Test unitaire de ajouterCoordonnee(Bateau)
//     */
//    public static void testAjouterCoordonneeAvecBateau() {
//        ArrayList<Coordonnee> zoneCoord; // liste où seront ajouter les coordonnées
//        
//        for (int i = 0; i < bateauPourTest.length; i++) {
//            zoneCoord.ajouterCoordonnee(bateauPourTest[i].getZoneContenu());
//        }
//    }
    
   
    /**
     * Test visuel de getZoneCoord
     * Retour valide = coordonnée bateau + coordonnée zoneAbordage
     * si la zone est un bateau
     */
    public static void testGetZoneCoord() {
        for (int i = 0; i < bateauPourTest.length; i++) {
            System.out.println(bateauPourTest[i].getZoneCoord());
            System.out.println(bateauPourTest[i].getZoneContenu().get(0));
        }
    }
    
    /**
     * Test unitaire de collision
     */
    public static void testCollision() {
        int nbEchecs;
        
        /* Coordonnée de départ des bateaux*/
        Coordonnee[] coordDepart = {
                new Coordonnee(5, 8),
                new Coordonnee(5, 3),
                new Coordonnee(5, 3),
                new Coordonnee(3, 3),
                new Coordonnee(3, 3),
        };
        
        /* Coordonnée d'arrive des bateaux */
        Coordonnee[] coordArrive = {
                new Coordonnee(7, 8),
                new Coordonnee(5, 6),
                new Coordonnee(5, 7),
                new Coordonnee(6, 3),
                new Coordonnee(3, 5),
        };


        /* réculats attendus */
        boolean[] resultatAttendu = {
                false, true, true, true, true
        };
        
        /* Placement d'un bateau de la zone de jeu*/
        Bateau bateauPlace = new Bateau(new Coordonnee(4, 4), new Coordonnee(7, 4), 
                0, zoneJeu);

        zoneJeu.ajouterCoordonnee(bateauPlace); // ajout du bateau sur la zone de jeu


        nbEchecs = 0;
        /* On regarde si les nouveaux bateaux touchent le bateau déjà placé*/
        for (int i = 0; i < coordDepart.length; i++) {
            Bateau bAPlacer2 = new Bateau(coordDepart[i], coordArrive[i], i+1, zoneJeu);
            
            if (bAPlacer2.collision(bateauPlace) != resultatAttendu[i]) {
                nbEchecs++;
            }
        }
        System.out.println("Nombre d'echecs de Collision : " + nbEchecs);

    }
    
    
    
    /**
     * Lancement des tests
     * @param args
     */
    public static void main(String[] args) {
        //testCreationZone();
        //testHorsZone(zoneJeu);
        //zoneJeu.afficherZone();
        //testAjouterCoordonnee();
        //testGetZoneCoord();
        testCollision();
    }

}
