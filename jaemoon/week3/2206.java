import java.util.*;

public class BOJ_2206 {

    private static int n;
    private static int m;
    private static int[][] map;
    private static int[][] visited;
    private static int[][] temp;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static List<int[]> wall = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String input = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j) - '0';
                if (map[i][j] == 1) {
                    wall.add(new int[]{i, j});
                }
            }
        }

        int max = 0;
        for (int[] wallPoint : wall) {
            temp = Arrays.copyOf(map, map.length);
            visited = Arrays.copyOf(map, map.length);
            int breakX = wallPoint[0];
            int breakY = wallPoint[1];
            temp[breakX][breakY] = 0;
            visited[breakX][breakY] = 1;

            temp[0][0] = 1;
            visited[0][0] = 1;
            bfs();

            if (temp[n - 1][m - 1] > max) {
                max = temp[n - 1][m - 1];
            }
        }
        if (max == 0) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }
    }

    private static void bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && temp[nx][ny] == 0 && visited[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny});
                    temp[nx][ny] = temp[x][y] + 1;
                }
            }
        }
    }
}

