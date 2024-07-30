import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2468 {
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        // 입력 받기
        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, arr[i][j]);
            }
        }

        // 높이: 1 ~ 최대 높이 - 1 (최대 높이면 모든 영역이 잠김 ⇒ 안전 영역은 0개)
        int maxSafeArea = 1; // 비가 하나도 내리지 않는 경우 ⇒ 안전 영역은 1개 ⇒ 1로 초기화
        for (int h = 1; h < maxHeight; h++) {
            visited = new boolean[N][N];
            int safeArea = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 높이가 h일 때 물에 잠기지 않고, 아직 방문하지 않은 영역일 경우
                    if (arr[i][j] > h && !visited[i][j]) {
                        safeArea++;
                        bfs(h, i, j);
                    }
                }
            }
            maxSafeArea = Math.max(maxSafeArea, safeArea);
        }
        System.out.println(maxSafeArea);
    }

    static void bfs(int height, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + move[i][0];
                int ny = now[1] + move[i][1];
                // 범위를 벗어나면 탐색 X
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }

                // 높이가 h일 때 물에 잠기지 않고, 아직 방문하지 않은 영역일 경우
                if (arr[nx][ny] > height && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}