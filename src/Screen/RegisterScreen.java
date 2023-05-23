package Screen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterScreen extends JFrame{
	
	private JPanel mainPanel = new JPanel();							//바탕 패널
	private JPanel topPanel = new JPanel();								//제목 패널
		private JLabel topLabel = new JLabel("회원가입");					//제목 요소
	private JPanel registerPanel = new JPanel();						//회원가입 패널
		private JLabel idLabel = new JLabel("ID ");						//회원가입 요소 - 아이디 라벨
		private JTextField idText = new JTextField();					//회원가입 요소 - 아이디 치는 곳
		private JLabel pwLabel = new JLabel("PASSWORD ");				//회원가입 요소 - 패스워드 라벨
		private JPasswordField pwText = new JPasswordField();			//회원가입 요소 - 패스워드 치는 곳
		private JLabel pwcheckLabel = new JLabel("PASSWORD CHECK ");	//회원가입 요소 - 패스워드 체크 라벨
		private JPasswordField pwcheckText = new JPasswordField();		//회원가입 요소 - 패스워드 체크 치는 곳
		private JLabel nameLabel = new JLabel("NAME ");					//회원가입 요소 - 이름 라벨
		private JTextField nameText = new JTextField();					//회원가입 요소 - 이름 치는 곳
		private JLabel emailLabel = new JLabel("EMAIL ");				//회원가입 요소 - 이메일 라벨
		private JTextField emailText = new JTextField();				//회원가입 요소 - 이메일 치는 곳
	private JPanel buttonPanel = new JPanel();							//버튼 패널
		private JButton rgstButton = new JButton("회원가입");				//버튼 요소 - 회원가입 버튼
		private JButton backButton = new JButton("뒤로가기");				//버튼 요소 - 뒤로 버튼
		
			
	public RegisterScreen()
	{	
		//바탕 패널 설정
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		setContentPane(mainPanel);
		
		//제목 요소
		topPanel.add(topLabel);
		
		//회원가입 - 각 요소 사이즈/위치 설정
		idLabel.setSize(100, 30);
		idText.setSize(100, 30);
		idLabel.setLocation(150, 0);
		idText.setLocation(300, 0);
		
		pwLabel.setSize(100, 30);
		pwText.setSize(100, 30);
		pwLabel.setLocation(150, 40);
		pwText.setLocation(300, 40);
		
		pwcheckLabel.setSize(150, 30);
		pwcheckText.setSize(100, 30);
		pwcheckLabel.setLocation(150, 80);
		pwcheckText.setLocation(300, 80);
		
		nameLabel.setSize(150, 30);
		nameText.setSize(100, 30);
		nameLabel.setLocation(150, 120);
		nameText.setLocation(300, 120);
		
		emailLabel.setSize(150, 30);
		emailText.setSize(200, 30);
		emailLabel.setLocation(150, 160);
		emailText.setLocation(300, 160);
		
		rgstButton.setSize(10, 20);
		backButton.setSize(10, 20);
		
		//회원가입 요소 삽입
		registerPanel.setLayout(null);
		registerPanel.add(idLabel);
		registerPanel.add(idText);
		registerPanel.add(pwLabel);
		registerPanel.add(pwText);
		registerPanel.add(pwcheckLabel);
		registerPanel.add(pwcheckText);
		registerPanel.add(emailLabel);
		registerPanel.add(emailText);
		registerPanel.add(nameLabel);
		registerPanel.add(nameText);
		
		buttonPanel.add(rgstButton);
		buttonPanel.add(backButton);
			
		//바탕 패널에 추가하기
		mainPanel.add(topPanel);
		mainPanel.add(registerPanel);
		mainPanel.add(buttonPanel);
		
		//각 패널 사이즈 설정
		topPanel.setPreferredSize(new Dimension(500, 200));
		registerPanel.setPreferredSize(new Dimension(500, 300));
		buttonPanel.setPreferredSize(new Dimension(500, 100));
		
		//바탕 패널, 전체 화면 관련 설정
		setResizable(false);
		setVisible(true);
		setSize(600,600);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//회원가입 버튼 누르면 실행되는 ActionListener
		rgstButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					JButton jbutton = (JButton)e.getSource();
					
					String id = idText.getText();
					String pw = new String(pwText.getPassword());
					String pwcheck = new String(pwcheckText.getPassword());
					String name = nameText.getText();
					String email = emailText.getText();
					
					System.out.println(email);
					
					//회원가입 요소 체크
					if(!pw.equals(pwcheck)) {
						JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 다릅니다!", "회원가입 오류", JOptionPane.ERROR_MESSAGE);
					} //비밀번호와 비밀번호 확인이 다름
					else if(id.isEmpty() == true || pw.isEmpty() == true || pwcheck.isEmpty() == true || name.isEmpty() == true || email.isEmpty() == true) {
						JOptionPane.showMessageDialog(null, "모든 항목을 채워야 합니다!", "회원가입 오류", JOptionPane.ERROR_MESSAGE);
					} //각 항목이 모두 채워지지 않음
					/* else if() {
						JOptionPane.showMessageDialog(null, "이메일의 형식이 올바르지 않습니다!", "회원가입 오류", JOptionPane.ERROR_MESSAGE);
					} //이메일에 골뱅이 확인, 정규식 사용? 너무 어려우면 빼도 될듯.....  */
					else if(id.length() <= 4 || id.length() >= 20) {
						JOptionPane.showMessageDialog(null, "아이디는 5자~19자 사이로 설정되어야 합니다", "회원가입 오류", JOptionPane.ERROR_MESSAGE);
					} //아이디가 5글자 미만, 19글자 초과임
					else if(pw.length() <= 4 || pw.length() >= 20) {
						JOptionPane.showMessageDialog(null, "비밀번호는 5자~19자 사이로 설정되어야 합니다!", "회원가입 오류", JOptionPane.ERROR_MESSAGE);
					} //비밀번호가 5글자 미만, 19글자 초과임
					else {
						String insertValue = "INSERT INTO login(mb_id, mb_pw, mb_name, mb_email) " + " VALUES('" + id + "', '" + pw + "', '" + name + "', '" + email + "')";
						
						//로그인 데이터베이스에 연결
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LOGIN_DATABASE?serverTimezone=UTC", "root", "1234");
						Statement stmt = conn.createStatement();
						stmt.executeUpdate(insertValue);
						
						JOptionPane.showMessageDialog(null, "회원가입 완료", "회원가입 완료", JOptionPane.INFORMATION_MESSAGE);
					} //회원가입 완료
					
					
				} catch(SQLException ex) {
					System.out.println("SQL 오류\n" + ex);
				}
			}
		});
		
		//뒤로가기 버튼 누르면 실행되는 ActionListener
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginScreen();
				setVisible(false);
			}
		});
		
	}
}