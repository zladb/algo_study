import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 14502 {
    /**
     * N: 지도의 세로 크기 
     * M: 지도의 가로 크기 
     * map: 지도
     */
    static int N, M, emptyArea = 0, maxSafeArea = 0;
    static int[][] map;
    static boolean[][] isWall, visited;
    static ArrayList<int[]> virusList = new ArrayList<>();
    static ArrayList<int[]> emptyList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        isWall = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptyList.add(new int[] { i, j });
                } else if (map[i][j] == 2) {
                    virusList.add(new int[] { i, j });
                }
            }
        }
        emptyArea = emptyList.size();
        makeWall(0);
        System.out.println(maxSafeArea);
    }

    static void makeWall(int depth) {
        if (depth == 3) { // 벽을 다 세웠다면 안전 영역의 크기 구하기
            maxSafeArea = Math.max(maxSafeArea, bfs());
            return;
        }

        for (int[] empty : emptyList) {
            int x = empty[0], y = empty[1];
            // 벽이 세워지지 않았고 벽을 세울 수 있는 경우
            if (!isWall[x][y] && map[x][y] == 0) {
                isWall[x][y] = true;
                makeWall(depth + 1);
                isWall[x][y] = false;
            }
        }
    }

    // 바이러스 퍼뜨리기
    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) { // visited 배열 초기화하기
            Arrays.fill(visited[i], false);
        }

        // 초기 바이러스 위치를 큐에 넣기
        for (int[] virus : virusList) {
            queue.offer(virus);
            visited[virus[0]][virus[1]] = true;
        }

        int safeArea = emptyArea - 3; // 안전 영역의 크기(벽을 3개 세웠으니 3을 빼줘야 함)
        int[][] move = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            // 네 방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + move[i][0];
                int ny = now[1] + move[i][1];
                // 범위를 벗어나는 경우 탐색 Xx
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                // 벽을 세웠거나 이미 방문한 경우 탐색 Xx
                if (isWall[nx][ny] || visited[nx][ny])
                    continue;

                // 빈 칸이면 바이러스가 퍼질 수 있음
                if (map[nx][ny] == 0) {
                    queue.offer(new int[] { nx, ny });
                    visited[nx][ny] = true;
                    safeArea--; // 안전영역의 크기 감소
                }
            }
        }
        return safeArea;
    }
}