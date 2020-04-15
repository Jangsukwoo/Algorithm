package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 1~2n까지 정수가 쓰여진 총 2n개의 카드 존재
 * 카드를 n장씩 반띵함
 * 규칙
 * 1.놓여진 카드가 없으면 카드를 냄
 * 2.놓여진 카드가 있으면 마지막에 나온 카드보다 큰 숫자가 적힌 카드를 냄
 * 3.낼 카드가 없으면 상대의 차례로 턴 넘어가고, 낼 카드는 사라짐
 * 
 * 둘 중 한명이라도 패가 없으면 게임 끝
 * 게임 종료시 상대방이 가지고 잇는 카드의 수를 점수로 획득.
 * 
 * 상근이 먼저 시작하고
 * 두 사람은 각자 낼 수 있는 카드 중 가장 작은 숫자를 먼저 내기로 함
 * 
 */
public class 카드캡터상근이 {
	static ArrayList<Integer> sang_guen_cards;
	static ArrayList<Integer> guen_sang_cards;
	static int n;
	static boolean[] cards;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		simulation();
		System.out.println(guen_sang_cards.size());
		System.out.println(sang_guen_cards.size());
	}
	private static void simulation(){
		boolean putCard = false;
		String turn = "sang_guen";//상근이 턴부터 시작
		int putCardNumber = 0;

		while(!sang_guen_cards.isEmpty() && !guen_sang_cards.isEmpty()){ //둘 중하나가 패가 전부 소진 될 때 까지 
			if(putCard){//카드가 놓여있고 
				if(turn=="sang_guen"){//상근이 턴
					boolean possible = false;
					for(int i=0;i<sang_guen_cards.size();i++) {
						if(putCardNumber<sang_guen_cards.get(i)){//놓여진 카드 보다 더 큰 카드면
							putCardNumber = sang_guen_cards.get(i);//카드 낸다.
							sang_guen_cards.remove(i);
							possible = true;
							break;
						}
					}

					if(possible==false){//더 크지 않으면
						//상대 턴으로 교체
						putCard = false;
					}
					turn = "guen_sang";//근상이 턴으로 교체
				}else if(turn=="guen_sang"){//근상이 턴
					boolean possible = false;
					for(int i=0;i<guen_sang_cards.size();i++) {
						if(putCardNumber<guen_sang_cards.get(i)){//놓여진 카드 보다 더 큰 카드면
							putCardNumber = guen_sang_cards.get(i);//카드 낸다.
							guen_sang_cards.remove(i);
							possible = true;
							break;
						}
					}
					if(possible==false){//더 크지 않으면
						//상대 턴으로 교체
						putCard = false;
					}
					turn = "sang_guen";//턴교체 
				}

			}else {
				//놓여진 카드가 없는 경우
				if(turn=="sang_guen"){//상근이 턴
					putCard = true;
					putCardNumber = sang_guen_cards.get(0);//상근이 카드 하나 꺼냄 
					sang_guen_cards.remove(0);
					turn = "guen_sang";//근상이 턴으로 교체
				}else if(turn=="guen_sang"){//근상이 턴
					putCard = true;
					putCardNumber = guen_sang_cards.get(0);//근상이 카드 하나 꺼냄 
					guen_sang_cards.remove(0);
					turn = "sang_guen";//상근이 턴으로 교체
				}
			}
		}
	}
	private static void setData() throws NumberFormatException, IOException {

		n = Integer.parseInt(br.readLine());
		cards = new boolean[(n*2+1)];

		sang_guen_cards = new ArrayList<Integer>();
		guen_sang_cards = new ArrayList<Integer>();

		for(int i=0;i<n;i++) {
			int card = Integer.parseInt(br.readLine());
			cards[card] = true;
		}
		for(int i=1;i<=(n*2);i++) {
			if(cards[i]) sang_guen_cards.add(i);
			else if(cards[i]==false) guen_sang_cards.add(i);
		}
	}
}
