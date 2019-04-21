import java.util.Random;

public class Main {
    public static void main (String [] args) throws Exception {
        Mips mips = new Mips();
        System.out.println(mips);

        int[] z = {1,4,5,6,8,0,-2,5,2,31};
        int r =0;
        while (mips.hasNextInstruction()){

            mips.doNextInstruction();
            System.out.println("****************************************************************** \n\n" + mips);
        }

        String s="";
        for (int i =0;i<10;i++){
            int x =z[i];
            x %=10;
            s = s.concat(String.valueOf(Util.decToBin(x,true,true))+"\n");
        }
//        System.out.println(Util.randomDataGenerator(11));
    }
}
