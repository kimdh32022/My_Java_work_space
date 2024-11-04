package phonenumber;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class kdh_0222_ArrayList {
	// 연락처를 저장할 ArrayList와 Scanner 객체
	private static ArrayList<kdh_0222_Person> List = new ArrayList<>();
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
				addList();
				break;
			case 2:
				printList();
				break;
			case 3:
				searchList();
				break;
			case 4:
				deleteList();
				break;
			case 5:
				randomList();
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
	private static void addList() {
		System.out.print("이름을 입력하세요: ");
		String name = scanner.nextLine();
		System.out.print("연락처를 입력하세요: ");
		String phoneNumber = scanner.nextLine();

		kdh_0222_Person person = new kdh_0222_Person(name, phoneNumber);
		List.add(person);
		System.out.println("연락처가 등록되었습니다.\n");
	}

	// 2. 출력: 모든 연락처 출력
	private static void printList() {
		if (List.isEmpty()) {
			System.out.println("등록된 연락처가 없습니다.");
		} else {
			for (kdh_0222_Person person : List) {
				System.out.println(person);
			}
		}
	}

	// 3. 검색: 이름으로 연락처 검색
	private static void searchList() {
		System.out.print("검색할 이름을 입력하세요: ");
		String searchName = scanner.nextLine();
		boolean found = false;

		for (kdh_0222_Person person : List) {
			if (person.getName().equalsIgnoreCase(searchName)) {
				System.out.println(person);
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("해당 이름의 연락처를 찾을 수 없습니다.");
		}
	}

	// 4. 삭제: 이름으로 연락처 삭제
	private static void deleteList() {
		System.out.print("삭제할 이름을 입력하세요: ");
		String deleteName = scanner.nextLine();
		boolean found = false;

		for (int i = 0; i < List.size(); i++) {
			if (List.get(i).getName().equalsIgnoreCase(deleteName)) {
				List.remove(i);
				System.out.println("연락처가 삭제되었습니다.");
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("해당 이름의 연락처를 찾을 수 없습니다.");
		}
	}

	// 5. 랜덤: 랜덤으로 연락처 출력
	private static void randomList() {
		if (List.isEmpty()) {
			System.out.println("등록된 연락처가 없습니다.");
		} else {
			Random random = new Random();
			int randomIndex = random.nextInt(List.size());
			System.out.println(List.get(randomIndex));
		}
	}
}


