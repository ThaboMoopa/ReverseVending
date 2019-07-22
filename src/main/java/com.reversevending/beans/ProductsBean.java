package com.reversevending.beans;

import com.reversevending.databaseOperationsDAO.ProductsDAO;
import com.reversevending.domain.Products;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductsBean implements Serializable {

    private ProductsDAO productsDAO;
    private Products product = new Products();


    //Go to controller
    public List<Products> getProductsList() {
        productsDAO = new ProductsDAO();
        productsList = productsDAO.populateTable();
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }

    private static List<Products> productsList;

    public void saveProductsRecord()
    {
        productsDAO = new ProductsDAO();
        productsDAO.add();
        System.out.println("Saving the student record: ");
    }

    public void deleteStudentRecord()
    {
        System.out.println("Deleting student record");
        productsDAO = new ProductsDAO();
        productsDAO.deleteProduct(product.getId());
    }

    public Products read()
    {
        System.out.println("Reading customer by Id");
        productsDAO = new ProductsDAO();
        return productsDAO.getProductById(product.getId());
    }

    public void updateStudentRecord()
    {
        System.out.println("Updating student record");
        productsDAO = new ProductsDAO();
        productsDAO.updateProduct(product);
    }
    public List<Products> populateProductsTable()
    {
        System.out.println("Populating the table");
        productsDAO = new ProductsDAO();
        productsList = productsDAO.populateTable();
        return productsList;
    }
}
