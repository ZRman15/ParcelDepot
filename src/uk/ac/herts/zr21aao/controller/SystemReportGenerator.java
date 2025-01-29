package uk.ac.herts.zr21aao.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import uk.ac.herts.zr21aao.Model.Customer;
import uk.ac.herts.zr21aao.Model.Parcel;

public class SystemReportGenerator {

	public static void generateSystemReport(String filePath) {
		// write logs
		SystemLog logger = SystemLog.getInstance();
		logger.writeLogsToFile(filePath);

		// append final state of parcels
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			writer.newLine();
			writer.write(".......Final Parcel State.......");
			writer.newLine();
			List<Parcel> parcels = ParcelList.getInstance().getParcels();
			for (Parcel p : parcels) {
				writer.write(p.toString() + " | Status: " + p.getStatus());
				writer.newLine();
			}

			writer.newLine();
			writer.write(".......Final Customer State.......");
			writer.newLine();
			for (Customer c : CustomerList.getInstance().getAllCustomers()) {
				writer.write(
						c.toString() + " : " + (c.getParcel() == null ? "No Parcel" : c.getParcel().getParcelID()));
				writer.newLine();
			}

			writer.newLine();
			writer.write(".......Report generation complete.......");
			writer.newLine();
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
