package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controle.Action;

public class Bouton extends JButton implements ActionListener 
{
	private static final long serialVersionUID = -2280713047779756704L;
	
	private ContentPanel parentPanel;
	
	private Action a ; 

	public Bouton() 
	{
		super();
	}

	public Bouton(String str, ContentPanel parentPanel) 
	{
		super(str);

		this.parentPanel = parentPanel ;
		
		a = this.parentPanel.action ;
		
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
				
				case "Exécuter les règles": a.executeRule();									
				break;
				
				case "Modifier": a.modifier();
				break;
				
				case "Ajouter": a.ajouter();
				break;
				
				case "Supprimer": a.supprimer();
				break;
				
				case "Retour": a.retour();
				break;
				
				case "Ajouter la règle": a.ajouterRegle();
				break;
				
				case "Annuler": a.annuler();
				break;
				
				case "Editer": a.editer();
				break;
				
				case "Quitter": System.exit(1);
				break;
			}
		}
		
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
}

