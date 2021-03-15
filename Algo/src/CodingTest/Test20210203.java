package CodingTest;

class Person{
	int no = 10;
	String name ;
	public Person(int no) {
		this.no = no;
		System.out.println("int no 호출");
	}
	public Person(int no, String anme) {
		this(no);
		this.name = name;
		System.out.println("int no, name 호출");
	}
}
public class Test20210203 {
	public static void main(String[] args) {
		Person one = new Person(100, "김철수");
		System.out.println(one.no);
	}
}
