package CodingTest;
import java.util.LinkedList;
public class Path {
	public static void main(String[] args) {
	
	}
	public int solution(int[][] board) {
		int N = board.length;
		LinkedList<int[]> queue = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		int min = Integer.MAX_VALUE;
		int[] cur;
		int[][] d = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
		int r, c;
		if(board[0][1] == 0) {
			queue.offer(new int[]{0, 1, 0, 1, 0});
			visit[0][0] = true;
			visit[0][1] = true;
			while (!queue.isEmpty()) {
				cur = queue.poll();
				if (cur[0] == N - 1 && cur[1] == N - 1) {
					min = cur[3] * 100 + cur[4] * 500;
					break;
				}
				if (cur[2] != -1) {
					r = cur[0] + d[cur[2]][0];
					c = cur[1] + d[cur[2]][1];
					if (r >= 0 && r < N && c >= 0 && c < N && board[r][c] == 0 && !visit[r][c]) {
						queue.offer(new int[]{r, c, cur[2], cur[3] + 1, cur[4]});
						visit[r][c] = true;
					}
				}
				for (int i = 0; i < 4; i++) {
					if (i == cur[2]) {
						continue;
					}
					r = cur[0] + d[i][0];
					c = cur[1] + d[i][1];
					if (r >= 0 && r < N && c >= 0 && c < N && board[r][c] == 0 && !visit[r][c]) {
						if (cur[2] == -1) {
							queue.offer(new int[]{r, c, i, cur[3] + 1, cur[4]});
						} else {
							queue.offer(new int[]{r, c, i, cur[3] + 1, cur[4] + 1});
						}
						visit[r][c] = true;
					}
				}
			}
		}
		if(board[1][0] == 0) {
			visit = new boolean[N][N];
			queue.clear();
			queue.offer(new int[]{1, 0, 1, 1, 0});
			visit[0][0] = true;
			visit[1][0] = true;
			while (!queue.isEmpty()) {
				cur = queue.poll();
				if (cur[0] == N - 1 && cur[1] == N - 1) {
					min = Math.min(min, cur[3] * 100 + cur[4] * 500);
					break;
				}
				if (cur[2] != -1) {
					r = cur[0] + d[cur[2]][0];
					c = cur[1] + d[cur[2]][1];
					if (r >= 0 && r < N && c >= 0 && c < N && board[r][c] == 0 && !visit[r][c]) {
						queue.offer(new int[]{r, c, cur[2], cur[3] + 1, cur[4]});
						visit[r][c] = true;
					}
				}
				for (int i = 0; i < 4; i++) {
					if (i == cur[2]) {
						continue;
					}
					r = cur[0] + d[i][0];
					c = cur[1] + d[i][1];
					if (r >= 0 && r < N && c >= 0 && c < N && board[r][c] == 0 && !visit[r][c]) {
						if (cur[2] == -1) {
							queue.offer(new int[]{r, c, i, cur[3] + 1, cur[4]});
						} else {
							queue.offer(new int[]{r, c, i, cur[3] + 1, cur[4] + 1});
						}
						visit[r][c] = true;
					}
				}
			}
		}
		return min;
	}
}
