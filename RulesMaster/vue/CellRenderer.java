package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class CellRenderer extends DefaultTableCellRenderer 
{ 
	private static final long serialVersionUID = -8803539356086207068L;

	public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column)
	{ 
		this.setHorizontalAlignment(JLabel.CENTER);
		
		for (int i=0 ; i<table.getColumnCount()-1 ; i++) 
		{	
			  table.getColumnModel().getColumn(i).setCellRenderer(this);
		}
		
		Component cell = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 

		Font font = new Font("Arial" , Font.BOLD ,12);
		
		cell.setFont(font);
		cell.setForeground(Color.DARK_GRAY);
		cell.setLocation(10, 0);

		return cell; 
	} 
 
}
