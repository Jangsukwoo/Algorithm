package CodingTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 느흐느TEST {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static boolean[][] visit;
	static int answer;
	static int area;
	static int N;
	static ArrayList<Integer> answerList = new ArrayList<Integer>();

	private static void solution(int sizeOfMatrix, int[][] matrix) {
		answer=0;
		N = sizeOfMatrix;
		visit= new boolean[N][N];
		for(int row=0;row<sizeOfMatrix;row++) {
			for(int col=0;col<sizeOfMatrix;col++) {
				if(visit[row][col]==false && matrix[row][col]==1) {
					answer++;
					area=1;
					visit[row][col] = true;
					dfs(row,col,matrix);
					answerList.add(area);
				}
			}
		}
		System.out.println(answer);
		Collections.sort(answerList);
		for(int i=0;i<answerList.size();i++) {
			System.out.print(answerList.get(i)+" ");
		}
	}
	private static void dfs(int row, int col, int[][] matrix) {
		for(int dir=0;dir<4;dir++) {
			int nr= row+dr[dir];
			int nc= col+dc[dir];
			if(rangeCheck(nr,nc) && visit[nr][nc]==false && matrix[nr][nc]==1) {
				visit[nr][nc]=true;
				area++;
				dfs(nr,nc,matrix);
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	  private static class InputData {
		    int sizeOfMatrix;
		    int[][] matrix;
		  }

		  private static InputData processStdin() {
		    InputData inputData = new InputData();

		    try (Scanner scanner = new Scanner(System.in)) {
		      inputData.sizeOfMatrix = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));      
		      
		      inputData.matrix = new int[inputData.sizeOfMatrix][inputData.sizeOfMatrix];
		      for (int i = 0; i < inputData.sizeOfMatrix; i++) {
		        String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
		        for (int j = 0; j < inputData.sizeOfMatrix; j++) {
		          inputData.matrix[i][j] = Integer.parseInt(buf[j]);
		        }
		      }
		    } catch (Exception e) {
		      throw e;
		    }

		    return inputData;
		  }

		  public static void main(String[] args) throws Exception {
		    InputData inputData = processStdin();

		    solution(inputData.sizeOfMatrix, inputData.matrix);
		  }
}
