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
	
	public static BigInteger encryption(BigInteger m,BigInteger n,BigInteger e)
	{
		return m.modPow(e, n);
	}
	
	public static BigInteger decipher(BigInteger c,BigInteger n,BigInteger d)
	{
		return c.modPow(d, n);
	}

	public static void main(String[] args)
	{
		BigInteger[] test=Rsa.create_key();
		System.out.println(test[0].toString()+"\n"+test[1]+"\n"+test[2]);
		BigInteger c=Rsa.encryption(new BigInteger("345678"), test[0], test[1]);
		System.out.println(c);
		BigInteger m=Rsa.encryption(c, test[0], test[2]);
		System.out.println(m);
	}

}
