package phonenumber;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class kdh_0222_Person {
    private String name;
    private String phoneNumber;
    private String registrationDate;

    // 생성자
    public kdh_0222_Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.registrationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Getter 메서드
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    // 객체 정보를 문자열로 출력
    @Override
    public String toString() {
        return "이름: " + name + ", 연락처: " + phoneNumber + ", 등록 날짜: " + registrationDate;
    }
}

