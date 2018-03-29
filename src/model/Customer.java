package model;

public class Customer {
	private String FIRST_NAME, MIDDLE_NAME , LAST_NAME, CREDIT_CARD_NO, APT_NO, 
		STREET_NAME, CUST_CITY, CUST_STATE, CUST_COUNTRY, CUST_ZIP, CUST_EMAIL;
	private Integer SSN, CUST_PHONE;
	
	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}

	public String getMIDDLE_NAME() {
		return MIDDLE_NAME;
	}

	public void setMIDDLE_NAME(String mIDDLE_NAME) {
		MIDDLE_NAME = mIDDLE_NAME;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	public String getCREDIT_CARD_NO() {
		return CREDIT_CARD_NO;
	}

	public void setCREDIT_CARD_NO(String cREDIT_CARD_NO) {
		CREDIT_CARD_NO = cREDIT_CARD_NO;
	}

	public String getAPT_NO() {
		return APT_NO;
	}

	public void setAPT_NO(String aPT_NO) {
		APT_NO = aPT_NO;
	}

	public String getSTREET_NAME() {
		return STREET_NAME;
	}

	public void setSTREET_NAME(String sTREET_NAME) {
		STREET_NAME = sTREET_NAME;
	}

	public String getCUST_CITY() {
		return CUST_CITY;
	}

	public void setCUST_CITY(String cUST_CITY) {
		CUST_CITY = cUST_CITY;
	}

	public String getCUST_STATE() {
		return CUST_STATE;
	}

	public void setCUST_STATE(String cUST_STATE) {
		CUST_STATE = cUST_STATE;
	}

	public String getCUST_COUNTRY() {
		return CUST_COUNTRY;
	}

	public void setCUST_COUNTRY(String cUST_COUNTRY) {
		CUST_COUNTRY = cUST_COUNTRY;
	}

	public String getCUST_ZIP() {
		return CUST_ZIP;
	}

	public void setCUST_ZIP(String cUST_ZIP) {
		CUST_ZIP = cUST_ZIP;
	}

	public String getCUST_EMAIL() {
		return CUST_EMAIL;
	}

	public void setCUST_EMAIL(String cUST_EMAIL) {
		CUST_EMAIL = cUST_EMAIL;
	}

	public Integer getSSN() {
		return SSN;
	}

	public void setSSN(Integer sSN) {
		SSN = sSN;
	}

	public Integer getCUST_PHONE() {
		return CUST_PHONE;
	}

	public void setCUST_PHONE(Integer cUST_PHONE) {
		CUST_PHONE = cUST_PHONE;
	}
	

}
