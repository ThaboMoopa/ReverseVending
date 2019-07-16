/**
 * 
 * @Thabo Moopa
 * @version 1.00, 13 May 2019
 * 
 */
package com.reversevending.domain;

import com.reversevending.databaseOperationsDAO.BankDetailsDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="bank_details",schema = "reversevendingmachine")
//@Named
//@SessionScoped
public class BankDetails implements Serializable {
	/**
	 *
	 * The class includes the property attributes for the bank details 
	 *
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name = "bank_id")
	private long id;
	
	@Column(name="account_number")
	private long accountNumber; 
	
	@Column(name = "branch_code")
	private long branchCode;
	
	@Column(name="name")
	private String name; 
	
	
	//Reference Type
	@ManyToOne
	@JoinColumn(name="customer_id", nullable = false)
	private Customer customer; 
	
	public void setCustomer(Customer customer)
	{
		this.customer = customer; 
	}
	
	public Customer getCustomer()
	{
		return customer; 
	}
	/**
	 * 
	 * Setters and getters for the bank details attributes
	 * @return accountNumber 
	 */
	
	
	
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBranchCode(long branchCode)
	{
		this.branchCode = branchCode; 
	}
	
	public long getBranchCode()
	{
		return branchCode; 
	}
	public void setId(long id)
	{
		this.id = id; 
	}
	public long getId()
	{
		return id; 
	}

	public BankDetails() {
	}

	public BankDetails(long accountNumber, long branchCode, String name) {
		this.accountNumber = accountNumber;
		this.branchCode = branchCode;
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + (int) (branchCode ^ (branchCode >>> 32));
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		BankDetails other = (BankDetails) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (branchCode != other.branchCode)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "BankDetails [id=" + id + ", accountNumber=" + accountNumber + ", branchCode=" + branchCode + ", name="
//				+ name + ", customer=" + customer + "]";
//	}
////
}
