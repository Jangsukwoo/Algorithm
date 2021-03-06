package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;
/*
5
1 3 5
1 5
2 1 4
1 3
1 2


 */
public class 팀배분 {
	static int n;
	static ArrayList<Integer>[] blackList;
	static TreeSet<Integer> teamA,teamB;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		divideTeam();
		setOutputData();
		bw.flush();
		bw.close();
	}
	private static void setOutputData() throws IOException {
		bw.write(teamA.size()+"\n");
		for (Integer num : teamA) bw.write(num+" ");
		bw.write("\n");
		bw.write(teamB.size()+"\n");
		for (Integer num : teamB) bw.write(num+" ");
	}
	private static void divideTeam() {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[] visit = new boolean[n+1];
		
		for(int num=1;num<=n;num++) {
			if(visit[num]==false){
				boolean A = false;
				for(int i=0;i<blackList[num].size();i++){//싫어하는 사람들 전부 B팀에 넣는다.
					if(teamA.contains(blackList[num].get(i))) {
						A = true;
						break;
					}
				}
				if(A){//싫어하는 사람이 A에 있는경우 b에 넣는다.	
					q.add(new int[] {num,1});//0은 A팀 1은 B팀 
					visit[num] = true;
					teamB.add(num);
				}
				else {			
					q.add(new int[] {num,0});//0은 A팀 1은 B팀 
					visit[num] = true;
					teamA.add(num);
				}
				while(!q.isEmpty()){
					int[] cur = q.poll();
					int currentPersonNumber = cur[0];
					int currentTeamNumber = cur[1];
					for(int i=0;i<blackList[currentPersonNumber].size();i++){
						int hateNumber = blackList[currentPersonNumber].get(i);
						if(visit[hateNumber]==false){//방문 안했으면 
							visit[hateNumber] = true;
							if(currentTeamNumber==0) {
								teamB.add(hateNumber);
								q.add(new int[] {hateNumber,1});
							}else {
								teamA.add(hateNumber);
								q.add(new int[] {hateNumber,0});
							}
						}
					}
				}
			}
		}
		
	}
	private static void setData() throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		blackList = new ArrayList[n+1];
		teamA = new TreeSet<Integer>();
		teamB = new TreeSet<Integer>();
		for(int i=1;i<=n;i++) blackList[i] = new ArrayList<Integer>();
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int j=0;j<size;j++){
				int personNumber = Integer.parseInt(st.nextToken());
				blackList[i].add(personNumber);
			}
		}
	}
}
