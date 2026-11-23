package setup;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    læser fra fil:  
        - og laver en liste af Input
        - læser size (verdens størrelse)
*/

public class InputReader {
    private int size;
    private List<Input> input; 

    private List<String> fileInput;
    private int[] interval; 
    private int amount; 
    
    public InputReader(File file) throws FileNotFoundException {
        // åbner scanner til læsning af file
        Scanner s = new Scanner(file); 
        // læser size 
        size = s.nextInt(); 
        // læser hver linje som Strings
        fileInput = fileToList(s); 
        s.close(); 

        // omdanner String info til Input objekter
        input = new ArrayList<>(); 
        for(String line : fileInput) {
            if(!line.equals("") && line.contains(" ")){ // forhindrer eventuelle fejl fra Scanner.nextLine()
                input.add(stringToInput(line));  
            }
        }
    }

    // fortolker filen som en liste af Strings
    private ArrayList<String> fileToList(Scanner s){
        s.nextLine(); 
        ArrayList<String> list = new ArrayList<>();
        while(s.hasNextLine()) {
            list.add(s.nextLine()); 
        }
        return list; 
    }

    // omdanner Strings til Input objekter
    private Input stringToInput(String s) {
        Input input; 
        String[] data = s.split(" "); // data[0] = type, data[1] = antal
        
        if(data[1].contains("-")) { // lav ny interval Input
            String[] data2 = data[1].split("-"); 
            interval = new int[2]; 
            interval[0] = Integer.valueOf(data2[0]); // min
            interval[1] = Integer.valueOf(data2[1]); // max
            input = new Input(data[0], interval[0], interval[1]); 
        
        // lav ny koordinat Input. [0]: bear [1]: 1 [2]: (3,5)
        } else if(data.length > 2) { 
            String[] data2 = data[2].split(","); 
            String sx = data2[0].replace("(", "");
            String sy = data2[1].replace(")", "");
            int x = Integer.valueOf(sx); 
            int y = Integer.valueOf(sy); 
            input = new Input(data[0], x, y, true); 
        
        // lav ny alm. Input
        } else { 
            amount = Integer.valueOf(data[1]); 
            input = new Input(data[0], amount); 
        }
        return input; 
    }

    public int getSize() {
        return size; 
    }

    public List<Input> getInput() {
        return input; 
    }
}
