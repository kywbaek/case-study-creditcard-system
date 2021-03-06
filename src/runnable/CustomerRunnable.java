package runnable;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import model.Customer;
import model.Transaction;
import resource.NoResultException;

public class CustomerRunnable {
	Scanner sc = new Scanner(System.in);
	String fileName, strFormat, strFormatCSV;
	PrintWriter printTo = null;
	
	// option 4
	public void getCustDetails() {
		CustomerDao cd = new CustomerDaoImpl();
		System.out.print("Please enter the social security number: "); 
		int ssn;
		
		// run until valid input is entered
		while (true) {
			try {
				ssn = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.print("Invalid input, please enter again: ");
				sc.next();
			}
		}
		System.out.print("Please enter the credit card number: "); 
		String ccn = sc.next();

		Customer cust = cd.getCust(ssn, ccn);
		
		// print the result and write to a file
		try {
			System.out.println("FIRST_NAME\t"+cust.getFIRST_NAME());
			System.out.println("MIDDLE_NAME\t"+cust.getMIDDLE_NAME());
			System.out.println("LAST_NAME\t"+cust.getLAST_NAME());
			System.out.println("SSN\t\t"+cust.getSSN());
			System.out.println("CREDIT_CARD_NO\t"+cust.getCREDIT_CARD_NO());
			System.out.println("APT_NO\t\t"+cust.getAPT_NO());
			System.out.println("STREET_NAME\t"+cust.getSTREET_NAME());
			System.out.println("CUST_CITY\t"+cust.getCUST_CITY());
			System.out.println("CUST_STATE\t"+cust.getCUST_STATE());
			System.out.println("CUST_COUNTRY\t"+cust.getCUST_COUNTRY());
			System.out.println("CUST_ZIP\t"+cust.getCUST_ZIP());
			System.out.println("CUST_PHONE\t"+cust.getCUST_PHONE());
			System.out.println("CUST_EMAIL\t"+cust.getCUST_EMAIL());
			
			fileName = new SimpleDateFormat("yyyyMMddHHmmss'_CustDetail.csv'").format(new Date());
			printTo = new PrintWriter(new File(fileName));
			strFormatCSV = "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n";
			printTo.printf(strFormatCSV,"FIRST_NAME","MIDDLE_NAME","LAST_NAME","SSN","CREDIT_CARD_NO","APT_NO",
						"STREET_NAME","CUST_CITY","CUST_STATE","CUST_COUNTRY","CUST_ZIP","CUST_PHONE","CUST_EMAIL");
			printTo.printf(strFormatCSV,cust.getFIRST_NAME(),cust.getMIDDLE_NAME(),cust.getLAST_NAME(),cust.getSSN(),
					cust.getCREDIT_CARD_NO(),cust.getAPT_NO(),cust.getSTREET_NAME(),cust.getCUST_CITY(),cust.getCUST_STATE(),
					cust.getCUST_COUNTRY(),cust.getCUST_ZIP(),cust.getCUST_PHONE(),cust.getCUST_EMAIL());
			System.out.println("\nThe output is recorded to the file >>> "+fileName);
			printTo.close();
			
		} catch (NullPointerException e) {
			System.out.println("Customer with the information provided does not exist in the database...");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	// helper method for option 5
	public void getCustDetails(Customer cust) {
		System.out.println("\tSSN\t\t"+cust.getSSN());
		System.out.println("\tCREDIT_CARD_NO\t"+cust.getCREDIT_CARD_NO());
		System.out.println("0)\tFIRST_NAME\t"+cust.getFIRST_NAME());
		System.out.println("1)\tMIDDLE_NAME\t"+cust.getMIDDLE_NAME());
		System.out.println("2)\tLAST_NAME\t"+cust.getLAST_NAME());
		System.out.println("3)\tAPT_NO\t\t"+cust.getAPT_NO());
		System.out.println("4)\tSTREET_NAME\t"+cust.getSTREET_NAME());
		System.out.println("5)\tCUST_CITY\t"+cust.getCUST_CITY());
		System.out.println("6)\tCUST_STATE\t"+cust.getCUST_STATE());
		System.out.println("7)\tCUST_COUNTRY\t"+cust.getCUST_COUNTRY());
		System.out.println("8)\tCUST_ZIP\t"+cust.getCUST_ZIP());
		System.out.println("9)\tCUST_PHONE\t"+cust.getCUST_PHONE());
		System.out.println("10)\tCUST_EMAIL\t"+cust.getCUST_EMAIL()+"\n");
	}
	
	// option 5
	public void modifyCustDetails() {
		int cols = 0;
		String modify = "y";
		CustomerDao cd = new CustomerDaoImpl();
		System.out.print("Please enter the social security number: ");
		int ssn;
		
		// run until valid input is entered
		while (true) {
			try {
				ssn = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.print("Invalid input, please enter again: ");
				sc.next();
			}
		}
		System.out.print("Please enter the credit card number: "); 
		String ccn = sc.next();
		
		Customer cust = cd.getCust(ssn, ccn);
		
		// return when no result
		try {
			System.out.println("\t** Current Information **");
			getCustDetails(cust);
		} catch (NullPointerException e) {
			System.out.println("\nCustomer with the information provided does not exist in the database...");
			return;
		}
		
		// run until value of "modify" changes (i.e. until user makes all needed modifications)
		while (modify.equals("y")) {
			System.out.print("Please enter the field number to modify: ");
			Integer colNum = -1;
			while (!(colNum instanceof Integer) || colNum>10 || colNum<0) {
				try {
					colNum = sc.nextInt();
					if (colNum>10 || colNum<0) {
						System.out.print("Invalid option, please select again: ");
					}
				} catch (InputMismatchException e) {
					System.out.print("Invalid option, please select again: ");
					sc.next();
				}
			}
			String[] colArr = {"FIRST_NAME","MIDDLE_NAME","LAST_NAME","APT_NO",
								"STREET_NAME","CUST_CITY","CUST_STATE","CUST_COUNTRY",
								"CUST_ZIP","CUST_PHONE","CUST_EMAIL"};
			String userCol = colArr[colNum];
				
			System.out.print("Please enter the new value for "+userCol+": "); 
			sc.nextLine();
			String userVal = sc.nextLine();
			if (cd.modifyCustDetails(cust, userCol, userVal)==true) {
				cols++;
			}
			System.out.print("Modify another field(y/n)? "); modify = sc.next(); 
		}
		if (cols>0) {
			cd.updateCust(cust);
			System.out.println("\n\t** Modified Information **");
			getCustDetails(cust);
		} else {
			System.out.println("\nNo fields are modified...");
		}
	}
	
	// option 6
	public void getMonthDetailsByCC() {
		CustomerDao cd = new CustomerDaoImpl();
		System.out.print("Please enter the social security number: "); 
		int ssn;
		
		// run until valid input is entered
		while (true) {
			try {
				ssn = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.print("Invalid input, please enter again: ");
				sc.next();
			}
		}
		System.out.print("Please enter the credit card number: "); 
		String ccn = sc.next();
		System.out.print("Please enter the year: "); 
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
		System.out.print("Please enter the month: "); 
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
		
		ArrayList<Transaction> trans = cd.getMonthDetailsByCC(ssn, ccn, y, m);
		
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
		fileName = new SimpleDateFormat("yyyyMMddHHmmss'_CustTranByMonth.csv'").format(new Date());
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
	
	// option 7
	public void getDetailByCustDate() {
		CustomerDao cd = new CustomerDaoImpl();
		System.out.print("Please enter the social security number: "); 
		int ssn;
		
		// run until valid input is entered
		while (true) {
			try {
				ssn = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.print("Invalid input, please enter again: ");
				sc.next();
			}
		}
		System.out.print("Please enter the credit card number: "); 
		String ccn = sc.next();
		System.out.print("Please enter the 'from date': \tyear  -> "); 
		String y = sc.next();
		System.out.print("\t\t\t\tmonth -> "); 
		String m = sc.next();
		System.out.print("\t\t\t\tday   -> "); 
		String d = sc.next();
		String fromDate = y+"-"+m+"-"+d;
		System.out.print("Please enter the 'to date': \tyear  -> "); 
		y = sc.next();
		System.out.print("\t\t\t\tmonth -> "); 
		m = sc.next();
		System.out.print("\t\t\t\tday   -> "); 
		d = sc.next();
		String toDate = y+"-"+m+"-"+d;
		
		ArrayList<Transaction> trans = cd.getDetailByCustDate(ssn, ccn, fromDate, toDate);
		
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
		fileName = new SimpleDateFormat("yyyyMMddHHmmss'_CustTranByPeriod.csv'").format(new Date());
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
}
