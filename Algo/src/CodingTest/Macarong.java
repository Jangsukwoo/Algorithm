package CodingTest;
/*
 * 현카
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Macarong {
	class Solution {
		public String[] solution(int[][] macaron) {
			String[] answer = new String[6];
			int[][] arr = new int[6][6];
			int[][] visit = new int[6][6];
			int c, color, t = 0, nr, nc;
			List<int[]> list1 = new ArrayList<>();
			List<int[]> list2 = new ArrayList<>();
			int[] cur;
			int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
			LinkedList<int[]> queue = new LinkedList<>();
			for(int[] ma : macaron) {
				c = ma[0] - 1;
				color = ma[1];
				for(int i = 0; i < 6; i++) {
					if(i == 5 || arr[i + 1][c] != 0) {
						arr[i][c] = (char)(color);
						break;
					}
				}
				while(true) {
					t++;
					list2.clear();
					for(int i = 0; i < 6; i++) {
						for(int j = 0; j < 6; j++) {
							if(arr[i][j] != 0 && visit[i][j] < t) {
								list1.clear();
								queue.clear();
								queue.offer(new int[] {i, j});
								visit[i][j] = t;
								list1.add(new int[] {i, j});
								while(!queue.isEmpty()) {
									cur = queue.poll();
									for(int k = 0; k < 4; k++) {
										nr = cur[0] + d[k][0];
										nc = cur[1] + d[k][1];
										if(nr >= 0 && nr < 6 && nc >= 0 && nc < 6 && visit[nr][nc] < t && arr[nr][nc] == arr[i][j]) {
											queue.offer(new int[] {nr, nc});
											visit[nr][nc] = t;
											list1.add(new int[]{nr, nc});
										}
									}
								}
								if(list1.size() >= 3) {
									list2.addAll(list1);
								}
							}
						}
					}
					if(list2.size() == 0) {
						break;
					}
					for(int[] loc : list2) {
						arr[loc[0]][loc[1]] = 0;
					}
					for(int i = 0; i < 6; i++) {
						for(int j = 5; j > 0; j--) {
							if(arr[j][i] != 0) {
								for(int k = j; k < 6; k++) {
									if(k == 5 || arr[k + 1][i] != 0) {
										arr[k][i] = arr[j][i];
										if(k != j) {
											arr[j][i] = 0;
										}
										break;
									}
								}
							}
						}
					}
				}
			}
			for(int i = 0; i < 6; i++) {
				answer[i] = "";
				for(int j = 0; j < 6; j++) {
					answer[i] += arr[i][j];
				}
			}
			return answer;
		}
	}
}
