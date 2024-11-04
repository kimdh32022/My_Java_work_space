package Practice_241023;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class utill_test {
	// 날짜 타입으로 생성하는데, 포맷터 클래스 이용해서
	// YYYY-MM-dd, HH:mm:ss -> 문자열로 변환 해주는 기능 
	
	public static String makeDateTime() {
		// 날짜와 시간을 생성해주는 클래스 이용. 
		LocalDateTime now = LocalDateTime.now();
		// 특정 포맷, 형태로 변경
		DateTimeFormatter dateTimeFormatter 
		= DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		// LocalDateTime 타입의 현재 시간 now 
		// DateTimeFormatter 클래스 이용해서, 원하는 날짜 시간 모양으로 변경하기. 
		String resultDateTime = now.format(dateTimeFormatter);
		return resultDateTime;
		
	}
}
