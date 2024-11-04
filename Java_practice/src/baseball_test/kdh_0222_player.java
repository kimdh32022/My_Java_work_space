package baseball_test;

public class kdh_0222_player {
		String name;
		String position;
		String team;
		int salary;
		
		public kdh_0222_player(String name, String position,String team, int salary) {
			this.name = name;
			this.position = position;
			this.team = team;
			this.salary = salary;
		}

		public void introduce() {
			System.out.println("이름 : "+ name +", 포지션 : "+ position +", 구단 : "+ team + ", 연봉 :  "+ salary + "억원");
		}
		public void attack() {
			System.out.println(name + " 선수가 공격에 나섭니다.");
		}
		public void defense() {
			System.out.println(name + " 선수가 수비로 나갑니다.");
		}
		
}
