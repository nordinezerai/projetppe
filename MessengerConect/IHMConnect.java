package MessengerConect;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class IHMConnect extends JFrame {

	private static final long serialVersionUID = -3337156035599744469L;

	private JPanel currentPanel;
	
	public IHMConnect() {
		this.setSize(600, 400);
		this.setTitle("Connection au serveur de messagerie");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
//		this.currentPanel = new ConnectPanel(this);
//		
//		this.setContentPane( currentPanel );
//		this.setVisible(true);
//		this.revalidate();
	}

	public JPanel getCurrentPanel() {return currentPanel;}
	public void setCurrentPanel(JPanel currentPanel) {this.currentPanel = currentPanel;}

}
