package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 숫자 버튼이 고장나서 몇 개의 숫자 버튼과 곱하기 버튼, 게산 버튼이있음
 * 숫자 X 를 만드는 최소 버튼 수 

약수들을 다 구해둔 뒤 
X의 약수집합을 만듬

X,누른카운트(계산버튼떄문에 1부터시작)
즉 X,1 을 큐에 넣고
나눠가면서 BFS를 수행했다.

1
1 1 1 1 1 1 1 1 1 1
128

1
0 1 1 0 0 1 0 0 0 0
60

3
0 1 1 0 0 1 0 0 0 0
60
1 1 1 1 1 1 1 1 1 1
128
0 1 0 1 0 1 0 1 0 1
128

1
0 0 0 0 0 0 0 0 0 1
891

1
0 0 0 0 0 0 0 0 0 1
793881
 */
public class 지희의고장난계산기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int X;
	static int push;
	static int justPush;
	static int Xsize;
	static int n,r;
	static String[] divisor;
	static ArrayList<Integer> numberlist;
	static HashSet<Integer> numberSet;
	static Queue<int[]> q; //값과 누른 회수 

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();	
			setDivideNumber();
			bfs();
			if(push!=Integer.MAX_VALUE) System.out.println("#"+testcase+" "+push);
			else System.out.println("#"+testcase+" "+"-1");
		}
	}

	private static void setDivideNumber() {
		for(int i=10;i<X;i++){
			if(X%i==0){//나눠 떨어지는 약수면서
				if(justPushCheck(i)){//누를 수 있는 숫자면
					numberlist.add(i);
					numberSet.add(i);
				}
			}
		}
	}

	private static void bfs(){
		while(!q.isEmpty()){//q다 비울 때 까지
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentNumberInfo = q.poll();
				int currentNumber = currentNumberInfo[0];
				int currentPushCount = currentNumberInfo[1];
				if(!justPushCheck(currentNumber)){//누를수 있는 숫자가 아니면 나눈다.
					for(int j=0;j<numberlist.size();j++){
						if(numberlist.get(j)!=1 && numberlist.get(j)!=0){//1과 0이 아닌 숫자에 대해서만
							if(currentNumber%numberlist.get(j)==0){
								int manyPush=0;
								if(numberlist.get(j)>=10) manyPush=Integer.toString(numberlist.get(j)).length()-1;
								int value = currentNumber/numberlist.get(j);
								if(value!=0){
									q.add(new int[] {value,currentPushCount+2+manyPush});//나눴을 때 0이 아니면 큐에 넣어준다.
									//+2는 나눗셈(곱셈)과 숫자를 한번 누른거, + manyPush (약수인 99는 두번 눌러야 하니)
								}
							}
						}
					}
				}else{//그냥 누를 수 있으면 비교
					push = Math.min(justPush+currentPushCount,push);//그냥 누른거+ 눌러온거 
				}
			}
		}
	}

	private static boolean justPushCheck(int currentNumber){
		justPush=0;
		String stringCurrentNumber = Integer.toString(currentNumber);
		for(int i=0;i<stringCurrentNumber.length();i++) {
			//누를 수 있는 숫자가 아니면 포기
			if(!numberSet.contains(Character.getNumericValue(stringCurrentNumber.charAt(i)))) {
				return false;
			}else justPush++;//누를 수 있음 
		}
		return true;
	}


	private static void setData() throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		numberlist = new ArrayList<Integer>();
		numberSet = new LinkedHashSet<Integer>();
		for(int i=0,num;i<10;i++) {
			num=Integer.parseInt(st.nextToken());
			if(num!=0) {
				numberlist.add(i);
				numberSet.add(i);
			}
		}
		st = new StringTokenizer(br.readLine());
		String target = st.nextToken();
		X = Integer.parseInt(target);
		push = Integer.MAX_VALUE;
		n = numberlist.size();
		r = Integer.toString(X).length();
		q = new LinkedList<>();
		q.add(new int[] {X,1});
	}

}
