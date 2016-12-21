package model;

import java.util.Arrays;

public class CustomerBean {
	
	@Override
	public String toString() {
		return "CustomerBean [ custid=" + custid + ", password=" + Arrays.toString(password) + ", email=" + email
				+ ", birth=" + birth +" ]";}

	private String custid ;   // varchar(20) primary key,
	private byte[] password;  // varbinary(50),
	private String email ;    // varchar(30),
	private java.util.Date birth; //datetime ���Τl���Osql.Date,sql.Timestamp
	                              //OO���u���]�w���� > �����O > �l���O        

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.util.Date getBirth() {
		return birth;
	}

	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}



}
