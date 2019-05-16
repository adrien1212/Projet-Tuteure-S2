/*
 * Jeu.java                                                  15 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * Lance le jeu
 * @author Groupe projet
 */
public class Jeu {

    /** Coup jou� par l'utilisateur */
    ArrayList<Coordonnee> coordJoue;
    
    /** TODO commenter le r�le du champ (attribut, r�le associatif...) */
    static Flotte flotteOrdi = new Flotte();
    /** TODO commenter le r�le du champ (attribut, r�le associatif...) */
    static Flotte test = new Flotte();
    
    /**
     * TODO commenter le r�le de cette m�thode
     */
    private static void init() {
        
        // TODO charger les catalogues
        Bateau b1 = new Bateau(7);
        Bateau b2 = new Bateau(5);
        Bateau b3 = new Bateau(4);
        Bateau b4 = new Bateau(3);
        
        flotteOrdi.ajouterBateau(b1);
        flotteOrdi.ajouterBateau(b2);
        flotteOrdi.ajouterBateau(b3);
        flotteOrdi.ajouterBateau(b4);
        
        System.out.println(flotteOrdi.toString());
        
        Zone.ZONE_JEU.placementAleatoireZone(flotteOrdi);
    }
    
    /**
     * TODO commenter le r�le de cette m�thode
     */
    private static void jouer() {
        // TODO Auto-generated method stub
        
    }

    
    
    /**
     * Lancement du jeu
     * @param args inutilis�
     */
    public static void main(String[] args) {
        Zone.ZONE_JEU.afficherZone();
        init();
        jouer();

        Zone.ZONE_JEU.afficherZone();
    }
}
