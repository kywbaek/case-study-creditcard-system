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
	String fileName, strFormat, strFormatCSV;
	PrintWriter printTo = null;
	
	// option 1
	public void tranByCustZipDate() {
		TransactionDao td = new TransactionDaoImpl();
		
		System.out.print("Please the zipcode: ");
		String zip = sc.next();
		System.out.print("Please the year: ");
		int y;
		
		// run until valid input is entered
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
		
		// run until valid input is entered
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
		
		// return when no result
		try {
			if (trans.size()==0) {
				throw new NoResultException("Transactions with the information provided do not exist in the database...");
			}
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		// print the result and write to a file
		fileName = new SimpleDateFormat("yyyyMMddHHmmss'_TranByCustZipDate.csv'").format(new Date());
		try {
			printTo = new PrintWriter(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		strFormat = "%-16s%-8s%-8s%-8s%-20s%-12s%-14s%-20s%s\n";
		strFormatCSV = "%s,%s,%s,%s,%s,%s,%s,%s,%s\n";
		printTo.printf(strFormatCSV, "TRANSACTION_ID","DAY","MONTH","YEAR","CREDIT_CARD_NO",
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
			
			printTo.printf(strFormatCSV, TRANSACTION_ID,DAY,MONTH,YEAR,CREDIT_CARD_NO,
					CUST_SSN,BRANCH_CODE,TRANSACTION_TYPE,TRANSACTION_VALUE);
		
			System.out.printf(strFormat, TRANSACTION_ID,DAY,MONTH,YEAR,CREDIT_CARD_NO,
					CUST_SSN,BRANCH_CODE,TRANSACTION_TYPE,TRANSACTION_VALUE);
		}
		System.out.println("\nThe output is recorded to the file >>> "+fileName);
		printTo.close();
	}
	
	// option 2
	public void tranTotalBytype() {
		TransactionDao td = new TransactionDaoImpl();

		// print the result and write to a file
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
			
			fileName = new SimpleDateFormat("yyyyMMddHHmmss'_TranTotalByType.csv'").format(new Date());
			printTo = new PrintWriter(new File(fileName));
			strFormatCSV = "%s,%s,%s\n";
			printTo.printf(strFormatCSV,"TRANSACTION_TYPE","TOTAL_VALUE","TOTAL_NUMBER_OF_TRANSACTION");
			printTo.printf(strFormatCSV,type,totalVal,totalNum);
			System.out.println("\nThe output is recorded to the file >>> "+fileName);
			printTo.close();
		} catch (NullPointerException e) {
			System.out.println("Transactions with the information provided do not exist in the database...");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	// option 3
	public void tranTotalByBranState() {
		TransactionDao td = new TransactionDaoImpl();

		System.out.print("Please enter the branch state: ");
		String state = sc.next();
		
		// print the result and write to a file
		try {
			Transaction tran = td.tranTotalByBranState(state);
			
			double totalVal = tran.getTOTAL_VALUES();
			int totalNum = tran.getTOTAL_NUMBERS();
			System.out.println("Total transaction value: " + totalVal);
			System.out.println("Total number of transaction: " + totalNum);
			
			fileName = new SimpleDateFormat("yyyyMMddHHmmss'_TranTotalByState.csv'").format(new Date());
			printTo = new PrintWriter(new File(fileName));
			strFormatCSV = "%s,%s,%s\n";
			printTo.printf(strFormatCSV,"BRANCH_STATE","TOTAL_VALUE","TOTAL_NUMBER_OF_TRANSACTION");
			printTo.printf(strFormatCSV,state,totalVal,totalNum);
			System.out.println("\nThe output is recorded to the file >>> "+fileName);
			printTo.close();
		} catch (NullPointerException e) {
			System.out.println("Transactions with the information provided do not exist in the database...");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
}
