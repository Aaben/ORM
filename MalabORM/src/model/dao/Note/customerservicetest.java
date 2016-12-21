package model.dao.Note;

import java.util.Arrays;

import model.CustomerBean;
import model.CustomerDAO;
import model.dao.CustomerDAOJdbc;

public class customerservicetest {

	private CustomerDAO customerdao = new CustomerDAOJdbc();//建構子直接乎叫
           // 因為要去資料庫撈 所以用CustomerBean接
	public CustomerBean login(String username, String password) {
		CustomerBean bean = customerdao.select(username);
		if (bean != null) {
			if (password != null && password.length() != 0) {
				byte[] pass = password.getBytes(); //利用陣列去接
				byte[] temp = bean.getPassword();  
				if (Arrays.equals(pass, temp)) 
					return bean;
			}
		}
		return null;
	}
		   // 新密碼要更新 所以用布林接
	public boolean chagepassword(String username, String oldpassword,String newpassword){
		CustomerBean bean=this.login(username, oldpassword);
		if(bean!=null){
			if (newpassword!=null && newpassword.length()!=0){
				byte [] pass = newpassword.getBytes();
				return customerdao.update(pass, bean.getEmail(), bean.getBirth(), username);
				//重點再回傳更新 
			}
		}
		return false; //if 失敗後再執行
				
			}
		
	
	

	public static void main(String[] args) {
		customerservicetest test=new customerservicetest();
		CustomerBean login=test.login("Alex","T");
		System.out.println("login=" + login);
		
		boolean result=test.chagepassword("Alex", "T", "A");
		System.out.println(result);
		

	}

}
