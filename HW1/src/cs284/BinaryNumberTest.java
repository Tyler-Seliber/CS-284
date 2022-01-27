package cs284;

public class BinaryNumberTest {
    public static void main(String[] args) {
        BinaryNumber bn = new BinaryNumber("1011");
        p("length: " + bn.length());
        p("index 0: " + bn.getDigit(0));
        p("index 1: " + bn.getDigit(1));
        p("index 2: " + bn.getDigit(2));
        p("index 3: " + bn.getDigit(3));
//        p("index 4: " + bn.getDigit(4));
        p("toInt: " + bn.toInt());
        bn.bitShift(1, 2);
        p("after bitShift: " + bn);

        BinaryNumber bn1 = new BinaryNumber("1010");
        BinaryNumber bn2 = new BinaryNumber("1100");
        p("bwor: " + BinaryNumber.bwor(bn1, bn2));
        p("bwand: " + BinaryNumber.bwand(bn1, bn2));

    }

    public static void p(String s) {
        System.out.println(s);
    }
}
