package CodingTest;

public class 플그인턴2 {
	public static void main(String[] args) {
		System.out.println(solution(2));
	}
    public static long solution(long n) {
        long answer = 0;
        answer=Long.parseLong(Long.toString(n, 2),3);
        return answer;
    }
}
