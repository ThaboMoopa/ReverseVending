package com.reversevending.beans;

import com.reversevending.databaseOperationsDAO.BankDetailsDAO;
import com.reversevending.domain.BankDetails;
import com.reversevending.domain.Customer;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;

@Named
@SessionScoped
public class BankDetailsBean implements Serializable {

    private long id;

    private long accountNumber;

    private long branchCode;

    private String name;

    private Customer customer;

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Customer getCustomer()
    {
        return customer;
    }
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

    private BankDetailsDAO bankDetailsDAO;
	//private List<BankDetails> bankDetailsList;

	public String redirect()
	{
		saveBankDetailsRecord();
		return "homepage.xhtml";
	}

	public void saveBankDetailsRecord()
	{
		bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(new BankDetails(accountNumber,branchCode,name));
		System.out.println("Saving the student record: ");
	}

	public void deleteBankDetailsRecord()
	{
		System.out.println("Deleting student record");
		bankDetailsDAO = new BankDetailsDAO();

		bankDetailsDAO.deleteBankDetails(id);
	}

//	public List<BankDetails> readBankDetails()
//	{
//		System.out.println("Reading bankDetails by Id");
//		bankDetailsDAO = new BankDetailsDAO();
//
//		bankDetailsList = bankDetailsDAO.getBankDetailsById(id);
//		return bankDetailsList;
//	}

//	public void updateBankDetailsRecord()
//	{
//		System.out.println("Updating student record");
//		bankDetailsDAO = new BankDetailsDAO();
//
//		bankDetailsDAO.updateBankDetails(this);
//	}
}
