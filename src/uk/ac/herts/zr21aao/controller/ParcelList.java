package uk.ac.herts.zr21aao.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.TreeSet;

import uk.ac.herts.zr21aao.Model.Parcel;

public class ParcelList {

	private static ParcelList INSTANCE;
	private final TreeSet<Parcel> parcels = new TreeSet<>();
	private boolean modified;

	public static ParcelList getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ParcelList();
		}

		return INSTANCE;

	}

	public boolean addParcel(Parcel parcel) {
		boolean added = parcels.add(parcel);

		if (added) {
			modified = true;
		}

		return added;
	}

	public boolean removeParcel(Parcel parcel) {
		boolean removed = parcels.remove(parcel);

		if (removed) {
			modified = true;
		}

		return removed;
	}

	public Parcel getParcel(String parcelID) {
		return parcels.stream().filter(p -> p.getParcelID().equals(parcelID)).findFirst().orElse(null);
	}

	public List<Parcel> getParcels() {
		return List.copyOf(parcels);
	}

	public boolean isModified() {
		return modified;
	}

	private ParcelList() {
		try (BufferedReader br = new BufferedReader(new FileReader("Parcels.csv"))) {
			String input = null;

			while ((input = br.readLine()) != null) {
				String[] temp = input.split(",");
				Parcel parcel = new Parcel();

				parcel.setParcelID(temp[0]);
				parcel.setDaysInDepot(Integer.parseInt(temp[1]));
				parcel.setWeight(Integer.parseInt(temp[2]));
				parcel.setWidth(Integer.parseInt(temp[3]));
				parcel.setLength(Integer.parseInt(temp[4]));
				parcel.setHeight(Integer.parseInt(temp[5]));
				parcel.setStatus(null);
				parcels.add(parcel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean saveParcels() {
		try (BufferedWriter br = new BufferedWriter(new FileWriter("Parcels.csv"))) {
			for (Parcel parcel : parcels) {
				br.write(parcel.getParcelID() + "," + parcel.getDaysInDepot() + "," + parcel.getWeight() + ","
						+ parcel.getLength() + "," + parcel.getWidth() + "," + parcel.getHeight());
				br.newLine();
			}

			modified = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return !modified;
	}

}
