/*
 * Coordonnee.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * Une coordonnée est définie par sa position en abscisse et en ordonnée
 * @author Groupe projet
 */
public class Coordonnee {

    /** Abscisse de la coordonnée */
    private int x;
    
    /** Ordonnée de la coordonnée */
    private int y;
    
    /** Indice de la coordonnée dans la zone */
    private int indiceCoord;
    
    /** Indique si la coordonnée est touchée */
    private boolean touche;
    
    /** Indique si la coordonnée contient un bateau */
    private boolean contientBateau;
    
    /** Indique si le bateau présent sur la coordonnée est coulé */
    private boolean bateauCoule;
    
    /** Indice du bateau de la flotte */
    private int indiceBateau;
    
    
    /**
     * Construit une coordonnée défini par un x et un y
     * @param x abscisse de la coordonnée
     * @param y ordonnée de la coordonnée
     */
    public Coordonnee(int x, int y) {
        verifCoordonnee(x, y);
        this.x = x;
        this.y = y;
        this.indiceCoord = y * Zone.tailleDefaut + x;
        this.indiceBateau = -1;
    }

    /**
     * Construit une coordonné avec l'indice du bateau et défini par un x et un y 
     * @param x abscisse de la coordonnée
     * @param y ordonnée de la coordonnée
     * @param indiceBateau indice du bateau dans la collection de flotte
     * @param contientBateau indique si la coordonnée contient un bateau
     */
    public Coordonnee(int x, int y, int indiceBateau, boolean contientBateau) {
        verifCoordonnee(x, y);
        this.x = x;
        this.y = y;
        this.indiceCoord = y * Zone.tailleDefaut + x;
        this.indiceBateau = indiceBateau;
        this.contientBateau = contientBateau;
    }

    
    
    /**
     * @return valeur de x
     */
    public int getX() {
        return x;
    }

    /**
     * @return valeur de y
     */
    public int getY() {
        return y;
    }

    /**
     * @return valeur de indiceCoord
     */
    public int getIndiceCoord() {
        return indiceCoord;
    }

    /**
     * @return valeur de touche
     */
    public boolean isTouche() {
        return touche;
    }

    /**
     * @param touche nouvelle valeur de touche
     */
    public void setTouche(boolean touche) {
        this.touche = touche;
    }
    
    /**
     * @return valeur de contientBateau
     */
    public boolean isContientBateau() {
        return contientBateau;
    }

    /**
     * @param contientBateau nouvelle valeur de contientBateau
     */
    public void setContientBateau(boolean contientBateau) {
        this.contientBateau = contientBateau;
    }

    /**
     * @return valeur de bateauCoule
     */
    public boolean isBateauCoule() {
        return bateauCoule;
    }

    /**
     * @param bateauCoule nouvelle valeur de bateauCoule
     */
    public void setBateauCoule(boolean bateauCoule) {
        this.bateauCoule = bateauCoule;
    }

    /**
     * @return valeur de indiceBateau
     */
    public int getIndiceBateau() {
        return indiceBateau;
    }

    /**
     * @param indiceBateau nouvelle valeur de indiceBateau
     */
    public void setIndiceBateau(int indiceBateau) {
        this.indiceBateau = indiceBateau;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    } 
    
    /**
     * TODO commenter le rôle de cette méthode
     * @return la coordonnée convertit en format A1
     */
    public String convertit() {
        char x;
        int y;
        
        x = (char) (this.getX() + 'A'); 
        y = (this.getY() + 1);
        
        return "" + x + y;
    }
    
    
    /**
     * Vérifie si les abscisses et ordonnée sont dans la zone
     * @param x abscisse de la coordonnée à tester
     * @param y ordonnée de la coordonnée à tester
     * @throws IllegalArgumentException si abscisse ou ordonnée invalide
     */
    private static void verifCoordonnee(int x, int y) 
        throws IllegalArgumentException {
        
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordonnée négative");
        } else if (Zone.tailleDefaut < x || Zone.tailleDefaut < y) {
            throw new IllegalArgumentException("Coordonné en dehors");
        }
    }

    /**
     * Compare deux coordonnées
     * @param aComparer coordonnées à comparer
     * @return vrai si coordonnées égales
     */
    public boolean coordonneesEgales(Coordonnee aComparer) {
        return this.getX() == aComparer.getX() 
            && this.getY() == aComparer.getY();
    }
    
    /**
     * Determine si un coup à déjà été joué à partir de la liste des coups
     * @param coupJoue collection contenant les coups joué
     * @return vrai si le coup à déjà était joué
     */
    public boolean dejaJoue(ArrayList<Coordonnee> coupJoue) {
        for (Coordonnee coup : coupJoue) {
            if (this.coordonneesEgales(coup)) {
                    return true;
            }
        }

        return false;
    }
    
  
    
    /**
     * Méthode renvoyant une arraylist avec tout les bateau coulé d'une arraylist
     * spécifique
     * @param coupJouer arraylist des coups joué
     * @param flotte la flotte du jeux en cours
     * @return une arraylist des bateau coulés
     */
    public ArrayList<Bateau> estCoule(ArrayList<Coordonnee> coupJouer, Flotte flotte) {
        ArrayList<Bateau> bateauCoule = new ArrayList<Bateau>();
        
        // on test tous les éléments
        // on compare les indice pour pas avoir 2 bateau identique
        for (int index =0 ; index < coupJouer.size() ; index ++) {
            if (coupJouer.get(index).isBateauCoule() && 
                    !bateauCoule.contains(flotte.getCollectionBateau().get(
                    coupJouer.get(index).getIndiceBateau()))) {
                //on ajoute le bateau de la flotte correspondant à l'indice
                bateauCoule.add(flotte.getCollectionBateau().get(
                    coupJouer.get(index).getIndiceBateau()));
            }
        }
        
        return bateauCoule;
    }
    
    
    /**
     * Determine si 2 coordonnée sont proches
     * @param coordB coordonnée à comparer avec this  
     * @return vrai si les coordonnées sont à cotés 
     */
    public boolean estProche(Coordonnee coordB) {
        int indiceA; // indice de this
        int indiceB; // indice de coordB
            
        /* récupération des indices */
        indiceA = this.getIndiceCoord();
        indiceB = coordB.getIndiceCoord();
        
        /* si la coordonnée est proche */
        if (indiceA + 1 == indiceB || indiceA - 1 == indiceB
         || indiceA - Zone.tailleDefaut == indiceB 
         || indiceA + Zone.tailleDefaut == indiceB) {
            return true;
        }
        
        return false;
    }
    
   
    /**
     * Méthode determinant si un coup est pertinant ou pas en fonction des 
     * coup déja joué et de la flotte selectionné pour la partie
     * @param coupDejaJoue arraylist des coups deja joué 
     * @param flotte 
     * @return si le coup est non pertinent ou pas
     */
    public boolean coupNonPertinent(ArrayList<Coordonnee> coupDejaJoue, Flotte flotte) {
    	boolean coupNonPertinent = false;
    	
    	ArrayList<Bateau> bateauCoule = estCoule( coupDejaJoue,flotte);

    	int xCoup,
    	yCoup;

    	xCoup= this.getX();
    	yCoup= this.getY();
    	// test d'un coup déjà joué
    	if (dejaJoue(coupDejaJoue)){
    		return true;
    	}
    	
    	//test d'un coup présent dans la zone d'antiabordage

//    	//test de jeux sur la zone d'abordage d'un bateau coulé
//    	for (int index=0 ; index< bateauCoule.size() ; index ++) {
//    		if (bateauCoule.get(index).getZoneContenu().get(0).collision(xCoup, yCoup)) {
//    			coupNonPertinent = true;
//    		}
//    	}

    	//test sur les bateau non coulé contenant plus de 2 case touché (instable)
    	// non prise en charge dans un premier temps TODO : à compléter si possible

    	return coupNonPertinent;
    }
    
}
