package com.reversevending.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="customer", schema = "reversevendingmachine")

public class Customer implements Serializable {
    /**
     * Attributes associated with the customer
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private long id;

    @Column(name = "contact")
    private String contact;


    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "surname")
    private String surname;

    @Column(name = "image")
    private byte[] image;

    //creating bank ID for the Collection of the ArrayList using annotations
    @OneToMany(mappedBy = "customer")
    private Set<BankDetails> bankDetails;

    @OneToMany(mappedBy = "customer")
    private Set<Address> address;

    @OneToMany(mappedBy = "customer")
    private Set<Transactions> transactions;

    @OneToMany(mappedBy = "customer")
    private Set<UserRoles> roles;

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Customer() {
    }

    public Customer(long id, String contact, String email, String name, String password, String surname){
        this.id = id;
        this.contact = contact;
        this.email = email;
        this.name = name;
        this.password = password;
        this.surname = surname;

    }


    public Customer(String contact, String email, String name, String password, String surname) {
        this.contact = contact;
        this.email = email;
        this.name = name;
        this.password = password;
        this.surname = surname;
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

//    @Override
//    public String toString() {
//        return "Customer [id=" + id + ", contact=" + contact + ", email=" + email + ", name=" + name + ", password="
//                + password + ", surname=" + surname + ", bankDetails=" + bankDetails + ", address=" + address
//                + ", transactions=" + transactions + "]";
//    }

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
