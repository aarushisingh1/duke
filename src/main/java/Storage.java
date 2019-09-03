import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;


public class Storage {
    static String sep = "@#@";
    public static ArrayList<TaskList.Task> loadTasks() {
        String s;
        String tempArr[];
        Date dll;
        SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        ArrayList<TaskList.Task> l = new ArrayList<TaskList.Task>();
        try {
            File f = new File("tasks.txt");
            if (!f.exists()) {
                return l;
            }
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
                tempArr = s.split(sep);
                switch (tempArr[0]) {
                    case "[T]":
                        TaskList.ToDo td = new TaskList.ToDo(tempArr[1], Boolean.parseBoolean(tempArr[2]));
                        l.add(td);
                        break;
                    case "[D]":
                        dll = formatter6.parse(tempArr[4]);
                        TaskList.Deadline dl = new TaskList.Deadline(tempArr[1], dll, Boolean.parseBoolean(tempArr[2]), tempArr[4]);
                        l.add(dl);
                        break;
                    case "[E]":
                        TaskList.Event ev = new TaskList.Event(tempArr[1], tempArr[3], Boolean.parseBoolean(tempArr[2]));
                        l.add(ev);
                        break;
                    default:
                        ;
                }
            }
            scanner.close();
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

}
