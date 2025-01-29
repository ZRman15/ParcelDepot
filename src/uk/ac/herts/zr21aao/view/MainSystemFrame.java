package uk.ac.herts.zr21aao.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import uk.ac.herts.zr21aao.Model.Parcel;
import uk.ac.herts.zr21aao.controller.CustomerList;
import uk.ac.herts.zr21aao.controller.ParcelList;
import uk.ac.herts.zr21aao.controller.SystemReportGenerator;

public class MainSystemFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JTable parcelTable;
	private JTable customerTable;
	private ParcelTableModel parcelTableModel;
	private CustomerTableModel customerTableModel;

	@SuppressWarnings("unused")
	public MainSystemFrame() {
		super("Parcel Management System");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1000, 600);
		tabbedPane = new JTabbedPane();

		// parcels tab
		JPanel parcelsPanel = new JPanel(new BorderLayout());

		// loding from parcelList
		ParcelList parcelList = ParcelList.getInstance();
		List<Parcel> parcels = parcelList.getParcels();
		parcelTableModel = new ParcelTableModel(parcels);
		parcelTable = new JTable(parcelTableModel);

//        parcelTable.getColumnModel().getColumn(ParcelTableModel.COL_COLLECT_FEE).setCellRenderer(new ButtonRenderer());
//        parcelTable.getColumnModel().getColumn(ParcelTableModel.COL_COLLECT_FEE)
//        .setCellEditor(
//            new ButtonEditor(
//                new JCheckBox(),  
//                parcelTableModel,
//                parcelTable        
//            )
//        );

		parcelTable.getColumnModel().getColumn(ParcelTableModel.COL_ACTION).setCellRenderer(new ButtonRenderer());
		parcelTable.getColumnModel().getColumn(ParcelTableModel.COL_ACTION)
				.setCellEditor(new ButtonEditor(new JCheckBox(), parcelTableModel, parcelTable));
		// adding add parcel button
		JButton addParcelButton = new JButton("Add Parcel");
		addParcelButton.addActionListener(e -> {
			new AddParcelDialog(this);
			List<Parcel> updatedParcels = ParcelList.getInstance().getParcels();
			parcelTableModel.setParcels(updatedParcels); // a method that updates the list
			parcelTableModel.fireTableDataChanged();
		});

		JButton reportButton = new JButton("Generate Report");
		reportButton.addActionListener(e -> {
			// printing report to FinalReport.txt
			String filePath = "FinalReport.txt";
			SystemReportGenerator.generateSystemReport(filePath);

			File file = new File(filePath);
			System.out.println("Report generated at: " + file.getAbsolutePath());

			JOptionPane.showMessageDialog(this, "Report generated to " + filePath, "Report",
					JOptionPane.INFORMATION_MESSAGE);
		});

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(addParcelButton);
		topPanel.add(reportButton);
		parcelsPanel.add(topPanel, BorderLayout.NORTH);
		parcelsPanel.add(new JScrollPane(parcelTable), BorderLayout.CENTER);
		tabbedPane.addTab("Parcels", parcelsPanel);

		// customers tab
		JPanel customersPanel = new JPanel(new BorderLayout());

		// load customers
		CustomerList customerList = CustomerList.getInstance();
		customerTableModel = new CustomerTableModel(customerList.getAllCustomers());
		customerTable = new JTable(customerTableModel);

		customersPanel.add(new JScrollPane(customerTable), BorderLayout.CENTER);
		tabbedPane.addTab("Customers", customersPanel);
		add(tabbedPane, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(MainSystemFrame::new);
	}
}