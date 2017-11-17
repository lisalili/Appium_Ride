
public class A {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a={1,3,4,7};
		int[] b={2,5,8,9};
		
		int[] c={1,2,1,1,2,3,1,1,1,1,1,3,4,1};
		mert(a,b,c);
		

	}

	private static void mert(int[] a, int[] b,int[] c) {
		int[] d= new int[a.length+b.length];
		int i;
		int j;
//		 for(i =0,j=0; i < a.length ,j<b.length;){
//			 
//		 }
		int count = 1;
		int n=c[0];
		 
		for(int z=1; z<c.length;z++){
			if(count == 0 ){
				n=c[z];
				count = 1;
			}
			if(n ==c[z]){
				count++;
			 }else{
				 count--;
			 }	
		}
		System.out.println(n);
		
	}
	

}
