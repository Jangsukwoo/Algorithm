package CodingTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class FrequencyPriorityQueue {
	static int commandSize;
	static ArrayList<Integer> queueList = new ArrayList<Integer>();
	static int Max;
	static int Maxcount;
	static int[] frequencyCount;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		frequencyCount = new int[101];
		HashSet<Integer> set = new HashSet<Integer>();
		commandSize = sc.nextInt();

		for(int c=0;c<commandSize;c++){
			String command = sc.next();
			switch (command){
			case "enqueue":
				int data = sc.nextInt();
				queueList.add(data);
				frequencyCount[data]=frequencyCount[data]+1;
				break;
			case "dequeue":
				Max = 0;
				Maxcount=0;
				set = new HashSet<Integer>();
				for(int i=1;i<=100;i++) if(Max<frequencyCount[i]) Max = frequencyCount[i]; //빈도검사
				for(int i=1;i<=100;i++) {
					if(Max==frequencyCount[i]) {
						Maxcount++;//카운트
						set.add(i);
					}
				}
				if(Max!=0 && Maxcount>1){//빈도수가 가장 큰게 두개 이상인 경우
					for (int i=0;i<queueList.size();i++){
						if(set.contains(queueList.get(i))){
							System.out.print(queueList.get(i)+" ");					
							frequencyCount[queueList.get(i)]= frequencyCount[queueList.get(i)]-1;
							queueList.remove(i);
							break;
						}
					}
				}else if(Max!=0 && Maxcount==1){//빈도수가 가장큰게 유일한 경우
					for (int i=0;i<queueList.size();i++){
						if(set.contains(queueList.get(i))){
							System.out.print(queueList.get(i)+" ");
							frequencyCount[queueList.get(i)]= frequencyCount[queueList.get(i)]-1;
							queueList.remove(i);
							break;
						}
					}
				}
				else if(Max==0) System.out.print("-1"+" ");
				break;
			}
		}
	}
}
