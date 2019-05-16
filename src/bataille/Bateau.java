/*
 * Bateau.java                                                  14 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package bataille;

import java.util.ArrayList;

/**
 * Créer des bateaux
 * @author Groupe projet
 */
public class Bateau extends Zone {
    
    /** Taille du bateau (équivaut à la longueur ou la largeur) */
    private int taille;
    
    /** indique si le bateau est coulé */
    private boolean coule;
    
    /** zone d'abordage du bateau */
    private Zone abordage;
    
    /** indice du bateau dans la flotte */
    private int indiceBateau;

    
    
    /**
     * Construit un bateau à une place précisé
     * @param longueur longueur du bateau (axe x)
     * @param largeur largeur du bateau (axe y)
     * @param indiceBateau indice du bateau dans la flotte
     * @param coordDepart coordonnée de départ où placer le bateau
     */
    public Bateau(int longueur, int largeur, int indiceBateau,
        Coordonnee coordDepart) {
        
        super(longueur, largeur, indiceBateau, coordDepart);
        this.indiceBateau = indiceBateau;
        this.coule = false;
        this.abordage = new Zone(this);
    }
    
    /**
     * TODO commenter l'état initial défini
     * @param longueur
     * @param largeur
     * @param indiceBateau
     */
    public Bateau(int longueur, int largeur, int indiceBateau) {
        super(longueur, largeur);
        this.indiceBateau = indiceBateau;
    }
    
    /**
     * Construit un bateau pour stocker les valeurs
     * @param taille taille du bateau
     */
    public Bateau(int taille) {
        this.taille = taille;
    }

    
    
    /**
     * @return valeur de taille
     */
    public int getTaille() {
        return taille;
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

    /**
     * @return valeur de abordage
     */
    public Zone getAbordage() {
        return abordage;
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
        return "Bateau [coule=" + coule + ", abordage=" + abordage 
             + ", indiceBateau=" + indiceBateau + "]";
    }
    
    
    
    /**
     * Vérifie si toutes les coordonnées du bateau sont touchées et change 
     * l'attribut coule du bateau
     * @return vrai si le bateau est coulé
     */
    public boolean estCoule() {
        ArrayList<Coordonnee> zoneBateau = this.getZone();

        int indice;
        for (indice = 0; indice < zoneBateau.size() 
                      && zoneBateau.get(indice).isTouche(); indice++);
        
        if (indice == zoneBateau.size()) {
            this.setCoule(true);
            for (Coordonnee coordAChanger : zoneBateau) {
                coordAChanger.setCoule(true);
            }
            return true;
        }
        
        return false;
    }
    
    
    
    
    /**
     * Construit un bateau à partir d'un autre bateau (bateau de stockage)
     * @param coordDepart coordonnée de départ du bateau à construire
     * @return le bateau créer
     */
    public Bateau construireBateau(Coordonnee coordDepart) {
        return new Bateau(getLongueur(), getLargeur(), getIndiceBateau(), 
                          coordDepart);
    }
    
    /**
     * Construit un bateau à partir d'éléments donnés
     * @param taille taille du bateau
     * @param vertical indique si le bateau est en position vertical
     * @param indice indice du bateau dans la flotte
     * @return le bateau créer
     */
    public static Bateau construireBateau(int taille, boolean vertical, 
                                          int indice) {
        if (vertical) {
            return new Bateau(1, taille, indice);
        } else {
            return new Bateau(taille, 1, indice);
        }
    }
    
    /**
     * Vérifie si le bateau peut être placer
     * @param aPlacer 
     * @param coordDepartBateau coordonnée où essayer de placer le bateau
     * @return vrai si le placement est possible
     */
    public static boolean placerBateau(Bateau aPlacer, 
                                       Coordonnee coordDepartBateau) {
        try {
            @SuppressWarnings("unused")
            Bateau aTester = new Bateau(aPlacer.getLongueur(), 
                                        aPlacer.getLargeur(), 
                                        aPlacer.getIndiceBateau(), 
                                        coordDepartBateau);
            if (aTester.collision()) {
                return false;
            }
            
        } catch (IllegalArgumentException eZone) {
            return false;
        }
        
        return true;
    }
    
    
    
    
    /**
     * Détermine le nombre de case touché d'un bateau
     * @return le nombre de case touché du bateau
     */
    public int nombreCaseTouche() {
        ArrayList<Coordonnee> zoneBateau = this.getZone();       
        int compteur;
        
        for(compteur = 0; compteur < zoneBateau.size() 
                       && zoneBateau.get(compteur).isTouche() ; compteur++);
        
        return compteur;
    }
}
