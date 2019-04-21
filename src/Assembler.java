import java.util.Scanner;

public class Assembler {

    public static void main(String [] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input =  scanner.nextLine();

        String [] inputs = input.split("\n");
        String output = "";
        for(int i =0 ; i<inputs.length;i++){
            String st  =inputs [i];
            String[] strings = st.split(" ");
            output.concat(opcodeToBinary(strings));
            for (int j = 1;j<strings.length;j++){

            }
        }

    }

    private static String opcodeToBinary(String[] input) throws Exception {
        String returnVal = "";
        switch (input[0]){
            //J-Type
//            case "J":returnVal = returnVal.concat( "0000010"+signExtend(input[1]));
//            if(input.length>2)throw new Exception("J instruction with 3 parts");
//            break;
            //R-Type
            case "add":;
            case "sub":;
            case "or":;
            case "and":;
            case "slt":returnVal = returnVal.concat( "000000");
                break;
            //I-Type
            case"Sw":returnVal = returnVal.concat( "10001011");
                break;
                case"Lw":returnVal = returnVal.concat( "10000011");
                    break;
                case"Addi":returnVal = returnVal.concat( "00001000");
                    break;
                case"Slti":returnVal = returnVal.concat( "00001010");
                    break;
                case"Andi":returnVal = returnVal.concat( "00001100");
                    break;
                case"Ori":returnVal = returnVal.concat(  "00001101");
                    break;
                case"Beq":returnVal = returnVal.concat( "00000100");
                    break;
            case"Bne":returnVal = returnVal.concat( "00000101");
                break;
            default:throw new Exception("not our command");
        }
        return null;
    }

    private static String signExtend(String s,int size) throws Exception {
        if(!s.matches("[01]+"))throw new Exception("not a binary num");
        return null;
    }
}
