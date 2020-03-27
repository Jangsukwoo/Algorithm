package CodingStudy;

import java.util.Arrays;
import java.util.Scanner;
/*
 * Palindrome
 */
public class 팰린드롬만들기 {
	static char[] name;
	static boolean possible;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String read = sc.nextLine();
		name = read.toCharArray();
		Arrays.sort(name);
		possible = true;
		makePalindrome();
		if(possible) {
			String palindrome = "";
			for(int i=0;i<name.length;i++) {
				palindrome+=name[i];
			}
			System.out.println(palindrome);
		}else System.out.println("I'm Sorry Hansoo");
	}
	private static void makePalindrome(){
		int lastIndex = name.length-1;
		System.out.println("검사하려는 문자열");
		System.out.println(Arrays.toString(name));
		for(int startIndex=0;startIndex<(name.length/2);startIndex++){
			if(name[startIndex]==name[lastIndex]) {
				System.out.println("라스트인덱스"+lastIndex);
				lastIndex--;
				continue;
			}
			else{//만약 같지 않다면
				System.out.println("같지않음");
				System.out.println("스타트인덱스"+startIndex);
				System.out.println("라스트인데긋"+lastIndex);
				boolean swap = false;
				for(int i=lastIndex;i>startIndex;i--){
					//뒤에서부터 보는데
					if(name[startIndex]==name[i]){//스왑할 수 있는 문자를 찾았다면
						char tmp = name[lastIndex];
						name[lastIndex] = name[i];
						name[i] = tmp;
						swap = true;
						System.out.println(Arrays.toString(name));
						break;
					}
				}
				if(swap){//스왑에 성공했다면 
					lastIndex--;
				}else {//스왑의 실패 했으면
					System.out.println("스왑실패");
					possible = false;
					return;//불가능하다고 바꾼 후 끝낸다.
				}
			}
		}
	}
}
