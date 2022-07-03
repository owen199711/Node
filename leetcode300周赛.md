* [解密消息](https://leetcode.cn/contest/weekly-contest-300/problems/decode-the-message/)
```java
 public String decodeMessage(String key, String message) {
        Map<Character,Character> hash=new HashMap();
        char []buf="abcdefghijklmnopqrstuvwxyz".toCharArray();
        int cnt=0;
        for(int i=0;i<key.length();i++){
            if(hash.containsKey(key.charAt(i))||key.charAt(i)==' ') continue;
            else{
                hash.put(key.charAt(i),buf[cnt]);
                cnt++;
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<message.length();i++){
            if(message.charAt(i)==' ') sb.append(' ');
            else sb.append(hash.get(message.charAt(i)));
        }
        return sb.toString();

    }
```
* [旋转矩阵4](https://leetcode.cn/contest/weekly-contest-300/problems/spiral-matrix-iv/)
```java
    private int [][]dir={{0,1},{1,0},{0,-1},{-1,0}};
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int i=0,j=0;
        int [][]f=new int[m][n];
        for(int k=0;k<m;k++) Arrays.fill(f[k],-1);
        f[i][j]=head.val;
        head=head.next;
        int stepi=m-1;
        int stepj=n-1;
        int d=0;
        while(head!=null){
            int step=0;
            if(d==0){
                step=stepj;
                if(stepj!=n-1) stepj-=1;
            }
            else if(d==2){
                step=stepj;
                stepj-=1;
            }
            else if(d%2==1){
                step=stepi;
                stepi-=1;
            }
            for(int k=0;k<step;k++){
                int x=dir[d][0]+i,y=dir[d][1]+j;
                if(x>=0&&x<m&&y>=0&&y<n&&head!=null){
                    f[x][y]=head.val;
                }
                if(head==null) break;
                i=x;j=y;
            }
            d=(d+1)%4; 
        }
        return f;
    }
```
* [知道秘密的人数](https://leetcode.cn/problems/number-of-people-aware-of-a-secret/)
```java
int m=(int)1e9+7;
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long []f=new long[forget+2];
        f[1]=1;f[0]=0;
        long sum=0;
        for(int i=2;i<=n;i++){
            sum=0;
            for(int j=delay;j<forget;j++) sum=sum%m+f[j];//统计所有需要产生 新产品的个数
            for(int j=forget;j>=1;j--) f[j]=f[j-1]; //更新每个产品的存活周期，直接向右移动
            f[1]+=sum; //产生新的产品，在第一天
        }
        long ans=0;
        for(int i=1;i<=forget;i++){
            ans=ans%m+f[i]%m; //统计所有产品。
        }
        return (int)ans%m;

    }
```
