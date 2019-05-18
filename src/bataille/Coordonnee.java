/*
 * Coordonnee.java                                                  16 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

/**
 * TODO commenter les responsabilités de cette classe
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
        verifCoordonnée(x, y);
        this.x = x;
        this.y = y;
        this.indiceCoord = y * Zone.tailleDefaut + x;
    }

    /**
     * Construit une coordonné avec l'indice du bateau et défini par un x et un y 
     * @param x abscisse de la coordonnée
     * @param y ordonnée de la coordonnée
     * @param indiceBateau indice du bateau dans la collection de flotte
     * @param contientBateau indique si la coordonnée contient un bateau
     */
    public Coordonnee(int x, int y, int indiceBateau, boolean contientBateau) {
        verifCoordonnée(x, y);
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
     * Vérifie si les abscisses et ordonnée sont dans la zone
     * @param x abscisse de la coordonnée à tester
     * @param y ordonnée de la coordonnée à tester
     * @throws IllegalArgumentException si abscisse ou ordonnée invalide
     */
    private static void verifCoordonnée(int x, int y) 
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
}
