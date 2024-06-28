package crc;
import java.lang.*;
import java.util.*;

public class CRC 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sender Side: ");
        System.out.println("Enter the length of the data: ");
        int data_len = sc.nextInt();

        System.out.println("Enter the data: ");
        String data = sc.next();

        System.out.println("Enter the length of the Generator(Divisor): ");
        int div_len = sc.nextInt();

        System.out.println("Enter the Generator(Divisor): ");
        String divisor = sc.next();

        System.out.println("Number of zeros to be appended: " + (div_len - 1));
        String appended_data = data;

        for (int i = 0; i < (div_len - 1); i++) 
        {
            appended_data += "0";
        }
        System.out.println("Message after appending zeros: " + appended_data);

        String new_data = appended_data.substring(0, div_len);
        int len = appended_data.length();
        int newData_len = new_data.length();
        int num = len-div_len+1;
        String redun_data="";
        for(int i=1; i<div_len; i++)
            redun_data+="0";
    
        String CRC_bits=CRC_Calculation(appended_data,num,new_data,newData_len,div_len,divisor,redun_data);
        String trans_data="";
        if(CRC_bits.charAt(0)=='0')
        {
            System.out.println("CRC bits: " + CRC_bits.substring(1));
            trans_data=data+CRC_bits.substring(1);
            System.out.println("Transmitted Data: "+trans_data);
        }
        else
        {
            System.out.println("CRC bits: " + CRC_bits);
            trans_data=data+CRC_bits;
            System.out.println("Transmitted Data: "+trans_data);
        }
          
        System.out.println("\nReceiver Side: ");
        String receive_data=trans_data;
        System.out.println("Received Data: "+receive_data);
        
        String new_data1 = receive_data.substring(0, div_len);
        int newData_len1 = new_data1.length();
        String remainder=CRC_Calculation(receive_data,num,new_data1,newData_len1,div_len,divisor,redun_data);
        int rem=Integer.parseInt(remainder);
        System.out.println("Remainder: "+remainder);
        if(rem==0)
            System.out.println("Since Remainder is 0, hence the message has received correctly!!!");
        else
            System.out.println("Since Remainder is not 0, hence the message has been corrupted!!!");
    }
    
    public static String CRC_Calculation(String receive_data,int num,String new_data,int newData_len,int div_len,String divisor,String redun_data)
    {
        int num1=div_len-1;
        while(num>0)
        {
            if (new_data.charAt(0) == '1' && newData_len==div_len) 
            {
                new_data = exOr(new_data, divisor);
                newData_len = new_data.length();
            }
            else if (new_data.charAt(0) == '1' && newData_len<div_len) 
            {
                new_data = exOr(new_data.substring(0)+receive_data.charAt(num1), divisor);
                newData_len = new_data.length();
            }
            else if(new_data.charAt(0)=='0' && new_data.charAt(1)=='1' )
            {
                new_data = exOr(new_data.substring(1)+receive_data.charAt(num1), divisor);
                newData_len = new_data.length();
            }
            else if(new_data.charAt(0)=='0' && new_data.charAt(1)=='0'  )
            {
                new_data = exOr(new_data.substring(2)+receive_data.charAt(num1), redun_data);
                newData_len = new_data.length();
            }
            num--;
            num1++;
        }
        return new_data;
    }
    public static String exOr(String a, String b) 
    {
        String result ="";
        for (int i = 0; i < a.length(); i++) 
        {
            if(a.charAt(i)==b.charAt(i))
                result+="0";
            else
                result+="1";
        }
        return result;
    }
}
