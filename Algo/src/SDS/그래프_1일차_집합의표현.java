package SDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * Union-Find (Disjoint Set)
 * -> 그래프에서 응용된다.
 * Union - 두 집합을 하나로 합친다.
 * Find - 어떤 원소 a에 대해서 a가 속한 집합을 반환
 * 
 * Step 1.
 * 배열 i에 대해서 
 * 초기화를 해준다.
 * 1 - 1
 * 2 - 2
 * 3 - 3
 * 4 - 4
 * 5 - 5
 * 
 * Step 2. 합친다.
 * 
 * union(1,3)
 * 1 - 1
 * 3 - 1
 * 
 * Step 3. 찾는다.
 * 
 * find(3) = 1
 * 
 * 
 */
public class 그래프_1일차_집합의표현 {
	static int N,M;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		N = Integer.parseInt(st.nextToken()); //1<=N<=1000000
		M = Integer.parseInt(st.nextToken()); //1<=M<=100000
		parent = new int[N+1];
		setParent();
		//N이 백만개니까 어떤 알고리즘이 좋을까?

		//0 a b 를 합집합으로 만든다.
		//1 a b 가 같은 집합에 있는지?
		//1로 시작하는 입력에 대해서 YES/NO를 출력한다.
		for(int i=0;i<M;i++) {
			line = br.readLine();
			st = new StringTokenizer(line);
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch (command) {
			case 0:	
				union(a,b); //합친다.
				break;
			case 1:
				int aParent = find(a);
				int bParent = find(b);
				if(aParent==bParent) bw.write("YES"+"\n");
				else bw.write("NO"+"\n");
				break;
			}
		}
		bw.flush();
		bw.close();
	}
	private static void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		parent[aParent] = bParent;
		
//		if(aParent<bParent) parent[bParent] = aParent;
//		else if(aParent>bParent) parent[aParent] = bParent; <-요건 왜??
		//-> 원소 숫자가 더 큰쪽으로 합친다. -> 약간의 시간적 이득을 볼 수 있따. 더 자식이 많은쪽은 가만히 있어야하는것 
	}
	private static int find(int a){
		if(parent[a]==a) return a;//같아질때까지, 즉 자신의 부모를 찾아간다.
		else return parent[a] = find(parent[a]); //부모를 갱신해놓아버린다. find가 끝나고 되돌아오면서 만나며 왔던 루트를 한번에 쳐다보도록. 
		//만약 return find(parent[a])라고만 해버리면 부모를 그냥 찾아간다.
	}
	private static void setParent() {
		for(int i=1;i<=N;i++) parent[i] = i;
	}
}
