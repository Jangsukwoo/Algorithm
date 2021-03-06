package codingtest20하;

import java.util.ArrayList;
import java.util.HashMap;

public class 쿠4 {
	static ArrayList<Integer>[] adjList;
	static int deparId,hubId,destId;
	static int answer;
	public static void main(String[] args) {
		solution("SEOUL", "DAEGU", "YEOSU",
				new String[][]{{"ULSAN", "BUSAN"}, 
			{"DAEJEON", "ULSAN"}, {"DAEJEON", "GWANGJU"}, 
			{"SEOUL", "DAEJEON"}, {"SEOUL", "ULSAN"}, 
			{"DAEJEON", "DAEGU"}, {"GWANGJU", "BUSAN"},
			{"DAEGU", "GWANGJU"}, {"DAEGU", "BUSAN"}, 
			{"ULSAN", "DAEGU"}, {"GWANGJU", "YEOSU"}, {"BUSAN", "YEOSU"}});
	}  
	public static int solution(String depar, String hub, String dest, String[][] roads) {
		adjList = new ArrayList[10001];
		for(int i=1;i<=10000;i++) adjList[i] = new ArrayList<Integer>();
		HashMap<String, Integer> giveIdMap = new HashMap<String, Integer>();
		int id = 1;
		for(int i=0;i<roads.length;i++) {
			String from = roads[i][0];
			String to = roads[i][1];
			if(!giveIdMap.containsKey(from)) giveIdMap.put(from,id++);
			if(!giveIdMap.containsKey(to)) giveIdMap.put(to,id++);
			int fromNum = giveIdMap.get(from);
			int toNum = giveIdMap.get(to);
			adjList[fromNum].add(toNum);
		}
		deparId = giveIdMap.get(depar);
		hubId = giveIdMap.get(hub);
		destId = giveIdMap.get(dest);
		dfs(deparId,false);
		
		return answer;
	}
	private static void dfs(int currentLocation, boolean passhub) {
		if(currentLocation==destId){
			if(passhub) {
				answer++;
				answer%=10007;
			}
			return;
		}
		for(int i=0;i<adjList[currentLocation].size();i++){
			int nextLocation = adjList[currentLocation].get(i);
			if(nextLocation==hubId) {
				dfs(nextLocation,true);
			}
			else {
				dfs(nextLocation,passhub);
			}
		}
	}
}
