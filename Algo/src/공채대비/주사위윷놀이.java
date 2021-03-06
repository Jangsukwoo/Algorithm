package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 19:58 시작
 * 시작칸에 말 4개
 * 중복순열
 * 하드코딩
 * 
 * 20:00 끝.. 두시간 다채우네 ㅠ
 */
public class 주사위윷놀이 {
	static int[] redCircleLine = {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40};
	static int[] blueLeftLine = {10,13,16,19};
	static int[] blueCenterLine = {20,22,24};
	static int[] blueRightLine = {30,28,27,26};
	static int[] lastLine = {25,30,35,40};
	
	static boolean[] redCircleVisit;
	static boolean[] blueLeftVisit;
	static boolean[] blueCenterVisit;
	static boolean[] blueRightVisit;
	
	static boolean[] lastVisit;
	static Horse[] horeslist = new Horse[4];
	static int[] dice = new int[10];
	static int[] pickHorseIdx = new int[10];
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean impossible;
	static int answer;
	static int score;
	
	
	static class Horse{
		String line;
		int redCircleIdx;
		int blueLeftIdx;
		int blueCenterIdx;
		int blueRightIdx;
		int lastIdx;
		boolean escape;
		public Horse(String line, int redCircleIdx, int blueLeftIdx, int blueCenterIdx, int blueRightIdx, int lastIdx, boolean escape) {
			this.line = line;
			this.redCircleIdx = redCircleIdx;
			this.blueLeftIdx = blueLeftIdx;
			this.blueCenterIdx = blueCenterIdx;
			this.blueRightIdx = blueRightIdx;
			this.lastIdx = lastIdx;
			this.escape = escape;
		}
	}
	
	public static void main(String[] args) throws IOException {
		setData();
		duplicationPermutation(0);
		System.out.println(answer);
	}
	private static void duplicationPermutation(int depth) {
		if(depth==10){
			initailization();
			if(simulation()) answer = Math.max(answer,score);
			return;
		}
		for(int i=0;i<4;i++){
			pickHorseIdx[depth] = i;
			duplicationPermutation(depth+1);
		}
	}
	private static void initailization() {
		redCircleVisit = new boolean[redCircleLine.length];
		blueLeftVisit = new boolean[blueLeftLine.length];
		blueCenterVisit = new boolean[blueCenterLine.length];
		blueRightVisit = new boolean[blueRightLine.length];
		lastVisit = new boolean[lastLine.length];
		for(int i=0;i<4;i++) horeslist[i] = new Horse("redCircle",0,0,0,0,0,false);
	}
	private static boolean simulation(){
		impossible = false;
		score=0;
		for(int i=0;i<10;i++){
			if(horeslist[pickHorseIdx[i]].escape==false) move(horeslist[pickHorseIdx[i]],i);
			else continue;
			if(impossible) return false;
		}
		return true;
	}
	private static void move(Horse horse,int i) {
		int jump = dice[i];
		
		int nextIdx=0;
		int currentIdx=0;
		switch (horse.line){
		case "redCircle":
			
			nextIdx = horse.redCircleIdx+jump;
			currentIdx = horse.redCircleIdx;
			if(nextIdx>=redCircleLine.length) {
				horse.escape=true;//탈출
			}
			//환승 하는 경우들		
			else if(nextIdx==5){
				if(blueLeftVisit[0]==false){
					blueLeftVisit[0] = true;
					score+=blueLeftLine[0];
					horse.line="blueLeft";
				}else impossible = true;
			}else if(nextIdx==10) {
				if(blueCenterVisit[0]==false){
					score+=blueCenterLine[0];
					blueCenterVisit[0] = true;
					horse.line="blueCenter";
				}else impossible = true;
			}else if(nextIdx==15){
				if(blueRightVisit[0]==false){
					score+=blueRightLine[0];
					blueRightVisit[0] = true;
					horse.line="blueRight";
				}else impossible = true;
			}else if(nextIdx==20){
				if(lastVisit[3]==false){
					lastVisit[3] = true;
					score+=lastLine[3];
					horse.line="last";
					horse.lastIdx=3;
				}else impossible = true;
			}else { //환승하는 경우가 아님
				if(redCircleVisit[nextIdx]==false){
					score+=redCircleLine[nextIdx];
					redCircleVisit[nextIdx] = true;
					horse.redCircleIdx=nextIdx;
				}else impossible = true;
			}
			redCircleVisit[currentIdx] = false;//떠남
			break;
		case "blueLeft":
			nextIdx = horse.blueLeftIdx+jump;
			currentIdx = horse.blueLeftIdx;
			if(nextIdx>=4){//노선을 벗어나면
				if(nextIdx==4){ //도착지면
					if(lastVisit[0]==false){
						lastVisit[0] = true;
						score+=lastLine[0];
						horse.line="last";
					}else impossible=true;
				}else { //last라인에서 더 갈 수 있으면
					nextIdx-=4;
					if(nextIdx>=4) {
						horse.escape=true;
					}
					else if(lastVisit[nextIdx]==false) {
						lastVisit[nextIdx] = true;
						score+=lastLine[nextIdx];
						horse.line="last";
						horse.lastIdx=nextIdx;
					}else impossible=true;
				}
			}else {
				if(blueLeftVisit[nextIdx]==false) {
					blueLeftVisit[nextIdx] = true;
					score+=blueLeftLine[nextIdx];
					horse.blueLeftIdx=nextIdx;
				}else impossible = true;
			}
			blueLeftVisit[currentIdx] = false;
			break;
		case "blueCenter":
			nextIdx = horse.blueCenterIdx+jump;
			currentIdx = horse.blueCenterIdx;
			if(nextIdx>=3){//노선을 벗어나면
				if(nextIdx==3){ //도착지면
					if(lastVisit[0]==false){
						lastVisit[0] = true;
						score+=lastLine[0];
						horse.line="last";
					}else impossible=true;
				}else { //last라인에서 더 갈 수 있으면
					nextIdx-=3;
					//3 5 4 2 1 3 4 2 1 5
					if(nextIdx>=4) {
						horse.escape=true;
					}
					else if(lastVisit[nextIdx]==false) {
						lastVisit[nextIdx] = true;
						score+=lastLine[nextIdx];
						horse.lastIdx=nextIdx;
						horse.line="last";
					}else impossible=true;
				}
			}else {
				if(blueCenterVisit[nextIdx]==false) {
					blueCenterVisit[nextIdx] = true;
					score+=blueCenterLine[nextIdx];
					horse.blueCenterIdx=nextIdx;
				}else impossible = true;
			}
			blueCenterVisit[currentIdx] = false;
			break;
		case "blueRight":
			nextIdx = horse.blueRightIdx+jump;
			currentIdx = horse.blueRightIdx;
			if(nextIdx>=4){//노선을 벗어나면
				if(nextIdx==4){ //도착지면
					if(lastVisit[0]==false){
						lastVisit[0] = true;
						score+=lastLine[0];
						horse.line="last";
					}else impossible=true;
				}else { //last라인에서 더 갈 수 있으면
					nextIdx-=4;
					if(nextIdx>=4) {
						horse.escape=true;
					}
					else if(lastVisit[nextIdx]==false) {
						lastVisit[nextIdx] = true;
						score+=lastLine[nextIdx];
						horse.lastIdx=nextIdx;
						horse.line="last";
					}else impossible=true;
				}
			}else {
				if(blueRightVisit[nextIdx]==false) {
					blueRightVisit[nextIdx] = true;
					score+=blueRightLine[nextIdx];
					horse.blueRightIdx=nextIdx;
				}else impossible = true;
			}
			blueRightVisit[currentIdx] = false;
			break;
		case "last":
			nextIdx = horse.lastIdx+jump;
			currentIdx = horse.lastIdx;
			if(nextIdx>=4){
				horse.escape=true;
			}else {
				if(lastVisit[nextIdx]==false) {
					lastVisit[nextIdx] = true;
					score+=lastLine[nextIdx];
					horse.lastIdx=nextIdx;
				}else impossible=true;
			}
			lastVisit[currentIdx] = false;
			break;
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<10;i++) dice[i] = Integer.parseInt(st.nextToken());
	}
}
