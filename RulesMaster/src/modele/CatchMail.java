package modele;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.moyosoft.connector.ms.outlook.folder.OutlookFolder;
import com.moyosoft.connector.ms.outlook.item.ItemsCollection;
import com.moyosoft.connector.ms.outlook.mail.OutlookMail;

import controle.Action;

public class CatchMail extends AbstractTableModel{
	
	private static final long serialVersionUID = 9064147467172497191L;

	public ArrayList<Mail> mails = new ArrayList<Mail>();
	
	public ItemsCollection ic ;
	
	private String[] entetes = {"expediteur","objet","date de réception"};
	
	 public CatchMail(Object object, Action action) {
    	 super();
    	 
    	 String nameBox = (String) object ;
    	 
    	 nameBox = nameBox.replace( "        -", "");
    	 nameBox = nameBox.replace( "    -", "");    	 
    	 
    	 OutlookFolder of = action.getFolderByName(nameBox);
    	 
    	 ic = of.getItems();
    	 
    	 for( int i = 0 ; i < ic.size() ; i++ ){
    		 String expediteur = ((OutlookMail)ic.get(i)).getFrom() ;
    		 String objet = ((OutlookMail)ic.get(i)).getSubject() ;
    		 String body = ((OutlookMail)ic.get(i)).getBody() ;
    		 String date_recu = ((OutlookMail)ic.get(i)).getReceivedTime().toString() ;
    		 mails.add( new Mail( expediteur, objet, body, date_recu) ) ;
    	 }
    	 
	 }

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return mails.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
        case 0:
            return mails.get(rowIndex).getFrom();
        case 1:
            return mails.get(rowIndex).getObjet();
        case 2:
            return mails.get(rowIndex).getDate();
        default:
           return null; 
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
	
	public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

}
