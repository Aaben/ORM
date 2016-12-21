package model;

import java.util.Arrays;

import model.dao.CustomerDAOJdbc;

public class CustomerService {

	private CustomerDAO customerDao= new CustomerDAOJdbc();
	
	public CustomerBean login(String username,String password){
		CustomerBean bean =  customerDao.select(username); //�����G�s��Ʈw
		if(bean!=null){                                    //���b�����
			if(password !=null && password.length()!=0){//�٥i�A�������
				byte [] pass = password.getBytes();//�ϥΪ̿�J�b���K�X
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
				                              // �`�N��ƫ��O 
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
