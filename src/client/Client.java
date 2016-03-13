package client;

import main.Const;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * ���ᯥ稢��� ࠡ��� �ணࠬ�� � ०��� ������
 * 
 * @author ����
 */
// deal with exit
public class Client {
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;

	/**
	 * ����訢��� � ���짮��⥫� ��� � �࣠�����뢠�� ����� ᮮ�饭�ﬨ �
	 * �ࢥ஬
	 */
	public Client() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Write IP.");
		System.out.println("In a form: xxx.xxx.xxx.xxx");

		String ip = scan.nextLine();

		try {
			// ������砥��� � �ࢥ�� � ����砥� ��⮪�(in � out) ��� ��।�� ᮮ�饭��
			socket = new Socket(ip, Const.Port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			createAndShow();

			System.out.println("Write name:");
			//out.println(scan.nextLine());
			String name = scan.nextLine();
			//add to  list of niks

			Resender resend = new Resender();
			resend.start();

			String str = "";
			while (!str.equals("exit")) {
				str = scan.nextLine();
				out.println(str);
			}
			//??????????????????????????????????????????????????????????????????????????????????????????^
			resend.setStop();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	private static void createAndShow(){
		JFrameMy frame = new JFrameMy();
		frame.addElementsToPanel();


	}

	/**
	 * ����뢠�� �室��� � ��室��� ��⮪� � ᮪��
	 */
	private void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			System.err.println("��⮪� �� �뫨 �������!");
		}
	}

	/**
	 * ����� � �⤥�쭮� ��� ����뫠�� �� ᮮ�饭�� �� �ࢥ� � ���᮫�.
	 * ����⠥� ���� �� �㤥� �맢�� ��⮤ setStop().
	 * 
	 * @author ����
	 */
	private class Resender extends Thread {

		private boolean stoped;
		
		/**
		 * �४�頥� ����뫪� ᮮ�饭��
		 */
		public void setStop() {
			stoped = true;
		}

		/**
		 * ���뢠�� �� ᮮ�饭�� �� �ࢥ� � ���⠥� �� � ���᮫�.
		 * ��⠭���������� �맮��� ��⮤� setStop()
		 * 
		 * @see Thread#run()
		 */
		@Override
		public void run() {
			try {
				while (!stoped) {
					String str = in.readLine();
					System.out.println(str);
				}
			} catch (IOException e) {
				System.err.println("�訡�� �� ����祭�� ᮮ�饭��.");
				e.printStackTrace();
			}
		}
	}

}
