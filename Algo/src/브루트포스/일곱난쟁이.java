package 브루트포스;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 난쟁이 9명
 * 합이 100이되는 난쟁이 경우 찾기
 * 가능한 정답이 여러 경우에는 아무거나 출력
 * 9C7 찾으면 된다.
 */
public class 일곱난쟁이 {
	static int[] dwarf = new int[9];
	static int[] dwarfcase = new int[7];
	static boolean flag;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<9;i++) dwarf[i] = sc.nextInt();
		dfs(0,0,0);
	}
	private static void dfs(int idx, int cnt ,int sum) {
		if(flag) return; //백트래킹
		if(cnt==7){
			if(sum == 100) {
				flag = true;
				Arrays.sort(dwarfcase);
				for(int i=0;i<7;i++) System.out.println(dwarfcase[i]);
			}
			return;
		}
		for(int i=idx;i<9;i++){
			dwarfcase[cnt] = dwarf[i];
			dfs(i+1,cnt+1,sum+dwarf[i]);
		}
	}
}
