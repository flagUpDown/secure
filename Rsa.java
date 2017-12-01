package secure;

import java.math.BigInteger;
import java.util.Random;

public class Rsa
{
	public static BigInteger[] create_key()
	{
		Random rnd=new Random();
		BigInteger p=BigInteger.probablePrime(1024, rnd);
		BigInteger q=p.nextProbablePrime();
		BigInteger n=p.multiply(q);
		BigInteger tem=p.subtract(BigInteger.ONE);
		BigInteger z=q.subtract(BigInteger.ONE);
		z=z.multiply(tem);
		Random rand=new Random();
		Integer x;
		BigInteger e;
		while(true)
		{
			x=rand.nextInt();
			x=Math.abs(x)+1;
			e=new BigInteger(x.toString());
			tem=z.gcd(e);
			if(tem.compareTo(BigInteger.ONE)==0)
			{
				break;
			}
		}
		BigInteger b= e.modInverse(z);
		BigInteger[] result={n,e,b};
		return result;
	}
	
	public static BigInteger modPow(BigInteger m,BigInteger n,BigInteger e)
	{
		return m.modPow(e, n);
	}
	

public static void main(String[] args)
{	
	BigInteger[] test=Rsa.create_key();
	System.out.println("所选取的两个大素数的乘积："+test[0]);
	System.out.println("随机选取的e："+test[1]);
	System.out.println("e的逆元："+test[2]);
	System.out.println("明文：233333333333");
	BigInteger m=new BigInteger("233333333333");
	BigInteger c=Rsa.modPow(m, test[0], test[1]);
	System.out.println("密文："+c);
	m=Rsa.modPow(c, test[0], test[2]);
	System.out.println("由密文解出明文："+m);
	c=Rsa.modPow(m, test[0], test[2]);
	System.out.println("数字签名（233333333333）得："+c);
	m=Rsa.modPow(c, test[0], test[1]);
	System.out.println("数字签名认证："+m);
}

}
