package com.reversevending.beans;


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfChunk;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.reversevending.databaseOperationsDAO.AddressDAO;
import com.reversevending.databaseOperationsDAO.BankDetailsDAO;
import com.reversevending.databaseOperationsDAO.CustomerDAO;
import com.reversevending.databaseOperationsDAO.TransactionLineDAO;
import com.reversevending.domain.*;

import javax.faces.context.FacesContext;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PdfReceipt implements Serializable {
    private static CustomerDAO customer = new CustomerDAO();
    private static AddressDAO addressDAO = new AddressDAO();
    private static BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();

    public static String printReceipt(long customerId, Transactions transactions)
    {
        try{
            Customer customers = customer.getById(customerId);
            Address addressCustomer = addressDAO.getAddressByCustomerID(customers.getId());
            BankDetails bankDetails = bankDetailsDAO.getBankDetailsByCustomerId(customerId);
            System.out.println(bankDetails.getAccountNumber());

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/Users/thabomoopa/Downloads/Invoice.pdf"));
            //Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            document.open();

            //Create the table
            PdfPTable table = new PdfPTable(2);
            PdfPTable table1 = new PdfPTable(3);
            PdfPTable address = new PdfPTable(2);
            PdfPTable bank = new PdfPTable(2);
            tableStructure(table);
            tableStructure(table1);
            tableStructure(address);
            tableStructure(bank);


            //Customer Details
            PdfPCell cell1 = new PdfPCell(new Paragraph("Name"));
            cellType(cell1);

            PdfPCell cell2 = new PdfPCell(new Paragraph(customers.getName()));
            cellType(cell2);


            PdfPCell cell3 = new PdfPCell(new Paragraph("Surname"));
            cellType(cell3);


            PdfPCell cell4 = new PdfPCell(new Paragraph(customers.getSurname()));
            cellType(cell4);


            PdfPCell cell5 = new PdfPCell(new Paragraph("CellPhone Number"));
            cellType(cell5);


            PdfPCell cell6 = new PdfPCell(new Paragraph(customers.getContact()));
            cellType(cell6);

            PdfPCell cell8 = new PdfPCell(new Paragraph("Email"));
            cellType(cell8);

            PdfPCell cell7 = new PdfPCell(new Paragraph(customers.getEmail()));
            cellType(cell7);

            //Add cells to table
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell8);
            table.addCell(cell7);

            //Address Details
            PdfPCell cellHouseNumber = new PdfPCell(new Paragraph("House Number"));
            Integer houseNumber = addressCustomer.getHouseNumber();
            Integer postalCode = addressCustomer.getPostalCode();
            PdfPCell cellNumber = new PdfPCell(new Paragraph(houseNumber.toString()));

            PdfPCell cellCity = new PdfPCell(new Paragraph("City"));
            PdfPCell cellAddCity = new PdfPCell(new Paragraph(addressCustomer.getCity()));

            PdfPCell cellStreet = new PdfPCell(new Paragraph("Street"));
            PdfPCell cellAddStreet = new PdfPCell(new Paragraph(addressCustomer.getStreet()));

            PdfPCell cellProvince = new PdfPCell(new Paragraph("Province"));
            PdfPCell cellAddProvince = new PdfPCell(new Paragraph(addressCustomer.getProvince()));

            PdfPCell cellPostalCode = new PdfPCell(new Paragraph("PostalCode"));
            PdfPCell cellCode = new PdfPCell(new Paragraph(postalCode.toString()));


            address.addCell(cellHouseNumber);
            address.addCell(cellNumber);
            address.addCell(cellCity);
            address.addCell(cellAddCity);
            address.addCell(cellStreet);
            address.addCell(cellAddStreet);
            address.addCell(cellProvince);
            address.addCell(cellAddProvince);
            address.addCell(cellPostalCode);
            address.addCell(cellCode);

            //Bank Details
            PdfPCell cellBankName = new PdfPCell(new Paragraph("Bank Name"));
            PdfPCell cellNameBank = new PdfPCell(new Paragraph(bankDetails.getName()));

            PdfPCell cellAccount = new PdfPCell(new Paragraph("Account Number"));
            Long account = bankDetails.getAccountNumber();
            Long code = bankDetails.getBranchCode();
            PdfPCell cellAccountBank = new PdfPCell(new Paragraph(account.toString()));

            PdfPCell cellBranch = new PdfPCell(new Paragraph("Branch Code"));
            PdfPCell cellBranchCode = new PdfPCell(new Paragraph(code.toString()));

            bank.addCell(cellBankName);
            bank.addCell(cellNameBank);

            bank.addCell(cellAccount);
            bank.addCell(cellAccountBank);

            bank.addCell(cellBranch);
            bank.addCell(cellBranchCode);


            TransactionLineDAO tr = new TransactionLineDAO();

            System.out.println(transactions.getId());
            System.out.println(tr.populateTable(transactions.getId()));
            System.out.println();
            int counter = tr.populateTable(transactions.getId()).size();


            PdfPCell cellProductName  = new PdfPCell(new Paragraph("Item Name")) ;
            cellType(cellProductName);
            PdfPCell cellProductPrice  = new PdfPCell(new Paragraph("Item Price")) ;
            cellType(cellProductPrice);
            PdfPCell cellQuantity  = new PdfPCell(new Paragraph("Quantity")) ;
            cellType(cellQuantity);
            PdfPCell cellTotal = new PdfPCell(new Paragraph("TOTAL"));
            cellTotal.setColspan(2);

            while(counter != 0)
            {
                PdfPCell cellName = new PdfPCell(new Paragraph(tr.populateTable(transactions.getId()).iterator().next().getProducts().getName()));

                Double price = tr.populateTable(transactions.getId()).iterator().next().getProducts().getPrice();
                Integer quantity = tr.populateTable(transactions.getId()).iterator().next().getQuantity();
                Double total = tr.totalForEachTransaction(transactions.getId());

                PdfPCell cellPrice = new PdfPCell(new Paragraph("R " + price.toString()));
                PdfPCell celQuantity = new PdfPCell(new Paragraph(quantity.toString()));
                PdfPCell cellTotalFigure = new PdfPCell(new Paragraph("R " + total.toString()));

                table1.addCell(cellProductName);
                table1.addCell(cellQuantity);
                table1.addCell(cellProductPrice);

                table1.addCell(cellName);
                table1.addCell(cellPrice);
                table1.addCell(celQuantity);

                table1.addCell(cellTotal);
                table1.addCell(cellTotalFigure);
                --counter;

            }
            document.add(new Paragraph("Invoice"));

            document.add(new Paragraph("Customer Details"));
            document.add(table);

            document.add(new Paragraph("Address Information"));
            document.add(address);

            document.add(new Paragraph("Bank Details"));
            document.add(bank);

            document.add(new Paragraph("Transaction Information"));
            document.add(table1);

            System.out.println("***********4");
            document.close();
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("FileNotFound");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return "DownloadReceipt.xhtml";

    }

    public static void cellType(PdfPCell cell)
    {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    }

    public static void tableStructure(PdfPTable table)
    {
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
    }


    public static void main(String[] args){
        Transactions transaction = new Transactions();
        transaction.setId(7);
        printReceipt(107, transaction);
        TransactionLineDAO tr = new TransactionLineDAO();
        for(TransactionLine line: tr.populateTable(19))
            System.out.println(line);
    }





}
