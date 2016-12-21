package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductBean;
import model.ProductDAO;

public class ProductDAOjdbc implements ProductDAO {
	private static final String SELECT_BY_ID = " select * from Product where id = ?";
	private static final String SELECT_ALL = "select * from product";
	private static final String UPDATE="update Product set name=?,price=?,make=?,expire=? where id=?";
	private static final String INSERT="Insert into Product values(?,?,?,?,?)";
	private static final String DELETE="delete Product where id=? ";
	Connection conn = null;
	
	public void getConnection() throws SQLException{
		String URL = "jdbc:sqlserver://localhost:1433;databaseName=java";
		conn = DriverManager.getConnection(URL,"sa","passw0rd");
	}
	
	public void closeConn() throws SQLException{
		if(conn!=null)
		conn.close();
	}
	
	public static void main(String[] args) {

	}
	
/*
 *------------------------------------------------------
 */
		
	@Override
	public ProductBean select(int id) {
		ProductBean result = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
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
	

	@Override
	public List<ProductBean> select() {
		List<ProductBean> emps = new ArrayList<ProductBean>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();			
			while(rs.next()){
			ProductBean emp = new ProductBean(); 
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
	
	@Override
	public int insert(ProductBean probean){
		int updateCount = 0;
		PreparedStatement pstmt=null;
		try {
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
	@Override
	public int delete(int id){
		int deleteCount = 0;
		PreparedStatement pstmt=null;
		try {
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
	@Override
	public int update(ProductBean probean){
		int updateCount = 0;
		PreparedStatement pstmt=null;
		try {
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
