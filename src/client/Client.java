package client;

import main.Const;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// deal with exit
public class Client implements JFrameAskName.NameListener {
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;
	private Scanner scan;
	private Resender resend;
	private JFrameChat frame;
	private JFrameAskName frameAskName;




	public Client() {



		scan = new Scanner(System.in);

	//	System.out.println("Write IP.");
	//	System.out.println("In a form: xxx.xxx.xxx.xxx");

	//	String ip = scan.nextLine();

		String ip = "127.0.0.1";



		try {
			socket = new Socket(ip, Const.Port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			frameAskName = new JFrameAskName();
			frameAskName.addNameListener(this);



			//String name = scan.nextLine();
			//out.println(name);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public synchronized void sendMessage(String str){
		out.println(str);
		if (str.equals("exit")) {
		resend.setStop();
		this.close();
			//close frame
		}
	}


	private synchronized void close() {
		try {
			in.close();
			out.close();
			socket.close();
			frame.setVisible(false);
			frame.dispose();
		} catch (Exception e) {
			System.err.println("Exception in close");
		}
	}

	@Override
	public void nameAppear(String string) {
		frame = new JFrameChat(this);
		resend = new Resender();
		resend.start();
		out.println(string);

	}


	private class Resender extends Thread {

		private boolean stoped;
		public void setStop() {
			stoped = true;
		}


		@Override
		public synchronized void run() {
			stoped = false;
			try {
				while (!stoped) {
					String str = in.readLine();
					System.out.println(str);
					frame.messageComes(str);

				}
			} catch (IOException e) {
				System.err.println("Exception in run");
				e.printStackTrace();
			}
		}

	}




}
