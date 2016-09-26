//Gerard McGlone
//CS 445

import java.io.*;

public class Assig3B
{
	public static void main(String[] args) throws IOException
	{
		File file = new File(args[0]);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		StringBuilder sb = new StringBuilder();
		String str = new String();
		MyStringBuilder msb = new MyStringBuilder();
		MyStringBuilder2 msb2 = new MyStringBuilder2();
		int r;
		int numChar = 0;
		
		//Test StringBuidler
		long sbAppendBeggining = System.nanoTime();
		while ((r = br.read()) != -1) //StringBuilder Append()
		{
			char c = (char) r;
			sb.append(c);
			numChar++;
		}
		long sbAppendEnd = System.nanoTime();
		long sbAppendTime = sbAppendEnd - sbAppendBeggining;
		
		long sbDeleteBeggining = System.nanoTime();
		while(sb.length() != 0)
		{
			sb.delete(0,1);
		}
		long sbDeleteEnd = System.nanoTime();
		long sbDeleteTime = sbDeleteEnd - sbDeleteBeggining;
		
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		long sbInsertBeggining = System.nanoTime();
		while ((r = br.read()) != -1)
		{
			char c = (char) r;
			int index = sb.length()/2;
			sb.insert(index, c);
		}
		long sbInsertEnd = System.nanoTime();
		long sbInsertTime = sbInsertEnd - sbInsertBeggining;
		System.out.println("\nStringBuilder Append Time: " + sbAppendTime);
		System.out.println("Time per StringBuilder Append Operation: " + ((double) sbAppendTime)/numChar);
		System.out.println("StringBuilder Delete Time: " + sbDeleteTime);
		System.out.println("Time per StringBuilder Delete Operation: " + ((double) sbDeleteTime)/numChar);
		System.out.println("StringBuilder Insert Time: " + sbInsertTime);
		System.out.println("Time per StringBuilder Insert Operation: " + ((double) sbInsertTime)/numChar);
		
		//Test MyStringBuilder
		//Test MyStringBuilder Append
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		long msbAppendBeggining = System.nanoTime();
		while ((r = br.read()) != -1) //StringBuilder Append()
		{
			char c = (char) r;
			msb.append(c);
		}
		long msbAppendEnd = System.nanoTime();
		long msbAppendTime = msbAppendEnd - msbAppendBeggining;
		
		//Test MyStringBuidler Delete
		long msbDeleteBeggining = System.nanoTime();
		while(msb.length() != 0)
		{
			msb.delete(0,1);
		}
		long msbDeleteEnd = System.nanoTime();
		long msbDeleteTime = msbDeleteEnd - msbDeleteBeggining;
		
		//Test MyStringBuilderInsert
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		long msbInsertBeggining = System.nanoTime();
		while ((r = br.read()) != -1)
		{
			char c = (char) r;
			int index = msb.length()/2;
			msb.insert(index, c);
		}
		long msbInsertEnd = System.nanoTime();
		long msbInsertTime = msbInsertEnd - msbInsertBeggining;
		System.out.println("\nMyStringBuilder Append Time: " + msbAppendTime);
		System.out.println("Time per MyStringBuilder Append Operation: " + ((double) msbAppendTime)/numChar);
		System.out.println("MyStringBuilder Delete Time: " + msbDeleteTime);
		System.out.println("Time per MyStringBuilder Delete Operation: " + ((double) msbDeleteTime)/numChar);
		System.out.println("MyStringBuilder Insert Time: " + msbInsertTime);
		System.out.println("Time per MyStringBuilder Insert Operation: " + ((double) msbInsertTime)/numChar);
		
		//Test MyStringBuilder2
		//Test MyStringBuilder2 Append
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		long msb2AppendBeggining = System.nanoTime();
		while ((r = br.read()) != -1) //StringBuilder2 Append()
		{
			char c = (char) r;
			msb2.append(c);
		}
		long msb2AppendEnd = System.nanoTime();
		long msb2AppendTime = msb2AppendEnd - msb2AppendBeggining;
		
		//Test MyStringBuidler2 Delete
		long msb2DeleteBeggining = System.nanoTime();
		while(msb2.length() != 0)
		{
			msb2.delete(0,1);
		}
		long msb2DeleteEnd = System.nanoTime();
		long msb2DeleteTime = msb2DeleteEnd - msb2DeleteBeggining;
		
		//Test MyStringBuilder2 Insert
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		long msb2InsertBeggining = System.nanoTime();
		while ((r = br.read()) != -1)
		{
			char c = (char) r;
			int index = msb.length()/2;
			msb2.insert(index, c);
		}
		long msb2InsertEnd = System.nanoTime();
		long msb2InsertTime = msb2InsertEnd - msb2InsertBeggining;
		System.out.println("\nMyStringBuilder2 Append Time: " + msb2AppendTime);
		System.out.println("Time per MyStringBuilder2 Append Operation: " + ((double) msb2AppendTime)/numChar);
		System.out.println("MyStringBuilder2 Delete Time: " + msb2DeleteTime);
		System.out.println("Time per MyStringBuilder2 Delete Operation: " + ((double) msb2DeleteTime)/numChar);
		System.out.println("MyStringBuilder2 Insert Time: " + msb2InsertTime);
		System.out.println("Time per MyStringBuilder2 Insert Operation: " + ((double) msb2InsertTime)/numChar);
		
		//Test String
		//Test String Append
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		long strAppendBeggining = System.nanoTime();
		while ((r = br.read()) != -1)
		{
			char c = (char) r;
			str += c;
		}
		long strAppendEnd = System.nanoTime();
		long strAppendTime = strAppendEnd - strAppendBeggining;
		
		//Test String Delete
		long strDeleteBeggining = System.nanoTime();
		while (str.length() != 0)
		{
			if (str.length() == 1)
			{
				str = str.substring(0, str.length() - 1);
				break;
			}
			str = str.substring(1, str.length());
		}
		long strDeleteEnd = System.nanoTime();
		long strDeleteTime = strDeleteEnd - strDeleteBeggining;
		
		//Test String Insert
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		long strInsertBeggining = System.nanoTime();
		while ((r = br.read()) != -1)
		{
			char c = (char) r;
			int mid = str.length()/2;
			if (str.length() == 0)
			{
				str = str + c;
			}
			else if (str.length() == 1)
			{
				str = c + str;
			}
			else
			{
				String sub1 = str.substring(0, mid);
				String sub2 = str.substring(mid, str.length());
				str = sub1 + c + sub2;
			}
		}
		long strInsertEnd = System.nanoTime();
		long strInsertTime = strInsertEnd - strInsertBeggining;;
		
		System.out.println("\nString Append Time: " + strAppendTime);
		System.out.println("Time per String Append Operation: " + ((double) strAppendTime)/numChar);
		System.out.println("String Delete Time: " + strDeleteTime);
		System.out.println("Time per String Delete Operation: " + ((double) strDeleteTime)/numChar);
		System.out.println("String Insert Time: " + strInsertTime);
		System.out.println("Time per String Insert Operation: " + ((double) strInsertTime)/numChar);
		
		
	}
}
		

