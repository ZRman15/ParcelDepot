package uk.ac.herts.zr21aao.Model;

import java.io.Serializable;

public class Parcel implements Serializable, Comparable<Parcel> {
	private static final long serialVersionUID = 1L;
	private String parcelID;
	private double weight;
	private int height;
	private int width;
	private int length;
	private int DaysInDepot;
	private boolean feePaid = false;

	public enum Status {
		Collected, Waiting
	}

	private Status status;
	private float discount;

	public String getParcelID() {
		return parcelID;
	}

	public void setParcelID(String parcelID) {
		this.parcelID = parcelID;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDaysInDepot() {
		return DaysInDepot;
	}

	public void setDaysInDepot(int daysInDepot) {
		DaysInDepot = daysInDepot;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public boolean isFeePaid() {
		return feePaid;
	}

	public void setFeePaid(boolean feePaid) {
		this.feePaid = feePaid;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double calculateFee() {

		final double baseFee = 10.0;
		final double dailyRate = 0.5; // per day in depot
		final double rate = 0.2;// per kg
		final double dimensionsFee = 0.1;

		double dailyCost = dailyRate * DaysInDepot;
		double weightCost = rate * weight;
		double dimensionCost = dimensionsFee * (width + height + length);

		double total = baseFee + dailyCost + weightCost + dimensionCost;

		total -= discount;

		if (total < 0) {
			total = 0;
		}

		return total;
	}

	@Override
	public int compareTo(Parcel other) {
		return this.parcelID.compareTo(other.parcelID);
	}

	@Override
	public int hashCode() {
		return parcelID.hashCode();
	}

	@Override
	public String toString() {
		return parcelID;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Parcel that && parcelID.equals(that.parcelID);
	}

}