package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 18:00
 * 진수가 적어도 K명의 병사를 이길 수 있는
 * 최소의 스탯포인트
3 3
10 5 5
5 0 5
5 5 0
3 2
234 23 342
35 4634 34
46334 6 789

2 1
10 0 0
9 10 10

3 2
100 0 0
50 50 50
151 0 0
 */
public class 용감한용사진수 {
	static int N,K;//병사수, 이겨야할 수
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int answer = Integer.MAX_VALUE;
	static ArrayList<Enemy> enemylist = new ArrayList<Enemy>();
	static Jinsu jinsu = new Jinsu(0,0,0);
	static class Jinsu{
		int power;
		int dex;
		int intelligence;
		public Jinsu(int power, int dex, int intelligence) {
			this.power = power;
			this.dex = dex;
			this.intelligence = intelligence;
		}
	}
	static class Enemy{
		int power;
		int dex;
		int intelligence;
		public Enemy(int power, int dex, int intelligence, int allStat) {
			super();
			this.power = power;
			this.dex = dex;
			this.intelligence = intelligence;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		sorting();
		getAnswer();
		System.out.println(answer);
	}
	private static void getAnswer() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int cnt = 0;
				for(int k=0;k<N;k++) {
					if(enemylist.get(k).intelligence<=enemylist.get(i).intelligence
							&& enemylist.get(k).dex<=enemylist.get(j).dex) cnt++;
					if(cnt==K) {
						answer = Math.min(answer,enemylist.get(k).power+enemylist.get(j).dex+enemylist.get(i).intelligence);
						break;
					}
				}
			}
		}
	}
	private static void sorting() {
		Collections.sort(enemylist,new Comparator<Enemy>() {
			@Override
			public int compare(Enemy o1, Enemy o2) {
				return Integer.compare(o1.power,o2.power);
			}
		});
	}
	private static void setData() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for(int i=0,p,d,in;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			in = Integer.parseInt(st.nextToken());
			enemylist.add(new Enemy(p,d,in,(p+d+in)));
		}
	}
}
