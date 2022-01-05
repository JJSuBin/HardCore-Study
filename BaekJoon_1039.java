import java.util.*;

class Point_1039 {
	int num, count;
	
	public Point_1039(int num, int count) {
		this.num=num;
		this.count=count;
	}
}

public class BaekJoon_1039 {
	static int n,k,max=-1;
	static boolean[][] visited;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		n=sc.nextInt();
		k=sc.nextInt();
		
		visited=new boolean[1000001][k+1];
		
		bfs();
		System.out.println(max);
	}

	static void bfs() {
		Queue<Point_1039> q=new LinkedList<>();
		q.offer(new Point_1039(n,0));
		
		while(!q.isEmpty()) {
			Point_1039 p=q.poll();
			
			// k번 이동해서 만들어진 수라면 max 갱신
			if(p.count==k) {
				max=Math.max(max, p.num);
				continue;
			}
			
			// 현재 정수의 각 자리수를 swap 할 수 있는 모든 경우의 수 탐색 
			int length=String.valueOf(p.num).length();
			for(int i=0;i<length;i++) {
				for(int j=i+1;j<length;j++) {
					int result=swap(p.num,i,j);
					
					/*
					 * 두 자릿수를 swap 할 수 있고 p.count+1번째 이동 횟수에서 
					 * 만들어진 적 없는 수라면 큐에 삽입
					 */
					if(result!=-1&&!visited[result][p.count+1]) {
						q.offer(new Point_1039(result,p.count+1));
						visited[result][p.count+1]=true;
					}
				}
			}
		}
	}
	
	// i번째 수와 j번째 수를 바꾸는 함수
	static int swap(int num, int i, int j) {
		// 정수 num을 각 자리수를 배열에 따로 저장
		char[] ch=String.valueOf(num).toCharArray();
		
		// swap한 결과의 첫 번쨰 자리수가 0이라면 불가능한 경우
		if(i==0&&ch[j]=='0')
			return -1;
		
		// swap
		char temp=ch[i];
		ch[i]=ch[j];
		ch[j]=temp;
		
		// 배열을 다시 정수로 변환하여 return
		return Integer.parseInt(new String(ch));
	}
}
