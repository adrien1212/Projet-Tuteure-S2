/*
 * Sauvegarde2.java                                                  6 mai 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package outil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import bataille.Bateau;
import bataille.Coordonnee;
import bataille.Zone;
import bataille.Flotte;

/**
 * Sauvegarder et Restaurer une partie
 * @author Groupe
 *
 */
public class Sauvegarde {
    
    /** Informations sur la taille de la zone de jeu */
    public static ArrayList<Integer> tailleZoneJeu = new ArrayList<Integer>();
    
    /** Flotte contenant tous les bateaux */
    public static Flotte laFlotte = new Flotte();
    
    
    /**
     * Sauvegarde d'une partie
     * @param zone
     * @param flotte
     * @param coupJoue
     */
    public static void creerSauvegarde(Zone zone, Flotte flotte, 
                                       ArrayList<Coordonnee> coupJoue) {
        
        try {
            /* cr�ation du document (arborescence) */
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document document = docBuilder.newDocument();
            
            /* cr�ation de l'�l�ment racine */
            Element racine = document.createElement("Bataille");
            document.appendChild(racine);
            
            /* cr�ation de l'�l�ment zone de jeu */
            Element eZoneJeu = document.createElement("ZoneJeu");
            racine.appendChild(eZoneJeu);
            
            /* cr�ation de l'�l�ment tailleHorizontale */
            Element eTailleHorizontale = document.createElement("tailleHorizontale");
            eTailleHorizontale.appendChild(document.createTextNode("" + zone.getTailleHorizontale()));
            eZoneJeu.appendChild(eTailleHorizontale);
            
            /* cr�ation de l'�l�ment taileVerticale */
            Element eTailleVerticale = document.createElement("tailleVerticale");
            eTailleVerticale.appendChild(document.createTextNode("" + zone.getTailleVerticale()));
            eZoneJeu.appendChild(eTailleVerticale);
            
            
            
            
            /* cr�ation de l'�l�ment zone */
            Element eZone = document.createElement("CasesUtiles");
            racine.appendChild(eZone);
            
            for (Coordonnee caseASauver : zone.getZoneCoord()) {
                /* cr�ation de l'�l�ment parent case */
                Element eCase = document.createElement("case");
                eZone.appendChild(eCase);
                
                /* cr�ation de l'�l�ment x */
                Element eX = document.createElement("x");
                eX.appendChild(document.createTextNode("" + caseASauver.getX()));
                eCase.appendChild(eX);
                
                /* cr�ation de l'�l�ment y */
                Element eY = document.createElement("y");
                eY.appendChild(document.createTextNode("" + caseASauver.getY()));
                eCase.appendChild(eY);
                
//                /* cr�ation de l'�l�ment touch� */
//                Element eTouche = document.createElement("touche");
//                eTouche.appendChild(document.createTextNode("" + caseASauver.isTouche()));
//                eCase.appendChild(eTouche);
                
                /* cr�ation de l'�l�ment coul� */
                Element eCoule = document.createElement("coule");
                eCoule.appendChild(document.createTextNode("" + caseASauver.isBateauCoule()));
                eCase.appendChild(eCoule);
            }
            
            
            /* cr�ation de l'�l�ment flotte */
            Element eFlotte = document.createElement("flotte");
            racine.appendChild(eFlotte);
            
            /* ajout des bateaux */
            for (Bateau bateau : flotte.getCollectionBateau()) {
                /* cr�ation de l'�l�ment parent bateau */
                Element eBateau = document.createElement("bateau");
                eFlotte.appendChild(eBateau);
                
                /* cr�ation de l'�l�ment caseDepart */
                Element eCaseX = document.createElement("coordDepartX");
                eCaseX.appendChild(document.createTextNode("" 
                                                     + bateau.getCoordDepart().getX()));
                eBateau.appendChild(eCaseX);
                
                Element eCaseY = document.createElement("coordDepartX");
                eCaseY.appendChild(document.createTextNode("" 
                                                     + bateau.getCoordDepart().getY()));
                eBateau.appendChild(eCaseY);
                
                
                /* cr�ation de l'�l�ment caseArrivee */
                Element eCase2X = document.createElement("coordArriveeX");
                eCase2X.appendChild(document.createTextNode("" 
                                                     + bateau.getCoordArrivee().getX()));
                eBateau.appendChild(eCase2X);
                
                Element eCase2Y = document.createElement("coordArriveeY");
                eCase2Y.appendChild(document.createTextNode("" 
                                                     + bateau.getCoordArrivee().getY()));
                eBateau.appendChild(eCase2Y);
                
                
                /* cr�ation de l'�l�ment coul� */
                Element eCoule = document.createElement("coule");
                eCoule.appendChild(document.createTextNode("" 
                                                           + bateau.isCoule()));
                eBateau.appendChild(eCoule);
                
            }
            
            
            /* cr�ation de l'�l�ment coup */
            Element coup = document.createElement("coup");
            racine.appendChild(coup);
            
            /* ajout des coups jou�s */
            for (int indice = 0; indice < coupJoue.size(); indice++) {
                /* cr�ation de l'�l�ment caseDepart */
                Element eCase = document.createElement("case");
                eCase.appendChild(document.createTextNode("" 
                                                       + coupJoue.get(indice)));
                coup.appendChild(eCase);
            }
            
            
            /* cr�ation du fichier */
            try {
                TransformerFactory transFact = TransformerFactory.newInstance();
                Transformer transformer = transFact.newTransformer();
                DOMSource source = new DOMSource(racine);
                StreamResult result = new StreamResult(new File("sauvegarde.xml"));
                System.out.println("Sauvegarde effectu�e");
                transformer.transform(source, result);
            } catch (TransformerConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (TransformerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } catch (ParserConfigurationException e) {
            System.err.println("PB avec DocumentBuilder");
        }
    }
    
    
    /**
     * Lecture du fichier de sauvegarde
     */
    public static void lire() {


        try {

            File fXmlFile = new File("sauvegarde.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);


            doc.getDocumentElement().normalize();

            System.out.println("Element racine :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Bataille");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nElement courant :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Zone : " + eElement.getAttribute("zone"));


                    /* R�cup�ration des donn�es de la zone de jeu */
                    System.out.println("*** R�cup�rations de la zone de jeu ***");
                    NodeList zoneDeJeu = doc.getElementsByTagName("ZoneJeu");
                    for (int i = 0; i < zoneDeJeu.getLength(); i++) {
                        //Element taille = (Element) zoneDeJeu.item(i);
                        System.out.println("tailleHorizontale : " + eElement.getElementsByTagName("tailleHorizontale").item(i).getTextContent());
                        System.out.println("tailleVerticale : " + eElement.getElementsByTagName("tailleVerticale").item(i).getTextContent());

                        tailleZoneJeu.add(Integer.parseInt(eElement.getElementsByTagName("tailleHorizontale").item(i).getTextContent()));
                        tailleZoneJeu.add(Integer.parseInt(eElement.getElementsByTagName("tailleVerticale").item(i).getTextContent()));

                    }

                    Zone zoneJeu = new Zone(new Coordonnee(0, 0), tailleZoneJeu.get(0), tailleZoneJeu.get(1));

                    System.out.println();


                    /* R�cup�ration des donn�es des bateaux */
                    int x, y, x2, y2;
                    System.out.println("*** R�cup�rations de la flotte ***");
                    NodeList flotte = doc.getElementsByTagName("bateau");
                    for (int i = 0; i < flotte.getLength(); i++) {
                        //Element bateau = (Element) flotte.item(i);
                        System.out.println("Bateau n� " +i);
                        System.out.println("coordDepart : " + eElement.getElementsByTagName("coordDepartX").item(i).getTextContent() + " "
                                + eElement.getElementsByTagName("coordDepartY").item(i).getTextContent());
                        System.out.println("coordArrivees : " + eElement.getElementsByTagName("coordArriveeX").item(i).getTextContent() + " "
                                + eElement.getElementsByTagName("coordArriveeX").item(i).getTextContent());
                        System.out.println("estCoule : " + eElement.getElementsByTagName("coule").item(i).getTextContent());

                        /* Informations sur les bateaux */
                        x = Integer.parseInt(eElement.getElementsByTagName("coordDepartX").item(i).getTextContent());
                        y = Integer.parseInt(eElement.getElementsByTagName("coordDepartY").item(i).getTextContent()); 
                        x2 = Integer.parseInt(eElement.getElementsByTagName("coordArriveeX").item(i).getTextContent());
                        y2 = Integer.parseInt(eElement.getElementsByTagName("coordArriveeY").item(i).getTextContent());

                        Bateau aConstruire = new Bateau(new Coordonnee(x,y), new Coordonnee(x2, y2), i, zoneJeu);

                        laFlotte.ajouterBateau(aConstruire);
                    }
                    
                    System.out.println();


                    /* R�cup�ration des donn�es des coups */
                    System.out.println("*** R�cup�rations des coups ***");
                    NodeList coups = doc.getElementsByTagName("case");
                    for (int i = 0; i < coups.getLength(); i++) {
                        //Element coordonnee = (Element) coups.item(i);
                        System.out.println("case : " + eElement.getElementsByTagName("case").item(i).getTextContent());
                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * @param args
     */
    public static void main(String args[] ) {
        
        /* Bouchon */
        Zone zoneDepart = new Zone();
        Coordonnee caseDepart = new Coordonnee(0,0);
        
        Bateau b1 = new Bateau(new Coordonnee(1, 1), new Coordonnee(4, 1), 0, zoneDepart);
        Bateau b2 = new Bateau(new Coordonnee(3, 4), new Coordonnee(3, 7), 1, zoneDepart);
        Bateau b3 = new Bateau(new Coordonnee(7, 7), new Coordonnee(8, 7), 2, zoneDepart);
        
        Flotte flotte = new Flotte();
        flotte.ajouterBateau(b1);
        flotte.ajouterBateau(b2);
        flotte.ajouterBateau(b3);
        
        ArrayList<Coordonnee> test = new ArrayList<Coordonnee>();
               
        test.add(caseDepart);
        test.add(caseDepart);
        
        
       creerSauvegarde(zoneDepart, flotte, test);
       //lire();
       
       System.out.println(tailleZoneJeu.toString());
       System.out.println(laFlotte.toString());
       
        
        

    }

}
