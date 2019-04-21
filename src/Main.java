import java.util.Random;

public class Main {
    public static void main (String [] args) throws Exception {
        char [] input = {'1','1', '0','0','1'};
        Mips mips = new Mips();
        System.out.println(Integer.toString(Util.binToDec(input,true)));
        System.out.println(String.copyValueOf(Util.decToBin(-33,true,true)));
        System.out.println(mips);
        while (mips.hasNextInstruction()){
            mips.doNextInstruction();
            System.out.println("****************************************************************** \n\n" + mips);
        }

        String st = "";
        for(int j = 0 ;j<1;j++){
            String s ="";
            for(int i =0;i<32;i++){
                Random random = new Random();
                int x = random.nextInt();
                x = Math.abs(x%2);
                if(x==0){
                    s = s.concat("0");
                }
                else if(x==1){
                    s = s.concat("1");
                }
                else{
                    s = s.concat("|");
                }
            }
            st = st.concat(s+"\n");
        }
    }
}
