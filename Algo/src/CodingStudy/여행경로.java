package CodingStudy;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * 티켓이 5장이면 방문하는 도시는 6개
 * String[][]에서
 * 첫번째 []는 티켓인덱스
 * 두번째 []의 0,1은 시작도시,도착도시 인덱스
 * 
 * 반복문으로 문자열 가공하는 처리에서 맨앞에 왜 널이 들어가는지?..
 */
public class 여행경로 {
	static int N;
	static ArrayList<String[]> pathList = new ArrayList<String[]>();
	static String[] path;
	static int pathSize;
	static int ticketSize;
	static boolean[] useTicket;
	public static void main(String[] args) {
		solution(new String[][] {{"ICN","SFO"},{"ICN","ATL"},{"SFO","ATL"},{"ATL","ICN"},{"ATL","SFO"}});
	}
    public static String[] solution(String[][] tickets) {
        String[] answer = {};
        ticketSize = tickets.length;
        pathSize = tickets.length+1;
        useTicket = new boolean[ticketSize];
        path = new String[pathSize];
        String start= "ICN";
        dfs(start,tickets,0,0);
        String[] dictionary = new String[pathList.size()];
        for(int i=0;i<pathList.size();i++){
        	String[] pathCase = pathList.get(i);
        	for(int j=0;j<pathCase.length;j++){
        		dictionary[i]+=pathCase[j]+",";
        	}
        }
        Arrays.sort(dictionary);//사전정렬
        String answerTemp = dictionary[0].substring(4);//왜 널이들어가는지..
        answer = answerTemp.split(",");   
        return answer;
    }
	private static void dfs(String start, String[][] tickets, int depth,int ticketNum) {
		if(depth==ticketSize){
			path[depth] = tickets[ticketNum][1];//마지막 티켓의 종착지 넣어줌 
			addPath();
			return;
		}
		for(int ticketNumber=0;ticketNumber<ticketSize;ticketNumber++){
			if(tickets[ticketNumber][0].equals(start)){//start도시에서 시작하는 티켓이면서
				if(useTicket[ticketNumber]==false){//사용하지않은 티켓이면
					path[depth]=tickets[ticketNumber][0];
					useTicket[ticketNumber]=true;
					dfs(tickets[ticketNumber][1],tickets,depth+1,ticketNumber);
					useTicket[ticketNumber]=false;
				}
			}
		}
	}
	private static void addPath() {
		String[] pathCase = new String[pathSize];
		for(int i=0;i<pathSize;i++) {
			pathCase[i] = path[i];//값 복사
		}
		pathList.add(pathCase);
	}
}
