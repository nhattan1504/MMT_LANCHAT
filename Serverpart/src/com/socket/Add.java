/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socket;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author ktant
 */
public class Add { 
    public String filePath="/home/ktant/Desktop/lan-chat-app-using-java-master/Assignment_01/Data.xml";
    

    public void addUser(String userName, String passWord){
     
        
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filePath);
 
            Node data = doc.getFirstChild();
             
            Element message = doc.createElement("user");
            
            Element nguoidung = doc.createElement("username"); 
                nguoidung.setTextContent(userName);
            Element matkhau = doc.createElement("password");
                matkhau.setTextContent(passWord);
            
            
            message.appendChild(nguoidung); 
            message.appendChild(matkhau);
            
            data.appendChild(message);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
 
	   } 
           catch(Exception ex){
		System.out.println("Exceptionmodify xml");
	   }
    }
    public boolean checkUser(String username){
        try {   
         File inputFile = new File(filePath);
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);


         NodeList nList = doc.getElementsByTagName("user");


         for (int temp = nList.getLength() - 1; temp >= 0 ; temp--) {
            Node nNode = nList.item(temp);
            

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {     
               Element eElement = (Element) nNode;
               String tempUser = eElement.getElementsByTagName("username").item(0).getTextContent();
               if (tempUser.equals(username)) {
                   return true;
                   }
               else {
                   return false;
               }
            }


         }
      } catch (Exception e) {
         e.printStackTrace();
      }

    return false;

    }
}
    
    

