package RulesManager;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends Bouton implements TableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1573912525340235845L;

	public ButtonRenderer() {
		super();
	}
	public ButtonRenderer(String text) {
		super(text, null);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		return this;
	}

}
