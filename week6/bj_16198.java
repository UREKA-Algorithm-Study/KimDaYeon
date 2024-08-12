import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        System.out.println(maxEnergy(list));
    }
    private static int maxEnergy(ArrayList<Integer> list ){
        if(list.size()==2) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < list.size()-1; i++) {
            // 에너지 모으기
            int sum=list.get(i-1)*list.get(i+1);
            // 구슬 제거
            int removed = list.remove(i);
            max = Math.max(max, sum+maxEnergy(list));
            list.add(i, removed); // 백트래킹
        }
        return max;
    }
}
