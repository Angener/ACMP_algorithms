package by.gp_solution.eremenko.part1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("OUTPUT.TXT", false));


        for (int i = 0; i < 4; i++) {
            dataOutput.writeInt((int) (Math.random() * 100));
        }
        dataOutput.close();



        DataInputStream dataInput = new DataInputStream(new BufferedInputStream(new FileInputStream("OUTPUT.TXT")));
        while (dataInput.available() > 0) {
            int first = dataInput.readInt();


            System.out.println(first);
        }

        dataInput.close();

    }
}
