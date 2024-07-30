import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
	static int N, M;
	static int[][] map;
	static boolean[][][] visited; // 벽을 부수면 이동할 수 있는 곳이 달라짐 > 벽을 부쉈는지에 따라 구분해서 확인해야 함

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1][2]; // [][][0]: 벽 부수기 X, [][][1]: 벽 부수개 O
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = line.charAt(j - 1) - '0';
			}
		}
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Pos> queue = new LinkedList<>();
		queue.offer(new Pos(1, 1, 1, 0));
		visited[1][1][0] = true;

		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		while (!queue.isEmpty()) {
			Pos now = queue.poll();
			if (now.x == N && now.y == M)
				return now.dist;

			// 네 방향 탐색
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				// 범위 벗어나면 탐색 X
				if (nx <= 0 || ny <= 0 || nx > N || ny > M) {
					continue;
				}

				// 이동하려는 곳이 벽 & 아직 벽을 부수지 않음 & 해당 지점을 방문하지 않음
				if (map[nx][ny] == 1 && now.crash == 0 && !visited[nx][ny][1]) {
					visited[nx][ny][1] = true;
					queue.offer(new Pos(nx, ny, now.dist + 1, 1));
				}
				// 이동하려는 곳이 벽이 아님 & 해당 지점을 방문하지 않음
				else if (map[nx][ny] == 0 && !visited[nx][ny][now.crash]) {
					visited[nx][ny][now.crash] = true;
					queue.offer(new Pos(nx, ny, now.dist + 1, now.crash));
				}
			}
		}
		return -1;
	}

	static class Pos {
		int x, y;
		int dist;
		int crash; // 0: 부수기 X, 1: 부수기 O

		public Pos(int x, int y, int dist, int crash) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.crash = crash;
		}
	}
}