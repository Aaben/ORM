package model.dao.Note;

import java.util.Arrays;

import model.CustomerBean;
import model.CustomerDAO;
import model.dao.CustomerDAOJdbc;

public class customerservicetest {

	private CustomerDAO customerdao = new CustomerDAOJdbc();//�غc�l�����G�s
           // �]���n�h��Ʈw�� �ҥH��CustomerBean��
	public CustomerBean login(String username, String password) {
		CustomerBean bean = customerdao.select(username);
		if (bean != null) {
			if (password != null && password.length() != 0) {
				byte[] pass = password.getBytes(); //�Q�ΰ}�C�h��
				byte[] temp = bean.getPassword();  
				if (Arrays.equals(pass, temp)) 
					return bean;
			}
		}
		return null;
	}
		   // �s�K�X�n��s �ҥH�Υ��L��
	public boolean chagepassword(String username, String oldpassword,String newpassword){
		CustomerBean bean=this.login(username, oldpassword);
		if(bean!=null){
			if (newpassword!=null && newpassword.length()!=0){
				byte [] pass = newpassword.getBytes();
				return customerdao.update(pass, bean.getEmail(), bean.getBirth(), username);
				//���I�A�^�ǧ�s 
			}
		}
		return false; //if ���ѫ�A����
				
			}
		
	
	

	public static void main(String[] args) {
		customerservicetest test=new customerservicetest();
		CustomerBean login=test.login("Alex","T");
		System.out.println("login=" + login);
		
		boolean result=test.chagepassword("Alex", "T", "A");
		System.out.println(result);
		

	}

}
