import java.util.Scanner;
import java.util.Stack;

public class BOJ_2468 {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static Stack<int[]> s = new Stack<>();
    static int[][] map;         // 입력 받은 높이 정보를 저장함.
    static int[][] visited;
    static int N;

    // 단순 BFS. 주어진 좌표부터 탐색함.
    public static void DFS(int y, int x) {
        s.push(new int[] {y,x});
        visited[y][x] = 1;

        while(!s.isEmpty()) {
            int[] nowPos = s.pop();

            for(int i = 0; i<4; i++) {
                int nx = nowPos[1]+dx[i];
                int ny = nowPos[0]+dy[i];
                if(nx<0||ny<0||nx>=N||ny>=N) {
                    continue;
                }
                if(map[ny][nx]==0 || visited[ny][nx]==1) {
                    continue;
                }
                s.push(new int[] {ny,nx});
                visited[ny][nx] = 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        visited = new int[N][N];

        // 맵 정보 입력과 동시에 최고 높이를 확인함
        int maxHeight = 0;
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                int input = sc.nextInt();
                map[i][j] = input;
                maxHeight = maxHeight>input? maxHeight:input;
            }
        }

        // 물 높이를 0부터 이전 단계에서 확인해둔 최고 높이까지 1칸씩 채우며 DFS 반복
        int maxCnt = 0;
        for(int i = 0; i<=maxHeight; i++) {

            // 높이가 i 이하인 지역은 0으로 바꿈(물에 잠긴 곳 처리)
            for(int j = 0; j<N; j++) {
                for(int k = 0; k<N; k++) {
                    if(map[j][k]<=i) {
                        map[j][k] = 0;
                    }
                }
            }

            // DFS로 잠기지 않은 구역 수 확인
            int cnt = 0;
            for(int j = 0; j<N; j++) {
                for(int k = 0; k<N; k++) {
                    if(map[j][k]!=0 && visited[j][k]==0) {
                        DFS(j,k);
                        cnt++;
                    }
                }
            }
            maxCnt = maxCnt>cnt?maxCnt:cnt;
            visited = new int[N][N];	// 다 썼으니 초기화
        }
        System.out.println(maxCnt);
    }
}
