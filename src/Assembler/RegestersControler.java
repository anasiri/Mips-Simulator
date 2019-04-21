package Assembler;

import java.util.HashMap;
import java.util.Map;

public class RegestersControler {
    private static Map<String, Integer> registers = new HashMap<>(32);
    public static String None = "00000000000000000000000000000000";

    static {
        registers.put("$zero", 0);
        registers.put("$at", 1);
        registers.put("$v0", 2);
        registers.put("$v1", 3);
        registers.put("$a0", 4);
        registers.put("$a1", 5);
        registers.put("$a2", 6);
        registers.put("$a3", 7);
        registers.put("$t0", 8);
        registers.put("$t1", 9);
        registers.put("$t2", 10);
        registers.put("$t3", 11);
        registers.put("$t4", 12);
        registers.put("$t5", 13);
        registers.put("$t6", 14);
        registers.put("$t7", 15);
        registers.put("$s0", 16);
        registers.put("$s1", 17);
        registers.put("$s2", 18);
        registers.put("$s3", 19);
        registers.put("$s4", 20);
        registers.put("$s5", 21);
        registers.put("$s6", 22);
        registers.put("$s7", 23);
        registers.put("$t8", 24);
        registers.put("$t9", 25);
        registers.put("$k0", 26);
        registers.put("$k1", 27);
        registers.put("$gp", 28);
        registers.put("$sp", 29);
        registers.put("$fp", 30);
        registers.put("$ra", 31);
    }

    public static int getReg(String reg) {
        if (!reg.startsWith("$") || !registers.containsKey(reg))
            throw new unexpect_reg();
        return registers.get(reg);
    }

    public static String intToBinaryUnsign(int source, int len) {
        StringBuilder temp = new StringBuilder(Integer.toBinaryString(source));
        while (temp.length() < len) {
            temp.insert(0, "0");
        }
        return temp.toString();
    }
    public static String intToBinarysign(int source, int len) {
        StringBuilder temp = new StringBuilder(Integer.toBinaryString(Math.abs(source)));
        char sign='0';
        if (source<0){
            sign='1';
        }
        while (temp.length() < len) {
            temp.insert(0, sign);
        }
        return temp.toString();
    }

    public static String RegDecode(TypeOfFunction type, Function function, String[] arr) {
        StringBuilder ans = new StringBuilder();
        switch (type) {
            case R:
                //special cases !
                if (function == Function.sll || function == Function.srl) {
                    try {
                        ans.append(intToBinarysign(0, 5));
                        ans.append(intToBinarysign(getReg(arr[1]), 5));
                        ans.append(intToBinarysign(getReg(arr[0]), 5));
                        ans.append(intToBinarysign(getReg(arr[2]), 5));
                        return ans.toString();
                    } catch (unexpect_reg e) {
                        return None;
                    }
                }
                if (function == Function.jr) {
                    try {
                        ans.append(intToBinarysign(getReg(arr[0]), 5));
                        ans.append(intToBinarysign(0, 5));
                        ans.append(intToBinarysign(0, 5));
                        ans.append(intToBinarysign(0, 5));
                        return ans.toString();
                    } catch (unexpect_reg e) {
                        return None;
                    }
                }
                //normal cases !
                try {
                    ans.append(intToBinarysign(getReg(arr[1]), 5));
                    ans.append(intToBinarysign(getReg(arr[2]), 5));
                    ans.append(intToBinarysign(getReg(arr[0]), 5));
                    ans.append(intToBinarysign(0, 5));
                    return ans.toString();
                } catch (unexpect_reg e) {
                    return None;
                }
            case I:
                //special cases !
                if (function == Function.lui) {
                    try {
                        ans.append(intToBinarysign(0, 5));
                        ans.append(intToBinarysign(getReg(arr[0]), 5));
                        ans.append(intToBinarysign(Integer.parseInt(arr[1]), 16));
                        return ans.toString();
                    } catch (unexpect_reg e) {
                        return None;
                    }
                }
                if (function == Function.lw || function == Function.sb || function == Function.sw || function == Function.ll || function == Function.sc) {
                    try {
                        ans.append(intToBinarysign(getReg(arr[2]), 5));
                        ans.append(intToBinarysign(getReg(arr[0]), 5));
                        ans.append(intToBinarysign(Integer.parseInt(arr[1]), 16));
                        return ans.toString();
                    } catch (unexpect_reg e) {
                        return None;
                    }
                }
                //normal cases !
                try {
                    ans.append(intToBinarysign(getReg(arr[1]), 5));
                    ans.append(intToBinarysign(getReg(arr[0]), 5));
                    ans.append(intToBinarysign(Integer.parseInt(arr[2]), 16));
                    return ans.toString();
                } catch (unexpect_reg e) {
                    return None;
                }

            case J:
                //normal cases !
                try {
                    return intToBinarysign(Integer.parseInt(arr[0]), 26);
                } catch (unexpect_reg e) {
                    return None;
                }
        }
        return None;
    }
}
