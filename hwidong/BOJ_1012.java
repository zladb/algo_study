import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1012 {
    static int[][] dir = {{1,0}, {0,1},{-1,0},{0,-1}};
    static int[][] map;
    static Queue<int[]> q = new LinkedList<>();
    static int N,M,K;

    static void BFS(int x, int y) {
        q.add(new int[] {x,y});

        while(!q.isEmpty()) {
            int nowX = q.peek()[0];
            int nowY = q.peek()[1];
            q.poll();
            for(int i = 0; i<4; i++) {
                int newX = nowX+dir[i][0];
                int newY = nowY+dir[i][1];
                if(newX<0||newY<0||newX>=M||newY>=N) {
                    continue;
                }
                if(map[newY][newX]==0) {
                    continue;
                }
                q.add(new int[] {newX,newY});
                map[newY][newX] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 0; t<T; t++) {
            M = sc.nextInt();
            N = sc.nextInt();
            K = sc.nextInt();

            map = new int[N][M];
            for(int i = 0; i<K; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                map[y][x] =1;
            }

            int cnt=0;
            for(int i = 0; i<N; i++) {
                for(int j = 0; j<M; j++) {
                    if(map[i][j]==1) {
                        BFS(j,i);
                        cnt++;
                    }
                }
            }
            System.out.printf("%d\n", cnt);
        }
    }
}
