package com.reversevending.beans;

import com.reversevending.databaseOperationsDAO.TransactionsDAO;
import com.reversevending.domain.Customer;
import com.reversevending.domain.TransactionLine;
import com.reversevending.domain.Transactions;
import com.reversevending.exceptions.Exceptions;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Named
@SessionScoped
public class TransactionsBean implements Serializable {

    private long id;

    //@Column(name="transaction_date")
    private LocalDate transactionDate;

    //@ManyToOne
    //@JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    //@OneToMany(mappedBy = "transactions")
    private Set<TransactionLine> transactionLine;


    public void setTransactionLine(Set<TransactionLine> transactionLine)
    {
        this.transactionLine = transactionLine;
    }

    public Set<TransactionLine> getTransactionLine()
    {
        return transactionLine;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getId()
    {
        return id;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public LocalDate getTransactionDate()
    {
        return transactionDate.now();
    }

    public Customer getCustomer()
    {
        return customer;
    }

    private TransactionsDAO transactionDAO;

	public void saveTransactionRecord(long customerId)
	{
        try{
            transactionDAO = new TransactionsDAO();
            Transactions transactions = new Transactions(getTransactionDate(),getCustomer());
            //transactionDAO.addTransactions(transactions, customerId);
        }
        catch(Exceptions e)
        {
            System.out.println(e.getMessage());

        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }

    }

	public void deleteTransactionRecord()
	{
	    try{
            System.out.println("Deleting student record");
            transactionDAO = new TransactionsDAO();
            transactionDAO.deleteTransaction(id);
        }
	    catch(Exceptions e)
        {
            System.out.println(e.getMessage());
        }
	    catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }

	}

	public Transactions readTransaction()
	{
		System.out.println("Reading customer by Id");
		transactionDAO = new TransactionsDAO();
		return transactionDAO.getTransactionById(id);
	}

	public void updateTransactionRecord(Transactions transactions)
	{
		System.out.println("Updating student record");
		transactionDAO = new TransactionsDAO();
		transactionDAO.updateTransaction(transactions);
	}
}
