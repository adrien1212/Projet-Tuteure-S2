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
 * TODO commenter les responsabilités de cette classe
 * @author Adrien
 *
 */
public class testBateau {

    
    /**
     * tests unitaire de CreerZoneAbordage
     */
    public static void testCreerZoneAbordage() {
        int nbEchecs; // nombre d'echecs du test
        
        Zone zoneJeu = new Zone();
       
        /* Coordonnée de la zone (sans abordage)*/
        Coordonnee[] coordonneeDepart =  {
                new Coordonnee(0, 0),
                new Coordonnee(5, 5),
                new Coordonnee(1, 8),
                new Coordonnee(9, 9),
        };
        
        Coordonnee[] coordonneeArrivees =  {
                new Coordonnee(1, 5),
                new Coordonnee(8, 6),
                new Coordonnee(5, 9),
                new Coordonnee(9, 9),
        };
        
        
        
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
            
            Bateau bateau = new Bateau(coordonneeDepart[i], coordonneeArrivees[i], zoneJeu);
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
     * Lancement des tests
     * @param args
     */
    public static void main(String[] args) {
        testCreerZoneAbordage();

    }

}
