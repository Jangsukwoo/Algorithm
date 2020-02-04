package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
1
6 2
3 3 3 2 1 1
3 3 3 2 2 1
3 3 3 3 3 2
2 2 3 2 2 2
2 2 3 2 2 2
2 2 2 2 2 2

푸는중 
 */
public class 활주로건설 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N,X;
	static int[][] map;
	static int answer;
	static boolean[][] slipway;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			widthCheck();
			heightCheck();
			bw.write("#"+testcase+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void widthCheck() {
		boolean possible = true;
		slipway = new boolean[N][N];
		for(int row=0,height;row<N;row++){//한 가로씩
			height = map[row][0];
			possible = true;
			for(int col=1;col<N;col++){//열검사
				if(height!=map[row][col]){//높이가 같지 않은데
					int diff =map[row][col]-height;
					switch (diff){
					case 1://오르막길
						for(int l=1;l<=X;l++){//경사로의 길이만큼 대본다.
							if((col-l)>=0){//
								if(map[row][col-l]==height && slipway[row][col-l]==false) continue;
								else {
									possible= false;
									break;
								}
							}else{//범위 만족하지 않으면
								possible = false;
								break;
							}
						}
						if(possible) {
							for(int l=1;l<=X;l++) slipway[row][col-l]=true;
							height++;
						}
						break;
					case -1://내리막길 
						for(int l=0;l<X;l++){//경사로의 길이만큼 대본다.
							if((col+l)<N){
								if(map[row][col+l]==(height-1) && slipway[row][col+l]==false) continue;
								else {
									possible= false;
									break;
								}
							}else{//범위 만족하지 않으면
								possible = false;
								break;
							}
						}
						if(possible) {
							for(int l=0;l<X;l++) slipway[row][col+l]=true;
							height--;
						}
						break;
					default :
						possible = false;
						break;
					}
				}
			}
			if(possible) answer++;
		}
	}
	private static void heightCheck() {
		boolean possible = true;
		slipway = new boolean[N][N];
		for(int col=0,height;col<N;col++){//한 가로씩
			height = map[0][col];
			possible = true;
			for(int row=1;row<N;row++){//열검사
				if(height!=map[row][col]){//높이가 같지 않은데
					int diff =map[row][col]-height;
					switch (diff){
					case 1://오르막길
						for(int l=1;l<=X;l++){//경사로의 길이만큼 대본다.
							if((row-l)>=0){//
								if(map[row-l][col]==height && slipway[row-l][col]==false) continue;
								else {
									possible= false;
									break;
								}
							}else{//범위 만족하지 않으면
								possible = false;
								break;
							}
						}
						if(possible) {
							for(int l=1;l<=X;l++) slipway[row-l][col]=true;
							height++;
						}
						break;
					case -1://내리막길 
						for(int l=0;l<X;l++){//경사로의 길이만큼 대본다.
							if((row+l)<N){
								if(map[row+l][col]==(height-1) && slipway[row+l][col]==false) continue;
								else {
									possible= false;
									break;
								}
							}else{//범위 만족하지 않으면
								possible = false;
								break;
							}
						}
						if(possible) {
							for(int l=0;l<X;l++) slipway[row+l][col]=true;
							height--;
						}
						break;
					default :
						possible = false;
						break;
					}
				}
			}
			if(possible) answer++;
		}
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		answer=0;
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
