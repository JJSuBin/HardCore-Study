import java.util.*;

public class BaekJoon_1111 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] num=new int[n];
		
		for(int i=0;i<n;i++)
			num[i]=sc.nextInt();
		
		/*
		 * 숫자의 개수가 1개이거나
		 * 숫자의 개수가 2개이지만 첫 번째 수와 두 번째 수가 다를 경우 다음 수가 여려가지가 될 수 있음
		 */
		if(n==1||(n==2&&(num[0]!=num[1])))
			System.out.println("A");
		// 숫자의 개수가 2개이면서 첫 번째 수와 두 번째 수가 같은 경우는 다음 숫자도 같다. 
		else if(n==2)
			System.out.println(num[0]);
		else {
			int a,b;
			
			// 숫자의 개수가 3개 이상이면서 첫 번째 수와 두 번째 수가 동일한 경우
			if(num[1]==num[0]) {
				a=1;
				b=0;
			}
			// f(n)=f(n-1)*a+b 풀어서 a와 b 구해주기
			else {
				a=(num[2]-num[1])/(num[1]-num[0]);
				b=num[1]-(num[0]*a);
			}
			
			int index=1;
			for(;index<n;index++)
				if(num[index]!=(num[index-1]*a+b))
					break;
			
			if(index!=n) // 숫자를 구하지 못하는 경우
				System.out.println("B");
			else
				System.out.println(num[n-1]*a+b);
		}
	}

}
