import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CalReader {
    protected String pathName = "./data";
    protected int existingTaskCount = 0;
    protected boolean isFolderThere = false;
    protected boolean isFileThere = false;

    public CalReader(String pathName) {
        this.pathName = pathName;
        try {
            existenceChecker();
        } catch (IOException | ParseException e) {
            System.err.println("Directory not there!" + e.getMessage());
        }

    }

    /**
     * A function that writes to the file at filePath location
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * A  function that deals with the possibility that the folder data and/or the file duke.txt may not exist.
     */
    public void existenceChecker() throws IOException, ParseException {
        File dirFile = new File(this.pathName);
        if (dirFile.isDirectory()) {
            File calfile = new File(pathName + "/nusmods_calendar.ics");
            String output=dataExtractor(calfile);
//            System.out.println(output);
            System.out.println("We are able to access the calender");
            String[] splitInputs = output.split("UID:");
            System.out.println(splitInputs.length);
            System.out.println(splitInputs[1]);
            int repeatSem=0;
            for(int i=1;i<2;i++)
            {
                exceptionExtractor(splitInputs[i]);

//                if(splitInputs[i].contains("RRULE:")) {
//                    countExtractor(splitInputs[i]);
//                }
//                else {
//                    System.out.println(1);
//                }
//                descriptionExtractor(splitInputs[i]);
            }
        } else {
            System.out.println("File does not exist");
        }

    }

    public boolean numToBool(int num) {
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A  function that extracts the tasks and adds it to the arraylist from the text file
     */
    public String dataExtractor(File textFile) throws IOException {
        Scanner myReader = new Scanner(textFile);
        String taskData = "";
        while (myReader.hasNextLine()) {
            taskData += ("\n" + myReader.nextLine());
        }
        return taskData;
    }

    public void countExtractor(String splitted)
    {
        String[] splittedcount = splitted.split("COUNT=");
        String[] moreSplitted = splittedcount[1].split(";");
        System.out.println(moreSplitted[0]);
    }
    public void descriptionExtractor(String splitted)
    {
        String[] splittedcount = splitted.split("SUMMARY:");
        String[] moreSplitted = splittedcount[1].split("\n");
        System.out.println(moreSplitted[0]);
    }
    public void exceptionExtractor(String splitted) throws ParseException {
        System.out.println("Exxxtraction");
        String[] splittedcount = splitted.split("\n");
//        System.out.println(splittedcount[0]);
        String[] Exc = new String[100];
        int tempindex=0;
        for(String i: splittedcount)
        {
            if(i.contains("EXDATE:"))
            {
                String ExDate;
                ExDate=i.split("EXDATE:")[1];
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
                SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
                Date d = sdf.parse(ExDate);
                String formattedTime = output.format(d);
                Exc[tempindex]=formattedTime;
                System.out.println(Exc[tempindex]);
                tempindex++;
            }
        }

    }
}