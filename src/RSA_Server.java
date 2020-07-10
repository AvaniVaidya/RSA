import java.util.*;
import java.io.*;
import java.net.*;
import java.math.*;

public class RSA_Server {
	
	public static BigInteger value_of_d(BigInteger phin, BigInteger e)
	{
		BigInteger i = new BigInteger("1");
		//double rem = ((phin*i)+1)%e;
		
		BigInteger rem = ((phin.multiply(i)).add(BigInteger.ONE)).mod(e);
		
		while(true)
		{
			if(rem.equals(BigInteger.ZERO))
				break;
			else
			{
				i = i.add(BigInteger.ONE);
				rem = ((phin.multiply(i)).add(BigInteger.ONE)).mod(e);
			}
			//i++;
			//rem = ((phin*i)+1)%e;
		}
		
		return ((phin.multiply(i)).add(BigInteger.ONE)).divide(e);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try
		{
			System.out.println("Waiting for Connection with client to be established");
			ServerSocket ss = new ServerSocket(8080);
			Socket s = ss.accept();
			System.out.println("Connection with client established");
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String phin = dis.readUTF();
			System.out.println(phin);
			String e = dis.readUTF();
			System.out.println(e);
			String n = dis.readUTF();
			System.out.println(n);
			String C = dis.readUTF();
			System.out.println(C);
						
			BigInteger d = value_of_d(new BigInteger(phin), new BigInteger(e));
			
			BigInteger M = new BigInteger(C).modPow(d, new BigInteger(n));
			
			System.out.println("Value of d is:"+d+" Value of Plaintext is:"+M);
			
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
 
 Waiting for Connection with client to be established
Connection with client established
160
7
187
11
Value of d is:23 Value of Plaintext is:88


Waiting for Connection with client to be established
Connection with client established
3120
7
3233
2868
Value of d is:1783 Value of Plaintext is:123

*/
