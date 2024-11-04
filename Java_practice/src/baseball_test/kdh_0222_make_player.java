package baseball_test;

import java.util.Random;

public class kdh_0222_make_player {
	public static void main(String[] args) {
		// 투수 객체를 생성함.
		kdh_0222_player pitcher1 = new kdh_0222_pitcher("김광현", "SSG 랜더스", 81);
		kdh_0222_player pitcher2 = new kdh_0222_pitcher("양현종", "KIA 타이거즈", 103);
		kdh_0222_player pitcher3 = new kdh_0222_pitcher("류현진", "한화 이글스", 170);
		kdh_0222_player pitcher4 = new kdh_0222_pitcher("박세웅", "롯데 자이언츠", 13);
		// 타자 객채를 생성함.
		kdh_0222_player hitter1 = new kdh_0222_hitter("최정", "SSG 랜더스", 106);
		kdh_0222_player hitter2 = new kdh_0222_hitter("김도영", "KIA 타이거즈", 1);
		kdh_0222_player hitter3 = new kdh_0222_hitter("채은성", "한화 이글스", 18);
		kdh_0222_player hitter4 = new kdh_0222_hitter("황성빈", "롯데 자이언츠", 1);

		kdh_0222_player[] players = { hitter1, hitter2, hitter3, hitter4, pitcher1, pitcher2, pitcher3, pitcher4 };

		for (kdh_0222_player player : players) {
			player.introduce();
			player.attack();
			player.defense();
		}
		Random rand = new Random();
		kdh_0222_player randomPlayer = players[rand.nextInt(players.length)];
		System.out.println("\n 추천 선수 : ");
		randomPlayer.introduce();
		
	}
	

	
	
}
