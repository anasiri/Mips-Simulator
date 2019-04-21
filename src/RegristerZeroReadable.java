public class RegristerZeroReadable extends Exception {
    public RegristerZeroReadable(){
        super("Trying to write in reg 0");
    }
}
