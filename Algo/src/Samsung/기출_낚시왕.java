package Samsung;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 시간내에 못품
 * 이동처리에서 오래걸림 
 * 
 * 맵에 써서 탐색하고 갱신하는 방법으로 했는데
 * 
 * 너무너무너무너ㅜ머눠무너무너너머무너무너무 너어어어어어어무우우우우 오래걸리고 뇌절 심하게와서 
 * 태환씨 풀이 대로 품
 * 큐에서 빼면서 맥스값 갱신하는 테크닉..
 * 나무재테크는 깔끔하게 풀었으면서 왜 이건 큐를 생각하지 못했는지에 대한 반성 ㅠㅠ
 * 
 * 으아악 ㅠㅠ
 * 
 * 
 */
class SharkInfo{
	int row;
	int col;
	int speed;
	int dir;
	int size;
	public SharkInfo(int r, int c, int s, int d, int z){
		row =r;
		col =c;
		speed = s;
		dir = d;
		size = z;
	}
}

public class 기출_낚시왕 {
	static int R,C,M;
	static int SharkSize;
	static int[][] sea;
	static Queue<SharkInfo> beforeSharkQueue = new LinkedList<SharkInfo>();
	static Queue<SharkInfo> afterSharkQueue = new LinkedList<SharkInfo>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		sea = new int[R][C];
		for(int i=1;i<=M;i++){
			int r = sc.nextInt()-1;
			int c = sc.nextInt()-1;
			int s = sc.nextInt();
			int d = sc.nextInt();
			int z = sc.nextInt();
			beforeSharkQueue.add(new SharkInfo(r, c, s, d, z));
			sea[r][c] = z;
		}
		for(int move=0;move<C;move++){//낚시꾼 이동
			for(int row=0;row<R;row++) {
				if(sea[row][move]!=0){
					SharkSize+=sea[row][move];
					sea[row][move]=0;
					break;
				}
			}
			moveSharks();
		}
		System.out.println(SharkSize);
	}
	private static void viewSea() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				System.out.print(sea[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void moveSharks(){
		//이동대상 물고기들의 다음 좌표들을 설정해준다.
		while(!beforeSharkQueue.isEmpty()){
			int diff=0;
			int dist=0;
			SharkInfo curShark = beforeSharkQueue.poll();
			if(sea[curShark.row][curShark.col]==0 || curShark.size<sea[curShark.row][curShark.col]) continue; //죽은자리면 무시
			//사이즈보다 작으면 죽은거라 무시
			sea[curShark.row][curShark.col]=0; //현재 자리를 0으로 하고 다음 좌표를 향해 떠남 
			switch (curShark.dir){
			case 1: //위
				diff = curShark.row-curShark.speed;
				if(diff>=0){
					curShark.row = diff; 
					afterSharkQueue.add(curShark);
				}else {
					dist = curShark.speed-curShark.row;
					if((dist/(R-1))%2==0){//짝수면
						curShark.row = dist%(R-1);
						curShark.dir = 2;
						afterSharkQueue.add(curShark);
					}else {
						curShark.row = (R-1)-(dist%(R-1));
						afterSharkQueue.add(curShark);
					}
				}
				break;
			case 2: //아래
				diff = curShark.speed+curShark.row;
				if(diff<R){
					curShark.row = diff; 
					afterSharkQueue.add(curShark);
				}else {
					dist =curShark.speed-((R-1)-curShark.row);
					if((dist/(R-1))%2==0){//짝수면
						curShark.row = (R-1)-(dist%(R-1));
						curShark.dir = 1;
						afterSharkQueue.add(curShark);	
					}else {
						curShark.row = (dist%(R-1));
						afterSharkQueue.add(curShark);
					}
				}
				break; 
			case 3: //오른쪽
				diff = curShark.speed+curShark.col;
				if(diff<C){
					curShark.col = diff; 
					afterSharkQueue.add(curShark);
				}else {
					dist =curShark.speed-((C-1)-curShark.col);
					if((dist/(C-1))%2==0){//짝수면
						curShark.col = (C-1)-(dist%(C-1));
						curShark.dir = 4;
						afterSharkQueue.add(curShark);
					}else {
						curShark.col = dist%(C-1);
						afterSharkQueue.add(curShark);
					}
				}
				break;
			case 4: //왼쪽
				diff = curShark.col-curShark.speed;
				if(diff>=0){
					curShark.col = diff; 
					afterSharkQueue.add(curShark);
				}else {
					dist = curShark.speed-curShark.col;
					if((dist/(C-1))%2==0){//짝수면
						curShark.col = dist%(C-1);
						curShark.dir = 3;
						afterSharkQueue.add(curShark);
					}else {
						curShark.col = (C-1)-(dist%(C-1));
						afterSharkQueue.add(curShark);
					}
				}
				break;
			}
		}
		while(!afterSharkQueue.isEmpty()){
			SharkInfo afterShark = afterSharkQueue.poll();
			
			sea[afterShark.row][afterShark.col] = Math.max(sea[afterShark.row][afterShark.col],afterShark.size);
			beforeSharkQueue.add(afterShark);
		}
	}
}

