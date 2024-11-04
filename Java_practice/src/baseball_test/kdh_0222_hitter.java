package baseball_test;


public class kdh_0222_hitter extends kdh_0222_player {

	public kdh_0222_hitter(String name,String team,  int salary) {
		super(name,"타자",team,salary);
	}
	@Override
	public void attack() {
		System.out.println(name + "선수가 홈런을 칩니다! ");
	}
	public void defense() {
		System.out.println(name + "선수가 파울볼을 잡습니다.");
	}
}
