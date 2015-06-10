package controle;

import java.util.ArrayList;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

import modele.CatchMail;
import modele.CatchRules;
import modele.Mail;
import modele.Rule;

public class MatchToken 
{
	
	public MatchToken()
	{

	}
	 
	public int match()  
	{		
		
		ArrayList<Rule> rules = CatchRules.rules;
		int numMatch = 0;
		
			for ( int i = 0 ; i < rules.size() && rules != null ; i++ ) 
			{
				Rule rule = rules.get(i);
				
				String box = rule.getBoite();
				box = box.replaceFirst("Boîte de réception","Inbox");
				box = box.replaceAll(" - ","/");
				
				CatchMail cm = new CatchMail(box);
				
				ArrayList<Mail> mails = CatchMail.mails;
				
				String boite_destination = rule.getBoite_destination();
				boite_destination = boite_destination.replace("Boîte de réception","Inbox");
				boite_destination = boite_destination.replace(" - ","/");
				
				for( int j = 0 ; j < mails.size() ; j++ ){
						
							boolean a = false;
							boolean b = false;
							boolean c = false;
							boolean messageHasBeenMoved = false;
							
							Mail mail;
							
							mail = mails.get(j);
							 							
							if( mail.getBody() != null && mail.getBody() != "" && rule.getBody() != null && rule.getBody() != "" )
							{		
									 a = mail.getBody().contains(rule.getBody());			
							}
							
							else if( rule.getBody() == null || rule.getBody() == "" ){ a = true ; }
							
							if( mail.getObjet() != null && mail.getObjet() != "" && rule.getObjet() != null && rule.getObjet() != "" )
							{		
									 b = mail.getObjet().contains(rule.getObjet());	 						
							}
							
							else if( rule.getObjet() == null || rule.getObjet() == "" ){ b = true ; }
							
							if( mail.getFrom() != null && mail.getFrom() != "" && rule.getExpediteur() != null && rule.getExpediteur() != "" )
							{
								     c = mail.getFrom().contains(rule.getExpediteur()) ;
							}
							
							else if( rule.getExpediteur() == null || rule.getExpediteur() == ""){ c = true ;}
							
							try {
								if( a == true && b == true && c == true ) {
										Folder destinationFolder;
									
										destinationFolder = ConnectServlet.store.getDefaultFolder().getFolder(boite_destination);								 
									
										CatchMail.messages.get(j).getFolder().copyMessages(new Message[] {CatchMail.messages.get(j)}, destinationFolder);
										
										messageHasBeenMoved = true;
								}								
								
								if(messageHasBeenMoved) {
									
										CatchMail.messages.get(j).setFlag(Flags.Flag.DELETED, true);	
										numMatch++;
								}
							}
							catch (MessagingException e) {
								e.printStackTrace();
							}
				}			
			}
			return numMatch ;
	}
}

