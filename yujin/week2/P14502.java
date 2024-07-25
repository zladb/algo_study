package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P14502 {

	static int[][] map;
	static int[][] visited;
	static int[][] virusMap;
	static int N,M;
	static int[] dx = { 1, 0, -1, 0 }; // 상하좌우 계산할 x좌표
	static int[] dy = { 0, 1, 0, -1 }; // 상하좌우 계산할 y좌표
	static String line;
	static Queue<Spot> virus;
	static Queue<Spot> virusTemp;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		
		virus = new LinkedList<Spot>();
		virusTemp = new LinkedList<Spot>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				visited[i][j] = map[i][j]=num;
				if (map[i][j]==2) {
					virus.offer(new Spot(i,j));
					virusTemp.offer(new Spot(i,j));
				}
			}
		}
		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if (map[i][j]==0) {
					visited[i][j] = 1;
					dfs(1);
					visited[i][j] = 0;
				}
			}
		}
		

		
		System.out.println(answer);
	}
	
	static void dfs(int count) {
		if (count ==3) {
			bfs();
			return;
		}
		for (int i =0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]==0) {
					visited[i][j]=1;
					dfs(count+1);
					visited[i][j]=0;
				}
			}
		}
	}
	
	static void bfs() {
//		int[][] virusMap = new int[N][M];
		virusMap = new int[N][M];
		virus = virusTemp;
		
		for(int i = 0; i<N; i++) {
			for(int j=0; j<M; j++) {
				virusMap[i][j] = visited[i][j];
				System.out.println(virusMap[i][j]);
			}
		}
		
		while(!virus.isEmpty()) {
			Spot s = virus.poll();
//			visited[s.x][s.y] = 1;
			
			for(int i = 0 ; i<4; i++) {
				int nx = s.x + dx[i];
				int ny = s.y + dy[i];
				
				if (nx<0 || nx >=N || ny <0 || ny >=M)
					continue;
				if (virusMap[nx][ny]>0)
					continue;
				virusMap[nx][ny] = virusMap[s.x][s.y]+1;
				virus.offer(new Spot(nx,ny));
			}
		}
		
		// 안전 지역칸 세기 
		int cnt = 0;
		for(int i = 0; i< N; i++) {
			for(int j = 0; j<M; j++) {
				if(virusMap[i][j]==0) {
					cnt ++;
					return;
				}
			}
		}
		if (answer > cnt) {
			answer = cnt;
		}
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
