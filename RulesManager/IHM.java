package RulesManager;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import MessengerConect.ConnectPanel;

public class IHM extends JFrame 
{
	private static final long serialVersionUID = 859059945559644169L;
	
	private JPanel currentPanel;

	public IHM() 
	{
		this.setLayout( new BorderLayout() );
		this.setSize(600, 400);
		this.setTitle("Connection au serveur de messagerie");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);	
		
		ConnectPanel connectPanel = new ConnectPanel(this);
		this.add( connectPanel, BorderLayout.CENTER );
		this.currentPanel = connectPanel;
		
		this.getContentPane().revalidate();
		this.repaint();
		this.setVisible(true);
	}
		
	public void accessMailBox() throws Exception 
	{
		this.setVisible(false);
		
		if( this.currentPanel != null )
		{
			this.remove( currentPanel );
			this.currentPanel = null;
			this.revalidate();
			this.repaint();
		}
	
		this.setSize(1000, 650);
		this.setTitle("Gestion des Règles");
		
		ContentPanel contentPanel = new ContentPanel(this);
		this.add( contentPanel, BorderLayout.CENTER );
		this.currentPanel = contentPanel;
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.getContentPane().revalidate();
		this.repaint();
	}
	
	public void disconnect()
	{
		this.remove(currentPanel);
		this.setSize(600,400);
		this.setTitle("Connection au serveur de messagerie");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		ConnectPanel connectPanel = new ConnectPanel(this);
		this.add( connectPanel, BorderLayout.CENTER );
		this.currentPanel = connectPanel;
		
		this.getContentPane().revalidate();
		this.repaint();
		this.setVisible(true);
		
	}
	
	public JPanel getCurrentPanel() {return currentPanel;}
	public void   setCurrentPanel(JPanel currentPanel) {this.currentPanel = currentPanel;}
	
}