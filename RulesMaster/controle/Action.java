package controle;

import java.util.ArrayList;


import modele.CatchMail;
import modele.CatchRules;
import modele.Rule;
import vue.ContentPanel;

import com.moyosoft.connector.ms.outlook.folder.FolderType;
import com.moyosoft.connector.ms.outlook.folder.FoldersCollection;
import com.moyosoft.connector.ms.outlook.folder.OutlookFolder;

public class Action {
	
	public ThreadOutlookSession tos;
	
	public ContentPanel contentPanel;
	
	public FoldersCollection FCollection;
	
	public Action(ContentPanel contentPanel) {
		this.contentPanel = contentPanel ;
		this.tos = new ThreadOutlookSession() ;
	}
	
	public String[] getNamesFolders(){
		
		FCollection = tos.outlookApplication.getDefaultFolder(FolderType.INBOX).getFolders();
		
		ArrayList<String> lOF = new ArrayList<String>() ;
		lOF.add(tos.outlookApplication.getDefaultFolder(FolderType.INBOX).getName());
		
		for( int i = 0 ; i < FCollection.size() ; i++ ){
			
			lOF.add("    -"+FCollection.get(i).getName()) ;
			
			if(FCollection.get(i).hasChildren()){	
				
				for(int j = 0 ; j < FCollection.get(i).getFolders().size() ; j++ ){
					lOF.add("        -"+FCollection.get(i).getFolders().get(j).getName());
				}
			}
		}

		String[] str = new String[lOF.size()];
		str = lOF.toArray(str) ;
		
		return str;	
	}
	
	public OutlookFolder getFolderByName(String boxName) {
		
		OutlookFolder of = null ;
		
		ArrayList<OutlookFolder> lAOF = new ArrayList<OutlookFolder>() ;
		
		OutlookFolder inbox = tos.outlookApplication.getDefaultFolder(FolderType.INBOX);
		
		lAOF.add(inbox);

		for(int i = 0 ; i < FCollection.size() ; i++ ){
			lAOF.add(FCollection.get(i)) ;
				if(FCollection.get(i).hasChildren()){
					for(int j = 0 ; j < FCollection.get(i).getFolders().size() ; j++ ){
						lAOF.add(FCollection.get(i).getFolders().get(j));
					}
				}
		}
		
		for(int i = 0 ; i < lAOF.size() ; i++){
			if( boxName.equals( lAOF.get(i).getName() ) ){
				of = lAOF.get(i);
				break;
			}
		}
		
		return of;
	}

	public CatchMail getTableMail() {
		CatchMail mtm = new CatchMail( contentPanel.jCBAnalysedBox.getSelectedItem(), this);
		return mtm;
	}

	public CatchRules getTableRule() {
		CatchRules mtr = new CatchRules( contentPanel.jCBAnalysedBox.getSelectedItem(), this) ;
		return mtr;
	}

	public void executeRule() {
		MatchToken tmr = new MatchToken(this);
		tmr.match();
	}

	public void modifier() {
		contentPanel.modifier();	
	}

	public void ajouter() {
		contentPanel.ajouter();
	}

	public void supprimer() {
		
		Rule rule = contentPanel.modeleRule.rules.get(contentPanel.rulesTable.getSelectedRow());
		contentPanel.modeleRule.removeRule(rule);
		contentPanel.refreshTable();
		
	}

	public void retour() {
		contentPanel.addPanel.retour();
	}

	public void ajouterRegle() {
			
		contentPanel.addPanel.ajouterRegle();
		
		String boite_destination = contentPanel.addPanel.jCBox.getSelectedItem().toString() ;
		String boite = contentPanel.jCBAnalysedBox.getSelectedItem().toString() ;
		
		boite_destination = boite_destination.replace( "        -", "");
		boite_destination = boite_destination.replace( "    -", "");
   	 
		boite = boite.replace( "        -", "");
		boite = boite.replace( "    -", "");

		if (contentPanel.addPanel.a) 
		{
			Rule rule = new Rule(contentPanel.addPanel.tFExp.getText(), contentPanel.addPanel.tFSubject
					.getText(), contentPanel.addPanel.tFBody.getText(), boite_destination, boite);
			contentPanel.modeleRule.addRule(rule);
			contentPanel.refreshTable();
			contentPanel.addPanel.retour();
		}		
	}

	public void annuler() {
		contentPanel.editPanel.annuler();
		contentPanel.refreshTable();
	}

	public void editer() {
		contentPanel.editPanel.editer();
		
		String boite_destination = contentPanel.editPanel.jCBox.getSelectedItem().toString() ;
		String boite = contentPanel.jCBAnalysedBox.getSelectedItem().toString() ;
		
		boite_destination = boite_destination.replace( "        -", "");
		boite_destination = boite_destination.replace( "    -", "");
   	 
		boite = boite.replace( "        -", "");
		boite = boite.replace( "    -", "");
		
		if (contentPanel.editPanel.a) 
		{
			Rule rule = new Rule(contentPanel.editPanel.tFExp2.getText(), contentPanel.editPanel.tFSubject2
					.getText(), contentPanel.editPanel.tFBody2.getText(), boite_destination, boite);
			contentPanel.modeleRule.editRule(rule);
			contentPanel.refreshTable();
			contentPanel.editPanel.annuler();
		}		
	}
	
	
}
