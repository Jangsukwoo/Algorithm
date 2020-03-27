package CodingStudy;

import java.util.Scanner;

/*
 * Palindrome
 * 
 * 투포인터로 했다가 실패 후 
 * 인터넷 참고해서 알파벳 개수에 따라 팰린드롬을 만들 수 있는 가를 판단 후 
 * 만들어주는 방식으로 풀어본다.
 */
public class 팰린드롬만들기 {
	static char[] name;
	static boolean possible;
	static int[] alphabet;
	static String palindrome;
	static char center;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String read = sc.nextLine();
		name = read.toCharArray();
		possible = true;
		makePalindrome();
		if(possible) System.out.println(palindrome);
		else System.out.println("I'm Sorry Hansoo");
	}
	private static void makePalindrome(){
		alphabet = new int[26];
		for(int i=0;i<name.length;i++) {
			int ascii = name[i]-'A';
			alphabet[ascii]+=1;
		}//출현 알파벳 카운팅
		int oddCnt = 0;//알파벳이 홀수개인 개수
		int evenCnt = 0;
		palindrome="";
		for(int i=0;i<alphabet.length;i++){
			if(alphabet[i]%2==0)evenCnt++;
			else oddCnt++;
		}
		if(name.length%2==0 && oddCnt>0
				|| name.length%2==1 && oddCnt>1){
			/*
			 * 1. 만들려는 팰린드롬이 짝수 길인데 oddCnt가 존재하면 불가
			 * 2. 만들려는 팰린드롬이 홀수 길인데 oddCnt가 1개 이상이면 불가  
			 */
			possible = false;
			return;
		}
		//이 조건에 걸리지 않으면 팰린드롬 만들기 가능
		for(int i=0;i<alphabet.length;i++){
			for(int j=0;j<alphabet[i]/2;j++) {
				palindrome+=(char) (i+'A');
			}
		} //앞줄 만들고
		
		if(name.length%2==0) { //만들어야하는 팰린드롬 길이가 짝수면
			for(int i=25;i>=0;i--){ //그냥 뒤에 연달아 붙임
				for(int j=0;j<alphabet[i]/2;j++) {
					palindrome+=(char)(i+'A');
				}
			}
		}else if(name.length%2==1){ //홀수면 센터값이 필요한데 
			for(int i=0;i<alphabet.length;i++){
				if(alphabet[i]%2==1) { //홀수 개인 알파벳을 센터로 지정하고
					center=(char) (i+'A');
				}
			}
			palindrome+=center; //센터를 붙이고
			for(int i=25;i>=0;i--){ //뒤에 연달아 붙인다
				for(int j=0;j<alphabet[i]/2;j++) {
					palindrome+=(char)(i+'A');
				}
			}
		}
	}
}
