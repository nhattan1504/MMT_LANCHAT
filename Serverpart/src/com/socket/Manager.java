
package com.socket;

import static com.socket.Database.getTagValue;
import java.io.File;
import javax.swing.table.DefaultTableModel;
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
 * 
 */
public class Manager {
    
    
    public String filePath="/home/ktant/Documents/MMT/Ass1/lan-chat-app-using-java-master/Assignment_01/Data.xml";
    public void FillTable(GUI_Manager frame){
      
        DefaultTableModel model = (DefaultTableModel) frame.jTable1.getModel();
    
        try{
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("user");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    model.addRow(new Object[]{getTagValue("username", eElement), getTagValue("password", eElement)});
                }
            }
        }
        catch(Exception ex){
            System.out.println("Filling Exception");
        }
    }
    public void Delete(String username, String password){
        // dau tien can check xem data da ton tai username hay chua sau do lay row nay add vo file data 
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filePath);
 
            Node data = doc.getFirstChild();
            
            Element newuser = doc.createElement("user");
            Element newusername = doc.createElement("username"); newusername.setTextContent(username);
            Element newpassword = doc.createElement("password"); newpassword.setTextContent(password);
            
            newuser.appendChild(newusername); newuser.appendChild(newpassword); data.appendChild(newuser);
            
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
        
    }



