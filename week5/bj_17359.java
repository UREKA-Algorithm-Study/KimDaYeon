import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int minCnt = Integer.MAX_VALUE; // 전환되는 최소 횟수

    public static void main(String[] args)  throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];

        int confirmCnt=0;

        for (int i = 0; i < n; i++) {
            String cur = br.readLine();
            arr[i]=cur;
            for (int j = 1; j < cur.length(); j++) {
                if (cur.charAt(j - 1) != cur.charAt(j))
                    confirmCnt++; // 숫자가 달라질 경우 카운트
            }
        }

        boolean[] visited = new boolean[n];
        List<String> list = new ArrayList<>(); // 순열 생성을 위한 리스트

        // 모든 가능한 순열 탐색
        permutations(arr, list, visited);

        // 전체 숫자가 달라질 경우와 마지막 글자와 첫번째 글자가 다를 경우를 합산
        System.out.println(confirmCnt+minCnt);
    }

    private static void permutations(String[] arr, List<String> list, boolean[] visited) {
        if (list.size() == n) {
            // 순열이 완성되면, 마지막 글자와 첫번째 글자가 다를 경우를 계산
            int cal = calculate(list);
            // 최소로 달라지는 경우 업데이트
            minCnt = Math.min(minCnt, cal);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(arr[i]);
                permutations(arr, list, visited);
                list.remove(list.size() - 1); // Backtracking
                visited[i] = false;
            }
        }
    }

    private static int calculate(List<String> list) {
        int cnt = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            String currentString = list.get(i);
            String nextString = list.get(i + 1);
            if (currentString.charAt(currentString.length() - 1) != nextString.charAt(0)) {
                cnt++;
            }
        }
        return cnt;
    }
}
