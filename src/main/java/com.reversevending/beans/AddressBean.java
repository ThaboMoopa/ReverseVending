package com.reversevending.beans;

import com.reversevending.databaseOperationsDAO.AddressDAO;
import com.reversevending.domain.Address;
import com.reversevending.domain.Customer;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class AddressBean implements Serializable {
    private long id;

    private String city;

    private int houseNumber;

    private int postalCode;

    private String province;

    private String street;

    private String description;

    private Customer customer;

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

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

    	private AddressDAO addressDAO;
	//private List<Address> addressList;

	public String redirect()
	{
		saveAddressRecord();
		return "BankDetailsRegister.xhtml";
	}

	public void saveAddressRecord()
	{
		addressDAO = new AddressDAO();
		addressDAO.addAddress(new Address(city, houseNumber,postalCode,province,street,description));
		System.out.println("Saving the student record: ");
	}

	public void deleteAddressRecord()
	{
		System.out.println("Deleting student record");
		addressDAO = new AddressDAO();
		addressDAO.deleteAddress(id);
	}

//	public List<Address> readAddress()
//	{
//		System.out.println("Reading customer by Id");
//		addressDAO = new AddressDAO();
//		addressList = addressDAO.getAddressById(id);
//		return addressList;
//	}

//	public void updateAddressRecord()
//	{
//		System.out.println("Updating student record");
//		addressDAO = new AddressDAO();
//		addressDAO.updateAddress(this);
//	}
}
