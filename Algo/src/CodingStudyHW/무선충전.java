package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 19:08~
 * 0 1 2 3 4 
 * X 상 우 하 좌 
 * 
1
20 1
2 2 3 2 2 2 2 3 3 4 4 3 2 2 3 3 3 2 2 3
4 4 1 4 4 1 4 4 1 1 1 4 1 4 3 3 3 3 3 3
7 10 3 40


기존에 풀었던 방식이 아닌
새로운 방식으로 시도했는데 런타임 에러잡느라고 한참 걸렸다......ㅠㅠ
32개쨰에서 런타임에러가 났는데
어떤 케이스에서 어떤 부분이 나는건지 당췌 알수 가없었기 떄문이다...

결국 생각끝에 발견한 논리의 오점은
a랑 b 유저가 동시에 같이 서있고
동시에 같은 아이디를 가진 ap에 접속했는데 
ap수가 2개이면
a의 두번째 접속 ap랑 더한게 큰지
b의 두번째 접속 ap랑 더한게 큰지를 확인하려고 
pq 맵에서
poll poll 을 한 후 peek을 접근하려 한다면 에러가 날것이라고 생각해냈고
이것을 고쳐주니 통과되었다....ㅠㅠㅠㅠ 눙물
 */

public class 무선충전 {
	static int T;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] userA,userB;
	static int time;
	static int apSize;
	static AP[] aps;
	static int[] dr = {0,-1,0,1,0};
	static int[] dc = {0,0,1,0,-1};
	static int userAR,userAC;
	static int userBR,userBC;
	static PriorityQueue<AP>[][] chargeMap;
	static int chargeAmount;
	static class AP implements Comparable<AP>{
		int row;
		int col;
		int chargeSize;
		int power;
		int id;
		public AP(int row, int col, int chargeSize, int power, int id) {
			this.row = row;
			this.col = col;
			this.chargeSize = chargeSize;
			this.power = power;
			this.id = id;
		}
		@Override
		public int compareTo(AP o) {
			return -Integer.compare(this.power,o.power);
		}//파워가 센기준
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			installAP();
			moveUser();
			//view();
			System.out.println("#"+testcase+" "+chargeAmount);
		}
	}
	private static void view() {
		for(int row=1;row<=10;row++) {
			for(int col=1;col<=10;col++){
				System.out.print(chargeMap[row][col].size()+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void installAP(){
		for(int ap=0;ap<apSize;ap++) {
			int ar = aps[ap].row;
			int ac = aps[ap].col;
			int c = aps[ap].chargeSize;
			for(int row=1;row<=10;row++) {
				for(int col=1;col<=10;col++) {
					int dist = Math.abs(ar-row) + Math.abs(ac-col);
					if(dist<=c) {
						chargeMap[row][col].add(aps[ap]);
					}
				}
			}
		}
	}

	private static void moveUser() {
		int t = 0;
		while(t<=time){
			userAR+=dr[userA[t]];
			userAC+=dc[userA[t]];
			userBR+=dr[userB[t]];
			userBC+=dc[userB[t]];
			t++;
			int apSizeOfuserA = chargeMap[userAR][userAC].size();
			int apSizeOfuserB = chargeMap[userBR][userBC].size();

			if (apSizeOfuserA>0 && apSizeOfuserB==0) chargeAmount+=chargeMap[userAR][userAC].peek().power;
			else if(apSizeOfuserB>0 && apSizeOfuserA==0) chargeAmount+=chargeMap[userBR][userBC].peek().power;
			else if(apSizeOfuserA>=1 && apSizeOfuserB>=1){//둘다 속한게 여러개일 때 
				if(chargeMap[userAR][userAC].peek().id==chargeMap[userBR][userBC].peek().id){//같은거 접속했는데
					if(apSizeOfuserA==1 && apSizeOfuserB==1){//둘다 이거밖에 충전받을 수 없으면
						chargeAmount+=(chargeMap[userAR][userAC].peek().power);
					}
					else if(apSizeOfuserA>1 && apSizeOfuserB==1) {//a가 속한게 더 많으면
						AP pollAP = chargeMap[userAR][userAC].poll();
						chargeAmount+=chargeMap[userAR][userAC].peek().power;
						chargeAmount+=chargeMap[userBR][userBC].peek().power;
						chargeMap[userAR][userAC].add(pollAP);
					}else if(apSizeOfuserA==1 && apSizeOfuserB>1) {//b가 속한게 더 많으면
						AP pollAP = chargeMap[userBR][userBC].poll();
						chargeAmount+=chargeMap[userAR][userAC].peek().power;
						chargeAmount+=chargeMap[userBR][userBC].peek().power;
						chargeMap[userBR][userBC].add(pollAP);
					}else if(apSizeOfuserA>1 && apSizeOfuserB>1){//둘다 속한게많으면
						chargeAmount+=chargeMap[userAR][userAC].peek().power;
						AP pollAP1 = chargeMap[userAR][userAC].poll();
						int secondApower = chargeMap[userAR][userAC].peek().power;
						chargeMap[userAR][userAC].add(pollAP1);
						
						AP pollAP2 = chargeMap[userBR][userBC].poll();
						int secondBpower = chargeMap[userBR][userBC].peek().power;
						chargeMap[userBR][userBC].add(pollAP2);
						chargeAmount+=Math.max(secondApower,secondBpower);
					}
				}
				else{//같은거에 접속한거 아니니까 최대 충전량 더함 
					chargeAmount+=chargeMap[userAR][userAC].peek().power;
					chargeAmount+=chargeMap[userBR][userBC].peek().power;
				}
			}
		}
	}
	private static void setData() throws IOException{
		st = new StringTokenizer(br.readLine());
		time = Integer.parseInt(st.nextToken());
		apSize = Integer.parseInt(st.nextToken());
		userA = new int[time+1];
		userB = new int[time+1];
		aps= new AP[apSize];
		st = new StringTokenizer(br.readLine());
		for(int t=1;t<=time;t++) userA[t] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int t=1;t<=time;t++) userB[t] = Integer.parseInt(st.nextToken());
		for(int i=0;i<apSize;i++) {
			st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			int chargeSize = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());
			aps[i] = new AP(row, col, chargeSize, power, i);
		}
		userAR = 1;
		userAC = 1;
		userBR = 10;
		userBC = 10;
		chargeAmount=0;
		chargeMap = new PriorityQueue[11][11];
		for(int row=0;row<=10;row++) {
			for(int col=0;col<=10;col++) {
				chargeMap[row][col] = new PriorityQueue<AP>();
			}
		}
	}
}
