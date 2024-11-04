package Ex_241021;

import java.util.Scanner;

public class Dohyeon0222 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("먹고 싶은 메뉴를 입력하세요.");
		String[] meun = new String[3];
		for (int i = 0; i < 3; i++) {
			String j = scanner.next();
			meun[i] = j;
		}
		System.out.println("먹고 싶은 메뉴 ");
		for (int i = 0; i < meun.length; i++) {
			System.out.println("메뉴 " + (i+1)+ ": " + meun[i]);
		}
	}

}
