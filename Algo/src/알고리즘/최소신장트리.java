package 알고리즘;
//백준
import java.util.Arrays;
import java.util.Scanner;

class FromTo implements Comparable<FromTo>{
	int From;
	int To;
	int Weight;
	FromTo(int f,int t, int w){
		From = f;
		To = t;
		Weight = w;
	}
	@Override
	public int compareTo(FromTo o) {
		return Integer.compare(this.Weight,o.Weight);//오름차순
	}
}
public class 최소신장트리{
	public static int V,E;
	public static FromTo[] Node;
	public static int[] Root;
	public static boolean Success;
	public static int result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		Root = new int[V+1];
		for(int i=1;i<=V;i++) Root[i]=i;
		Node = new FromTo[E];//간선개수
		for(int i=0;i<E;i++) Node[i] = new FromTo(sc.nextInt(),sc.nextInt(),sc.nextInt());
		Arrays.sort(Node);
		for(int i=0;i<E;i++) {
			Kruscal(Node[i].From,Node[i].To);
			if(Success) result += Node[i].Weight;
		}
		System.out.println(result);
	}
	private static void Kruscal(int u, int v) {
		Success = false;
		u = find(u);
		v = find(v);
		if(u==v) return; //이미 루트가 같으면 병합할 필요가 없으므로. 사이클 이슈 방지하기위함이기도함.
		Root[u] = v;
		Success = true;
	}
	private static int find(int u) {//루트찾기
		if(u==Root[u]) return u; //루트가 같으니까
		return Root[u] = find(Root[u]);
	}
}
