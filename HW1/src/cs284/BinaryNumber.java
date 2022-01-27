package cs284;

/*
 * Name: Tyler Seliber
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

public class BinaryNumber {
    private int[] binary;

    public BinaryNumber(int length) {
        if (length < 0) {
            illegalArgs("Length must be positive");
        }
        this.binary = new int[length];
        for (int i = 0; i < length; i += 1) {
            binary[i] = 0;
        }
    }

    public BinaryNumber(String str) {
        String acceptable_chars = "01";
        this.binary = new int[str.length()];
        for (int i = 0; i < str.length(); i += 1) {
            char c = str.charAt(str.length() - 1 - i);
            if (!acceptable_chars.contains(c + "")) {
                illegalArgs("Invalid character in string");
            }
            binary[i] = Character.getNumericValue(c);
        }
    }

    private static void illegalArgs(String error) {
        throw new IllegalArgumentException(error);
    }

    private static void indexOutOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds");
    }

    public int length() {
        return binary.length;
    }

    public int getDigit(int index) {
        if (index < 0 || index >= binary.length) {
            indexOutOfBounds(index);
        }
        return binary[index];
    }

    public int toInt() {
        int result = 0;
        for (int i = 0; i < length(); i += 1) {
            result += binary[i] * Math.pow(2, i);
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

        if (direction == -1) {
            // Left shift
            extend(amount);
        } else {
            // Right shift
            contract(-1 * amount);
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = length() - 1; i >= 0; i -= 1) {
            result.append(binary[i]);
        }
        return result.toString();
    }

    public static BinaryNumber bwor(BinaryNumber bn1, BinaryNumber bn2) {
        if (bn1.length() != bn2.length()) {
            BinaryNumber.illegalArgs("Binary numbers must be of equal length");
        }
        BinaryNumber result = new BinaryNumber(bn1.length());
        for (int i = 0; i < bn1.length(); i += 1) {
            if (bn1.binary[i] == 1 || bn2.binary[i] == 1) {
                result.binary[i] = 1;
            } else {
                result.binary[i] = 0;
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
            if (bn1.binary[i] == 1 && bn2.binary[i] == 1) {
                result.binary[i] = 1;
            } else {
                result.binary[i] = 0;
            }
        }
        return result;
    }

    // Method to bitshift left
    private void extend(int amount) {
        if (amount < 0) {
            illegalArgs("Extend amount must be positive");
        }
        // Create new array with length = length + amount
        int[] newBinary = new int[length() + amount];
        // Fill added indices with 0
        for (int i = 0; i < amount; i += 1) {
            newBinary[i] = 0;
        }
        // Copy contents of old array into new array
        for (int i = 0; i < length(); i += 1) {
            newBinary[amount + i] = binary[i];
        }
        // Set binary to new array
        binary = newBinary;
    }

    // Method to bitshift right
    private void contract(int amount) {
        if (amount > 0) {
            illegalArgs("Contract amount must be negative");
        }
        // Create new array with length = length + amount
        int[] newBinary = new int[length() + amount];
        amount *= -1;
        for (int i = 0; i < amount; i += 1) {
            newBinary[i] = binary[i + amount];
        }
        // Set binary to new array
        binary = newBinary;
    }

}
