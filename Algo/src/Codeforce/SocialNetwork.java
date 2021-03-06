package Codeforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * 데크의 contains와 set의 contains의 차이가 엄청 컸다...
 * set으로 TLE 해결한 문제..
 */

public class SocialNetwork {
	static int messages;
	static int conversations;
	static int[] ids;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//쪼갠 값이 존재할때까지 반복
		messages = Integer.parseInt(st.nextToken());
		conversations = Integer.parseInt(st.nextToken());
		ids = new int[messages];
		st = new StringTokenizer(br.readLine(), " ");
		HashSet<Integer> set = new HashSet<Integer>();
		Deque<Integer> dq = new ArrayDeque<Integer>();
		for(int id=0;id<messages;id++) {
			ids[id] = Integer.parseInt(st.nextToken());
			if(dq.size()!=conversations){//데크에 여유가있고
				if(set.contains(ids[id])) continue; //지금 값이 존재하면
				else {
					dq.addFirst(ids[id]); //존재하지않으면 입구로 삽임
					set.add(ids[id]);
				}
			}
			else{//데크에 여유가 없으면
				if(set.contains(ids[id])) continue; //지금 값이 존재하면
				else {//존재하지않으면 입구로 삽임
					set.remove(dq.removeLast());//출구로 값 하나 빼고
					dq.addFirst(ids[id]);//지금값 입구로 삽입
					set.add(ids[id]);
				}
			}
		}
		int dqSize = dq.size();
		System.out.println(dqSize);
		for(int i=0; i<dqSize;i++) {
			System.out.print(dq.removeFirst()+" ");
		}
	}
}
