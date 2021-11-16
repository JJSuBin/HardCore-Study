import java.util.*;

public class BaekJoon_1929 {
	static int n,m;
	static boolean[] isPrime=new boolean[1000001];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		m=sc.nextInt();
		n=sc.nextInt();
		
		Eratosthenes();
		
		for(int i=m;i<=n;i++)
			if(!isPrime[i])
				System.out.println(i);
	}
	
	// 에라토스테네스의 채 : 소수 구하기
	static void Eratosthenes() {
		// true : 소수 아님, false : 소수
		isPrime[0]=isPrime[1]=true;
		
		for(int i=2;i<=Math.sqrt(isPrime.length);i++) {
			if(isPrime[i])
				continue;
			
			for(int j=i+i;j<isPrime.length;j+=i) {
				isPrime[j]=true;
			}
		}
	}
}
