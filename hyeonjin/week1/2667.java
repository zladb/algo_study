import java.io.*;
import java.util.*;

public class 2667 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 연결 == 상하좌우 연결
        // 단지수, 각 단지에 속하는 집의 수 출력
        // 단지수 = BFS 탐색 횟수
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        // 단지에 속하는 집의 수 저장하기
        ArrayList<Integer> apartments = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 아파트이고 아직 방문하지 않았다면 BFS 탐색
                if (map[i][j] == 1 && !visited[i][j]) {
                    apartments.add(bfs(i, j));
                }
            }
        }
        // 집의 수를 오름차순으로 정렬
        Collections.sort(apartments);
        StringBuilder sb = new StringBuilder();
        sb.append(apartments.size()).append("\n");
        for (int apartment : apartments) {
            sb.append(apartment).append("\n");
        }
        System.out.println(sb);
    }

    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int total = 0;
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            total++;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + move[i][0];
                int ny = now[1] + move[i][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }

                // 방문하지 않았고 집이 있는 곳인 경우
                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return total;
    }
}