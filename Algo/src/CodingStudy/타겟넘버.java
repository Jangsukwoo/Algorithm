package CodingStudy;
/*
 * N개의 음이 아닌 정수
 * 숫자 Set이 주어지면
 * 그 숫자 Set에 대해서 타겟 넘버를 만드는 가능한 모든 방법?
 * 
 * 숫자 개수는 2에서 20개 사이
 * + 뒤에 -,+ 가능 
 * 2의 승수만큼의 경우의 수의 가지가 생긴다.
 */
public class 타겟넘버 {
	static int finalDepth;
	static int targetNumber;
	static int answer;
	static int[] numberData;
	public static void main(String[] args) {
		solution(new int[] {1,1,1,1,1},3);
	}
    public static int solution(int[] numbers, int target) {
        finalDepth = numbers.length;
        targetNumber = target;
        numberData = numbers.clone();
        dfs(0,0);//depth,sum
        return answer;
    }
	private static void dfs(int depth, int sum) {
		if(depth==finalDepth) { //끝까지 다 봤고
			if(sum==targetNumber) answer++; //타겟 넘버와 같으면 카운팅
			return;
		}
		dfs(depth+1,(sum+numberData[depth]));//더해보고
		dfs(depth+1,(sum-numberData[depth]));//빼보고
	}
}
