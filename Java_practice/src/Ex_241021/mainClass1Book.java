package Ex_241021;

public class mainClass1Book {

	// 1. 기본값의 태압을 메서드 인자로 전달 할 경우
	// 결과 > 기본값 타입은 복사를 해서 전달하므로 , 복사본이다. 원본과 별개다
	
	// 2. 참조형 타비을 메서드 인자로 전달 할 경우
	// 결과 > 참조형 탑입을 복사를 해서 전달하지만, 원본이다. 0x100 복사를 해도 그대로임.
	static void increase(int radius) {
		radius++;
		System.out.println("1. 기본형 탑으로 받을 경우의 값 확인 : ");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
