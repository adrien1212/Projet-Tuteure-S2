/*
 * Flotte.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;
import java.util.Random;

/**
 * TODO commenter les responsabilités de cette classe
 * @author Adrien
 *
 */

public class Flotte {

    /** Nombre de bateau dans la flotte TODO recup auto*/
    private final int NB_BATEAU = 4;



    /** Collection des bateaux composant la flotte  */
    private ArrayList<Bateau> collectionBateau;

    /** Nombre de bateau non coulé dans la flotte */
    private int bateauRestant;



    /**
     * Construit une flotte par défaut
     */
    public Flotte() {
        this.bateauRestant = NB_BATEAU;
        this.collectionBateau = new ArrayList<Bateau>();
    }

    

    /**
     * @return valeur de collectionBateau
     */
    public ArrayList<Bateau> getCollectionBateau() {
        return collectionBateau;
    }

    /**
     * @return valeur de bateauRestant
     */
    public int getBateauRestant() {
        return bateauRestant;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Flotte [collectionBateau = " + collectionBateau 
                + ", bateauRestant = " + bateauRestant + "]";
    }

    

    /**
     * Trier les bateaux par taille et par ordre décroissant
     */
    public void trierFlotte() {
        /* Liste des bateaux*/
        ArrayList<Bateau> aParcourir = this.getCollectionBateau();
        
        Bateau aInserer; // Bateau à positionner à la bonne place
        int j;           // parcours de boucle dans le sens inverse
        
        for (int i = 1; i < aParcourir.size(); i++) {
            aInserer = aParcourir.get(i);
            j = i;
            while(j > 0 && aParcourir.get(j-1).getTailleBateau() < aInserer.getTailleBateau()) {
                j--;
            }
            aParcourir.remove(i);
            aParcourir.add(j, aInserer);
        }       
    }
    
    /**
     * Ajouter un bateau dans la flotte
     * @param aAjouter
     */
    public void ajouterBateau(Bateau aAjouter) {
        collectionBateau.add(aAjouter);
    }
  
    /**
     * Retire un bateau à la flotte
     */
    public void bateauCoule() {
        bateauRestant--;
    }
    
    
    /**
     * Placement aléatoire des bateaux de la flotte sur la zone de jeu
     * @param zoneJeu 
     */
    public void placementAleatoireBateau(Zone zoneJeu) {
        int nombreAleatoire;    // nombre aléatoire qui donne le num de la case
        
        boolean placementOk; // dit si le placement d'un bateau est possible
        
        boolean bateauVertical; // direction verticale du bateau
        
        // liste des coordonnées où on ne peut pas placer de bateau
        ArrayList<Coordonnee> coordZoneNonPlacable = new ArrayList<Coordonnee>();
                           
        ArrayList<Bateau> collecBateau = this.getCollectionBateau();
        
        // récupérer tous les bateaux de la flotte
        for (int i = 0; i < collecBateau.size(); i++) {
            placementOk = true;
            
            /* Direction du bateau */
            nombreAleatoire = (int) (Math.random() * 100);
            bateauVertical = nombreAleatoire < 50 ? true : false;
            
            do {
                do {
                    Random nbAlea = new Random();
                    nombreAleatoire = nbAlea.nextInt(zoneJeu.getTailleHorizontale() * zoneJeu.getTailleVerticale());
                    System.out.println(nombreAleatoire);
                    if (coordZoneNonPlacable.contains(nombreAleatoire)) {
                        placementOk = false;
                    } else {
                        placementOk = true;
                    }
                } while(!placementOk);
                
                placementOk = true;
                if(collecBateau.get(i).collision(zoneJeu)) {
                    placementOk = false;
                }
            } while (!placementOk);
            
            
//            /* Construction du bateau sur la zone de jeu */
//            Bateau bConstruit = new Bateau(collecBateau.get(i).getCoordDepart(),
//                                           collecBateau.get(i).getCoordArrivee(),
//                                           zoneJeu);
//            
            
                    
            /* Récupérer la zone d'abordage du bateau et l'ajouter aux coordonnées 
             * non placables
             */
//            collecBateau.get(i).creerZoneAbordage(zoneJeu);
//            for (Coordonnee coord : collecBateau.get(i).getZoneAbordage()) {
//                coordZoneNonPlacable.add(coord.getY() * zoneJeu.getTailleHorizontale() 
//                                         + coord.getX());
//            }
            
        }
        
    }
}

