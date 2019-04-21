package Assembler;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadTools {
    public static void main(String[] args) {
        //test
//        try {
//            ArrayList<String> strings = ReadFile(Paths.get("E:\\projects\\mips_assembly_to_machine_code\\src\\sample.txt"));
//            for (String xx : strings) {
//                System.out.println(xx);
//            }
//            strings = Normalaze(strings);
//            System.out.println("***************************");
//            for (String xx : strings) {
//                System.out.println(xx);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            ArrayList<String> strings = ReadFile(Paths.get("./src/Assembler/sample-input.txt"));
            strings = Normalaze(strings);
            ArrayList<String> ans=new ArrayList<>(strings.size());
            for (String xx:strings){
                ans.add(Convertor.conver(xx));
            }
            WriteFile(Paths.get("./src/Assembler/sample-output.txt"),ans);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static ArrayList<String> Normalaze(ArrayList<String> code) {
        int codeChandam = 0;
        Map<String, Integer> mapcode = new HashMap<>();
        for (int j = 0; j < code.size(); j++) {
            String hh = code.get(j);
            if (hh.contains(":")) {
                mapcode.put(hh, codeChandam);
            } else {
                codeChandam++;
            }
        }
        for (int i = code.size() - 1; i >= 0; i--) {
            String hh = code.get(i);
            if (hh.contains(":")) {
                code.remove(i);
            }
        }//remove all label
        for (String hh : mapcode.keySet()) {
            for (int i = code.size() - 1; i >= 0; i--) {
                if (code.get(i).contains(hh.substring(0, hh.length() - 1)) && !hh.equalsIgnoreCase(code.get(i))) {
                    String myString = code.get(i);
                    int index = myString.indexOf(hh.substring(0, hh.length() - 1));
                    if (myString.startsWith("j")) {
                        myString = myString.substring(0, index - 1) + " " + mapcode.get(hh);
                    } else {
                        int howMuchGo=mapcode.get(hh)-i;
                        howMuchGo--;
                        myString = myString.substring(0, index - 1) + " "+ (howMuchGo);
                    }
                    code.remove(i);
                    code.add(i, myString);
                }
            }
        }
        return code;
    }

    public static ArrayList<String> ReadFile(Path path) throws FileNotFoundException {
        ArrayList<String> listline = new ArrayList<>(100);
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                if (line.trim().equalsIgnoreCase("")) {
                    line = br.readLine();
                    continue;
                }
                listline.add(line.trim().toLowerCase());
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listline;
    }

    public static void WriteFile(Path path, ArrayList<String> code) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(path.toFile(), "UTF-8");
        for (String x : code) {
            writer.println(x);
        }
        writer.close();
    }
}
