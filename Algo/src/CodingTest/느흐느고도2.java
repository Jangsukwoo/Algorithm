package CodingTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class 느흐느고도2 {
	static int target;
	static int minPush = Integer.MAX_VALUE;
	static int justPush;
	static boolean[] buttons = new boolean[10];
	static boolean[] channel = new boolean[1000001];
	static Queue<int[]> q;
	static HashSet<Integer> possibleButtons = new LinkedHashSet<Integer>();
	public static void main(String[] args) {
		System.out.println(solution(5457, new int[] {6,7,8}));
	}
    public static int solution(int page, int[] broken){
    	Arrays.fill(buttons,true); //버튼 전부 true
		int breakButtonSize = broken.length;
		target = page;
		for(int i=0;i<breakButtonSize;i++){
			int button = broken[i];
			buttons[button] = false;
		}

		for(int i=0;i<10;i++) {
			if(buttons[i]) possibleButtons.add(i);
		}
		bfs(); //bfs
		exceptionConditionCheck();
        return minPush;
    }
	private static void bfs() {
		q = new LinkedList<int[]>();
		insertQueue(new int[]{target,0});
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
	private static void insertQueue(int[] info) {
		q.add(info);//누른 횟수
		channel[info[0]] = true;
	}
	private static void exceptionConditionCheck() { //100이면  그냥 안눌러도 되니까 마지막 검증
		if(target==100) minPush=0;
		minPush = Math.min(Math.abs(target-100),minPush);
	}
	private static boolean justPushCheck(int currentNumber) {
		String channelNumber = Integer.toString(currentNumber);
		for(int i=0;i<channelNumber.length();i++){
			if(possibleButtons.contains(Character.getNumericValue(channelNumber.charAt(i)))) continue;
			else return false;
		}
		return true;
	}
}
