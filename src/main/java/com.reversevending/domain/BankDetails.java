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
import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BankDetails that = (BankDetails) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
