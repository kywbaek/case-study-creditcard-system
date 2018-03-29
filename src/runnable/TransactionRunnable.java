package runnable;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.TransactionDao;
import dao.TransactionDaoImpl;
import model.Transaction;
import resource.MyException;

public class TransactionRunnable {
	Scanner sc = new Scanner(System.in);

	public void tranByCustZipDate() {
		TransactionDao td = new TransactionDaoImpl();
		
		System.out.print("Please the zipcode: ");
		String zip = sc.next();
		System.out.print("Please the year: ");
		int y;
		while (true) {
			try {
				y = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.print("Invalid input, please enter again: ");
				sc.next();
			}
		}
		System.out.print("Please the month: ");
		int m;
		while (true) {
			try {
				m = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.print("Invalid input, please enter again: ");
				sc.next();
			}
		}
		
		ArrayList<Transaction> trans = td.tranByCustZipDate(zip, y, m);
		try {
			if (trans.size()==0) {
				throw new MyException("Transactions with the information provided do not exist in the database...");
			}
		} catch (MyException e) {
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("TRANSACTION_ID\tDAY\tMONTH\tYEAR\tCREDIT_CARD_NO\t\t"
				+ "CUST_SSN\tBRANCH_CODE\tTRANSACTION_TYPE\tTRANSACTION_VALUE");
		
		for (Transaction tran: trans) {
			int TRANSACTION_ID = tran.getTRANSACTION_ID();
			int DAY = tran.getDAY();
			int MONTH = tran.getMONTH();
			int YEAR = tran.getYEAR();
			String CREDIT_CARD_NO = tran.getCREDIT_CARD_NO();
			int CUST_SSN = tran.getCUST_SSN();
			int BRANCH_CODE = tran.getBRANCH_CODE();
			String TRANSACTION_TYPE = tran.getTRANSACTION_TYPE();
			while (TRANSACTION_TYPE.length()<8) {
				TRANSACTION_TYPE += " ";
			}
			double TRANSACTION_VALUE = tran.getTRANSACTION_VALUE();
			System.out.println(TRANSACTION_ID+"\t\t"+DAY+"\t"+MONTH+"\t"+YEAR+"\t"
								+CREDIT_CARD_NO+"\t"+CUST_SSN+"\t"+BRANCH_CODE+"\t\t"
								+TRANSACTION_TYPE+"\t\t"+TRANSACTION_VALUE);
		}
	}
	
	public void tranTotalBytype() {
		TransactionDao td = new TransactionDaoImpl();

		try {
			// get available transaction types
			ArrayList<Transaction> trans = td.tranTypes();
			for (Transaction t: trans) {
				System.out.print(t.getTRANSACTION_TYPE()+"\n");
			}
			
			// get the result with given type
			System.out.print("\nPlease enter the transaction type from the list: ");
			String type = sc.next();
			
			Transaction tran = td.tranTotalBytype(type);
			double totalVal = tran.getTOTAL_VALUES();
			int totalNum = tran.getTOTAL_NUMBERS();
			System.out.println("Total transaction value: " + totalVal);
			System.out.println("Total number of transaction: " + totalNum);
		} catch (NullPointerException e) {
			System.out.println("Transactions with the information provided do not exist in the database...");
		}
	}
	
	public void tranTotalByBranState() {
		TransactionDao td = new TransactionDaoImpl();

		System.out.print("Please enter the branch state: ");
		String state = sc.next();
		try {
			Transaction tran = td.tranTotalByBranState(state);
			
			double totalVal = tran.getTOTAL_VALUES();
			int totalNum = tran.getTOTAL_NUMBERS();
			System.out.println("Total transaction value: " + totalVal);
			System.out.println("Total number of transaction: " + totalNum);
		} catch (NullPointerException e) {
			System.out.println("Transactions with the information provided do not exist in the database...");
		}
	}
}
