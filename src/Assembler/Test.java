package Assembler;

public class Test {


    public static void main(String[] args) {

        String ans = Convertor.conver("add $t0 $t0 $t0");
        String CorentAns = "00000001000010000100000000100000";
        if (CorentAns.equals(ans)) {
            System.out.println("add work");
        }
        ans = Convertor.conver("addi $t0 $t0 1");
        CorentAns = "00100001000010000000000000000001";
        if (CorentAns.equals(ans)) {
            System.out.println("addi work");
        }
        ans = Convertor.conver(" j 4");
        CorentAns = "00001000000000000000000000000100";
        if (CorentAns.equals(ans)) {
            System.out.println("j work");
        }
    }
}
