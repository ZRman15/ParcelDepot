package uk.ac.herts.zr21aao.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

import uk.ac.herts.zr21aao.Model.Customer;

public class CustomerList {
	private static final CustomerList INSTANCE = new CustomerList();
	private final TreeSet<Customer> customers = new TreeSet<>();

	public static CustomerList getInstance() {
		return INSTANCE;
	}

	public CustomerList() {
		ParcelList parcelManager = ParcelList.getInstance();

		try (BufferedReader br = new BufferedReader(new FileReader("Custs.csv"))) {
			br.lines().map(input -> input.split(","))
					.map(array -> new Customer(array[0], parcelManager.getParcel(array[1]))).forEach(customers::add);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printCustomers() {
		for (Customer customer : customers) {
			System.out.println(customer + ": " + customer.getParcel());
		}
	}

	public boolean removeCustomerByParcelID(String parcelID) {
		// Find a customer whose parcel has that ID
		Customer toRemove = customers.stream()
				.filter(c -> c.getParcel() != null && c.getParcel().getParcelID().equals(parcelID)).findFirst()
				.orElse(null);

		if (toRemove != null) {
			return customers.remove(toRemove);
		}
		return false;
	}

	public Collection<Customer> getAllCustomers() {
		return Collections.unmodifiableCollection(customers);
	}

}