package model.dao.Note;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CustomerBean;
import model.CustomerDAO;

public class CustomerDAOJdbcNote implements CustomerDAO {
	// 3.�Ѽƥ~��
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=java";
	private static final String Username = "sa";
	private static final String Password = "passw0rd";
	// 1. ���n���bSQL���ի��O �`�Ncustid �n�[�ݸ�
	private static final String SELECT_BY_CUSTUD = "SELECT * FROM customer where custid=?";
	// 1. ��ƫ��A�n�`�N''�u��varchar PK �L�k��ʩ�̫�
	private static final String UPDATE = "UPDATE customer set  password=? ,email=?,birth=? where custid =?";


	public static void main(String[] args) {
		CustomerDAO customerDao = new CustomerDAOJdbcNote();
		CustomerBean select = customerDao.select("Ellen");
		System.out.println(select);                                 // �[�@���
		customerDao.update("F".getBytes(),"iii@ntu.edu.tw", new java.sql.Date(0) , "GG");
		System.out.println(customerDao.select("GG")); 
//		CustomerBean customerbean = new CustomerBean();
//		customerbean.setPassword("K".getBytes());
//		customerbean.setEmail("iii@iii");
//		customerbean.setBirth(new java.sql.Date(0));
//		customerbean.setCustid("GG");  //�]���OPK�H����ק�ؼ�
//		customerDao.update(customerbean);
	}

	// 2. type=�^�Ǫ���(�������X�̭����O��) �ݸ�SQL��ƫ��Avarchar�ҥH()�̶�String
	@Override
	public CustomerBean select(String custid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerBean result = null;
		try {
			// 3.��ӳ��ݭngetConnection �ҥH�ѼƩԨ�~���g
			conn = DriverManager.getConnection(URL, Username, Password);
			// 3.�~�����ŧi�ݩ� �̭��N���ݭn
			pstmt = conn.prepareStatement(SELECT_BY_CUSTUD);
			// 4.
			pstmt.setString(1, custid); //select�u���@�� ?�hdb�����
			rs = pstmt.executeQuery();//����ʺA���O
			if (rs.next()) { // �]��PK�ҥH�u�����o�@����Ƥ���WHILE
				result = new CustomerBean();
				result.setCustid(rs.getString("custid"));
				result.setPassword(rs.getBytes("password"));
				result.setEmail(rs.getString("email"));
				result.setBirth(rs.getDate("birth"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {/*
					 * �Yconn ���ѱ��� �h�᭱pstmt,rs��������ҥH��null ��null
					 * �L�kclose()�ҥH�n�ư�null�I�sclose�����p
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
		return result; // ����]�btry �̫�@�w�n�^��

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
			
			// ResultSet rs = pstmt.executeUpdate(); ���ݭnrs��			
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
