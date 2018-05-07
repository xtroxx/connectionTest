package br.com.connectionTest;

import java.util.TimerTask;

public class ConnectionTesterTimer extends TimerTask {

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	String url;
	String logFile;
	int connectionTimeout;
	
	public ConnectionTesterTimer(String url,String logFile, int conTimeout) {
		this.url = url;
		this.logFile = logFile;
		this.connectionTimeout = conTimeout;
	}
	
    public void run() {
		ConnectionTester test = new ConnectionTester(this.url,this.logFile,this.connectionTimeout);
		
		try {
			test.testConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			test.putLog(e.getMessage());
		}
     }
	
}
