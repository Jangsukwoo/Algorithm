import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
//0,1,2,3 - 상하좌우
class Atomic{
    int row;
    int col;
    int dir;
    int energy;
    Atomic(int r, int c, int d, int e){
        row=r;
        col=c;
        dir=d;
        energy=e;
    }
    @Override
    public String toString() {
        return "Atomic [row=" + row + ", col=" + col + ", dir=" + dir + ", energy=" + energy + "]";
    }
     
}
 
 
public class 원자소멸 {
    static int T,N,sumEnergy;
    static Atomic[] atomicQueue = new Atomic[1001];
    static int[][] testMap;
    static int dr[] = {1,-1,0,0};
    static int dc[] = {0,0,-1,1};
	static int front = 0;
	static int rear = 0;
	static int atomicQueueSize=0;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        testMap = new int[4001][4001];
        for(int testcase=1;testcase<=T;testcase++){
            N = sc.nextInt(); 
            front=0;
            rear=0;
            atomicQueueSize=0;
            for(int i=0;i<N;i++) {         	
                int x = (sc.nextInt()+1000)*2; //열
                int y = (sc.nextInt()+1000)*2; //행
                int dir = sc.nextInt();
                int K = sc.nextInt();              
                push(new Atomic(y,x,dir,K));
            }    
            sumEnergy=0;
            
            Move();
            System.out.println("#"+testcase+" "+sumEnergy);
        }
    }
    private static void Move() {
 
        while(!atomicQueueisempty()){
             //맵 다시
            int atomequeueSize = atomicQueueSize;
            for(int i=0;i<atomequeueSize;i++) {
 
                Atomic currentAtomic1 = pop();   
                int nr = currentAtomic1.row+dr[currentAtomic1.dir];
                int nc = currentAtomic1.col+dc[currentAtomic1.dir];     
                                
                if(rangeCheck(nr,nc)) { //이동은 성공
                     
                    //
                    if(testMap[nr][nc]==0) {//이동한 곳이 새 땅이면
                         
                         
                        push(new Atomic(nr,nc,currentAtomic1.dir,currentAtomic1.energy));//그대로 넣음
                        testMap[nr][nc]+=1;
                    }
                     
                    else if(testMap[nr][nc]>=1){//누군가 밟은곳이라면 넣지않고 에너지 넣고 skip
                     
                        sumEnergy += currentAtomic1.energy;
                        testMap[nr][nc]+=1;
                    }
                }
            }//처음이동 
             
            int newatomequeueSize = atomicQueueSize;//잔류 원자
             
            //생존 check
            for(int i=0;i<newatomequeueSize;i++) {   
                Atomic currentAtomic = pop();    
                if(testMap[currentAtomic.row][currentAtomic.col]==1){//1이면 자기 자신만 밟은거니까     
                     
                    push(new Atomic(currentAtomic.row,currentAtomic.col,currentAtomic.dir,currentAtomic.energy));//그대로
                }
                 
                else if(testMap[currentAtomic.row][currentAtomic.col]>1){//1보다 크면 어떤 원자가 와서 죽은거니까 더함.
 
                    sumEnergy += currentAtomic.energy;
                }
                testMap[currentAtomic.row][currentAtomic.col] = 0;
            }//처음이동 
        }   
    }
    private static boolean rangeCheck(int nr, int nc) {
        if((nr>=0 && nr<4001) && (nc>=0 && nc<4001)) return true; //영역 만족
        return false; //범위 밖
    }

	private static boolean atomicQueueisempty() {
		if(rear==front) return true;
		else return false;
	}
	private static Atomic pop() {
		if(rear!=front) {
			atomicQueueSize--;
			Atomic data = atomicQueue[front];
			front = (front+1)%1001;
			return data;
		}
		return null;
	}
	private static void push(Atomic value) {
		if(rear<=1001) {
			atomicQueueSize++;
			atomicQueue[rear]=value;
			rear = (rear+1)%1001;
		}
	}
}