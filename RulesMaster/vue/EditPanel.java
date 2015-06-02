package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Rule;

public class EditPanel extends JPanel 
{

	public ContentPanel contentPanel;
	
	private static final long serialVersionUID = -841724517580827299L;
	private static JButton retour2Button;
	private static JButton editButton;

	public  TextField tFExp2 = new TextField();
	public  TextField tFSubject2 = new TextField();
	public  TextField tFBody2 = new TextField();
	
	public  JComboBox<String> jCBox ;

	public Rule ruleToEdit;

	public Rule newRuleEdited;

	private static JLabel lExp2 = new JLabel("Expediteur : ");
	private static JLabel lSubject2 = new JLabel("Objet : ");
	private static JLabel lBody2 = new JLabel("Sujet : ");
	private static JLabel lBox2 = new JLabel("Boite : ");

	private static final Color rulesTableScrollPaneColor = new Color(50);
	private static final Font arial11pt = new Font("Arial", Font.PLAIN, 11);
	private static final Insets buttonMargin = new Insets(2, 2, 2, 2);
	
	public boolean a ;

	public EditPanel( ContentPanel contentPanel ) 
	{
		this.contentPanel = contentPanel;
		
		tFExp2.setBounds(180, 100, 150, 30);
		tFSubject2.setBounds(420, 100, 230, 30);
		tFBody2.setBounds(180, 150, 300, 30);
		
		jCBox = new JComboBox<>( contentPanel.action.getNamesFolders());
		jCBox.setBounds(180, 200, 280, 30);
		jCBox.setSelectedItem("Sélectionnez une boîte");

		lExp2.setBounds(100, 100, 150, 30);
		lSubject2.setBounds(370, 100, 150, 30);
		lBody2.setBounds(100, 150, 300, 30);
		lBox2.setBounds(100, 200, 150, 30);

		retour2Button = new Bouton("Annuler", contentPanel);
		retour2Button.setFont(arial11pt);
		retour2Button.setMargin(buttonMargin);
		retour2Button.setBounds(430, 350, 100, 20);

		editButton = new Bouton("Editer", contentPanel);
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

	public void annuler() 
	{
		this.setVisible(false);
		contentPanel.rulesTableScrollPane.setVisible(true);
		contentPanel.mailsTableScrollPane.setVisible(true);
		contentPanel.jCBAnalysedBox.setEnabled(true);
		contentPanel.execButton.setEnabled(true);
		contentPanel.addButton.setEnabled(true);
		contentPanel.delButton.setEnabled(true);
	}

	public void editer() {
		a = true;

		if (tFExp2.getText().isEmpty() && tFSubject2.getText().isEmpty()
				&& tFBody2.getText().isEmpty()) 
		{
			lExp2.setForeground(Color.red);
			lExp2.setText("Expediteur * : ");
			lSubject2.setForeground(Color.red);
			lSubject2.setText("Objet * : ");
			lBody2.setForeground(Color.red);
			lBody2.setText("Sujet * : ");
			a = false;
		}		
	}
}
