package controle;

import java.util.ArrayList;

import com.moyosoft.connector.ms.outlook.folder.OutlookFolder;
import com.moyosoft.connector.ms.outlook.item.ItemsCollection;

import modele.Mail;
import modele.Rule;

public class MatchToken 
{
	private Action action;
	
	public MatchToken(Action action)
	{
		this.action = action;
	}
	 
	public void match()  
	{		
		
		ArrayList<Rule> rules = action.contentPanel.modeleRule.rules;
		
		ArrayList<Mail> mails = action.contentPanel.modeleMail.mails;
		
		ItemsCollection ic = action.contentPanel.modeleMail.ic ;
		
			for ( int i = 0 ; i < rules.size() && rules != null ; i++ ) 
			{
				Rule rule = rules.get(i);
				
				String destinationFolder = rule.getBoite_destination();
				
				destinationFolder = destinationFolder.replace( "        -", "");
				destinationFolder = destinationFolder.replace( "    -", "");
				
				for( int j = 0 ; j < mails.size() ; j++ ){
						
							boolean a = false;
							boolean b = false;
							boolean c = false;
							
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
							
							if( a == true && b == true && c == true )
							{
									 OutlookFolder of = action.getFolderByName(destinationFolder) ;
									 
									 ic.get(j).move(of);
							}							
				}
			
			}
			
			action.contentPanel.refreshTable() ;
	}
}
