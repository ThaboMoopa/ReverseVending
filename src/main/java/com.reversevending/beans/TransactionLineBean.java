package com.reversevending.beans;

import com.reversevending.databaseOperationsDAO.TransactionLineDAO;
import com.reversevending.domain.Products;
import com.reversevending.domain.TransactionLine;
import com.reversevending.domain.Transactions;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@SessionScoped
public class TransactionLineBean implements Serializable {
    private TransactionLineDAO transactionLineDAO;

    private long id;

    private int quantity;

    private double total;

    private Transactions transactions;

    private Products products;

    private LocalDate localDate;

    public void setProducts(Products products)
    {
        this.products = products;
    }
    public Products getProducts(){
        return products;
    }

    public LocalDate getLocalDate() {
        return localDate.now();
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setTransaction(Transactions transactions)
    {
        this.transactions = transactions;
    }

    public Transactions getTransactions()
    {
        return transactions;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setTotal(double total)
    {
        this.total=total;
    }
    public double getTotal()
    {
        return total;
    }

    //Move to Controller
	public List<TransactionLine> getTransactionsLineList() {
		if(transactionLineDAO.populateTable().isEmpty())
		{
			transactionsLineList = null;
		}
		else
		{
			transactionsLineList = transactionLineDAO.populateTable();
		}

		return transactionsLineList;
	}

    private List<TransactionLine> transactionsLineList;



	public String redirect()
	{
		//saveTransactionLineRecord();
		return "homepage.xhtml";
	}

	public void saveTransactionLineRecord(long productId, long transactionId)
	{

		transactionLineDAO = new TransactionLineDAO();
		transactionLineDAO.addTransactionLine(new TransactionLine(getQuantity(),getTotal(), getLocalDate()), productId, transactionId);
		System.out.println("Saving the TransactionLineDAO record: ");
	}

	public void deleteTransactionLineRecord()
	{
		System.out.println("Deleting TransactionLineDAO record");
		transactionLineDAO = new TransactionLineDAO();
		transactionLineDAO.deleteTransactionLine(id);
	}

	public TransactionLine readTransactionLine()
	{
		System.out.println("Reading TransactionLineDAO by Id");
		transactionLineDAO = new TransactionLineDAO();
		return transactionLineDAO.getTransactionLineById(id);
	}

	public void updateWithAddButton(long id)
    {
        transactionLineDAO = new TransactionLineDAO();
        transactionLineDAO.updateTransactionLineAddButton(id);
    }

    public void updateWithRemoveButton(long id)
    {
        transactionLineDAO = new TransactionLineDAO();
        transactionLineDAO.updateTransactionLineRemoveButton(id);
    }
}
