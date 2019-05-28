/*
 * Jeu.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;
import java.util.Scanner;

import outil.OutilSaisie;

/**
 * TODO commenter les responsabilités de cette classe
 * @author Groupe projet
 */
public class Jeu {
        
    /** Coup joué par l'utilisateur */
    public static ArrayList<Coordonnee> coupJoueur = new ArrayList();
    
    /** Coup joué par l'utilisateur 2 */
    public static ArrayList<Coordonnee> coupOrdi = new ArrayList();
    
    /** Flotte du joueur */
    public static Flotte flotteJoueur = new Flotte();
    
    /** Flotte de l'ordinateur */
    public static Flotte flotteOrdi = new Flotte();
    
    /** Zone de jeu de l'ordinateur */
    public static Zone zoneOrdi = new Zone();
    
    
    /**
     * Initialise le jeu
     */
    public static void init() {
        Bateau test0 = new Bateau(4);
        Bateau test1 = new Bateau(5);
        Bateau test2 = new Bateau(3);
        Bateau test3 = new Bateau(3);
       
        flotteOrdi.ajouterBateau(test0);
        flotteOrdi.ajouterBateau(test1);
        flotteOrdi.ajouterBateau(test2);
        flotteOrdi.ajouterBateau(test3);
        
        flotteJoueur = flotteOrdi;
    }
    
    
    
    /**
     * Coup joué par l'ordinateur
     * @return le coup joué par l'ordinateur 
     */
    public static Coordonnee coupOrdi() {
        Coordonnee coup; // coup joué
        int x; // abscisse du coup
        int y; // ordonnée du coup
        
        boolean present; // indique si le coup à déja etait joué
        
        do {
            x = (int) (Math.random() * Zone.tailleDefaut);
            y = (int) (Math.random() * Zone.tailleDefaut);
            coup = new Coordonnee(x, y);
            
            present = coup.coupNonPertinent(false, flotteOrdi);
        } while (present);
        
        coupOrdi.add(coup); // ajout du coup à la collection
        return coup;
    }
    
    /**
     * Joue contre l'ordinateur
     */
    public static void jouer() {
        boolean placement; // si le placement est possible
        placement = flotteOrdi.placementBateauAlea(zoneOrdi);
        
        boolean fin;      // indicateur si la partie est fini
        int indiceBateau; // indice du bateau à la case touché
        char reponse;     // réponse de l'utilisateur
        
        if (!placement) {
            System.out.println("Placement impossible");
        }
        
        fin = false;
        while (!fin && placement) {
            
            /* tir du joueur */
            do {
                zoneOrdi.afficherZone();
                indiceBateau = zoneOrdi.coup(coupJoueur);
                if (indiceBateau == -1) {
                    System.out.println("plouf");
                } else if (flotteOrdi.getCollectionBateau().get(indiceBateau).bateauCoule()) {
                    System.out.println("Coulé");
                    flotteOrdi.bateauCoule();
                } else if (indiceBateau != -1) {
                    System.out.println("Touché");
                } else {
                    System.out.println("plouf");
                }
                
                fin = flotteOrdi.getBateauRestant() == 0 
                   || flotteJoueur.getBateauRestant() == 0;
            } while (!fin && indiceBateau != -1);
            
            /* tir de l'ordi */
            reponse = 't';
            while (!fin && reponse != 'p') {
                System.out.println(coupOrdi()); // refaire le coup ordi
                reponse = OutilSaisie.saisieReponse();
            }
        }
        
        if (flotteOrdi.getBateauRestant() == 0) {
            System.out.println("\n----- GAGNE -----");
        } else {
            System.out.println("\nxxxxx PERDU xxxxx");
        }
    }
    
    /**
     * Affichage du menu principal de l'application
     */
    public static void menuPrincipale() {
        boolean ok; // indicateur de saisie valide
        int choix; // choix du joueur
        
        Scanner entree = new Scanner(System.in);
        
        System.out.println("****************************************** \n"
                + "             Bataille Navale            \n"
                + "******************************************\n\n"
                
                + "1- Jouer \n"
                + "2- Aide  \n"
                + "3- Quitter\n\n"
                + "******************************************");
        
        ok = true;
        choix = -1;
        do {
            if (entree.hasNextInt()) {
                choix = entree.nextInt();
            }
            
            switch (choix) {
            case 1:
                menuJouer();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("Choix incorrect !");
                ok = false;
            }
            entree.nextLine();
        } while(!ok);
    }
    
    
    /**
     * Menu pour choisir comment jouer
     */
    public static void menuJouer() {
        boolean ok; // indicateur de saisie valide
        int choix; // choix du joueur
        
        Scanner entree = new Scanner(System.in);
        
        System.out.println("****************************************** \n"
                + "             Jouer            \n"
                + "******************************************\n\n"
                
                + "1- Jouer tout seul\n"
                + "2- Joueur contre l'ordinateur  \n"
                + "3- Retourner au menu principal\n\n"
                
                + "******************************************");
        
        ok = true;
        choix = -1;
        do {
            if (entree.hasNextInt()) {
                choix = entree.nextInt();
            }
            
            switch (choix) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                menuPrincipale();
                break;
            default:
                System.out.println("Choix incorrect !");
                ok = false;
            }
            entree.nextLine();
        } while(!ok);
    }
    
    
    /**
     * Lancement de l'application
     * @param args inutilisé
     */
    public static void main(String[] args) {
        
        
       // menuPrincipale();
            
       
        
        
        init();
        jouer();
    }
}
