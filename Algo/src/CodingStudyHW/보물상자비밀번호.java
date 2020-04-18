package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;


/*
 * 19:35 시작
 * 차근차근 풀자 ㅎㅎ
 * 보물 상자 뚜껑은 시계방향으로 돌릴 수 있다.
 * 4개의 변을 가진 보물상자가 있는데
 * 시계방향으로 돌린다.
 * 돌리는건 변에 적힌 숫자의 개수만큼 돌리면 될듯.
 * 변에 적혀있는 숫자는 16진수.
 * 
 * 18:09 끝
 */
public class 보물상자비밀번호 {
	static int T;
	static int N,K;
	static char[] treasureBox;
	static HashSet<String> hexaSet;
	static ArrayList<Long> longList;
	static int rotate;
	static int spin;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			simulation();
			System.out.println("#"+testcase+" "+longList.get(K-1));
		}
	}
	private static void simulation() {
		for(int spin=0;spin<rotate;spin++){
			setHexaData();
			rotate();
		}
		Collections.sort(longList,new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return -Long.compare(o1,o2);
			}
		});
	}
	/*
	 * 
1
12 10
1B3B3B81F75E
	 */
	private static void setHexaData(){
		//풀면서 안 사실
		//radix는 기수(진법)이다.
		String readHexa="";
		for(int i=0;i<rotate;i++) readHexa+=treasureBox[i];
		if(!hexaSet.contains(readHexa)) {
			hexaSet.add(readHexa);
			longList.add(Long.parseLong(readHexa,16));
			readHexa="";
		}
		for(int i=rotate;i<(rotate*2);i++) readHexa+=treasureBox[i];
		if(!hexaSet.contains(readHexa)) {
			hexaSet.add(readHexa);
			longList.add(Long.parseLong(readHexa,16));
			readHexa="";
		}
		for(int i=rotate*2;i<(rotate*3);i++) readHexa+=treasureBox[i];
		if(!hexaSet.contains(readHexa)) {
			hexaSet.add(readHexa);
			longList.add(Long.parseLong(readHexa,16));
			readHexa="";
		}
		for(int i=rotate*3;i<(rotate*4);i++) readHexa+=treasureBox[i];
		if(!hexaSet.contains(readHexa)) {
			hexaSet.add(readHexa);
			longList.add(Long.parseLong(readHexa,16));
			readHexa="";
		}
	}

	private static void rotate() {
		char temp = treasureBox[N-1];
		for(int i=N-1;i>0;i--) treasureBox[i]=treasureBox[i-1];
		treasureBox[0] = temp;
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		rotate = N/4;
		spin=0;
		treasureBox = new char[N];
		treasureBox= br.readLine().toCharArray();
		hexaSet = new HashSet<String>();
		longList = new ArrayList<Long>();
	}
}
