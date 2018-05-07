package br.com.connectionTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConnectionTester {

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getLogFilePath() {
		return logFilePath;
	}


	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}


	public int getConnectionTimeout() {
		return connectionTimeout;
	}


	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}


	private String url;
	private String logFilePath;
	private int connectionTimeout;
	private final String USER_AGENT = "Mozilla/5.0";
	
	
	public ConnectionTester(String url, String logFilePath, int connectionTimeout) {
		this.url = url;
		this.logFilePath = logFilePath;
		this.connectionTimeout = connectionTimeout;
	}
	
	public void testConnection() throws Exception{
		
		URL obj = new URL(this.url);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setConnectTimeout(connectionTimeout*1000);

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		//System.out.println(response.toString());
	}
	
	public void putLog(String logMessage) {

		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		String logText = dateFormat.format(date)+";"+logMessage+";\n";
		
		System.out.println(logText);
		
		try(FileWriter fw = new FileWriter(this.logFilePath, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(logText);

			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
				e.printStackTrace();
			}
	}
	
	
}
