package Samsung;

import java.io.FileInputStream;
import java.util.Scanner;

public class 상시_야구공 {
	static boolean[] visit = new boolean[9];
	static int[] battingorder;
	static int[][] player;
	static int count = 0;
	static int inning, score, batter, out, maxscore, batting;
	static int[] base;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		inning = sc.nextInt();
		player = new int[inning][9];

		for (int i = 0; i < inning; i++)
			for (int j = 0; j < 9; j++)
				player[i][j] = sc.nextInt();

		battingorder = new int[9]; // 타순

		battingorder[3] = 0;

		permutation(0); // 순열
		System.out.println(maxscore);
	}

	private static void permutation(int cur) { // 8!
		if (cur == 9) { ////
			score = 0;
			batter = 0;

			base = new int[3];
			for (int in = 0; in < inning; in++) {
				out = 0;

				while (out != 3) {
					batting = player[in][battingorder[(batter % 9)]];
					switch (batting) {
					case 1:
					case 2:
					case 3:
						for (int i = 0; i < batting; i++) {
							if (base[2] == 1) {
								score += 1;
							}
							base[2] = base[1];
							base[1] = base[0];
							base[0] = 0;
						}
						base[batting - 1] = 1;
						break;
					case 4:
						for (int i = 0; i < batting; i++) {
							if (base[2] == 1) {
								score += 1;
							}
							base[2] = base[1];
							base[1] = base[0];
							base[0] = 0;
						}
						score += 1;
						break;
					case 0:
						out++;
						break;
					}
					batter++;
				}
				base = new int[3];
			}

			if (score >= maxscore)
				maxscore = score;
		} else if (cur == 3) {
			permutation(cur + 1);
		} else {
			for (int i = 1; i < 9; i++) {
				if (visit[i] == false) {
					visit[i] = true;
					battingorder[cur] = i;
					permutation(cur + 1);
					visit[i] = false;
				}
			}
		}
	}
}
