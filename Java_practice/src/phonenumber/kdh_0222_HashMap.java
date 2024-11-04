	package phonenumber;

	import java.util.HashMap;
	import java.util.Map;
	import java.util.Random;
	import java.util.Scanner;

	public class kdh_0222_HashMap {
	    // 연락처를 저장할 HashMap과 Scanner 객체
	    private static HashMap<String, kdh_0222_Person> phoneBook = new HashMap<>();
	    private static Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        while (true) {
	            System.out.println("===========================================");
	            System.out.println("1. 입력, 2. 출력, 3. 검색, 4. 삭제, 5. 랜덤, 6. 종료");
	            System.out.println("===========================================");
	            int number = scanner.nextInt();
	            scanner.nextLine(); // 입력 버퍼 비우기

	            switch (number) {
	            case 1:
	                addContact();
	                break;
	            case 2:
	                printContacts();
	                break;
	            case 3:
	                searchContact();
	                break;
	            case 4:
	                deleteContact();
	                break;
	            case 5:
	                randomContact();
	                break;
	            case 6:
	                System.out.println("프로그램을 종료합니다.");
	                return; // while 문 탈출
	            default:
	                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
	            }
	        }
	    }

	    // 1. 입력: 연락처 추가
	    private static void addContact() {
	        System.out.print("이름을 입력하세요: ");
	        String name = scanner.nextLine();
	        System.out.print("연락처를 입력하세요: ");
	        String phoneNumber = scanner.nextLine();

	        // HashMap에 이름을 키로, Person 객체를 값으로 저장
	        kdh_0222_Person person = new kdh_0222_Person(name, phoneNumber);
	        phoneBook.put(name, person);
	        System.out.println("연락처가 등록되었습니다.\n");
	    }

	    // 2. 출력: 모든 연락처 출력
	    private static void printContacts() {
	        if (phoneBook.isEmpty()) {
	            System.out.println("등록된 연락처가 없습니다.");
	        } else {
	            for (Map.Entry<String, kdh_0222_Person> entry : phoneBook.entrySet()) {
	                System.out.println(entry.getValue());
	            }
	        }
	    }

	    // 3. 검색: 이름으로 연락처 검색
	    private static void searchContact() {
	        System.out.print("검색할 이름을 입력하세요: ");
	        String searchName = scanner.nextLine();

	        // HashMap에서 이름을 키로 검색
	        if (phoneBook.containsKey(searchName)) {
	            System.out.println(phoneBook.get(searchName));
	        } else {
	            System.out.println("해당 이름의 연락처를 찾을 수 없습니다.");
	        }
	    }

	    // 4. 삭제: 이름으로 연락처 삭제
	    private static void deleteContact() {
	        System.out.print("삭제할 이름을 입력하세요: ");
	        String deleteName = scanner.nextLine();

	        // HashMap에서 이름을 키로 삭제
	        if (phoneBook.remove(deleteName) != null) {
	            System.out.println("연락처가 삭제되었습니다.");
	        } else {
	            System.out.println("해당 이름의 연락처를 찾을 수 없습니다.");
	        }
	    }

	    // 5. 랜덤: 랜덤으로 연락처 출력
	    private static void randomContact() {
	        if (phoneBook.isEmpty()) {
	            System.out.println("등록된 연락처가 없습니다.");
	        } else {
	            Random random = new Random();
	            Object[] values = phoneBook.values().toArray();
	            kdh_0222_Person randomPerson = (kdh_0222_Person) values[random.nextInt(values.length)];
	            System.out.println(randomPerson);
	        }
	    }
	}
