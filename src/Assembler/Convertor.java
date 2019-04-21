package Assembler;

public class Convertor {
    public static String conver(String code) {
        code = code.toLowerCase().trim();
        code = code.replace("(", " ");
        code = code.replace(")", "");
        code = code.replace(",", " ");
        code = code.replace("  ", " ");
        code = code.replace("  ", " ");
        String[] arg = code.split(" ");
        for (int i=0;i<arg.length;i++){
            arg[i]= arg[i].trim();
        }
        //
        Function f;
    try {
        f = Function.getEnum(arg[0]);
    }catch (Exception e){
        return RegestersControler.None;
    }

        String[] targer = new String[arg.length - 1];
        for (int i = 1; i < arg.length; i++) {
            targer[i - 1] = arg[i];
        }
        return Decoder(f, targer);
    }

    private static String Decoder(Function f, String[] arg) {
        String opcode;
        String funct;
        TypeOfFunction type;
        switch (f) {
            case add:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(32, 6);
                break;
            case addu:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(33, 6);
                break;
            case and:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(36, 6);
                break;
            case jr:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(8, 6);
                break;

            case nor:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(39, 6);
                break;
            case or:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(37, 6);
                break;
            case slt:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(42, 6);
                break;
            case sltu:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(43, 6);
                break;
            case sll:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(0, 6);
                break;
            case srl:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(2, 6);
                break;
            case sub:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(34, 6);
                break;
            case subu:
                type = TypeOfFunction.R;
                opcode = RegestersControler.intToBinarysign(0, 6);
                funct = RegestersControler.intToBinarysign(35, 6);
                break;
            case slti:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(10, 6);
                funct = "";
                break;
            case sltiu:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(11, 6);
                funct = "";
                break;
            case ori:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(13, 6);
                funct = "";
                break;
            case sb:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(40, 6);
                funct = "";
                break;
            case sc:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(56, 6);
                funct = "";
                break;
            case sh:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(41, 6);
                funct = "";
                break;
            case sw:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(43, 6);
                funct = "";
                break;
            case addi:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(8, 6);
                funct = "";
                break;
            case addiu:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(9, 6);
                funct = "";
                break;
            case andi:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(12, 6);
                funct = "";
                break;
            case beq:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(4, 6);
                funct = "";
                break;
            case bne:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(5, 6);
                funct = "";
                break;
            case lbu:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(36, 6);
                funct = "";
                break;
            case ll:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(48, 6);
                funct = "";
                break;
            case lui:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(15, 6);
                funct = "";
                break;
            case lw:
                type = TypeOfFunction.I;
                opcode = RegestersControler.intToBinarysign(35, 6);
                funct = "";
                break;
            case j:
                type = TypeOfFunction.J;
                opcode = RegestersControler.intToBinarysign(2, 6);
                funct = "";
                break;
            case jal:
                type = TypeOfFunction.J;
                opcode = RegestersControler.intToBinarysign(3, 6);
                funct = "";
                break;
            default:
                return RegestersControler.None;

        }
        return opcode + RegestersControler.RegDecode(type, f, arg) + funct;
    }
//    private static TypeOfFunction Findtype(Function f) {
//        if (f == Function.add || f == Function.addu || f == Function.and || f == Function.jr || f == Function.nor ||
//                f == Function.nor || f == Function.slt || f == Function.sltu || f == Function.sll || f == Function.srl || f == Function.sub ||
//                f == Function.subu || f == Function.div || f == Function.divu || f == Function.mfhi || f == Function.mflo || f == Function.mfc0 ||
//                f == Function.mult || f == Function.multu || f == Function.sra) {
//            return TypeOfFunction.R;
//        } else if (f == Function.j || f == Function.jal) {
//            return TypeOfFunction.J;
//        } else {
//            return TypeOfFunction.I;
//        }
//    }
}
