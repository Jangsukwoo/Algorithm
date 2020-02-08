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
 */
public class 주사위윷놀이 {
	static int[] dice = new int[10];
	static int[] redline = new int[] {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40};
	static int[] centerRedline = new int[] {25,30,35,40};//마지막 40도 처리해줘야할듯
	static int[] blueline1 = new int[] {10,13,16,19,25};//우
	static int[] blueline2 = new int[] {20,22,24,25};//상
	static int[] blueline3 = new int[] {30,28,27,26,25};//좌
	static int blueline1Idx = 5;
	static int blueline2Idx = 10;
	static int blueline3Idx = 15;
	static boolean[] rVisit,crVisit;
	static boolean[] b1Visit,b2Visit,b3Visit;
	static boolean[] arriveHorse;
	static Horse[] horselist = new Horse[4];
	static int score;
	static int maxScore;
	static int[] pickHorse = new int[10];
	static int cnt=0;
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
		b1Visit = new boolean[blueline1.length];
		b2Visit = new boolean[blueline2.length];
		b3Visit = new boolean[blueline3.length];
		arriveHorse = new boolean[4];
		score=0;
		int horseIdx=0;
		int[] testcase = new int[] {0,0,1,0,2,2,2,0,0,2};//말 한마리만 이동해보자
		for(int i=0,move;i<10;i++){//주사위 하나씩
			move = dice[i];
			//moveHorse(move,testcase[i]);//이동
			//horseIdx=(horseIdx+1)%4;
			if(arriveHorse[pickHorse[i]]==false) {
				moveHorse(move,pickHorse[i]);//이동 가능하면 보낸다.	
			}
		}
		maxScore = Math.max(maxScore,score);
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
		//		System.out.println((horseIdx+1)+"번말");
		//		System.out.println("이동전");
		//		System.out.println("이동값 "+move);
		//		System.out.println(horselist[horseIdx].line+"위에있음");
		//		System.out.println();
		int currnetScore = 0;
		switch (horselist[horseIdx].line) {
		case "red":
			int currentRedIdx = horselist[horseIdx].redlineIdx;
			int nextRedIdx = currentRedIdx+move;
			if(rVisit.length>nextRedIdx){//영역 안에 있고 
				if(rVisit[nextRedIdx]==false){//false면
					rVisit[nextRedIdx] = true;
					rVisit[currentRedIdx] = false;//떠남
					if(nextRedIdx==(rVisit.length-1)){//마지막 값이면
						crVisit[crVisit.length-1]=true;
					}
					score+=redline[nextRedIdx];//스코어 획득
					currnetScore=redline[nextRedIdx];
					if(redline[nextRedIdx]==10){
						horselist[horseIdx].line="blue";
						horselist[horseIdx].bluelineNumber=10;
						horselist[horseIdx].bluelineIdx=0;
					}else if(redline[nextRedIdx]==20){
						horselist[horseIdx].line="blue";
						horselist[horseIdx].bluelineNumber=20;
						horselist[horseIdx].bluelineIdx=0;
					}else if(redline[nextRedIdx]==30) {
						horselist[horseIdx].line="blue";
						horselist[horseIdx].bluelineNumber=30;
						horselist[horseIdx].bluelineIdx=0;
					}else {
						horselist[horseIdx].redlineIdx = nextRedIdx;
					}
				}
			}else {//영역 넘어가버리면
				arriveHorse[horseIdx]=true;//도착!
				rVisit[currentRedIdx] = false;//얜 떠나고 끝남
				if(currentRedIdx==(rVisit.length-1)){//마지막 값이면
					crVisit[crVisit.length-1]=false;
				}
			}
			break;
		case "blue":
			int curentBlueIdx = horselist[horseIdx].bluelineIdx;
			int nextBlueIdx =0;
			//System.out.println("현재블루값"+curentBlueIdx+"의 실제값"+blueline1[curentBlueIdx]);

			if(horselist[horseIdx].bluelineNumber==10){ //각 라인에 대해서 
				nextBlueIdx=curentBlueIdx+move;
				if(b1Visit.length>nextBlueIdx){//아직 라인 안이고
					if(b1Visit[nextBlueIdx]==false){//가려는곳이 false면 
						if(nextBlueIdx==b1Visit.length-1){//만약 가려는곳이 센터전환점이면
							if(crVisit[0]==false){//아무도 방문 안되어있으면 
								crVisit[0]=true;
								score+=blueline1[nextBlueIdx];//스코어 획득
								currnetScore=blueline1[nextBlueIdx];
								horselist[horseIdx].centerlineIdx = 0;
								horselist[horseIdx].line="center";//센터라인에 올림
								b1Visit[curentBlueIdx] = false;//떠남
							}
						}else{//센터 전환점이 아니면
							horselist[horseIdx].bluelineIdx = nextBlueIdx;
							horselist[horseIdx].line="blue";	
							score+=blueline1[nextBlueIdx];//스코어 획득
							currnetScore=blueline1[nextBlueIdx];
							b1Visit[curentBlueIdx] = false;//떠남
							b1Visit[nextBlueIdx]=true;
						}
					}//가려는곳이 false가 아니면 안간다.
				}else {//라인밖이면 센터라인에 갈 수 있는지?
					int centerIdx = curentBlueIdx+move-(b1Visit.length-1);
					if(centerIdx<crVisit.length){//센터 영역 안에 들어갈 수 있으며
						if(crVisit[centerIdx]==false){//그 자리에 갈 수 있으면
							b1Visit[curentBlueIdx] = false;//현재자리 false
							crVisit[centerIdx] = true;
							score+=centerRedline[centerIdx];//스코어 획득
							currnetScore=centerRedline[centerIdx];
							horselist[horseIdx].centerlineIdx=centerIdx;
							horselist[horseIdx].line="center"; //센터에 올림
						}
					}else {//센터 영역 넘어가버리면
						b1Visit[curentBlueIdx]=false;//그냥 떠나버린다.
						arriveHorse[horseIdx]=true;//도착!
					}
				}
				if(blueline1[curentBlueIdx]==10){//시작 위치인경우는 red에서도 떠남처리필요
					rVisit[blueline1Idx]=false;//떠남처리
				}
			}else if (horselist[horseIdx].bluelineNumber==20){
				nextBlueIdx=curentBlueIdx+move;
				if(b2Visit.length>nextBlueIdx){//아직 라인 안이고
					if(b2Visit[nextBlueIdx]==false){//가려는곳이 false면 
						if(nextBlueIdx==b2Visit.length-1){//만약 가려는곳이 센터전환점이면
							if(crVisit[0]==false){//아무도 방문 안되어있으면 
								score+=blueline2[nextBlueIdx];//스코어 획득
								currnetScore=blueline2[nextBlueIdx];
								horselist[horseIdx].centerlineIdx = 0;
								horselist[horseIdx].line="center";//센터라인에 올림
								b2Visit[curentBlueIdx] = false;//떠남
							}
						}else{//센터 전환점이 아니면
							horselist[horseIdx].bluelineIdx = nextBlueIdx;
							horselist[horseIdx].line="blue";	
							score+=blueline2[nextBlueIdx];//스코어 획득
							currnetScore=blueline2[nextBlueIdx];
							b2Visit[curentBlueIdx] = false;//떠남
							b2Visit[nextBlueIdx]=true;
						}
					}//가려는곳이 false가 아니면 안간다.
				}else {//라인밖이면 센터라인에 갈 수 있는지?
					int centerIdx = curentBlueIdx+move-(b2Visit.length-1);
					if(centerIdx<crVisit.length){//센터 영역 안에 들어갈 수 있으며
						if(crVisit[centerIdx]==false){//그 자리에 갈 수 있으면
							b2Visit[curentBlueIdx] = false;//현재자리 false
							crVisit[centerIdx] = true;
							score+=centerRedline[centerIdx];//스코어 획득
							currnetScore=centerRedline[centerIdx];
							horselist[horseIdx].centerlineIdx=centerIdx;
							horselist[horseIdx].line="center"; //센터에 올림
						}
					}else {//센터 영역 넘어가버리면
						b2Visit[curentBlueIdx]=false;//그냥 떠나버린다.
						arriveHorse[horseIdx]=true;//도착!
					}
				}
				if(blueline2[curentBlueIdx]==10){//시작 위치인경우는 red에서도 떠남처리필요
					rVisit[blueline2Idx]=false;//떠남처리
				}
			}else if (horselist[horseIdx].bluelineNumber==30){
				nextBlueIdx=curentBlueIdx+move;
				if(b3Visit.length>nextBlueIdx){//아직 라인 안이고
					if(b3Visit[nextBlueIdx]==false){//가려는곳이 false면 
						if(nextBlueIdx==b3Visit.length-1){//만약 가려는곳이 센터전환점이면
							if(crVisit[0]==false){//아무도 방문 안되어있으면 
								score+=blueline3[nextBlueIdx];//스코어 획득
								currnetScore=blueline3[nextBlueIdx];
								horselist[horseIdx].centerlineIdx = 0;
								horselist[horseIdx].line="center";//센터라인에 올림
								b3Visit[curentBlueIdx] = false;//떠남
							}
						}else{//센터 전환점이 아니면
							horselist[horseIdx].bluelineIdx = nextBlueIdx;
							horselist[horseIdx].line="blue";	
							score+=blueline3[nextBlueIdx];//스코어 획득
							currnetScore=blueline3[nextBlueIdx];
							b3Visit[curentBlueIdx] = false;//떠남
							b3Visit[nextBlueIdx]=true;
						}
					}//가려는곳이 false가 아니면 안간다.
				}else {//라인밖이면 센터라인에 갈 수 있는지?
					int centerIdx = curentBlueIdx+move-(b3Visit.length-1);
					if(centerIdx<crVisit.length){//센터 영역 안에 들어갈 수 있으며
						if(crVisit[centerIdx]==false){//그 자리에 갈 수 있으면
							b3Visit[curentBlueIdx] = false;//현재자리 false
							crVisit[centerIdx] = true;
							score+=centerRedline[centerIdx];//스코어 획득
							currnetScore=centerRedline[centerIdx];
							horselist[horseIdx].centerlineIdx=centerIdx;
							horselist[horseIdx].line="center"; //센터에 올림
						}
					}else {//센터 영역 넘어가버리면
						b3Visit[curentBlueIdx]=false;//그냥 떠나버린다.
						arriveHorse[horseIdx]=true;//도착!
					}
				}
				if(blueline3[curentBlueIdx]==30){//시작 위치인경우는 red에서도 떠남처리필요
					rVisit[blueline3Idx]=false;//떠남처리
				}
			}
			break;
		case "center"://마지막 센터라인이면 
			int currentCenterIdx = horselist[horseIdx].centerlineIdx;
			int nextCenterIdx = currentCenterIdx+move;
			if(crVisit.length>nextCenterIdx){//영역 안에 있으며 
				if(crVisit[nextCenterIdx]==false){//갈 수 있는데
					if(nextCenterIdx==(crVisit.length-1)){//마지막지점에 가려한다면
						if(rVisit[rVisit.length-1]==false){//마지막에 아무도 없을 때만
							score+= centerRedline[nextCenterIdx];
							currnetScore=centerRedline[nextCenterIdx];
							crVisit[currentCenterIdx] = false;
							crVisit[nextCenterIdx] = true;
							rVisit[rVisit.length-1] = true;
							horselist[horseIdx].centerlineIdx=nextCenterIdx;
						}
					}else {
						score+= centerRedline[nextCenterIdx];
						currnetScore=centerRedline[nextCenterIdx];
						crVisit[currentCenterIdx] = false;
						crVisit[nextCenterIdx] = true;
						horselist[horseIdx].centerlineIdx=nextCenterIdx;
					}
				}
			}else {//영역 넘어가버리면
				crVisit[currentCenterIdx] = false;//얜 떠나고 끝남
				arriveHorse[horseIdx]=true;//도착!
				if(currentCenterIdx==(crVisit.length-1)){//마지막 값이면
					rVisit[rVisit.length-1]=false;
				}
			}
			break;
		}
		//		System.out.println("이동후");
		//		System.out.println("얻은 스코어"+currnetScore);
		//		System.out.println(horselist[horseIdx].line+"위에있음");
		return ;
	}

	private static void setData() {
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<10;i++) dice[i] = sc.nextInt();//주사위
	}
}
