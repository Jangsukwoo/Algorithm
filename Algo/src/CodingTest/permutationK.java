package CodingTest;
/*
 * line
 */
import java.util.Arrays;
import java.util.Scanner;

public class permutationK {
	static int[] data;
	static int dataSize;
	static boolean[] visit;
	static int[] result;
	static int[] answer;
	static int K;
	static int num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		K = sc.nextInt();
		String[] temp = line.split(" ");
		dataSize = temp.length;
		data = new int[dataSize];
		visit = new boolean[dataSize];
		result = new int[dataSize];
		answer = new int[dataSize];
		for(int i=0; i<dataSize;i++){		
			data[i] =Integer.parseInt(temp[i]);
		}//입력 끝
		Arrays.sort(data);//데이터 정렬
		
		dfs(0);
		
		for(int i=0;i<dataSize;i++) {
			System.out.print(answer[i]);
		}
	}
	private static void dfs(int cnt) {
		if(cnt == dataSize){
			num++;
			if(num==K) {
				for(int i=0;i<dataSize;i++) {
					answer[i] = result[i];
				}
			}
		}
		for(int cur=0;cur<dataSize;cur++){
			if(visit[cur]==false) {
				visit[cur]=true;
				result[cnt]= data[cur];
				dfs(cnt+1);
				visit[cur]=false;
			}
		}
	}
}
