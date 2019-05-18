/*
 * Jeu.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

/**
 * TODO commenter les responsabilités de cette classe
 * @author Groupe projet
 */
public class Jeu {

    /**
     * Lancement de l'application
     * @param args inutilisé
     */
    public static void main(String[] args) {
        
        Zone zoneJeu = new Zone();
        Flotte marine = new Flotte();
        
        Bateau essai = new Bateau(new Coordonnee(1, 1), new Coordonnee(7, 1), 0, zoneJeu);
        Bateau essai2 = new Bateau(new Coordonnee(0, 2), new Coordonnee(0, 5), 1, zoneJeu);
        Bateau essai3 = new Bateau(new Coordonnee(9, 8), new Coordonnee(9, 9), 2, zoneJeu);
       
        marine.ajouterBateau(essai);
        marine.ajouterBateau(essai2);
        
        System.out.print("ZONE JEU : " + zoneJeu.toString());
        
        zoneJeu.ajouterFlotte(marine);
        zoneJeu.afficherZone();
    }
}
