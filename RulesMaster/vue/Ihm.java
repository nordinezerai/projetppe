package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Ihm extends JFrame {

	private static final long serialVersionUID = 6810537376327037440L;

	public Ihm() {
		this.setLayout( new BorderLayout() );
		this.setSize(1000, 650);
		this.setTitle("Gestion des Règles");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);	
		
		ContentPanel contentPanel = new ContentPanel();
		
		this.add( contentPanel, BorderLayout.CENTER );		
		this.getContentPane().revalidate();
		this.repaint();
		this.setVisible(true);
	}

}
