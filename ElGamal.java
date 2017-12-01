package secure;

import java.math.BigInteger;
import java.util.Random;

public class ElGamal
{
	public static BigInteger[] create_key()
	{
		Random rnd=new Random();
		BigInteger p=BigInteger.probablePrime(50, rnd);
		BigInteger g=Generator.multiple(p);
		g=Generator.rand_multiple(p, g);
		Random rand=new Random();
		Integer tem=rand.nextInt();
		tem=Math.abs(tem)+1;
		BigInteger a=new BigInteger(tem.toString());
		a=a.mod(p.subtract(BigInteger.TWO));
		BigInteger b=g.modPow(a, p);
		BigInteger[] result={p,g,b,a};
		return result;
	}
	
	public static BigInteger[] encryption(BigInteger x,BigInteger p,BigInteger g,BigInteger b)
	{
		Random rand=new Random();
		Integer tem=rand.nextInt();
		tem=Math.abs(tem)+1;
		BigInteger k=new BigInteger(tem.toString());
		k=k.mod(p.subtract(BigInteger.TWO));
		BigInteger r=g.modPow(k, p);
		BigInteger s=b.modPow(k, p);
		s=s.multiply(x);
		s=s.mod(p);
		BigInteger[] result= {r,s};
		return result;
	}
	
	public static BigInteger decipher(BigInteger r,BigInteger s,BigInteger p,BigInteger a)
	{
		BigInteger x=r.modPow(a, p);
		x=x.modInverse(p);
		x=x.multiply(s);
		x=x.mod(p);
		return x;
	}
	
	public static BigInteger[] sign(BigInteger m,BigInteger p,BigInteger g,BigInteger a)
	{
		BigInteger z=p.subtract(BigInteger.ONE);
		Random rand=new Random();
		BigInteger k;
		while(true)
		{
			Integer tem=rand.nextInt();
			tem=Math.abs(tem)+1;
			k=new BigInteger(tem.toString());
			if(k.gcd(z).compareTo(BigInteger.ONE)==0)
			{
				k=k.mod(z);
				break;
			}
		}
		BigInteger r=g.modPow(k, p);
		BigInteger s=m.subtract(r.multiply(a)).multiply(k.modInverse(z)).mod(z);
		
		BigInteger[] result= {r,s};
		
		return result;
	}
	
	public static Boolean check(BigInteger m,BigInteger r,BigInteger s,BigInteger p,BigInteger g,BigInteger b)
	{
		b=b.modPow(r, p);
		r=r.modPow(s, p);
		b=b.multiply(r).mod(p);
		g=g.modPow(m, p);
		
		return b.compareTo(g)==0;
	}

	public static void main(String[] args)
	{
		BigInteger[] test=create_key();
		System.out.println("生成的公钥：p="+test[0]+"  g="+test[1]+"  b="+test[2]);
		System.out.println("生成的私钥："+test[3]);
		System.out.println("明文：2333");
		BigInteger m=new BigInteger("2333");
		BigInteger[] test1=encryption(m,test[0],test[1],test[2]);
		System.out.println("密文：r="+test1[0]+"  s="+test1[1]);
		System.out.println("解密得到："+decipher(test1[0], test1[1], test[0], test[3]));
		
		BigInteger[] test2=sign(m,test[0],test[1],test[3]);
		System.out.println("数字签名（2333）：r="+test2[0]+"  s="+test2[1]);
		System.out.println("数字签名验证: "+check(m,test2[0],test2[1],test[0],test[1],test[2]));
	}

}
