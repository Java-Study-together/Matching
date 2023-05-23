package Screen;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminScreen extends JFrame{
	
	private JPanel mainPanel = new JPanel();						//바탕 패널
	private JPanel topPanel = new JPanel();							//제목 패널
		private JLabel topLabel = new JLabel("어드민 화면");				//제목 요소
	private JPanel buttonPanel = new JPanel();						//버튼 패널
		private JButton logoutButton = new JButton("로그아웃");			//버튼 요소 - 로그아웃 버튼
	
			
	public AdminScreen()
	{	
		//바탕 패널 설정
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		setContentPane(mainPanel);
		
		//제목 요소
		topPanel.add(topLabel);
		
		buttonPanel.add(logoutButton);

		//바탕 패널에 추가하기
		mainPanel.add(topPanel);
		mainPanel.add(buttonPanel);
		
		//각 패널 사이즈 설정
		topPanel.setPreferredSize(new Dimension(500, 200));
		buttonPanel.setPreferredSize(new Dimension(500, 100));
		
		//바탕 패널, 전체 화면 관련 설정
		setResizable(false);
		setVisible(true);
		setSize(600,600);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginScreen();
				setVisible(false);
			}
		});
		
	}
}