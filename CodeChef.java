		/* package codechef; // don't place package name! */
		import java.util.*;

import java.lang.*;
		//import java.math.*;
		import java.io.*;
		
		/* Name of the class has to be "Main" only if the class is public. */
		 class CodeChef
		{
		 public static class FastReader {
		
				BufferedReader b;
				StringTokenizer s;
				public FastReader() {
					b=new BufferedReader(new InputStreamReader(System.in));
				}
				String next() {
					while(s==null ||!s.hasMoreElements()) {
						try {
							s=new StringTokenizer(b.readLine());
						}
						catch(IOException e) {
							e.printStackTrace();
						}
					}
					return s.nextToken();
				}
				public int nextInt() {
					return Integer.parseInt(next());
				}
				public long nextLong() {
					return Long.parseLong(next());
				}
				public double nextDouble() {
					return Double.parseDouble(next());
				}
				String nextLine() {
					String str="";
					try {
						str=b.readLine();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
					return str;
				}
				boolean hasNext() {
				    if (s != null && s.hasMoreTokens()) {
				        return true;
				    }
				    String tmp;
				    try {
				        b.mark(1000);
				        tmp = b.readLine();
				        if (tmp == null) {
				            return false;
				        }
				        b.reset();
				    } catch (IOException e) {
				        return false;
				    }
				    return true;
				}
			
		}
		public static int md=(int)(1e9+7);
		static long dp[][];
		static HashSet<Integer> hs;
		static int xx[]= {-1,1,0,0,1,1,-1,-1};
		static int yy[]= {0,0,1,-1,1,-1,1,-1};
		static class Trie{
		    Trie a[];
			public Trie() {
				this.a=new Trie[2];
			}
		}
			public static void main (String[] args) throws java.lang.Exception
			{	
				// your code goes here
			FastReader sc=new FastReader();
			BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
			
			if(sc.hasNext()){
//				sieve();
				int t=1;
				while(t--!=0) {
					int n=sc.nextInt();
					int q=sc.nextInt();
					
					int cnt[]=new int[n+1];
					TreeMap<Integer,Integer> tm=new TreeMap<>(Collections.reverseOrder());
					int a[]=new int[n];
					for(int i=0;i<n;i++) {
						a[i]=sc.nextInt();
						cnt[a[i]]++;
					}
					for(int i=0;i<cnt.length;i++)if(cnt[i]>0)tm.put(cnt[i],tm.getOrDefault(cnt[i],0)+1);
					int ans=0;
					long an=0;
					for(int el:tm.keySet()) {
						ans+=tm.get(el);
						int val=tm.higherKey(el)==null?0:tm.higherKey(el);
						long curr=el;
						while(curr>val) {
							an+=curr*ans;
							curr--;
						}
					}
					System.out.println("ans="+an+" "+tm);
					for(int i=0;i<q;i++) {
						int ind=sc.nextInt()-1;
						int val=sc.nextInt();
						an-=cnt[a[ind]];
						cnt[a[ind]]--;
						a[ind]=val;
						cnt[a[ind]]++;
						an+=cnt[a[ind]];
						log.write(an+"\n");
					}
				}
				
				  log.flush();
			
			}
	}
static boolean check(int a[]) {
	int xor=0,and=a[0];
	for(int i=0;i<a.length;i++) {
		xor^=a[i];
		and&=a[i];
		if(and<xor)return true;
	}
	xor=0;and=a[a.length-1];
	for(int i=a.length-1;i>=0;i--) {
		xor^=a[i];
		and&=a[i];
		if(and>xor)return true;
	}
	return false;
}			
static int abs(int a) {
	return a>=0?a:-a;
}
static long abs(long a) {
	return a>=0?a:-a;
}	
			static void insert(Trie proot,int num){
			    Trie root=proot;
			    for(int i=30;i>=0;i--){
			        int bit=(num&(1<<i))>0?1:0;
			        if(root.a[bit]==null)root.a[bit]=new Trie();
			        root=root.a[bit];
			    }
			}    
			static Trie delete(Trie root,int num,int i){
			    if(i==-1)return null;
			    if((num&(1<<i))>0){
			        root.a[1]=delete(root.a[1],num,i-1);
			        if(root.a[1]==null && root.a[0]==null)return null;
			        else return root;
			    }
			    else {
			        root.a[0]=delete(root.a[0],num,i-1);
			        if(root.a[1]==null && root.a[0]==null)return null;
			        else return root;
			    }
			} 
			static int find(Trie proot,int num){
			    Trie root=proot;
			    int ans=0;
			    if(root==null)return 0;
			    for(int i=30;i>=0;i--){
			        int bit=(num&(1<<i))>0?1:0;
			        if(root.a[bit]!=null){
			            // System.out.print("1");
			            ans+=(1<<i)*bit;
			            root=root.a[bit];
			        }
			        else if(root.a[1-bit]!=null){
			            // System.out.print("0");
			        	  ans+=(1<<i)*(1-bit);
			            root=root.a[1-bit];
			        }
			        else return 0;
			    }
			    // System.out.println();
			    return ans;
			}   
static int eval(int x,int y,char a[],boolean vis[][]) {
	if(x<-10 || y<-10 || x>10 || y>10)return 0;
	if(vis[x+10][y+10])return 0;
	int ans=1;
	vis[x+10][y+10]=true;
	for(int k=0;k<4;k++) {
		if(a[k]=='0')continue;
		ans+=eval(x+xx[k],y+yy[k],a,vis);
	}
	return ans;
}
			static int find(int el,int p[]) {
			    if(p[el]<0)return el;
			    return p[el]=find(p[el],p);
			}
			static boolean union(int a,int b,int p[]) {
			        int p1=find(a,p);
			        int p2=find(b,p);
			        if(p1>=0 && p1==p2)return false;
			        else {
			            if(p[p1]<p[p2]) {
			                p[p1]+=p[p2];
			                p[p2]=p1;
			            }
			            else {
			                p[p2]+=p[p1];
			                p[p1]=p2;
			            }
			            return true;
			        }

			}     			
static void eval(ArrayList<Integer> ar,int i) {
	if(i>=ar.size()) {
		num(ar);
		return;
	}
	for(int j=i;j<ar.size();j++) {
		 int tp=ar.get(j);
		 ar.set(j,ar.get(i));
		 ar.set(i, tp);
		 eval(ar,i+1);
		 tp=ar.get(j);
		 ar.set(j,ar.get(i));
		 ar.set(i, tp);
		 
	}
}			
			
			static final int MAXN = 10000000;
		    static int spf[] = new int[MAXN];
		    static void sieve()
		    {
		        spf[1] = 1;
		        for (int i=2; i<MAXN; i++)
		            spf[i] = i;

		        for (int i=4; i<MAXN; i+=2)
		            spf[i] = 2;
		      
		        for (int i=3; i*i<MAXN; i++)
		        {
		            if (spf[i] == i)
		            {
		                for (int j=i*i; j<MAXN; j+=i)
		                    if (spf[j]==j)
		                        spf[j] = i;
		            }
		        }
		    }
		    static HashSet<Integer> get(int x,HashSet<Integer> ret)
		    {
//		        HashSet<Integer> ret = new HashSet<>();
		        while (x != 1)
		        {
		            ret.add(spf[x]);
		            x = x / spf[x];
		        }
		        return ret;
		    }		
static void num(ArrayList<Integer> ar) {
	if(ar.get(0)==0)return;
	int nm=0;
	for(int i=0;i<ar.size();i++) {
		nm*=10;
		nm+=ar.get(i);
	}
	hs.add(nm);
}		    
  
			
	static long ev(long a) {
		return ((a)*(a+1))/2;
	}		
	//static long eval(int a[],int i,int md,int x) {
	//	if(i>=a.length)return md==0?0:(long)(1e15);
	//	return Math.min(a[i]+eval(a,i+1,(md+a[i]%x)%x,x),eval(a,i+1,md,x));
	//}		
	//		
static void eval(ArrayList<ArrayList<Integer>> ar,int src,int pr,int cnt,int d[],int par[]) {
	d[src]=cnt+1;
	par[src]=pr;
	for(int el:ar.get(src)) {
		if(pr==el)continue;
		eval(ar,el,src,cnt+1,d,par);
	}
}

	

			static int max(int a,int b) {return a>=b?a:b;}			
			static int min(int a,int b) {return a>=b?b:a;}	
			static long max(long a,long b) {return a>=b?a:b;}	
			static long min(long a,long b) {return a>=b?b:a;}	
			
		static HashMap<Integer,Integer> prime(int num) {
			HashMap<Integer,Integer> hm=new HashMap<>();
			int ct=0;
			int mx=0;
			int nm=0;
			while(num%2==0) {
				num/=2;
				ct++;
			}
			if(ct>0)hm.put(2,ct);
			for(int i=3;i*i<=num;i+=2) {
				ct=0;
				while(num%i==0) {
					num/=i;
					ct++;
				}
				if(ct>0)hm.put(i,ct);
			}
			if(num>1) {
				hm.put(num,1);
			}
			return hm;
		}	
		
				public static class pair{
					int a;
					long b;
					public pair(int a,long b) {
						this.a=a;
						this.b=b;
					}
				}
					public static class trip{
						int a,b;
						int c;
						int d;
						public trip(int a,int b,int c,int d) {
							this.a=a;
							this.b=b;
							this.c=c;
							this.d=d;
						}
						public int compareTo(trip q) {
							return  this.b-q.b;
						}
					}
								
			public static int mm=(int)(1e9+7);
			static long mul(long a,long b) {
				return ((a%mm)*(b%mm))%mm;
			}	
			static long sub(long a,long b) {
				long val=((a%mm)-(b%mm)+mm)%mm;
				return val;
			}
		static long inv(long a) {
			return pow(a,mm-2);
		}
		static long div(long a,long b) {
			long val=inv(b);
			return (val%mm*a%mm)%mm;
		}	
		static long ncr(int n, int r){
			    if(r>n-r)r=n-r;
			    long ans=1;
			    for(int i=0;i<r;i++){
			          ans=(ans%mm*(n-i)%mm)%mm;
			          ans=div(ans,i+1);
			    }
			    return ans;
		} 		
			static long pow(long a, long pw) {
				long temp;
				if(pw==0)return 1;
				temp=pow(a,pw/2);
				if(pw%2==0)return mul(temp,temp);
				return mul(a,mul(temp,temp));
				
			}	
			static long poww(long a, long pw) {
				long temp;
				if(pw==0)return 1;
				temp=poww(a,pw/2);
				if(pw%2==0)return temp*temp;
				return a*temp*temp;
				
			}
			static int pow(int a, int pw) {
				int temp;
				if(pw==0)return 1;
				temp=pow(a,pw/2);
				if(pw%2==0)return temp*temp;
				return a*temp*temp;
				
			}	
			public static int m=(int)(1e9+7);
//		public static int add(int a,int b) {
//			return ((a%m)+(b%m))%m;
//		}
		public static long add(long a,long b) {
			return ((a%m)+(b%m))%m;
		}
		public static int mul(int a,int b) {
			long p=a;
			long q=b;
			long ans=((p%m)*(q%m))%m;
			return (int)(ans);
		}
	//	static TreeSet<Integer> prime(int n){
	//		boolean vis[]=new boolean[n+1];
	//		Arrays.fill(vis, true);
	//		vis[0]=vis[1]=false;
	//		for(int i=2;i*i<=n;i++) {
	//			if(vis[i]) {
	//			for(int j=2*i;j<=n;j+=i) {
	//				vis[j]=false;
	//			}
	//			}
	//		}
	//		TreeSet<Integer> ar=new TreeSet<>();
	//		for(int i=0;i<=n;i++) {
	//			if(vis[i])ar.add(i);
	//		}
	//		return ar;
	//	}
		static int gcd(int a,int b) {
			if(b==0)return a;
			return gcd(b,a%b);
		}	
		static long gcd(long a,long b) {
			if(b==0)return a;
			return gcd(b,a%b);
		}
		static void mergesort(int[] a,int start,int end) {
			if(start>=end)return;
			int mid=start+(end-start)/2;
			mergesort(a,start,mid);
			mergesort(a,mid+1,end);
			merge(a,start,mid,end);
		}
		static void merge(int[]a, int start,int mid,int end) {
			int ptr1=start;
			int ptr2=mid+1;
			int b[]=new int[end-start+1];
			int i=0;
			while(ptr1<=mid && ptr2<=end) {
				if(a[ptr1]<=a[ptr2]) {
					b[i]=a[ptr1];
					ptr1++;
					i++;
				}
				else {
					b[i]=a[ptr2];
					ptr2++;
					i++;
				}
			}
			while(ptr1<=mid) {
				b[i]=a[ptr1];
				ptr1++;
				i++;
			}
			while(ptr2<=end) {
				b[i]=a[ptr2];
				ptr2++;
				i++;
			}
			for(int j=start;j<=end;j++) {
				a[j]=b[j-start];
			}
		}
		static void mergesort(ArrayList<pair> a,int start,int end) {
			if(start>=end)return;
			int mid=start+(end-start)/2;
			mergesort(a,start,mid);
			mergesort(a,mid+1,end);
			merge(a,start,mid,end);
		}
		static void merge(ArrayList<pair> ar, int start,int mid,int end) {
			int ptr1=start;
			int ptr2=mid+1;
			ArrayList<pair> b=new ArrayList<>();
			int i=0;
			while(ptr1<=mid && ptr2<=end) 
			{
				long n1=ar.get(ptr1).a*(long)(ar.get(ptr2).b);
				long n2=ar.get(ptr1).b*(long)(ar.get(ptr2).a);
				if(n1>n2) {
					b.add(new pair(ar.get(ptr1).a,ar.get(ptr1).b));
					ptr1++;
					i++;
				}
				else {
					b.add(new pair(ar.get(ptr2).a,ar.get(ptr2).b));
					ptr2++;
					i++;
				}
			}
			while(ptr1<=mid) {
				b.add(new pair(ar.get(ptr1).a,ar.get(ptr1).b));
				ptr1++;
				i++;
			}
			while(ptr2<=end) {
				b.add(new pair(ar.get(ptr2).a,ar.get(ptr2).b));
				ptr2++;
				i++;
			}
			for(int j=start;j<=end;j++) {
				ar.set(j, b.get(j-start));
			}
		}
		
		}
		
							