package modele;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;

import controle.ConnectServlet;

public class CatchMail {

	public static ArrayList<Mail> mails = new ArrayList<Mail>();
	public static ArrayList<Message> messages = new ArrayList<Message>();
	
	public CatchMail(String box) {

    	 String nameBox = box ; 
    	 
    	 Folder folder = null ;
    	 
    	 try {
			folder = ConnectServlet.store.getDefaultFolder().getFolder(nameBox);
		
	    	 if(!folder.isOpen()) {
	    		 folder.open(Folder.READ_WRITE);
	    	 }
    	 
	    	 for(Message message : folder.getMessages()) {
	    		 String expediteur = message.getFrom().toString() ;
	    		 String objet = message.getSubject() ;
	    		 String body = getMessage(message) ;
	    		 String date_recu = message.getReceivedDate().toString() ;
	    		 messages.add(message);
	    		 mails.add(new Mail( expediteur, objet, body, date_recu));
	    	 }
    	 }
    	 
    	 catch(MessagingException e) {
    		 e.printStackTrace();
    	 }
	 }
	
	public String getMessage(Message mail){
		String msg_body = null ;
		
		Message message = mail;
		
		try{
			if(message.getContent() instanceof MimeMultipart)
			{
				MimeMultipart mime = (MimeMultipart) message.getContent();
				BodyPart body = mime.getBodyPart(0);
	
				InputStream instr=body.getDataHandler().getDataSource().getInputStream();
				InputStreamReader ipsr = new InputStreamReader(instr) ;
				BufferedReader br = new BufferedReader(ipsr);
				
				String ligne_mimemessage="";
				
					while ((ligne_mimemessage = br.readLine()) != null)
					{
							msg_body += ligne_mimemessage+"\r\n";
					}
	
					br.close();
					ipsr.close();
					instr.close();
			}
			
			else if(message.getContent() instanceof Multipart )
			{
				Multipart multipart = (Multipart)message.getContent(); 
						 
					if(multipart.getBodyPart(0).getContentType().contains("text/html") || multipart.getBodyPart(0).getContentType().contains("text/plain") )
					{
							msg_body=(String)multipart.getBodyPart(0).getContent();
					}	
			}
			
			else 
			{
				msg_body=(String)message.getContent();
			}
	
			byte[] utf8Bytes = msg_body.getBytes();
			
	        msg_body = new String(utf8Bytes, "utf-8");
	        
	        msg_body = msg_body.replaceAll("&quot;","\"");
			
	        msg_body = StringUtils.stripAccents( msg_body );
			
	        if(msg_body == null){msg_body="";}
		}
        catch(Exception e) {
        	e.printStackTrace();
        }
		return msg_body;
	}
}
