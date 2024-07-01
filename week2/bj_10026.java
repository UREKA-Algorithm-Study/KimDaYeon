import java.util.*;
public class Main {
    static int n;
    static char[][] arr;
    static char[][] arrRG;
    static char[] c = {'R', 'G', 'B'};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new char[n][n];
        arrRG = new char[n][n];

        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < n; j++) {
                arr[i][j]=s.charAt(j);
                // 적록색약 배열 생성
                if (s.charAt(j) == 'R') {
                    arrRG[i][j] = 'G';
                } else {
                    arrRG[i][j] = s.charAt(j);
                }
            }
        }

        // 적록색략이 아닌 사람이 봤을 때의 구역의 개수
        int cnt=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j]=='R' || arr[i][j]=='G' || arr[i][j]=='B') {
                    bfs(arr, i, j, arr[i][j]);
                    cnt++;
                }
            }
        }

        //  적록색약인 사람이 봤을 때의 구역의 수
        // 적록색약인 사람의 경우를 처리할 때 배열을 복사하여 사용
        int cnt2=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arrRG[i][j]=='G' || arrRG[i][j]=='B') {
                    bfs(arrRG, i, j, arrRG[i][j]);
                    cnt2++;
                }
            }
        }
        System.out.println(cnt+" "+cnt2);
    }

    private static void bfs(char[][] arr, int x, int y, char c) {
        Queue<int []> q = new LinkedList<>();
        q.add(new int[] {x,y});
        arr[x][y]='1';

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int[] dx = {1,-1,0,0};
            int[] dy = {0,0,-1,1};
            for (int i = 0; i < 4; i++) {
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];
                if(nx>=0 && ny>=0 && nx<n && ny<n && arr[nx][ny]==c) {
                    arr[nx][ny]='1';
                    q.add(new int[] {nx, ny});
                }
            }
        }
    }
}
