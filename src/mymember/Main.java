package mymember;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Main  extends JFrame implements ActionListener,MouseListener {

	Vector v;								//Data List
	Vector cols;							//Column List
	
	
	JTable jTable;							//table 생성
	DefaultTableModel	dtm;				//기본 테이블모델(테이블레이아웃)
	JScrollPane 		pane;				//스크롤 포함
	
	JPanel				topButton;			//버튼 패널
	JPanel				botButton;			//버튼 패널
	JButton btn1, btn2, btn3, btn4, btn5, btn6;	//신규, 정보수정, 엑셀, 종료, 새로고침, 검색
	JTextField searchId;
	static Update memberClick;
	//	Vector memberClick;
	public Main () {					//메인화면 만들기
		super("회원가입 프로그램");
		init_component();
		this.setVisible(true);
		this.setSize(800,200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	private void init_component() {
		v	 = getVlist();
		cols = getCols();
				
		dtm = new DefaultTableModel(v,cols);					//기본 테이블 모델, 행과 열 항목 설정
		jTable = new JTable(dtm);								//테이블명(jTable) 을 기본테이블 형식으로 지정
		pane	= new JScrollPane(jTable);						//jTable에 스크롤 기능 추가
		this.add(pane);											//화면에 최종테이블 추가하기
		
		btn1 = new JButton("신규가입"); btn2 = new JButton("정보수정");
		btn3 = new JButton("엑셀출력"); btn4 = new JButton("종    료");
		btn5 = new JButton("새로고침"); 
		searchId = new JTextField(12); btn6 = new JButton("검    색");
		topButton = new JPanel();	botButton = new JPanel();	// top/bot Button(버튼생성공간) 패널만들기
												
		topButton.add(btn1);topButton.add(btn2);topButton.add(btn3);	// 생성한 버튼을 topButton공간에 추가하기
		topButton.add(btn4); botButton.add(searchId); botButton.add(btn6); botButton.add(btn5);
		this.add(topButton,BorderLayout.NORTH);							// 최종 패널 추가, 위치 North(border형식)
		this.add(botButton,BorderLayout.SOUTH);
		
		//버튼액션
		this.btn1.addActionListener(this);
		this.btn2.addActionListener(this);
		this.btn3.addActionListener(this);
		this.btn4.addActionListener(this);
		this.btn5.addActionListener(this);
		this.btn6.addActionListener(this);
		//JTable 이벤트 연결
		this.jTable.addMouseListener(this);
	}

	private Vector getCols() {
		Vector cols = new Vector();
		
		cols.add("ID");
		cols.add("PW");
		cols.add("이름");
		cols.add("전화번호");
		cols.add("직업");
		cols.add("성별");
		cols.add("메일");
		cols.add("소개");
		cols.add("가입일");
		return cols;
	}

	private Vector getVlist() {  //MemberOpen 클래스에서 오픈한 멤버 데이터를 v에 저장해서 반환
		MemberProcess mp = new MemberProcess();
		Vector      v  = mp.getMemberList();
		return v;
	}
	private Vector getVlistSearch(String searchIdVal) {  //위의 VList와 같지만 where문을 추가
		MemberProcess mp = new MemberProcess();
		Vector      v  = mp.getMemberListSearch(searchIdVal);
		return v;
	}

	public static void main(String[] args) {
		new Main();						//초기화면 창 띄우기

	}
	
	
	
	// 버튼클릭 이벤트 연결
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();				//btn에 클릭시 얻는 소스를 저장
		System.out.println(btn.getText() + " 버튼 클릭");	//클릭시 얻은 소스 중 txt를 출력
		switch(btn.getText()) {
		case "신규가입" : new New(); break;	//각 버튼 클릭 시 해당하는 method로 연결
		case "정보수정" : new Update(); break;
		
		case "종    료" : System.exit(-1); break;	//바로 종료
		case "새로고침" : refreshM(); break;
		case "검    색" : String searchIdVal = searchId.getText(); search(searchIdVal); break;
						  //System.out.println(searchIdVal);검색값 확인
		case "엑셀출력" : 
			Vector v = getDataList();
			try {
				writeToExcel(v);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		}
	}
	
	private void search(String searchIdVal) {
		v	 = getVlistSearch(searchIdVal);								//v 의 데이터를 검색어기준 search
		DefaultTableModel dtm = new DefaultTableModel(v,cols);  //새로 고친 데이터를 데이터모델에 반영
		jTable.setModel(dtm);									//새로 데이터 테이블을 꾸민다
	}

	//데이터리스트 출력메소드
	private Vector getDataList() {
		MemberProcess mDao = new MemberProcess();
		Vector v = mDao.getMemberList();
		System.out.println(v.toString());
		return v;
	}

	//새로고침
	private void refreshM() {
		v	 = getVlist();										//v 의 데이터를 새로고침 하고
		DefaultTableModel dtm = new DefaultTableModel(v,cols);  //새로 고친 데이터를 데이터모델에 반영
		jTable.setModel(dtm);									//새로 데이터 테이블을 꾸민다
		
	}
	//마우스 테이블 클릭 이벤트 연결
	@Override
	public void mouseClicked(MouseEvent e) {
		int r = jTable.getSelectedRow();		// 클릭한 줄 번호
		int c = jTable.getSelectedColumn();		// 클릭한 열 번호
		
		String clickId = (String) jTable.getValueAt(r, 0);
		
		//System.out.println("jTable정보전송"); 확인완료
		memberClick = new Update(clickId);
		
	}
	//Data v를 받아서 엑셀로 내보내기
	//엑셀 조작 라이브러리 필요
	private void writeToExcel(Vector v) throws IOException {
		Date today = new Date();
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyMMdd");
		String nal = sdf.format(today);
		String filePath = "C:\\Users\\PYO\\Desktop\\JAVA\\java_study\\Project01\\work\\MemberList_"
				+ nal
				+ ".xlsx.";
		
		FileOutputStream fos = new FileOutputStream(filePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet    sheet	  = workbook.createSheet("MemberSheet");
		
		XSSFRow      curRow;  
		
		int row = v.size();
		for (int i = 0; i < row; i++) {
			curRow			= sheet.createRow(i);
			Vector member   = (Vector) v.get(i);

			String userid = (String) member.get(0);
			String userpw = (String) member.get(1);
			String username = (String) member.get(2);
			String tel = (String) member.get(3);
			String job = (String) member.get(4);
			String gender = (String) member.get(5);
			String email = (String) member.get(6);
			String intro = (String) member.get(7);
			String indate = (String) member.get(8);
			curRow.createCell(0).setCellValue(userid);
			curRow.createCell(1).setCellValue(userpw);
			curRow.createCell(2).setCellValue(username);
			curRow.createCell(3).setCellValue(tel);
			curRow.createCell(4).setCellValue(job);
			curRow.createCell(5).setCellValue(gender);
			curRow.createCell(6).setCellValue(email);
			curRow.createCell(7).setCellValue(intro);
			curRow.createCell(8).setCellValue(indate);
		}
		
		workbook.write(fos);
		fos.close();
	
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}