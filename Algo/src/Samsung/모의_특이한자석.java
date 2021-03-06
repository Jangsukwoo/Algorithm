package Samsung;

import java.util.Scanner;

public class 모의_특이한자석 {
	static int[][] gearState = new int[4][8];
	static int rotateSize;
	static int[] rotateWise = new int[4];
	static int score;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T= sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++){
			score=0;
			rotateSize = sc.nextInt();
			for(int row=0;row<4;row++)
				for(int col=0;col<8;col++) gearState[row][col]=sc.nextInt();
			for(int r=0;r<rotateSize;r++){
				int gid = sc.nextInt();
				int wise = sc.nextInt(); //wise 1 : cw , wise -1 : ccw
				for(int i=0;i<4;i++) rotateWise[i]=0;
				wiseCheck((gid-1),wise);
				rotate();
			}
			total();
			System.out.println("#"+testcase+" "+score);
		}
	}
	private static void view() {
		for(int row=0;row<4;row++) {
			for(int col=0;col<8;col++) {
				System.out.print(gearState[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void total() {
		for(int gid=0;gid<4;gid++){
			switch (gid){
			case 0:
				if(gearState[gid][0]==1) score+=1;
				break;
			case 1:		
				if(gearState[gid][0]==1) score+=2;
				break;
			case 2:		
				if(gearState[gid][0]==1) score+=4;
				break;
			case 3:		
				if(gearState[gid][0]==1) score+=8;
				break;

			}
		}
	}
	private static void rotate() {
		int save=0;
		for(int gid=0;gid<4;gid++){
			switch (rotateWise[gid]){
			case 1://CW
				save = gearState[gid][7];
				for(int i=7;i>0;i--) gearState[gid][i]=gearState[gid][i-1];					
				gearState[gid][0] = save;
				break;
			case -1://CCW
				save = gearState[gid][0];
				for(int i=0;i<7;i++) gearState[gid][i]=gearState[gid][i+1];					
				gearState[gid][7] = save;
				break;
			}
		}
	}
	private static void wiseCheck(int gearID, int wise){
		//확인해야할 인덱스는 2,6 //2는 오른쪽, 6은 왼쪽
		switch (gearID) {
		case 0:
			rotateWise[gearID] =  wise;
			if(gearState[gearID][2]!=gearState[1][6]){//같지 않으면
				rotateWise[1] = -wise;
				if(gearState[1][2]!=gearState[2][6]){//같지 않으면
					rotateWise[2] = wise;
					if(gearState[2][2]!=gearState[3][6]){//같지 않으면
						rotateWise[3] = -wise;
					}
				}
			}
			break;
		case 1:
			rotateWise[gearID] =  wise;
			if(gearState[gearID][6]!=gearState[0][2]){//왼쪽바퀴가 같지 않으면
				rotateWise[0] = -wise;
			}
			if(gearState[gearID][2]!=gearState[2][6]){//오른쪽바퀴가 같지 않으면
				rotateWise[2] = -wise;
				if(gearState[2][2]!=gearState[3][6]){//같지 않으면
					rotateWise[3] = wise;
				}

			}
			break;
		case 2:
			rotateWise[gearID] =  wise;
			if(gearState[gearID][2]!=gearState[3][6]){//오른쪽바퀴가 같지 않으면
				rotateWise[3] = -wise;
			}
			if(gearState[gearID][6]!=gearState[1][2]){//왼쪽바퀴가 같지 않으면
				rotateWise[1] = -wise;
				if(gearState[1][6]!=gearState[0][2]){//같지 않으면
					rotateWise[0] = wise;
				}

			}
			break;
		case 3:
			rotateWise[gearID] =  wise;
			if(gearState[gearID][6]!=gearState[2][2]){//같지 않으면
				rotateWise[2] = -wise;
				if(gearState[2][6]!=gearState[1][2]){//같지 않으면
					rotateWise[1] = wise;
					if(gearState[1][6]!=gearState[0][2]){//같지 않으면
						rotateWise[0] = -wise;
					}
				}
			}
			break;
		}
	}
}
