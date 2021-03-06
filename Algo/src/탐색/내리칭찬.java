package 탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * 15:40~16:30
 */
public class 내리칭찬 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int n,m;//직원수, 칭찬회수 
	static StringTokenizer st;
	static ArrayList<Integer>[] adjList;
	static int[] complimentPoint;
	static HashMap<Integer, Integer> complimentTable; //칭찬 정보
	public static void main(String[] args) throws IOException {
		setData();
		dfsAll();
		getAnswer();
	}
	private static void getAnswer() throws IOException {
		for(int id=1;id<=n;id++) bw.write(complimentPoint[id]+" ");
		bw.flush();
		bw.close();
	}
	private static void dfsAll() {
		for(Integer id : complimentTable.keySet()) {//시작점만 본다.
			int point = complimentTable.get(id);
			dfs(id,point);
		}
	}
	private static void dfs(int id, int point) {
		complimentPoint[id]+=point;
		for(int idx=0;idx<adjList[id].size();idx++) {
			int subordinate = adjList[id].get(idx);
			dfs(subordinate,point);
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[n+1];
		complimentPoint = new int[n+1];
		complimentTable = new HashMap<Integer, Integer>();
		
		st = new StringTokenizer(br.readLine());
		for(int id=1;id<=n;id++) {
			adjList[id] = new ArrayList<>();
			int superior = Integer.parseInt(st.nextToken());
			if(id==1) continue; //사장은 skip
			adjList[superior].add(id);//부하직원 추가
		}
		for(int complement=1;complement<=m;complement++){
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			if(complimentTable.containsKey(id)) {
				int getPoint = complimentTable.get(id);
				complimentTable.put(id,getPoint+point);
			}else complimentTable.put(id,point);
		}
	}
}
