import java.lang.Math;
public class Util {

    public static int binToDec(char [] input,boolean signed) throws Exception {
        if(signed){
            if(!isBinary(input))throw new Exception("not binary");
            int sum = 0;
            if(input[0]=='1'){//negative
                char [] invertedInput = invertDigits(input);
                sum =  (int) Long.parseLong(String.valueOf(invertedInput),2);
                sum +=1;
                sum *=-1;
                return sum;
            }else {
                sum =  (int) Long.parseLong(String.valueOf(input),2);
                return sum;
            }
        }else{
            if(!isBinary(input))throw new Exception("not binary");
            int sum =  (int) Long.parseLong(String.valueOf(input),2);
            return sum;
        }
    }
    public static char [] decToBin(int input,boolean signed,boolean signExtend){
        int a;
        int x = Math.abs(input);
        String out ="";
        while(x > 0)
        {
            a = x % 2;
            out = Integer.toString(a) + out;
            x = x / 2;
        }
        char [] num = out.toCharArray();
        if(signed && input<0 ){
            boolean flag = true;
            for(int i = num.length-1;i>-1;i--){
                if (num [i]=='1'&&flag)flag = false;
                else if(num [i] == '0'&&!flag)num[i]='1';
                else if(num[i]=='1')num [i] ='0';
            }
            if(num[0]=='0'){
                String temp = "1"+String.copyValueOf(num);
                num = temp.toCharArray();
            }
        }
        if(signExtend){
            char sign = '0';
            if(input<0) sign = '1';
            if (num.length<32){
                char [] signExtendedNum = new char[32];
                int i = signExtendedNum.length-1;
                int j = num.length-1;
                while (j>-1){
                    signExtendedNum[i--] =   num[j--];
                }
                while (i>-1){
                    signExtendedNum[i--] = sign;
                }
                return signExtendedNum;
            }
        }
        return  num;
    }
    public static char [] invertDigits(char[] binaryInt) {
        String result = String.valueOf(binaryInt);
        result = result.replace("0", " "); //temp replace 0s
        result = result.replace("1", "0"); //replace 1s with 0s
        result = result.replace(" ", "1"); //put the 1s back in
        return result.toCharArray();
    }
    private static boolean isBinary(char[] input) {
        boolean notBinary = false;
        for (int i =0 ;i<input.length;i++){
            if(input[i]=='1'||input[i]=='0')continue;
            notBinary = true;
            break;
        }
        return !notBinary;
    }
}
