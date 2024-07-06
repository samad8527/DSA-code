	//La illaha illalla muhammadur rasul Allah (SAW)
	//Bismillah hir-rehamn nir-rahim
	
	import java.io.*;
	import java.util.*;

	
	public class ComdeFormces {	
		static int x[]= {0,-1,1,0,1,1,-1,-1};
		static int y[]= {-1,0,0,1,1,-1,1,-1};
		public static int m=(int)(1e9+7);
//		static HashMap<Integer,Integer> dp[][];
		static int cnt,nn,ans,bb;
//		static long ans[];
		static long dp[][];
		static long dp2[][];
		static int st[][];
		static long pre[];
		static int lg[];
		 static class Trie{
			    Trie a[];
				public Trie() {
					this.a=new Trie[2];
				}
			}
		 
		 
		 static ArrayList<pair> anis;
		 static ArrayList<Integer> arr;
		 static boolean fg;
		 static int p1,p2,node;
		 static long inf=(long)(1e12);
				public static void main(String[] args) throws Exception{
					// TODO Auto-generated method stub
			FastReader sc=new FastReader();	
			BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
			//		 OutputStream out = new BufferedOutputStream ( System.out );
			int tc=1;
//			sieve();
			int t=sc.nextInt();
			long ct[]= {0,9,4,3,2,1,1,1,1,1};
			while(t--!=0) {
			     int n=sc.nextInt();
			     int m=sc.nextInt();
			     char a[][]=new char[n][m];
			     for(int i=0;i<n;i++) {
			    	 a[i]=sc.next().toCharArray();
			     }
			     int p[]=new int[n*m];
			     Arrays.fill(p, -1);
			     for(int i=0;i<n;i++) {
			    	  for(int j=0;j<m;j++) {
			    		  if(a[i][j]=='#') {
			    			  for(int k=0;k<4;k++) {
			    				  int nx=i+x[k];
			    				  int ny=j+y[k];
			    				  if(nx>=0 && ny>=0 && nx<n && ny<m && a[nx][ny]=='#') {
			    					  int nd1=nx*m+ny;
			    					  int nd2=i*m+j;
			    					  union(nd1,nd2,p);
			    				  }
			    			  }
			    		  }
			    	  }
			     }
			     int max=0;
			     for(int i=0;i<n;i++) {
			    	 int ans=0;
			    	 HashSet<Integer> hs=new HashSet<>();
			    	 for(int j=0;j<m;j++) {
			    		 if(a[i][j]!='#') {
			    			 ans++;
			    			 for(int k=0;k<4;k++) {
			    				  int nx=i+x[k];
			    				  int ny=j+y[k];
			    				  if(nx>=0 && ny>=0 && nx<n && ny<m && a[nx][ny]=='#') {
			    					  int nd1=nx*m+ny;
			    					  if(!hs.contains(find(nd1,p))) {
			    						  hs.add(find(nd1,p));
			    						  ans+=abs(p[find(nd1,p)]);
			    					  }
			    				  }
			    			  }
			    		 }
			    		 else {
			    			 int nd=i*m+j;
			    			 if(!hs.contains(find(nd,p))) {
			    				 hs.add(find(nd,p));
			    				 ans+=abs(p[find(nd,p)]);
			    			 }
			    		 }
			    	 }
//			    	 System.out.print(ans+" ");
			    	 max=max(max,ans);
			     }
//			     System.out.println();
			     for(int j=0;j<m;j++) {
			    	 int ans=0;
			    	 HashSet<Integer> hs=new HashSet<>();
			    	 for(int i=0;i<n;i++) {
			    		 if(a[i][j]!='#') {
			    			 ans++;
			    			 for(int k=0;k<4;k++) {
			    				  int nx=i+x[k];
			    				  int ny=j+y[k];
			    				  if(nx>=0 && ny>=0 && nx<n && ny<m && a[nx][ny]=='#') {
			    					  int nd1=nx*m+ny;
			    					  if(!hs.contains(find(nd1,p))) {
			    						  hs.add(find(nd1,p));
			    						  ans+=abs(p[find(nd1,p)]);
			    					  }
			    				  }
			    			  }
			    		 }
			    		 else {
			    			 int nd=i*m+j;
			    			 if(!hs.contains(find(nd,p))) {
			    				 hs.add(find(nd,p));
			    				 ans+=abs(p[find(nd,p)]);
			    			 }
			    		 }
			    	 }
//			    	 System.out.print(ans+" ");
			    	 max=max(max,ans);
			     }
//			     System.out.println();
			     log.write(max+"\n");
				  
			}
			log.flush();
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
static long eval(long r,long ct[],int k) {
	long v=pow(ct[k]+1,r);
	return v;
}				
				public static void build(int a[][],int b[]) {
					for(int i=0;i<b.length;i++) {
						a[i][0]=b[i];
					}
					int jmp=2;
					   while(jmp<=b.length) {
						for(int j=0;j<b.length;j++) {
							int ind=(int)(Math.log(jmp/2)/Math.log(2));
							int ind2=(int)(Math.log(jmp)/Math.log(2));
							if(j+jmp-1<b.length) {
							a[j][ind2]=Math.max(a[j][ind],a[j+(jmp/2)][ind]);
							}
						}
						jmp*=2;
					}   
				//	   for(int i=0;i<a.length;i++) {
				//		   for(int j=0;j<33;j++) {
				//			   System.out.print(a[i][j]+" ");
				//		   }
				//		   System.out.println();
				//	   }
				}
		public static int serst(int a[][],int i,int j) {
			int len=j-i+1;
			int hp=1;
			int tlen=len>>=1;
//			System.out.println(tlen);		
			while(tlen!=0) {
				tlen>>=1;
				hp<<=1;
			}
//			System.out.println(hp);
			int ind=(int)(Math.log(hp)/Math.log(2));
			int i2=j+1-hp;
			return Math.max(a[i][ind], a[i2][ind]);
		}				
static int eval(ArrayList<ArrayList<Integer>> ar,int src,int pr,int ch[]) {
	int max=1;
	for(int el:ar.get(src)) {
		if(el==pr)continue;
		max=max(max,1+eval(ar,el,src,ch));
	}
	return ch[src]=max;
}
static void eval(ArrayList<ArrayList<Integer>> ar,int src,int pr,int b,ArrayList<Integer> tp) {
	if(src==b) {
		tp.add(b);
		int mid=(tp.size()+1)/2-1;
		ans+=tp.size()/2;
		bb=tp.get(mid);
		return;
	}
	tp.add(src);
	for(int el:ar.get(src)) {
		if(el==pr)continue;
	     eval(ar,el,src,b,tp);
	}
	tp.remove(tp.size()-1);
}
	static void update(long f[],long upd,int ind) {
		int vl=ind;
		while(vl<f.length) {
			f[vl]+=upd;
			int tp=~vl;
			tp++;
			tp&=vl;
			vl+=tp;
		}
	}	
	static long ser(long f[],int ind) {
		int vl=ind;
		long sm=0;
		while(vl!=0) {
			sm+=f[vl];
			int tp=~vl;
			tp++;
			tp&=vl;
			vl-=tp;
		}
		return sm;
	}				
static long eval(int a[][],int i,int h,long x) {
//	if(i>=a.length)return;
//	if(dp[i][h]<=sc)return;
//	dp[i][h]=min(dp[i][h],sc);
//	eval(a,i+1,sc+a[i][0],h+a[i][1]);
//	eval(a,i+1,sc,h);
	if(i<0)return h==0?0:inf;
	if(h<0)return inf;
	if(dp[i][h]!=-1)return dp[i][h];
	long v1=a[i][0]+eval(a,i-1,h-a[i][1],x);
	long v2=eval(a,i-1,h,x);
	long ans=inf;
	if(x*i-v1>=0)ans=min(ans,v1);
	if(x*i-v2>=0)ans=min(ans,v2);
	return dp[i][h]=ans;
}				
				
		static int query(int seg[],int l,int h,int nl,int nh,int pos) {
			if(nl<=l && h<=nh)return seg[pos];
			if(nh<l || nl>h)return 0;
			int mid=l+(h-l)/2;
			return max(query(seg,l,mid,nl,nh,2*pos+1),query(seg,mid+1,h,nl,nh,2*pos+2));
		}
	static void update(int seg[],int l,int h,int ind,int val,int pos) {
		if(l==h) {
			seg[pos]=max(seg[pos],val);
			return;
		}
		int mid=l+(h-l)/2;
		if(l<=ind && ind<=mid)update(seg,l,mid,ind,val,2*pos+1);
		else update(seg,mid+1,h,ind,val,2*pos+2);
		seg[pos]=max(seg[2*pos+1], seg[2*pos+2]);
	}					

static class pair2{
	long a,b;
	public pair2(long a,long b) {
		this.a=a;
		this.b=b;
	}
}				
static boolean eval(PriorityQueue<pair> pq,PriorityQueue<pair> pq2,long x,long y) {
	boolean vis[]=new boolean[pq.size()];
	int n=pq.size();
	while(n--!=0) {
		while(pq.size()>0 && vis[pq.peek().c])pq.poll();
		while(pq2.size()>0 && vis[pq2.peek().c])pq2.poll();
		// max horizontal cut
		int x1=pq.peek().a;
		int y1=pq.peek().b;
		// max vertical cut
		int x2=pq2.peek().a;
		int y2=pq2.peek().b;
		if(x1==x) {
		  y-=y1;
		  vis[pq.peek().c]=true;
		  pq.poll();
		  
		}
		else if(y2==y) {
		  x-=x2;
		  vis[pq2.peek().c]=true;
		  pq2.poll();
		}
		else return false;
		
	}
	return x>=0&&y>=0;
}			
static boolean eval2(PriorityQueue<pair> pq,PriorityQueue<pair> pq2,long x,long y) {
	boolean vis[]=new boolean[pq.size()];
	int n=pq.size();
	while(n--!=0) {
		while(pq.size()>0 && vis[pq.peek().c])pq.poll();
		while(pq2.size()>0 && vis[pq2.peek().c])pq2.poll();
		// max horizontal cut
		int x1=pq.peek().a;
		int y1=pq.peek().b;
		// max vertical cut
		int x2=pq2.peek().a;
		int y2=pq2.peek().b;
		 if(y2==y) {
			  x-=x2;
			  vis[pq2.peek().c]=true;
			  pq2.poll();
		}
		else if(x1==x) {
		  y-=y1;
		  vis[pq.peek().c]=true;
		  pq.poll();
		}
		else return false;
	}
	return x>=0&&y>=0;
}		
static int[] zed(char a[]) {
	int z[]=new int[a.length];
	int l=0;
	int r=0;
	for(int i=0;i<a.length;i++) {
		if(i>r) {
			l=r=i;
			while(r<a.length && a[r]==a[r-l])r++;
			z[i]=r-l;
			r--;
		}
		else {
			int k1=i-l;
			if(z[k1]<r-i+1) {
				z[i]=z[k1];
			}
			else {
				l=i;
				while(r<a.length && a[r]==a[r-l])r++;
				z[i]=r-l;
				r--;
			}
		}	
	}
	return z;
}				
static long eval(int a[],int b[],int n,int q,int k) {
	boolean vis[]=new boolean[n+1];
	int tk=k;
	ArrayList<Integer> ar=new ArrayList<>();
	vis=new boolean[n+1];
	while(!vis[q]) {
		ar.add(b[q]);
		vis[q]=true;
		q=a[q];
	}
	long val=0;
	long a2=0;
	for(int i=0;i<min(ar.size(),tk);i++) {
		a2=max(a2,val+(tk-i)*(long)ar.get(i));
		val+=ar.get(i);
	}
	if(ar.size()<=tk)a2=max(a2,val);
	return a2;
	
}				
static final int MAXN = 2000001;
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
static ArrayList<pair> get(int x)
{
    HashMap<Integer,Integer> hm = new HashMap<>();
    while (x != 1)
    {
        hm.put(spf[x], hm.getOrDefault(spf[x], 0)+1);
        x = x / spf[x];
    }
    ArrayList<pair> ar=new ArrayList<>();
    for(int el:hm.keySet())ar.add(new pair(el,hm.get(el)));
    return ar;
}			
	// small function bazi due to alas
	static int max(int a,int b) {return a>=b?a:b;}			
	static int min(int a,int b) {return a>=b?b:a;}	
	static long max(long a,long b) {return a>=b?a:b;}	
	static long min(long a,long b) {return a>=b?b:a;}
	static int abs(int a) {
		return a>=0?a:-a;
	}
	
	static long abs(long a) {
	return a>=0?a:-a;
	}
	
	
	
	static long div(long a,long b) {
		long an=pow(b,m-2)%m;
		return mul(an,a);
	}	
	public static long mul(long a, long b) {
		return (a*b)%m;
	}
	public static long add(long a, long b) {
		return (a+b)%m;
	}
	public static long sub(long a,long b) {
	  return (a%m-b%m+m)%m;	
	}	
	static int gcd(int a,int b) {
		if(b==0)return a;
		else return gcd(b,a%b);
	}
	static long gcd(long a,long b) {
		if(b==0)return a;
		return gcd(b,a%b);
	}
	static long ncr(int n, int r){
	    if(r>n-r)r=n-r;
	    long ans=1;
	    for(int i=0;i<r;i++){
	          ans=mul(ans,(n-i));
	          ans=div(ans,i+1);
	    }
	    return ans;
	}
	public static class trip{
		int a;
		int b;
		int c;
		public trip(int a,int b,int c) {
			this.a=a;
			this.b=b;
			this.c=c;
		}
	}	
	static void mergesort(int[] a,int start,int end) {
			if(start>=end)return ;
			int mid=start+(end-start)/2;
			mergesort(a,start,mid);
			mergesort(a,mid+1,end);
			merge(a,start,mid,end);
			
		}
	static void merge(int[] a, int start,int mid,int end) {
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
	public static class pair{
		int a,b,c,d,e;
		public pair(int a,int b,int c) {
			this.a=a;
			this.b=b;
			this.c=c;
		}
		public pair(int a,int b) {
			this.a=a;
			this.b=b;
		}
		public pair(int a,int b,int c,int d) {
			this.a=a;
			this.b=b;
			this.c=c;
			this.d=d;
		}
		public pair(int a,int b,int c,int d,int e) {
			this.a=a;
			this.b=b;
			this.c=c;
			this.d=d;
			this.e=e;
		}
	//			@Override
	//			public String toString() {
	//				return "{"+this.a+" "+this.b+"}";
	//				}
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
			static int poww(int a, int pw) {
				int temp;
				if(pw==0)return 1;
				temp=poww(a,pw/2);
				if(pw%2==0)return temp*temp;
				return a*temp*temp;
				
			}
		
	}