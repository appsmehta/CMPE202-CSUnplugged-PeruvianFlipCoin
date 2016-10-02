package circuitPackage;

import java.util.Scanner;

public class CircuitClass 
{
	public static void main(String[] args) 
	{
		int[] input = new int[6];
		boolean[] boolinput = new boolean[6];
		int output1 = 0, output2 = 0, output3 = 0, output4 = 0, output5 = 0, output6 = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Put your six digit binary input here -");
		
		for(int i=0;i<6;i++)
		{
			input[i] = sc.nextInt();
		}
		
		System.out.print("Input to the circuit : ");
	
		for(int i=0;i<6;i++)
		{
			if(input[i]==1)
				boolinput[i] = true;
			else
				boolinput[i] = false;
			
			System.out.print(input[i]);
		}
		
		
		System.out.println("\n");
		
		//First Round
		boolean firstnumber1 = !(boolinput[0] & boolinput[5]);
		boolean firstnumber2 = boolinput[1] & boolinput[2];
		boolean firstnumber3 = boolinput[1] | boolinput[3];
		boolean firstnumber4 = boolinput[2] ^ boolinput[4];
		boolean firstnumber5 = !(boolinput[3] | boolinput[5]);
		boolean firstnumber6 = !(boolinput[4]);
		
		System.out.println(firstnumber1+" "+firstnumber2+" "+firstnumber3+" "+firstnumber4+" "+firstnumber5+" "+firstnumber6);
		
		//Second Round
		boolean secondnumber1 = firstnumber1 ^ firstnumber2;
		boolean secondnumber2 = !(firstnumber1 | firstnumber3);
		boolean secondnumber3 = firstnumber2 | firstnumber4;
		boolean secondnumber4 = !(firstnumber3 & firstnumber5);
		boolean secondnumber5 = firstnumber4 & firstnumber6;
		boolean secondnumber6 = !(firstnumber6);
		
		System.out.println(secondnumber1+" "+secondnumber2+" "+secondnumber3+" "+secondnumber4+" "+secondnumber5+" "+secondnumber6);
		
		//Third Round
		boolean thirdnumber1 = !(secondnumber1);
		boolean thirdnumber2 = secondnumber1 ^ secondnumber2;
		boolean thirdnumber3 = !(secondnumber2 | secondnumber3);
		boolean thirdnumber4 = secondnumber3 | secondnumber4;
		boolean thirdnumber5 = !(secondnumber4 & secondnumber5);
		boolean thirdnumber6 = secondnumber5 & secondnumber6;
		
		System.out.println(thirdnumber1+" "+thirdnumber2+" "+thirdnumber3+" "+thirdnumber4+" "+thirdnumber5+" "+thirdnumber6+"\n");
		
		
		if(thirdnumber1 == true)
			output1 = 1;
		if(thirdnumber2 == true)
			output2 = 1;
		if(thirdnumber3 == true)
			output3 = 1;
		if(thirdnumber4 == true)
			output4 = 1;
		if(thirdnumber5 == true)
			output5 = 1;
		if(thirdnumber6 == true)
			output6 = 1;
		
		System.out.println("Output of the circuit : "+output1+" "+output2+" "+output3+" "+output4+" "+output5+" "+output6);
		
	}
}
