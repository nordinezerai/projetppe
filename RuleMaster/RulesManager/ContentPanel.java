package RulesManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.apache.commons.lang3.StringUtils;

import MessengerConect.ConnectPanel;
 
public class ContentPanel extends JPanel 
{
	private static final long serialVersionUID = -614913619452345835L;
	
	private static final Font arial11pt = new Font("Arial",Font.PLAIN,11);
	private static final Insets buttonMargin = new Insets(2, 2, 2, 2);
	public static final Color rulesTableScrollPaneColor = new Color(50);
	
	public static final String[] title = {"Expéditeur", "Sujet", "Corps du Message","Boîte Destination","Matchs","Modifier"};
	public static Object[][] donnees;
	
	private Rule oldRuleToEdit ;
	private Rule newRuleEdited ;

	public IHM ihm;
	public ModeleDynamiqueObjet modele; 
	public JTable rulesTable;
	public JScrollPane rulesTableScrollPane; 
	private JLabel usedBoxLabel;

	private JButton loadButton;
	private JButton testButton;
	private JButton execButton;
	private JButton addButton;
	private JButton delButton;
	private JButton disconectButton;
	
	public MyComboBox<String> jCBAnalysedBox= new MyComboBox<String>(ConnectPanel.listBox,this);

	private AddPanel addPanel;
	private EditPanel editPanel;

	public ContentPanel(IHM ihm) throws Exception
	{	
		this.ihm=ihm;
		
		this.setLayout(null);
		this.setFocusable(true);
		usedBoxLabel = new JLabel("Boîte analysé : ");
		usedBoxLabel.setBounds(20, 20, 220, 30);
		
		jCBAnalysedBox.setBounds(110, 20, 220, 30);
	
		loadButton = new Bouton("Charger l'état courant", this);
		loadButton.setFont(arial11pt);
		loadButton.setMargin(buttonMargin);
		loadButton.setBounds(550, 20, 130, 30);		
		
		testButton = new Bouton("Tester les règles", this);
		testButton.setFont(arial11pt);
		testButton.setMargin(buttonMargin);
		testButton.setBounds(690, 20, 130, 30);
		
		execButton = new Bouton("Exécuter les règles", this);
		execButton.setFont(arial11pt);
		execButton.setMargin(buttonMargin);
		execButton.setBounds(830,20,130,30);
		
		addButton = new Bouton("Ajouter", this);
		addButton.setFont(arial11pt);
		addButton.setMargin(buttonMargin);
		addButton.setBounds(690, 560, 100, 20);
		
		delButton = new Bouton("Supprimer", this);
		delButton.setFont(arial11pt);
		delButton.setMargin(buttonMargin);
		delButton.setBounds(830, 560, 100, 20);	
		
		disconectButton = new Bouton("Deconnection", this);
		disconectButton.setFont(arial11pt);
		disconectButton.setMargin(buttonMargin);
		disconectButton.setBounds(100, 560, 100, 20);	
		
		rulesTable = new JTable();
		
		modele = new ModeleDynamiqueObjet(ConnectPanel.psw,ConnectPanel.id,ConnectPanel.listBox2[jCBAnalysedBox.getSelectedIndex()]);
		
		rulesTable.setModel(modele);
		rulesTable.setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );		
		rulesTable.setRowHeight(30);
		rulesTable.getColumn("Modifier").setCellRenderer(new ButtonRenderer("Modifier"));	
		rulesTable.getColumn("Modifier").setCellEditor( new ButtonEditor(this) );
	
		rulesTableScrollPane = new JScrollPane(rulesTable);
		rulesTableScrollPane.setBounds(10, 90, 975, 410);
		rulesTableScrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rulesTableScrollPaneColor));
		
		addPanel = new AddPanel( this );
		addPanel.setVisible(false);
		editPanel = new EditPanel( this );			
		editPanel.setVisible(false);
		
		jCBAnalysedBox.setSelectedIndex(1);
		
		this.add(usedBoxLabel);
		this.add(jCBAnalysedBox);
		this.add(loadButton);		
		this.add(testButton);
		this.add(execButton);
		this.add(addButton);
		this.add(delButton);
		this.add(disconectButton);
		this.add(rulesTableScrollPane);			
		this.add(addPanel);
		this.add(editPanel);
	}
	
	public void retour() 
	{
		addPanel.retour();
		rulesTableScrollPane.setVisible(true);
		loadButton.setEnabled(true);
		execButton.setEnabled(true);
		testButton.setEnabled(true);
		addButton.setEnabled(true);
		delButton.setEnabled(true);		
	}

	public void initAjouter() 
	{
		rulesTableScrollPane.setVisible(false);
		loadButton.setEnabled(false);
		execButton.setEnabled(false);
		testButton.setEnabled(false);
		addButton.setEnabled(false);
		delButton.setEnabled(false);
		addPanel.initAjouter();
	}

	public void supprimer() throws IOException 
	{
		 int[] selection = rulesTable.getSelectedRows();
		 if(selection.length == 0){}
		 
		 else
		 {
		
			 JDialog.setDefaultLookAndFeelDecorated(true);
			 int response = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment continuer ?", "Confirmation",
	         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			 if (response == JOptionPane.NO_OPTION) 
			 {
	      
			 } 
	   
			 else if (response == JOptionPane.YES_OPTION) 
			 {
  
				   for(int i = selection.length - 1; i >= 0; i--)
				   {
					 modele.getRules().remove(selection[i]);
					 modele.updateCsv(modele.rules);
					 rulesTable.revalidate();
				   }
				   
				   modele.fireTableRowsDeleted(rulesTable.getSelectedRows()[0], (rulesTable.getSelectedRows()[0]+rulesTable.getSelectedRows().length)-1);
				   rulesTable.revalidate();		   
			 } 
	    
			 else if (response == JOptionPane.CLOSED_OPTION) 
			 {
	     
			 }
		}
	}

	public void modifier() 
	{
		rulesTableScrollPane.setVisible(false);
		loadButton.setEnabled(false);
		execButton.setEnabled(false);
		testButton.setEnabled(false);
		addButton.setEnabled(false);
		delButton.setEnabled(false);
		editPanel.setVisible(true);
		oldRuleToEdit = editPanel.modifier();
	}

	public void annuler() 
	{
		editPanel.setVisible(false);
		rulesTableScrollPane.setVisible(true);
		loadButton.setEnabled(true);
		execButton.setEnabled(true);
		testButton.setEnabled(true);
		addButton.setEnabled(true);
		delButton.setEnabled(true);
	}

	public void editer() throws IOException 
	{
		
		newRuleEdited = editPanel.getNewRule();
		
		if(!newRuleEdited.compareRule(oldRuleToEdit))
		{
			JDialog.setDefaultLookAndFeelDecorated(true);
		    int response = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment continuer ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) 
		    {
		        System.out.println("No button clicked");
		    } 
		    
		    else if (response == JOptionPane.YES_OPTION) 
		    {
		    	modele.rules.set(rulesTable.getSelectedRow(), newRuleEdited);
		    	modele.updateCsv(modele.rules);
				editPanel.setVisible(false);
				rulesTableScrollPane.setVisible(true);
				loadButton.setEnabled(true);
				execButton.setEnabled(true);
				testButton.setEnabled(true);
				addButton.setEnabled(true);
				delButton.setEnabled(true);
		    } 
		    
		    else if (response == JOptionPane.CLOSED_OPTION) 
		    {
		      System.out.println("JOptionPane closed");
		    }
		}
		
		else
		{
			annuler();
		}
	}
	
	public static Object[][] getDonnees() 
	{
		return donnees;
	}

	public static void setDonnees(Object[][] donnees) 
	{
		ContentPanel.donnees = donnees;
	}

	public ModeleDynamiqueObjet getModele() 
	{
		return modele;
	}

	public void setModele(ModeleDynamiqueObjet modele) 
	{
		this.modele = modele;
	}

	public JTable getRulesTable() 
	{
		return rulesTable;
	}

	public void setRulesTable(JTable rulesTable) 
	{
		this.rulesTable = rulesTable;
	}

	public JScrollPane getRulesTableScrollPane() 
	{
		return rulesTableScrollPane;
	}

	public void setRulesTableScrollPane(JScrollPane rulesTableScrollPane) 
	{
		this.rulesTableScrollPane = rulesTableScrollPane;
	}

	public static String[] getTitle() 
	{
		return title;
	}

	public void refreshTable() throws IOException 
	{
		this.remove(rulesTableScrollPane);
		modele = new ModeleDynamiqueObjet(ConnectPanel.psw,ConnectPanel.id,ConnectPanel.listBox2[jCBAnalysedBox.getSelectedIndex()]);
		rulesTable = new JTable();
		rulesTable.setModel(modele);
		rulesTable.setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );		
		rulesTable.setRowHeight(30);
		rulesTable.getColumn("Modifier").setCellRenderer(new ButtonRenderer("Modifier"));	
		rulesTable.getColumn("Modifier").setCellEditor( new ButtonEditor(this) );
		rulesTableScrollPane = new JScrollPane(rulesTable);
		rulesTableScrollPane.setBounds(10, 90, 975, 410);
		rulesTableScrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rulesTableScrollPaneColor));
		this.add(rulesTableScrollPane);
		ihm.revalidate();
		ihm.repaint();
		
	}
}
