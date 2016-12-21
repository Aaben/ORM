package model;

import java.util.Arrays;

import model.dao.CustomerDAOJdbc;

public class CustomerService {

	private CustomerDAO customerDao= new CustomerDAOJdbc();
	
	public CustomerBean login(String username,String password){
		CustomerBean bean =  customerDao.select(username); //直接乎叫資料庫
		if(bean!=null){                                    //撈帳號資料
			if(password !=null && password.length()!=0){//還可再限制長度
				byte [] pass = password.getBytes();//使用者輸入帳號密碼
				byte [] temp = bean.getPassword(); 
				if (Arrays.equals(pass, temp)){
					return bean;
				}
			}
			
		}
		return null;
	}
	
	public boolean changePassword(String username,String oldPassword,String newPassword){
		CustomerBean bean =this.login(username, oldPassword);
		if(bean!= null ){
			if (newPassword!=null && newPassword.length()!=0){
				byte [] pass= newPassword.getBytes();
				System.out.println("newpassword= "+Arrays.toString(pass));
				                              // 注意資料型別 
				return customerDao.update(pass, bean.getEmail(), bean.getBirth(), username);
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		CustomerService  customerService = new CustomerService ();
		CustomerBean login=customerService.login("Alex", "T");
		System.out.println("login=" + login);	
		
		boolean changepassword=customerService.changePassword("Alex","T","G");
		System.out.println("changepassword=" + changepassword);
		
		
       
		
		
		

	}

}
