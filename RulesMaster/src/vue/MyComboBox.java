package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

	public class MyComboBox<e> extends JComboBox<e> implements ActionListener {

		private static final long serialVersionUID = -3468696227114715364L;
		
		private ContentPanel panel ;
		
		public MyComboBox(e[] str, ContentPanel panel)
		{
			super(str);
			this.panel = panel;
			this.addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			this.panel.refreshTable();
		}
}
