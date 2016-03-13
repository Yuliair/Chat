package client;

import main.Const;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Обеспечивает работу программы в режиме клиента
 * 
 * @author Влад
 */
// deal with exit
public class Client {
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;

	/**
	 * Запрашивает у пользователя ник и организовывает обмен сообщениями с
	 * сервером
	 */
	public Client() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Write IP.");
		System.out.println("In a form: xxx.xxx.xxx.xxx");

		String ip = scan.nextLine();

		try {
			// Подключаемся в серверу и получаем потоки(in и out) для передачи сообщений
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
	 * Закрывает входной и выходной потоки и сокет
	 */
	private void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			System.err.println("Потоки не были закрыты!");
		}
	}

	/**
	 * Класс в отдельной нити пересылает все сообщения от сервера в консоль.
	 * Работает пока не будет вызван метод setStop().
	 * 
	 * @author Влад
	 */
	private class Resender extends Thread {

		private boolean stoped;
		
		/**
		 * Прекращает пересылку сообщений
		 */
		public void setStop() {
			stoped = true;
		}

		/**
		 * Считывает все сообщения от сервера и печатает их в консоль.
		 * Останавливается вызовом метода setStop()
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
				System.err.println("Ошибка при получении сообщения.");
				e.printStackTrace();
			}
		}
	}

}
