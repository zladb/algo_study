import java.util.*;

public class BOJ_2468 {

    /**
     * n : 행, 열 개수
     * map : 건물 높이 배열
     * temp : map 복사용 배열
     * visited : 방문 체크 배열
     * dx, dy : 상, 하, 좌, 우 탐색 배열
     * cnt : 강우량별 잠기지 않은 영역 개수
     * result : 최대 영역
     */
    private static int n;
    private static int[][] map;
    private static int[][] temp;
    private static int[][] visited;
    private static int[] dx = new int[] {-1, 1, 0, 0};
    private static int[] dy = new int[] {0, 0, -1, 1};
    private static int cnt = 0;
    private static int result = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 행, 열 입력
        n = sc.nextInt();
        // 건물 높이 초기화
        map = new int[n][n];

        // 최대 건물 높이
        int max = 0;
        // 건물 높이 입력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] > max) {
                    max = map[i][j];
                }
            }
        }

        // 0 ~ 최대 강우량까지 순회
        for (int i = 0; i <= max; i++) {
            // 영역 개수 초기화
            cnt = 0;
            // 강우량 별로 map을 사용해야되기 때문에 temp에 복사
            temp = Arrays.copyOf(map, map.length);
            // 방문 배열 초기화
            visited = new int[n][n];

            // 배열 전체 순회
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    // 강우량보다 작은 경우
                    if (i >= temp[j][k]) {
                        // 침수 표시
                        temp[j][k] = 0;
                        // 방문 표시
                        visited[j][k] = 1;
                    }
                }
            }

            // 배열 전체 순회
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    // 침수되지 않고 방문하지 않은 경우
                    if (temp[j][k] != 0 && visited[j][k] == 0) {
                        // 탐색
                        bfs(j, k);
                    }
                }
            }

            // 잠기지 않은 영역 비교
            if (cnt > result) {
                result = cnt;
            }
        }
        System.out.println(result);
    }

    private static void bfs(int i, int j) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});

        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            // 상, 하, 좌, 우 순회
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k], ny = y + dy[k];
                // 배열 범위 안이면서 침수되지 않고 방문하지 않은 경우
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && temp[nx][ny] != 0 && visited[nx][ny] == 0) {
                    // 큐에 추가
                    queue.add(new int[]{nx, ny});
                    //방문표시
                    visited[nx][ny] = 1;
                }
            }
        }
        // 안전 영역 증가
        cnt++;
    }
}

