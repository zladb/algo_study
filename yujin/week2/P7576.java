package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7576 {

	static int[][] map;
	static int[][] visited;
	static int N,M;
	static int[] dx = { 1, 0, -1, 0 }; // 상하좌우 계산할 x좌표
	static int[] dy = { 0, 1, 0, -1 }; // 상하좌우 계산할 y좌표
	static String line;
	static Queue<Spot> queue;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		
		queue = new LinkedList<Spot>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j]=num;
				if (map[i][j]==1) {
					queue.offer(new Spot(i,j));
				}
			}
		}
	
		while(!queue.isEmpty()) {
			Spot s = queue.poll();
//			visited[s.x][s.y] = 1;
			
			for(int i = 0 ; i<4; i++) {
				int nx = s.x + dx[i];
				int ny = s.y + dy[i];
				
				if (nx<0 || nx >=N || ny <0 || ny >=M)
					continue;
				if (map[nx][ny]>0 || map[nx][ny]==-1)
					continue;
				map[nx][ny] = map[s.x][s.y]+1;
				queue.offer(new Spot(nx,ny));
			}
		}
		
		
		int max = 0;
		
		for(int i = 0; i< N; i++) {
			for(int j = 0; j<M; j++) {
//				System.out.println(map[i][j]);
				if(map[i][j]==0) {
					System.out.println(-1);
					return;
				}
				if(map[i][j] > max)
					max = map[i][j];
			}
		}
		
		System.out.println(max-1);
	}
		
	

}

class Spot {
	int x, y;

	public Spot(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}
