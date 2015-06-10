package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controle.ConnectServlet;

public class CatchRules {

	public static ArrayList<Rule> rules = new ArrayList<Rule>();
    
    private Connection connexion = null;
	private Statement statement = null ;
	private ResultSet resultat = null ;
	 
	private String url = "jdbc:mysql://localhost:3306/rulesmasterweb";
	private String user = "root";
	private String motDePasse = "";
	private String id = ConnectServlet.id ;
    
    public CatchRules() {
    	 
    	rules.clear();
    	  
    	try {
    		 Class.forName("com.mysql.jdbc.Driver") ;
	   	     connexion = DriverManager.getConnection( url, user, motDePasse );
	   	     statement = connexion.createStatement();
	   	     resultat = statement.executeQuery( "SELECT expediteur, objet, body, boite_destination, boite  FROM rule WHERE ( utilisateur ='"+id+"' ) ; " ) ;
	   	     
	   	     while ( resultat.next() ) {
	   	    	    String expediteur = resultat.getString( "expediteur" );
	   	    	    String objet = resultat.getString( "objet" );
	   	    	    String body = resultat.getString( "body" );
	   	    	    String boite_destination = resultat.getString( "boite_destination" );
	   	    	    String boite = resultat.getString( "boite" );
	   	    	    rules.add(new Rule( expediteur, objet, body, boite_destination, boite));
	   	     }
	   	 } 
	   	 catch ( SQLException | ClassNotFoundException e ) {
	   	     e.printStackTrace();
	   	 } 
	   	 finally {
	   		 if ( resultat != null ) {
	   		        try {
	   		            resultat.close();
	   		        } 
	   		        catch ( SQLException ignore ) {}
	   		    }
	   		    if ( statement != null ) {
	   		        try {
	   		            statement.close();
	   		        } 
	   		        catch ( SQLException ignore ) {}
	   		    }
	   		 if ( connexion != null ) {
	   	         try {    	             
	   	             connexion.close();
	   	         } 
	   	     	 catch ( SQLException ignore ) {}
	   		 }
	   	 }    	
    }

	public void addRule(Rule rule) {
		try{
			connexion = DriverManager.getConnection( url, user, motDePasse );
	    	statement = connexion.createStatement();
	    	statement.executeUpdate( "INSERT INTO rule (expediteur, objet, body, boite_destination, boite, utilisateur) VALUES ( '"+rule.getExpediteur()+"', '"+rule.getObjet()+"', '"+rule.getBody()+"', '"+rule.getBoite_destination()+"', '"+rule.getBoite()+"', '"+id+"');" );
		}
		catch ( SQLException e ) {
	   	     e.printStackTrace();
		}
	    
		if ( statement != null ) {
	        try {
	            statement.close();
	        } 
	        catch ( SQLException ignore ) {}
	    }
     	
		if ( connexion != null ) {
     		try {    	             
     			connexion.close();
     		} 
     		catch ( SQLException ignore ) {}
	    }
	}

	public void removeRule(Rule rule) {
		try{
			connexion = DriverManager.getConnection( url, user, motDePasse );
		    statement = connexion.createStatement();
		    statement.executeUpdate("DELETE FROM rule WHERE expediteur='"+rule.getExpediteur()+"' AND objet='"+rule.getObjet()+"' AND body='"+rule.getBody()+"' AND boite_destination='"+rule.getBoite_destination()+"' AND boite='"+rule.getBoite()+"' AND utilisateur='"+id+"' ; " );
		}
		catch ( SQLException e ) {
		   	     e.printStackTrace();
		}
		    
		if ( statement != null ) {
		    try {
		    	statement.close();
		    } 
		    catch ( SQLException ignore ) {}
		}
	    
		if ( connexion != null ) {
	         try {    	             
	             connexion.close();
	         } 
	     	 catch ( SQLException ignore ) {}
		}
			
	}
}
