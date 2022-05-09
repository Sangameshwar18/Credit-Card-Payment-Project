package com.cg.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Address")
public class Address {

	@Id
	@NotNull(message = "Address Id can't be null")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;

	@NotNull(message = "Door No can't be null")
	private String doorNo;

	@Column(name = "Street")
	@NotNull(message = "street can't be null")
	private String street;

	@Column(name = "Area")
	@NotNull(message = "area can't be null")
	private String area;

	@Column(name = "City")
	@NotNull(message = "city can't be null")
	private String city;

	@Column(name = "State")
	@NotNull(message = "state can't be null")
	private String state;

	@Column(name = "Pincode")
	@NotNull(message = "pincode can't be null")
	private int pincode;

	public Address() {
	}

	public Address(@NotNull(message = "Address Id can't be null") long addressId,
			@NotNull(message = "Door No can't be null") String doorNo,
			@NotNull(message = "street can't be null") String street,
			@NotNull(message = "area can't be null") String area, @NotNull(message = "city can't be null") String city,
			@NotNull(message = "state can't be null") String state,
			@NotNull(message = "pincode can't be null") int pincode) {
		super();
		this.addressId = addressId;
		this.doorNo = doorNo;
		this.street = street;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public Address(@NotNull(message = "Door No can't be null") String doorNo,
			@NotNull(message = "street can't be null") String street,
			@NotNull(message = "area can't be null") String area, @NotNull(message = "city can't be null") String city,
			@NotNull(message = "state can't be null") String state,
			@NotNull(message = "pincode can't be null") int pincode) {
		super();
		this.doorNo = doorNo;
		this.street = street;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", doorNo=" + doorNo + ", street=" + street + ", area=" + area
				+ ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}

}
