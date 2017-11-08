import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Main_25_18 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter filename: ");
        File file = new File(input.nextLine());
        Long fileLengthLong = file.length();
        byte[] fileContent = new byte[fileLengthLong.intValue()];
        try {
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.read(fileContent);
            inputStream.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        String text = new String(fileContent);

        int[] counts = HuffmanCode.getCharacterFrequency(text);

        System.out.printf("%-15s%-15s%-15s%-15s\n", "ASCII Code", "Character", "Frequency", "Code");

        HuffmanCode.Tree tree = HuffmanCode.getHuffmanTree(counts);
        String[] codes = HuffmanCode.getCode(tree.root);

        for (int i = 0; i < codes.length; i++) {
            if (counts[i] != 0) {
                System.out.printf("%-15s%-15s%-15s%-15s\n", i, (char) i + "", counts[i], codes[i]);
            }
        }
    }
}
