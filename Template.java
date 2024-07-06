import java.util.*;

public class Template {
	  static class Trie{
			Trie a[];
			int ind;
			public Trie() {
				this.a=new Trie[2];
				this.ind=-1;
			}
		}
	// primes in logn
	static final int MAXN = 1000001;
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
    static HashSet<Integer> get(int x)
    {
        HashSet<Integer> ret = new HashSet<>();
        while (x != 1)
        {
            ret.add(spf[x]);
            x = x / spf[x];
        }
        return ret;
    }
	//find and union
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
	//Manacher's Algo
		static int[] manacher_odd(String ss) {
		    int n = ss.length();
		    ss = "$" + ss + "^";
		    char s[]=ss.toCharArray();
		    int p[]=new int[n+2];
		    int l = 1, r = 1;
		    for(int i = 1; i <= n; i++) {
		        p[i] = Math.max(0, Math.min(r - i, p[l + (r - i)]));
		        while(s[i - p[i]] == s[i + p[i]]) {
		            p[i]++;
		        }
		        if(i + p[i] > r) {
		            l = i - p[i]; r = i + p[i];
		        }
		    }
		    return p;
		}
	//KMP ALGO
	static int[] lps(int a[],String s) {
		int i=1;
		int j=0;
		a[0]=0;
		while(i<s.length()) {
			if(s.charAt(i)==s.charAt(j)) {
				a[i]=j+1;
				i++;
				j++;
			}
			else {
				if(j!=0) {
	            j=a[j-1];
				}
				else {
				a[i]=0;
				i++;
				}
			}
		}
		return a;
	}
	//Z algo
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
//Segment Tree
		static void build(int seg[],int a[],int l,int h,int pos) {
			if(l==h) {
				seg[pos]+=a[l];
				return;
			}
			int mid=l+(h-l)/2;
			build(seg,a,l,mid,2*pos+1);
			build(seg,a,mid+1,h,2*pos+2);
			seg[pos]=Math.min(seg[2*pos+1], seg[2*pos+2]);
		}	
		static int query(int seg[],int l,int h,int nl,int nh,int pos) {
			if(nl<=l && h<=nh)return seg[pos];
			if(nh<l || nl>h)return 0;
			int mid=l+(h-l)/2;
			return query(seg,l,mid,nl,nh,2*pos+1)|query(seg,mid+1,h,nl,nh,2*pos+2);
		}
	static void update(int seg[],int l,int h,int ind,int val,int pos) {
		if(l==h) {
			seg[pos]+=val;
			return;
		}
		int mid=l+(h-l)/2;
		if(l<=ind && ind<=mid)update(seg,l,mid,ind,val,2*pos+1);
		else update(seg,mid+1,h,ind,val,2*pos+2);
		seg[pos]=Math.min(seg[2*pos+1], seg[2*pos+2]);
	}		
//Max sparse Table
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
//	System.out.println(tlen);		
	while(tlen!=0) {
		tlen>>=1;
		hp<<=1;
	}
//	System.out.println(hp);
	int ind=(int)(Math.log(hp)/Math.log(2));
	int i2=j+1-hp;
	return Math.max(a[i][ind], a[i2][ind]);
}
// Min Sparse Table
public static void build2(int a[][],int b[]) {
	for(int i=0;i<b.length;i++) {
		a[i][0]=b[i];
	}
	int jmp=2;
	   while(jmp<=b.length) {
		for(int j=0;j<b.length;j++) {
			int ind=(int)(Math.log(jmp/2)/Math.log(2));
			int ind2=(int)(Math.log(jmp)/Math.log(2));
			if(j+jmp-1<b.length) {
			a[j][ind2]=Math.min(a[j][ind],a[j+(jmp/2)][ind]);
			}
		}
		jmp*=2;
	}   
}
public static int serst2(int a[][],int i,int j) {
	int len=j-i+1;
	int hp=1;
	int tlen=len>>=1;
//	System.out.println(tlen);		
	while(tlen!=0) {
		tlen>>=1;
		hp<<=1;
	}
//	System.out.println(hp);
	int ind=(int)(Math.log(hp)/Math.log(2));
	int i2=j+1-hp;
	return Math.min(a[i][ind], a[i2][ind]);
}
//Fenwick Tree
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
// fenwick tree range update and range query
	static void rangeUpdate(int l,int r,long upd,long f1[],long f2[]){
	    update(f1,upd,l);
	    update(f1,-upd,r+1);
	    update(f2,upd*(l-1),l);
	    update(f2,-upd*r,r+1);
	}    
	static long rangeQuery(int l,int r,long f1[],long f2[]){
	    return (ser(f1,r)*r-ser(f2,r))-(ser(f1,l-1)*(l-1)-ser(f2,l-1));
	}	
//radix sort
public static void radixSort(int a[]) {
	int n=a.length;
	int res[]=new int[n];
	
	int p=1;

	for(int i=0;i<=8;i++) {
		int cnt[]=new int[10];
	     for(int j=0;j<n;j++) {
	    	 a[j]=res[j];
	    	 cnt[(a[j]/p)%10]++;
	    	
	     }
	     for(int j=1;j<=9;j++) {
	    	 cnt[j]+=cnt[j-1];
	     }
	     
	     for(int j=n-1;j>=0;j--) {
	        res[cnt[(a[j]/p)%10]-1]=a[j];
	        cnt[(a[j]/p)%10]--;
	     }
	     
	     p*=10;
		
	}
}
//Divisors of A number
static ArrayList<Integer> divisors(int n){
	
	ArrayList<Integer> ar=new ArrayList<>();
     for (int i=2; i<=Math.sqrt(n); i++){
	        if (n%i == 0){
	            if (n/i == i) {
	            	ar.add(i);
	            }
	            else {
	               ar.add(i);
	               ar.add(n/i);
	            }
	         }
       }
	return ar;
}
//Prime Numbers
static ArrayList<Integer> prime(int n){
	ArrayList<Integer> ar=new ArrayList<>();
	int cnt=0;
	boolean pr=false;
	while(n%2==0) {
		ar.add(2);
		n/=2;	
	}
	for(int i=3;i*i<=n;i+=2) {
		pr=false;
		while(n%i==0) {
			n/=i;
			ar.add(i);
			pr=true;
		}
	
	}
	if(n>2) ar.add(n);
	return ar;
	
}
static void insert(Trie proot,int num){
    Trie root=proot;
    for(int i=20;i>=0;i--){
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
    for(int i=20;i>=0;i--){
        int bit=(num&(1<<i))>0?1:0;
        if(root.a[1-bit]!=null){
            // System.out.print("1");
            ans+=(1<<i);
            root=root.a[1-bit];
        }
        else {
            // System.out.print("0");
            root=root.a[bit];
        }
    }
    // System.out.println();
    return ans;
}   
//mod factorial
static long factmod(long n,long mod) {
	if(n==0)return 0;
	long ans=1;
	long temp=1;
	while(temp<=n) {
		ans=((ans%mod)*((temp)%mod))%mod;
		temp++;
	}
	return ans%mod;
}		

}
