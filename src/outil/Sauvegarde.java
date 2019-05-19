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

import org.w3c.dom.Attr;
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

    /**
     * Créer une sauvegarde de la zone, la flotte, et les coups joués
     * @param zone zone à sauvegarder
     * @param flotte flotte à sauvegarder
     * @param coupJoue coup joué à sauvegarder
     */
    public static void creerSauvegarde(Zone zone, Flotte flotte, 
                                       ArrayList<Coordonnee> coupJoue) {
        
        try {
            /* création du document (arborescence) */
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document document = docBuilder.newDocument();
            
            /* création de l'élément racine */
            Element racine = document.createElement("Bataille");
            document.appendChild(racine);
            
            
            /* création de l'élément zone */
            Element eZone = document.createElement("zone");
            racine.appendChild(eZone);
            
            for (Coordonnee caseASauver : zone.getZoneCoord()) {
                /* création de l'élément parent case */
                Element eCase = document.createElement("case");
                eZone.appendChild(eCase);
                
                /* création de l'élément x */
                Element eX = document.createElement("x");
                eX.appendChild(document.createTextNode("" + caseASauver.getX()));
                eCase.appendChild(eX);
                
                /* création de l'élément y */
                Element eY = document.createElement("y");
                eY.appendChild(document.createTextNode("" + caseASauver.getY()));
                eCase.appendChild(eY);
                
                /* création de l'élément indice bateau */
                Element eIndice = document.createElement("indiceBateau");
                eIndice.appendChild(document.createTextNode("" + caseASauver.getIndiceBateau()));
                eCase.appendChild(eIndice);
                
                /* création de l'élément touché */
                Element eTouche = document.createElement("touche");
                eTouche.appendChild(document.createTextNode("" + caseASauver.isTouche()));
                eCase.appendChild(eTouche);
                
                /* création de l'élément coulé */
                Element eCoule = document.createElement("coule");
                eCoule.appendChild(document.createTextNode("" + caseASauver.isBateauCoule()));
                eCase.appendChild(eCoule);
            }
            
            
            /* création de l'élément flotte */
            Element eFlotte = document.createElement("flotte");
            racine.appendChild(eFlotte);
            
            /* ajout des bateaux */
            for (Bateau bateau : flotte.getCollectionBateau()) {
                /* création de l'élément parent bateau */
                Element eBateau = document.createElement("bateau");
                eFlotte.appendChild(eBateau);
                
                /* création de l'élément caseDepart */
                Element eCase = document.createElement("coordDepart");
                eCase.appendChild(document.createTextNode("" 
                                                     + bateau.getCoordDepart()));
                eBateau.appendChild(eCase);
                
                //* création de l'élément caseDepart */
                Element eCase2 = document.createElement("coordArrivee");
                eCase2.appendChild(document.createTextNode("" 
                                                     + bateau.getCoordArrivee()));
                eBateau.appendChild(eCase2);
                
                
                /* création de l'élément coulé */
                Element eCoule = document.createElement("coule");
                eCoule.appendChild(document.createTextNode("" 
                                                           + bateau.isCoule()));
                eBateau.appendChild(eCoule);
            }
            
            
            /* création de l'élément coup */
            Element coup = document.createElement("coup");
            racine.appendChild(coup);
            
            /* ajout des coups joués */
            for (int indice = 0; indice < coupJoue.size(); indice++) {
                /* création de l'élément caseDepart */
                Element eCase = document.createElement("case");
                eCase.appendChild(document.createTextNode("" 
                                                       + coupJoue.get(indice)));
                coup.appendChild(eCase);
            }
            
            
            /* création du fichier */
            try {
                TransformerFactory transFact = TransformerFactory.newInstance();
                Transformer transformer = transFact.newTransformer();
                DOMSource source = new DOMSource(racine);
                StreamResult result = new StreamResult(new File("sauvegarde.xml"));
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
    
//    /**
//     * Lit la sauvegarde et restaure les données
//     */
//    public static void lireSauvegarde() {
//        
//        Node nodeCase;
//        Coordonnee caseALire; // case a lire et à restaurer
//        /* tableau contenant les cases à lire */
//        Coordonnee[] tabCases = new Coordonnee[Zone.LONGUEUR * Zone.LARGEUR];
//        
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = factory.newDocumentBuilder();
//            Document document = docBuilder.parse(new File("sauvegarde.xml"));
//            
//            /* récupération élément racine */
//            Element racine = document.getDocumentElement();
//            
//            /* récupération de la racine */
//            NodeList racineNoeuds = racine.getChildNodes();
//            
//            /* récupération des éléments de la sauvegarde */
//            Element eZone = (Element) racineNoeuds.item(0);
//            Element eFlotte = (Element) racineNoeuds.item(1);
//            Element eCoup = (Element) racineNoeuds.item(2);
//            
//            
//            /* remplissage du tableau case */
//            NodeList nodeZone = eZone.getChildNodes();
//            for (int indice = 0; indice < tabCases.length; indice++) {
//                nodeCase = nodeZone.item(indice);
//                System.out.println(nodeCase.getTextContent());
//                //caseALire = new Case(nodeCase., y, indiceBateau, estTouche, coule);
//                //tabCases[indice] = caseALire;
//            }
//            
//        } catch (ParserConfigurationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SAXException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    /**
     * TODO commenter le rôle de cette méthode
     * e
     * @param args
     */
    public static void main(String args[] ) {
        Zone zoneDepart = new Zone();
        Coordonnee caseDepart = new Coordonnee(0,0);
        
        Bateau b1 = new Bateau(new Coordonnee(1, 1), new Coordonnee(4, 1), 0, zoneDepart);
        Bateau b2 = new Bateau(new Coordonnee(3, 4), new Coordonnee(3, 7), 1, zoneDepart);
        Bateau b3 = new Bateau(new Coordonnee(7, 7), new Coordonnee(8, 7), 2, zoneDepart);
        
        Flotte flotte = new Flotte();
        flotte.ajouterBateau(b1);
        flotte.ajouterBateau(b2);
        flotte.ajouterBateau(b3);
        
//        zoneDepart.ajouterCoordonnee(b1);
//        zoneDepart.ajouterCoordonnee(b2);
//        zoneDepart.ajouterCoordonnee(b2);

        System.out.println(b1.getZoneCoord().toString());
        
        ArrayList<Coordonnee> test = new ArrayList<Coordonnee>();
               
        test.add(caseDepart);
        test.add(caseDepart);
        
        
       creerSauvegarde(zoneDepart, flotte, test);
//       //lireSauvegarde();
        
        

    }

}
