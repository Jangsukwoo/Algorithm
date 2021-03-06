package BAEKJOON;
import java.util.LinkedList;
//간단하게 풀수있는거였다. 자료구조적인 생각을 꼭 하자.
//태우 도움으로 품

import java.util.PriorityQueue;
import java.util.Queue;
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
		return -Integer.compare(this.important,o.important);
	}
}
public class 프린터큐 {
	static int T,N,M,data,result;
	static PriorityQueue<Document> pq;
	static Queue<Document> q;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++) {
			pq = new PriorityQueue<Document>();
			q = new LinkedList<Document>();
			N = sc.nextInt();
			M = sc.nextInt(); //궁금한 문서 번호
			char target = (char) ('A'+M);
			result =0;
			for(int i=0;i<N;i++){
				data = sc.nextInt();
				pq.add(new Document((char) ('A'+i),data));
				q.add(new Document((char) ('A'+i),data));
			}
			//pq를 쓰는 목적은 가장 높은 중요도를 확인하기 위함
			
			/*
			 * 기존 문서의 순서가 유지되어야 하기 때문에 큐에 데이터를 빼주면 다시 넣어서 순환하는 형식.
			 */
			
			Document doc,doc2;
			int count=0;
			while(!q.isEmpty()) {
				doc = q.peek();
				doc2 = pq.peek();
				if(doc.important<doc2.important){//우선순위가 작은 것은 뒤로 보낸다.
					q.poll();
					q.add(doc);//뒤로넣기
				}
				else if(doc.important==doc2.important) {//가장 먼저 나와야할 데이터를 찾음
					if(doc.name==target) {//실제로 찾는 값이면
						result = count+1;
						break;	
					}else{//찾는 값이 아니면 해당사항이 아니므로 빼버린다.
						q.poll();
						pq.poll();
						count++;
					}
				}
			}
			System.out.println(result);
		}
	}
}
