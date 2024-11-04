package Ex_241021;

import java.util.Scanner;

public class ContinueExample {

	public static void main(String[] args) {
		Scanner scanner= new Scanner(System.in);
		 System.out.println("정수를5개입력하세요.");
		 int sum=0;
		 for(int i=0; i<5; i++) {
			 int n=scanner.nextInt();
			 if(n<=0) 
				 continue; // 0이나음수인경우더하지않고다음반복으로진행
			 else sum += n; // 양수인경우덧셈
		}
		 	System.out.println("양수의합은" + sum);
		 	scanner.close();

	}

}
