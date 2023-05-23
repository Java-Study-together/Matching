package Screen;
import javax.swing.*;

import Room.RoomFrame;
import net.ReadThread;
import view.MainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame{
	
	private JPanel mainPanel = new JPanel();						//바탕 패널
	private JPanel topPanel = new JPanel();							//제목 패널
		private JLabel topLabel = new JLabel("환영합니다 "+ LoginScreen.global_name +"님");				//제목 요소
	private JPanel buttonPanel = new JPanel();						//버튼 패널
		private JButton logoutButton = new JButton("로그아웃");		//버튼 요소 - 로그아웃 버튼
	private JPanel screenPanel = new JPanel();						//중앙 패널
		private JButton chatButton = new JButton("채팅방");			//중앙 버튼 요소 - 채팅방 버튼
		private JButton diaryButton = new JButton("기록하기");			//중앙 버튼 요소 - 기록하기 버튼
	private BufferedReader br;
	private PrintWriter pw;
			
	public MainScreen()
	{	
		//바탕 패널 설정
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		setContentPane(mainPanel);
		
		//제목 요소
		topPanel.add(topLabel);
		
		screenPanel.add(chatButton);
		screenPanel.add(diaryButton);
		
		buttonPanel.add(logoutButton);

		//바탕 패널에 추가하기
		mainPanel.add(topPanel);
		mainPanel.add(screenPanel);
		mainPanel.add(buttonPanel);
		
		//각 패널 사이즈 설정
		topPanel.setPreferredSize(new Dimension(500, 200));
		screenPanel.setPreferredSize(new Dimension(500, 300));
		buttonPanel.setPreferredSize(new Dimension(500, 100));
		
		//바탕 패널, 전체 화면 관련 설정
		setResizable(false);
		setVisible(true);
		setSize(600,600);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		chatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket socket;
				try {
					setVisible(false);
					
					socket = new Socket("192.168.220.191", 9000);
					System.out.println("접속 성공");

					List<view.RoomFrame> rfList = new ArrayList<>();
					MainFrame mf = new MainFrame(socket, rfList);
					new ReadThread(socket, mf).start();
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//로그아웃 버튼 실행
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginScreen();
				setVisible(false);
			}
		});
		
		
	}
}