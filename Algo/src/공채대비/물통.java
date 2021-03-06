package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 18:43~
 * F(A) A에 물을 가득 채움
 * E(A) A의 물을 모두 버림
 * M(A,B) 
 * A의 물을 B에 부음. 
 * 만약
 * A에 남아있는 물의 양이 B에 남아있는 빈공간보다
 * 적거나 같으면 A를 B에 다 부음
 * 만약  
 * A에 남아있는 물의 양이 B에 남아있는 공간보다 많으면
 * B를 꽉채우고 A에 남김 
 * 
 * 19:52
 */
public class 물통 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int waterA,waterB;
	static int A,B;
	static HashSet<String> set = new HashSet<String>();
	static int answer=Integer.MAX_VALUE;
	static Queue<int[]> q = new LinkedList<int[]>();
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		if(answer==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(answer);
	}
	private static void bfs(){
		int work=0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] current = q.poll();
				int a = current[0];
				int b = current[1];
				if(a==A && b==B) {
					answer = work;
					return;
				}
				String str = "";
				str = Integer.toString(a)+" "+Integer.toString(waterB);
				if(!set.contains(str)) insertQueue(a,waterB,str);
				
				str = Integer.toString(waterA)+" "+Integer.toString(b);
				if(!set.contains(str)) insertQueue(waterA, b,str);

				/*
				 * A에 남아있는 물의 양이 B에 남아있는 빈공간보다
				 * 적거나 같으면 A를 B에 다 부음
				 * 만약  
				 * A에 남아있는 물의 양이 B에 남아있는 공간보다 많으면
				 * B를 꽉채우고 A에 남김 
				 */
				if(waterB>=a+b){
					str = Integer.toString(0)+" "+Integer.toString(b+a);
					if(!set.contains(str)) insertQueue(0, b+a,str);
				}else{
					str = Integer.toString(a-(waterB-b))+" "+Integer.toString(waterB);
					if(!set.contains(str)) insertQueue(a-(waterB-b), waterB,str);
				}
				if(waterA>=b+a){
					str = Integer.toString(b+a)+" "+Integer.toString(0);
					if(!set.contains(str)) insertQueue(b+a, 0,str);
				}else {
					str = Integer.toString(waterA)+" "+Integer.toString(b-(waterA-a));
					if(!set.contains(str)) insertQueue(waterA,b-(waterA-a),str);
				}
				str = Integer.toString(a)+" "+Integer.toString(0);
				if(!set.contains(str)) insertQueue(a,0,str);
				str = Integer.toString(0)+" "+Integer.toString(b);
				if(!set.contains(str)) insertQueue(0,b,str);
			}
			work++;
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		waterA = Integer.parseInt(st.nextToken());
		waterB = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		insertQueue(0,0,"0 0");
	}
	private static void insertQueue(int a, int b,String str) {
		q.add(new int[] {a,b});
		set.add(str);
	}
}
