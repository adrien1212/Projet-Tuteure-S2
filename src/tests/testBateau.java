/*
 * testBateau.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package tests;

import bataille.Bateau;
import bataille.Coordonnee;
import bataille.Flotte;
import bataille.Zone;

/**
 * Tests unitaires de Bateaux
 * @author Adrien
 *
 */
public class testBateau {

    /** Zone de jeu par default */
    public static Zone zoneJeu = new Zone();
        
    /** Coordonnées de départ de la zone (ici une zone est un bateau) */
    public static Coordonnee[] coordonneeDepart =  {
            new Coordonnee(0, 0),
            new Coordonnee(5, 5),
            new Coordonnee(1, 8),
            new Coordonnee(9, 9),
    };
    
    /** Coordonnées d'arrivé la zone (ici une zone est un bateau) */
    public static Coordonnee[] coordonneeArrivees =  {
            new Coordonnee(0, 4),
            new Coordonnee(7, 5),
            new Coordonnee(4, 8),
            new Coordonnee(9, 9),
    };
    
    /** Taille des zones */
    public static int[] tailleZone = {
            5, 3, 4, 1
    };
    
    /** Direction de la zone */
    public static boolean[] zoneVerticale = {
            true, false, false, false
    };
    
    
    
    /**
     * tests unitaire de CreerZoneAbordage
     */
    public static void testCreerZoneAbordage() {
        int nbEchecs; // nombre d'echecs du test
        
        Zone zoneJeu = new Zone();   
        
        /* Coordonnée de la zone (avec abordage)*/
        Coordonnee[] coordonneeDepartAttendues =  {
                new Coordonnee(0, 0),
                new Coordonnee(4, 4),
                new Coordonnee(0, 7),
                new Coordonnee(8, 8),
        };
        
        Coordonnee[] coordonneeArriveesAttendues =  {
                new Coordonnee(2, 6),
                new Coordonnee(9, 7),
                new Coordonnee(6, 9),
                new Coordonnee(9, 9),
        };
        
        nbEchecs = 0;
        for (int i = 0; i < coordonneeDepart.length; i++) {
            
            Bateau bateau = new Bateau(coordonneeDepart[i], coordonneeArrivees[i], 0, zoneJeu);
            bateau.creerZoneAbordage(zoneJeu);
            
            // vérifier si coordonnées égales
            if (!(bateau.getZoneContenu().get(0).getCoordDepart()).coordonneesEgales(coordonneeDepartAttendues[i]) ||
                !(bateau.getZoneContenu().get(0).getCoordArrivee()).coordonneesEgales(coordonneeArriveesAttendues[i])) {
                nbEchecs++;
            } 
            
        }
        System.out.println(nbEchecs);
    }
    
    
    /**
     * Test de la méthode unitaire calculCoordArrivee
     */
    public static void testCalculCoordArrivee() {
        int nbEchecs = 0; // comtpe le nombre d'echecs
        
        System.out.println("*** Test de CalculCoordArrivee ***");
        Coordonnee coordArriveTrouvee;
        for (int i = 0; i < coordonneeDepart.length; i++) {
            coordArriveTrouvee = Bateau.calculCoordArrivee(coordonneeDepart[i], tailleZone[i], zoneVerticale[i]);
            
            /* Comparer la coordonnée trouvé avec la coordonnée attendue*/
            if(!coordArriveTrouvee.coordonneesEgales(coordonneeArrivees[i])) {
                nbEchecs++;
            }
        }
        System.out.println("Nombre d'echecs du calcul des coords d'arrivée = " + nbEchecs);
    }



    /**
     * Test unitaire de PlacerBateau
     * 
     */
    public static void testPlacerBateau() {
        int nbEchecsTest1; 
        int nbEchecsTest2;
        
        /* Direction du bateau : true si verticale*/
        boolean[] verticale = {
            true, true, true, true,
        };
        
        /* Résultat du placement des bateau*/
        boolean[] resultatsAttendus = {
             true, false, false, false     
        };
        
        /*
         * 1er test, on test si le bateau n'est pas en dehors de la zone
         */
        System.out.println("*** PlacerBateau Test1 ***");
        nbEchecsTest1 = 0;
        for (int i = 0; i < coordonneeDepart.length; i++) {
            Bateau bAPlacer = new Bateau(5);
            
            if(bAPlacer.placerBateau(coordonneeDepart[i], i, verticale[i], 
               zoneJeu) != resultatsAttendus[i]) {
                nbEchecsTest1++;
            }
        }
        System.out.println("Nombre d'echecs test 1 - placerBateau = " + 
                           nbEchecsTest1);

        /* 
         * 2ème test, on place les bateaux en fonction d'un autre bateau déjà placé
         * et on vérifie si le placement est possible ou non
         */
        System.out.println("*** PlacerBateau Test2 *** ");
        
        /* Mise en place de la zone de jeu*/
        Bateau bateauPlace = new Bateau(new Coordonnee(4, 5), new Coordonnee(7, 5), 
                                        0, zoneJeu);
        
        zoneJeu.ajouterCoordonnee(bateauPlace); // ajout du bateau sur la zone de jeu

        
        Coordonnee[] coordTest2Depart = {
                new Coordonnee(5, 8),
                new Coordonnee(5, 3),
                new Coordonnee(5, 3),
                new Coordonnee(3, 3),
                new Coordonnee(3, 3),
        };
         
        int[] tailleBateau = {
                4, 3, 4, 4, 3
        };
        
        boolean[] verticale2 = {
            false, true, true, false, true  
        };
        
        for (int i = 0; i < coordTest2Depart.length; i++) {
            Bateau bAPlacer2 = new Bateau(tailleBateau[i]);
            System.out.println(bAPlacer2.placerBateau(coordTest2Depart[i], i, verticale2[i], zoneJeu));
            
            System.out.println(bAPlacer2.toString());
        }
        
        
    }
    
    
    /**
     * Lancement des tests
     * @param args
     */
    public static void main(String[] args) {
        //testCreerZoneAbordage();
        //testCalculCoordArrivee();
        testPlacerBateau();
        
        

    }

}
