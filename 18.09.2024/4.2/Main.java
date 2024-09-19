import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        File liczby = new File("przyklad.txt");

        try{
            Scanner scanner = new Scanner(liczby);

            int max_czynnikow = 0;
            int max_unikatowych = 0;

            while(scanner.hasNextLine()){

                int liczba = Integer.parseInt(scanner.nextLine());

                int i = 2;
                int czynniki = 0;
                int unikatowe = 0;

                while(i < liczba){

                    if(liczba % i == 0){

                        boolean pierwsza = true;

                        for(int j = 2; j < i; j++){
                            if(i % j == 0){
                                pierwsza = false;
                            }
                        }
                        if(pierwsza){
                            czynniki += 1;
                            liczba /= i;
                        }
                        else{
                            liczba /= 1;
                        }
                        i += 1;
                    }
                }

                if(czynniki > max_czynnikow){
                    max_czynnikow = czynniki;
                }
            }
            System.out.println("najwięcej czynników: " + max_czynnikow);
        }
        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
