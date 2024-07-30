// P2468

package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class T1 {

	public static int[][] map;
	public static int[][] visited;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int water_level = 1;
		int max_safe_area = 0;
		
		while(water_level <= 100) {
			int safe_area_cnt =0;
			
			visited = new int[N][N];
			for(int i = 0; i<N; i++) {
				for(int j =0; j<N; j++) {
					if(visited[i][j]==0 && map[i][j] > water_level) {
						bfs(i,j, water_level);
						safe_area_cnt++;
					}
						
				}
			}
//			System.out.println(water_level + " "+ safe_area_cnt);
			max_safe_area = Math.max(max_safe_area, safe_area_cnt);
			
			water_level ++;
		}
		
		System.out.println(max_safe_area);
	}

	private static void bfs(int i, int j, int water_level) {
		Queue<Spot> q = new LinkedList<Spot>();
		q.offer(new Spot(i,j));
		while(!(q.isEmpty())) {
			Spot s = q.poll();
			
			for(int d=0; d<4; d++) {
				int nx = s.x + dx[d];
				int ny = s.y + dy[d];
				if (nx<0|| nx>=N|| ny<0|| ny>=N)
					continue;
				if (visited[nx][ny]==1 || map[nx][ny]<=water_level)
					continue;
				q.offer(new Spot(nx,ny));
				visited[nx][ny]=1;
			}
		}
	}

}

