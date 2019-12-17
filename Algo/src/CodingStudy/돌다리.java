package CodingStudy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 스카이 콩콩 힘 A,B (2<=A,B<=30)
 * 동규와 주미의 위치 N,M (0<=N,M<=100000)
 * 점프는 좌,우
 * 슈퍼점프는 현위치의 A 또는 B배
 * 총 8가지의 방법
 * 1. -1,1
 * 2. -A,+A
 * 3. -B,+B
 * 4. A배,B배
 *
 *최소의 이동 횟수니까
 *가능한 방법은 다 시도하면서
 *가장 먼저 주미를 잡기까지의 이동회수를 구한다 -> BFS
 */
public class 돌다리 {
	static int A,B;//스카이 콩콩의 힘 A,B
	static int N,M;//동규의 위치, 주미의 위치
	static int move;
	static Queue<Integer> q = new LinkedList<Integer>();
	static boolean[] stoneVisit = new boolean[100001];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		N = sc.nextInt();
		M = sc.nextInt();
		q.add(N);
		stoneVisit[N] = true;
		BFS();
		System.out.println(move);
	}
	private static void BFS() {
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int currentStone = q.poll();//현재 서있는 위치
				if(currentStone==M) return;//주미랑 같은 위치면 끝냄
				//8가지 방법 해보기
				int next = currentStone+1;
				if(isPossible(next)) insertQueue(next);
				next = currentStone-1;
				if(isPossible(next)) insertQueue(next);
				next = currentStone-A;
				if(isPossible(next)) insertQueue(next);
				next = currentStone+A;
				if(isPossible(next)) insertQueue(next);
				next = currentStone-B;
				if(isPossible(next)) insertQueue(next);
				next = currentStone+B;
				if(isPossible(next)) insertQueue(next);
				next = currentStone*A;
				if(isPossible(next)) insertQueue(next);
				next = currentStone*B;
				if(isPossible(next)) insertQueue(next);
			}
			move++;//이동
		}
	}
	private static void insertQueue(int next) {
		q.add(next);
		stoneVisit[next] = true;
	}
	private static boolean isPossible(int next) {
		if(next>=0 && next<=100000 && stoneVisit[next]==false) return true;//영역 만족하고 방문 안했으면
		return false;
	}
}