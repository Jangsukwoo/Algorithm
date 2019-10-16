package Samsung;

import java.util.Scanner;
/*
 * 시간내에 못품
 * 이동처리에서 오래걸림 
 * 
 */
class SharkInfo{
	int id;
	int row;
	int col;
	int speed;
	int dir;
	int size;
	boolean life;
	public SharkInfo(int i, int r, int c, int s, int d, int z){
		id = i;
		row =r;
		col =c;
		speed = s;
		dir = d;
		size = z;
		life = true;
	}
}

public class 기출_낚시왕 {
	static int R,C,M;
	static int SharkSize;
	static int[][] sea;
	static SharkInfo[] sharks;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		sharks = new SharkInfo[M+1];
		sea = new int[R][C];
		for(int i=1;i<=M;i++){
			int r = sc.nextInt()-1;
			int c = sc.nextInt()-1;
			int s = sc.nextInt();
			int d = sc.nextInt();
			int z = sc.nextInt();
			sharks[i] = new SharkInfo(i,r, c, s, d, z);
			sea[r][c] = i;
		}
		sharks[0] = new SharkInfo(0,0,0,0,0,0);
		for(int move=0;move<C;move++){//낚시꾼 이동
			System.out.println("낚시");
			viewSea();
			for(int row=0;row<R;row++) {
				
				if(sea[row][move]!=0){
					SharkSize+=sharks[sea[row][move]].size;
					sharks[sea[row][move]].life=false;
					sea[row][move]=0;
					break;
				}
			}
			System.out.println("전");
			viewSea();
			moveSharks();
			System.out.println("후");
			viewSea();
		}
		System.out.println(SharkSize);
	}
	private static void viewSea() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				System.out.print(sharks[sea[row][col]].size+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void moveSharks(){
		for(int i=1;i<=M;i++){
			int diff=0;
			int dist=0;
			SharkInfo curShark = sharks[i];
			if(sharks[i].life){
				switch (curShark.dir){
				case 1: //위
					diff = curShark.row-curShark.speed;
					if(diff>=0){
						sea[curShark.row][curShark.col] = 0;
						curShark.row = diff; 
						sea[curShark.row][curShark.col] = curShark.id;
					}else {
						dist = curShark.speed-curShark.row;
						if((dist/(R-1))%2==0){//짝수면
							sea[curShark.row][curShark.col] = 0;
							curShark.row = dist%(R-1);
							curShark.dir = 2;
							if(sea[curShark.row][curShark.col]!=0){//도착 지점에 상어가 있다면
								eatCheck(curShark);
							}else sea[curShark.row][curShark.col] = curShark.id;
						}else {
							sea[curShark.row][curShark.col] = 0;
							curShark.row = (R-1)-(dist%(R-1));
							if(sea[curShark.row][curShark.col]!=0){//도착 지점에 상어가 있다면
								eatCheck(curShark);
							}else sea[curShark.row][curShark.col] = curShark.id;
						}
					}
					break;
				case 2: //아래
					diff = curShark.speed+curShark.row;
					if(diff<R){
						sea[curShark.row][curShark.col] = 0;
						curShark.row = diff; 
						sea[curShark.row][curShark.col] = curShark.id;
					}else {
						dist =curShark.speed-((R-1)-curShark.row);
						if((dist/(R-1))%2==0){//짝수면
							sea[curShark.row][curShark.col] = 0;
							curShark.row = (R-1)-(dist%(R-1));
							curShark.dir = 1;
							if(sea[curShark.row][curShark.col]!=0){//도착 지점에 상어가 있다면
								eatCheck(curShark);
							}else sea[curShark.row][curShark.col] = curShark.id;	
						}else {
							sea[curShark.row][curShark.col] = 0;
							curShark.row = (dist%(R-1));
							if(sea[curShark.row][curShark.col]!=0){//도착 지점에 상어가 있다면
								eatCheck(curShark);
							}else sea[curShark.row][curShark.col] = curShark.id;
						}
					}
					break; 
				case 3: //오른쪽
					diff = curShark.speed+curShark.col;
					if(diff<C){
						sea[curShark.row][curShark.col] = 0;
						curShark.col = diff; 
						sea[curShark.row][curShark.col] = curShark.id;
					}else {
						dist =curShark.speed-((C-1)-curShark.col);
						if((dist/(C-1))%2==0){//짝수면
							sea[curShark.row][curShark.col] = 0;
							curShark.col = (C-1)-(dist%(C-1));
							curShark.dir = 4;
							if(sea[curShark.row][curShark.col]!=0){//도착 지점에 상어가 있다면
								eatCheck(curShark);
							}else sea[curShark.row][curShark.col] = curShark.id;	
						}else {
							sea[curShark.row][curShark.col] = 0;
							curShark.col = dist%(C-1);
							if(sea[curShark.row][curShark.col]!=0){//도착 지점에 상어가 있다면
								eatCheck(curShark);
							}else sea[curShark.row][curShark.col] = curShark.id;
						}
					}
					break;
				case 4: //왼쪽
					diff = curShark.col-curShark.speed;
					if(diff>=0){
						sea[curShark.row][curShark.col] = 0;
						curShark.col = diff; 
						sea[curShark.row][curShark.col] = curShark.id;
					}else {
						dist = curShark.speed-curShark.col;
						if((dist/(C-1))%2==0){//짝수면
							sea[curShark.row][curShark.col] = 0;
							curShark.col = dist%(C-1);
							curShark.dir = 3;
							if(sea[curShark.row][curShark.col]!=0){//도착 지점에 상어가 있다면
								eatCheck(curShark);
							}else sea[curShark.row][curShark.col] = curShark.id;	
						}else {
							sea[curShark.row][curShark.col] = 0;
							curShark.col = (C-1)-(dist%(C-1));
							if(sea[curShark.row][curShark.col]!=0){//도착 지점에 상어가 있다면
								eatCheck(curShark);
							}else sea[curShark.row][curShark.col] = curShark.id;
						}
					}
					break;
				}
			}
		}
	}
	private static void eatCheck(SharkInfo curShark) {
		int sharksize = sharks[sea[curShark.row][curShark.col]].size;
		if(curShark.size>sharksize){//현재 도착한 녀석이 더 크면
			sharks[sea[curShark.row][curShark.col]].life=false;
			sea[curShark.row][curShark.col] = curShark.id;
		}else {
			sharks[curShark.id].life=false; //현재 상어가 더 작으면 false
		}
	}
}

