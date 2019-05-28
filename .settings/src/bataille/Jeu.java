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
    public static ArrayList<Coordonnee> coupJoue = new ArrayList<Coordonnee>();

    /** Flotte de l'ordinateur */
    public static Flotte flotteOrdi = new Flotte();
    
    /** Zone de jeu de l'ordinateur */
    public static Zone zoneOrdi = new Zone();
    
    
    
    /**
     * Initialise le jeu
     */
    public static void init() {
        // TODO auto
        Bateau test0 = new Bateau(4);
        Bateau test1 = new Bateau(5);
        Bateau test2 = new Bateau(3);
        
        flotteOrdi.ajouterBateau(test0);
        flotteOrdi.ajouterBateau(test1);
        flotteOrdi.ajouterBateau(test2);
        
        flotteOrdi.placementBateauAlea(zoneOrdi);
    }
    
    /**
     * Joue tant qu'on a pas gagné
     */
    public static void jouer() {
        while (flotteOrdi.getBateauRestant() != 0) {
            zoneOrdi.afficherZone();
            if (zoneOrdi.coup()) {
                System.out.println("Touché");
            } else {
                System.out.println("plouf");
            }
        }
    }
    
    /**
     * Lancement de l'application
     * @param args inutilisé
     */
    public static void main(String[] args) {
        init();
        jouer();
    }
}
