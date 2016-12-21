package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductBean;

public class ProductDAOjdbcfirst {
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=java";
	private static final String Username = "sa";
	private static final String Password = "passw0rd";

	
	public static void main(String[] args) {
		ProductDAOjdbcfirst dao = new ProductDAOjdbcfirst();
		ProductBean select = dao.select(1);
		System.out.println("select =" + select);
		
		List<ProductBean> array =dao.select();
		System.out.println(array);
		
		
		ProductBean probean = new ProductBean();
		probean.setId(13);
		probean.setName("GGININ");
		probean.setPrice(200.00);
		probean.setMake(new java.sql.Date(0));
		probean.setExpire(78);
		int insert = dao.insert(probean);
        System.out.println("insert " + insert + " rows");
		
		int delete = dao.delete(13);
		System.out.println("delete "+delete);

		probean.setId(11);
		probean.setName("GIINDER");
		probean.setPrice(5660.00);
		probean.setMake(new java.sql.Date(50000));
		probean.setExpire(7788);
		int update = dao.update(probean);
		System.out.println("update "+update);
		
		
	}
	
/*
 *------------------------------------------------------
 */                                                 // oracle 用*會錯
	private static final String SELECT_BY_ID = " select * from Product where id = ?";
	
	public ProductBean select(int id) {
		ProductBean result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, Username, Password);
			pstmt = conn.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new ProductBean();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setPrice(rs.getDouble("price"));
				result.setMake(rs.getDate("make"));
				result.setExpire(rs.getInt("expire"));

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
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}

		return result;
	}
/*
 * ----------------------------------------------------------------
 */
	private static final String SELECT_ALL = "select * from product";

	public List<ProductBean> select() {

		List<ProductBean> emps = new ArrayList<ProductBean>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn = DriverManager.getConnection(URL, Username, Password);
			pstmt = conn.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
			ProductBean emp = new ProductBean(); //要放裡面 才會繼續讀下列
			emp.setId(rs.getInt("id"));
			emp.setName(rs.getString("name"));
			emp.setPrice(rs.getDouble("price"));
			emp.setMake(rs.getDate("make"));
			emp.setExpire(rs.getInt("expire"));
			emps.add(emp);	
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
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
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}

		return emps;

	}
/*
 * ------------------------------------------------------
 */
	private static final String INSERT="Insert into Product values(?,?,?,?,?)";
	public int insert(ProductBean probean){
		int updateCount = 0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(URL,Username,Password);
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, probean.getId());
			pstmt.setString(2, probean.getName());
			pstmt.setDouble(3, probean.getPrice());
			pstmt.setDate(4, new java.sql.Date(0));
			pstmt.setInt(5,probean.getExpire());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
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
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
		
		return updateCount;
		
	}
/*
 *--------------------------------------------------------- 
 */

	private static final String DELETE="delete Product where id=? ";
	
	public int delete(int id){
		int deleteCount = 0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(URL,Username,Password);
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			deleteCount=pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
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
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
		
	
		return deleteCount;
	}
/*
 * -------------------------------------------------------
 */
	private static final String UPDATE="update Product set name=?,price=?,make=?,expire=? where id=?";
	public int update(ProductBean probean){
		int updateCount = 0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(URL,Username,Password);
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setInt(5, probean.getId());
			pstmt.setString(1, probean.getName());
			pstmt.setDouble(2, probean.getPrice());
			pstmt.setDate(3, new java.sql.Date(0));
			pstmt.setInt(4,probean.getExpire());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
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
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
		
		return updateCount;
		
	}
}
