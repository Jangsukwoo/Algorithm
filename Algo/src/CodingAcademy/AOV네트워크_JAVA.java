package CodingAcademy;

public class AOV네트워크_JAVA {
	static class Node{
		int vertax;
		Node link;
		public Node(int vertax, Node link) {
			this.vertax = vertax;
			this.link = link;
		}
	}
	static class Heads{
		int count;
		Node link;
		public Heads(int count, Node link) {
			this.count = count;
			this.link = link;
		}
	}
	public static void main(String[] args) {

	}
	public boolean isWorkable(Heads graph[], int n) {
		int i=0,j=0,k=0,t=-1;
		Node ptr = null;
		for(i=0;i<n;i++) {
			if(graph[i].count==0){//진입차수가 0?
				graph[i].count = t;
				t=i;
			}
		}//?

		for(i=0;i<n;i++) {
			if(t==-1) { //진입차수가 0인게 없다는건 사이클이라는 것
				return false;//
			}
			else {
				j=t; //진입차수가 0 인 정점에서 링크를 타본다.
				//t = -1
				ptr = graph[j].link; //진입차수가 0인 노드 시작
				while(ptr.link!=null) {
					//k = ptr->vertax;
					graph[k].count--;
					if(graph[k].count==0){
						graph[k].count = t;
						t = k;
					}
				}
			}
		}
		return true;
	}
}
