// 시간초과

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2206 {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static Queue<int[]> q = new LinkedList<>();
    static int[][] map;
    static int[][] visited;
    static int N, M;

    // 단순 맵 탐색 BFS
    public static void BFS() {
        q.offer(new int[] {0,0});
        visited[0][0] = 1;

        while(!q.isEmpty()) {
            int[] nowPos = q.poll();
            int x = nowPos[1];
            int y = nowPos[0];

            for(int i = 0; i<4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx<0||ny<0||nx>=M||ny>=N) {
                    continue;
                }
                if(map[ny][nx] == 1 || visited[ny][nx] != 0) {
                    continue;
                }
                q.offer(new int[] {ny,nx});
                visited[ny][nx] = visited[y][x] + 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];

        ArrayList<int[]> walls = new ArrayList<>();

        // 맵 입력
        for(int i = 0; i<N; i++) {
            String input = sc.next();
            for(int j = 0; j<M; j++) {
                int temp = input.charAt(j)-'0';
                if(temp==1){                   // 벽은 ArrayList에 따로 저장해놨다가 벽을 허물 때 사용
                    walls.add(new int[]{i,j});
                }
                map[i][j] = temp;
            }
        }

        int shortestPath = Integer.MAX_VALUE;
        int attempt = walls.size();
        for (int i = 0; i < attempt; i++) {
            visited = new int[N][M];
            map[walls.get(i)[0]][walls.get(i)[1]] = 0;      // 벽을 하나씩 허물어보며 탐색
            BFS();
            if(visited[N-1][M-1]!=0){           // 끝까지 도착했다면, 기존의 최소경로와 비교하여 값을 최신화 함.
                shortestPath = shortestPath < visited[N-1][M-1] ? shortestPath : visited[N-1][M-1];
            }
            map[walls.get(i)[0]][walls.get(i)[1]] = 1;      // 허물었던 벽 원상복구
        }

        // 최단경로가 한번도 최신화되지 않았다면, 불가능한 것으로 간주
        if(shortestPath==Integer.MAX_VALUE){
            shortestPath = -1;
        }
        System.out.printf("%d\n", shortestPath);
    }
}