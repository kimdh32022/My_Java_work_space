package baseball_test;

public class kdh_0222_pitcher extends kdh_0222_player {

	public kdh_0222_pitcher(String name, String team, int salary) {
		super(name, "투수", team, salary);
	}

	public void attack() {
		System.out.println(name + "선수가 삼진을 잡습니다.");
	}

	public void defense() {
		System.out.println(name + "선수 앞에 투수땅볼을 1루에 던집니다.");
	}

}
