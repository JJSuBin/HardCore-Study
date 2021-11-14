import java.util.*;

public class BaekJoon_15666 {
	static int n,m;
	static int[] arr,print;
	// 입력 순서를 지키기 위해 HashSet이 아닌 LinkedHashSet을 사용해야 한다. 
	static LinkedHashSet<String> set=new LinkedHashSet<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		arr=new int[n];
		print=new int[m];
		
		for(int i=0;i<n;i++)
			arr[i]=sc.nextInt();
		
		Arrays.sort(arr); // 정렬된 배열 사용
		dfs(0,0);
		
		// LinkedHashSet에 저장된 수열 출력
		Iterator iter=set.iterator();
		while(iter.hasNext())
			System.out.println(iter.next());
	}
	
	static void dfs(int depth, int start) {
		// 선택된 m개의 숫자로 만들어진 수열을 LinkedHashSet에 저장
		if(depth==m) {
			StringBuilder sb=new StringBuilder();
			for(int i:print)
				sb.append(i).append(" ");
			set.add(sb.toString());
			return;
		}
		
		// 숫자를 중복하여 선택해도 되기 때문에 visited 배열이나 for문의 시작을 지정하지 않아도 됨
		for(int i=start;i<n;i++) {
			print[depth]=arr[i];
			dfs(depth+1,i);
		}
	}
}
