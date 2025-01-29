package uk.ac.herts.zr21aao.view;

import uk.ac.herts.zr21aao.Model.Parcel;
import uk.ac.herts.zr21aao.Model.Staff;
import uk.ac.herts.zr21aao.controller.SystemLog;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ParcelTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	// columns for parcelList view in table
	private static final String[] COLUMN_NAMES = { "Parcel ID", "Weight (kg)", "Height (cm)", "Width (cm)",
			"Length (cm)", "Days In Depot", "Status", "Collect Fee", "Action" };

	public static final int COL_PARCEL_ID = 0;
	public static final int COL_WEIGHT = 1;
	public static final int COL_HEIGHT = 2;
	public static final int COL_WIDTH = 3;
	public static final int COL_LENGTH = 4;
	public static final int COL_DAYS = 5;
	public static final int COL_STATUS = 6;
	public static final int COL_COLLECT_FEE = 7;
	public static final int COL_ACTION = 8;

	private List<Parcel> parcels;

	public ParcelTableModel(List<Parcel> parcels) {
		this.parcels = new ArrayList<>(parcels);
	}

	public void setParcels(List<Parcel> updatedList) {
		this.parcels = new ArrayList<>(updatedList);
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return parcels.size();
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
		Parcel p = parcels.get(rowIndex);

		return switch (columnIndex) {
		case COL_PARCEL_ID -> p.getParcelID();
		case COL_WEIGHT -> p.getWeight();
		case COL_HEIGHT -> p.getHeight();
		case COL_WIDTH -> p.getWidth();
		case COL_LENGTH -> p.getLength();
		case COL_DAYS -> p.getDaysInDepot();
		case COL_STATUS -> p.getStatus() == null ? "Waiting" : p.getStatus().toString();
		case COL_COLLECT_FEE -> p.isFeePaid();
		case COL_ACTION -> "Mark Collected";
		default -> null;
		};
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return switch (columnIndex) {
		case COL_COLLECT_FEE -> Boolean.class;
		default -> String.class;
		};
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == COL_COLLECT_FEE) {
			Parcel p = parcels.get(rowIndex);
			return !p.isFeePaid();
		}
		if (columnIndex == COL_ACTION) {
			Parcel p = parcels.get(rowIndex);
			return p.isFeePaid();
		}
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Parcel p = parcels.get(rowIndex);

		if (columnIndex == COL_COLLECT_FEE) {
			boolean newFeePaid = (Boolean) aValue;
			if (newFeePaid) {

				double fee = p.calculateFee();
				JOptionPane.showMessageDialog(null, "Collected fee: £" + fee, "Fee Collection",
						JOptionPane.INFORMATION_MESSAGE);

				// set fee as paid
				p.setFeePaid(true);
				String id = p.getParcelID();
				Staff staff1 = Staff.initStaff();
				SystemLog.getInstance().log("Parcel with ID: " + id + " Has been has been PAID for. Fee Collected: £"
						+ fee + " by Staff: " + staff1.toString());
			}
		}

		fireTableRowsUpdated(rowIndex, rowIndex);
	}

	public void markParcelAsCollected(int rowIndex) {
		if (rowIndex < 0 || rowIndex >= parcels.size())
			return;
		Parcel p = parcels.get(rowIndex);
		p.setStatus(Parcel.Status.Collected);

		// update row in table
		fireTableRowsUpdated(rowIndex, rowIndex);
	}

	public Parcel getParcelAt(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < parcels.size()) {
			return parcels.get(rowIndex);
		}
		return null;
	}
}