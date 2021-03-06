package CodingTest;

import java.util.Scanner;


public class 느흐느20_2 {
	static int answer=0;
	private static void solution(int day, int width, int[][] blocks) {
		/*
		 * 오전에 벽돌 쌓고
		 * 오후에 붓는다.
		 */		
		answer=0;
		int maxIdx=0;
		int maxHeight=0;
		int[] workSpace = new int[width];
		/*
		 * 초기상태 정의
		 */
		
		for(int d=0;d<day;d++) {
			for(int i=0;i<width;i++) workSpace[i]+=blocks[d][i];
			
			for(int i=0;i<width;i++) { //제일 높은곳 찾기
				if(maxHeight<workSpace[i]) {
					maxHeight = workSpace[i];
					maxIdx = i;
				}
			}
			leftRecur(maxIdx,workSpace,width);
			rightRecur(maxIdx,workSpace,width);
		}
		System.out.println(answer);
	}
/*
1
6
6 2 11 0 3 5
 */
	private static void leftRecur(int maxIdx, int[] workSpace, int width) {
		if(maxIdx-1==-1) return;
		int leftMaxHeight = 0;
		int leftMaxHeightIdx=maxIdx;
		for(int i=maxIdx-1;i>=0;i--) {
			if(workSpace[i]>=leftMaxHeight) {
				leftMaxHeight = workSpace[i];
				leftMaxHeightIdx = i;
			}
		}//왼쪽제일 높은거 찾기
		for(int i=maxIdx-1;i>leftMaxHeightIdx;i--) {
			answer+=leftMaxHeight-workSpace[i];
			workSpace[i]=leftMaxHeight;
		}
		leftRecur(leftMaxHeightIdx, workSpace, width);
	}
	private static void rightRecur(int maxIdx, int[] workSpace, int width) {
		if(maxIdx+1==width) return;
		int rightMaxHeight = 0;
		int rightMaxHeightIdx=maxIdx;
		for(int i=maxIdx+1;i<width;i++) {
			if(workSpace[i]>=rightMaxHeight) {
				rightMaxHeight = workSpace[i];
				rightMaxHeightIdx = i;
			}
		}//오른쪽 기준 제일 높은거 찾기
		for(int i=maxIdx+1;i<rightMaxHeightIdx;i++) {
			answer+=rightMaxHeight-workSpace[i];
			workSpace[i]=rightMaxHeight;
		}
		rightRecur(rightMaxHeightIdx, workSpace, width);
	}


	private static class InputData {
		int day;
		int width;
		int[][] blocks;
	}

	private static InputData processStdin() {
		InputData inputData = new InputData();

		try (Scanner scanner = new Scanner(System.in)) {
			inputData.day = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));      
			inputData.width = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

			inputData.blocks = new int[inputData.day][inputData.width];
			for (int i = 0; i < inputData.day; i++) {
				String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
				for (int j = 0; j < inputData.width; j++) {
					inputData.blocks[i][j] = Integer.parseInt(buf[j]);
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return inputData;
	}

	public static void main(String[] args) throws Exception {
		InputData inputData = processStdin();

		solution(inputData.day, inputData.width, inputData.blocks);
	}
}