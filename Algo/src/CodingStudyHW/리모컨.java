package CodingStudyHW;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 21:48 시작
 * 수빈이의 처음 채널은 100번이고
 * 이동하려는 채널은 N이다.
 * 리모컨의 버튼은 0~9가 있고 +와 - 버튼이 있음.
 * 채널 0에서 -를 누른경우는 변하지 않고
 * 채널은 무한대만큼 있음
 * 
 * bfs로 다 봐도 못누른 경우와
 * bfs로 봤을때 구한 버튼 회수와
 * 그냥 100부터 - or + 을 시작했을때의 최소값을 구해보자
 * 
 * 채널 제한을 처음에 50만으로 두었다가
 * 3번 테스트케이스가 왜 저 답이 나오는지 한참 생각하다가
 * 문제를 다시 읽어보니 채널이 무한대까지 있음을 발견하구
 * channel boolean 배열을 백만1까지 제한을 두고 제출했더니 바로 맞아버렸다 ㅎ0ㅎ
 * (어차피 가고싶은 채널은 50만이고 +,- 버튼으로 50만 이상을 누를 일은 없기때문에)
 *  
 *  22:40 끝
 */
public class 리모컨 {
	static int N;
	static int minPush = Integer.MAX_VALUE;
	static int justPush;
	static boolean[] buttons = new boolean[10];
	static boolean[] channel = new boolean[1000001];
	static Queue<int[]> q;
	static HashSet<Integer> possibleButtons = new LinkedHashSet<Integer>();
	public static void main(String[] args) {
		setData();
		bfs();
		exceptionConditionCheck();
		System.out.println(minPush);
	}
	private static void exceptionConditionCheck() {
		if(N==100) minPush=0;
		minPush = Math.min(Math.abs(N-100),minPush);
	}
	private static void bfs() {
		q = new LinkedList<int[]>();
		insertQueue(new int[]{N,0});
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] queueData = q.poll();
				int currentNumber = queueData[0];
				int currentPush = queueData[1];
				if(justPushCheck(currentNumber)){//그냥 누를 수 있으면
					int currentNumberLength = Integer.toString(currentNumber).length();
					minPush = Math.min((currentNumberLength+currentPush),minPush);//알고있는 최소값이랑 현재 채널 번호의 최소값 비교 후 갱신
				}else{
					int nextUpChannel = currentNumber+1;
					if(nextUpChannel<=1000000 && channel[nextUpChannel]==false) insertQueue(new int[] {nextUpChannel,currentPush+1});
					int nextDownChannel = currentNumber-1;
					if(nextDownChannel>=0 && channel[nextDownChannel]==false) insertQueue(new int[] {nextDownChannel,currentPush+1});
				}
			}
		}
	}
	private static boolean justPushCheck(int currentNumber) {
		String channelNumber = Integer.toString(currentNumber);
		for(int i=0;i<channelNumber.length();i++){
			if(possibleButtons.contains(Character.getNumericValue(channelNumber.charAt(i)))) continue;
			else return false;
		}
		return true;
	}
	private static void insertQueue(int[] info) {
		q.add(info);//누른 횟수
		channel[info[0]] = true;
	}
	private static void setData() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Arrays.fill(buttons,true);
		int breakButtonSize = sc.nextInt();
		for(int i=0;i<breakButtonSize;i++){
			int button = sc.nextInt();
			buttons[button] = false;
		}
		for(int i=0;i<10;i++) {
			if(buttons[i]) possibleButtons.add(i);
		}
	}
}
