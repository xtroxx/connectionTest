package br.com.connectionTest;

import java.util.Timer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String url = args[0];
		String logFilePath = args[1];
		int timerSeconds = Integer.parseInt(args[2]);
		
		Timer timer = new Timer();
		timer.schedule(new ConnectionTesterTimer(url,logFilePath,10), 0, timerSeconds*1000);
	}

}
