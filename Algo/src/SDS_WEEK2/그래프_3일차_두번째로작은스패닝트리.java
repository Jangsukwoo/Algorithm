package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 * The second Minimum Spanning Tree
 * 최소스패닝트리 T보다 크면서 그중에 가장 작은 스패닝트리를 구하는 문제
 * 스패닝트리의 값만 구하면 된다.
 * 
 * 이 문제는 4시간동안 생각해서 푸는건 불가능하다고 하신다..
 * 
 * ->접근
 * V<=5만
 * E<=20만
 * MST = 최소신장트리. 모든 정점을 갈 수 있는 최소 비용의 간선 개수 
 * ->프림,크루스칼로 풀어야 한다.
 * 최소스패닝트리에서 간선의 개수는 E = V-1개
 * 
 * Solution 
 * 간선 하나를 추가하면 MST가 아닌게 되버림
 * 이떄 사이클을 구성하는 엣지들을 찾아야함 
 * step1. Cycle 찾기
 * step2. Cycle에서 가장 큰 Edge 찾기
 * 이 두 step이 logE에 처리되야함 (공통조상찾기 LCA -> logE)
 * 이떄  lca sparse Table에서 max값을 쓴다.
 * 여기서 maxCost를 뽑아야함.
 * 
 * 즉 전체적으로 해야할 것은
 * 1. MST를 한번 돌려서 구한다 ElogE
 * 2. LCA를 구하는데 LCA 테이블에는 firstMaxCost와 secondMaxCost를 구한다. (E-V+1) ElogE
 * 
 * 
 * 코드가 엄청나게 길고 어려운 문제이기 떄문에 
 * 올려주는 정답 코드를 보고 분석 + 이해.
 * 접근, 시간복잡도 판단, 아이디어, 알고리즘 구성을 학습하는게 중요하다
 * 
 * 아래는 현직님이 구성하신 코드 
 */
public class 그래프_3일차_두번째로작은스패닝트리 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int V, E, H, minCost, ans;
    private static int[] parents, visited, depth;
    private static List<int[]> edges;
    private static int[] mstEdge; // mst 엣지가 되는 엣지 번호에 cost 저장
    private static List<int[]>[] trees;
    private static int[][] par, maxCost;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int v = V;
        H = 0;
        while (v > 0) {
            v /= 2;
            H++;
        }

        trees = new ArrayList[V+1];
        mstEdge = new int[E];
        parents = new int[V+1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
            trees[i] = new ArrayList<>();
        }

        minCost = 0;

        // 간선 저장
        edges = new ArrayList<>();
        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edges.add(new int[]{i, a, b, c});
        }

        int ret = kruskal();
        if (ret == -1) {
            bw.write("-1\n");
        } else {
            secondMst();
            bw.write(ans + "\n");
        }
        bw.close();
    }

    // MST - Kruskal
    private static int kruskal() {
        Comparator<int[]> edgeComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[3], o2[3]);
            }
        };
        Collections.sort(edges, edgeComparator);

        int cnt = 0;
        int[] edge;
        for (int i=0; i<edges.size(); i++) {
            edge = (int[])edges.get(i);
            int s = edge[1];
            int e = edge[2];

            int ps = find(s);
            int pe = find(e);
            if (ps == pe) continue;

            // union
            parents[pe] = ps;

            int idx = edge[0];
            int cost = edge[3];

            // tree 만들기
            trees[s].add(new int[]{idx, e, cost});
            trees[e].add(new int[]{idx, s, cost});
            mstEdge[idx] = 1;
            minCost += cost;
            cnt++;
        }

        if (cnt != V-1) return -1;  // MST 가 없는 경우

        return 0;
    }

    private static int find(int s) {
        if (parents[s] == s) return s;
        else return parents[s] = find(parents[s]);
    }

    private static void secondMst() {
        visited = new int[V+1];
        depth = new int[V+1];
        par = new int[V+1][H];
        maxCost = new int[V+1][H];

        // dfs 돌려서 depth 와 부모 저장
        dfs(1);

        ans = Integer.MAX_VALUE;    // 2^31-1

        // tree 로 연결이 되지 않은 간선의 lca 구하기
        for (int[] edge : edges) {
            if (mstEdge[edge[0]] > 0) continue; // mst 연결된 간선 패스

            int s = edge[1];
            int e = edge[2];
            int c = edge[3];
            int res = lca(s, e, c);

            if (minCost != 1 && c != 1 && res == 0) {
                continue;
            }
            ans = Math.min(ans, minCost - res + c);
        }

        if (ans == Integer.MAX_VALUE || ans == minCost) {
            ans = -1;
        }
    }

    private static int lca(int a, int b, int c) {
        int ret = 0;

        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a]-depth[b];
        int h = 0;
        while (diff > 0) {
            if (diff % 2 == 1) {
                if (maxCost[a][h] == c) {
                    ret = Math.max(ret, getMax(a, h, c));
                } else {
                    ret = Math.max(ret, maxCost[a][h]);
                }
                a = par[a][h];
            }
            diff /= 2;
            h++;
        }

        if (a == b) return ret;

        for (h = H-1; h >= 0; h--) {
            if (par[a][h] == par[b][h]) continue;

            if (maxCost[a][h] == c) {
                ret = Math.max(ret, getMax(a, h, c));
            } else {
                ret = Math.max(ret, maxCost[a][h]);
            }
            if (maxCost[b][h] == c) {
                ret = Math.max(ret, getMax(b, h, c));
            } else {
                ret = Math.max(ret, maxCost[b][h]);
            }

            a = par[a][h];
            b = par[b][h];
        }
        ret = Math.max(ret, (maxCost[a][0] == c)?ret : maxCost[a][0]);
        ret = Math.max(ret, (maxCost[b][0] == c)?ret : maxCost[b][0]);

        return ret;
    }

    private static int getMax(int a, int k, int c) {
        if (k == 0) return 0;

        int ret = -1;
        if (maxCost[a][k-1] == c) {
            ret = Math.max(ret, getMax(a, k-1, c));
        } else {
            ret = Math.max(ret, maxCost[a][k-1]);
        }

        if (maxCost[par[a][k-1]][k-1] == c) {
            ret = Math.max(ret, getMax(par[a][k-1], k-1, c));
        } else {
            ret = Math.max(ret, maxCost[par[a][k-1]][k-1]);
        }

        return ret;
    }

    private static void dfs(int n) {
        if (visited[n] != 0) return;

        visited[n]++;
        for (int[] node : trees[n]) {
            int next = node[1];
            int cost = node[2];

            if (visited[next] != 0) continue;

            depth[next] = depth[n] + 1;
            par[next][0] = n;
            maxCost[next][0] = cost;

            for (int i = 1; i < H; i++) {
                par[next][i] = par[par[next][i-1]][i-1];
                maxCost[next][i] = Math.max(maxCost[next][i-1], maxCost[par[next][i-1]][i-1]);
            }
            dfs(next);
        }
    }
}