package Practice_241025;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerEx {
	public static void main(String[] args) {
		// 문자를 읽을 때 사용 문자 기반의 입력 도구
		BufferedReader in = null;
		// 문자 기반의 출력 도구
		BufferedWriter out = null;
		// 통신하기 위한 도구
		ServerSocket listener = null;
		Socket socket = null;
		// 키보드로 입력 된 바이트 코드를 읽어서 사용자가 읽기 편한 형태로 제공하는 기능.
		Scanner scanner = new Scanner(System.in); // 키보드에서 읽을 scanner 객체 생성
		// 파일 입출력, 네트워크 통신을 할때, try 구문으로 감싸야함.
		// 서버 입장에서, 포트 번호 : 9999 라는 번호로 문을 열어둠.
		try {
			listener = new ServerSocket(8520); // 서버 소켓 생성
			System.out.println("연결을 기다리고 있습니다.....");
			
			//상대방으로 부터 소켓 연결이 온다면 accept 함수가 동작하고, 서로 간에 소켓으로 연결됨.
			socket = listener.accept(); // 클라이언트로부터 연결 요청 대기
			System.out.println("연결되었습니다.");
			
			// socket.getInputStream()란 상대방으로 부터 전달 받은 데이터를 읽는 도구 -> 메모리 임시 저장
			// new InputStreamReader -> 바이트로 읽은 코드를 앍어서 문자 기반으로 읽기
			// BufferedReader -> 보조 스트름등을 이용해서, 문자 기반으로 읽기
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 내가 상대방에게 전달할 데이터를 갖고 있다.
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while (true) {
				// 한 문장씩 읽기, in 이란 상대방으로부터의 메세지를 읽어 놓은 상태.
				String inputMessage = in.readLine(); // 클라이언트로부터 한 행 읽기
				// inputMessage = bye면 종료,
				if (inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트에서 bye로 연결을 종료하였음");
					break; // "bye"를 받으면 연결 종료
				}
				// 상대방으로부터 받은 메세지를 콘솔에 출력
				System.out.println("클라이언트: " + inputMessage);
				System.out.print("보내기>>"); // 프롬프트
				// 서버가 상대방에게 전달할 메세지를 전달
				String outputMessage = scanner.nextLine(); // 키보드에서 한 행 읽기
				out.write(outputMessage + "\n"); // 키보드에서 읽은 문자열 전송
				out.flush(); // out의 스트림 버퍼에 있는 모든 문자열 전송
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				scanner.close(); // scanner 닫기
				socket.close(); // 통신용 소켓 닫기
				listener.close(); // 서버 소켓 닫기
			} catch (IOException e) {
				System.out.println("클라이언트와 채팅 중 오류가 발생했습니다.");
			}
		}
	}
}