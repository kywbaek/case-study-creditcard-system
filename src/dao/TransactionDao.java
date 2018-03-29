package dao;

import java.util.ArrayList;

import model.Transaction;

public interface TransactionDao {
	public Transaction getTran(int TRANSACTION_ID);
	public ArrayList<Transaction> getAllTran();
	public void insertTran(Transaction cust); 
	public void updateTran(Transaction cust);
	public void deleteTran(int TRANSACTION_ID);
	public ArrayList<Transaction> tranByCustZipDate(String zip, int y, int m);
	public Transaction tranTotalBytype(String type);
	public Transaction tranTotalByBranState(String state);
	public ArrayList<Transaction> tranTypes();
}
