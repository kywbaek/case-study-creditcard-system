package resource;

public class QueriesMySQL {
	public final static String tranByCustZipDate = "SELECT cr.* "
												+ "	FROM CDW_SAPP_CREDITCARD cr " 
												+ "		JOIN CDW_SAPP_CUSTOMER c "
												+ "			ON cr.CUST_SSN = c.SSN "
												+ "				AND cr.CREDIT_CARD_NO = c.CREDIT_CARD_NO "
												+ "WHERE c.CUST_ZIP = ? "
												+ "		AND cr.YEAR = ? "
												+ "		AND cr.MONTH = ? "
												+ "ORDER BY cr.DAY DESC";	//cust_zip(string), month(int), year(int)
			
	public final static String tranTotalBytype = "SELECT TRANSACTION_TYPE, "
												+ "	SUM(TRANSACTION_VALUE) total_values, "
												+ "	COUNT(*) total_numbers "
												+ "FROM cdw_sapp_creditcard	"
												+ "WHERE TRANSACTION_TYPE=? "
												+ "GROUP BY TRANSACTION_TYPE";	//transaction_type(string)
	
	public final static String tranTotalByBranState = "SELECT br.BRANCH_STATE, "
													+ "	SUM(cr.TRANSACTION_VALUE) total_values, "
													+ "	COUNT(*) total_numbers "
													+ "FROM cdw_sapp_creditcard cr "
													+ "	JOIN cdw_sapp_branch br "
													+ "		ON cr.BRANCH_CODE = br.BRANCH_CODE "
													+ "WHERE br.BRANCH_STATE = ? "
													+ "GROUP BY br.BRANCH_STATE";	//branch_state(string)
	
	
	
	public final static String getCustDetails = "SELECT * FROM cdw_sapp_customer "
												+ "WHERE SSN = ? "
												+ "	AND CREDIT_CARD_NO = ?";	//ssn(int), credit_card_no(string)
	
	public final static String getMonthDetailsByCC = "SELECT * FROM cdw_sapp_creditcard "
													+ "WHERE (CUST_SSN, CREDIT_CARD_NO, YEAR, MONTH) = ( ?, ?, ?, ? ) "
													+ "ORDER BY DAY DESC";	//cust_ssn(int), credit_card_no(string), month(int), year(int)
	
	public final static String getDetailByCustDate = "SELECT * FROM cdw_sapp_creditcard "
													+ "WHERE CUST_SSN = ? "
													+ "	AND CREDIT_CARD_NO = ? "
													+ "	AND STR_TO_DATE(CONCAT(YEAR,'-',MONTH,'-',DAY), '%Y-%m-%d') >= ? "
													+ "	AND STR_TO_DATE(CONCAT(YEAR,'-',MONTH,'-',DAY), '%Y-%m-%d') <= ? "
													+ "ORDER BY YEAR DESC, MONTH DESC, DAY DESC";
													//ssn(int), credit_card_no(string),fromdate(string),todate(string)
	
	public final static String getAllCust = "SELECT * FROM cdw_sapp_customer";
	public final static String insertCust = "INSERT INTO cdw_sapp_customer values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	public final static String updateCust = "UPDATE cdw_sapp_customer SET FIRST_NAME=?, MIDDLE_NAME=?, LAST_NAME=?, "
											+ "APT_NO=?, STREET_NAME=?, CUST_CITY=?, CUST_STATE=?, "
											+ "CUST_COUNTRY=?, CUST_ZIP=?, CUST_PHONE=?, CUST_EMAIL=? "
											+ "WHERE SSN=? AND CREDIT_CARD_NO=?";
	public final static String deleteCust = "DELETE FROM cdw_sapp_customer WHERE SSN=? AND CREDIT_CARD_NO=?";
	
	public final static String getTranDetails = "SELECT * FROM cdw_sapp_creditcard WHERE TRANSACTION_ID = ?";
	public final static String getAllTran = "SELECT * FROM cdw_sapp_creditcard";
	public final static String insertTran = "INSERT INTO cdw_sapp_creditcard values ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	public final static String updateTran = "UPDATE cdw_sapp_creditcard SET DAY=?, MONTH=?, YEAR=?, CREDIT_CARD_NO=?, "
											+ "CUST_SSN=?, BRANCH_CODE=?, TRANSACTION_TYPE=?, TRANSACTION_VALUE=? "
											+ "WHERE TRANSACTION_ID = ?";
	public final static String deleteTran = "DELETE FROM cdw_sapp_creditcard WHERE TRANSACTION_ID = ?";
	public final static String tranTypes = "SELECT TRANSACTION_TYPE "
											+ "FROM cdw_sapp_creditcard	"
											+ "GROUP BY TRANSACTION_TYPE";
}

