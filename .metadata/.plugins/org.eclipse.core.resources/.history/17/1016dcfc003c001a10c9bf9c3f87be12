package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * 
NUM 600000000
ADD
END
1
1

QUIT
 */
public class 고스택_1일차 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Stack<Long> goStack = new Stack<>();
	static ArrayList<String> commands;
	static int commandSize;
	static int N;
	static int limit = 1000000000;//10^9
	static boolean error;
	public static void main(String[] args) throws IOException {
		while(true){
			String read = br.readLine();
			if(read.equals("QUIT")) return;
			else {
				commandSize=0;
				commands = new ArrayList<>();
				while(true) {
					if(read.equals("END")) break;
					else {
						commands.add(read);
						commandSize++;
						read = br.readLine();
					}
				}//프로그램 명령 전부 입력
				N = Integer.parseInt(br.readLine());
				goStackSimulation();
				System.out.println();
			}
		}
	}

	private static void goStackSimulation() throws NumberFormatException, IOException {
		for(int testcase=1;testcase<=N;testcase++) {
			goStack = new Stack<>();
			error = false;
			long value = Long.parseLong(br.readLine());
			goStack.add(value);

			for(int command=0;command<commandSize;command++){//명령어의 개수 만큼
				String excute = commands.get(command);
				long data=0;
				long first=0;
				long second=0;
				switch (excute) {

				case "POP":	//스택 가장 위에 숫자 제거		
					if(goStack.size()==0)error=true;
					else goStack.pop();
					break;
				case "INV":	//첫번째 수의 부호를 바꿈
					if(goStack.size()==0)error=true;
					else {
						data = goStack.pop();
						goStack.add(data*-1);
					}
					break;
				case "DUP":	//첫번째 숫자 복사 후 저장
					if(goStack.size()==0)error=true;
					else {
						data = goStack.peek();
						goStack.add(data);
					}
					break;
				case "SWP":	//첫번쨰숫자,두번째 숫자 스왑
					if(goStack.size()<2) error = true;
					else {
						first = goStack.pop();
						second = goStack.pop();
						goStack.add(first);
						goStack.add(second);
					}
					break;
				case "ADD":	//첫번쨰,두번쨰 합
					if(goStack.size()<2) error = true;
					else {
						first = goStack.pop();
						second = goStack.pop();
						if((Math.abs(first+second))>limit) error = true;
						else goStack.add(first+second);
					}
					break;
				case "SUB":	//두번쨰 - 첫번째	
					if(goStack.size()<2) error = true;
					else {
						first = goStack.pop();
						second = goStack.pop();
						if((Math.abs(second-first))>limit) error = true;
						else goStack.add(second-first);
					}
					break;
				case "MUL":	//첫번쨰,두번째 곱	
					if(goStack.size()<2) error = true;
					else {
						first = goStack.pop();
						second = goStack.pop();
						if((Math.abs(second*first))>limit) error = true;
						else goStack.add(second*first);
					}
					break;
				case "DIV":	//두번째 나누기 첫번째 second/first
					if(goStack.size()<2) error = true;
					else {
						first = goStack.pop();
						second = goStack.pop();
						if(first==0) error = true;
						else {
							int minus=0;
							if(first<0) minus++;
							if(second<0) minus++;
							first = Math.abs(first);
							second = Math.abs(second);
							if(minus==1) goStack.add(-(second/first));
							else goStack.add(second/first);
						}
					}
					break;
				case "MOD":	//두번째 모듈러 첫번쨰. 나머지의 부호는 피제수의 부호를 따라감 . 13/5에서 13은 피제수 5는 제수
					if(goStack.size()<2) error = true;
					else {
						first = goStack.pop();
						second = goStack.pop();//피제수
						if(first==0) error = true;
						else {
							goStack.add(second%first);
						}
					}

					break;
				default: //NUM X인 경우
					StringTokenizer st = new StringTokenizer(excute);
					if(st.nextToken().equals("NUM")) {
						long saveValue = Long.parseLong(st.nextToken());
						goStack.add(saveValue);
					}
					break;
				}
				if(error) break;
			}
			if(error) System.out.println("ERROR");
			else{
				if(goStack.size()!=1) System.out.println("ERROR");
				else System.out.println(goStack.pop());
			}
		}
		br.readLine();
	}
}


