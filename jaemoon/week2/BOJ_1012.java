import java.io.*;
import java.util.*;

public class BOJ_1012 {

    /**
     * n : 세로
     * m : 가로
     * cabbageMap : 배추 지도 배열
     * visited : 방문 현황 배열
     * dx, dy : 상, 하, 좌, 우 체크 배열
     */
    private static int n;
    private static int m;
    private static int[][] cabbageMap;
    private static int[][] visited;
    private static int[] dx = new int[]{-1, 1, 0, 0};
    private static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        // 테스트 케이스 수 입력
        int testCase = sc.nextInt();

        // 테스트 케이스 만큼 반복
        for (int i = 0; i < testCase; i++) {

            // 가로, 세로, 배추좌표개수 입력
            m = sc.nextInt();
            n = sc.nextInt();
            int k = sc.nextInt();

            // 가로 세로만큼 배열 할당
            cabbageMap = new int[n][m];
            visited = new int[n][m];

            //배추 좌표 입력
            for (int j = 0; j < k; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();

                cabbageMap[y][x] = 1;
            }

            // 지렁이 수
            int cnt = 0;

            // 배추지도 순회
            for (int j = 0; j < n; j++) { 
                for (int l = 0; l < m; l++) {
                    // 현재 좌표가 방문하지 않은 배추인 경우
                    if (cabbageMap[j][l] == 1 && visited[j][l] == 0) {
                        // 방문표시
                        visited[j][l] = 1;
                        // 주변 배추 탐색
                        bfs(j, l);
                        // 지렁이 수 증가
                        cnt++;
                    }
                }
            }
            // 지렁이 수 출력
            System.out.println(cnt);
        }
    }

    public static void bfs(int i, int j) {
        Deque<int[]> queue = new ArrayDeque<>();
        // 큐에 시작 좌표 삽입
        queue.add(new int[]{i, j});
        
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 맨 앞 좌표 꺼내기
            int[] start = queue.poll();
            // 가로 세로 좌표 할당
            int x = start[0];
            int y = start[1];

            // 상, 하, 좌, 우 체크
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                // 배추 지도 밖이거나 배추가 아니거나 방문한 배추인 경우
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || cabbageMap[nx][ny] == 0 || visited[nx][ny] == 1) {
                    // 무시
                    continue;
                }
                
                // 방문 표시
                visited[nx][ny] = 1;
                // 큐에 좌표 삽입
                queue.add(new int[]{nx, ny});
            }
        }
    }
}
