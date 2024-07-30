import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11725 {
	static int N;
	static int[] parents;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parents = new int[N + 1]; // 부모를 저장하는 배열
		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 양방향 연결
			graph[a].add(b);
			graph[b].add(a);
		}

		dfs(1); // 루트 노드(=1)부터 시작하기
		parents[1] = 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; i++) {
			sb.append(parents[i]).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int x) {
		// 연결된 노드 탐색하기
		for (int i = 0; i < graph[x].size(); i++) {
			int j = graph[x].get(i);
			if (parents[j] == 0) { // 부모가 정해지지 않음 == 아직 탐색하지 않음
				parents[j] = x;
				dfs(j);
			}
		}
	}
}