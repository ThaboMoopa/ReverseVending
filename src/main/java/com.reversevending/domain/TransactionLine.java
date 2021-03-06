package com.reversevending.domain;

import com.reversevending.databaseOperationsDAO.TransactionLineDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="transaction_line",schema = "reversevendingmachine")
//@Named
//@SessionScoped
public class TransactionLine implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="transaction_line_id")
	private long id; 
	
	@Column(name="quantity")
	private int quantity; 
	
	@Column(name="total")
	private double total;

	@Column(name="dayOfMonth")
	private LocalDate localDate;

	@ManyToOne
	@JoinColumn(name="transaction_id", nullable = false)
	private Transactions transactions; 
	
	@ManyToOne
	@JoinColumn(name="products_id")
	private Products products;

	public TransactionLine(int quantity, double total, LocalDate localDate) {
		this.quantity = quantity;
		this.total = total;
		this.localDate = localDate;
	}

	public LocalDate getLocalDate() {
		return localDate.now();
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public TransactionLine() {
	}

	public void setProducts(Products products)
	{
		this.products = products; 
	}
	public Products getProducts(){
		return products; 
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TransactionLine that = (TransactionLine) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	//	@Override
//	public String toString() {
//		return "TransactionLine [id=" + id + ", quantity=" + quantity + ", total=" + total + ", transaction="
//				+ transactions + "]";
//	}


}
