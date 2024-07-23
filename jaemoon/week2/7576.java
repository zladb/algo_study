import java.io.*;
import java.util.*;

public class 7576 {

    /**
     * m : 가로
     * n : 세로
     * tomatoMap : 토마토 배열
     * dx, dy : 상, 하, 좌, 우 배열
     */
    private static int m;
    private static int n;
    private static int[][] tomatoMap;
    private static int[] dx = new int[]{-1, 1, 0, 0};
    private static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        // 가로, 세로 입력
        m = sc.nextInt();
        n = sc.nextInt();

        // 토마토 배열 선언
        tomatoMap = new int[n][m];

        // 큐 선언
        Deque<int[]> queue = new ArrayDeque<>();
        // 토마토 입력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tomatoMap[i][j] = sc.nextInt();
                // 토마토가 있는 경우
                if (tomatoMap[i][j] == 1) {
                    //큐에 삽입
                    queue.add(new int[]{i, j});
                }
            }
        }

        // 모든 토마토가 익을 때까지의 최소 날짜
        int days = bfs(queue);

        // 결과 판단
        boolean result = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 안익은 토마토가 하나라도 있을 경우
                if (tomatoMap[i][j] == 0) {
                    // 익지 못하는 상태
                    result = false;
                }
            }
        }

        // 결과 출력
        if (result) {
            System.out.println(days);
        } else {
            System.out.println(-1);
        }

    }

    public static int bfs(Deque<int[]> queue) {
        // 최소 날짜 변수 (시작이 0일이기 때문에 -1로 설정)
        int days = -1;

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {

            // 큐 사이즈 지정(날짜를 계산하기 위해 지정, 지정하지 않으면 모두 한 날짜에 익어버림)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 맨 앞 원소 꺼내기
                int[] start = queue.poll();
                int x = start[0];
                int y = start[1];

                // 상, 하, 좌, 우 체크
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    // 위치가 지도 안이면서 익지 않은 토마토인 경우
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && tomatoMap[nx][ny] == 0) {
                        // 익음 표시
                        tomatoMap[nx][ny] = 1;
                        // 큐에 삽입
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
            // 날짜 증가
            days++;
        }

        // 날짜 반환
        return days;
    }
}

