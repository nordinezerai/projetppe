package RulesManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;

public class MyComboBox<e> extends JComboBox<e> implements ActionListener
{

	private static final long serialVersionUID = -3468696227114715364L;

	private ModeleDynamiqueObjet modele ;
	
	private ContentPanel panel ;
	
	public MyComboBox() 
	{
		super();
		this.addActionListener(this);
	}
	
	public MyComboBox(e[] str, ContentPanel panel)
	{
		super(str);
		this.panel = panel;
		this.modele = panel.modele;
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try 
		{			
			panel.refreshTable();
		} 
		
		catch (IOException e1) 
		{			
			e1.printStackTrace();
		}
	}

}
