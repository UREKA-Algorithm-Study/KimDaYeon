import java.util.*;

class Solution {
    static int[] parent;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        parent = new int[n];
        // 대표 노드를 자기 자신으로 초기화
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 모든 간선(컴퓨터 연결)을 순회하면서 유니온 파인드 수행
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        // 각 노드의 최종 대표를 확인하여 네트워크의 수를 카운트
        for(int i = 0; i < n; i++) {
            if(parent[i] == i) answer++;
        }

        return answer;
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b) parent[b] = a;
    }

    static int find(int a) {
        if(parent[a] == a) return a;
        else {
            return parent[a] = find(parent[a]); // 재귀 함수 형태로 구현 -> 경로 압축
        }
    }
}
