package Ex_241021;

import java.util.concurrent.TimeUnit;

public class kdh0222_menu {
	String name;
	int price;
	String[] material;

	public kdh0222_menu(String name, int price, String[] material) {
		super();
		this.name = name;
		this.price = price;
		this.material = material;
	}

	public void showMenu() {
		System.out.println("음식 이름은 : " + this.name);
		System.out.println("가격은 : " + this.price + "원");
		System.out.println("음식의 재료들은 : ");
		for (String source : material) {
			System.out.println(source);
		}

	}
}
