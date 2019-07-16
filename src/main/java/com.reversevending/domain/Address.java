/**
 * Address class to hold attribute value of the customer address
 * @Thabo Moopa
 * @version 1.00, 13 May 2019
 * 
 */
package com.reversevending.domain;

import com.reversevending.databaseOperationsDAO.AddressDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="address", schema = "reversevendingmachine")
//@Named
//@SessionScoped
public class Address implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="address_id")
	private long id; 
	
	@Column(name="city")
	private String city;
	
	@Column(name="house_number")
	private int houseNumber; 
	
	@Column(name="postal_code")
	private int postalCode; 
	
	@Column(name="province")
	private String province;
	
	@Column(name="street")
	private String street;
	
	@Column(name="description")
	private String description; 
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable = false)
	private Customer customer;

	public Address() {
	}

	public Address(String city, int houseNumber, int postalCode, String province, String street, String description) {
		this.city = city;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.province = province;
		this.street = street;
		this.description = description;
	}

	/**
	 * Setters and getters for the address attributes
	 * @param
	 */
	public void setCustomer(Customer customer)
	{
		this.customer = customer; 
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	
	/**
	 * This method sets the data for the description of Address of the customer
	 * @param description used to input the description 
	 */
	public void setDescription(String description)
	{
		this.description = description; 
	}
	/**
	 * Method used to retrieve the description of the address 
	 * @return description value from the set method
	 */
	public String getDescritpion()
	{
		return description; 
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + houseNumber;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + postalCode;
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (houseNumber != other.houseNumber)
			return false;
		if (id != other.id)
			return false;
		if (postalCode != other.postalCode)
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "Address [id=" + id + ", city=" + city + ", houseNumber=" + houseNumber + ", postalCode=" + postalCode
//				+ ", province=" + province + ", street=" + street + ", description=" + description + ", customer="
//				+ customer + "]";
//	}


}
