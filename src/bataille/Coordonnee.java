/*
 * Coordonnee.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * Une coordonn�e est d�finie par sa position en abscisse et en ordonn�e
 * @author Groupe projet
 */
public class Coordonnee {

    /** Abscisse de la coordonn�e */
    private int x;
    
    /** Ordonn�e de la coordonn�e */
    private int y;
    
    /** Indice de la coordonn�e dans la zone */
    private int indiceCoord;
    
    /** Indique si la coordonn�e est touch�e */
    private boolean touche;
    
    /** Indique si la coordonn�e contient un bateau */
    private boolean contientBateau;
    
    /** Indique si le bateau pr�sent sur la coordonn�e est coul� */
    private boolean bateauCoule;
    
    /** Indice du bateau de la flotte */
    private int indiceBateau;
    
    
    /**
     * Construit une coordonn�e d�fini par un x et un y
     * @param x abscisse de la coordonn�e
     * @param y ordonn�e de la coordonn�e
     */
    public Coordonnee(int x, int y) {
        verifCoordonnee(x, y);
        this.x = x;
        this.y = y;
        this.indiceCoord = y * Zone.tailleDefaut + x;
        this.indiceBateau = -1;
    }

    /**
     * Construit une coordonn� avec l'indice du bateau et d�fini par un x et un y 
     * @param x abscisse de la coordonn�e
     * @param y ordonn�e de la coordonn�e
     * @param indiceBateau indice du bateau dans la collection de flotte
     * @param contientBateau indique si la coordonn�e contient un bateau
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
     * TODO commenter le r�le de cette m�thode
     * @return la coordonn�e convertit en format A1
     */
    public String convertit() {
        char x;
        int y;
        
        x = (char) (this.getX() + 'A'); 
        y = (this.getY() + 1);
        
        return "" + x + y;
    }
    
    
    /**
     * V�rifie si les abscisses et ordonn�e sont dans la zone
     * @param x abscisse de la coordonn�e � tester
     * @param y ordonn�e de la coordonn�e � tester
     * @throws IllegalArgumentException si abscisse ou ordonn�e invalide
     */
    private static void verifCoordonnee(int x, int y) 
        throws IllegalArgumentException {
        
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordonn�e n�gative");
        } else if (Zone.tailleDefaut < x || Zone.tailleDefaut < y) {
            throw new IllegalArgumentException("Coordonn� en dehors");
        }
    }

    /**
     * Compare deux coordonn�es
     * @param aComparer coordonn�es � comparer
     * @return vrai si coordonn�es �gales
     */
    public boolean coordonneesEgales(Coordonnee aComparer) {
        return this.getX() == aComparer.getX() 
            && this.getY() == aComparer.getY();
    }
    
    /**
     * Determine si un coup � d�j� �t� jou� � partir de la liste des coups
     * @param coupJoue collection contenant les coups jou�
     * @return vrai si le coup � d�j� �tait jou�
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
     * M�thode renvoyant une arraylist avec tout les bateau coul� d'une arraylist
     * sp�cifique
     * @param coupJouer arraylist des coups jou�
     * @param flotte la flotte du jeux en cours
     * @return une arraylist des bateau coul�s
     */
    public ArrayList<Bateau> estCoule(ArrayList<Coordonnee> coupJouer, Flotte flotte) {
        ArrayList<Bateau> bateauCoule = new ArrayList<Bateau>();
        
        // on test tous les �l�ments
        // on compare les indice pour pas avoir 2 bateau identique
        for (int index =0 ; index < coupJouer.size() ; index ++) {
            if (coupJouer.get(index).isBateauCoule() && 
                    !bateauCoule.contains(flotte.getCollectionBateau().get(
                    coupJouer.get(index).getIndiceBateau()))) {
                //on ajoute le bateau de la flotte correspondant � l'indice
                bateauCoule.add(flotte.getCollectionBateau().get(
                    coupJouer.get(index).getIndiceBateau()));
            }
        }
        
        return bateauCoule;
    }
    
    
    /**
     * Determine si 2 coordonn�e sont proches
     * @param coordB coordonn�e � comparer avec this  
     * @return vrai si les coordonn�es sont � cot�s 
     */
    public boolean estProche(Coordonnee coordB) {
        int indiceA; // indice de this
        int indiceB; // indice de coordB
            
        /* r�cup�ration des indices */
        indiceA = this.getIndiceCoord();
        indiceB = coordB.getIndiceCoord();
        
        /* si la coordonn�e est proche */
        if (indiceA + 1 == indiceB || indiceA - 1 == indiceB
         || indiceA - Zone.tailleDefaut == indiceB 
         || indiceA + Zone.tailleDefaut == indiceB) {
            return true;
        }
        
        return false;
    }
    
   
    /**
     * M�thode determinant si un coup est pertinant ou pas en fonction des 
     * coup d�ja jou� et de la flotte selectionn� pour la partie
     * @param coupDejaJoue arraylist des coups deja jou� 
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
    	// test d'un coup d�j� jou�
    	if (dejaJoue(coupDejaJoue)){
    		return true;
    	}
    	
    	//test d'un coup pr�sent dans la zone d'antiabordage

//    	//test de jeux sur la zone d'abordage d'un bateau coul�
//    	for (int index=0 ; index< bateauCoule.size() ; index ++) {
//    		if (bateauCoule.get(index).getZoneContenu().get(0).collision(xCoup, yCoup)) {
//    			coupNonPertinent = true;
//    		}
//    	}

    	//test sur les bateau non coul� contenant plus de 2 case touch� (instable)
    	// non prise en charge dans un premier temps TODO : � compl�ter si possible

    	return coupNonPertinent;
    }
    
}
