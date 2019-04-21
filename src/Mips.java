import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mips {

    private int pCounter;
    private char [][] programMemory;
    private char [][] dataMemory = new char[1024][32];
    private char [][] regFile = new char[32][32];
    private ALU alu;

    public Mips(){
        pCounter =0;
        alu = new ALU();
        File file = new File("./src/Test File/RegFile.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i =0;
        while (sc.hasNextLine()){
            regFile[i++] = sc.nextLine().toCharArray();
        }
        //data memory
        file = new File("./src/Test File/DataMemory.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

         i =0;
        while (sc.hasNextLine()){
            dataMemory[i++] = sc.nextLine().toCharArray();
        }

        file = new File("./src/Test File/ProgramMemory.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        i =0;
        while (sc.hasNextLine()){
            i++;
            sc.nextLine();
        }
        programMemory = new char[i][];


        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        i =0;
        while (sc.hasNextLine()) {
            programMemory[i++] = sc.nextLine().toCharArray();
        }
    }


    public void doNextInstruction() throws Exception {
        char [] instruction = programMemory[pCounter/4];
        Opcodes opcodes = Opcodes.notInstrucion;
        opcodes = opcode(instruction);
        pCounter +=4;
        int regOneIndex = -1;
        int regTwoIndex = -1;
        //may be invalid
        int regThreeIndex = -1;
        int jumpAddr = -1;
        int immediate = -1;
        try {
            regOneIndex = Util.binToDec(
                    String.valueOf(instruction).substring(6,11).toCharArray(),false);
            regTwoIndex = Util.binToDec(
                    String.valueOf(instruction).substring(11,16).toCharArray(),false);
            regThreeIndex = Util.binToDec(
                    String.valueOf(instruction).substring(16,21).toCharArray(),false);
            jumpAddr = Util.binToDec(
                    String.valueOf(instruction).substring(6).toCharArray(),false);
            immediate = Util.binToDec(
                    String.valueOf(instruction).substring(16).toCharArray(),true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(opcodes.toString());
        switch (opcodes){
            case add:{
                try {
                    add(regThreeIndex,regOneIndex,regTwoIndex);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case sub:{
                try {
                    sub(regThreeIndex,regOneIndex,regTwoIndex);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            case or:{
                try {
                    or(regThreeIndex,regOneIndex,regTwoIndex);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            case and:{
                try {
                    and(regThreeIndex,regOneIndex,regTwoIndex);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            case slt:{
                try {
                    slt(regThreeIndex,regOneIndex,regTwoIndex);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            case J:{
                jump(jumpAddr);

                break;
            }
            case Sw:{
                try {
                    Sw(regOneIndex,regTwoIndex,immediate);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            case Lw:{
                try {
                    Lw(regOneIndex,regTwoIndex,immediate);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            case Addi:{
                try {
                    Addi(regTwoIndex,regOneIndex,immediate);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            case Slti:{
                try {
                    Slti(regTwoIndex,regOneIndex,immediate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case Andi:{
                try {
                    Andi(regTwoIndex,regOneIndex,immediate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case Ori:{
                try {
                    Ori(regTwoIndex,regOneIndex,immediate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case Beq:{
                try {
                    Beq(regTwoIndex,regOneIndex,immediate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case Bne:{
                try {
                    Bnq(regTwoIndex,regOneIndex,immediate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case notInstrucion:{
                throw new Exception("not an instruction!");
            }
        }
    }

    private void add(int dstIndex,int src1Index,int src2Index) throws Exception {
        if(dstIndex==0)throw new RegristerZeroReadable();
        alu.setInput1(regFile[src1Index]);
        alu.setInput2(regFile[src2Index]);
        alu.setAluCode(AluCode.ADD);
        alu.update();
        int sum = Util.binToDec(alu.getOutput(),true);
        regFile[dstIndex] = Util.decToBin(sum,true,true);
    }
    private void sub(int dstIndex,int src1Index,int src2Index) throws Exception {
        if(dstIndex==0)throw new RegristerZeroReadable();
        alu.setInput1(regFile[src1Index]);
        alu.setInput2(regFile[src2Index]);
        alu.setAluCode(AluCode.SUB);
        alu.update();
        int sum = Util.binToDec(alu.getOutput(),true);
        regFile[dstIndex] = Util.decToBin(sum,true,true);
    }
    private void or(int dstIndex,int src1Index,int src2Index) throws Exception {
        if(dstIndex==0)throw new RegristerZeroReadable();
        alu.setInput1(regFile[src1Index]);
        alu.setInput2(regFile[src2Index]);
        alu.setAluCode(AluCode.OR);
        alu.update();
        int sum = Util.binToDec(alu.getOutput(),true);
        regFile[dstIndex] = Util.decToBin(sum,true,true);
    }
    private void and(int dstIndex,int src1Index,int src2Index) throws Exception {
        if(dstIndex==0)throw new RegristerZeroReadable();
        alu.setInput1(regFile[src1Index]);
        alu.setInput2(regFile[src2Index]);
        alu.setAluCode(AluCode.AND);
        alu.update();
        int sum = Util.binToDec(alu.getOutput(),true);
        regFile[dstIndex] = Util.decToBin(sum,true,true);
    }
    private void slt(int dstIndex,int src1Index,int src2Index) throws Exception {
        if(dstIndex==0)throw new RegristerZeroReadable();
        alu.setInput1(regFile[src1Index]);
        alu.setInput2(regFile[src2Index]);
        alu.setAluCode(AluCode.SUB);
        alu.update();
        if(alu.getOutput()[0]=='1'){
            regFile[dstIndex] = Util.decToBin(1,true,true);
        }else{
            regFile[dstIndex] = Util.decToBin(0,true,true);
        }
    }
    private void jump(int jumpAddr) {
        char [] pc = Util.decToBin(pCounter,false,true);
        char [] new26bit = Util.decToBin(jumpAddr<<2,false,true);
        for (int i =4;i<31;i++){
            pc[i] = new26bit[i];
        }
        try {
            pCounter = Util.binToDec(pc,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void Sw(int base, int regIndex, int immediate) throws Exception {
        int addr;
        alu.setInput1(regFile[base]);
        alu.setInput2(Util.decToBin(immediate/4,true,true));
        alu.setAluCode(AluCode.ADD);
        alu.update();
        addr = Util.binToDec(alu.getOutput(),true);

        int val = Util.binToDec(regFile[regIndex],true);

        dataMemory[addr] = Util.decToBin(val,true,true);

    }
    private void Lw(int base, int regIndex, int immediate) throws Exception {
        if(regIndex==0)throw new RegristerZeroReadable();
        int addr;
        alu.setInput1(regFile[base]);
        alu.setInput2(Util.decToBin(immediate/4,true,true));
        alu.setAluCode(AluCode.ADD);
        alu.update();
        addr = Util.binToDec(alu.getOutput(),true);

        int val = Util.binToDec(dataMemory[addr],true);

       regFile[regIndex] = Util.decToBin(val,true,true);


    }
    private void Addi(int dstIndex, int srcIndex, int immediate) throws Exception {
        if(dstIndex==0)throw new RegristerZeroReadable();
        alu.setInput1(regFile[srcIndex]);
        alu.setInput2(Util.decToBin(immediate,true,true));
        alu.setAluCode(AluCode.ADD);
        alu.update();
        int val = Util.binToDec(alu.getOutput(),true);
        regFile[dstIndex] = Util.decToBin(val,true,true);

    }
    private void Slti(int dstIndex, int srcIndex, int immediate) throws Exception {
        if(dstIndex==0)throw new RegristerZeroReadable();
        alu.setInput1(regFile[srcIndex]);
        alu.setInput2(Util.decToBin(immediate,true,true));
        alu.setAluCode(AluCode.SUB);
        alu.update();
        if(alu.getOutput()[0]=='1'){
            regFile[dstIndex] = Util.decToBin(1,true,true);
        }else{
            regFile[dstIndex] = Util.decToBin(0,true,true);
        }
    }
    private void Andi(int dstIndex, int srcIndex, int immediate) throws Exception {
        if(dstIndex==0)throw new RegristerZeroReadable();
        alu.setInput1(regFile[srcIndex]);
        alu.setInput2(Util.decToBin(immediate,true,true));
        alu.setAluCode(AluCode.AND);
        alu.update();
        int val = Util.binToDec(alu.getOutput(),true);
        regFile[dstIndex] = Util.decToBin(val,true,true);
    }
    private void Ori(int dstIndex, int srcIndex, int immediate) throws Exception {
        if(dstIndex==0)throw new RegristerZeroReadable();
        alu.setInput1(regFile[srcIndex]);
        alu.setInput2(Util.decToBin(immediate,true,true));
        alu.setAluCode(AluCode.OR);
        alu.update();
        int val = Util.binToDec(alu.getOutput(),true);
        regFile[dstIndex] = Util.decToBin(val,true,true);
    }
    private void Beq(int src1Index, int src2Index, int immediate) throws Exception {
        alu.setInput1(regFile[src1Index]);
        alu.setInput2(regFile[src2Index]);
        alu.setAluCode(AluCode.SUB);
        alu.update();
        if(alu.isBcond()){
            pCounter += immediate<<2;
        }
    }
    private void Bnq(int src1Index, int src2Index, int immediate) throws Exception {
        alu.setInput1(regFile[src1Index]);
        alu.setInput2(regFile[src2Index]);
        alu.setAluCode(AluCode.SUB);
        alu.update();
        if(!alu.isBcond()){
            pCounter += immediate<<2;
        }
    }

    private Opcodes opcode(char[] instruction){
        int opcode = 0;
        int function=0;
        try {
            opcode = Util.binToDec( String.valueOf(instruction).substring(0,6).toCharArray(),false);
            function = Util.binToDec(String.valueOf(instruction).substring(26).toCharArray(),false);
        } catch (Exception e) {
            e.printStackTrace();
        }


        switch (opcode){
            case 0:{
                switch (function){
                    case (32):return Opcodes.add;
                    case(34):return Opcodes.sub;
                    case(36):return Opcodes.and;
                    case(37):return Opcodes.or;
                    case(42):return Opcodes.slt;
                }
            }
            case 2: return  Opcodes.J;
            case 4:return Opcodes.Beq;
            case 5:return Opcodes.Bne;
            case 8:return Opcodes.Addi;
            case 10:return Opcodes.Slti;
            case 12:return Opcodes.Andi;
            case 13:return Opcodes.Ori;
            case 35:return Opcodes.Lw;
            case 43:return Opcodes.Sw;
        }
        return Opcodes.notInstrucion;

    }

    @Override
    public String toString() {
        String s = "";
        s = s.concat("Program Counter value is: " + pCounter+"\n");
        s = s.concat("Register file values are:\n");
        for (int i=0;i<regFile.length;i++){
            s = s.concat("Reg "+i +": "+String.valueOf(regFile[i])+"\n");
        }
        s = s.concat("\n\n");
        for(int i =0;i<20;i++){
            s = s.concat("Data Mem Line " + (i+1) +": "+String.valueOf(dataMemory[i])+"\n");
        }


        return s;
    }

    public boolean hasNextInstruction() {
        if(pCounter<0) return false;
        if (pCounter/4<programMemory.length)return true;
        return false;
    }
}
