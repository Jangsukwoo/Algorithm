package BAEKJOON;
/*
1
6 0
1 1 9 1 1 1
 */
import java.util.PriorityQueue;
import java.util.Scanner;
class Document implements Comparable<Document>{
	char name;
	int important;
	public Document(char n, int i) {
		name = n;
		important = i;
	}
	@Override
	public int compareTo(Document o) {
//		if(this.important == o.important) return -Integer.compare(this.name,o.name);
//		else return Integer.compare(this.important,o.important);
		return -Integer.compare(this.important,o.important);
	}
}
public class 프린터큐 {
	static int T,N,M,data,result;
	static PriorityQueue<Document> pq;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++) {
			pq = new PriorityQueue<Document>();
			N = sc.nextInt();
			M = sc.nextInt(); //궁금한 문서 번호
			char target = (char) ('A'+M);
			result =0;
			for(int i=0;i<N;i++){
				data = sc.nextInt();
				pq.add(new Document((char) ('A'+i),data));
			}
//			for(int i=0;i<N;i++) {
//				System.out.println("나온데이터"+pq.poll().name+" "+(i+1)+"번째");
//			}
			
			for(int i=0;i<N;i++) {
				if(target==pq.poll().name) {
					result = i+1;
					break;
				}
			}
			System.out.println(result);
		}
	}
}
