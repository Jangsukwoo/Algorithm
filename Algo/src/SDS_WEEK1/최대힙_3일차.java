package SDS_WEEK1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
public class 최대힙_3일차 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static long[] heap;
    static int leafIdx=1;
    static int nodeSize;
    static long INF=-1;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        heap = new long[(N*4)+1];
        for(int i=0;i<N;i++){
            int input = Integer.parseInt(br.readLine());
            if(input!=0) {
            //    System.out.println(input+" 삽입");
            //    System.out.println(Arrays.toString(heap));
                insert(input);
            //    System.out.println(Arrays.toString(heap));
            }
            else {
               // System.out.println("삭제");
             //   System.out.println(Arrays.toString(heap));
                delete();
               // System.out.println(Arrays.toString(heap));
            }
            //System.out.println();
        }
        bw.flush();
        bw.close();
    }
    private static void insert(int input) { //삽입
        if(nodeSize==0){//루트 삽입 케이스
            nodeSize++;
            heap[nodeSize]=input;
        }else {
            nodeSize++;
            heap[nodeSize]=input;//리프 노드에 삽입 후
            int childIdx = nodeSize;
            int parentIdx = nodeSize/2;
            while(true) {//부모랑 비교
                if(childIdx==1) break;
                if(heap[parentIdx]>heap[childIdx]) break;
                else {
                    swap(parentIdx,childIdx);
                    childIdx = parentIdx;
                    parentIdx = parentIdx/2;
                    if(childIdx==1) break;
                }
            }
            //다음 leafidx자리
        }
    }
    private static void delete() throws IOException { //삭제
        if(nodeSize!=0) {
            //System.out.println(heap[1]);
            //System.out.println("삭제되는값"+heap[1]);
            bw.write(heap[1]+"\n");
            heap[1] = heap[nodeSize];
            heap[nodeSize]=0;
            nodeSize--;
          //  System.out.println("맨위로 올라간 값"+ heap[1]);
            //System.out.println(heap[1]);
            if(nodeSize==0) return;
            int parentIdx = 1;
            int leftChildIdx = 0;
            int rightChildIdx = 0;
            while(true) {
                leftChildIdx = parentIdx*2;
                rightChildIdx = parentIdx*2+1;
                
                if(heap[leftChildIdx]==0 && heap[rightChildIdx]==0) break;
                
                long tmp=heap[leftChildIdx]>=heap[rightChildIdx]?heap[leftChildIdx]:heap[rightChildIdx];
                
                if((heap[leftChildIdx]<=heap[rightChildIdx] &&tmp==0) ||(heap[leftChildIdx]>=heap[rightChildIdx])){
                    if(heap[parentIdx]<heap[leftChildIdx]){
                        swap(parentIdx, leftChildIdx);
                        parentIdx = leftChildIdx;
                    }else break;
                }//왼쪽이 더 크면
                
                else if((heap[leftChildIdx]>heap[rightChildIdx] &&tmp==0) ||(heap[leftChildIdx]<heap[rightChildIdx])){//오른쪽이 더 크면
                    if(heap[parentIdx]<heap[rightChildIdx]) {
                        swap(parentIdx, rightChildIdx);
                        parentIdx = rightChildIdx;
                    }else break;
                }//오른쪽이 더크면
            }
        }else bw.write("0"+"\n");
    }
    private static void swap(int parentIdx, int childIdx) {
        long temp = heap[parentIdx];
        heap[parentIdx] = heap[childIdx];
        heap[childIdx] = temp;
    }
}
