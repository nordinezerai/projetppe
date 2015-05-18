package RulesManager;
import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8836259087361431728L;
	protected JButton bouton;

	public ButtonEditor(ContentPanel panel) {
		super();

		bouton = new Bouton("Modifier", panel);
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		return bouton;
	}

	@Override
	public Object getCellEditorValue() {
		return false;
	}
}