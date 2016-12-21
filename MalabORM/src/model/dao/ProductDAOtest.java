package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.ProductBean;
import model.ProductDAO;

@SuppressWarnings("unused")
public class ProductDAOtest {

	public static void main(String[] args) {
		ProductDAO dao = new ProductDAOjdbc();
		try {
			dao.getConnection();//要用的話interface要定義方法
			                    //例外也要注意
			//find by primaryKey
//			ProductBean emp=dao.select(1);
//			System.out.println(emp);
//			System.out.println(emp.getMake());
			
			//get all 
//			List<ProductBean> emp2 =dao.select();
//			System.out.println(emp2);
			
			//insert
//			ProductBean emp3 = new ProductBean();
//			emp3.setId(11);
//			emp3.setName("GGININ");
//			emp3.setPrice(200.00);
//			emp3.setMake(new java.sql.Date(12));
//			emp3.setExpire(78);
//			System.out.println("insert " +dao.insert(emp3)+ " rows");
			
			//update 
//			ProductBean emp4 = new ProductBean();
//			emp4.setId(11);
//			emp4.setName("GIINDER");
//			emp4.setPrice(5660.00);
//			emp4.setMake(new java.sql.Date(50000));
//			emp4.setExpire(7788);
//			System.out.println("update "+dao.update(emp4));
            
            //delete
			int emp5=dao.delete(11);
			System.out.print("delete "+emp5);
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
        
	}

}
