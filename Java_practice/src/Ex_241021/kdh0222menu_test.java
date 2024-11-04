package Ex_241021;

import java.util.Random;

public class kdh0222menu_test {

	public static void main(String[] args) {
		String[] food1MaterialStrings = {"바지락 조개", "마늘", "파스타 면"};
		kdh0222_menu Menu = new kdh0222_menu("봉골레 파스타", 7500, food1MaterialStrings);
		
		
		
		String[] food2MaterialStrings = {"참치 캔", "밥", "깻잎", "김"};
		kdh0222_menu Menu1 = new kdh0222_menu("참치 김밥", 5500, food2MaterialStrings);
		
		String[] food3MaterialStrings = {"달걀", "식은 밥", "참기름", "대파"};
		kdh0222_menu Menu2 = new kdh0222_menu("계란볶음밥", 7500, food3MaterialStrings);
		
		kdh0222_menu [] menus = {Menu,Menu1,Menu2};
		
		randomRecommendMenu(menus);
	}
	public static void randomRecommendMenu(kdh0222_menu [] menus) {
		Random random = new Random();
		int randomNumber = random.nextInt(3); // 3 미만의 정수를 뽑기, 
		menus[randomNumber].showMenu();
	}
}
 