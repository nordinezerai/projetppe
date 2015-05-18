package RulesManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.TextField;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MessengerConect.ConnectPanel;

public class EditPanel extends JPanel 
{

	private ContentPanel parentPanel;
	
	private static final long serialVersionUID = -841724517580827299L;
	private static JButton retour2Button;
	private static JButton editButton;

	public static TextField tFExp2 = new TextField();
	public static TextField tFSubject2 = new TextField();
	public static TextField tFBody2 = new TextField();
	
	private static JComboBox<String> jCBox = new JComboBox<String>(ConnectPanel.listBox2);

	private static JLabel lExp2 = new JLabel("Expediteur : ");
	private static JLabel lSubject2 = new JLabel("Objet : ");
	private static JLabel lBody2 = new JLabel("Sujet : ");
	private static JLabel lBox2 = new JLabel("Boite : ");

	private static final Color rulesTableScrollPaneColor = new Color(50);
	private static final Font arial11pt = new Font("Arial", Font.PLAIN, 11);
	private static final Insets buttonMargin = new Insets(2, 2, 2, 2);

	public EditPanel( ContentPanel parentPanel ) 
	{
		this.parentPanel = parentPanel;
		
		tFExp2.setBounds(180, 100, 150, 30);
		tFSubject2.setBounds(420, 100, 230, 30);
		tFBody2.setBounds(180, 150, 300, 30);
		jCBox.setBounds(180, 200, 150, 30);
		jCBox.setSelectedItem("Sélectionnez une boîte");

		lExp2.setBounds(100, 100, 150, 30);
		lSubject2.setBounds(370, 100, 150, 30);
		lBody2.setBounds(100, 150, 300, 30);
		lBox2.setBounds(100, 200, 150, 30);

		retour2Button = new Bouton("Annuler", this);
		retour2Button.setFont(arial11pt);
		retour2Button.setMargin(buttonMargin);
		retour2Button.setBounds(430, 350, 100, 20);

		editButton = new Bouton("Editer", this);
		editButton.setFont(arial11pt);
		editButton.setMargin(buttonMargin);
		editButton.setBounds(550, 350, 100, 20);
		
		this.setLayout(null);
		this.setBounds(150, 100, 700, 400);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,rulesTableScrollPaneColor));
		this.add(retour2Button);
		this.add(editButton);
		this.add(tFExp2);
		this.add(tFSubject2);
		this.add(tFBody2);
		this.add(jCBox);
		this.add(lExp2);
		this.add(lSubject2);
		this.add(lBody2);
		this.add(lBox2);
		this.setVisible(true);
	}

	public Rule modifier() 
	{
		tFExp2.setText((String) parentPanel.getModele().getValueAt(
				parentPanel.getRulesTable().getSelectedRow(), 0));
		tFSubject2.setText((String) parentPanel.getModele().getValueAt(
				parentPanel.getRulesTable().getSelectedRow(), 1));
		tFBody2.setText((String) parentPanel.getModele().getValueAt(
				parentPanel.getRulesTable().getSelectedRow(), 2));
		jCBox.setSelectedItem((String) parentPanel.getModele().getValueAt(
				parentPanel.getRulesTable().getSelectedRow(), 3));
		Rule oldRuleToEdit = new Rule(tFExp2.getText(), tFSubject2.getText(),
				tFBody2.getText(), jCBox.getSelectedItem().toString(), "0","");
		return oldRuleToEdit;
	}

	public void annuler() 
	{
		parentPanel.annuler();
	}

	public void editer() throws IOException 
	{
		parentPanel.editer();
	}
	
	public Rule getNewRule() 
	{
		Rule newRuleEdited = new Rule(tFExp2.getText(), tFSubject2.getText(),
				tFBody2.getText(), jCBox.getSelectedItem().toString(), "0","");
		return newRuleEdited;
	}
}
