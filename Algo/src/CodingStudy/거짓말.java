package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


/*
 * 사람수 N, 파티수 M
 * 진실은 아는 사람이 없는 파티를 최대한 많이 가게하기
 * 즉
 * 거짓말쟁이로 알려지지 않으면서 과장된 이야기를 할 수 있는 파티 수 
 * 1번 파티에서 진실 말함
 * 2번 파티에서 과장된 이야기 함
 * 이 두 파티에 참여한 동일 인물은 지민이를 거짓말 쟁이라고 생각함
 * 
4 3
1 2
2 1 2
1 3
2 2 4 
6 5
1 6
2 4 5
2 1 2
2 2 3
2 3 4
2 5 6
 */
public class 거짓말 {
	static int N,M;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Set<Integer> knowFactPersons;
	static ArrayList<Integer>[] partys;
	static int max;
	public static void main(String[] args) throws IOException {
		setData();
		getMaxParticipationParty();
		System.out.println(max);
	}
	private static void getMaxParticipationParty() {
		addKnowFactPeople();//진실을 아는 집단에 사람들 추가
		getMaxParticipation(); //진실을 알지 못하는 사람들로만 구성된 파티에 참가하자 
	}
	private static void getMaxParticipation() {
		for(int i=0;i<M;i++){
			boolean factPerson = false;
			for(int j=0;j<partys[i].size();j++){
				if(knowFactPersons.contains(partys[i].get(j))) {
					factPerson = true;
					break;
				}
			}
			if(factPerson) continue;
			else max++;
		}
	}
	private static void addKnowFactPeople() {
		for(int partyTry=0;partyTry<M;partyTry++) { 
			//파티 수만큼 다 돌려버리기, 어떻게 건너건너 알지 모르니까 파티 개수만큼 시뮬레이션 돌려버려보고
			for(int i=0;i<M;i++){
				boolean factPerson = false;
				for(int j=0;j<partys[i].size();j++){
					if(knowFactPersons.contains(partys[i].get(j))) {
						factPerson = true;
						break;
					}
				}
				if(factPerson) {
					for(int j=0;j<partys[i].size();j++){
						knowFactPersons.add(partys[i].get(j));//진실을 들은 사람들 추가 
					}
				}
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		partys = new ArrayList[M];
		for(int i=0;i<M;i++) partys[i] = new ArrayList<Integer>();
		knowFactPersons = new HashSet<Integer>();
		int knowPersonsize = Integer.parseInt(st.nextToken());
		for(int i=0;i<knowPersonsize;i++) {
			int num = Integer.parseInt(st.nextToken());
			knowFactPersons.add(num);//진실을 들은 집단, 진실을 아는 집단
		}
		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int partySize = Integer.parseInt(st.nextToken());
			for(int j=0;j<partySize;j++) partys[i].add(Integer.parseInt(st.nextToken()));
		}
	}
}
