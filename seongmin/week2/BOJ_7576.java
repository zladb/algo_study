package algoClassification.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {

    static int m, n, answer;
    static int[][] arr;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());  // 열
        n = Integer.parseInt(st.nextToken());  // 행
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    queue.offer(new int[]{i,j});  // 익은 토마토(1) 좌표  ->  큐에 삽입
                }
            }
        }

        System.out.println(bfs());  // 너비 우선 탐색
    }

    static int bfs() {

        while (!queue.isEmpty()) {  // 큐가 비어 있지 않다면
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위, 다음 노드가 '0'이면 큐에 삽입
                if ((0 <= nx && nx < n && 0 <= ny && ny < m) && arr[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});  // 방문 노드 좌표 추가
                    arr[nx][ny] = arr[cx][cy] + 1;   // 다음 노드가 '0'  ->  현재 노드 +1 (자체 방문 체크)
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) return -1;
                answer = Math.max(answer, arr[i][j]);
            }
        }
        return answer-1;
    }
}
