package CodingStudySamsungProblem;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 주사위 윷놀이판이 있고
 * 처음에 시작칸에 말 4개가 있음
 * 말은 게임판에 적힌 화살표 방향으로 움직임
 * 칸의 색에 따라 움직이는 방향이 정해져있고
 * 시작과 도착칸은 말이 이미 있어도 이동할 수 있지만
 * 다른 이동하려고하는 칸에 이미 말이 있는 경우
 * 그 칸으로 이동할 수 없다.
 * 도착칸을 넘어가면
 * 그 칸에서 멈춤 
 * 
 * 말이 이동할 때마다 그 칸에 있는 점수가 추가된다. 
 * 최종적으로 얻는 스코어는?
 * 
 * 맵을 어떻게 만들지
 * 
5 1 1 1 1 4 5 1 1 2 //가운데 겹치게

하..해결
 */
public class 주사위윷놀이 {
	static int[] dice = new int[10];
	static int[] redline = new int[] {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40};
	static boolean[] rVisit,crVisit;
	static int[] centerRedline = new int[] {25,30,35,40};//마지막 40도 처리해줘야할듯
	static int[][] blueline;
	static boolean[][] bluelineVisit;
	static boolean[] arriveHorse;
	static Horse[] horselist = new Horse[4];
	static int score;
	static int maxScore;
	static int[] pickHorse = new int[10];
	static int cnt=0;
	static boolean possible;
	static class Horse{
		String line;
		int bluelineNumber;
		int bluelineIdx;
		int redlineIdx;
		int centerlineIdx;
		public Horse(String line, int bluelineNumber, int bluelineIdx, int redlineIdx) {
			this.line = line;
			this.bluelineNumber = bluelineNumber;
			this.bluelineIdx = bluelineIdx;
			this.redlineIdx = redlineIdx;
		}

	}
	public static void main(String[] args) {
		setData();
		dfs(0);
		//play();
		System.out.println(maxScore);
	}

	private static void play(){		
		for(int i=0;i<4;i++) horselist[i] = new Horse("red",0,0,0);
		rVisit = new boolean[redline.length];
		crVisit = new boolean[centerRedline.length];
		arriveHorse = new boolean[4];
		bluelineVisit = new boolean[3][];
		bluelineVisit[0] = new boolean[blueline[0].length];
		bluelineVisit[1] = new boolean[blueline[1].length];
		bluelineVisit[2] = new boolean[blueline[2].length];
		score=0;
		possible = true;
		for(int i=0,move;i<10;i++){//주사위 하나씩
			move = dice[i];
			if(arriveHorse[pickHorse[i]]==false) {
				moveHorse(move,pickHorse[i]);//이동 가능하면 보낸다.	
			}
		}
		if(possible) maxScore = Math.max(maxScore,score);
	}

	private static void dfs(int depth) {
		if(depth==10){//10개 고름
			play();
			return;
		}
		for(int i=0;i<4;i++){
			pickHorse[depth]=i;
			dfs(depth+1);
		}
	}

	private static void moveHorse (int move,int horseIdx) {
		switch (horselist[horseIdx].line) {
		case "red":
			redlineCheck(move,horseIdx);
			break;
		case "blue":
			bluelineCheck(move,horseIdx);
			break;
		case "center"://마지막 센터라인이면 
			ceterlineCheck(move,horseIdx);
			break;
		}
		return ;
	}
	private static void redlineCheck(int move, int horseIdx) {
		int currentRedIdx = horselist[horseIdx].redlineIdx;
		int nextRedIdx = currentRedIdx+move;
		if(rVisit.length>nextRedIdx){//영역 안에 있고 
			if(rVisit[nextRedIdx]==false){//false면
				rVisit[currentRedIdx] = false;//떠남
				rVisit[nextRedIdx] = true;
				score+=redline[nextRedIdx];//스코어 획득
				if(redline[nextRedIdx]==10 || redline[nextRedIdx]==20 || redline[nextRedIdx]==30){
					horselist[horseIdx].line="blue";
					horselist[horseIdx].bluelineNumber=redline[nextRedIdx];
					horselist[horseIdx].bluelineIdx=0;
				}else {
					horselist[horseIdx].redlineIdx = nextRedIdx;
				}
				
				if(nextRedIdx==(rVisit.length-1)){//마지막 값이면
					crVisit[crVisit.length-1]=true;//센터라인의 마지막에도 true
				}
			} else {
				possible = false;
				return;
			}
		}else {//영역 넘어가버리면
			arriveHorse[horseIdx]=true;//도착!
			rVisit[currentRedIdx] = false;//얜 떠나고 끝남
			if(currentRedIdx==(rVisit.length-1)){//마지막 값이면
				crVisit[crVisit.length-1]=false;
			}
		}
	}

	private static void bluelineCheck(int move, int horseIdx) {
		int curentBlueIdx = horselist[horseIdx].bluelineIdx;
		int nextBlueIdx =0;
		int bluelineIdx=0;
		if(horselist[horseIdx].bluelineNumber==10) bluelineIdx=0;
		else if(horselist[horseIdx].bluelineNumber==20) bluelineIdx=1;
		else if(horselist[horseIdx].bluelineNumber==30) bluelineIdx=2;
		nextBlueIdx=curentBlueIdx+move;
		if(bluelineVisit[bluelineIdx].length>nextBlueIdx){//아직 라인 안이고
			if(bluelineVisit[bluelineIdx][nextBlueIdx]==false){//가려는곳이 false인데
				if(nextBlueIdx==(bluelineVisit[bluelineIdx].length-1)){//만약 가려는곳이 센터전환점이면
					if(crVisit[0]==false){//아무도 방문 안되어있으면 
						crVisit[0]=true;
						score+=blueline[bluelineIdx][nextBlueIdx];//스코어 획득
						horselist[horseIdx].centerlineIdx = 0;
						horselist[horseIdx].line="center";//센터라인에 올림
						bluelineVisit[bluelineIdx][curentBlueIdx]= false;//떠남
					} else {
						possible = false;
						return;
					}
				}else{//센터 전환점이 아니면
					horselist[horseIdx].bluelineIdx = nextBlueIdx;
					horselist[horseIdx].line="blue";	
					score+=blueline[bluelineIdx][nextBlueIdx];//스코어 획득
					bluelineVisit[bluelineIdx][curentBlueIdx] = false;//떠남
					bluelineVisit[bluelineIdx][nextBlueIdx]=true;
				}
			} else {
				possible = false;
				return;
			}
		}else {//라인밖이면 센터라인에 갈 수 있는지?
			int centerIdx = curentBlueIdx+move-(bluelineVisit[bluelineIdx].length-1);
			if(centerIdx<crVisit.length){//센터 영역 안에 들어갈 수 있으며
				if(crVisit[centerIdx]==false){//그 자리에 갈 수 있으면
					bluelineVisit[bluelineIdx][curentBlueIdx] = false;//떠남. blueNext처리 불필요
					crVisit[centerIdx] = true;
					score+=centerRedline[centerIdx];//스코어 획득
					horselist[horseIdx].centerlineIdx=centerIdx;
					horselist[horseIdx].line="center"; //센터에 올림
					if(centerIdx==(crVisit.length-1)){//만약 센터라인의 마지막이면
						rVisit[rVisit.length-1]=true;//red 마지막값도 true
					}
				} else {
					possible = false;
					return;
				}
			}else {//센터 영역 넘어가버리면
				bluelineVisit[bluelineIdx][curentBlueIdx] = false;//떠남
				arriveHorse[horseIdx]=true;//도착!
			}
		}
		if(blueline[bluelineIdx][curentBlueIdx]==10){//시작 위치인경우는 red에서도 떠남처리필요
			rVisit[5]=false;//떠남처리
		}else if(blueline[bluelineIdx][curentBlueIdx]==20) {
			rVisit[10]=false;//떠남처리
		}else if(blueline[bluelineIdx][curentBlueIdx]==30) {
			rVisit[15]=false;//떠남처리
		}
	}
	private static void ceterlineCheck(int move, int horseIdx){
		int currentCenterIdx = horselist[horseIdx].centerlineIdx;
		int nextCenterIdx = currentCenterIdx+move;
		if(crVisit.length>nextCenterIdx){//영역 안에 있으며 
			if(crVisit[nextCenterIdx]==false){//갈 수 있는데
				if(nextCenterIdx==(crVisit.length-1)){//마지막지점에 가려한다면
					if(rVisit[rVisit.length-1]==false){//마지막에 아무도 없을 때만
						score+= centerRedline[nextCenterIdx];
						crVisit[currentCenterIdx] = false;
						crVisit[nextCenterIdx] = true;
						rVisit[rVisit.length-1] = true;
						horselist[horseIdx].centerlineIdx=nextCenterIdx;
						//System.out.println("마지막지점에도착");
					} else {
						possible = false;
						return;
					}
				}else { //마지막 지점을 가려하게 아니면 
					score+= centerRedline[nextCenterIdx];
					crVisit[currentCenterIdx] = false;
					crVisit[nextCenterIdx] = true;
					horselist[horseIdx].centerlineIdx=nextCenterIdx;
				}
			} else {
				possible = false;
				return;
			}
		}else {//영역 넘어가버리면
			crVisit[currentCenterIdx] = false;//얜 떠나고 끝남
			arriveHorse[horseIdx]=true;//도착!
			if(currentCenterIdx==(crVisit.length-1)){//마지막 값이면
				rVisit[rVisit.length-1]=false;
			}
		}
	}

	private static void setData() {
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<10;i++) dice[i] = sc.nextInt();//주사위
		blueline = new int[][]{{10,13,16,19,25},{20,22,24,25},{30,28,27,26,25}};
	}
}