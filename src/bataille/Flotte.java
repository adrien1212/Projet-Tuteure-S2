/*
 * Flotte.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * Une flotte contient plusieurs bateaux
 * @author Groupe Projet
 *
 */

public class Flotte {

    /** Collection des bateaux composant la flotte  */
    private ArrayList<Bateau> collectionBateau;
    
    /** Nombre de bateaux non coulé dans la flotte */
    private int bateauRestant;


    /**
     * Construit une flotte par défaut
     */
    public Flotte() {
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
            aInserer.setIndiceBateau(j);
        }       
    }
    
    /**
     * Ajouter un bateau dans la flotte
     * @param aAjouter
     */
    public void ajouterBateau(Bateau aAjouter) {
        bateauRestant++;
        collectionBateau.add(aAjouter);
    }
  
    /**
     * Retire un bateau à la flotte
     */
    public void bateauCoule() {
        bateauRestant--;
    }
 
    
    /**
     * Place si possible les bateaux de la flotte this aléatoirement sur la zone
     * @param zoneOuPlacer zone où placer les bateaux
     * @return vrai si le placement est possible
     */
    public boolean placementBateauAlea(Zone zoneOuPlacer) {
        int BOUCLAGE_MAX = 1000000; // nombre de bouclage maximal
        int NB_ESSAI = 100;      // nombre d'essai maximal
        int essai;                // nombre d'essai de placement
        
        /* Coordonnees nonJouable */
        ArrayList<Integer> coordNonJouable = new ArrayList<Integer>();
        
        /* stockage temporaire des bateaux à placer */
        this.trierFlotte();
        ArrayList<Bateau> collecTmp = this.collectionBateau;
        
        Bateau bateauAPlacer;  // bateau à placer
        boolean ok;            // si le placement est possible
        
        int x; // abscisse de la coordonnée de départ du bateau à placer
        int y; // ordonnée de la coordonnée du départ bateau à placer
        Coordonnee coordonneeDepart; // coordonnée de départ où placer le bateau
        
        boolean vertical; // indique si le bateau est en position vertical
         
        essai = 0;
        for (int indice = 0, bouclage; 
                 indice < collectionBateau.size() && essai < NB_ESSAI; indice++) {
            
            /* récupération du bateau à placer */
            bateauAPlacer = collectionBateau.get(indice);
            
            /* boucle si placement impossible */
            bouclage = 0;
            do {
                /* génération aléatoire pour vertical */
                vertical = Math.random() < 0.5;

                /* génération aléatoire du x et y */
                if (vertical) {
                    x = (int) (Math.random() * Zone.tailleDefaut);
                    y = (int) (Math.random() * (Zone.tailleDefaut - bateauAPlacer.getTailleBateau()));
                } else {
                    x = (int) (Math.random() * (Zone.tailleDefaut - bateauAPlacer.getTailleBateau()));
                    y = (int) (Math.random() * Zone.tailleDefaut);
                }

                coordonneeDepart = new Coordonnee(x, y);
                ok = bateauAPlacer.placerBateau(coordonneeDepart, vertical, 
                        zoneOuPlacer);

                bouclage++;
            } while (!ok && bouclage < BOUCLAGE_MAX);
            
            if (!ok && indice != 0) {
                indice--; // recommence au bateau précèdent
                
                /* changement du bateau précèdent */
                this.collectionBateau.set(indice, collecTmp.get(indice));
                
                /* on retire la dernière zone ajouter */
                zoneOuPlacer.getZoneContenu().remove(zoneOuPlacer.getZoneContenu().size()-1);
                
                indice--; // retire l'incrementation
                essai++;  // Incrémente le nombre d'essai
            } else {
                /* placement du bateau sur la zone si possible */
                bateauAPlacer = bateauAPlacer.constuireBateau(coordonneeDepart, 
                                                              indice, vertical, 
                                                              zoneOuPlacer);
                
                /* Ajout des coordonnées ou on ne peut pas placer de bateau*/
                for (Coordonnee nonJouable : bateauAPlacer.getZoneContenu().get(0).getZoneCoord()) {
                    coordNonJouable.add(nonJouable.getIndiceCoord());
                }
                
                /* changement du bateau dans la flotte */
                this.collectionBateau.set(indice, bateauAPlacer); 
            }
        }
        
        if (essai == NB_ESSAI) {
            return false;
        }
        
        zoneOuPlacer.ajouterFlotte(this);
        return true;
    }
    
    /**
     * Affiche les bateaux dans la flotte
     */
    public void afficherFlotte() {
        System.out.println();
        for (Bateau bateau : collectionBateau) {
            System.out.println(bateau);
        }
        System.out.println();
    }
}

