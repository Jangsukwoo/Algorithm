package CodingAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제
 * 숫자가 섞인 보드판을 받은 후
 * 재배열을 통해 원하는 보드판을 만들기
 * 
 * Goal
 * 123
 * 456
 * 78#
 * 
 * 원하는 보드판을 만들 수 없다면 Impossible 출력
 * 
 * bfs와 dfs처럼 a*도 탐색 알고리즘임
 * 
 * f = g+h
 * g = 현재 depth
 * h = heuristic value (Manhattan distance 또는 같지 않은 칸의 개수 등)

1

#13
847
562

1

618
547
23#

1

123
4#5
786

1

#34
285
716
 */
public class EightPuzzle_Astar_Algorithm {
	static int T;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static char[][] board;
	static char[][] goal;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static HashMap<Integer,Integer> visitMap = new HashMap<>();
	static HashSet<Integer> impossibleSet =  new HashSet<Integer>();
	static PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
		@Override
		public int compare(Node o1, Node o2){
			if(o1.f==o2.f) return Integer.compare(o1.g,o2.g); //f = g+h라면 좀 더 이동회수가 적었던 노드가 더 우선순위를 가짐.
			return Integer.compare(o1.f,o2.f); //f = g+h에서 f기준으로 정렬 
		}
	});
	static class Node{
		String board;
		int g;
		int f;
		public Node(String data,int g,int f) {
			this.board = data;
			this.g=g;
			this.f=f;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			br.readLine();//빈라인 read처리
			setData(); //Input
			astarAlgorithm();
			if(visitMap.containsKey(Integer.parseInt("123456789"))){
				bw.write((int) visitMap.get(Integer.parseInt("123456789"))+"\n");
			}else {
				for(Integer key : visitMap.keySet()) impossibleSet.add(key);
				bw.write("impossible"+"\n");
			}
		}
		bw.flush();
		bw.close();
	}
	private static void astarAlgorithm(){
		while(!pq.isEmpty()) {
			Node currentNode = pq.poll();
			String numberBoard = currentNode.board;
			int sharpIndex = numberBoard.indexOf("9");
			int cr = sharpIndex/3;
			int cc = sharpIndex%3;
			if(impossibleSet.contains(Integer.parseInt(numberBoard))) return;
			
			String data = "";
			for(int dir=0;dir<4;dir++){//move UP,RIGHT,LEFT,DOWN
				int nr = cr+dr[dir];
				int nc = cc+dc[dir];
				if(rangeCheck(nr,nc)){//영역을 만족 하는 경우
					StringBuilder next = new StringBuilder(numberBoard);
					next = swap(cr,cc,nr,nc,next); //swap
					data = next.toString();
					//heuristic logic
					int heuristic = getHeuristicValue(data);
					if(visitMap.containsKey(Integer.parseInt(data))) continue; //이미 본 케이스면 건너뛴다.
					else {
						//f = g+h
						pq.add(new Node(data,currentNode.g+1,(currentNode.g+heuristic)));
						visitMap.put(Integer.parseInt(data),currentNode.g+1);
					}
				}
			}
			//조사 후 
			if(visitMap.containsKey(Integer.parseInt("123456789"))) {
				return; //찾았으면 끝냄 
			}
		}
	}
	private static int getHeuristicValue(String data){
		int count = 0;
		for(int i=0;i<data.length();i++) {
			if("123456789".charAt(i)!=data.charAt(i)) count++;
		}
		return count;
	}
	private static StringBuilder swap(int cr, int cc, int nr, int nc, StringBuilder next) {
		int currentRC = cr*3+cc;
		int nextRC = nr*3+nc;
		char temp = next.charAt(currentRC);
		next.setCharAt(currentRC,next.charAt(nextRC));
		next.setCharAt(nextRC,temp);
		return next;
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<3 && nc>=0 && nc<3) return true;
		return false;
	}
	private static void setData() throws IOException {
		board = new char[3][3];
		visitMap.clear();
		pq.clear();
		String boardStr="";
		for(int row=0;row<3;row++) {
			board[row] = br.readLine().toCharArray();
			for(int col=0;col<3;col++) {
				if(board[row][col]=='#') board[row][col] = '9';
				boardStr+=board[row][col];
			}
		}
		pq.add(new Node(boardStr,0,0));
		visitMap.put(Integer.parseInt(boardStr),0);
	}
}
