package week1;

import java.util.*;
public class bj_13335 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 트럭의 수
        int w = sc.nextInt(); // 다리의 길이
        int l = sc.nextInt(); // 최대 하중

        Queue<Integer> truck = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            truck.add(sc.nextInt()); // 트럭의 무게
        }

        int ans = 0;
        int weight = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            q.add(0);
        }

        while(!q.isEmpty()) {
            weight-=q.poll();
            ans++;
            if (!truck.isEmpty()) {
                if (truck.peek()+weight<=l) {
                    weight=truck.peek()+weight;
                    q.add(truck.poll());
                } else {
                    q.add(0);
                }
            }
        }
        System.out.println(ans);
    }
}