package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * 리스트 탐색으로 하니 시간초과났음
 * Map으로 도감 두개 만들어서 자료구조 바꾸니 통과 
 * Key-Value라 O(1)
 */
public class 나는야포켓몬마스터이다솜 {
	static int pokemons;
	static int questions;
	static HashMap<String,Integer> pokemonBookSearchName = new HashMap<String, Integer>();//이름으로 찾는 도감
	static HashMap<Integer,String> pokemonBookSearchNum = new HashMap<Integer, String>(); //숫자로 찾는 도감
	static StringBuilder sb = new StringBuilder();
 	public static void main(String[] args) throws IOException{ //리드라인에 대한 IOException 예외처리 
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		//스트림 형태로 받은 후 버퍼
 		StringTokenizer st = new StringTokenizer(br.readLine());
 		//버퍼를 읽고 스트링을 토큰화한다.리드라인은 한 라인 자체를 읽음 
		pokemons = Integer.parseInt(st.nextToken());//한 라인 속에 토큰
		questions =Integer.parseInt(st.nextToken());

		for(int i=0;i<pokemons;i++) {
			String pokemon = br.readLine();
			pokemonBookSearchName.put(pokemon,i+1);
			pokemonBookSearchNum.put(i+1,pokemon);
		}
		for(int q=0;q<questions;q++) {//숫자인지 문자인지 판단할 때 이게 최선인가??. 더 좋은 판단이 있을 것 같음...
			String question = br.readLine();
			char firstData = question.charAt(0);
			if(firstData>='0' && firstData <='9'){//숫자면
				int pokemonNum = Integer.parseInt(question);
				sb.append(pokemonBookSearchNum.get(pokemonNum)+"\n");			
			}else{//문자면
				sb.append(pokemonBookSearchName.get(question)+"\n");	
			}
		}
		System.out.println(sb.toString());
	}
}
