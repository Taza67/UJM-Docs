package connexion;

import java.io.File;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParamBD {

	protected static String bdURL;
	protected static String bdLogin;
	protected static String bdPassword;

	public static void init(ServletContext context) {
		try {
			Class.forName(context.getInitParameter("JDBC_DRIVER"));
			bdURL = context.getInitParameter("JDBC_URL");
			bdLogin = context.getInitParameter("JDBC_LOGIN");
			bdPassword = context.getInitParameter("JDBC_PASSWORD");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void init(String xmlFileName) {
	    try {
	        File inputFile = new File(xmlFileName);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();

	        NodeList nodeList = doc.getElementsByTagName("context-param");

	        for (int i = 0; i < nodeList.getLength(); i++) {
	            Node node = nodeList.item(i);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	                Element element = (Element) node;
	                String paramName = element.getElementsByTagName("param-name").item(0).getTextContent();
	                String paramValue = element.getElementsByTagName("param-value").item(0).getTextContent();
	                switch (paramName) {
	                    case "JDBC_DRIVER":
	                        Class.forName(paramValue);
	                        break;
	                    case "JDBC_URL":
	                        bdURL = paramValue;
	                        break;
	                    case "JDBC_LOGIN":
	                        bdLogin = paramValue;
	                        break;
	                    case "JDBC_PASSWORD":
	                        bdPassword = paramValue;
	                        break;
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}

}

