package 브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 독일 로또는 1~49 중에 숫자 6개를 고름
 * 
 */
public class 로또 {
	static int N;
	static StringBuilder sb;
	static boolean[] visit;
	static int[] data;
	static int[] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while(true){
			String readLine = br.readLine();
			StringTokenizer st = new StringTokenizer(readLine);
			N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			data = new int[N];
			visit = new boolean[N];
			result = new int[6];
			for(int i=0;i<N;i++) data[i] = Integer.parseInt(st.nextToken());
			dfs(0,0);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void dfs(int idx, int cnt) {
		if(cnt==6){
			for(int i=0;i<6;i++) sb.append(result[i]+" ");
			sb.append("\n");
			return;
		}
		for(int i=idx;i<N;i++) {
			if(visit[i]==false){
				visit[i] = true;
				result[cnt] = data[i];
				dfs(i+1,cnt+1);
				visit[i] = false;
			}
		}
	}
}
