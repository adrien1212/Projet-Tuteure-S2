/*
 * testCoordonnee.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package tests;

import bataille.Coordonnee;

/**
 * Classe test de coordonnee
 * @author MZ
 *
 */
public class testCoordonnee {

    
    /** tableau contenant le jeux de test des coordonnée fausse */
    final static int[][] tableauTestFaux = {{-50,0},{0,-43},{-22,-45}};
    
    /** tableau contenant le jeux de test des coordonnée vraie */
    final static int[][] tableauTestVraie = {{0,0},{0,-0},{3,5}};
    
    /**
     * méthodes testant le constructeur et les getters de la classe Coordonnée
     */
    private static void testConstructeurGetters() {
        int compteurValideTableauFaux =3;
        int compteurValideTableauVraie =0;
        try {
            for( int index =0 ; index < tableauTestFaux.length ; index ++) {
                @SuppressWarnings("unused")
                Coordonnee test = new Coordonnee(tableauTestFaux[index][0],
                                                 tableauTestFaux[index][1]);
                compteurValideTableauFaux --;
               // System.out.println(compteurValideTableauVraie);
            }
        }catch (IllegalArgumentException exception) {
           
        }
        try {
            for( int index =0 ; index < tableauTestVraie.length ; index ++) {
                @SuppressWarnings("unused")
                Coordonnee test = new Coordonnee(tableauTestVraie[index][0],
                                                 tableauTestVraie[index][1]);
                compteurValideTableauVraie ++;
            }
        }catch (IllegalArgumentException exception) {
            
        }
        if (compteurValideTableauFaux == 3) {
            System.out.println("tous les test avec des valeur fausse sont OK");
        }else {
            System.out.println("tous les test avec des valeur fausse ne sont pas OK");
        }
        
        if (compteurValideTableauVraie == 3) {
            System.out.println("tous les test avec des valeur Vraie sont OK");
        }else {
            System.out.println("tous les test avec des valeur Vraie ne sont pas OK");
        }
        
        Coordonnee testGet = new Coordonnee(2,3);
        if (testGet.getX()==2) {
            System.out.println("Get X ok");
        }else {
            System.out.println("Get X Nok");
        }
        if (testGet.getY()==3) {
            System.out.println("Get Y ok");
        }else {
            System.out.println("Get Y Nok");
        }
       
    }
    /**
     * Lancement des tests
     * @param args
     */
    public static void main(String[] args) {
        testConstructeurGetters();
    }
}
