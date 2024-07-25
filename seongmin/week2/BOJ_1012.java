package algoClassification.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012 {

    static int row, col, bugCnt;
    static int[][] arr; // 0으로 초기화
    static boolean[][] visit;  // false 초기화

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            arr = new int[row][col];
            visit = new boolean[row][col];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[x][y] = 1;
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (arr[i][j] == 1 && !visit[i][j]) {  // 배추 있는 땅(1)이고 방문하지 않았다면
                        bfs(new int[]{i, j});
                        bugCnt += 1;  // bfs 처리할 때마다 '연결된 땅'이므로 벌레 +1
                    }
                }
            }
            System.out.println(bugCnt);
            bugCnt = 0; // TestCase 끝나면 0으로 초기화
        }
    }

    static void bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        int[] dx = {0, 0, 1, -1};  // 동서남북 좌표 값
        int[] dy = {1, -1, 0, 0};
        queue.offer(start);

        while (!queue.isEmpty()) { // 큐에 값이 있다면  ->  방문해야할 방이 있다면
            int[] pos = queue.poll();
            int cx = pos[0];       // 현재 좌표 (cx, cy)
            int cy = pos[1];
            visit[cx][cy] = true;  // 현재 좌표는 방문 처리

            for (int i = 0; i < 4; i++) { // 4방향 탐색
                int nx = cx + dx[i];      // 다음 좌표 방문 처리
                int ny = cy + dy[i];

                // 범위(행, 열 체크) 안에 있으며, 다음 방에 배추 심어져 있고 방문을 안 했다면
                if (0 <= nx && nx < row && 0 <= ny && ny < col && arr[nx][ny] == 1 && !visit[nx][ny]) {
                    // 3가지 조건 만족 시 queue에 다음 노드 삽입 + 방문 체크
                    queue.offer(new int[]{nx, ny});
                    visit[nx][ny] = true;
                }
            }
        }
    }
}

