/*
 * TestCase.java                                                  30 avr. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package testsbataille;

import bataille.Coordonnee;

/**
 * Test unitaire de la classe Coordonnee
 * @author Groupe
 *
 */
public class TestCoordonnee {

    /**
     * Test des coordonnées seuls
     */
    public static void TestDesCoordonnees() {

        int NbEchecs; // nombre d'echecs des tests
        
        boolean ok;   // indicateur de résultat valide

        /* jeux de test */
        int[][] coordonnees = new int[][] { 
            { 0, 0 }, 
            { 26, 26 }, 
            { -0, -0 }, 
            { 7, 3 }, 
            { 3, 7 }, 
            { 100, 6 },
            { 6, 100 }, 
            { -12, 8 }, 
            { 8, -12 },

        };

        /* Résultat des tests attendus */
        boolean[] resultatsAttendus = new boolean[] { true, true, true, true, 
                                                      true, true, true, true, 
                                                      true };

        NbEchecs = 0;
        for (int i = 0; i < coordonnees.length; i++) {
            ok = true;
            try {
                Coordonnee aTester = new Coordonnee(coordonnees[i][0], coordonnees[i][1]);
            } catch (IllegalArgumentException coordonneesInvalide) {
                ok = false;
            }

            if (ok != resultatsAttendus[i]) {
                NbEchecs++;
                System.out.println(i);
            }

        }
        System.out.println("Nombre d'eches : " + NbEchecs);

    }

    /**
     * Vérifier que la classe Case est valide
     * 
     * @param args
     */
    public static void main(String[] args) {
        TestDesCoordonnees();
    }

}
