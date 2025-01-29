package uk.ac.herts.zr21aao.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

import uk.ac.herts.zr21aao.Model.Parcel;
import uk.ac.herts.zr21aao.controller.CustomerList;
import uk.ac.herts.zr21aao.controller.ParcelList;
import uk.ac.herts.zr21aao.controller.SystemLog;
import uk.ac.herts.zr21aao.Model.Staff;

// ButtonEditor handles what happens when the user clicks the button
public class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final JCheckBox checkBox;
	private final JButton button;
	private String label;
	private int row;
	private int column;
	private ParcelTableModel tableModel;
	@SuppressWarnings("unused")
	private JTable table;

	public ButtonEditor(JCheckBox checkBox, ParcelTableModel tableModel, JTable table) {
		this.checkBox = checkBox;
		this.tableModel = tableModel;
		this.table = table;

		this.button = new JButton();
		this.button.setOpaque(true);
		this.button.addActionListener(this);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		this.label = (value == null) ? "" : value.toString();
		this.button.setText(label);
		this.row = row;
		this.column = column;
		return button;
	}

	@Override
	public Object getCellEditorValue() {
		return label;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Parcel p = tableModel.getParcelAt(row);
		if (p == null)
			return;

		if (column == ParcelTableModel.COL_COLLECT_FEE) {

			double fee = p.calculateFee();
			JOptionPane.showMessageDialog(null, "Collected fee: $" + fee, "Fee Collection",
					JOptionPane.INFORMATION_MESSAGE);
			p.setFeePaid(true);

		} else if (column == ParcelTableModel.COL_ACTION) {
			// Mark as collected
			p.setStatus(Parcel.Status.Collected);

			String id = p.getParcelID();
			JOptionPane.showMessageDialog(null, "Parcel with ID:" + id + " Has been marked as Collected",
					"Parcel Collected", JOptionPane.INFORMATION_MESSAGE);
			Staff staff1 = Staff.initStaff();
			SystemLog.getInstance().log(
					"Parcel with ID: " + p.getParcelID() + " was marked as COLLECTED by Staff: " + staff1.toString());
			CustomerList newList = CustomerList.getInstance();
			newList.removeCustomerByParcelID(p.getParcelID());
			ParcelList.getInstance().removeParcel(p);
			newList.printCustomers();

		}
		tableModel.setParcels(ParcelList.getInstance().getParcels());

		tableModel.fireTableDataChanged();
		fireEditingStopped();
	}
}