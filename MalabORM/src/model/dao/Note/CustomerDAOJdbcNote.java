package model.dao.Note;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CustomerBean;
import model.CustomerDAO;

public class CustomerDAOJdbcNote implements CustomerDAO {
	// 3.參數外拉
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=java";
	private static final String Username = "sa";
	private static final String Password = "passw0rd";
	// 1. 須要先在SQL測試指令 注意custid 要加問號
	private static final String SELECT_BY_CUSTUD = "SELECT * FROM customer where custid=?";
	// 1. 資料型態要注意''只有varchar PK 無法更動放最後
	private static final String UPDATE = "UPDATE customer set  password=? ,email=?,birth=? where custid =?";


	public static void main(String[] args) {
		CustomerDAO customerDao = new CustomerDAOJdbcNote();
		CustomerBean select = customerDao.select("Ellen");
		System.out.println(select);                                 // 加毫秒數
		customerDao.update("F".getBytes(),"iii@ntu.edu.tw", new java.sql.Date(0) , "GG");
		System.out.println(customerDao.select("GG")); 
//		CustomerBean customerbean = new CustomerBean();
//		customerbean.setPassword("K".getBytes());
//		customerbean.setEmail("iii@iii");
//		customerbean.setBirth(new java.sql.Date(0));
//		customerbean.setCustid("GG");  //因為是PK以此找修改目標
//		customerDao.update(customerbean);
	}

	// 2. type=回傳物件(此為集合裡面都是值) 問號SQL資料型態varchar所以()裡塞String
	@Override
	public CustomerBean select(String custid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerBean result = null;
		try {
			// 3.兩個都需要getConnection 所以參數拉到外面寫
			conn = DriverManager.getConnection(URL, Username, Password);
			// 3.外面有宣告屬性 裡面就不需要
			pstmt = conn.prepareStatement(SELECT_BY_CUSTUD);
			// 4.
			pstmt.setString(1, custid); //select只有一個 ?去db撈資料
			rs = pstmt.executeQuery();//執行動態指令
			if (rs.next()) { // 因為PK所以只有取得一筆資料不用WHILE
				result = new CustomerBean();
				result.setCustid(rs.getString("custid"));
				result.setPassword(rs.getBytes("password"));
				result.setEmail(rs.getString("email"));
				result.setBirth(rs.getDate("birth"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {/*
					 * 若conn 失敗掛掉 則後面pstmt,rs都不執行所以為null 而null
					 * 無法close()所以要排除null呼叫close的情況
					 */
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
		return result; // 不能包在try 最後一定要回傳

	}

	// 2. 
	@Override
	public boolean update(byte[] password, String email, java.util.Date birth, String custid) {
//	public boolean update(CustomerBean customerbean){

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
			

//			pstmt.setBytes(1,customerbean.getPassword());
//			pstmt.setString(2,customerbean.getEmail());
//			pstmt.setString(3,customerbean.getBirth().toString());
//			pstmt.setString(4, customerbean.getCustid());
			
			// ResultSet rs = pstmt.executeUpdate(); 不需要rs接			
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
