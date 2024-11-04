package Practice_241023;

public class person_test {
	private String name;
	private String contact;
	private String regDate;

	// 나중에는 , 멤버 속성만 추가하고,
	// 애너테이션 이용해서, 게터/세터, 생성자, toString
	// 지금은 수동으로 만들기.

	// 생성자, 매개변수 3개
	public person_test(String name, String contact, String regDate) {
		super();
		this.name = name;
		this.contact = contact;
		this.regDate = regDate;
	}

	// get/set,
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	// toString
	@Override
	public String toString() {
		return "PersonLsy1205Test [name=" + name + ", contact=" + contact + ", regDate=" + regDate + "]";
	}

}
