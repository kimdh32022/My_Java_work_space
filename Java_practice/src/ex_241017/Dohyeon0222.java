package ex_241017;

import java.lang.classfile.ClassFile.DeadLabelsOption;
import java.util.Scanner;

public class Dohyeon0222 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("아이디를 입력하세요 : ");
		String Id = scanner.next();
		System.out.println("비밀번호를 입력하세요 : ");
		String PassWord = scanner.next();
		System.out.println("이름를 입력하세요 : ");
		String Name = scanner.next();
		
		if (Id.equals("admin")) {
			if (PassWord.equals("1234")) {
				System.out.println("환영합니다. "+ Name + "님");
			} else 
				System.out.println("일치하지 않습니다. 다시 입력해주세요.");
		} else 
			System.out.println("일치하지 않습니다. 다시 입력해주세요.");

		
	

	}

}
