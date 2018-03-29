package model;

public class Transaction {
	private String CREDIT_CARD_NO, TRANSACTION_TYPE;
	private Integer TRANSACTION_ID, DAY, MONTH, YEAR, CUST_SSN, BRANCH_CODE, TOTAL_NUMBERS;
	private Double TRANSACTION_VALUE, TOTAL_VALUES;
	
	public String getCREDIT_CARD_NO() {
		return CREDIT_CARD_NO;
	}

	public void setCREDIT_CARD_NO(String cREDIT_CARD_NO) {
		CREDIT_CARD_NO = cREDIT_CARD_NO;
	}

	public String getTRANSACTION_TYPE() {
		return TRANSACTION_TYPE;
	}

	public void setTRANSACTION_TYPE(String tRANSACTION_TYPE) {
		TRANSACTION_TYPE = tRANSACTION_TYPE;
	}

	public Integer getTRANSACTION_ID() {
		return TRANSACTION_ID;
	}

	public void setTRANSACTION_ID(Integer tRANSACTION_ID) {
		TRANSACTION_ID = tRANSACTION_ID;
	}

	public Integer getDAY() {
		return DAY;
	}

	public void setDAY(Integer dAY) {
		DAY = dAY;
	}

	public Integer getMONTH() {
		return MONTH;
	}

	public void setMONTH(Integer mONTH) {
		MONTH = mONTH;
	}

	public Integer getYEAR() {
		return YEAR;
	}

	public void setYEAR(Integer yEAR) {
		YEAR = yEAR;
	}

	public Integer getCUST_SSN() {
		return CUST_SSN;
	}

	public void setCUST_SSN(Integer cUST_SSN) {
		CUST_SSN = cUST_SSN;
	}

	public Integer getBRANCH_CODE() {
		return BRANCH_CODE;
	}

	public void setBRANCH_CODE(Integer bRANCH_CODE) {
		BRANCH_CODE = bRANCH_CODE;
	}

	public Double getTRANSACTION_VALUE() {
		return TRANSACTION_VALUE;
	}

	public void setTRANSACTION_VALUE(Double tRANSACTION_VALUE) {
		TRANSACTION_VALUE = tRANSACTION_VALUE;
	}

	public Integer getTOTAL_NUMBERS() {
		return TOTAL_NUMBERS;
	}

	public void setTOTAL_NUMBERS(Integer tOTAL_NUMBERS) {
		TOTAL_NUMBERS = tOTAL_NUMBERS;
	}

	public Double getTOTAL_VALUES() {
		return TOTAL_VALUES;
	}

	public void setTOTAL_VALUES(Double tOTAL_VALUES) {
		TOTAL_VALUES = tOTAL_VALUES;
	}
}
