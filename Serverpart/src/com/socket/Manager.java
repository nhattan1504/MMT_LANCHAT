
package com.socket;

import static com.socket.Database.getTagValue;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * 
 */
public class Manager {
    
    
    public String filePath="/home/ktant/Desktop/lan-chat-app-using-java-master/Assignment_01/Data.xml";
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
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
         
        Document doc = db.parse(new FileInputStream(new File(filePath)));
         
        // retrieve the element 'link'
        Element element = (Element) doc.getElementsByTagName("username").item(0);
 
        // remove the specific node
        element.getParentNode().removeChild(element);
         
        // Normalize the DOM tree, puts all text nodes in the
        // full depth of the sub-tree underneath this node
        doc.normalize();
        
        }
           catch(Exception ex){
		System.out.println("Exceptionmodify xml");
	   }
	
	}
        
    }



