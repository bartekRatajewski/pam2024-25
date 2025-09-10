import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        File liczby = new File("liczby.txt");

        try{
            Scanner scanner = new Scanner(liczby);

            int i = 0;

            while(scanner.hasNextLine()){

                String line = scanner.nextLine();

                String first = line.substring(0,1);

                String last = line.substring(line.length() - 1);

                if(first.equals(last)){
                    if(i == 0){
                        System.out.println(line);
                    }
                    i+=1;
                }
            }
            System.out.println("Tych liczb jest: " + i);
        }
        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
