package fr.isilis.tools.opc;

import java.util.regex.Pattern;

import microsoft.exchange.webservices.data.EmailMessage;
import fr.isilis.tools.opc.rule.Rule;
import fr.isilis.tools.opc.rule.RulesManager;

public class MatchRules 
{
	
	private boolean a = false;
	private boolean b = false;
	private boolean c = false;

	public void matchRule(EmailMessage emailMessage) throws Exception 
	{			
		
		RulesManager rulesMngr = new RulesManager();
		rulesMngr.init();
		
		for ( int i = 0 ; i < rulesMngr.getRules().length && rulesMngr.getRules() != null ; i++ ) 
		{
			Rule rule = rulesMngr.getRules()[i];
			
			for( int j = 0 ; rule.getKeywords() != null && j < rule.getKeywords().length && this.a == false && emailMessage.getBody().toString() != null ; j++ )
			{		
				for(int l = 0 ; l < rule.getKeywords()[j].split(":")[l].length()  && this.a == true; l++ )
				{
					 this.a = Pattern.matches("\\s+|\\W|&nbsp;"+rule.getKeywords()[j].split(":")[l]+"\\s+|\\W|&nbsp;", emailMessage.getBody().toString());				
				}
			}
			
			for( int k = 0 ; rule.getObjectKeywords() != null && k < rule.getObjectKeywords().length && this.b == false && emailMessage.getSubject().toString() != null ; k++ )
			{		
				for( int l = 0 ; l < rule.getObjectKeywords()[k].split(":")[l].length()  && this.b == true; l++ )
				{
					 this.b = Pattern.matches("\\s+|\\W|&nbsp;"+rule.getObjectKeywords()[k].split(":")[l]+"\\s+|\\W|&nbsp;", emailMessage.getSubject());					
				}		
			}
			
			if( emailMessage.getFrom() != null && rule.getFrom() != null )
			{
				this.c = ( emailMessage.getFrom().toString() == rule.getFrom() ) ;
			}
			
			if( this.a == true && this.b == true && this.c == true && StoreManager.idByFolderNames.containsKey(rule.getFolderName()) )
			{
				emailMessage.move(StoreManager.idByFolderNames.get(rule.getFolderName()));
			}
		}
	}
}


