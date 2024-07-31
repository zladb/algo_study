import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 0번 인덱스 사용 X,  루트 노드는 1로 고정,  2번 인덱스부터 사용
        parent = new int[n + 1];       // 배열 초기화
        tree = new ArrayList[n + 1];   // 리스트 초기화

        for (int i = 1; i < n + 1; i++) {  // 리스트를 요소로 갖는 배열 초기화
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            tree[p1].add(p2);   // 무향 그래프 -> 양방향 데이터 삽입
            tree[p2].add(p1);
        }

        dfs(1, -1); // (현재 노드, 부모 노드)

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < n + 1; i++) {
            sb.append(parent[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int cur_v, int pNode) {
        parent[cur_v] = pNode;
        for (int next_v : tree[cur_v]) {  // 현재 노드에 방문해야되는 다음 노드 탐색
            if (parent[next_v] == 0) {    // 다음 노드에 방문을 안 했다면
                dfs(next_v, cur_v);
            }
        }
    }
}
