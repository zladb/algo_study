import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 연구소(map)을 1차원으로 풀어서 벽 세움.
// 빈칸 수에서 감염된 칸 수를 빼서 결과 도출
// 탐색은 BFS 적용
// 감염 영역 계산 시 기록된 감염 영역의 최솟값(min)보다 커지면 탐색 종료

public class BOJ_14502 {
    static int N,M;
    static int[][] map = null;
    static int[][] visited = null;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static Queue<int[]> qVirus = new LinkedList<>();    // 최초 바이러스 위치를 저장하는 큐
    static Queue<int[]> q = new LinkedList<>();         // 감염 영역을 계산할 때 사용하는 큐
    static int min = Integer.MAX_VALUE;     // 감염 영역 수의 최솟값

    private static void BFS() {
        int cnt = 0;    // 감염된 영역 수
        q = new LinkedList<int[]>(qVirus);  // 큐 복사
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[1];
            int y = now[0];

            for (int i = 0; i < 4; i++) {
                int nX = x + dx[i];
                int nY = y + dy[i];

                if(nX<0||nY<0||nX>=M||nY>=N){   // map 범위를 벗어날 때 처리
                    continue;
                }
                if(map[nY][nX]!=0 || visited[nY][nX]==1){	// 벽, 바이러스가 있는 자리이거나 이미 방문했을 때 처리
                    continue;
                }
                q.offer(new int[]{nY,nX});
                visited[nY][nX] = 1;
                cnt++;  // 큐에 감염될 구역을 넣을 때마다 +1;
            }
            if(cnt>min) {   // 현재까지 감염된 구역 수가 이전에 탐색했던 최솟값보다 크면 종료
                return;
            }
        }
        min = cnt;  // 끝까지 탐색되었다면 이전에 탐색했던 최솟값보다 작거나 같음.
    }

    private static void changeMap(int x, int y, int num) {
        map[y][x] = num;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visited = new int[N][M];
        int blanks = 0;

        int input;
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                input = sc.nextInt();
                if(input == 0) {
                    blanks++;
                }
                if(input == 2) {
                    qVirus.offer(new int[] {i,j});
                }
                map[i][j] = input;
            }
        }

        // 연구소(map)을 1차원으로 간주하여 벽 세움.
        int size = N*M;
        for(int i = 0; i<size-2; i++) {
            int xi = i%M;
            int yi = i/M;
            if(map[yi][xi]!=0) {
                continue;
            }
            changeMap(xi,yi,1); // 첫번째 벽 세운 곳을 map에 최신화

            for(int j = i+1; j<size-1; j++) {
                int xj = j%M;
                int yj = j/M;
                if(map[yj][xj]!=0) {
                    continue;
                }
                changeMap(xj,yj,1); // 두번째 벽 세운 곳을 map에 최신화
                for(int k = j+1; k<size; k++) {
                    int xk = k%M;
                    int yk = k/M;
                    if(map[yk][xk]!=0) {
                        continue;
                    }
                    changeMap(xk,yk,1);    // 세번째 벽 세운 곳을 map에 최신화
                    BFS();
                    visited = new int[N][M];    // 탐색 종료 후 visited 초기화
                    changeMap(xk,yk,0);    // 세번째 벽 원상복구
                }
                changeMap(xj,yj,0); // 두번째 벽 원상복구
            }
            changeMap(xi,yi,0); // 첫번째 벽 원상복구
        }
        System.out.println(blanks - min - 3);   // 총 빈칸 수 - 감염된 영역 - 벽이 차지했던 칸
    }
}
