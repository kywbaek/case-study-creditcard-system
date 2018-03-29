package dao;

import java.util.ArrayList;

import model.Customer;
import model.Transaction;

public interface CustomerDao {
	public Customer getCust(int SSN, String CREDIT_CARD_NO);
	public ArrayList<Customer> getAllCust();
	public void insertCust(Customer cust); 
	public void updateCust(Customer cust);
	public void deleteCust(int SSN, String CREDIT_CARD_NO);
	public boolean modifyCustDetails(Customer cust, String col, String val);
	public ArrayList<Transaction> getMonthDetailsByCC(int SSN, String CREDIT_CARD_NO, int YEAR, int MONTH);
	public ArrayList<Transaction> getDetailByCustDate(int ssn, String ccn, String fromDate, String toDate);
}