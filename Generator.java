package secure;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Generator
{
	public static HashSet<BigInteger> compose(BigInteger n)
	{
		n=n.abs();
		HashSet<BigInteger> compose=new HashSet<BigInteger>();
		BigInteger compare=new BigInteger(sqrt(n.toString()));
		BigInteger p=BigInteger.TWO;
		BigInteger tem;
		while(p.compareTo(compare)!=1)
		{
			tem=n.mod(p);
			if(tem.compareTo(BigInteger.ZERO)==0)
			{
				compose.add(p);
				n=n.divide(p);
				compare=new BigInteger(sqrt(n.toString()));
				continue;
			}
			p=p.add(BigInteger.ONE);
		}
		compose.add(n);
		return compose;
	}
	
	public static BigInteger multiple(BigInteger prime)
	{
		BigInteger n=prime.subtract(BigInteger.ONE);
		HashSet<BigInteger> com=compose(n);
		BigInteger tem;
		for(tem=BigInteger.TWO;tem.compareTo(n)!=1;tem=tem.add(BigInteger.ONE))
		{
			int flag=1;
			for(BigInteger i:com)
			{
				BigInteger b=tem.modPow(n.divide(i), prime);
				if(b.compareTo(BigInteger.ONE)==0)
				{
					flag=0;
					break;
				}
			}
			if(flag==1)
			{
				return tem;
			}
		}
		return new BigInteger("-1");
	}
	
	public static HashSet<BigInteger> all_multiple(BigInteger prime,BigInteger one_generator)
	{
		HashSet<BigInteger> all=new HashSet<BigInteger>();
		BigInteger n=prime.subtract(BigInteger.ONE);
		for(BigInteger k=BigInteger.TWO;k.compareTo(n)!=1;k=k.add(BigInteger.ONE))
		{
			BigInteger tem=k.gcd(n);
			if(tem.compareTo(BigInteger.ONE)==0)
			{
				all.add(one_generator.modPow(k, prime));
			}
		}
		all.add(one_generator);
		return all;
	}
	
	public static BigInteger rand_multiple(BigInteger prime,BigInteger one_generator)
	{
		Random rand=new Random();
		BigInteger n=prime.subtract(BigInteger.ONE);
		Integer tem;
		BigInteger k,compare;
		while(true)
		{
			tem=rand.nextInt();
			tem=Math.abs(tem)+1;
			k=new BigInteger(tem.toString());
			compare=k.gcd(n);
			if(compare.compareTo(BigInteger.ONE)==0)
			{
				return one_generator.modPow(k, prime);
			}
		}
	}
	
	public static String sqrt(String num)
    {
        BigInteger b=new BigInteger(num);
        if(b.compareTo(BigInteger.ZERO)<0)
            return "-1";

        String sqrt="0"; //�������
        String pre="0"; //������������Ҫ����ı�����
        BigInteger trynum; //���̣����Ź�������Ҫ����ļ���
        BigInteger flag;  //���̣��õ�����Ҫ�������֮��һ����
        BigInteger _20=new BigInteger("20"); //����20
        BigInteger dividend; ///������������Ҫ����ı�����
        BigInteger A;  //(10*A+B)^2=M
        BigInteger B;
        BigInteger BB;
        //���ֵĳ���
        int len=num.length(); 

        if(len%2==1)  //�����������Ļ�����λ����1��0�ճ�ż��λ
        {
            num="0"+num;
            len++;
        }
        //�õ���ƽ����һ����len/2λ  
        for(int i=0;i<len/2;++i)
        {
            dividend=new BigInteger(pre+num.substring(2*i,2*i+2));
            A=new BigInteger(sqrt);
            for(int j=0;j<=9;++j)
            {
                B=new BigInteger(j+"");
                BB=new BigInteger((j+1)+"");

                trynum=_20.multiply(A).multiply(B).add(B.pow(2));
                flag=_20.multiply(A).multiply(BB).add(BB.pow(2));
                
                //����Ҫ���jʹ������������еı�����֮��Ϊ��С����
                if(trynum.subtract(dividend).compareTo(BigInteger.ZERO)<=0
                        &&flag.subtract(dividend).compareTo(BigInteger.ZERO)>0)
                {
                	//������ϵõ���j
                    sqrt+=j;
                    //���¿�����������Ҫ����ı�����
                    pre=dividend.subtract(trynum).toString();
                    break;
                }
            }
        }
        return sqrt.substring(1);
    }
	
	public static void main(String[] args)
	{
		System.out.println("������һ��������");
		Scanner in=new Scanner(System.in);
		BigInteger prime=new BigInteger(in.next());
		in.close();
		System.out.println("��С����Ԫ��"+multiple(prime));
		System.out.println("���е�����Ԫ��"+all_multiple(new BigInteger("13"),new BigInteger("2")));
		System.out.println("���ѡȡһ������Ԫ��"+rand_multiple(new BigInteger("13"),new BigInteger("2")));
	}
}