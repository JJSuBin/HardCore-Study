import java.util.*;

public class BaekJoon_1062 {
	static int n,k,result=Integer.MIN_VALUE;
	static boolean[] visited=new boolean[26];
	static String[] word;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt(); // 단어의 개수
		k=sc.nextInt(); // 가르칠 수 있는 글자 수
		
		word=new String[n];
		
		for(int i=0;i<n;i++) {
			String str=sc.next();
			
			str=str.replace("anta","");
			str=str.replace("tica","");
			
			word[i]=str;
		}
		
		/*
		 * 모든 단어는 "anta"로 시작되고, "tica"로 끝나기 때문에 기본적으로 
		 * a,c,n,i,t는 읽을 수 있어야 한다. 가르칠 수 있는 글자가 5개가 안된다면 어떤 단어도 읽을 수 X
		 */
		if(k<5) {
			System.out.println(0);
			System.exit(0);
		}
		else if(k==26) { // 26개 알파벳을 모두 가르칠 수 있다면 모든 단어 읽을 수 있음
			System.out.println(n);
			System.exit(0);
		}
		
		// a,c,n,i,t는 미리 방문처리
		visited['a'-'a']=true;
		visited['c'-'a']=true;
		visited['i'-'a']=true;
		visited['n'-'a']=true;
		visited['t'-'a']=true;
		
		teach(0,0);
		System.out.println(result);
	}
	
	// 중복을 없애 시간초과를 받지 않기 위해 start 변수 사용
	static void teach(int depth, int start) {
		if(depth==k-5) {
			result=Math.max(result, Count());
			
			return;
		}
		
		// 백트래킹
		for(int i=start;i<26;i++) {
			if(!visited[i]) {
				visited[i]=true;
				teach(depth+1,i+1);
				visited[i]=false;
			}
		}
	}
	
	// 선택한 글자로 n개의 단어 중 몇개의 단어를 읽을 수 있는지 count하는 함수
	static int Count() {
		int count=0;
		
		for(int i=0;i<n;i++) {
			boolean read=true;
			for(int j=0;j<word[i].length();j++) {
				if(!visited[word[i].charAt(j)-'a']) {
					read=false;
					break;
				}
			}
			if(read)
				count++;
		}
		
		return count;
	}
}
