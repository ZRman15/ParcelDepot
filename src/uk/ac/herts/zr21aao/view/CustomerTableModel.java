package uk.ac.herts.zr21aao.view;

import uk.ac.herts.zr21aao.Model.Customer;
import uk.ac.herts.zr21aao.Model.Parcel;
import uk.ac.herts.zr21aao.controller.CustomerList;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] COLUMN_NAMES = { "First Name", "Last Name", "Parcel ID" };

	private List<Customer> customers;

	public CustomerTableModel(Collection<Customer> customers) {
		this.customers = new ArrayList<>(customers);
	}

	@Override
	public int getRowCount() {
		return customers.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Customer c = customers.get(rowIndex);
		return switch (columnIndex) {
		case 0 -> c.getFirstName();
		case 1 -> c.getLastName();
		case 2 -> {
			Parcel p = c.getParcel();
			yield (p == null ? "No Parcel" : p.getParcelID());
		}
		default -> null;
		};
	}
	// can't figure out how to get this method to work with the CustomerList class.
	// the list itself will update but the list in the GUI stays the same for customers but changes for parcels??
	public void setCustomers(CustomerList collection) {
		collection = new CustomerList();
		fireTableDataChanged();
	}
}