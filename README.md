# 实现rsa和ElGamal加密算法以及数字签名

采用Java 9.0.1版本进行开发，且不向下兼容。

由于采用了Java自带的BigInteher类，所以能实现任意位数的加密和解密

每一个类都包含main方法，其中的为测试代码。



> 代码编写了一个名叫 `Euclid`的类，并实现的两个静态方法：
>
> gcd函数采用辗转相除法，实现求两个整数的最大公因子；
>
> extend_gcd函数采用扩展欧几里得算法，同时出于实际情况的考虑，略作改动，即已知两个整数a,b，求得gcd(a,b),x,y使得x|a|+y|b|=gcd(a,b)。

> 代码编写了一个名叫 ` Rsa` 的类，并实现的两个静态方法：
>
> create_key()函数用于生成公钥和私钥相关的参数，即n,e,b；
>
> modPow()函数用于实现x的y次方模z的结果。可实现加密，解密，数字签名，以及数字签名的认证。

> 代码编写了一个名叫 ` Generator` 的类， 由于实际情况的需要，该类都是围绕一个素数的既约剩余系所构成的乘法群展开讨论。
>
> multiple()函数实现寻找出最小的生成元。
>
> all_multiple()函数实现的是找出所有的生成元。
>
> rand_multiple()函数实现随机抽取一个生成元。

> 代码编写了一个名叫 ` ElGamal` 的类，并实现的五个静态方法，同时用到了寻找生成元的方法。
>
> create_key()函数用于生成公钥和私钥相关的参数，即{p,g,b}为公钥，{a}为私钥。
>
> encryption()函数用于加密。
>
> decipher()函数用于解密。
>
> sign()函数用于数字签名。
>
> check()函数用于数字签名验证。