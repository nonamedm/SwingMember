package mymember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class MemberProcess {
	private Connection 		  conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet 		  rs = null;
	public MemberProcess() {
		// TODO Auto-generated constructor stub
	}
	public MemberVO MemberProcess(String clickId) {
		MemberVO vo = null;
		conn = DBConn.getInstance();		
		String sql = "";
		try {
			sql = "SELECT USERID, USERPW, USERNAME"
					+ ",TEL,JOB,GENDER,EMAIL,INTRO,INDATE"
					+ " FROM USERTABLE"
					+ " WHERE USERID = ?"
					+ " ORDER BY USERID ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clickId);
			rs 	= pstmt.executeQuery();
			if( rs.next() ) {
				String userid = rs.getString("USERID");	//배열 mem에 순서대로 담기
				String userpw = rs.getString("USERPW");
				String username = rs.getString("USERNAME");	
				String tel = rs.getString("TEL");
				int idx = tel.indexOf("-");
				int idx2 = tel.indexOf("-",5);
				String tel1 = tel.substring(0,idx);
				String tel2 = tel.substring(idx+1,idx2);
				String tel3 = tel.substring(idx2+1);
				String job = rs.getString("JOB");
				String gender = rs.getString("GENDER");
				String email = rs.getString("EMAIL");
				int idxm = email.indexOf("@");
				String email1 = email.substring(0,idxm);
				String email2 = email.substring(idxm+1);
				String intro = rs.getString("INTRO");
				String indate = rs.getString("INDATE");
				vo = new MemberVO(userid, userpw,username,tel1,tel2,tel3,job,gender,email1,email2,intro,indate);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
		
		
	}

	public Vector getMemberList() {
		
		Vector v = new Vector();			// 멤버 정보를 담을 배열 v 생성
		conn = DBConn.getInstance();		// 생성해 둔 DBConn의 getInstance 호출
		String sql = "";
		try {
			sql = "SELECT USERID, USERPW, USERNAME"
					+ ",TEL,JOB,GENDER,EMAIL,INTRO,INDATE FROM USERTABLE ORDER BY USERID ASC";
			pstmt = conn.prepareStatement(sql);
			rs 	= pstmt.executeQuery();
			while(rs.next()) {
				Vector mem = new Vector();	// v에 2차원배열로 담을 수 있게 배열 mem 생성
				mem.add(rs.getString("USERID"));	//배열 mem에 순서대로 담기
				mem.add(rs.getString("USERPW"));
				mem.add(rs.getString("USERNAME"));	
				mem.add(rs.getString("TEL"));	
				mem.add(rs.getString("JOB"));
				mem.add(rs.getString("GENDER"));
				mem.add(rs.getString("EMAIL"));
				mem.add(rs.getString("INTRO"));
				mem.add(rs.getString("INDATE"));
				v.add(mem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return v;
	}
	public void insertMember(MemberVO v) {
		Connection 			conn 	= null;
		PreparedStatement	pstmt 	= null;
		conn = DBConn.getInstance();
		String sql = "";
		try {
			sql = "INSERT INTO USERTABLE (USERID, USERPW, USERNAME, TEL, JOB, GENDER,EMAIL, INTRO)\r\n" + 
					"VALUES             (?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, v.getUserid());
			pstmt.setString(2, v.getUserpw());
			pstmt.setString(3, v.getUsername());
			String tel = v.getTel1()+"-"+v.getTel2()+"-"+v.getTel3();
			//System.out.println(tel); 전화번호 입력 확인
			pstmt.setString(4, tel);
			pstmt.setString(5, v.getUserjob());
			pstmt.setString(6, v.getGender());
			String email = v.getEmail1()+"@"+v.getEmail2();
			//System.out.println(email); email 입력 확인
			pstmt.setString(7,email);
			pstmt.setString(8, v.getUserintro());
			pstmt.executeUpdate();
			System.out.println("저장되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void updateMember(MemberVO v) {
		Connection 			conn 	= null;
		PreparedStatement	pstmt 	= null;
		conn = DBConn.getInstance();
		String sql = "";
		try {
			sql = "update usertable "
				+ " set USERPW=?, USERNAME=?, TEL=?, JOB=?, GENDER=?,EMAIL=?, INTRO=?\r\n"
				+ " WHERE USERID = ?" ;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, v.getUserpw());
			pstmt.setString(2, v.getUsername());
			String tel = v.getTel1()+"-"+v.getTel2()+"-"+v.getTel3();
			System.out.println(tel);
			pstmt.setString(3, tel);
			pstmt.setString(4, v.getUserjob());
			pstmt.setString(5, v.getGender());
			String email = v.getEmail1()+"@"+v.getEmail2();
			System.out.println(email);
			pstmt.setString(6,email);
			pstmt.setString(7, v.getUserintro());
			pstmt.setString(8, v.getUserid());
			pstmt.executeUpdate();
			System.out.println("수정되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void deleteMember(MemberVO v) {
		Connection 			conn 	= null;
		PreparedStatement	pstmt 	= null;
		conn = DBConn.getInstance();
		String sql = "";
		try {
			sql = "delete usertable where userid = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, v.getUserid());
			
			pstmt.executeUpdate();
			System.out.println("삭제되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	public Vector getMemberListSearch(String searchIdVal) {
		Vector v = new Vector();			// 멤버 정보를 담을 배열 v 생성
		conn = DBConn.getInstance();		// 생성해 둔 DBConn의 getInstance 호출
		String sql = "";
		try {
			sql = "SELECT USERID, USERPW, USERNAME"
					+ ",TEL,JOB,GENDER,EMAIL,INTRO,INDATE FROM USERTABLE WHERE USERID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchIdVal);
			rs 	= pstmt.executeQuery();
			while(rs.next()) {
				Vector mem = new Vector();	// v에 2차원배열로 담을 수 있게 배열 mem 생성
				mem.add(rs.getString("USERID"));	//배열 mem에 순서대로 담기
				mem.add(rs.getString("USERPW"));
				mem.add(rs.getString("USERNAME"));	
				mem.add(rs.getString("TEL"));	
				mem.add(rs.getString("JOB"));
				mem.add(rs.getString("GENDER"));
				mem.add(rs.getString("EMAIL"));
				mem.add(rs.getString("INTRO"));
				mem.add(rs.getString("INDATE"));
				v.add(mem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	
}
