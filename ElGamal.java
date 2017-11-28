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

	public static void main(String[] args)
	{
		BigInteger[] test=create_key();
		System.out.println(test[0].toString()+"  "+test[1]+"  "+test[2]+"  "+test[3]);
		BigInteger[] test1=encryption(new BigInteger("233"),test[0],test[1],test[2]);
		System.out.println(test1[0].toString()+"  "+test1[1]);
		System.out.println(decipher(test1[0], test1[1], test[0], test[3]));
	}

}
