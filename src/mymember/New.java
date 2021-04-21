package mymember;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class New extends JFrame implements ActionListener{
	
	JTextField tfId, tfName, tfIndate, tfTel1, tfTel2, tfTel3,tfEmail,mailsite;
	JPasswordField pfPwd;
	JComboBox	   cbJob,cbEmail;
	JRadioButton   rbMan, rbWoman;
	JTextArea	   taIntro;
	JButton		   ok, cancel;
	JPanel 		pTel, pEmail;
	GridBagLayout gb;
	GridBagConstraints gbc;
	Vector updateMember;
	
	public New() {
		init_component();
	}
	
	private void init_component() {	//새 창 화면
		this.setTitle("New");
		this.setSize(700,450);
//		pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		gb = new GridBagLayout();		//layout 설정
		this.setLayout(gb);
		gbc 		= new GridBagConstraints();	// 레이아웃에 들어갈 항목들을 넣을 수 있는 칸?
		gbc.fill 	= GridBagConstraints.BOTH;	//컨텐츠 배치하고 남은부분 늘려서 채우기, x,y축 둘 다 채우기
		gbc.weightx = 1.0;						//공간 채울 때 컴포넌트 간 비율
		gbc.weighty = 1.0;
		
		//ID 입력창 생성
		JLabel userid = new JLabel("아이디");
		tfId = new JTextField(12);
		gbAdd(userid,0,0,1,1); gbAdd(tfId, 1, 0, 2, 1);
		//PW 입력창 생성
		JLabel userpw = new JLabel("비밀번호");
		pfPwd = new JPasswordField(12);
		gbAdd(userpw,0,1,1,1);	gbAdd(pfPwd,1,1,2,1);
		//이름 입력창 생성
		JLabel username = new JLabel("이름");
		tfName = new JTextField(20);
		gbAdd(username,0,2,1,1); gbAdd(tfName,1,2,2,1);
		//전화번호 입력창 생성
		JLabel usertel = new JLabel("전화번호");
		JLabel dash    = new JLabel("-");	dash.setHorizontalAlignment(JLabel.CENTER);
		JLabel dash1    = new JLabel("-");	dash1.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints gbcs 		= new GridBagConstraints();	// 복수의 text field항목의 레이아웃 지정용
		gbcs.fill 	= GridBagConstraints.BOTH;	gbcs.weightx = 1.0;	gbcs.weighty = 1.0;
		pTel = new JPanel();		// 전화번호 칸3개와 - 를 넣을 판떼기 생성
		pTel.setLayout(gb);
		tfTel1 = new JTextField(5);
		tfTel2 = new JTextField(5);
		tfTel3 = new JTextField(5);
		pTel.add(tfTel1,gbcs);pTel.add(dash,gbcs);
		pTel.add(tfTel2,gbcs);pTel.add(dash1,gbcs);pTel.add(tfTel3,gbcs);	
		gbAdd(usertel,0,3,1,1); gbAdds(pTel,1,3,2,1);
		//직업 입력창 생성
		JLabel userjob = new JLabel ("직업");
		String [] arrJob = {" ","회사원", "학생", "군인","자영업" ,"무직", "기타"};
		cbJob = new JComboBox(arrJob);	//"회사원", "학생", "군인","자영업" ,"무직", "기타"
		gbAdd(userjob, 0, 4, 1, 1); gbAdd(cbJob, 1, 4, 2, 1);
		//성별 선택창 생성
		JLabel usergender = new JLabel ("성별");
		rbMan 	= new JRadioButton("남");
		rbWoman	= new JRadioButton("여");
		ButtonGroup bg = new ButtonGroup(); bg.add(rbMan); bg.add(rbWoman);	//	버튼들끼리 그룹지정, 중복선택방지
												// 중복선택 하려면 그냥 그룹을 안하거나, 체크박스로 만드는게 더 낫다.
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEADING));
		buttons.add(rbMan,gbcs);buttons.add(rbWoman,gbcs);
		gbAdd(usergender, 0, 5, 1, 1);gbAdd(buttons, 1, 5, 1, 1);
		
		//이메일 입력창 생성
		JLabel email 	= new JLabel ("이메일");
		pEmail   = new JPanel ();		// 이메일과 사이트선택창 넣을 판뗴기 생성
		pEmail.setLayout(gb);
		tfEmail = new JTextField(20);
		JLabel mailat = new JLabel("@");  mailat.setHorizontalAlignment(JLabel.CENTER);
		mailsite = new JTextField(20);
		String [] mails = {"직접입력","네이버","다음(한메일)","구글(Gmail)","핫메일","네이트"};
		cbEmail = new JComboBox(mails);
		pEmail.add(tfEmail,gbcs);pEmail.add(mailat,gbcs);pEmail.add(mailsite,gbcs);pEmail.add(cbEmail,gbcs);
		gbAdd(email, 0, 6, 1, 1); gbAdds(pEmail, 1, 6, 2, 1);
		//자기소개 입력창 생성
		JLabel intro = new JLabel("추가 입력");
		taIntro = new JTextArea(5,20);
		JScrollPane pane = new JScrollPane(taIntro);
		gbAdd(intro, 0, 11, 1, 1); gbAdd(pane, 1, 11, 2, 1);
		//가입일 생성
		JLabel lblIndate = new JLabel("가입일");
		tfIndate 		 = new JTextField();
		gbAdd(lblIndate, 0,12,1,1); 	
		gbAdd(tfIndate,  1,12,2,1);
		
		//확인, 취소버튼
		JPanel pButton = new JPanel();
		ok	   = new JButton("확인");
		cancel = new JButton("취소");
		pButton.add(ok); pButton.add(cancel);
		gbAdd(pButton, 0, 13, 4, 1);
		
		this.ok.addActionListener(this);
		this.cancel.addActionListener(this);
		this.cbEmail.addActionListener(new ActionListener() {//email 선택 액션
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String text=(String) cb.getSelectedItem();
				switch(text) {
				case "직접입력" : mailsite.setText("");break;
				case "네이버" : mailsite.setText("naver.com"); break;
				case "다음(한메일)" : mailsite.setText("daum.net"); break;
				case "구글(Gmail)" : mailsite.setText("gmail.com"); break;
				case "핫메일" : mailsite.setText("hotmail.com"); break;
				case "네이트" : mailsite.setText("nate.com"); break;
				}
				
			}
		});
	}
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x; gbc.gridy = y;				// 추가할 컴포넌트의 배치 좌표
		gbc.gridwidth = w;	gbc.gridheight = h;		// 추가할 컴포넌트의 너비와 높이
		gb.setConstraints(c, gbc);					// 컴포넌트를 위치,크기 정보에 따라 GridBagLayout에 배치
		gbc.insets = new Insets(2, 2, 2, 2);		// Layout -> component 간의 여백주기, (top,left,bottom,right) 순서
		this.add(c,gbc);							// 최종 배치된 컴포넌트를 추가하기
		
	}
	private void gbAdds(JComponent c, int x, int y, int w, int h) {
		
		gbc.gridx = x; gbc.gridy = y;				// 추가할 컴포넌트의 배치 좌표
		gbc.gridwidth = w;	gbc.gridheight = h;		// 추가할 컴포넌트의 너비와 높이
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		this.add(c,gbc);
	}


	public static void main(String[] args) {
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		switch(btn.getText()) {
		case "확인" : 
		if(this.pfPwd.getText().equals("")) {JOptionPane.showMessageDialog(this, "비밀번호를 입력해 주세요"); break;}
		else if (this.tfId.getText().equals("")){JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요"); break;}
		else if (this.tfName.getText().equals("")){JOptionPane.showMessageDialog(this, "이름을 입력해 주세요"); break;}
		else {
		newMember(); 
		JOptionPane.showMessageDialog(this, "가입되었습니다");this.dispose();
		break;}
		case "취소" : this.dispose(); break;
		}
		
	}

	private void newMember() {
		MemberProcess mp = new MemberProcess();
		MemberVO      v  = getviewData();		//회원가입창 입력정보를 memberVO로 보내기 위함
		mp.insertMember(v);		
		
	}

	private MemberVO getviewData() {
		MemberVO vo = new MemberVO();
		String userid = this.tfId.getText();
		vo.setUserid(userid);
		String userpw = this.pfPwd.getText();
		vo.setUserpw(userpw);
		String username = this.tfName.getText();
		vo.setUsername(username);
		String tel1 = this.tfTel1.getText();
		vo.setTel1(tel1);
		String tel2 = this.tfTel2.getText();
		vo.setTel2(tel2);
		String tel3 = this.tfTel3.getText();
		vo.setTel3(tel3);
		String userjob = (String) this.cbJob.getSelectedItem();
		vo.setUserjob(userjob);
		String gender = "";
		if(rbMan.isSelected()) gender = "남";
		if(rbWoman.isSelected()) gender = "여";
		vo.setGender(gender);
		String email1 = this.tfEmail.getText();
		String email2 = this.mailsite.getText();
		vo.setEmail1(email1);vo.setEmail2(email2);
		String intro = this.taIntro.getText();
		vo.setUserintro(intro);
		String indate = this.tfIndate.getText();
		vo.setRegdate(indate);
		
		return vo;
	}

}
