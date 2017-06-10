package model;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ServerThread implements Runnable{
	ServerSocket server;
	Socket client;
	Model m;
	private final int PORT = 5000;

	public ServerThread(Socket c, Model m){
		this.client = c;
		this.m = m;
	}
	public void run(){
		System.out.println("Thread rodando");
		try{
			server = new ServerSocket(PORT);
		}
		catch(IOException e){
			System.out.println(e);
		}
		try{
			// System.out.println("AQUI");
			client = server.accept();
		}
		catch(IOException e){
			System.out.println(e);
		}
		if(client != null){
			m.connected();
		}
		// while(true){
		// 	System.out.println("THREAD");
		// }
	}
}