package ex_241017;

import java.util.Scanner;

public class Hello {

	public static void main(String[] args) {
		System.out.println("===================회원가입 폼 만들기===================");
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID를 입력해주세요 : ");
		String Id = scanner.next();
		System.out.println("이름을 입력해주세요 : ");
		String Name = scanner.next();
		System.out.println("E-mail주소를 입력해주세요 : ");
		String Email = scanner.next();
		System.out.println("PassWord를 입력해주세요 : ");
		String Password = scanner.next();
		System.out.println("PassWord를 다시 입력해주세요 : ");
		String RePassword = scanner.next();
		System.out.println("주소를 입력해주세요 : ");
		String Adress = scanner.next();
		System.out.println("전화번호를 입력해주세요 : ");
		String Tel = scanner.next();
		System.out.println("좋아하는 메뉴를 입력해주세요 : ");
		String Food = scanner.next();
		
		System.out.println("ID:"+ Id);
		System.out.println("Name:"+ Name);
		System.out.println("E-mail:"+ Email);
		System.out.println("PassWord:"+ Password);
		System.out.println("PassWord 재확인:"+ RePassword);
		System.out.println("주소:"+ Adress);
		System.out.println("전화번호:"+ Tel);
		System.out.println("좋아하는 메뉴 :"+ Food);
		
		System.out.println("===================================================");
		
		
	}

}
