import java.util.*;
import java.io.*;
import java.net.*;
import java.math.*;

public class RSA_Client {
	
	public static BigInteger gcd(BigInteger num1, BigInteger num2)
	{
		if(num2.equals(BigInteger.ZERO))
		{
			return num1;
		}
		
		return gcd(num2, num1.mod(num2));
	}
	
	public static BigInteger value_of_e(BigInteger phin)
	{
		BigInteger e = new BigInteger("4");
		while(true)
		{
			if(gcd(phin,e).equals(BigInteger.ONE))
			{
				break;
			}
			else
			{
				e = e.add(BigInteger.ONE);
			}
		}
		
		return e;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		try
		{
			Socket s = new Socket("localhost",8080);
			System.out.println("Connected to server");
			
			System.out.println("Enter the value of p:");
			BigInteger p = new BigInteger(sc.next());
			
			System.out.println("Enter the value of q:");
			BigInteger q = new BigInteger(sc.next());
			
			System.out.println("Enter the value of M:");
			BigInteger M = new BigInteger(sc.next());
			
			BigInteger n = p.multiply(q);
			System.out.println("n:"+n);
			
			BigInteger left = p.subtract(BigInteger.ONE);
			BigInteger right = q.subtract(BigInteger.ONE);
			
			BigInteger phin = left.multiply(right);
			System.out.println("phin:"+phin);
			
			BigInteger e = value_of_e(phin);
			
			BigInteger C = M.modPow(e, n);
			
			System.out.println("Value of e is:"+e+" Value of Ciphertext is:"+C);
			
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(phin.toString());
			dos.writeUTF(e.toString());
			dos.writeUTF(n.toString());
			dos.writeUTF(C.toString());

		}
		catch(Exception e)
		{
			e.getMessage();
		}
		

	}

}

/*
 * 
 OUTPUT:
 
 Connected to server
Enter the value of p:
17
Enter the value of q:
11
Enter the value of M:
88
n:187
phin:160
Value of e is:7 Value of Ciphertext is:11


Connected to server
Enter the value of p:
61
Enter the value of q:
53
Enter the value of M:
123
n:3233
phin:3120
Value of e is:7 Value of Ciphertext is:2868

*/
