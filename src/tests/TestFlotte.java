/*
 * TestForme.java                                                  16 mai 2019
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
public class TestFlotte {

    /** TODO commenter le rôle du champ (attribut, rôle associatif...) */
    private static Zone zoneJeu = new Zone();
    
    /**
     * Tests unitaires de trierFlotte par ordre decroissant
     */
    public static void testTrierFlotte() {
        Coordonnee coordDepart = new Coordonnee(1, 1);
        
        Flotte aTrier = new Flotte();
        
        Bateau b1 = new Bateau(5);
        Bateau b2 = new Bateau(8);
        Bateau b3 = new Bateau(2);
        Bateau b4 = new Bateau(3);
        
        aTrier.ajouterBateau(b1);
        aTrier.ajouterBateau(b2);
        aTrier.ajouterBateau(b3);
        aTrier.ajouterBateau(b4);
        
       
        aTrier.trierFlotte();
        
        for (Bateau bateau : aTrier.getCollectionBateau()) {
            System.out.println(bateau.toString());
        }
    }
    /**
     * TODO commenter le rôle de cette méthode
     * @param args
     */
    public static void main(String[] args) {
        //testTrierFlotte();
        
        Zone zoneJeu = new Zone();
        Flotte marine = new Flotte();
        
        Bateau essai = new Bateau(2);
        Bateau essai2 = new Bateau(3);
        Bateau essai3 = new Bateau(1);
        
        marine.ajouterBateau(essai);
        marine.ajouterBateau(essai2);
        marine.ajouterBateau(essai3);
        
        marine.placementAleatoireBateau(zoneJeu);
        
        zoneJeu.afficherZone();
       
    }

}
