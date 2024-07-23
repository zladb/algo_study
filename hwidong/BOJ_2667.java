import java.util.*;

public class BOJ_2667 {
    static int[][] map;
    static int n;
    static Queue<int[]> q = new LinkedList<>();
    static int[][] dir = {{1,0}, {0,1},{-1,0},{0,-1}};

    static int BFS(int x, int y){
        int cnt = 0;
        q.add(new int[]{x, y});
        map[y][x] = 0;
        while (!q.isEmpty()){
            int[] now = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int newX = now[0] + dir[i][1];
                int newY = now[1] + dir[i][0];

                if(newX<0 || newY<0 || newX>=n || newY>=n){
                    continue;
                }
                if(map[newY][newX] == 0){
                    continue;
                }
                q.add(new int[]{newX, newY});
                map[newY][newX] = 0;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];

        sc.nextLine(); // 버퍼에 남은 개행문자 제거
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j)-'0';
            }
        }

        int numOfGroups = 0;    // 그룹의 수
        ArrayList<Integer> apts = new ArrayList<>();    // 각 그룹의 아파트 수를 저장하는 리스트
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 1){
                    apts.add(BFS(j,i));
                    numOfGroups++;
                }
            }
        }

        System.out.printf("%d\n", numOfGroups);
        Collections.sort(apts);
        for(int numOfApt : apts){
            System.out.printf("%d\n", numOfApt);
        }
    }
}
