package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 특이한자석 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] magnetState = new int[4][8];
	static int[] rotateWise;
	static int K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			play();
			System.out.println("#"+testcase+" "+getScore());
		}
	}
	private static int getScore() {
		int score=0;
		if(magnetState[0][0]==1) score+=1;
		if(magnetState[1][0]==1) score+=2;
		if(magnetState[2][0]==1) score+=4;
		if(magnetState[3][0]==1) score+=8;
		return score;
	}
	private static void play() throws IOException {
		for(int round=0;round<K;round++) {
			st = new StringTokenizer(br.readLine());
			int magnetId = Integer.parseInt(st.nextToken());
			int wise = Integer.parseInt(st.nextToken());
			rotateWise = new int[4];
			setRotateWise(magnetId-1,wise);
			rotate();
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		K = Integer.parseInt(br.readLine());
		for(int magnet=0;magnet<4;magnet++) {
			st = new StringTokenizer(br.readLine());
			for(int state=0;state<8;state++) {
				magnetState[magnet][state] = Integer.parseInt(st.nextToken());
			}
		}

	}
	private static void setRotateWise(int magnetId, int wise) {
		rotateWise[magnetId]=wise;
		switch (magnetId) {
		case 0:
			if(magnetState[magnetId][2]!=magnetState[1][6]){//같지 않으면
				rotateWise[1] = -wise;
				if(magnetState[1][2]!=magnetState[2][6]){//같지 않으면
					rotateWise[2] = wise;
					if(magnetState[2][2]!=magnetState[3][6]){//같지 않으면
						rotateWise[3] = -wise;
					}
				}
			}
			break;
		case 1:
			if(magnetState[magnetId][6]!=magnetState[0][2]){//왼쪽바퀴가 같지 않으면
				rotateWise[0] = -wise;
			}
			if(magnetState[magnetId][2]!=magnetState[2][6]){//오른쪽바퀴가 같지 않으면
				rotateWise[2] = -wise;
				if(magnetState[2][2]!=magnetState[3][6]){//같지 않으면
					rotateWise[3] = wise;
				}

			}
			break;
		case 2:
			rotateWise[magnetId] =  wise;
			if(magnetState[magnetId][2]!=magnetState[3][6]){//오른쪽바퀴가 같지 않으면
				rotateWise[3] = -wise;
			}
			if(magnetState[magnetId][6]!=magnetState[1][2]){//왼쪽바퀴가 같지 않으면
				rotateWise[1] = -wise;
				if(magnetState[1][6]!=magnetState[0][2]){//같지 않으면
					rotateWise[0] = wise;
				}

			}
			break;
		case 3:
			if(magnetState[magnetId][6]!=magnetState[2][2]){//같지 않으면
				rotateWise[2] = -wise;
				if(magnetState[2][6]!=magnetState[1][2]){//같지 않으면
					rotateWise[1] = wise;
					if(magnetState[1][6]!=magnetState[0][2]){//같지 않으면
						rotateWise[0] = -wise;
					}
				}
			}
			break;

		}
	}
	private static void rotate() {
		int save=0;
		for(int magnet=0;magnet<4;magnet++){
			switch (rotateWise[magnet]){
			case 1://CW
				save = magnetState[magnet][7];
				for(int i=7;i>0;i--) magnetState[magnet][i]=magnetState[magnet][i-1];					
				magnetState[magnet][0] = save;
				break;
			case -1://CCW
				save = magnetState[magnet][0];
				for(int i=0;i<7;i++) magnetState[magnet][i]=magnetState[magnet][i+1];					
				magnetState[magnet][7] = save;
				break;
			}
		}
	}
}
