package secure;

import java.math.BigInteger;

public class Euclid
{
	public static BigInteger gcd(String x,String y)
	{
		BigInteger a=new BigInteger(x);
		BigInteger b=new BigInteger(y);
		a=a.abs();
		b=b.abs();
		BigInteger r;
		while(b.compareTo(BigInteger.ZERO)==1)
		{
			r=a.mod(b);
			a=b;
			b=r;
		}
		return a;
	}
	
	public static BigInteger[] extend_gcd(String u,String w)
	{
		BigInteger a=new BigInteger(u);
		BigInteger b=new BigInteger(w);
		BigInteger x,y;
		BigInteger[] array=new BigInteger[3];
		if(b.compareTo(BigInteger.ZERO)==0)
		{
			x=BigInteger.ONE;
			y=BigInteger.ZERO;
			array[0]=a;
			array[1]=x;
			array[2]=y;
			return array;
		}
		BigInteger x2=BigInteger.ONE;
		BigInteger x1=BigInteger.ZERO;
		BigInteger y2=BigInteger.ZERO;
		BigInteger y1=BigInteger.ONE;
		BigInteger q,r;
		while(b.compareTo(BigInteger.ZERO)==1)
		{
			q=a.divide(b);
			r=a.mod(b);
			x=q.multiply(x1);
			x=x2.subtract(x);
			y=q.multiply(y1);
			y=y2.subtract(y);
			
			a=b;
			b=r;
			x2=x1;
			x1=x;
			//x2=BigInteger.ONE;
			y2=y1;
			y1=y;
		}
		x=x2;
		y=y2;
		array[0]=a;
		array[1]=x;
		array[2]=y;
		return array;
		
	}

	public static void main(String[] args)
	{
		//BigInteger result=Euclid.gcd("112","8");
		BigInteger result2[]=Euclid.extend_gcd("7","5");
		System.out.println(result2[0].toString()+"  "+result2[1]+"  "+result2[2]);
	}

}
