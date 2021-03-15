package CodingTest;

public class ExceptionTest {
	public static void main(String[] args) {
		try {
			System.out.println(10/0);
			System.out.println("a");
		}catch(Exception e) {
			System.out.println("b");
		}finally {
			System.out.println("c");
		}
		System.out.println("d");
	}
}
