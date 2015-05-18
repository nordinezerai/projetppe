package RulesManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import MessengerConect.ConnectPanel;

public class Bouton extends JButton implements ActionListener 
{
	private static final long serialVersionUID = -2280713047779756704L;
	
	private JPanel parentPanel;
	private String name;

	public Bouton() 
	{
		super();
	}

	public Bouton(String str, JPanel parentPanel) 
	{
		super(str);
		this.name = str;
		this.parentPanel = parentPanel;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		try 
		{
			switch (getText()) 
			{
				case "Charger l'état courant":
				break;
				
				case "Tester les règles":
				break;
				
				case "Exécuter les règles":
				break;
				
				case "Modifier": ((ContentPanel) parentPanel).modifier();
				break;
				
				case "Ajouter": ((ContentPanel) parentPanel).initAjouter();
				break;
				
				case "Supprimer": ((ContentPanel) parentPanel).supprimer();
				break;
				
				case "Retour": ((AddPanel) parentPanel).getParentPanel().retour();
				break;
				
				case "Ajouter la règle": ((AddPanel) parentPanel).ajouterRegle();
				break;
				
				case "Annuler": ((EditPanel) parentPanel).annuler();
				break;
				
				case "Editer": ((EditPanel) parentPanel).editer();
				break;
				
				case "Quitter": System.exit(1);
				break;
				
				case "Connection": ((ConnectPanel) parentPanel).connect();	
				break;
				
				case "Deconnection" : ((ContentPanel) parentPanel).ihm.disconnect();
				break;
			}
		}
		
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
}
