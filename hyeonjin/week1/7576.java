import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 7576 {
	/**
	 * N: 상자의 세로 크기 
	 * M: 상자의 가로 크기 box: 상자에 담긴 토마토의 정보 
	 * queue: BFS 탐색을 위한 큐 (입력에서 익은 토마토가 하나 이상 주어질 수 있으므로 static 선언)
	 */
	static int N, M, tomato;
	static int[][] box;
	static boolean[][] visited;
	static Queue<int[]> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 변수 초기화
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		visited = new boolean[N][M];

		// 토마토 정보 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				// 익은 토마토면 큐에 넣고 방문처리
				if (box[i][j] == 1) {
					queue.offer(new int[] { i, j });
					visited[i][j] = true;
				}
				// 익지 않은 토마토면 익지 않은 토마토의 개수 세기
				else if (box[i][j] == 0) {
					tomato++;
				}
			}
		}

		if (tomato == 0) { // 익지 않은 토마토가 없는 경우
			System.out.println(0);
			return;
		}

		System.out.println(bfs());
	}

	// BFS 탐색
	static int bfs() {
		// 네 방향 탐색
		int[] dx = { 0, 0, 1, -1 };
		int[] dy = { 1, -1, 0, 0 };

		int finTomato = 0; // 익은 토마토의 수
		int day = 0; // 토마토가 다 익는데 걸리는 최소 일수
		while (!queue.isEmpty()) {
			int size = queue.size();
			day++;

			// 하루마다 토마토가 익으므로, 큐의 크기만큼 반복하도록 해야 함
			for (int i = 0; i < size; i++) {
				int[] now = queue.poll();

				for (int j = 0; j < 4; j++) {
					int nx = now[0] + dx[j];
					int ny = now[1] + dy[j];
					// 범위를 벗어나면 탐색하지 않기
					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;

					// 방문하지 않았고 익지 않은 토마토가 있는 경우
					if (!visited[nx][ny] && box[nx][ny] == 0) {
						// 큐에 넣고 방문 처리 & 익은 토마토의 개수 증가
						queue.offer(new int[] { nx, ny });
						visited[nx][ny] = true;
						finTomato++; // 처음에는 큐에 이미 익은 토마토가 들어있으므로 여기에서 세야 함
					}
				}
			}
			// 토마토가 다 익은 경우
			if (finTomato == tomato)
				return day;
		}
		return -1;
	}
}