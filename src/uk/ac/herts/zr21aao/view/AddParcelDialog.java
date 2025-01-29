package uk.ac.herts.zr21aao.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import uk.ac.herts.zr21aao.Model.Parcel;
import uk.ac.herts.zr21aao.Model.Staff;
import uk.ac.herts.zr21aao.controller.ParcelList;
import uk.ac.herts.zr21aao.controller.SystemLog;

public class AddParcelDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final ParcelList parcelManager = ParcelList.getInstance();

	private final JTextField txtParcelID = new JTextField();
	private final JSpinner spnDaysInDepot = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1)),
			spnWeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D)),
			spnWidth = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D)),
			spnHeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D)),
			spnLength = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));

	private final JButton btnAdd = new JButton("Add"), btnReset = new JButton("Reset");

	@SuppressWarnings("unused")
	public AddParcelDialog(MainSystemFrame parent) {
		super(parent, "Add Parcel Dialog", true);

		JPanel pnlCenter = new JPanel(new GridLayout(6, 2));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		btnAdd.addActionListener(e -> addParcel());
		btnReset.addActionListener(e -> reset());

		pnlCenter.add(new JLabel("Parcel ID: ", SwingConstants.RIGHT));
		pnlCenter.add(txtParcelID);
		pnlCenter.add(new JLabel("Days in Depot: ", SwingConstants.RIGHT));
		pnlCenter.add(spnDaysInDepot);
		pnlCenter.add(new JLabel("Weight (kg): ", SwingConstants.RIGHT));
		pnlCenter.add(spnWeight);
		pnlCenter.add(new JLabel("Width (cm): ", SwingConstants.RIGHT));
		pnlCenter.add(spnWidth);
		pnlCenter.add(new JLabel("Height (cm): ", SwingConstants.RIGHT));
		pnlCenter.add(spnHeight);
		pnlCenter.add(new JLabel("Length (cm): ", SwingConstants.RIGHT));
		pnlCenter.add(spnLength);

		pnlSouth.add(btnAdd);
		pnlSouth.add(btnReset);

		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		setSize(320, 240);
		setResizable(false);
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	private void addParcel() {
		Parcel parcel = new Parcel();
		parcel.setParcelID(txtParcelID.getText());
		parcel.setDaysInDepot((int) spnDaysInDepot.getValue());
		parcel.setWeight(((Double) spnWeight.getValue()).doubleValue());
		parcel.setWidth((int) ((Double) spnWidth.getValue()).doubleValue());
		parcel.setHeight((int) ((Double) spnHeight.getValue()).doubleValue());
		parcel.setLength((int) ((Double) spnLength.getValue()).doubleValue());

		if (parcelManager.addParcel(parcel)) {
			Staff staff1 = Staff.initStaff();
			SystemLog.getInstance()
					.log("Added parcel with ID: " + parcel.getParcelID() + " By Staff: " + staff1.toString());

			JOptionPane.showMessageDialog(this, "Parcel successfully added.", getTitle(),
					JOptionPane.INFORMATION_MESSAGE);
			dispose(); // close dialog
		} else {
			SystemLog.getInstance().log("Error occured while adding parcel");
			JOptionPane.showMessageDialog(this, "Unable to add parcel.", getTitle(), JOptionPane.WARNING_MESSAGE);
		}
	}

	private void reset() {
		txtParcelID.setText("");
		spnDaysInDepot.setValue(1);
		spnWeight.setValue(1D);
		spnWidth.setValue(1D);
		spnHeight.setValue(1D);
		spnLength.setValue(1D);
	}
}