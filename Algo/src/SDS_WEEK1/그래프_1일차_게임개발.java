package SDS_WEEK1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 2 -1
 */
public class 그래프_1일차_게임개발 {
	static int N;
	static int[] buildingIndegree;
	static LinkedList<Integer>[] edgeList;
	static int[] answerTime;
	static int[] time;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		input();
		topological();
		printAnswer();
	}

	private static void printAnswer() throws IOException {
		for(int i=1;i<=N;i++) bw.write(answerTime[i]+"\n");
		
		bw.flush();
		bw.close();
	}

	private static void topological() {
		Queue<Integer> q = new LinkedList<>();
		for(int i=1;i<=N;i++) {
			if(buildingIndegree[i]==0) {
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			int zeroIndegreeBuilding = q.poll();
			for(int i=0;i<edgeList[zeroIndegreeBuilding].size();i++){
				buildingIndegree[edgeList[zeroIndegreeBuilding].get(i)]--;
				answerTime[edgeList[zeroIndegreeBuilding].get(i)]=Math.max(answerTime[zeroIndegreeBuilding]+time[edgeList[zeroIndegreeBuilding].get(i)],answerTime[edgeList[zeroIndegreeBuilding].get(i)]);
				//멀리돌아가는경우??
				if(buildingIndegree[edgeList[zeroIndegreeBuilding].get(i)]==0) {
					q.add(edgeList[zeroIndegreeBuilding].get(i));
	
				}
			}
		}
	}

	private static void input() throws IOException {
		String line = br.readLine();
		N = Integer.parseInt(line);
		buildingIndegree = new int[N+1];
		edgeList = new LinkedList[N+1];
		answerTime = new int[N+1];
		time = new int[N+1];
		for(int i=1;i<=N;i++) edgeList[i] = new LinkedList<>();
		for(int i=1;i<=N;i++) {
			line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			answerTime[i] = Integer.parseInt(st.nextToken());
			time[i] = answerTime[i];
			int from = Integer.parseInt(st.nextToken());
			while(from!=-1) {
				int to = i;
				edgeList[from].add(to);
				buildingIndegree[to]++;
				from = Integer.parseInt(st.nextToken());
			}
		}
		
	}
}
