package com.mandela.java12months.cypher;

import com.sun.jdi.PathSearchingVirtualMachine;

import javax.swing.tree.TreePath;
import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {
    public boolean isValidKey(int key, char[] alphabet){
        int newKey=key%alphabet.length;
        if(newKey>0){
            return true;
        }
        return false;
    }

    public boolean isFileExists(String filePath){
        if(Files.exists(Path.of(filePath))){
            return true;
        }
        return false;
    }
}