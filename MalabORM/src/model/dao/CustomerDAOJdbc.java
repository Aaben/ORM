package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CustomerBean;
import model.CustomerDAO;

public class CustomerDAOJdbc implements CustomerDAO {
	
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=java";
	private static final String Username = "sa";
	private static final String Password = "passw0rd";
	private static final String SELECT_BY_CUSTUD = "SELECT * FROM customer where custid=?";
	private static final String UPDATE = "UPDATE customer set  password=? ,email=?,birth=? where custid =?";

	public static void main(String[] args) {
		CustomerDAO customerDao = new CustomerDAOJdbc();
		CustomerBean select = customerDao.select("Ellen");
		System.out.println(select);                             
		customerDao.update("E".getBytes(),"iii@ntu", new java.sql.Date(0) , "GG");

	}

	@Override
	public CustomerBean select(String custid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerBean result = null;
		try {
			
			conn = DriverManager.getConnection(URL, Username, Password);
			pstmt = conn.prepareStatement(SELECT_BY_CUSTUD);
			pstmt.setString(1, custid); 
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new CustomerBean();
				result.setCustid(rs.getString("custid"));
				result.setPassword(rs.getBytes("password"));
				result.setEmail(rs.getString("email"));
				result.setBirth(rs.getDate("birth"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {

					rs.close();

				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			if (pstmt != null) {
				try {

					pstmt.close();

				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			if (conn != null) {
				try {

					conn.close();

				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
		}
		return result; 

	}


	@Override
	public boolean update(byte[] password, String email, java.util.Date birth, String custid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, Username, Password);
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setBytes(1,password);
			pstmt.setString(2,email);
			if(birth!=null){
				long time = birth.getTime();
				pstmt.setDate(3, new java.sql.Date(time));
			}else{
				pstmt.setDate(3,null);
			}
			pstmt.setString(4,custid);
			


			int i =pstmt.executeUpdate();
			if(i!=0) {
				return true;
				}
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			
				if (conn!=null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}
			} 
		return false;
	}

}
