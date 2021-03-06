package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 낚시꾼은 한칸씩 옆으로 이동함.
 * 상어는 각자 방향과 크기를 가지고 있고 
 * 1초씩 각자 이동한다.
 * 1초 후 한칸에 상어가 두마리 이상 있는 경우에는
 * 가장 크기가 큰 상어가 나머지를 다 잡아먹는다.
 * 
 * 모얌 그냥 점화식 안구하고 그냥 돌렸는데 통과됐다....괜히 고민하지말고 완탐해버리자
 */
public class 낚시왕 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R,C,M;//row,col,상어수
	static int getSharkSize;
	static PriorityQueue<Shark>[][] aquariumPQueue;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static class Shark{
		int row;
		int col;
		int speed;
		int dir;
		int size;
		public Shark(int row, int col, int speed, int dir, int size) {
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		fishing();
		System.out.println(getSharkSize);
	}

	private static void fishing() {
		for(int col=1;col<=C;col++){//낚시꾼 한칸씩 이동 
			for(int row=1;row<=R;row++){//작살 
				if(aquariumPQueue[row][col].size()>0){//상어가 있으면 
					int sharkSize = aquariumPQueue[row][col].poll().size;
					getSharkSize+=sharkSize;//상어를 빼고 크기를 더해준다.
					break;
				}
			}
			System.out.println(col);
			System.out.println("이동전");
			view();
			moveShark();//상어들 이동
			System.out.println("이동후");
			view();
		}
	}

	private static void view() {
		for(int row=1;row<=R;row++) {
			for(int col=1;col<=C;col++) {
				System.out.print(aquariumPQueue[row][col].size()+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void moveShark(){
		ArrayList<Shark> allSharkList = getSharks();//수족관에 있는 상어들 전부 뺌 
		for(Shark currentShark : allSharkList){//한마리씩 이동 처리 
			//상어가 가진 속도와 방향에 따라 1초 뒤에 위치 결정해주기
			//다음 위치 결정식을 어떻게 세워야하나..
			//그냥 돌려보자
			int nr = currentShark.row;
			int nc = currentShark.col;
			int dir = currentShark.dir;
			for(int i=0;i<currentShark.speed;i++){
				switch (dir) {
				case 0:
					if(nr==1) {
						dir=(dir+2)%4;
						nr+=dr[dir];
					}else nr+=dr[dir];
					break;
				case 1:
					if(nc==C) {
						dir=(dir+2)%4;
						nc+=dc[dir];
					}else nc+=dc[dir];
					break;
				case 2:
					if(nr==R){
						dir=(dir+2)%4;
						nr+=dr[dir];
					}else nr+=dr[dir];
					break;
				case 3:
					if(nc==1) {
						dir=(dir+2)%4;
						nc+=dc[dir];
					}else nc+=dc[dir];
					break;
				}
			}
			currentShark.row=nr;
			currentShark.col=nc;
			currentShark.dir=dir;
		}
		insertAquarium(allSharkList);//상어들 자신의 새로운 위치, 다시 수족관으로
		setSurviveShark();//한 칸에 두마리 이상 있는 경우 잡아먹히는 처리 
	}
	private static void insertAquarium(ArrayList<Shark> allSharkList){
		for(Shark currentShark : allSharkList) aquariumPQueue[currentShark.row][currentShark.col].add(currentShark);
	}
	private static void setSurviveShark() {
		for(int row=1;row<=R;row++) {
			for(int col=1;col<=C;col++){
				if(aquariumPQueue[row][col].size()>1){
					Shark maxShark = aquariumPQueue[row][col].poll();
					while(!aquariumPQueue[row][col].isEmpty()) aquariumPQueue[row][col].poll();
					aquariumPQueue[row][col].add(maxShark);
				}
			}
		}
	}
	private static ArrayList<Shark> getSharks(){
		ArrayList<Shark> sharks = new ArrayList<Shark>();
		for(int row=1;row<=R;row++){
			for(int col=1;col<=C;col++) {
				if(aquariumPQueue[row][col].size()>0) {
					sharks.add(aquariumPQueue[row][col].poll());
				}
			}
		}
		return sharks;
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		aquariumPQueue = new PriorityQueue[R+1][C+1];
		Comparator<Shark> cp = new Comparator<Shark>() {
			@Override
			public int compare(Shark o1, Shark o2) {
				return -Integer.compare(o1.size,o2.size);//크기 내림차순 
			}
		};
		for(int row=1;row<=R;row++)
			for(int col=1;col<=C;col++) aquariumPQueue[row][col] = new PriorityQueue<Shark>(cp);
		for(int i=0,row,col,speed,dir,size;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			speed = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken()); //1,2,3,4 : 상하우좌 
			size = Integer.parseInt(st.nextToken());
			switch (dir) {
			case 1:
				dir = 0;
				break;
			case 2:
				dir = 2;
				break;
			case 3:
				dir = 1;
				break;
			case 4:
				dir = 3;
				break;
			}
			aquariumPQueue[row][col].add(new Shark(row, col, speed, dir, size));
		}

	}	
}
