package mymember;

import java.util.Vector;

public class MemberVO {
	
	private String userid;
	private String userpw;
	private String username;
	private String tel1, tel2, tel3;
	private String userjob;
	private String gender;
	private String email1,email2;
	private String userintro;
	private String regdate;
	public MemberVO() {}
	public MemberVO(String userid, String userpw, String username, String tel1, String tel2, String tel3,
			String userjob, String gender, String email1, String email2, String userintro, String regdate) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
		this.userjob = userjob;
		this.gender = gender;
		this.email1 = email1;
		this.email2 = email2;
		this.userintro = userintro;
		this.regdate = regdate;
	}
	

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	public String getUserjob() {
		return userjob;
	}
	public void setUserjob(String userjob) {
		this.userjob = userjob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getUserintro() {
		return userintro;
	}
	public void setUserintro(String userintro) {
		this.userintro = userintro;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ""
				+ ", tel=" + tel1+"-"+tel2+"-"+tel3+ ",  userjob=" + userjob + ", gender=" + gender 
				+ ", email1="+ email1 + ", email2=" + email2 + ", userintro=" + userintro + ", regdate="
				+ regdate + "]";
	}
	public void add(Vector mem) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
