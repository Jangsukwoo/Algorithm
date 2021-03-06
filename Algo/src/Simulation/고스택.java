package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * 에러조건
 * 1. 숫자 부족으로 연산을 수행 할 수 없을 때
 * 2. 0으로 나눴을 때
 * 3. 연산 결과의 절대값이 10^9를 넘어갈 때
 * 
 * 음수 나눗셈,나머지 조건
 * 1. 피연산자가 음수일 때 
 * 피연산자의 절대값을 씌우고
 * 피연산자중 음수가 1개일 땐
 * 몫의 부호가 음수 
 * 
 * 2.나머지시 피제수의 부호를 그대로 따라간다.
 * 
 * * 피제수
 * a/b 에서 a가 피제수,b가 제수
 * 
 * ㅠㅠ 디버깅 너무 힘들었던 문제다.. 처음에 문제 이해도 어려웠고.. 조건이행력,구현력이 요구되는 문제..
 * 디버깅에서 발견했던 코드 로직 문제점
 * 1. 곱셈 연산에서 int범위 터져서 값이 이상하게 나왔다.
 * 2. numberPointer 잘못 넣어서 값이 이상하게 나왔다. 뭔생각으로 리스트를 왜 스택처럼 뽑아냈는지... 요구 조건을 제대로 이행하지 못한것임..
 * 3. error를 두번 출력하는것.. 이미 명령어 수행도중에 error를 출력했는데 모든 명령 수행 후 스택 사이즈에 대한 조건 체크에서 또 error를 출력
 * ㅠ ㅡ ㅠ  한 3시간 꽉채웠다.. 
 */
public class 고스택 {
	static Stack<Integer> gostack = new Stack<Integer>();
	static ArrayList<Integer> numbers;
	static ArrayList<String> commands;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){//언제끝날지 모름 
			numbers = new ArrayList<Integer>();
			commands = new ArrayList<String>();
			boolean end = false;
			while(true) {
				String readLine = br.readLine();
				StringTokenizer st = new StringTokenizer(readLine);
				String command = st.nextToken();
				commands.add(command);
				switch (command) {
				case "NUM":
					int value = Integer.parseInt(st.nextToken());
					numbers.add(value);
					break;
				case "QUIT"://아예끝
					return;
				case "END":
					end = true;
					break;
				}
				if(end) break;
			}
			String readLine = br.readLine();
			int N = Integer.parseInt(readLine);
			for(int excute=0;excute<N;excute++){//숫자만큼
				int numberPointer = 0;
				boolean error = false;
				boolean errorPrint = false;
				int first=0;
				int second=0;
				readLine = br.readLine();
				gostack.clear(); //고스택초기화
				gostack.add(Integer.parseInt(readLine));

				for(int i=0;i<commands.size();i++){//명령 하나씩 수행	
					switch (commands.get(i)) {//명령
					case "NUM"://가장 맨 위에 숫자 저장
						gostack.add(numbers.get(numberPointer++));
						break;
					case "POP"://가장 맨위 숫자 제거
						if(gostack.size()==0) error = true;
						else gostack.pop();
						break;
					case "INV"://첫번째 수의 부호를 바꾼다.
						int value = gostack.pop();
						value = -value;
						gostack.add(value);
						break;
					case "DUP"://첫번째 숫자와 똑같은 값을 저장
						gostack.add(gostack.peek());
						break;
					case "SWP"://첫번재 숫자랑 두번재 숫자 위치 스왑
						if(gostack.size()<=1) error = true;
						else {
							first = gostack.pop();
							second = gostack.pop();
							gostack.add(first);
							gostack.add(second);
						}
						break;
						//아래는 연산케이스
					case "ADD"://첫번재 숫자랑 두번째 숫자 더하기
						if(gostack.size()<=1) error = true;
						else {
							first = gostack.pop();
							second = gostack.pop();

							if(Math.abs((first+second))>1000000000) error = true;
							else gostack.add(first+second);
						}
						break;
					case "SUB"://첫번째 숫자랑 두번째 숫자 빼기 (두번째-첫번째)
						if(gostack.size()<=1) error = true;
						else {
							first = gostack.pop();
							second = gostack.pop();
							if(Math.abs((second-first))>1000000000) error = true;
							else gostack.add(second-first);
						}
						break;
					case "MUL"://첫번째 숫자랑 두번째 숫자 곱
						if(gostack.size()<=1) error = true;
						else {
							first = gostack.pop();
							second = gostack.pop();
							//곱셈에서 인트가 터질수 있다..ㅠㅠ 
							if(Math.abs((long)((long)first*(long)second))>1000000000) error = true;
							else gostack.add((first*second));
						}
						break;
					case "DIV"://첫번째 숫자로 두번째 수를 나눈 몫 저장
						if(gostack.size()<=1) error = true;
						else {
							int negativeNumberCount=0;
							first = gostack.pop();
							second = gostack.pop();
							if(first==0) error = true;
							else {
								//음수카운트
								if(first<0) negativeNumberCount++;
								if(second<0) negativeNumberCount++;
								//절대값처리
								first = Math.abs(first);
								second = Math.abs(second);
								first = second/first;
								if(negativeNumberCount==1) first = -first;
								gostack.add(first);
							}
						}
						break;
					case "MOD"://첫번째 숫자로 두번째 숫자를 나눈 나머지 저장
						if(gostack.size()<=1) error = true;
						else {
							boolean check = false;
							first = gostack.pop();
							second = gostack.pop();
							if(first==0) error = true;
							else {
								if(second<0) check=true;
								first = second%first;
								if(check && first>0) first = -first;//피제수가 음수면 음수화
								gostack.add(first);
							}
						}
						break;
					}

					//명령 해보고
					
					if(error) {
						System.out.println("ERROR");	
						errorPrint = true;
						break;
					}
				}
				if(!errorPrint) {
					if(gostack.size()==1) System.out.println(gostack.pop());
					else System.out.println("ERROR");
				}
			}//프로그램수행 끝
			br.readLine();
			System.out.println();
		}
	}
}
