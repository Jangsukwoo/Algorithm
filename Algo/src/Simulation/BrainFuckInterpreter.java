package Simulation;

import java.util.Scanner;
import java.util.Stack;

/*
 * 4:50~5:30
 * 6:00~
 * 
 * 정수를 담는 1개의 배열
 * 이 배열의 칸 하나를 가리키는 포인터
 * 명령구성
 * - : 포인터가 가리키는 숫자 1 감소
 * + : 포인터가 가리키는 숫자 1 증가
 * < : 포인터 왼쪽 이동
 * > : 포인터 오른쪽 이동
 * [ : 포인터가 가리키는 숫자가 0이면 [과 짝을 이루는 ]로 점프
 * ] : 포인터가 가리키는 숫자가 0이 아니면 ]과 짝을 이루는 [로 점프
 * . : 포인터가 기리키는 숫자 출력
 * , : 문자 하나를 읽고 포인터가 가리키는 곳에 저장. 입력의 마지막(EOF)인 경우 255 저장
 * 단, 값 저장시 2^8 (256) 모듈러 연산으로 256을 넘지않도록 삽입
 * 
 * 첫번째 명령부터 순차적으로 수행하고
 * 수행할 명령이 없으면 프로그램이 종료된다.
 * 데이터 배열의 값은 0으로 초기화되어있고 포인터가 가리키는 칸은 0이다.
 * 
 * '[' 의 개수 - ']'의 개수가 0이거나 큰 값으로 종료되면 잘 짜여진 프로그램이다.
 * 이 문제는 이 프로그램이 무한루프에 빠지는지 안빠지는지 판단하는 코드를 짜면된다.
 * 주의할 점은 루프의 중첩상태를 신경써야한다.
 * 무한루프에 빠진 대괄호의 안쪽에 괄호들이 있을 수 있기 떄문.
 * 무한루프는 단 한곳에서 빠지고
 * 그 지점을 정확히 찾아야한다.
 * 
 * ~10:00 끝
 * 엄청 오래걸렸다.. 
 * 난 확실히 시뮬레이션 구현에 약하다.
 * 막상 풀고다니
 * 정말 문제가 시키는 '그대로' 풀면 된다.
 * 문제가 하라는대로, 조건대로 충실하게 푸는 훈련이 많이 필요하다고 느꼈다.
 * 시뮬레이션 문제를 많이 풀어보면서 요구사항에 익숙해지는 연습을 하자.
 * 문제의 요구사항을 빠르게 구현할 수 있도록 해야겠다.
 */
class Bracket{
	int left;
	int right;
	public Bracket(int l,int r){
		left=l;
		right =r;
	}
}
public class BrainFuckInterpreter {
	static int memorySize;
	static int codeSize;
	static int inputSize;
	static int memoryPointer,inputPointer,codePointer;
	static int cnt;
	static int maxPointer;
	static int[] memory;
	static char[] code;
	static char[] input;
	static Bracket[] brackets;
	static StringBuilder sb = new StringBuilder();
	static boolean exit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++){
			
			//입력
			Stack<Integer> bracketStack = new Stack<Integer>();
			memorySize = sc.nextInt();
			codeSize = sc.nextInt();
			inputSize = sc.nextInt();
			memory = new int[memorySize];
			code = new char[codeSize];
			input = new char[inputSize];
			memoryPointer = inputPointer = codePointer = 0;
			exit = false;
			cnt=0;
			maxPointer=0;
			sc.nextLine();
			String readLine  = sc.nextLine();
			code = readLine.toCharArray();
			brackets = new Bracket[code.length];
			for(int i=0;i<code.length;i++){
				brackets[i] = new Bracket(0,0);
				//괄호의 인덱스 정보를 이렇게 관리하는 방법은 처음 써봤는데
				//잘 작동했다.
				if(code[i]=='['){
					bracketStack.add(i);
					brackets[i].left=i;
				}else if(code[i]==']'){
					int left = bracketStack.pop();
					brackets[i].right = i;
					brackets[i].left = left;
					brackets[left].right = i;
				}
			}
			readLine = sc.nextLine();
			input = readLine.toCharArray();
			
			
			//처리
			interPreter();

			//출력
			if(exit) sb.append("Terminates"+"\n");
			else sb.append("Loops"+" "+brackets[maxPointer].left+" "+brackets[maxPointer].right+"\n");
		}
		System.out.println(sb.toString());
	}
	private static void interPreter(){
		while(true){
			//System.out.println("MemoryPointer"+memoryPointer);
			//System.out.println(codePointer);
			//System.out.println(memory[0]+" "+memory[1]+" "+memory[2]);
			switch (code[codePointer]){
			case '-':		
				memory[memoryPointer]-=1; //포인터가 가리키는 숫자 -1
				memory[memoryPointer]%=256; //모듈러
				codePointer++;
				break;
			case '+':	
				memory[memoryPointer]+=1; //포인터가 가리키는 숫자 +1
				memory[memoryPointer]%=256; //모듈러
				codePointer++;
				break;
			case '<': 
				memoryPointer--;
				codePointer++;
				//-1이면 반대편으로 넘어간다.
				if(memoryPointer==-1) memoryPointer=memorySize-1;
				break;
			case '>':	
				memoryPointer++;
				codePointer++;
				//memorySize면 반대편으로 넘어간다.
				if(memoryPointer==memorySize) memoryPointer=0;
				break;
			case '[': 
				if(memory[memoryPointer]==0){//0이면 ]로점프
					codePointer = brackets[codePointer].right;
				}else codePointer++;
				break;
			case ']':		
				if(memory[memoryPointer]!=0){//0이아니면 [로 점프
					codePointer = brackets[codePointer].left;
				}else codePointer++;
				break;
			case '.'://포인터가 가리키는 숫자 출력..?	은 무시
				codePointer++;
				break;
			case ',':			
				if(inputPointer==inputSize) {
					memory[memoryPointer]=255;
				}
				else {
					memory[memoryPointer]=input[inputPointer];
					inputPointer++;
				}
				codePointer++;
				break;

			}
			//중첩 대괄호중 가장 바깥 대괄호에 의해 무한루프에 빠졌을 수 있으니 maxPointer 지정
			if(codePointer>maxPointer) maxPointer = codePointer;		
			
			if(codePointer==codeSize) {
				exit=true;
				break;
			}
			cnt++; //명령 수행 카운트, 5천만번이 넘어도 안끝났으면 무한루프에 빠져있는 것이다.
			
			//한참 고민 끝에 대회 사이트에 찾아가 시크릿 케이스를 돌려보니
			//무한루프 대괄호 인덱스를이상하게 찾는 것을 발견
			//연산이 덜됐음을 깨닫고 다시 코드를 보니 5천만이아니라 5백만까지로 조건이 걸려있었다....
			//이것을 찾는 데도 시간이 많이 걸렸으니
			//빠르게푸는 것도 중요하지만 정확하게, 꼼꼼하게 푸는 태도의 중요성도 느꼈다.
			//정확성에도 신경을 쓰자.
			if(cnt>=50000001){ //<- 요놈이 5백만으로 되어있었다 ㅠㅠ
				exit=false;
				break;
			}
		}
	}
}
