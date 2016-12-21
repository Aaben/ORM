package com.lcpan;

import java.sql.SQLException;
import java.util.*;

public interface IEmpDAO {
	public void getConnection() throws SQLException;
	public int insert(EmpVO emp) throws SQLException;
	public int update(EmpVO emp) throws SQLException;
	public int delete(int empno) throws SQLException;
	public EmpVO findByPrimaryKey(int empno) throws SQLException;
	public List<EmpVO> getAll() throws SQLException;
	public void closeConn() throws SQLException;
} // end of class IEmpDAO