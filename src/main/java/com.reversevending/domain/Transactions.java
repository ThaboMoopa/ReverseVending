package com.reversevending.domain;

import com.reversevending.databaseOperationsDAO.TransactionsDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity 
@Table(name="transactions", schema = "reversevendingmachine")
//@Named
//@SessionScoped
public class Transactions implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="transaction_id")
	private long id; 
	
	@Column(name="transaction_date")
	private LocalDate transactionDate; 
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable = false)
	private Customer customer;
	
	@OneToMany(mappedBy = "transactions")
	private Set<TransactionLine> transactionLine;

	public Transactions(){}
	public Transactions(LocalDate transactionDate, Customer customer) {
		this.transactionDate = transactionDate;
		this.customer = customer;

	}

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
		return transactionDate; 
	}
	
	public Customer getCustomer()
	{
		return customer; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
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
		Transactions other = (Transactions) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id != other.id)
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		//return "Transaction [id=" + id + ", transactionDate= customer=" + customer + "]";
//		return "Transaction [id=" + id + ", transactionDate=" + transactionDate + ", customer=" + customer + "]";
//	}

//
}
