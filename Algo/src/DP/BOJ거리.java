package DP;
//백준
import java.util.Arrays;
import java.util.Scanner;

public class BOJ거리 {
	//BOJ순으로 밟아야한다.
	//증가하는 순으로 밟아야한다.
	//백준 시간초과 나는 코드 풀이 ↓
	//같은 정답을 여러번 구하지않는 다이나믹 프로그래밍 접근이 필요
	static int n;
	static String s;
	static int[] d;
	static char next_char(char cur) {
		if(cur=='B') return 'O';
		if(cur=='O') return 'J';
		if(cur=='J') return 'B';
		return ' ';
	}
	public static int go(int index) {	//start's index
		// index -> n-1 (minimum energy)
		if(index ==n-1) {
			return 0;
		}
		if(d[index]!=-1) {
			return d[index];
		}
		// index ->? 어디로가야 최소가 될지 모른다.
		int ans=-1;
		for(int j=index+1;j<n;j++){
			//어디를 갈 수있을까?
			if(s.charAt(j) == next_char(s.charAt(index))) {
				//index -> j->..->n-1
				//-------- -------
				//(j-index)^2 go(j)
				int temp =go(j);
				if(temp!=-1) {
					int val = (j-index)*(j-index) + temp;
					if(ans==-1 || ans>val) {
						ans =val;
					}	
				}
			}
		}
		d[index] = ans;
		return ans;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		s = sc.next();
		d = new int[n];
		Arrays.fill(d, -1);
		System.out.println(go(0));
	}
}