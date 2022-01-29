package cs284;

/*
 * Name: Tyler Seliber
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

import java.util.Arrays;

public class BinaryNumber {
    private int[] data;

    public BinaryNumber(int length) {
        if (length < 0) {
            illegalArgs("Length must be positive");
        }
        // Create a new array with 'length' zeros
        this.data = new int[length];
    }

    public BinaryNumber(String str) {
        String acceptable_chars = "01";
        this.data = new int[str.length()];
        for (int i = 0; i < str.length(); i += 1) {
            char c = str.charAt(str.length() - 1 - i);
            if (!acceptable_chars.contains(c + "")) {
                illegalArgs("Invalid character in string");
            }
            data[i] = Character.getNumericValue(c);
        }
    }

    public int length() {
        return data.length;
    }

    public int getDigit(int index) {
        if (index < 0 || index >= data.length) {
            indexOutOfBounds(index);
        }
        return data[index];
    }

    public static BinaryNumber bwor(BinaryNumber bn1, BinaryNumber bn2) {
        if (bn1.length() != bn2.length()) {
            BinaryNumber.illegalArgs("Binary numbers must be of equal length");
        }
        BinaryNumber result = new BinaryNumber(bn1.length());
        for (int i = 0; i < bn1.length(); i += 1) {
            if (bn1.data[i] == 1 || bn2.data[i] == 1) {
                result.data[i] = 1;
            } else {
                result.data[i] = 0;
            }
        }
        return result;
    }

    public static BinaryNumber bwand(BinaryNumber bn1, BinaryNumber bn2) {
        if (bn1.length() != bn2.length()) {
            BinaryNumber.illegalArgs("Binary numbers must be of equal length");
        }
        BinaryNumber result = new BinaryNumber(bn1.length());
        for (int i = 0; i < bn1.length(); i += 1) {
            if (bn1.data[i] == 1 && bn2.data[i] == 1) {
                result.data[i] = 1;
            } else {
                result.data[i] = 0;
            }
        }
        return result;
    }

    public void bitShift(int direction, int amount) {
        if (Math.abs(direction) != 1) {
            illegalArgs("Direction must be 1 or -1");
        }
        if (amount < 0) {
            illegalArgs("Amount must be positive");
        }
        if (direction == 1 && amount > length()) {
            illegalArgs("Amount is too large");
        }

        if (direction == -1) { // Left shift
            // Create a new array with contents of this.data followed by additional zeros (become leading zeros in binary number)
            this.data = Arrays.copyOf(this.data, length() + amount);
        } else { // Right shift
            // Create a new array with contents of this.data with first amount elements (least significant bits) removed
            this.data = Arrays.copyOfRange(this.data, amount, length());
        }
    }

    // Binary number addition
    public void add(BinaryNumber aBinaryNumber) {
        // If the lengths are different, pad the shorter one with zeros
        if (this.length() != aBinaryNumber.length()) {
            BinaryNumber smaller;
            BinaryNumber larger;
            if (this.length() > aBinaryNumber.length()) {
                smaller = aBinaryNumber;
                larger = this;
            } else {
                smaller = this;
                larger = aBinaryNumber;
            }

            int[] leadingZeros = new int[larger.length() - smaller.length()];
            smaller.data = Arrays.copyOf(smaller.data, larger.length());


        }
        int carry = 0;
        // Add all but the most significant digits
        for (int i = 0; i < length(); i += 1) {
            int sum = this.data[i] + aBinaryNumber.data[i] + carry;
            if (sum >= 2) {
                carry = 1;
                this.data[i] = sum - 2; // Subtract 2 to get a binary bit
            } else {
                carry = 0;
                this.data[i] = sum;
            }
        }

        // Expand array if there is still a carry
        if (carry == 1) {
            this.data = Arrays.copyOf(this.data, length() + 1);
            this.data[length() - 1] = 1;
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = length() - 1; i >= 0; i -= 1) {
            result.append(data[i]);
        }
        return result.toString();
    }

    public int toInt() {
        int result = 0;
        for (int i = 0; i < length(); i += 1) {
            result += data[i] * Math.pow(2, i);
        }
        return result;
    }

    // Throw IllegalArgumentException with the given error message
    private static void illegalArgs(String error) {
        throw new IllegalArgumentException(error);
    }

    // Throw IndexOutOfBoundsException for the given invalid index
    private static void indexOutOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds");
    }
}
