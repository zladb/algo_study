package algoClassification.BFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class BOJ_2667 {

    static int n;
    static int[][] map;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            ArrayList<String> tmp = new ArrayList<>(Arrays.asList(sc.next().split("")));
            for (int j = 0; j < tmp.size(); j++) {
                map[i][j] = Integer.parseInt(tmp.get(j));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {                  // 1이면서 방문하지 않았다면 bfs 실행
                    ans.add(bfs(new int[] { i, j }));  // 값 반환 후 ans에 삽입
                }
            }
        }

        Collections.sort(ans);  // 오름차순 정렬
        System.out.println(ans.size());
        for (int i : ans) {
            System.out.println(i);
        }
    }

    static int bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>(); // 좌표를 담으려면 배열로 받아야 함.
        int[] dx = { 0, 0, -1, 1 };
        int[] dy = { 1, -1, 0, 0 };
        queue.offer(start);
        map[start[0]][start[1]] = 0;  // 방문 표시
        int cnt = 1;                  // 연결 되어 있는 집의 수

        while (!queue.isEmpty()) {    // 큐가 비어있지 않다면 (방문 해야될 방이 존재함.)
            int[] pos = queue.poll();
            int cx = pos[0], cy = pos[1]; // 현재 x, y 좌표

            for (int i = 0; i < 4; i++) {  // 4방향 탐색
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] == 1) {  // 범위 포함 여부 확인, 집인지 확인, 방문 여부 확인
                    queue.offer(new int[] {nx, ny});
                    map[nx][ny] = 0;
                    cnt += 1;
                }
            }
        }
        return cnt;
    }
}