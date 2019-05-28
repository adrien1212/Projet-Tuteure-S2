/*
 * OutilSaisie.java                                                  6 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package outil;

import java.util.Scanner;
import java.util.regex.Pattern;

import bataille.Coordonnee;
import bataille.Zone;

/**
 * TODO commenter les responsabilit�s de cette classe
 * 
 * @author Pc
 *
 */
public class OutilSaisie {

    /** Lecture de l'entree standard */
    private static Scanner entree = new Scanner(System.in);

    /**
     * Demande un entier � l'utilisateur
     * @param message message � afficher lors de la saisie
     * @return entierSaisi l'entier saisi par l'utilisateur
     */
    public static int saisieEntier(String message) {
        int entierSaisi = 0;
        boolean ok;
        
        do {
            System.out.print(message);
            ok = entree.hasNextInt();
            if (ok) {
                entierSaisi = entree.nextInt();
                if (entierSaisi < 0 && Zone.tailleDefaut < entierSaisi) {
                    System.out.println("L'entier saisi n'est pas valide");
                    ok = false;
                }
            } else {
                System.out.println("Vous n'avez pas saisi un entier");
            }
            entree.nextLine();
        } while (!ok);
        
        return entierSaisi;
    }

    /**
     * Demande une r�ponse � une question ferm�
     * @param message message � afficher lors de la saisie
     * @return la r�ponse de l'utilisateur
     */
    public static boolean saisieBoolean(String message) {
        boolean nOk;     // indicateur de saisie valide
        
        String saisie;   // saisie de l'utilisateur
        boolean reponse; // r�ponse de l'utilisateur
        
        nOk = true;
        reponse = false;
        do {
            System.out.print(message);
            saisie = entree.nextLine().toUpperCase();
            if (saisie.length() > 0) {
                if (saisie.charAt(0) == 'O') {
                    reponse = true;
                } else if (saisie.charAt(0) == 'N') {
                    reponse = false;
                } else {
                    System.out.println("Caract�re invalide");
                }
            }
        } while (nOk);
        
        return reponse;
    }
    
    /**
     * Demande � l'utilisateur une coordonn�e sous la forme (A1)
     * @return une nouvelle case au coordonn�es saisie
     */
    public static Coordonnee saisieCoordonnee() {
        boolean ok;
        String reponse;
        
        int x,
            y;
        
        x = y = -1;
        do {
            System.out.print("Saisir une coordonn�e (ex : A1) : ");
            reponse = entree.nextLine();
            ok = Pattern.matches("[a-zA-Z]{1}[0-9]{1,2}", reponse);
            
            if (!ok) {
                System.out.println("Saisie invalide");
            } else {
                x = conversion(reponse.toUpperCase().charAt(0));
                y = Integer.parseInt(reponse.substring(1, reponse.length()));
                if (x < 0 || Zone.tailleDefaut-1 < x 
                 || y < 0 || Zone.tailleDefaut < y) {
                    System.out.println("Coordonnees invalide");
                    ok = false;
                }
            }
        } while (!ok);
        
        return new Coordonnee(x, y-1);
    }
    
    /**
     * Convertit un caract�re en entier
     * @param aConvertir
     * @return le caract�re associer � l'entier
     */
    public static int conversion(char aConvertir) {
        return (int) (aConvertir - 'A');
    }
}
