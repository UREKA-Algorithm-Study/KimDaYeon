import java.util.*;
// 재귀로 모든 노드에 벽을 3개 세운다
// 바이러스를 bfs로 퍼뜨린다 (방문하지 않은 노드는 안전영역)
// 0의 갯수를 구한다
// -> 이 값을 max값과 비교하여 최대값을 구한다
public class Main{
    //  0은 빈 칸, 1은 벽, 2는 바이러스
    static int N,M;
    static int[][] arr;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M ; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        // dfs로 바이러스 주변에 벽을 전부 3개씩 세워보기
        dfs(0);
        // 최대값 출력
        System.out.println(max);
    }
    static int max=0;

    private static void dfs(int x) {
        // 벽 3칸을 세웠을 때 bfs를 실행해서 바이러스를 퍼뜨린다
        if (x==3) {
            int cnt = bfs();
            max=Math.max(cnt, max);
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j]==0) {
                    arr[i][j]=1;
                    dfs(x+1);
                    arr[i][j]=0;
                }
            }
        }
    }

    // 바이러스인 2부터 시작하여 bfs 실행
    private static int bfs() {
        Queue<int []> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j]==2) {
                    visited[i][j]=true;
                    q.add(new int[]{i, j});
                }
            }
        }
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        while(!q.isEmpty()) {
            int[] now = q.poll();
            // 현재 위치에서 상하좌우로 이동
            for (int i = 0; i < 4; i++) {
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];

                if (nx>=0 && ny>=0 && nx<N && ny<M
                        && arr[nx][ny]==0 && !visited[nx][ny]) {
                    visited[nx][ny]=true;
                    q.add(new int[] {nx,ny});
                }
            }
        }
        // 0이면서 && 방문한 노드가 아닌 곳 => 안전영역
        int cnt=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j]==0 && !visited[i][j]) cnt++;
            }
        }
        return cnt;
    }
}