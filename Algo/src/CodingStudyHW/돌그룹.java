package CodingStudyHW;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 11:54 시작
 * 돌 그룹 A,B,C가 있음
 * 크기가 같지 않은 두 그룹 고르고
 * 작은쪽을 X, 큰쪽을 Y라 함
 * X엔 X+X
 * Y엔 Y-X
 * 
 * 돌을 같은 개수로 만들 수 있으면 1 아니면 0 
 * 13:00 끝
 * 
 * 처음에 boolean[1001][1001][1001]로 했는데 메모리 초과가 났다.
 * 이건 메모리 문제이기도하면서 
 * 연산중간에 각 자리의 값이 1000보다 넘는 값이 들어가기 때문에
 * 사실 이렇게풀면 안된다.
 * 그래서 어떤 방식으로 방문체크를 할까 하다가
 * hashset을 이용해서
 * A,B,C를 스트링으로 더한 문자열을 만든 후
 * 방문 해보지 못한 문자열에 대해서만 방문해서 연산을 해보는 방식으로 했다.
 * 또한 연산 결과중에 
 * Y-X를 하고 X+X를 해도 어차피 같은 결과를 가지게되면
 * 이러한 케이스는 더이상 봐줄 필요가 없으니 건너뛰도록했다.
 * hashSet , bfs로 최종적으로 문제를 해결 할 수 있었다.
 */
public class 돌그룹 {
	static int A,B,C;
	static int result;
	static boolean find;
	static HashSet<String> visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		bfs();
		if(find) System.out.println("1");
		else System.out.println("0");
	}
	private static void bfs() {
		int[] data = new int[]{A,B,C};
		Queue<int[]> q = new LinkedList<int[]>();
		visit = new HashSet<String>();
		q.add(data);
		visit.add(Integer.toString(A)+Integer.toString(B)+Integer.toString(C));
		while(!q.isEmpty()){
			int[] currentABC = q.poll();
			int a = currentABC[0];
			int b = currentABC[1];
			int c = currentABC[2];
			String stringData = Integer.toString(a)+Integer.toString(b)+Integer.toString(c);
			//System.out.println(a+" "+b+" "+c);
			if(a==b && a==c) {
				find = true;
				return;
			}
			if(a!=b){
				if(a>b){
					if(a!=(b+b)) {
						a-=b;
						b+=b;
						stringData = Integer.toString(a)+Integer.toString(b)+Integer.toString(c);
						if(!visit.contains(stringData)) {
							q.add(new int[] {a,b,c});
							visit.add(stringData);
						}
					}
				}
				else {
					if(b!=(a+a)) {
						b-=a;
						a+=a;
						stringData = Integer.toString(a)+Integer.toString(b)+Integer.toString(c);
						if(!visit.contains(stringData)) {
							q.add(new int[] {a,b,c});
							visit.add(stringData);
						}
					}
				}
			}
			if(b!=c){
				if(b>c){
					if(b!=(c+c)) {
						b-=c;
						c+=c;
						stringData = Integer.toString(a)+Integer.toString(b)+Integer.toString(c);
						if(!visit.contains(stringData)) {
							q.add(new int[] {a,b,c});
							visit.add(stringData);
						}
					}
				}
				else {
					if(c!=(b+b)) {
						c-=b;
						b+=b;
						stringData = Integer.toString(a)+Integer.toString(b)+Integer.toString(c);
						if(!visit.contains(stringData)) {
							q.add(new int[] {a,b,c});
							visit.add(stringData);
						}
					}
				}
			}
			if(a!=c){
				if(a>c){
					if(a!=(c+c)) {
						a-=c;
						c+=c;
						stringData = Integer.toString(a)+Integer.toString(b)+Integer.toString(c);
						if(!visit.contains(stringData)) {
							q.add(new int[] {a,b,c});
							visit.add(stringData);
						}
					}
				}
				else {
					if(c!=(a+a)) {
						c-=a;
						a+=a;
						stringData = Integer.toString(a)+Integer.toString(b)+Integer.toString(c);
						if(!visit.contains(stringData)) {
							q.add(new int[] {a,b,c});
							visit.add(stringData);
						}
					}
				}
			}
		}
	}
}
