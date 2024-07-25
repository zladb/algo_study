package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1012 {

	P1012() {
		// TODO Auto-generated constructor stub
	}

	static int T, N, M, K;
	static int[][] visited;
	static int[][] map;
	static int[] dx = { 1, 0, -1, 0 }; // 상하좌우 계산할 x좌표
	static int[] dy = { 0, 1, 0, -1 }; // 상하좌우 계산할 y좌표
	static String line;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {

			st = new StringTokenizer(bf.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][M]; // 미로를 저장할 배열
			visited = new int[N][M]; // 거리를 계산할 dist 배열

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(bf.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				map[r][c] = 1;
			}

			int answer = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j] == 0 && map[i][j] == 1) {
						bfs(i, j);
						answer ++;
					}

				}
			}

			System.out.println(answer);
			

		}
	}

	public static int bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y));
		visited[x][y] = 1;
		int cnt = 0;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			cnt++;
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (visited[nx][ny] == 1 || map[nx][ny] == 0)
					continue;
				q.offer(new Pair(nx, ny));
				visited[nx][ny] = 1;
			}
		}

		return cnt;
	}

}

class Pair {
	int x, y;

	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}