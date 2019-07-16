package com.reversevending.domain;

//import com.reversevending.databaseOperationsDAO.ProductsDAO;

//import javax.enterprise.context.SessionScoped;
//import javax.inject.Named;
import com.reversevending.databaseOperationsDAO.ProductsDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="products",schema = "reversevendingmachine")
//@Named
//@SessionScoped
public class Products implements Serializable {

	private static final long serialVersionUID = -8105302459739491970L;

	public Products(){
	}

	public Products(double price, String name, String description)
	{
		this.name = name;
		this.description = description;
		this.price = price;
	}
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="products_id")
	private long id;

	@Column(name="price")
	private double price;

	@Column(name="name")
	private String name;

	@Column(name="description")
	private String description;

	@OneToMany(mappedBy = "products")
	private Set<TransactionLine> transactionLine;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {

		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<TransactionLine> getTransactionLine() {
		return transactionLine;
	}

	public void setTransactionLine(Set<TransactionLine> transactionLine) {
		this.transactionLine = transactionLine;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Products products = (Products) o;
		return id == products.id &&
				Double.compare(products.price, price) == 0 &&
				Objects.equals(name, products.name) &&
				Objects.equals(description, products.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, price, name, description);
	}

//	@Override
//	public String toString() {
//		return "Products{" +
//				"id=" + id +
//				", price=" + price +
//				", name='" + name + '\'' +
//				", description='" + description + '\'' +
//				'}';
//	}



}



