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

public class AddPanel extends JPanel 
{
	
	private static final long serialVersionUID = 7875064804654757936L;

	private ContentPanel parentPanel;

	private static JButton retourButton;
	private static JButton ajoutButton;

	private static TextField tFExp = new TextField();
	private static TextField tFSubject = new TextField();
	private static TextField tFBody = new TextField();
	private static JComboBox<String> jCBox = new JComboBox<String>(ConnectPanel.listBox2);
	
	private static JLabel lExp = new JLabel("Expediteur : ");
	private static JLabel lSubject = new JLabel("Objet : ");
	private static JLabel lBody = new JLabel("Sujet : ");
	private static JLabel lBox = new JLabel("Boite : ");

	private static final Color rulesTableScrollPaneColor = new Color(50);
	private static final Font arial11pt = new Font("Arial", Font.PLAIN, 11);
	private static final Insets buttonMargin = new Insets(2, 2, 2, 2);

	public AddPanel( ContentPanel parentPanel ) 
	{
		this.parentPanel = parentPanel;
		
		tFExp.setBounds(180, 100, 150, 30);
		tFSubject.setBounds(420, 100, 230, 30);
		tFBody.setBounds(180, 150, 300, 30);
		jCBox.setBounds(180, 200, 150, 30);
		jCBox.setSelectedItem("Sélectionnez une boîte");

		lExp.setBounds(100, 100, 150, 30);
		lSubject.setBounds(370, 100, 150, 30);
		lBody.setBounds(100, 150, 300, 30);
		lBox.setBounds(100, 200, 150, 30);

		retourButton = new Bouton("Retour", this);
		retourButton.setFont(arial11pt);
		retourButton.setMargin(buttonMargin);
		retourButton.setBounds(430, 350, 100, 20);

		ajoutButton = new Bouton("Ajouter la règle", this);
		ajoutButton.setFont(arial11pt);
		ajoutButton.setMargin(buttonMargin);
		ajoutButton.setBounds(550, 350, 100, 20);

		this.setVisible(false);
		this.setLayout(null);
		this.setBounds(150, 100, 700, 400);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,rulesTableScrollPaneColor));
		this.add(tFExp);
		this.add(tFSubject);
		this.add(tFBody);
		this.add(jCBox);
		this.add(lExp);
		this.add(lSubject);
		this.add(lBody);
		this.add(lBox);
		this.add(retourButton);
		this.add(ajoutButton);
	}

	public void initAjouter() 
	{
		this.setVisible(true);
	}

	public void ajouterRegle() throws IOException 
	{
		boolean a = true;
		boolean b = true;

		if (jCBox.getSelectedItem().equals("Sélectionnez une boîte")) 
		{
			lBox.setForeground(Color.red);
			lBox.setText("Boite * : ");
			a = false;
		}
		;

		if (tFExp.getText().isEmpty() && tFSubject.getText().isEmpty()
				&& tFBody.getText().isEmpty()) 
		{
			lExp.setForeground(Color.red);
			lExp.setText("Expediteur * : ");
			lSubject.setForeground(Color.red);
			lSubject.setText("Objet * : ");
			lBody.setForeground(Color.red);
			lBody.setText("Sujet * : ");
			b = false;
		}

		if (a && b) 
		{
			parentPanel.getModele().getRules().add(new Rule(tFExp.getText(), tFSubject
					.getText(), tFBody.getText(), jCBox.getSelectedItem().toString(), "0",""));
			
			parentPanel.getModele().fireTableRowsInserted(
					parentPanel.modele.rules.size() - 1,
					parentPanel.modele.rules.size() - 1);
			parentPanel.getModele().updateCsv(parentPanel.modele.rules);
			parentPanel.getRulesTable().revalidate();
			parentPanel.retour();
		}
	}
	
	public void retour() 
	{
		this.setVisible(false);
		tFExp.setText("\r");
		tFSubject.setText("\r");
		tFBody.setText("\r");
		jCBox.setSelectedIndex(0);
		lExp.setForeground(Color.black);
		lExp.setText("Expediteur : ");
		lSubject.setForeground(Color.black);
		lSubject.setText("Objet : ");
		lBody.setForeground(Color.black);
		lBody.setText("Sujet : ");
		lBox.setForeground(Color.black);
		lBox.setText("Boite : ");
	}
	
	public ContentPanel getParentPanel() 
	{
		return parentPanel;
	}

	public void setParentPanel(ContentPanel parentPanel) 
	{
		this.parentPanel = parentPanel;
	}


}
