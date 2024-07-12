import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 지방의 수
        int[] arr = new int[n]; // 예산요청 금액
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int m = sc.nextInt(); // 총 예산

        Arrays.sort(arr);
        int ans=0; // 상한액

        int pl = 0;
        int pr = arr[n-1];

        while(pl<=pr) {
            int pc = (pl+pr)/2;
            int sum=0;
            for (int i = 0; i < n; i++) {
                sum+=Math.min(pc, arr[i]);
            }

            if(sum<=m) {
                pl=pc+1;
                ans=pc;
            } else pr=pc-1;
        }
        System.out.println(ans);
    }
}