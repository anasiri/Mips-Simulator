package Assembler;

public enum Function {
    add,addi,addiu,addu,
    and,andi,beq,bne,j,
    jal,jr,lbu,lhu,
    ll,lui,lw,nor,or,
    ori,slt,slti,sltiu,
    sltu,sll,srl,sb,
    sc,sh,sw,sub,subu,
    bc1t,bc1f,div,divu,
    lwc1,ldc1,mfhi,mflo,
    mfc0,mult,multu,sra,
    swc1,sdc1,blt,bgt,ble,
    bge,li,move;

    public static Function getEnum(String value) {
        for(Function v : values())
            if(v.toString().trim().equalsIgnoreCase(value.trim())) return v;
        throw new IllegalArgumentException();
    }
}
