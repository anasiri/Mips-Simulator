public class ALU {
    private char [] input1 =new char [32];
    private char [] input2 = new char [32];
    private AluCode aluCode ;

    private char [] output = new char [32];
    private boolean bcond = false;

    public void update() {
        int inp1=0;
        int inp2=0;
        try {
            inp1 = Util.binToDec(input1,true);
            inp2 = Util.binToDec(input2,true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        switch (aluCode) {
            case ADD:{
                inp1 +=inp2;
                output = Util.decToBin(inp1,true,true);
                bcond = false;
                break;
            }
            case SUB:{
                inp1 -=inp2;
                output = Util.decToBin(inp1,true,true);
                if(inp1==0)bcond=true;
                else bcond = false;
                break;
            }
            case OR:
            {
                inp1 = inp1|inp2;
                output = Util.decToBin(inp1,true,true);
                bcond = false;
                break;
            }
            case AND:{

                inp1 = inp1&inp2;
                output = Util.decToBin(inp1,true,true);
                bcond = false;
                break;
            }
        }

    }
    public boolean isBcond() {
        return bcond;
    }

    public char[] getOutput() {
        return output;
    }

    public void setInput1(char[] input1) {
        this.input1 = input1;
    }

    public void setInput2(char[] input2) {
        this.input2 = input2;
    }

    public void setAluCode(AluCode aluCode) {
        this.aluCode = aluCode;
    }
}
