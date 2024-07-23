import java.io.*;
import java.util.*;

public class 2667 {

    /**
     * n : 배열 크기
     * apartMap : 아파트 현황 배열
     * visited : 방문 체크 배열
     * dx, dy : 상, 하, 좌, 우 체크 배열
     * apartCount : 단지 내 아파트 개수를 담을 배열
     */
    private static int n;
    private static int[][] apartMap;
    private static int[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static ArrayList<Integer> apartCount = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 크기 입력
        n = Integer.parseInt(br.readLine());

        // 배열 크기 할당
        apartMap = new int[n][n];
        visited = new int[n][n];

        // 아파트 현황 입력
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                apartMap[i][j] = input.charAt(j) - '0';
            }
        }

        // 단지 개수 변수
        int complexCnt = 0;

        // 단지 현황 전체 순회
        for (int i = 0; i < apartMap.length; i++) {
            for (int j = 0; j < apartMap[i].length; j++) {
                // 현재 좌표가 방문하지 않은 아파트인 경우
                if (apartMap[i][j] == 1 && visited[i][j] == 0) {
                    // 방문 처리
                    visited[i][j] = 1;
                    // 단지 내 아파트 개수 확인
                    apartCount.add(bfs(i, j)) ;
                    // 단지 증가
                    complexCnt++;
                }
            }
        }

        // 단지 개수 출력
        System.out.println(complexCnt);
        // 오름차순 정렬
        Collections.sort(apartCount);
        // 각 단지 내 아파트 수 출력
        for (Integer apart : apartCount) {
            System.out.println(apart);
        }
    }

    public static int bfs(int i, int j) {
        // 단지 내 아파트 개수 변수
        int apartCount = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        // 시작 좌표 삽입
        queue.add(new int[]{i, j});

        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 맨 앞 좌표 꺼내기
            int[] start = queue.poll();
            // 단지 내 아파트 개수 증가
            apartCount++;
            // 좌표 할당
            int x = start[0], y = start[1];

            // 좌표 기준 상, 하, 좌, 우 순회
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k], ny = y + dy[k];
                // 좌표가 배열 범위 밖이거나 아파트가 아니거나 방문한 아파트인 경우
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || apartMap[nx][ny] == 0 || visited[nx][ny] == 1) {
                    // 무시
                    continue;
                }

                // 방문처리
                visited[nx][ny] = 1;
                // 큐에 좌표 삽입
                queue.add(new int[]{nx, ny});
            }
        }
        // 단지 내 아파트 개수 리턴
        return apartCount;
    }
}
