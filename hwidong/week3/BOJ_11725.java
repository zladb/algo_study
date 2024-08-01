import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_11725 {
    static int N;
    static ArrayList<Integer>[] nodeList = null;    // 인접리스트
    static int[] parents = null;
    static int[] visited = null;

    static void DFS(int now){
        for(int next : nodeList[now]){
            if(parents[next]==0){   // parents가 visited 역할과 부모노드 저장 두가지 역할을 함.
                parents[next] = now;    // 부모 노드 정보 저장
                DFS(next);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        parents = new int[N+1];
        nodeList = new ArrayList[N+1];
        visited = new int[N+1];

        // 인접리스트 초기화
        for (int i = 0; i < N + 1; i++) {
            nodeList[i] = new ArrayList<>();
        }

        // 입력
        for(int i = 0; i<N-1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            nodeList[a].add(b);
            nodeList[b].add(a);
        }

        // 탐색
        visited[1] = 1;
        DFS(1);

        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i]);
        }
    }
}
