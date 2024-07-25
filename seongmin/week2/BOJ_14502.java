package algoClassification.BFS;

import java.io.*;
import java.util.*;

// 인터넷 자료 참조 했습니다.

public class BOJ_14502 {

    static int[][] map;
    static int n, m;
    static ArrayList<int[]> emptyCells = new ArrayList<>();  // 빈 칸 좌표 저장
    static ArrayList<int[]> virus = new ArrayList<>();  // 바이러스 좌표 저장
    static ArrayList<ArrayList<int[]>> wallCombinations = new ArrayList<>();  // 빈 칸 좌표에 대한 조합
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BaekJoon/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 행
        m = Integer.parseInt(st.nextToken());  // 열
        map = new int[n][m];
        int maxSize = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {  // 빈 칸 x, y 좌표 저장
                    emptyCells.add(new int[]{i, j});
                } else if (map[i][j] == 2) {  // 바이러스 좌표
                    virus.add(new int[]{i, j});
                }
            }
        }

        combination(new ArrayList<>(), 0);  // 벽 3개를 세울 수 있는 모든 (x, y)좌표를 조합을 통해 구한다.

        for (int i = 0; i < wallCombinations.size(); i++) {  // 벽은 무조건 3개를 세워야 한다.
            int wallSet = 0;
            for (int[] wallCombination : wallCombinations.get(i)) {
                int x = wallCombination[0];
                int y = wallCombination[1];
                if (map[x][y] == 0) {  // 빈 칸이면 벽을 세우고 카운트 +1
                    map[x][y] = 1;
                    wallSet++;
                }
            }

            if (wallSet == 3) {  // 3개를 세웠다면 bfs 탐색
                int[][] cloneMap = bfs();  // 바이러스를 퍼뜨린다. (copy 본 활용)
                int safetySize = 0;
                for (int j = 0; j < n; j++) {  // map 전체 검색 후 0이 있다면 갯수를 세서 최댓값 갱신
                    for (int k = 0; k < m; k++) {
                        if (cloneMap[j][k] == 0)
                            safetySize++;
                    }
                }
                maxSize = Math.max(safetySize, maxSize);
            }

            for (int[] wallCombination : wallCombinations.get(i)) {  // 3개를 세우든 못 세우든 map 무조건 복구
                int x = wallCombination[0];
                int y = wallCombination[1];
                if (map[x][y] == 1) {
                    map[x][y] = 0;
                }
            }
        }
        System.out.println(maxSize);
    }

    static void combination(ArrayList<int[]> selected, int depth) {  // 깊이 우선 탐색
        if (depth == 3) {                                            // 최대 벽 3개
            wallCombinations.add(new ArrayList<>(selected));         // 복사본을 넣는다.
            return;
        }

        for (int i = 0; i < emptyCells.size(); i++) {     // 격자 판에 빈 칸 갯수만큼 반복
            selected.add(emptyCells.get(i));              // 빈 칸(0) 좌표를 넣는다.
            combination(selected, depth + 1);      // dfs
            selected.remove(selected.size() - 1);  // 백트래킹
        }
    }

    static int[][] bfs() {
        int[][] cloneMap = deepCopy();  // 배열 깊은 복사
        Queue<int[]> queue = new LinkedList<>();
        for (int[] x : virus) {
            queue.offer(x);
        }

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 + 빈 칸이면 바이러스를 퍼뜨린다.
                if (0 <= nx && nx < n && 0 <= ny && ny < m && cloneMap[nx][ny] == 0) {
                    cloneMap[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return cloneMap;
    }

    static int[][] deepCopy() {
        int[][] copy = new int[map.length][];

        for (int i = 0; i < map.length; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }
}