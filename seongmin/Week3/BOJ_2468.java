import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int n, maxSafeArea;
    static int[][] area;
    static boolean[][] visited;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        area = new int[n][n];
        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                area[i][j] = sc.nextInt();
                maxHeight = Math.max(area[i][j], maxHeight);  // 최대 높이
            }
        }

        for (int height = 0; height <= maxHeight; height++) {
            int safeArea = 0;
            visited = new boolean[n][n];  // 방문 체크

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (area[i][j] > height && !visited[i][j]) {  // 방문 영역이 물에 잠기지 않고 방문 X
                        bfs(i, j, height, visited);
                        safeArea++;
                    }
                }
            }
            maxSafeArea = Math.max(safeArea, maxSafeArea);  // 최대 안전 영역 갱신
        }
        System.out.println(maxSafeArea);
    }

    static void bfs(int x, int y, int height, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            visited[cx][cy] = true;  // 현재 방문 처리

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {         // 범위 확인 여부
                    if (area[nx][ny] > height && !visited[nx][ny]) {  // 다음 방문 값이 height 보다 크고 방문 처리 X
                        queue.offer(new int[]{nx, ny});  // next (x, y) 좌표 삽입
                        visited[nx][ny] = true;          // 다음 방문 처리
                    }
                }
            }
        }
    }
}

