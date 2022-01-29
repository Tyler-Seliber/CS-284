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
        bn.bitShift(-1, 2);
        p("after bitShift: " + bn);

        BinaryNumber bn1 = new BinaryNumber("1010");
        BinaryNumber bn2 = new BinaryNumber("1100");
        p("bwor: " + BinaryNumber.bwor(bn1, bn2));
        p("bwand: " + BinaryNumber.bwand(bn1, bn2));

        BinaryNumber bn3 = new BinaryNumber("01101");
        BinaryNumber bn4 = new BinaryNumber("01001");
        bn3.add(bn4);
        p("01101 + 01001 = " + bn3);

        BinaryNumber bn5 = new BinaryNumber("10110");
        BinaryNumber bn6 = new BinaryNumber("11101");
        bn5.add(bn6);
        p("10110 + 11101 = " + bn5);

        BinaryNumber bn7 = new BinaryNumber("01001");
        BinaryNumber bn8 = new BinaryNumber("01101");
        bn7.add(bn8);
        p("01001 + 01101 = " + bn7);

        BinaryNumber bn9 = new BinaryNumber("11101");
        BinaryNumber bn10 = new BinaryNumber("10110");
        bn9.add(bn10);
        p("11101 + 10110 = " + bn9);

        BinaryNumber bn11 = new BinaryNumber("101");
        BinaryNumber bn12 = new BinaryNumber("1");
        bn11.add(bn12);
        p("101 + 1 = " + bn11);

        BinaryNumber bn13 = new BinaryNumber("101");
        BinaryNumber bn14 = new BinaryNumber("001");
        bn13.add(bn14);
        p("101 + 001 = " + bn13);
    }

    public static void p(String s) {
        System.out.println(s);
    }
}
