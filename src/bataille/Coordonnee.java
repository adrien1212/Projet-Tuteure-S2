/*
 * Coordonnee.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

/**
 * TODO commenter les responsabilit�s de cette classe
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
        verifCoordonn�e(x, y);
        this.x = x;
        this.y = y;
        this.indiceCoord = y * Zone.tailleDefaut + x;
    }

    /**
     * Construit une coordonn� avec l'indice du bateau et d�fini par un x et un y 
     * @param x abscisse de la coordonn�e
     * @param y ordonn�e de la coordonn�e
     * @param indiceBateau indice du bateau dans la collection de flotte
     * @param contientBateau indique si la coordonn�e contient un bateau
     */
    public Coordonnee(int x, int y, int indiceBateau, boolean contientBateau) {
        verifCoordonn�e(x, y);
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



    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    
    
    
    /**
     * V�rifie si les abscisses et ordonn�e sont dans la zone
     * @param x abscisse de la coordonn�e � tester
     * @param y ordonn�e de la coordonn�e � tester
     * @throws IllegalArgumentException si abscisse ou ordonn�e invalide
     */
    private static void verifCoordonn�e(int x, int y) 
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
}
