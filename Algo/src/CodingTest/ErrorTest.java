package CodingTest;

public class ErrorTest {
	static int sum = 0;
	int data = 0;
	public static void main(String[] args) {
		ErrorTest error = new ErrorTest();
		
		int data = 0;
		int sum = 0;
		
		while(data<=10) {
			sum+=data;
			data++;
		}
		System.out.println(data+" : "+ErrorTest.sum);
	}
}
