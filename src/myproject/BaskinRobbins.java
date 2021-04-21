package myproject;


//추가할 것들
//인트로 음악 및 인게임 음악, 끝날떄 음악 -> java FX

//웹등록 및 어플로 제작


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BaskinRobbins extends JFrame {
	JTextField players = new JTextField();
	int ROWS;
	JButton next = new JButton();
	JButton plus = new JButton();
	JLabel lbl = new JLabel();
	JLabel lbl1 = new JLabel();
	JLabel lbl2 = new JLabel();
	ImageIcon logo = new ImageIcon();
	ImageIcon numberImage = new ImageIcon();
	
	
	int gamenumber;
	int playernum;
	int nextnumber;
	int nextavailable;
	public BaskinRobbins () {

		super("31");
		init_Component();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		this.setVisible(true);
	}
	private void init_Component() {
		this.setLayout(new BorderLayout());
		next = new JButton("다음차례");
		plus = new JButton("1추가");
		lbl2 = new JLabel("참여인원 : ");
		players = new JTextField("",3);
		         
		logo = new ImageIcon("C:\\Users\\PYO\\Desktop\\JAVA\\java_study\\img\\brlogo.jpg");
		lbl = new JLabel("",logo,JLabel.CENTER);
		ROWS=0;
		gamenumber = 0;
		nextnumber = 0;
		nextavailable = 1;			// 얘는 0이 활성화이므로 1부터 시작
		playernum=0;
		Panel northP = new Panel();
		Panel southP = new Panel();
		this.add(southP,BorderLayout.SOUTH);
		this.add(northP,BorderLayout.NORTH);
		this.add(lbl, BorderLayout.CENTER);
		southP.add(plus,BorderLayout.WEST);
		southP.add(next,BorderLayout.EAST);
		northP.add(lbl2,BorderLayout.NORTH);
		northP.add(players,BorderLayout.NORTH);
		
		players.addActionListener(new ActionListener() {//인원설정 액션
			@Override
			public void actionPerformed(ActionEvent e) {
				ROWS = Integer.parseInt(players.getText().replaceAll("[^\\d]", ""));
				JButton [] btn = new JButton[ROWS];
				for (int i = 0; i < btn.length; i++) {
					btn[i] = new JButton("플레이어"+(i+1));
					btn[i].setBackground(new Color(255,255,255));	// 기본버튼색 흰색 설정
					northP.add(btn[i],BorderLayout.SOUTH);
					pack();
				}
				players.setEnabled(false);					//인원 설정 후 참여인원버튼 비활성화 (무한인원추가 방지)
				System.out.println(players.getText());
				btn[0].setBackground(new Color(192,192,192));	//자기차례 버튼 색상 설정 -> 제일 첫순서 회색설정
					
				
				next.addActionListener(new ActionListener() {	//	턴넘기기 버튼 액션 넣기
						@Override
						public void actionPerformed(ActionEvent e) {
							nextnumber=0;					//3회이상 클릭 후 버튼비활성화 해제하기
							
							if(gamenumber<31 && nextavailable==0) {				// 게임 진행중일때 다음차례 넘기기버튼액션
							if(playernum==ROWS-1) {			//턴표시 플레이어수 최종으로 왔을 때 다시 1로 돌아가기
								btn[playernum].setBackground(new Color(255,255,255));
								playernum=0; btn[0].setBackground(new Color(192,192,192));	//playernum 재설정해서 1번으로 턴넘기기
								nextavailable=1;				//넥스트 버튼 한번 클릭 후 비활성화 시키기
							}
							else {
							btn[playernum].setBackground(new Color(255,255,255));
							playernum++;
							btn[playernum].setBackground(new Color(192,192,192));
							System.out.println(gamenumber);
							nextavailable=1;				//넥스트 버튼 한번 클릭 후 비활성화 시키기 (양쪽 다 넣기)
							}
						}else if(gamenumber==31){								// 게임 끝났을 때 처음으로 돌아가기 버튼액션
							dispose();						// 기존게임 끝내고
							new BaskinRobbins();	}		// 새 게임 불러오기
						else {}								// 자기턴에 버튼추가 안했으면 넥스트버튼 비활성화
							}
					});
				
				
			}
		});
		
		
		plus.addActionListener(new ActionListener() {//번호추가 액션
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(ROWS==0) {}else {
				if(gamenumber<31 && nextnumber<3) {gamenumber++;nextnumber++;
				nextavailable=0;												// next버튼 활성화
				numberImage = new ImageIcon("C:\\Users\\PYO\\Desktop\\JAVA\\java_study\\img\\"+gamenumber+".jpg");
				
				lbl1 = new JLabel("",numberImage,JLabel.CENTER);
				lbl.setIcon(numberImage);
				if(gamenumber==31) {
					plus.setText("게임종료");
					next.setText("다시하기");
				}
				}
				else{
					if(gamenumber==31) {
					System.exit(-1);}else{}}		//else는 숫자세번 누르고나면 아무것도 안하게 만들기
			}}
		});
	}
	
	
	public static void main(String[] args) {
		new BaskinRobbins();
		
		
	}

}
