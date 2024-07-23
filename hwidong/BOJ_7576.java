import java.util.*;
// 행: x, j, M
// 열: y, i, N
// {y,x} [i][j]
public class BOJ_7576 {
    static int N,M;
    static int[][] map = null;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        map = new int[N][M];

        // map 입력
        int input;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                input = sc.nextInt();
                if(input==1){       // 입력값이 1일 때, queue에 현재위치 추가
                    q.offer(new int[] {i,j});
                }
                map[i][j] = input;
            }
        }

        // 0인 곳은 아직 방문하지 않은 토마토
        // 1 이상인 곳은 방문하는데까지 걸린 시간
        // 시작위치를 1, 다음위치에 (현재위치+1)을 저장하며 탐색
        while(!q.isEmpty()){
            int[] now = q.poll();
            int x = now[1];
            int y = now[0];

            for (int i = 0; i < 4; i++) {
                int nX = x + dx[i];
                int nY = y + dy[i];

                if(nX<0||nY<0||nX>=M||nY>=N){   // map 범위를 벗어날 때 처리
                    continue;
                }

                if(map[nY][nX]!=0){             // 이미 방문했거나, 토마토가 없는 칸 처리
                    continue;
                }
                q.offer(new int[]{nY,nX});
                map[nY][nX] = map[y][x] + 1;    // (현재 칸에 기록된 날짜 + 1) 저장
            }
        }

        // 배열에 저장된 최댓값 및 방문하지 못한 칸 확인
        int max = 0;
        exit: for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int currentValue = map[i][j];
                if(currentValue==0){    // 방문하지 못한 칸이 존재할 때 처리
                    max=0;
                    break exit;
                }
                max = max > currentValue ? max : currentValue;
            }
        }
        System.out.printf("%d\n",max-1);
    }
}
