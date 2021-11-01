import java.util.*;

// 후위 표기식 계산
public class BaekJoon_1935 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String str=sc.next();
		
		double[] num=new double[27];
		for(int i=0;i<n;i++)
			num[i]=sc.nextDouble();
		
		Stack<Double> stack=new Stack<>();
		for(int i=0;i<str.length();i++) {
			int temp=(int)str.charAt(i);
			
			if(temp>=65&&temp<=90) // 숫자라면 스택에 삽입
				stack.add(num[temp-65]);
			
			else { // 연잔자라면 스택에서 두 개의 숫자를 꺼내 연산 후 스택에 삽입
				double a=stack.pop();
				double b=stack.pop();
				
				switch(temp) {
				case 42: // 곱셈
					stack.push(b*a);
					break;
				case 43: // 덧셈
					stack.push(b+a);
					break;
				case 47: // 나눗셈
					stack.push(b/a);
					break;
				case 45: // 뺄셈
					stack.push(b-a);
					break;
				}
			}
		}
		System.out.printf("%.2f",stack.pop()); // 소숫점 둘째 자리까지 출력
	}
}
