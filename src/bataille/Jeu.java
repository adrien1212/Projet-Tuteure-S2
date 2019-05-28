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
    
    /** Zone de l'ordinateur */
    public static Zone zoneOrdi;

    /** Indique si le placement est possible */
    public boolean placementOk;
    
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
        
        present = false; // TODO remove 
        
        do {
            x = (int) (Math.random() * Zone.tailleDefaut);
            y = (int) (Math.random() * Zone.tailleDefaut);
            coup = new Coordonnee(x, y);
            
            present = coup.coupNonPertinent(coupOrdi, flotteOrdi);
        } while (present);
        
        coupOrdi.add(coup); // ajout du coup à la collection
        return coup;
    }
    
    /**
     * Jeu quand on joue seul contre l'ordinateur
     */
    public static void jouer() {
        
        /** Zone de jeu de l'ordinateur */
        Zone.tailleDefaut = 16;
        zoneOrdi = new Zone();
                
        Bateau porteAvion = new Bateau(4);
        Bateau croiseur1 = new Bateau(3);
        Bateau croiseur2 = new Bateau(3);
        Bateau sousMarin1 = new Bateau(2);
        Bateau sousMarin2 = new Bateau(2);
        Bateau sousMarin3 = new Bateau(2);
        Bateau vedette1 = new Bateau(1);
        Bateau vedette2 = new Bateau(1);
        Bateau vedette3 = new Bateau(1);
        Bateau vedette4 = new Bateau(1);
        
        flotteOrdi.ajouterBateau(porteAvion);
        flotteOrdi.ajouterBateau(croiseur1);
        flotteOrdi.ajouterBateau(croiseur2);
        flotteOrdi.ajouterBateau(sousMarin1);
        flotteOrdi.ajouterBateau(sousMarin2);
        flotteOrdi.ajouterBateau(sousMarin3);
        flotteOrdi.ajouterBateau(vedette1);
        flotteOrdi.ajouterBateau(vedette2);
        flotteOrdi.ajouterBateau(vedette3);
        flotteOrdi.ajouterBateau(vedette4);
        
        flotteOrdi.afficherFlotte();
        
        boolean placement; // si le placement est possible
        placement = flotteOrdi.placementBateauAlea(zoneOrdi);
        
        if (!placement) {
            System.out.println("Placement impossible");
        }
        
        int indiceBateau; // indice du bateau à la case touché

        while (flotteOrdi.getBateauRestant() != 0 && placement) {

            /* tir du joueur */
            zoneOrdi.afficherZone();
            indiceBateau = zoneOrdi.coup(coupJoueur,flotteOrdi);
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
        }
    }
    
    /**
     * Joue contre l'ordinateur
     */
    public static void jouerOrdi() {
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
                indiceBateau = zoneOrdi.coup(coupJoueur,flotteOrdi);
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
                
                fin = flotteOrdi.getBateauRestant() == 0;
            } while (!fin && indiceBateau != -1);
            
            /* tir de l'ordi */
            reponse = 't';
            while (!fin && reponse != 'p') {
                System.out.print("\n" + coupOrdi().convertit());
                reponse = OutilSaisie.saisieReponse();
                if (reponse == 'c') {
                    flotteJoueur.bateauCoule();
                }
                fin = flotteJoueur.getBateauRestant() == 0;
            }
        }
        
        if (flotteOrdi.getBateauRestant() == 0) {
            System.out.println("\n----- GAGNE -----");
        } else if (flotteJoueur.getBateauRestant() == 0) {
            System.out.println("\nxxxxx PERDU xxxxx");
        }
    }
    
    /**
     * Affichage du menu principal de l'application
     */
    public static void menuPrincipal() {
        boolean ok; // indicateur de saisie valide
        int choix; // choix du joueur
        
        Scanner entree = new Scanner(System.in);
        
        System.out.print("\n****************************************** \n"
                + "             Bataille Navale            \n"
                + "******************************************\n\n"
                + " 1 - Jouer \n"
                + " 2 - Aide  \n"
                + " 3 - Quitter\n\n");
        
        choix = -1;
        do {
            
            System.out.print("    => ");
            if (entree.hasNextInt()) {
                choix = entree.nextInt();
            }
            
            ok = true;
            switch (choix) {
                case 1: menuJouer();
                        break;
                case 2: aide();
                        break;
                case 3: break;
                default: System.out.println("Choix incorrect !");
                         ok = false;
            }
            entree.nextLine();
        } while(!ok);
    }
    
    
    
    /**
     * Affichage de l'aide pour le joueur
     */
    public static void aide() {
        System.out.print("\n****************************************** \n"
                + "             Aide            \n"
                + "******************************************\n\n"
                
                + "1) But du jeu \n"
                + "Au début du jeu, chaque joueur place à sa guise tous les bateaux sur sa grille de façon stratégique. \n"
                + "Le but étant de compliquer au maximum la tache de son adversaire, c’est-à-dire détruire tous vos navires. \n"
                + "Bien entendu, le joueur ne voit pas la grille de son adversaire.\r\n" 
                + "Une fois tous les bateaux en jeu, la partie peut commencer.. \n"
                + "Un à un, les joueurs se tire dessus pour détruire les navires ennemis.\r\n\n" 
                + "Exemple le joueur dit a voit haute H7 correspondant à la case au croisement \n"
                + "de la lettre H et du numéro 7 sur les côtés des grilles.\r\n\n" 
                + "Si un joueur tire sur un navire ennemi, l’adversaire doit le signaler en disant « touché ». \n"
                + "Il peut pas jouer deux fois de suite et doit attendre le tour de l’autre joueur.\r\n" 
                + "Si le joueur ne touche pas de navire, l’adversaire le signale en disant « plouf  ».\r\n" 
                + "Si le navire est entièrement touché l’adversaire doit dire « coulé  ».\r\n" 
                
                
                
                );
    }
    
    
    /**
     * Menu pour choisir comment jouer
     */
    public static void menuJouer() {
        boolean ok; // indicateur de saisie valide
        int choix; // choix du joueur
        
        Scanner entree = new Scanner(System.in);
        
        System.out.print("\n****************************************** \n"
                + "             Jouer            \n"
                + "******************************************\n\n"
                
                + " 1 - Jouer tout seul\n"
                + " 2 - Joueur contre l'ordinateur  \n"
                + " 3 - Retourner au menu principal\n\n");
        
        choix = -1;
        do {
            
            System.out.print("    => ");
            if (entree.hasNextInt()) {
                choix = entree.nextInt();
            }
            
            ok = true;
            switch (choix) {
                case 1: jouer();
                        break;
                case 2: menuNiveauJeu();
                        break;
                case 3: menuPrincipal();
                        break;
                default: System.out.println("Choix incorrect !");
                         ok = false;
            }
            entree.nextLine();
        } while(!ok);
    }
    
    
    /**
     * Niveau du jeu 
     */
    public static void menuNiveauJeu() {
        boolean ok; // indicateur de saisie valide
        int choix; // choix du joueur
        
        Scanner entree = new Scanner(System.in);
        
        System.out.print("\n****************************************** \n"
                + "             Niveau            \n"
                + "******************************************\n\n"
                
                + " 1 - Facile\n"
                + " 2 - Moyen  \n"
                + " 3 - Difficile\n"
                + " 4 - Retour menu Joueur\n"
                + " 5 - Retour menu Principale\n\n");
        
        choix = -1;
        do {
            
            System.out.print("    => ");
            if (entree.hasNextInt()) {
                choix = entree.nextInt();
            }
            
            ok = true;
            switch (choix) {
                case 1: niveauFacile();
                        break;
                case 2: niveauMoyen();
                        break;
                case 3: niveauDifficile();
                        break;
                case 4: menuJouer();
                        break;
                case 5: menuPrincipal();
                        break;
                default: System.out.println("Choix incorrect !");
                         ok = false;
            }
            
            entree.nextLine();
        } while(!ok);
    }
    
    
    /**
     * Mise en place du jeu pour un niveau facile
     */
    public static void niveauFacile() {
        
        /** Zone de jeu de l'ordinateur */
        Zone.tailleDefaut = 10;
        zoneOrdi = new Zone();
        
        Bateau porteAvion = new Bateau(4);
        Bateau croiseur1 = new Bateau(3);
        Bateau croiseur2 = new Bateau(3);
        Bateau sousMarin1 = new Bateau(2);
        Bateau sousMarin2 = new Bateau(2);
       
        flotteOrdi.ajouterBateau(porteAvion);
        flotteOrdi.ajouterBateau(croiseur1);
        flotteOrdi.ajouterBateau(croiseur2);
        flotteOrdi.ajouterBateau(sousMarin1);
        flotteOrdi.ajouterBateau(sousMarin2);

        flotteJoueur.ajouterBateau(porteAvion);
        flotteJoueur.ajouterBateau(croiseur1);
        flotteJoueur.ajouterBateau(croiseur2);
        flotteJoueur.ajouterBateau(sousMarin1);
        flotteJoueur.ajouterBateau(sousMarin2);
        
        flotteOrdi.afficherFlotte();
        
        jouerOrdi();
    }
    
    /**
     * Mise en place du jeu pour un niveau moyen
     */
    public static void niveauMoyen() {
        
        /** Zone de jeu de l'ordinateur */
        Zone.tailleDefaut = 16;
        zoneOrdi = new Zone();
                
        Bateau porteAvion = new Bateau(4);
        Bateau croiseur1 = new Bateau(3);
        Bateau croiseur2 = new Bateau(3);
        Bateau sousMarin1 = new Bateau(2);
        Bateau sousMarin2 = new Bateau(2);
        Bateau sousMarin3 = new Bateau(2);
        Bateau vedette1 = new Bateau(1);
        Bateau vedette2 = new Bateau(1);
        Bateau vedette3 = new Bateau(1);
        Bateau vedette4 = new Bateau(1);
       
        flotteOrdi.ajouterBateau(porteAvion);
        flotteOrdi.ajouterBateau(croiseur1);
        flotteOrdi.ajouterBateau(croiseur2);
        flotteOrdi.ajouterBateau(sousMarin1);
        flotteOrdi.ajouterBateau(sousMarin2);
        flotteOrdi.ajouterBateau(sousMarin3);
        flotteOrdi.ajouterBateau(vedette1);
        flotteOrdi.ajouterBateau(vedette2);
        flotteOrdi.ajouterBateau(vedette3);
        flotteOrdi.ajouterBateau(vedette4);
        
        flotteOrdi.afficherFlotte();
        
        flotteJoueur.ajouterBateau(porteAvion);
        flotteJoueur.ajouterBateau(croiseur1);
        flotteJoueur.ajouterBateau(croiseur2);
        flotteJoueur.ajouterBateau(sousMarin1);
        flotteJoueur.ajouterBateau(sousMarin2);
        flotteJoueur.ajouterBateau(sousMarin3);
        flotteJoueur.ajouterBateau(vedette1);
        flotteJoueur.ajouterBateau(vedette2);
        flotteJoueur.ajouterBateau(vedette3);
        flotteJoueur.ajouterBateau(vedette4);
        
        jouerOrdi();
    }
    
    
    /**
     * Mise en place du jeu pour un niveau difficile
     */
    public static void niveauDifficile() {
        
        /** Zone de jeu de l'ordinateur */
        Zone.tailleDefaut = 22;
        zoneOrdi = new Zone();
        
        Bateau porteAvion = new Bateau(4);
        Bateau croiseur1 = new Bateau(3);
        Bateau croiseur2 = new Bateau(3);
        Bateau sousMarin1 = new Bateau(2);
        Bateau sousMarin2 = new Bateau(2);
        Bateau sousMarin3 = new Bateau(2);
        Bateau vedette1 = new Bateau(1);
        Bateau vedette2 = new Bateau(1);
        Bateau vedette3 = new Bateau(1);
        Bateau vedette4 = new Bateau(1);
       
        flotteOrdi.ajouterBateau(porteAvion);
        flotteOrdi.ajouterBateau(croiseur1);
        flotteOrdi.ajouterBateau(croiseur2);
        flotteOrdi.ajouterBateau(sousMarin1);
        flotteOrdi.ajouterBateau(sousMarin2);
        flotteOrdi.ajouterBateau(sousMarin3);
        flotteOrdi.ajouterBateau(vedette1);
        flotteOrdi.ajouterBateau(vedette2);
        flotteOrdi.ajouterBateau(vedette3);
        flotteOrdi.ajouterBateau(vedette4);
        
        flotteOrdi.afficherFlotte();
        
        flotteJoueur.ajouterBateau(porteAvion);
        flotteJoueur.ajouterBateau(croiseur1);
        flotteJoueur.ajouterBateau(croiseur2);
        flotteJoueur.ajouterBateau(sousMarin1);
        flotteJoueur.ajouterBateau(sousMarin2);
        flotteJoueur.ajouterBateau(sousMarin3);
        flotteJoueur.ajouterBateau(vedette1);
        flotteJoueur.ajouterBateau(vedette2);
        flotteJoueur.ajouterBateau(vedette3);
        flotteJoueur.ajouterBateau(vedette4);
        
        jouerOrdi();
    }
    
    
    /**
     * Lancement de l'application
     * @param args inutilisé
     */
    public static void main(String[] args) {
        menuPrincipal();
    }
}
