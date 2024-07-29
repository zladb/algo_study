import java.util.*;

public class Main {

    /**
     * n : 노드 개수
     * tree : 전체 트리
     * visited : 방문 표시 배열
     * record : 부모 표시 배열
     */
    private static int n;
    private static List<List<Integer>> tree;
    private static int[] visited;
    private static int[] record;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 노드 개수 입력
        n = sc.nextInt();

        // 각 배열 선언
        tree = new ArrayList<>();
        visited = new int[n];
        record = new int[n];

        // 인접 리스트이기 때문에 큰 리스트 안에 노드 개수만큼 작은 리스트 추가
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        // 연결 표시
        for (int i = 0; i < n - 1; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            tree.get(x).add(y);
            tree.get(y).add(x);
        }

        // 루트가 1이기 때문에 1부터 시작
        visited[0] = 1;
        dfs(0);
    }

    private static void dfs(int start) {
        // 스택 선언
        Stack<Integer> stack = new Stack<>();
        // 스택에 시작 지점 삽입
        stack.push(start);

        // 스택이 빌 때까지 순회
        while (!stack.isEmpty()) {
            // 맨 마지막 원소 꺼내기
            int x = stack.pop();
            // x번째 리스트 순회(x와 연결된 값들)
            for (int near : tree.get(x)) {
                // 방문하지 않은 경우
                if (visited[near] == 0) {
                    // 스택에 삽입
                    stack.push(near);
                    // 방문 표시
                    visited[near] = 1;
                    // 부모 표시
                    record[near] = x + 1;
                }
            }
        }

        // 부모 배열 출력 (2번 째 노드부터)
        for (int i = 1; i < n; i++) {
            System.out.println(record[i]);
        }
    }
}

