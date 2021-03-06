package CodingStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 19:15 시작
 * 비밀번호는 4자리 소수이고
 * 1000 이상 10000미만의 소수들임
 * 두 소수 사이의 변환에 필요한 최소 회수 
 * 불가능하면 possible
 * 19:40
 */
public class 소수경로 {
	static Set<Integer> primeSet = new HashSet<Integer>();
	static int T;
	static Queue<Integer> q = new LinkedList<Integer>();
	static boolean[] visit;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int transformation;
	static boolean possible;
	public static void main(String[] args) throws NumberFormatException, IOException {
		makePrimeSet();
		T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			st = new StringTokenizer(br.readLine());
			int original = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			transformation=0;
			possible = false;
			bfs(original,target);
			if(possible) bw.write(transformation+"\n");
			else bw.write("Impossible"+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void bfs(int original, int target) {
		visit = new boolean[10000];
		char[] number = new char[4];
		insertQueue(original);
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int currentNumber = q.poll();
				if(currentNumber==target) {
					q.clear();
					possible = true;
					return;
				}//꺼낸 숫자가 같은지 확인
				String stringNumber = Integer.toString(currentNumber);
				for(int j=0;j<4;j++) number[j] = stringNumber.charAt(j);
				for(int j=0;j<4;j++){//한글자씩 바꿔 본다.
					if(j==0){
						for(int k=1;k<=9;k++) {
							if(number[j]!=(char)(k+'0')){//같지 않으면 바꿔봄
								String tmp ="";
								for(int l=0;l<4;l++){
									if(l==j) tmp+=(char)(k+'0');
									else tmp+=number[l];
								}
								int num = Integer.parseInt(tmp);
								if(primeSet.contains(num) && visit[num]==false) insertQueue(num);
							}
						}
					}
					else {
						for(int k=0;k<=9;k++) {
							if(number[j]!=(char)(k+'0')){//같지 않으면 바꿔봄
								String tmp ="";
								for(int l=0;l<4;l++){
									if(l==j) tmp+=(char)(k+'0');
									else tmp+=number[l];
								}
								int num = Integer.parseInt(tmp);
								if(primeSet.contains(num) && visit[num]==false) insertQueue(num);
							}
						}
					}
				}
			}
			transformation++;
		}
	}
	private static void insertQueue(int original) {
		q.add(original);
		visit[original] = true;
	}
	private static void makePrimeSet() { //에라토스테네스의 체 
		boolean[] visit = new boolean[10000];
		for(int i=2;i<=9999;i++) {
			if(i>2 && i%2==0) continue; //짝수는 어차피 소수가 아니다.
			for(int j=2;(j*i)<=9999;j++) visit[j*i] = true;
		}
		for(int i=1000;i<=9999;i++) {
			if(visit[i]==false) {
				primeSet.add(i);
			}
		}
	}
}
