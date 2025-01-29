package uk.ac.herts.zr21aao.Model;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("unused")
public class Staff {

	private String staffID;
	private String firstName;
	private String lastName;
	private String password;

	public Staff(String staffID, String firstName, String lastName, String password) {
		this.staffID = staffID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public static Staff initStaff() {
		Staff staff1 = new Staff("S0001", "Jamie", "Oliver", "password");
		return staff1;

	}

	@Override
	public String toString() {
		return staffID + "(" + firstName + " " + lastName + ")";
	}

	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private int encryptPassword() {
		int encryptedPass = password.hashCode();
		return encryptedPass;
	}

	public int getPassword() {
		int password = encryptPassword();
		return password;

	}

	private void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, password, staffID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(staffID, other.staffID);
	}

}
