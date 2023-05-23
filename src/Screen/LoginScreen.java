package Screen;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;



public class LoginScreen extends JFrame{
	
	private JPanel mainPanel = new JPanel();						//바탕 패널
	private JPanel topPanel = new JPanel();							//제목 패널
		private JLabel topLabel = new JLabel("스터디 매칭 시스템");		//제목 요소
	private JPanel loginPanel = new JPanel();						//로그인 패널
		private JLabel idLabel = new JLabel("ID ");					//로그인 요소 - 아이디 라벨
		private JTextField idText = new JTextField();				//로그인 요소 - 아이디 치는 곳
		private JLabel pwLabel = new JLabel("PASSWORD ");			//로그인 요소 - 패스워드 라벨
		private JPasswordField pwText = new JPasswordField();		//로그인 요소 - 패스워드 치는 곳
	private JPanel buttonPanel = new JPanel();						//버튼 패널
		private JButton lgnButton = new JButton("로그인");			//버튼 요소 - 로그인 버튼
		private JButton rgstButton = new JButton("회원가입");			//버튼 요소 - 회원가입 버튼
		
	public static String global_name;										//전역변수에 이름 저장
	
			
	public LoginScreen()
	{	
		//바탕 패널 설정
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		setContentPane(mainPanel);
		
		//제목 요소
		topPanel.add(topLabel);
		
		//로그인 - 각 요소 사이즈/위치 설정
		idLabel.setSize(100, 30);
		pwLabel.setSize(100, 30);
		idLabel.setLocation(150, 0);
		pwLabel.setLocation(150, 40);
		idText.setSize(100, 30);
		pwText.setSize(100, 30);
		idText.setLocation(300, 0);
		pwText.setLocation(300, 40);
		
		//로그인 요소 삽입
		loginPanel.setLayout(null);
		loginPanel.add(idLabel);
		loginPanel.add(idText);
		loginPanel.add(pwLabel);
		loginPanel.add(pwText);
		
		//로그인 버튼 요소
		buttonPanel.add(lgnButton);
		lgnButton.setSize(10, 20);
		buttonPanel.add(rgstButton);
		lgnButton.setSize(10, 20);
		
		//바탕 패널에 추가하기
		mainPanel.add(topPanel);
		mainPanel.add(loginPanel);
		mainPanel.add(buttonPanel);
		
		//각 패널 사이즈 설정
		topPanel.setPreferredSize(new Dimension(500, 200));
		loginPanel.setPreferredSize(new Dimension(500, 300));
		buttonPanel.setPreferredSize(new Dimension(500, 100));
		

		
		//바탕 패널, 전체 화면 관련 설정
		setResizable(false);
		setVisible(true);
		setSize(600,600);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//로그인 버튼 누르면 실행되는 ActionListener
		lgnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					//로그인 데이터베이스에 연결
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LOGIN_DATABASE?serverTimezone=UTC", "root", "1234");
					Statement stmt = conn.createStatement();
					
					//Jbutton 정보 가져오기
					JButton jbutton = (JButton)e.getSource();
					
					//id와 pw 가져오기
					String id = idText.getText();
					String pw = new String(pwText.getPassword());
					
					//데이터베이스에서 아이디와 비밀번호 확인
					String checkStr = "SELECT mb_pw, mb_name FROM login WHERE mb_id = '" + id + "'";
					
					ResultSet result = stmt.executeQuery(checkStr);
					result.next();
					
					
					//로그인 처리하는 try catch 문
					try {
						if (pw.equals(result.getString(1))) {
							if(id.equals("admin")){		//어드민 아이디로 로그인하면 어드민 화면으로 이동
								new AdminScreen();
								setVisible(false);
							}
							else{						//일반 유저는 메인화면으로 이동
								global_name = result.getString(2);
								new MainScreen();
								setVisible(false);
							}
						} else
							JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 다시 확인해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					} catch(SQLException ex) {
						JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 다시 확인해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException ex) {
					System.out.println("SQL 오류\n" + ex);
				}
				
			}
		});
		
		//회원가입 창으로 이동하는 ActionListener
		rgstButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterScreen();
				setVisible(false);
			}
		});
	}

}
