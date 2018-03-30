package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connector.ConnectionMySQL;
import model.Transaction;
import resource.QueriesMySQL;

public class TransactionDaoImpl extends ConnectionMySQL implements TransactionDao{
	
	Transaction extractTranFromResultSet(ResultSet rs) {
	    Transaction tran = new Transaction();
	    
	    try {
		    tran.setTRANSACTION_ID( rs.getInt("TRANSACTION_ID") );
		    tran.setDAY( rs.getInt("DAY") );
		    tran.setMONTH( rs.getInt("MONTH") );
		    tran.setYEAR( rs.getInt("YEAR") );
		    tran.setCREDIT_CARD_NO( rs.getString("CREDIT_CARD_NO") );
		    tran.setCUST_SSN( rs.getInt("CUST_SSN") );
		    tran.setBRANCH_CODE( rs.getInt("BRANCH_CODE") );
		    tran.setTRANSACTION_TYPE( rs.getString("TRANSACTION_TYPE") );
		    tran.setTRANSACTION_VALUE( rs.getDouble("TRANSACTION_VALUE") );
		    
		    return tran;
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	private Transaction extractTranTotalFromResultSet(ResultSet rs) {
	    Transaction tran = new Transaction();
	    
	    try {
		    tran.setTOTAL_VALUES( rs.getDouble("TOTAL_VALUES") );
		    tran.setTOTAL_NUMBERS( rs.getInt("TOTAL_NUMBERS") );
		    
		    return tran;
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	private Transaction extractTranTypeFromResultSet(ResultSet rs) {
	    Transaction tran = new Transaction();
	    
	    try {
	    	tran.setTRANSACTION_TYPE( rs.getString("TRANSACTION_TYPE") );
		    
		    return tran;
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public Transaction getTran(int TRANSACTION_ID) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.getTranDetails);
			ps.setInt(1, TRANSACTION_ID);
			rs = ps.executeQuery();
			
			if(rs.next()) {
	            return extractTranFromResultSet(rs);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Transaction> getAllTran() {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.getAllTran);
			rs = ps.executeQuery();
			ArrayList<Transaction> trans = new ArrayList<Transaction>();
			
			while (rs.next()) {
	            Transaction tran = extractTranFromResultSet(rs);
	            trans.add(tran);
	        }
			return trans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertTran(Transaction tran) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.insertTran);
			ps.setInt(1, tran.getTRANSACTION_ID());
			ps.setInt(2, tran.getDAY());
			ps.setInt(3, tran.getMONTH());
			ps.setInt(4, tran.getYEAR());
			ps.setString(5, tran.getCREDIT_CARD_NO());
			ps.setInt(6, tran.getCUST_SSN());
			ps.setInt(7, tran.getBRANCH_CODE());
			ps.setString(8, tran.getTRANSACTION_TYPE());
			ps.setDouble(9, tran.getTRANSACTION_VALUE());
			int i = ps.executeUpdate();
			
			if (i==1) {
				System.out.println("Insertion Successful...");
			} else {
				System.out.println("Insertion Failed...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void updateTran(Transaction tran) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.updateTran);
			ps.setInt(1, tran.getDAY());
			ps.setInt(2, tran.getMONTH());
			ps.setInt(3, tran.getYEAR());
			ps.setString(4, tran.getCREDIT_CARD_NO());
			ps.setInt(5, tran.getCUST_SSN());
			ps.setInt(6, tran.getBRANCH_CODE());
			ps.setString(7, tran.getTRANSACTION_TYPE());
			ps.setDouble(8, tran.getTRANSACTION_VALUE());
			ps.setInt(9, tran.getTRANSACTION_ID());
			int i = ps.executeUpdate();
			
			if (i==1) {
				System.out.println("Update Successful...");
			} else {
				System.out.println("Update Failed...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void deleteTran(int TRANSACTION_ID) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.deleteTran);
			ps.setInt(1, TRANSACTION_ID);
			int i = ps.executeUpdate();
			
			if(i==1) {
				System.out.println("Delete Successful...");
	        } else {
	        	System.out.println("Delete Failed...");
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Transaction> tranTypes() {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.tranTypes);
			rs = ps.executeQuery();
			
			ArrayList<Transaction> trans = new ArrayList<Transaction>();
			while (rs.next()) {
				Transaction tran = extractTranTypeFromResultSet(rs);
	            trans.add(tran);
	        }
			return trans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Transaction> tranByCustZipDate(String zip, int y, int m) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.tranByCustZipDate);
			ps.setString(1, zip);
			ps.setInt(2, y);
			ps.setInt(3, m);
			rs = ps.executeQuery();
			ArrayList<Transaction> trans = new ArrayList<Transaction>();
			
			while (rs.next()) {
				Transaction tran = extractTranFromResultSet(rs);
	            trans.add(tran);
	        }
			return trans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Transaction tranTotalBytype(String type) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.tranTotalBytype);
			ps.setString(1, type); 
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Transaction tran = extractTranTotalFromResultSet(rs);
				return tran;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Transaction tranTotalByBranState(String state) {
		try {
			if (con==null || con.isClosed()) {
				dbConnect();
			}
			ps = con.prepareStatement(QueriesMySQL.tranTotalByBranState);
			ps.setString(1, state); 
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Transaction tran = extractTranTotalFromResultSet(rs);
				return tran;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
