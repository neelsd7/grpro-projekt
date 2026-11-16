package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.*;

import setup.InputReader; 
import setup.Input; 

import elements.*; 

public class TestInputReader {

    @Test
    public void testFile_t1_1a() {
        File file = new File("src/input/t1-1a.txt"); 
        InputReader ir; 
        try {
            ir = new InputReader(file); 
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage()); 
            ir = null; 
        }
        
        int size = ir.getSize(); 
        List<Input> input = ir.getInput(); 

        //Input actual_input = new Input(Grass.class, 3);
        
        assertEquals(size,5); 
        
        //assertEquals(input.get(0),actual_input);
    }
}
