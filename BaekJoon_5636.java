import java.util.*;

public class BaekJoon_5636 {
	static StringBuilder sb=new StringBuilder();
	static String str;
	static int max,length;
	static int[] num;
	static boolean[] isPrime=new boolean[100001];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		Eratosthenes();
		
		while(true) {
			str=sc.next();
			max=Integer.MIN_VALUE;
			length=str.length();
			num=new int[256];
			
			if(str.equals("0"))
				break;
			
			for(int i=0;i<length;i++) 
				num[i]=Integer.parseInt(str.charAt(i)+"");
			
			for (int i=0;i<length;i++) { 
	            int tmp=num[i];
	            
	            if(!isPrime[tmp])
	            	max=Math.max(max,tmp);
	        }
			
	        for (int i=0;i<length-1;i++) {
	            int tmp=0;
	            for (int j=i;j<=i+1;j++)
	                tmp=tmp*10+num[j];
	            
	            if(!isPrime[tmp])
	            	max=Math.max(max,tmp);
	        }
	        for (int i=0;i<length-2; i++) {
	            int tmp = 0;
	            for (int j=i;j<=i+2;j++)
	                tmp=tmp*10+num[j];
	            
	            if(!isPrime[tmp])
	            	max=Math.max(max,tmp);
	        }
	        for (int i=0;i<length-3;i++) {
	            int tmp=0;
	            for(int j=i;j<=i+3;j++)
	                tmp=tmp*10+num[j];
	            
	            if(!isPrime[tmp])
	            	max=Math.max(max,tmp);
	        }
	        for (int i=0;i<length-4;i++) {  
	            int tmp=0;
	            for(int j=i;j<=i+4;j++)
	                tmp=tmp*10+num[j];
	            
	            if(!isPrime[tmp])
	            	max=Math.max(max,tmp);
	        }

			sb.append(max).append('\n');
		}
		
		System.out.println(sb);
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
