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

public class AddPanel extends JPanel 
{
	
	private static final long serialVersionUID = 7875064804654757936L;

	public ContentPanel contentPanel ;

	private static JButton retourButton;
	private static JButton ajoutButton;

	public TextField tFExp = new TextField();
	public TextField tFSubject = new TextField();
	public TextField tFBody = new TextField();
	
	public JComboBox<String> jCBox ;
	
	public JLabel lExp = new JLabel("Expediteur : ");
	public JLabel lSubject = new JLabel("Objet : ");
	public JLabel lBody = new JLabel("Sujet : ");
	public JLabel lBox = new JLabel("Boite : ");

	private static final Color rulesTableScrollPaneColor = new Color(50);
	private static final Font arial11pt = new Font("Arial", Font.PLAIN, 11);
	private static final Insets buttonMargin = new Insets(2, 2, 2, 2);
	
	public boolean a ;
	
	public AddPanel(ContentPanel contentPanel) 
	{
		this.contentPanel = contentPanel;
		
		tFExp.setBounds(180, 100, 150, 30);
		tFSubject.setBounds(420, 100, 230, 30);
		tFBody.setBounds(180, 150, 300, 30);
		
		jCBox = new JComboBox<>( contentPanel.action.getNamesFolders());
		jCBox.setBounds(180, 200, 280, 30);
		jCBox.setSelectedItem("S�lectionnez une bo�te");

		lExp.setBounds(100, 100, 150, 30);
		lSubject.setBounds(370, 100, 150, 30);
		lBody.setBounds(100, 150, 300, 30);
		lBox.setBounds(100, 200, 150, 30);

		retourButton = new Bouton("Retour", contentPanel);
		retourButton.setFont(arial11pt);
		retourButton.setMargin(buttonMargin);
		retourButton.setBounds(430, 350, 100, 20);

		ajoutButton = new Bouton("Ajouter la r�gle", contentPanel);
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
	
	public void retour() 
	{
		this.setVisible(false);
		tFExp.setText("\r");
		tFSubject.setText("\r");
		tFBody.setText("\r");
		lExp.setForeground(Color.black);
		lExp.setText("Expediteur : ");
		lSubject.setForeground(Color.black);
		lSubject.setText("Objet : ");
		lBody.setForeground(Color.black);
		lBody.setText("Sujet : ");
		lBox.setForeground(Color.black);
		lBox.setText("Boite : ");
		contentPanel.rulesTableScrollPane.setVisible(true);
		contentPanel.mailsTableScrollPane.setVisible(true);
		contentPanel.jCBAnalysedBox.setEnabled(true);
		contentPanel.execButton.setEnabled(true);
		contentPanel.addButton.setEnabled(true);
		contentPanel.delButton.setEnabled(true);
	}

	public void ajouterRegle() {
		a = true;

		if (tFExp.getText().isEmpty() && tFSubject.getText().isEmpty()
				&& tFBody.getText().isEmpty()) 
		{
			lExp.setForeground(Color.red);
			lExp.setText("Expediteur * : ");
			lSubject.setForeground(Color.red);
			lSubject.setText("Objet * : ");
			lBody.setForeground(Color.red);
			lBody.setText("Sujet * : ");
			a = false;
		}		
	}
}
