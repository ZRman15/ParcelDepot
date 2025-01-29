package uk.ac.herts.zr21aao.view;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

// shows a button in the cell
public class ButtonRenderer extends JButton implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	public ButtonRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}

		setText((value == null) ? "" : value.toString());
		return this;
	}
}