/*
 * Coordonnee.java                                                  14 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

/**
 * Cr�er des coordonn�es d�fini par un x, un y
 * @author Groupe projet
 */
public class Coordonnee {

    /** abscisse de la coordonn�e */
    private int x;
    
    /** ordonn�e de la coordonn�e */
    private int y;
    
    /** indice de la coordonn�e (utilis� pour sauver) */
    private int indiceCoord;
    
    /** indice du bateau pr�sent sur cette coordonn�e (les zones du bateau) */
    private int indiceBateau;
    
    /** indique si la coordonn�e � re�u un tir */
    private boolean touche;
    
    /** indique si la coordonn�e � un bateau pr�sent */
    private boolean contientBateau;
    
    /** indique si le bateau pr�sent est coul� */
    private boolean coule;
    
    
    
    /**
     * Cr�er une coordonn�e par d�faut (pour l'application)
     * @param x abscisse de la coordonn�e
     * @param y ordonn�e de la coordonn�e
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
     * Cr�er une coordonn�e pour le passage de valeur
     * @param x abscisse de la coordonn�e
     * @param y ordonn�e de la coordonn�e
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
