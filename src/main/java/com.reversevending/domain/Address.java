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
import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return id == address.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



}
