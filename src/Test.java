import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Test {
    public static void main(String[] args) throws Exception {
        String str = "001101010101011";
        File file = new File("C:\\Users\\yusei\\Desktop\\output.txt");
        OutputStream out = new FileOutputStream(file);
        BitOutput bOut = new BitOutput(out);
        bOut.writeBits(str);
        bOut.close();
        out.close();
    }
}
