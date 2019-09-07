package SWExpertAcademy;

import java.util.Arrays;
import java.util.Scanner;
class Node{
    int number;
    char data;
    int value;
    Node left,right;
}
class tree{
    public void postorder(Node root) { //후위순회 = L R V
        if(root != null) {
            postorder(root.left); //왼
            postorder(root.right); //오
            //System.out.print(root.data); //루트
            if(root.data=='-') root.value = root.left.value-root.right.value;
            else if(root.data=='+') root.value = root.left.value+root.right.value;
            else if(root.data=='*') root.value = root.left.value*root.right.value;
            else if(root.data=='/') root.value = root.left.value/root.right.value;
        }
    }
}
public class 사칙연산{
    public static void main(String[] args) {
        tree t = new tree();
        Scanner sc = new Scanner(System.in);
        for(int test=1;test<=10;test++) {
            //입력
            int V = sc.nextInt()+1;
            Node[] n = new Node[V];
            sc.nextLine();
            for(int i=1; i<V;i++) n[i] = new Node();
            for(int i=1; i<V;i++) {
                String[] input = sc.nextLine().trim().split(" ");
                if(input.length==4) {
                    n[i].number = i;
                    n[i].data = input[1].charAt(0);
                    n[i].left = n[Integer.parseInt(input[2])];
                    n[i].right = n[Integer.parseInt(input[3])]; 
                    n[i].value = 0;
                }
                else if(input.length==2) {
                    n[i].number = i;
                    n[i].value = Integer.parseInt(input[1]);
                }
            }//노드 다 만듬
            //처리
            t.postorder(n[1]);
            System.out.println("#"+test+" "+n[1].value);
        }
    }
}