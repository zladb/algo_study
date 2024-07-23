package com.gumi.sam.java.algo;

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

public class P2667 {

	public P2667() {
		// TODO Auto-generated constructor stub
	}

	static int N;
	static int[][] visited;
	static char[][] map;
	static int[] dx = {1, 0 , -1, 0};       // 상하좌우 계산할 x좌표
	static int[] dy = {0, 1, 0, -1};        // 상하좌우 계산할 y좌표
	
	public static void main(String[] args) throws IOException {
	      BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	      StringTokenizer st = new StringTokenizer(bf.readLine());
	      
	      N = Integer.parseInt(st.nextToken());
	      map = new char[N][N];  // 미로를 저장할 배열
	      visited = new int[N][N];    // 거리를 계산할 dist 배열
	      Queue<Pair> qu = new LinkedList<>();
	      
	      for(int i=0; i<N; i++){
	          String line = bf.readLine();
	          for(int j=0; j<N; j++){
	        	  map[i][j] = line.charAt(j);
	          }
	          
	      }
	      
	      
	      List<Integer> answer = new ArrayList<>();
	      
	      for (int i = 0; i < N; i++) {
			for (int j = 0; j<N; j++) {
				if(visited[i][j]==0 && map[i][j]=='1')
				{
					int temp = bfs(i, j);
					answer.add(temp);
					
				}
			
			}
		}
	     
	      Collections.sort(answer);
	      System.out.println(answer.size());

	      for (int element : answer) {
	    	  System.out.println(element);
	      }
	      
	}

	public static int bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x,y));
		visited[x][y]=1;
		int cnt = 0;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			cnt++;
			for(int i =0; i<4; i++)
			{
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if (nx <0 || nx >=N || ny<0 || ny >=N)
					continue;
				if (visited[nx][ny]==1 || map[nx][ny]=='0')
					continue;
				q.offer(new Pair(nx,ny));
				visited[nx][ny]=1;
			}
		}
		
		int a = 2;
		return cnt;
	}
	
}


class Pair{
	int x, y;

	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}