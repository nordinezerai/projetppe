package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controle.Action;

public class CatchRules extends AbstractTableModel {

	private static final long serialVersionUID = -703532676053729899L;

	public static ArrayList<Rule> rules = new ArrayList<Rule>();
	 
    private final String[] entetes = { "expediteur", "objet", "body", "boite destination", "Modifier"};
    
    private Connection connexion = null;
	private Statement statement = null ;
	private ResultSet resultat = null ;
	 
	private String url = "jdbc:mysql://localhost:3306/rulesmaster";
	private String user = "root";
	private String motDePasse = "";
	
	public Action a;
    
    public CatchRules(Object object, Action action) {
    	super();
    	
    	String nameBox = (String) object ; 
    	a = action;
    	
    	rules.clear();
    	 
    	try {
	   	     connexion = DriverManager.getConnection( url, user, motDePasse );
	   	     statement = connexion.createStatement();
	   	     resultat = statement.executeQuery( "SELECT expediteur, objet, body, boite_destination, boite  FROM rule WHERE ( utilisateur ='"+a.tos.outlookApplication.getCurrentUser().getName()+"' ) AND ( boite ='"+nameBox+"' ) ; " ) ;
	   	     while ( resultat.next() ) {
	   	    	    String expediteur = resultat.getString( "expediteur" );
	   	    	    String objet = resultat.getString( "objet" );
	   	    	    String body = resultat.getString( "body" );
	   	    	    String boite_destination = resultat.getString( "boite_destination" );
	   	    	    String boite = resultat.getString( "boite" );
	   	    	    rules.add(new Rule( expediteur, objet, body, boite_destination, boite));
	   	     }
	   	 } 
	   	 catch ( SQLException e ) {
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

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return rules.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex)
	   	 {
	        case 0:
	            return rules.get(rowIndex).getExpediteur();
	        case 1:
	            return rules.get(rowIndex).getObjet();
	        case 2:
	            return rules.get(rowIndex).getBody();
	        case 3:
	            return rules.get(rowIndex).getBoite_destination();
	        case 4:
	        	return "Modifier";
	        default:
	           return null;
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 4)
    	{
        return true;
    	}
    	else return false;
    }
	
	public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

	public void addRule(Rule rule) {
		try{
		connexion = DriverManager.getConnection( url, user, motDePasse );
	    statement = connexion.createStatement();
	    statement.executeUpdate( "INSERT INTO rule (expediteur, objet, body, boite_destination, boite, utilisateur) VALUES ( '"+rule.getExpediteur()+"', '"+rule.getObjet()+"', '"+rule.getBody()+"', '"+rule.getBoite_destination()+"', '"+rule.getBoite()+"', '"+a.tos.outlookApplication.getCurrentUser().getName()+"');" );
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
		    statement.executeUpdate("DELETE FROM rule WHERE expediteur='"+rule.getExpediteur()+"' AND objet='"+rule.getObjet()+"' AND body='"+rule.getBody()+"' AND boite_destination='"+rule.getBoite_destination()+"' AND boite='"+rule.getBoite()+"' AND utilisateur='"+a.tos.outlookApplication.getCurrentUser().getName()+"' ; " );
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

	public void editRule(Rule rule) {
		
		Rule oldRule = rules.get(a.contentPanel.rulesTable.getSelectedRow());
		
		try{
			connexion = DriverManager.getConnection( url, user, motDePasse );
		    statement = connexion.createStatement();
		    statement.executeUpdate("UPDATE rule SET expediteur='"+rule.getExpediteur()+"' , objet='"+rule.getObjet()+"' , body='"+rule.getBody()+"' , boite_destination='"+rule.getBoite_destination()+"' , boite='"+rule.getBoite()+"' WHERE expediteur='"+oldRule.getExpediteur()+"' AND objet='"+oldRule.getObjet()+"' AND body='"+oldRule.getBody()+"' AND boite_destination='"+oldRule.getBoite_destination()+"' AND boite='"+oldRule.getBoite()+"' AND utilisateur='"+a.tos.outlookApplication.getCurrentUser().getName()+"' ; " );
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
