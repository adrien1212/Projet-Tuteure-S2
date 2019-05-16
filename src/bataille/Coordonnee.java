/*
 * Coordonnee.java                                                  14 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

/**
 * Créer des coordonnées défini par un x, un y
 * @author Groupe projet
 */
public class Coordonnee {

    /** abscisse de la coordonnée */
    private int x;
    
    /** ordonnée de la coordonnée */
    private int y;
    
    /** indice de la coordonnée (utilisé pour sauver) */
    private int indiceCoord;
    
    /** indice du bateau présent sur cette coordonnée (les zones du bateau) */
    private int indiceBateau;
    
    /** indique si la coordonnée à reçu un tir */
    private boolean touche;
    
    /** indique si la coordonnée à un bateau présent */
    private boolean contientBateau;
    
    /** indique si le bateau présent est coulé */
    private boolean coule;
    
    
    
    /**
     * Créer une coordonnée par défaut (pour l'application)
     * @param x abscisse de la coordonnée
     * @param y ordonnée de la coordonnée
     * @param indiceCoord 
     */
    public Coordonnee(int x, int y, int indiceCoord) {
        this.x = x;
        this.y = y;
        this.indiceCoord = indiceCoord;
        this.indiceBateau = -1;
        this.touche = false;
        this.contientBateau = false;
        this.coule = false;
    }
    
    /**
     * Créer une coordonnée pour le passage de valeur
     * @param x abscisse de la coordonnée
     * @param y ordonnée de la coordonnée
     */
    public Coordonnee(int x, int y) {
        this.x = x;
        this.y = y;
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
     * @return valeur de idBateau
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
     * @return valeur de coule
     */
    public boolean isCoule() {
        return coule;
    }

    /**
     * @param coule nouvelle valeur de coule
     */
    public void setCoule(boolean coule) {
        this.coule = coule;
    }



    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }    
}
