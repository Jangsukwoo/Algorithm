package CodingStudy;
/*
 * N,M,K
 * 40,40,100
 * N,M : 노트북 세로, 가로
 * K : 스티커 개수
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 스티커붙이기 {
	static int N,M,K;
	static int[][] notebook;
	static Sticker[] stickers;
	static int stickerCnt;
	static class Sticker{
		int rowSize;
		int colSize;
		int[][] degree0;
		int[][] degree90;
		int[][] degree180;
		int[][] degree270;
		//시계방향으로
		public Sticker(int rowSize, int colSize) {
			this.rowSize = rowSize;
			this.colSize = colSize;
		}
		public void make4KindsVersionFromRotation() {
			degree90 = new int[colSize][rowSize];
			degree180 = new int[rowSize][colSize];
			degree270 = new int[colSize][rowSize];
			//2 5 
			for(int row=0;row<rowSize;row++){
				for(int col=0;col<colSize;col++){
					degree90[col][(rowSize-1)-row] = degree0[row][col];
				}
			}

			for(int row=0;row<colSize;row++) {
				for(int col=0;col<rowSize;col++){
					degree180[col][(colSize-1)-row] = degree90[row][col];
				}
			}

			for(int row=0;row<rowSize;row++){
				for(int col=0;col<colSize;col++){
					degree270[col][(rowSize-1)-row] = degree180[row][col];
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		attach();
		output();
	}
	private static void output() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				if(notebook[row][col]==1) stickerCnt++;
			}
		}
		System.out.println(stickerCnt);
	}
	private static void attach() {
		for(int stickerNo=0; stickerNo<K; stickerNo++){//첫번째 스티커부터 확인 
			for(int rotateNo=0;rotateNo<4;rotateNo++){
				if(checkAttach(stickerNo,rotateNo)) break;//붙였으면 break;
			}
		}
	}
	private static boolean checkAttach(int stickerNumber, int rotateNumber){
		switch (rotateNumber) {
		case 0:
			if(tryAttach(stickers[stickerNumber].degree0)) return true;
			break;
		case 1:
			if(tryAttach(stickers[stickerNumber].degree90)) return true;
			break;
		case 2:
			if(tryAttach(stickers[stickerNumber].degree180)) return true;
			break;
		case 3:
			if(tryAttach(stickers[stickerNumber].degree270)) return true;
			break;
		}
		return false;
	}
	private static boolean tryAttach(int[][] degree) {
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++){
				if((row+(degree.length-1))<N && (col+(degree[0].length-1))<M){//붙이기가 가능한 영역 안에서만
					//붙이기가 가능한가?
					boolean possible = true;
					for(int stickerRow=0;stickerRow<degree.length; stickerRow++){
						for(int stickerCol=0;stickerCol<degree[0].length;stickerCol++){
							if(notebook[row+stickerRow][col+stickerCol]==1 && degree[stickerRow][stickerCol]==1) {
								possible=false;
								break;
							}
						}
						if(possible==false) break; 
					}
					if(possible){//붙이기가 가능하면
						for(int stickerRow=0;stickerRow<degree.length; stickerRow++){
							for(int stickerCol=0;stickerCol<degree[0].length;stickerCol++){
								if(degree[stickerRow][stickerCol]==1) notebook[row+stickerRow][col+stickerCol]=1;
							}
						}
						return true; //함수 끝내버림
					}
				}
			}
		}//맨왼쪽 맨 위캇부터 확인 
		return false;//위에서 안끝났으면 false
	}
	private static void setData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		notebook = new int[N][M];
		stickers = new Sticker[K];
		for(int i=0,rowSize,colSize;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			stickers[i] = new Sticker(rowSize, colSize);
			stickers[i].degree0 = new int[rowSize][colSize];
			//스티커 입력 받은 후 
			for(int row=0;row<rowSize;row++) {
				st = new StringTokenizer(br.readLine());
				for(int col=0;col<colSize;col++) {
					stickers[i].degree0[row][col] = Integer.parseInt(st.nextToken());
				}
			}//스티커 입력 받았으면 4가지 버전 만들어 둔다.
			stickers[i].make4KindsVersionFromRotation();
		}
	}
}
