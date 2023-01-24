package labs;

import DocumentClasses.DocumentCollection;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Lab2 {
    public static DocumentClasses.DocumentCollection documents;
    public static DocumentClasses.DocumentCollection queries;

    public void main() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(new File("./files/docvector")))) {
            documents = (DocumentCollection)
                    is.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
