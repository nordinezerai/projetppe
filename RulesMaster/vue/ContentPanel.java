package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controle.Action;
import modele.CatchMail;
import modele.CatchRules;

public class ContentPanel extends JPanel {

	static final long serialVersionUID = -3799608298065837312L;
	
	private static final Font arial11pt = new Font("Arial",Font.PLAIN,11);
	private static final Insets buttonMargin = new Insets(2, 2, 2, 2);
	public static final Color rulesTableScrollPaneColor = new Color(50);
	
	public Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	public Border border2 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	
	public CatchRules modeleRule; 
	public CatchMail modeleMail;
	public JTable rulesTable;
	public JTable mailsTable;
	public JScrollPane rulesTableScrollPane;
	public JScrollPane mailsTableScrollPane;
	
	private JLabel usedBoxLabel;

	public JButton execButton;
	public JButton addButton;
	public JButton delButton;
	public JButton disconectButton;
	
	public Action action = new Action(this);
	
	public MyComboBox<String> jCBAnalysedBox ;
	
	public AddPanel addPanel;
	public EditPanel editPanel;

	public ContentPanel() {
	
		this.setLayout(null);
		this.setFocusable(true);
		
		usedBoxLabel = new JLabel("Boîte analysé : ");
		usedBoxLabel.setBounds(20, 20, 220, 30);
		
		execButton = new Bouton("Exécuter les règles", this);
		execButton.setFont(arial11pt);
		execButton.setMargin(buttonMargin);
		execButton.setBounds(690, 20, 130, 30);
		
		addButton = new Bouton("Ajouter", this);
		addButton.setFont(arial11pt);
		addButton.setMargin(buttonMargin);
		addButton.setBounds(690, 560, 100, 20);
		addButton.setToolTipText("Ajouter de nouvelles règles");
		
		delButton = new Bouton("Supprimer", this);
		delButton.setFont(arial11pt);
		delButton.setMargin(buttonMargin);
		delButton.setBounds(830, 560, 100, 20);	
		
		disconectButton = new Bouton("Quitter", this);
		disconectButton.setFont(arial11pt);
		disconectButton.setMargin(buttonMargin);
		disconectButton.setBounds(100, 560, 100, 20);	
		
		rulesTable = new JTable();
		mailsTable = new JTable();
		
		jCBAnalysedBox = new MyComboBox<String>( action.getNamesFolders() , this);		
		jCBAnalysedBox.setBounds(110, 20, 280, 30);
		
		modeleMail = action.getTableMail();
		modeleRule = action.getTableRule();
		
		rulesTable.setModel(modeleRule);
		rulesTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );		
		rulesTable.setRowHeight(30);	
		rulesTable.getColumn("Modifier").setCellRenderer(new ButtonRenderer("Modifier", this));	
		rulesTable.getColumn("Modifier").setCellEditor( new ButtonEditor(this) );
		rulesTable.setDefaultRenderer(String.class , new CellRenderer());
		
		mailsTable.setModel(modeleMail);
		mailsTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );		
		mailsTable.setRowHeight(30);
		mailsTable.setDefaultRenderer(String.class , new CellRenderer());
		
		border = BorderFactory.createTitledBorder( border , "Règles", TitledBorder.LEFT , TitledBorder.TOP);		
		border2 = BorderFactory.createTitledBorder( border2 , "Visualisation de la boîte mails", TitledBorder.LEFT , TitledBorder.TOP);	
		
		rulesTableScrollPane = new JScrollPane(rulesTable);
		rulesTableScrollPane.setBounds(10, 90, 975, 210);
		rulesTableScrollPane.setBorder(border);
		
		mailsTableScrollPane = new JScrollPane(mailsTable);
		mailsTableScrollPane.setBounds(10, 320, 975, 210);
		mailsTableScrollPane.setBorder(border2);
		
		addPanel = new AddPanel(this);
		addPanel.setVisible(false);
		
		editPanel = new EditPanel(this);			
		editPanel.setVisible(false);
		
		this.add(usedBoxLabel);				
		this.add(execButton);
		this.add(addButton);
		this.add(delButton);
		this.add(disconectButton);
		this.add(rulesTableScrollPane);
		this.add(mailsTableScrollPane);
		this.add(addPanel);
		this.add(editPanel);	
		this.add(jCBAnalysedBox);
	}
	
	public void refreshTable() { 
		this.remove(rulesTableScrollPane);
		this.remove(mailsTableScrollPane);		
		
		rulesTable = new JTable() ;
		rulesTable.setModel(action.getTableRule());
		rulesTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );		
		rulesTable.setRowHeight(30);	
		rulesTable.getColumn("Modifier").setCellRenderer(new ButtonRenderer("Modifier", this));	
		rulesTable.getColumn("Modifier").setCellEditor( new ButtonEditor(this) );
		rulesTable.setDefaultRenderer(String.class , new CellRenderer());
		
		mailsTable = new JTable() ;
		mailsTable.setModel(action.getTableMail());
		mailsTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );		
		mailsTable.setRowHeight(30);
		mailsTable.setDefaultRenderer(String.class , new CellRenderer());
		
		rulesTableScrollPane = new JScrollPane(rulesTable);
		rulesTableScrollPane = new JScrollPane(rulesTable);
		rulesTableScrollPane.setBounds(10, 90, 975, 210);
		rulesTableScrollPane.setBorder(border);

		mailsTableScrollPane = new JScrollPane(mailsTable);
		mailsTableScrollPane = new JScrollPane(mailsTable);
		mailsTableScrollPane.setBounds(10, 320, 975, 210);
		mailsTableScrollPane.setBorder(border2);
		
		this.add(mailsTableScrollPane);
		this.add(rulesTableScrollPane);		
		this.repaint();
		this.revalidate();
	}
	
	public void ajouter() {
		rulesTableScrollPane.setVisible(false);
		mailsTableScrollPane.setVisible(false);
		jCBAnalysedBox.setEnabled(false);
		execButton.setEnabled(false);
		addButton.setEnabled(false);
		delButton.setEnabled(false);
		addPanel.setVisible(true);
	}
	
	public void modifier() {
		rulesTableScrollPane.setVisible(false);
		mailsTableScrollPane.setVisible(false);
		jCBAnalysedBox.setEnabled(false);
		execButton.setEnabled(false);
		addButton.setEnabled(false);
		delButton.setEnabled(false);
		this.editPanel.tFExp2.setText((String) modeleRule.getValueAt(
				rulesTable.getSelectedRow(), 0));
		this.editPanel.tFSubject2.setText((String) modeleRule.getValueAt(
				rulesTable.getSelectedRow(), 1));
		this.editPanel.tFBody2.setText((String) modeleRule.getValueAt(
				rulesTable.getSelectedRow(), 2));
		this.editPanel.jCBox.setSelectedItem((String) modeleRule.getValueAt(
				rulesTable.getSelectedRow(), 3));
		editPanel.setVisible(true);
	}
}
