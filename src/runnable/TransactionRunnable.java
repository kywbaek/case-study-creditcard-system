package runnable;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.TransactionDao;
import dao.TransactionDaoImpl;
import model.Transaction;
import resource.NoResultException;

public class TransactionRunnable {
	Scanner sc = new Scanner(System.in);
	String fileName, strFormat;
	PrintWriter printTo = null;
	
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
				throw new NoResultException("Transactions with the information provided do not exist in the database...");
			}
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		fileName = new SimpleDateFormat("yyyyMMddHHmmss'_output.txt'").format(new Date());
		try {
			printTo = new PrintWriter(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		strFormat = "%-16s%-8s%-8s%-8s%-20s%-12s%-14s%-20s%s\n";
		printTo.println("The transactions made by customers living in the zipcode("+zip+") on "+y+"/"+m+"\n");
		printTo.printf(strFormat, "TRANSACTION_ID","DAY","MONTH","YEAR","CREDIT_CARD_NO",
				"CUST_SSN","BRANCH_CODE","TRANSACTION_TYPE","TRANSACTION_VALUE");

		System.out.printf(strFormat, "TRANSACTION_ID","DAY","MONTH","YEAR","CREDIT_CARD_NO",
				"CUST_SSN","BRANCH_CODE","TRANSACTION_TYPE","TRANSACTION_VALUE");
		
		for (Transaction tran: trans) {
			int TRANSACTION_ID = tran.getTRANSACTION_ID();
			int DAY = tran.getDAY();
			int MONTH = tran.getMONTH();
			int YEAR = tran.getYEAR();
			String CREDIT_CARD_NO = tran.getCREDIT_CARD_NO();
			int CUST_SSN = tran.getCUST_SSN();
			int BRANCH_CODE = tran.getBRANCH_CODE();
			String TRANSACTION_TYPE = tran.getTRANSACTION_TYPE();
			double TRANSACTION_VALUE = tran.getTRANSACTION_VALUE();
			
			printTo.printf(strFormat, TRANSACTION_ID,DAY,MONTH,YEAR,CREDIT_CARD_NO,
					CUST_SSN,BRANCH_CODE,TRANSACTION_TYPE,TRANSACTION_VALUE);
		
			System.out.printf(strFormat, TRANSACTION_ID,DAY,MONTH,YEAR,CREDIT_CARD_NO,
					CUST_SSN,BRANCH_CODE,TRANSACTION_TYPE,TRANSACTION_VALUE);
		}
		System.out.println("\nThe output is recorded to the file >>> "+fileName);
		printTo.close();
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
			
			fileName = new SimpleDateFormat("yyyyMMddHHmmss'_output.txt'").format(new Date());
			printTo = new PrintWriter(new File(fileName));
			printTo.println("The transaction type: "+type);
			printTo.println("Total transaction value: " + totalVal);
			printTo.println("Total number of transaction: " + totalNum);
			System.out.println("\nThe output is recorded to the file >>> "+fileName);
			printTo.close();
		} catch (NullPointerException e) {
			System.out.println("Transactions with the information provided do not exist in the database...");
		} catch (IOException e) {
			e.printStackTrace();
			return;
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
