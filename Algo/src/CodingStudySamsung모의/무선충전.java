package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 10 by 10 map
 * 
 * 설치된 BC영역 내에 사용자가 접속하면 충전을 하게되는데
 * 접속자 수가 2명 이상일 경우
 * 균등하게 충전하게됌
 * 모든 사용자가 충전한 양의 최대값
 * 
 * 시작부터 충전가능
 * 두 사용자가 같은 위치에 존재 가능
 * A는 1,1에서
 * B는 10,10에서 출발
 * 
 * 제일 센 BC에 접속해서 충전받는게 최대값을 가질 것임
 */
public class 무선충전 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static int[] dr = new int[] {0,-1,0,1,0};
	static int[] dc = new int[] {0,0,1,0,-1};//이동하지않음, 상우하좌
	static int[] A,B;
	static BC[] bcs;
	static int M,bcSize;
	static int sum;
	static class BC{
		int row;
		int col;
		int charge;
		int power;
		int id;
		public BC(int row, int col, int charge, int power, int id) {
			this.row = row;
			this.col = col;
			this.charge = charge;
			this.power = power;
			this.id = id;
		}

	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			simualation();
			System.out.println("#"+testcase+" "+sum);
		}
	}
	private static void simualation(){
		Comparator<BC> cp = new Comparator<BC>() {			
			@Override
			public int compare(BC o1, BC o2) {
				return -Integer.compare(o1.power,o2.power);
			}
		};
		int Arow = 1;
		int Acol = 1;
		int Brow = 10;
		int Bcol = 10;
		for(int i=0;i<=M;i++) {
			PriorityQueue<BC> BCA = new PriorityQueue<BC>(cp);
			PriorityQueue<BC> BCB = new PriorityQueue<BC>(cp);
			Arow = Arow+dr[A[i]];
			Acol = Acol+dc[A[i]];
			Brow = Brow+dr[B[i]];
			Bcol = Bcol+dc[B[i]];
			for(int j=0,Adist,Bdist;j<bcSize;j++) {
				Adist = Math.abs(bcs[j].row-Arow)+Math.abs(bcs[j].col-Acol);
				Bdist = Math.abs(bcs[j].row-Brow)+Math.abs(bcs[j].col-Bcol);
				if(bcs[j].charge>=Adist) BCA.add(bcs[j]);
				if(bcs[j].charge>=Bdist) BCB.add(bcs[j]);//기지국 범위 내에 있으면 넣는다.
			}
			int BCASize = BCA.size();//속한 갯수
			int BCBSize = BCB.size();
			if(BCASize>0 && BCBSize==0) sum+=BCA.peek().power;
			else if(BCASize==0 && BCBSize>0) sum+=BCB.peek().power;
			else if(BCASize>0 && BCBSize>0){//둘다 속한게 여러개면
				if(BCA.peek().power==BCB.peek().power && (BCA.peek().id==BCB.peek().id)){//power와 아이디가 같은경우
					if(BCASize==1 && BCBSize==1) sum+=BCA.peek().power;//둘다 딱 한개씩 있는거라면
					else if(BCASize>1 && BCBSize==1) {//A가 속한게 더 많다면 
						BCA.poll();
						sum+=BCA.peek().power;
						sum+=BCB.peek().power;
					}
					else if(BCASize==1 && BCBSize>1) { //B가 속한게 더 많다면
						BCB.poll();
						sum+=BCA.peek().power;
						sum+=BCB.peek().power;
					}else { //둘다 속한게 많은 경우 
						sum+=BCA.peek().power;//A것을 더해주고
						BCA.poll();
						BCB.poll();
						sum+=Math.max(BCA.peek().power,BCB.peek().power);
					}
				}else {//아예 다른 BC인경우니까
					sum+=BCA.peek().power+BCB.peek().power;
				}
			}
		}
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		bcSize = Integer.parseInt(st.nextToken());
		A = new int[M+1];
		B = new int[M+1];
		A[0] = 0;
		B[0] = 0;//처음엔 제자리
		map = new int[11][11];
		bcs = new BC[bcSize];
		sum=0;
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=M;i++) A[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=M;i++) B[i] = Integer.parseInt(st.nextToken());
		for(int i=0,col,row,charge,power;i<bcSize;i++) {
			st = new StringTokenizer(br.readLine());
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			charge = Integer.parseInt(st.nextToken());
			power = Integer.parseInt(st.nextToken());
			bcs[i] = new BC(row, col, charge, power,i);
		}
	}
}
