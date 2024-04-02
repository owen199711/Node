package org.example;

public class zuichanghuiwenxulie {
    //得到的最长回文序列长度。

    // 最长回文串序列长度
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int [][]f=new int[n][n];
        for(int i=n-1;i>=0;i--){
            for(int j=i;j<n;j++){
                if(i==j) f[i][j]=1;
                else if(i<j){
                    if(s.charAt(i)==s.charAt(j)){
                        f[i][j]=f[i+1][j-1]+2;
                    }else{
                        f[i][j]=Math.max(f[i][j-1],f[i+1][j]);
                    }
                }
            }
        }
        return f[0][n-1];
    }
}
