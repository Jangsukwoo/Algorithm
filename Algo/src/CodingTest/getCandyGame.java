package CodingTest;

//시간 부족으로 구현 못함 ㅠ


import java.util.ArrayList;
import java.util.Scanner;
class Player{
	int id;
	int candy;
	ArrayList<Integer> following = new ArrayList<Integer>();
	Player(int id) {
		this.id = id;
	}
}
public class getCandyGame {
	static int players;
	static char[] cards;
	static Player[] player;
	static int order;
	static boolean[] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		players = sc.nextInt();
		player = new Player[players];
		for(int i=0;i<players;i++) player[i] = new Player(i);
		sc.nextLine();
		String readLine = sc.nextLine();
		String[] data = readLine.split(" ");
		String[] numData = readLine.split(" ");
		for(int i=0;i<data.length;i++){
			order = (i%players);
			switch (data[i]) {
			case "A":
				visit = new boolean[players];
				BFS(order);
				break;
			case "J":
				visit = new boolean[players];
				
				break;
			case "Q":
				for(int j=0;j<players;j++) {
					player[j].candy = player[j].candy+1;
				}
				break;
			case "K":
				order = (order-1)%players;
				player[order].following.add(Integer.parseInt(numData[i+1]));
				break;

			}
		}
	}
	private static void BFS(int getPlayer) {
		
	}
}
