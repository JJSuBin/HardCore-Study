import java.util.*;

public class BaekJoon_1166 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		long n=sc.nextInt();
		long l=sc.nextInt();
		long w=sc.nextInt();
		long h=sc.nextInt();
		
		double start=0;
		double end=Math.max(l,Math.max(w, h));
		
		// 이분탐색
		for(int i=0;i<10000;i++) {
			double mid=(start+end)/2; // 정육면체의 한 변의 길이
			
			// 직육면체에 들어갈 수 있는 정육면체의 개수 구해 n과 비교
			if((long)(l/mid)*(long)(w/mid)*(long)(h/mid)>=n)
				start=mid;
			else
				end=mid;
		}
		System.out.println(start);
	}

}
