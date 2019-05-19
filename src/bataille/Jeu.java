/*
 * Jeu.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * TODO commenter les responsabilités de cette classe
 * @author Groupe projet
 */
public class Jeu {
    
    /** Coup joué par l'utilisateur */
    public static ArrayList<Coordonnee> coupJoue;

    /**
     * Lancement de l'application
     * @param args inutilisé
     */
    public static void main(String[] args) {
        
        Zone zoneJeu = new Zone();
        Flotte marine = new Flotte();
        
        Bateau test0 = new Bateau(4);
        Bateau test1 = new Bateau(5);
        Bateau test2 = new Bateau(3);
        
        //test0 = test0.constuireBateau(new Coordonnee(0, 0), 0, true, zoneJeu);
        //System.out.println(test0);
        
        marine.ajouterBateau(test0);
        marine.ajouterBateau(test1);
        marine.ajouterBateau(test2);
        
        System.out.println("marine :\n");
        for (Bateau aAfficher : marine.getCollectionBateau()) {
            System.out.println(aAfficher);
        }
        System.out.println("\n");
        
        marine.placementBateauAlea(zoneJeu);
        
        System.out.println("marine :\n");
        for (Bateau aAfficher : marine.getCollectionBateau()) {
            System.out.println(aAfficher);
        }
        System.out.println("\n");
            
        for (Coordonnee coord : zoneJeu.getZoneCoord()) {
            System.out.print(coord + "  ");
        }
        
        zoneJeu.afficherZone();
    }
}
