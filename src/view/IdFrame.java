package view;

import Screen.LoginScreen;
import net.WriteClass;

public class IdFrame {
	
	MainFrame mf;
	WriteClass wc;
	
	public IdFrame(WriteClass wc, MainFrame mf) {
		this.wc = wc;
		this.mf = mf;
		
		mf.id = LoginScreen.global_name;
		
		wc.sendMessage(false, false);
		
		mf.isFirst = false;
		mf.setVisible(true);
	}

}