package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.CatchRules;

public class ConnectServlet extends HttpServlet {
		private Session session = null;
	
		public static Store store = null;
    
		private Folder inbox = null;
		
		public static CatchRules cr ;
    
		public static ArrayList<Folder> boxs = new ArrayList<Folder>();
    
		public static String[] boxNames ;

		public static String id;
		
		private String psw;
		
		private String message;
		
		public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
				
				if(request.getParameter("email").isEmpty() && request.getParameter("motdepasse").isEmpty()) {
					
					message = "Veuillez entrez vos identifiants";
					
					request.setAttribute("message", message);
					
					this.getServletContext().getRequestDispatcher( "/WEB-INF/login.jsp" ).forward( request, response );
				}
				
				else{
						try {			
						    
							Properties props = new Properties();	
							
							id=request.getParameter("email");
							psw=request.getParameter("motdepasse");
							
							props.setProperty("mail.pop.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
							props.setProperty("mail.pop.socketFactory.port", "993");
			
							session = Session.getInstance(props, null);
			
							store = session.getStore("imaps");
							store.connect("imap-mail.outlook.com",id,psw);
							inbox = store.getDefaultFolder().getFolder("INBOX");
							
							boxs.add(inbox);
							
							ArrayList<String> listBox = new ArrayList<String>();
							listBox.add("Selectionnez une boite");
							listBox.add("Boîte de réception") ;
							
							Folder[] folders = inbox.list();
			    	
					    	for(Folder folder : folders ) {				
					    		listBox.add(folder.getFullName());
					    		boxs.add(folder);
					    		if(folder.list().length != 0) {
					    			for(int j = 0 ; j < folder.list().length ; j++ ){
					    				listBox.add(folder.list()[j].getFullName());
					    				boxs.add(folder.list()[j]);
					    			}
					    		}
							} 
					    	
					    	for(int i = 0 ; i < listBox.size() ; i++) {
					    		listBox.set(i , listBox.get(i).replaceFirst("Inbox", "Boîte de réception"));
					    		listBox.set(i , listBox.get(i).replaceAll("/"," - "));
					    	}
					    	boxNames = new String[listBox.size()];
					    	boxNames = listBox.toArray(boxNames);
						} 		
						
					    catch (Exception e) {
							e.printStackTrace();
							message = "Identifiants incorrect !";
							request.setAttribute("message", message);
							this.getServletContext().getRequestDispatcher( "/WEB-INF/login.jsp" ).forward( request, response );
						}
					
					cr = new CatchRules();
					
					this.getServletContext().getRequestDispatcher( "/WEB-INF/rules.jsp" ).forward( request, response );
				}
		}

}
