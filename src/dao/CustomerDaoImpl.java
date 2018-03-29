package dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connector.ConnectionMySQL;
import model.Customer;
import model.Transaction;
import resource.QueriesMySQL;

public class CustomerDaoImpl extends ConnectionMySQL implements CustomerDao {
	
	private Customer extractCustFromResultSet(ResultSet rs) throws SQLException {
	    Customer cust = new Customer();
	    
	    cust.setFIRST_NAME( rs.getString("FIRST_NAME") );
	    cust.setMIDDLE_NAME( rs.getString("MIDDLE_NAME") );
	    cust.setLAST_NAME( rs.getString("LAST_NAME") );
	    cust.setSSN( rs.getInt("SSN") );
	    cust.setCREDIT_CARD_NO( rs.getString("CREDIT_CARD_NO") );
	    cust.setAPT_NO( rs.getString("APT_NO") );
	    cust.setSTREET_NAME( rs.getString("STREET_NAME") );
	    cust.setCUST_CITY( rs.getString("CUST_CITY") );
	    cust.setCUST_STATE( rs.getString("CUST_STATE") );
	    cust.setCUST_COUNTRY( rs.getString("CUST_COUNTRY") );
	    cust.setCUST_ZIP( rs.getString("CUST_ZIP") );
	    cust.setCUST_PHONE( rs.getInt("CUST_PHONE") );
	    cust.setCUST_EMAIL( rs.getString("CUST_EMAIL") );
	    
	    return cust;
	}
	
	@Override
	public Customer getCust(int SSN, String CREDIT_CARD_NO) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.getCustDetails);
			ps.setInt(1, SSN);
			ps.setString(2, CREDIT_CARD_NO);
			rs = ps.executeQuery();
			
			if(rs.next()) {
	            return extractCustFromResultSet(rs);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Customer> getAllCust() {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.getAllCust);
			rs = ps.executeQuery();
			ArrayList<Customer> custs = new ArrayList<Customer>();
			
			while (rs.next()) {
	            Customer cust = extractCustFromResultSet(rs);
	            custs.add(cust);
	        }
			return custs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertCust(Customer cust) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.insertCust);
			ps.setString(1, cust.getFIRST_NAME());
			ps.setString(2, cust.getMIDDLE_NAME());
			ps.setString(3, cust.getLAST_NAME());
			ps.setInt(4, cust.getSSN());
			ps.setString(5, cust.getCREDIT_CARD_NO());
			ps.setString(6, cust.getAPT_NO());
			ps.setString(7, cust.getSTREET_NAME());
			ps.setString(8, cust.getCUST_CITY());
			ps.setString(9, cust.getCUST_STATE());
			ps.setString(10, cust.getCUST_COUNTRY());
			ps.setString(11, cust.getCUST_ZIP());
			ps.setLong(12, cust.getCUST_PHONE());
			ps.setString(13, cust.getCUST_EMAIL());
			int i = ps.executeUpdate();
			
			if (i==1) {
				System.out.println("Insertion Successful!!");
			} else {
				System.out.println("Insertion Failed!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void updateCust(Customer cust) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.updateCust);
			ps.setString(1, cust.getFIRST_NAME());
			ps.setString(2, cust.getMIDDLE_NAME());
			ps.setString(3, cust.getLAST_NAME());
			ps.setString(4, cust.getAPT_NO());
			ps.setString(5, cust.getSTREET_NAME());
			ps.setString(6, cust.getCUST_CITY());
			ps.setString(7, cust.getCUST_STATE());
			ps.setString(8, cust.getCUST_COUNTRY());
			ps.setString(9, cust.getCUST_ZIP());
			ps.setInt(10, cust.getCUST_PHONE());
			ps.setString(11, cust.getCUST_EMAIL());
			ps.setInt(12, cust.getSSN());
			ps.setString(13, cust.getCREDIT_CARD_NO());
			int i = ps.executeUpdate();
			
			if (i==1) {
				System.out.println("\nUpdate Successful!!");
			} else {
				System.out.println("\nUpdate Failed!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void deleteCust(int SSN, String CREDIT_CARD_NO) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.deleteCust);
			ps.setInt(1, SSN);
			ps.setString(2, CREDIT_CARD_NO);
			int i = ps.executeUpdate();
			
			if(i==1) {
				System.out.println("Delete Successful!!");
	        } else {
	        	System.out.println("Delete Failed!!");
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean modifyCustDetails(Customer cust, String col, String val) {

		// Casting String to int for customer phone number
		if (col.equals("CUST_PHONE")) {
			try {
				cust.setCUST_PHONE(Integer.parseInt(val));
				return true;
			} catch (NumberFormatException e) {
				System.out.println("Invalid value for Customer Phone Number...");
				return false;
			}
		// For all other fields, we get setters by field names and modify the values using them
		} else {
			try {
				Class<?> c = Class.forName("model.Customer");
				Method setter = c.getMethod("set"+col, String.class);
				setter.invoke(cust, val);
				return true;
			} catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
				return false;
			} catch (NoSuchMethodException e) {
				System.out.println("Invalid field name...");
				return false;
			}
		}

	}
	
	public ArrayList<Transaction> getMonthDetailsByCC(int SSN, String CREDIT_CARD_NO, int YEAR, int MONTH) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.getMonthDetailsByCC);
			ps.setInt(1, SSN);
			ps.setString(2, CREDIT_CARD_NO);
			ps.setInt(3, YEAR);
			ps.setInt(4, MONTH);
			rs = ps.executeQuery();
			
			ArrayList<Transaction> trans = new ArrayList<Transaction>();
			TransactionDaoImpl td = new TransactionDaoImpl();
			
			while (rs.next()) {
				Transaction tran = td.extractTranFromResultSet(rs);
				trans.add(tran);
			}
			return trans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Transaction> getDetailByCustDate(int ssn, String ccn, String fromDate, String toDate) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.getDetailByCustDate);
			ps.setInt(1, ssn);
			ps.setString(2, ccn);
			ps.setString(3, fromDate);
			ps.setString(4, toDate);
			rs = ps.executeQuery();
			
			ArrayList<Transaction> trans = new ArrayList<Transaction>();
			TransactionDaoImpl td = new TransactionDaoImpl();
			
			while (rs.next()) {
				Transaction tran = td.extractTranFromResultSet(rs);
				trans.add(tran);
			}
			return trans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

