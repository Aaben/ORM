package model;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

	ProductBean select(int i);

	List<ProductBean> select();

	int insert(ProductBean probean);
	

	int delete(int id);

	int update(ProductBean probean);

	void getConnection() throws SQLException;

	

}