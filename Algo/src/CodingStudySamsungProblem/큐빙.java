package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 11:30 시작
 * 
 * - 면
 * 윗 : U
 * 아래 : D
 * 앞 : F
 * 뒤 : B
 * 왼 : L
 * 오 : R
 * 
 * - 색
 * w:흰
 * y:노
 * r:빨
 * o:오
 * g:초
 * b:파
 * 
 * -방향
 * +:시계
 * -:반시계
 * 
 * 
 * 18:54 끝
 * 
 * 
 *    다 신 안 풀 어 
 */
public class 큐빙 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static char[][] cube = new char[12][9];//전개도
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			cubeInitailize();
			rotate();
			makeAnswer();
		}
		bw.flush();
		bw.close();
	}

	private static void makeAnswer() throws IOException {
		for(int row=0;row<3;row++) {
			for(int col=3;col<6;col++) {
				bw.write(cube[row][col]);
			}
			bw.write("\n");
		}
	}

	private static void rotate() throws NumberFormatException, IOException {
		int rotateSize = Integer.parseInt(br.readLine());
		char[][] command = new char[rotateSize][2];
		st = new StringTokenizer(br.readLine());
		for(int rotate=0;rotate<rotateSize;rotate++){
			
			command[rotate] = st.nextToken().toCharArray();
					spin(command[rotate]);
			}
		}
	private static void spin(char[] command) {
		char face = command[0];
		char clockwise = command[1];
		switch (face){//각 면에 대해 시계방향, 반시계방향 회전 처리
		case 'U':
			if(clockwise=='+') upClockWise();
			else if(clockwise=='-') upCounterClockWise();
			break;
		case 'F':
			if(clockwise=='+') frontClockWise();
			else if(clockwise=='-') frontCounterClockWise();
			break;
		case 'D':
			if(clockwise=='+') downClockWise();
			else if(clockwise=='-') downCounterClockWise();
			break;
		case 'B':
			if(clockwise=='+') backClockWise();
			else if(clockwise=='-') backCounterClockWise();
			break;
		case 'L':
			if(clockwise=='+') leftClockWise();
			else if(clockwise=='-') leftCounterClockWise();
			break;
		case 'R':
			if(clockwise=='+') rightClockWise();
			else if(clockwise=='-') rightCounterClockWise();
			break;
		}
	}

	private static void upClockWise(){
		char[][] copyCube = copyCube();
		for(int row=0;row<3;row++) {
			for(int col=3;col<6;col++) {
				cube[row][col] = copyCube[5-col][row+3]; 
			}
		} //면 90도 회전 
		for(int col=6,target=3;col<9;col++,target++) cube[3][target] = copyCube[3][col];
		for(int col=3,target=0;col<6;col++,target++) cube[3][target] = copyCube[3][col];
		for(int col=0,target=5;col<3;col++,target--) cube[11][target] = copyCube[3][col];
		for(int col=3,target=8;col<6;col++,target--) cube[3][target] = copyCube[11][col];
	}

	private static void upCounterClockWise(){//반시계
		char[][] copyCube = copyCube();
		for(int row=0;row<3;row++) {
			for(int col=3;col<6;col++) {
				cube[row][col] = copyCube[col-3][5-row]; 
			}
		} //면 반시계 회전  
		for(int col=5,target=0;col>=3;col--,target++) cube[3][target] = copyCube[11][col];
		for(int col=0,target=3;col<3;col++,target++) cube[3][target] = copyCube[3][col];
		for(int col=3,target=6;col<6;col++,target++) cube[3][target] = copyCube[3][col];
		for(int col=6,target=5;col<9;col++,target--) cube[11][target] = copyCube[3][col];
	}

	private static void frontClockWise() {
		char[][] copyCube = copyCube();
		for(int row=3;row<6;row++) {
			for(int col=3;col<6;col++) {
				cube[row][col] = copyCube[8-col][row]; 
			}
		} //면 90도 회전 
		for(int col=3,target=3;col<6;target++,col++) cube[target][6] = copyCube[2][col];
		for(int row=3,target=5;row<6;row++,target--) cube[6][target] = copyCube[row][6];
		for(int col=5,target=5;col>=3;col--,target--) cube[target][2] = copyCube[6][col];
		for(int row=5,target=3;row>=3;row--,target++) cube[2][target] = copyCube[row][2];
	}

	private static void frontCounterClockWise(){
		char[][] copyCube = copyCube();
		for(int row=3;row<6;row++) {
			for(int col=3;col<6;col++) {
				cube[row][col] = copyCube[col][5-(row-3)]; 
			}
		} //면 90도 반시계회전
		for(int col=3,target=5;col<6;col++,target--) cube[target][2] = copyCube[2][col];
		for(int row=3,target=3;row<6;row++,target++) cube[6][target] = copyCube[row][2];
		for(int col=3,target=5;col<6;col++,target--) cube[target][6] = copyCube[6][col];
		for(int row=5,target=5;row>=3;row--,target--) cube[2][target] = copyCube[row][6];
	}

	private static void downClockWise() {
		char[][] copyCube = copyCube();
		for(int row=6;row<9;row++) {
			for(int col=3;col<6;col++) {
				cube[row][col] = copyCube[11-col][row+3-6]; 
			}
		} //면 90도 회전 
		for(int col=3,target=2;col<6;col++,target--) cube[5][target] = copyCube[9][col];
		for(int col=0,target=3;col<3;col++,target++) cube[5][target] = copyCube[5][col];
		for(int col=3,target=6;col<6;col++,target++) cube[5][target] = copyCube[5][col];
		for(int col=8,target=3;col>=6;col--,target++) cube[9][target] = copyCube[5][col];		
	}

	private static void downCounterClockWise() {
		char[][] copyCube = copyCube();
		for(int row=6;row<9;row++) {
			for(int col=3;col<6;col++) {
				cube[row][col] = copyCube[col+3][11-row]; 
			}
		} //면 반시계 회전  
		for(int col=6,target=3;col<9;col++,target++) cube[5][target] = copyCube[5][col];
		for(int col=3,target=0;col<6;col++,target++) cube[5][target] = copyCube[5][col];
		for(int col=0,target=5;col<3;col++,target--) cube[9][target] = copyCube[5][col];
		for(int col=3,target=8;col<6;col++,target--) cube[5][target] = copyCube[9][col];
	}

	private static void backClockWise() {
		char[][] copyCube = copyCube();
		for(int row=9;row<12;row++) {
			for(int col=3;col<6;col++) {
				cube[row][col] = copyCube[11-(col-3)][row-6]; 
			}
		} //면 90도 회전. 전개도여도 회전 방향은 그대로 돌려준다.
		for(int col=5,target=3;col>=3;col--,target++) cube[target][0] = copyCube[0][col];
		for(int row=3,target=3;row<6;row++,target++) cube[8][target] = copyCube[row][0];
		for(int col=3,target=5;col<6;col++,target--) cube[target][8] = copyCube[8][col];
		for(int row=5,target=5;row>=3;row--,target--) cube[0][target] = copyCube[row][8];
	}

	private static void backCounterClockWise() {
		char[][] copyCube = copyCube();
		for(int row=9;row<12;row++) {
			for(int col=3;col<6;col++) {
				cube[row][col] = copyCube[col+6][14-row]; 
			}
		} //면 90도 회전. 전개도여도 회전 방향은 그대로 돌려준다.
		for(int col=3,target=3;col<6;col++,target++) cube[target][8] = copyCube[0][col];
		for(int row=3,target=5;row<6;row++,target--) cube[8][target] = copyCube[row][8];
		for(int col=3,target=3;col<6;col++,target++) cube[target][0] = copyCube[8][col];
		for(int row=5,target=3;row>=3;row--,target++) cube[0][target] = copyCube[row][0];
	}

	private static void leftClockWise() {
		char[][] copyCube = copyCube();
		for(int row=3;row<6;row++) {
			for(int col=0;col<3;col++) {
				cube[row][col] = copyCube[5-col][row-3]; 
			}
		} //면 90도 회전. 전개도여도 회전 방향은 그대로 돌려준다.
		for(int row=0,target=3;row<3;row++,target++) cube[target][3] = copyCube[row][3];
		for(int row=3,target=6;row<6;row++,target++) cube[target][3] = copyCube[row][3];
		for(int row=6,target=9;row<9;row++,target++) cube[target][3] = copyCube[row][3];
		for(int row=9,target=0;row<12;row++,target++) cube[target][3] = copyCube[row][3];
	}

	private static void leftCounterClockWise() {
		char[][] copyCube = copyCube();
		for(int row=3;row<6;row++) {
			for(int col=0;col<3;col++) {
				cube[row][col] = copyCube[col+3][2-(row-3)]; 
			}
		} //면 90도 회전. 전개도여도 회전 방향은 그대로 돌려준다.
		for(int row=0,target=9;row<3;row++,target++) cube[target][3] = copyCube[row][3];
		for(int row=3,target=0;row<6;row++,target++) cube[target][3] = copyCube[row][3];
		for(int row=6,target=3;row<9;row++,target++) cube[target][3] = copyCube[row][3];
		for(int row=9,target=6;row<12;row++,target++) cube[target][3] = copyCube[row][3];
	}

	private static void rightClockWise() {
		char[][] copyCube = copyCube();
		for(int row=3;row<6;row++) {
			for(int col=6;col<9;col++) {
				cube[row][col] = copyCube[5-(col-6)][row+3]; 
			}
		} //면 90도 회전. 전개도여도 회전 방향은 그대로 돌려준다.
		for(int row=0,target=9;row<3;row++,target++) cube[target][5] = copyCube[row][5];
		for(int row=3,target=0;row<6;row++,target++) cube[target][5] = copyCube[row][5];
		for(int row=6,target=3;row<9;row++,target++) cube[target][5] = copyCube[row][5];
		for(int row=9,target=6;row<12;row++,target++) cube[target][5] = copyCube[row][5];
	}

	private static void rightCounterClockWise() {
		char[][] copyCube = copyCube();
		for(int row=3;row<6;row++) {
			for(int col=6;col<9;col++) {
				cube[row][col] = copyCube[col-3][8-(row-3)]; 
			}
		} //면 90도 회전. 전개도여도 회전 방향은 그대로 돌려준다.
		for(int row=0,target=3;row<3;row++,target++) cube[target][5] = copyCube[row][5];
		for(int row=3,target=6;row<6;row++,target++) cube[target][5] = copyCube[row][5];
		for(int row=6,target=9;row<9;row++,target++) cube[target][5] = copyCube[row][5];
		for(int row=9,target=0;row<12;row++,target++) cube[target][5] = copyCube[row][5];
	}

	private static void cubeInitailize(){
		//큐브 초기화
		for(int row=0;row<=2;row++) {
			for(int col=3;col<=5;col++) {
				cube[row][col]='w';
			}
		}//위면,흰
		
		
		for(int row=3;row<=5;row++) {
			for(int col=0;col<=2;col++){
				cube[row][col]='g';
			}
		}//왼쪽,초
		
		
		for(int row=3;row<=5;row++) {
			for(int col=3;col<=5;col++){
				cube[row][col]='r';
			}
		}//앞,빨
		
		
		for(int row=3;row<=5;row++) {
			for(int col=6;col<=8;col++){
				cube[row][col]='b';
			}
		}//오른쪽,파
		
		
		for(int row=6;row<=8;row++) {
			for(int col=3;col<=5;col++){
				cube[row][col]='y';
			}
		}//아래, 노랑
		
		
		for(int row=9;row<=11;row++) {
			for(int col=3;col<=5;col++){
				cube[row][col]='o';
			}
		}// 뒷면 오렌지
	}
	private static char[][] copyCube() {
		char[][] copyCube = new char[12][9];
		for(int row=0;row<12;row++) {
			for(int col=0;col<9;col++){
				copyCube[row][col] = cube[row][col];
			}
		}
		return copyCube;
	}
	private static void view(){
		//큐브 전개도
		for(int row=0;row<12;row++) {
			for(int col=0;col<9;col++) {
				System.out.print(cube[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
