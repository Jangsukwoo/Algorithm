package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 17:10 시작
 * 17:30 끝
 */
public class 링크와스타트 {
	static int[][] abilityMap;
	static boolean[] visit;
	static int[] startTeam;
	static int[] linkTeam;
	static int answer = Integer.MAX_VALUE;
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		nCr(0,0);
		System.out.println(answer);
	}
	private static void nCr(int idx, int r) {
		if(r==(N/2)){
			int size = N/2;
			linkTeam= new int[N-size];
			setLinkTeam();
			getGapBetweenTeam();
			return;
		}
		for(int i=idx;i<N;i++) {
			startTeam[r] = i;
			nCr(i+1, r+1);
		}
	}
	private static void setLinkTeam() {
		visit = new boolean[N];
		for(int i=0;i<(N/2);i++) visit[startTeam[i]] = true;
		int idx=0;
		for(int i=0;i<N;i++){
			if(visit[i]==false) {
				linkTeam[idx++] = i;
			}
		}
	}
	private static void getGapBetweenTeam(){
		int startAbility = 0;
		int linkAbility = 0;
		for(int i=0;i<startTeam.length;i++) {
			for(int j=i+1;j<startTeam.length;j++) {
				startAbility += (abilityMap[startTeam[i]][startTeam[j]]+abilityMap[startTeam[j]][startTeam[i]]);
			}
		}
		for(int i=0;i<linkTeam.length;i++) {
			for(int j=i+1;j<linkTeam.length;j++) {
				linkAbility += (abilityMap[linkTeam[i]][linkTeam[j]]+abilityMap[linkTeam[j]][linkTeam[i]]);
			}
		}
		
		answer = Math.min(answer,Math.abs(startAbility-linkAbility));
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		abilityMap = new int[N][N];
		startTeam = new int[N/2];
		for(int row=0;row<N;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				abilityMap[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
