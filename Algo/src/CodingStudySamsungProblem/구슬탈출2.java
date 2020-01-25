package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 빨간 구슬을 구멍을 통해 빼낸다.
 * 단, 파란구슬은 구멍에 빠지면 안된다.
 * 구슬을 움직이는 방법은
 * 판을 상,하,좌,우로 기울인다
 * 
 * 성공 기준은
 * 빨간 구슬만 구멍에 빠지는 것이고
 * 파란 구슬이 빠지거나 
 * 빨강,파랑 구슬이 동시 에 빠지면 실패이다.
 * 구슬이 더이상 움직이지 않을 때 게임은 멈추고
 * 보드의 상태가 주어졌을 때 
 * 최소 몇 번 만에 빨간 구슬을 빼낼 수 있는지 구하라
 * 단, 10번의 시도로 구슬을 꺼낼 수 없으면
 * -1을 출력하고 프로그램을 끝낸다.
 * 
 * 무조건 벽에 둘러쌓여있다.
 * BFS로 풀어보기.
 * 
 * 최소몇번?
 * 
 * 키보드ㅗ장나ㅓ내이ㅟ ㅜㅜㅜㅜㅜㅜㅜㅜ
 */
public class 구슬탈출2{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static char[][] board;
	static int N,M;//세로,가로
	static int ans;
	static class BeadInfo{
		//y = 행, x = 여
		int ry; 
		int rx;
		int by;
		int bx;
		int count;
		public BeadInfo(int ry, int rx, int by, int bx, int count) {
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
			this.count = count;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //세로 
		M = Integer.parseInt(st.nextToken()); //가로
		board = new char[N][M];
		for(int row=0;row<N;row++) board[row] = br.readLine().toCharArray();
		ans = 
	}
}
