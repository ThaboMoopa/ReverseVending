package com.reversevending.beans;

import com.reversevending.databaseOperationsDAO.CustomerDAO;
import com.reversevending.databaseOperationsDAO.TransactionsDAO;
import com.reversevending.domain.Address;
import com.reversevending.domain.BankDetails;
import com.reversevending.domain.Customer;
import com.reversevending.domain.Transactions;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import com.reversevending.encryption.*;
import jdk.internal.util.xml.BasicXmlPropertiesProvider;

@Named
@SessionScoped
public class CustomerBean implements Serializable {
    private CustomerDAO customerDAO;
    private Customer customer;


//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "customer_id")
    private long id;

    //@Column(name = "contact")
    private String contact;


   // @Column(name = "email")
    private String email;

    //@Column(name = "name")
    private String name;

    //@Column(name = "password")
    private String password;

   // @Column(name = "surname")
    private String surname;

    //@Column(name = "image")
    private byte[] image;

    //creating bank ID for the Collection of the ArrayList using annotations
    //@OneToMany(mappedBy = "customer")
    private Set<BankDetails> bankDetails;

   // @OneToMany(mappedBy = "customer")
    private Set<Address> address;

    //@OneToMany(mappedBy = "customer")
    private Set<Transactions> transactions;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Setters and getters for the attributes of the customer class
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    //Collections type
//        public List<BankDetails> getBankDetails() {
//            return bankDetails;
//        }
//        public void setBankDetails(List<BankDetails> bankDetails) {
//            this.bankDetails = bankDetails;
//        }
//        public List<Address> getAddress() {
//            return address;
//        }
//        public void setAddress(List<Address> address) {
//            this.address = address;
//        }
//
//        public void setTransaction(List<Transactions> transactions) {
//            this.transactions = transactions;
//        }
//
//        public List<Transactions> getTransaction()
//        {
//            return transactions;
//        }


    @Override
    public String toString() {
        return "Customer [id=" + id + ", contact=" + contact + ", email=" + email + ", name=" + name + ", password="
                + password + ", surname=" + surname + ", bankDetails=" + bankDetails + ", address=" + address
                + ", transactions=" + transactions + "]";
    }

    public String redirect()
	{
		String result = null;
		if(!isEmailAvailable().equals("failure"))
		{
			saveCustomerRecord();
			result = "AddressRegister.xhtml";
		}
		else
		{
			result = "CustomerRegister.xhtml";
		}
		return result;
	}

	public String redirectToHomePage()
	{
		String log = null;
		System.out.println("***********Result before login******" + login());
		login();
		if(!login().equals("failure"))
		{
		    //System.out.println(id);
			log = "homepage.xhtml";
//			customerDAO = new CustomerDAO();
//			customerDAO.addTransactions(id);

			TransactionsDAO transactionsDAO = new TransactionsDAO();
			transactionsDAO.addTransactions(customerDAO.getCustomerID());
            //TransactionsBean transactionsBean = new TransactionsBean();
            //transactionsBean.saveTransactionRecord(id);
		}
		else
			log = "login.xhtml";
		return log;
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No user with that details can be found"))
	}



	public String login()
	{
		customerDAO = new CustomerDAO();
		//customer = new Customer();
		String result = customerDAO.login(email, password);
		return result;
	}

	public void saveCustomerRecord()
    {

        customerDAO = new CustomerDAO();
        customerDAO.add(new Customer(contact, email, name, BCrypt.hashpw(password, BCrypt.gensalt(12)), surname));
        System.out.println("Saving the student record: ");
    }

    public void deleteStudentRecord()
    {
        System.out.println("Deleting student record");
        customerDAO = new CustomerDAO();
        customerDAO.delete(customer.getId());
    }

//    public List<Customer> readCustomer()
//    {
//        System.out.println("Reading customer by Id");
//        customerDAO = new CustomerDAO();
//        customerList = customerDAO.getById(id);
//        return customerList;
//    }

    public void updateCustomerRecord()
    {
        System.out.println("Updating student record");
        customerDAO = new CustomerDAO();
        customerDAO.update(new Customer(id, contact,name, email, password, surname));
    }
    public void logout()
	{
		customerDAO = new CustomerDAO();
		customerDAO.logout();

	}

	public void preload()
    {
        customerDAO = new CustomerDAO();
        customerDAO.preloadData();
        System.out.println("Data preloaded");
    }

    public String isEmailAvailable()
	{
		customerDAO = new CustomerDAO();
		String result = customerDAO.isEmailAvailable(email);
		return result;
	}

}
