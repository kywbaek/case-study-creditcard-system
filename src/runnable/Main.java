package runnable;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		TransactionRunnable tr;
		CustomerRunnable cr;
		Scanner sc = new Scanner(System.in);
		
		// print available options and implement switch cases for them
		// run until "8) Exit" is selected
		while (true) {
			System.out.println("** Available Options **");
			System.out.println("1) Display the transactions made by customers living in a given zipcode for a given month and year");
			System.out.println("2) Display the total number and total values of transactions for a given type");
			System.out.println("3) Display the total number and total values of transactions for branches in a given state");
			System.out.println("4) Display the existing account details of a customer");
			System.out.println("5) Modify the existing account details of a customer");
			System.out.println("6) Display the monthly bill for a customer for a given month and year");
			System.out.println("7) Display the transactions made by a customer between two dates");
			System.out.println("8) Exit\n");
			System.out.print("Please select query from the above options: ");
			Integer option = 0;
			
			// run until valid option is selected
			while (!(option instanceof Integer) || option>8 || option<1) {
				try {
					option = sc.nextInt();
					switch (option) {
					case 1: tr = new TransactionRunnable();
							tr.tranByCustZipDate();
							break;
					case 2: tr = new TransactionRunnable();
							tr.tranTotalBytype();
							break;
					case 3: tr = new TransactionRunnable();
							tr.tranTotalByBranState();
							break;
					case 4: cr = new CustomerRunnable();
							cr.getCustDetails();
							break;
					case 5: cr = new CustomerRunnable();
							cr.modifyCustDetails();
							break;
					case 6: cr = new CustomerRunnable();
							cr.getMonthDetailsByCC();
							break;
					case 7: cr = new CustomerRunnable();
							cr.getDetailByCustDate();
							break;
					case 8: System.out.println("Exiting...");
							sc.close();
							System.exit(0);
					default:System.out.print("Invalid option, please select again: ");
							break;
					}	
				} catch (InputMismatchException e) {
					System.out.print("Invalid option, please select again: ");
					sc.next();
				}
			}
			sc.nextLine();
			System.out.print("\nPress enter to continue...\n");
			sc.nextLine();
		}
	}
// 4210653310061055		1237818
}
