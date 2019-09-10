import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;

//handles the saving and loading of data
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
    public static void saveTasks(ArrayList<TaskList.Task> arr) {
        if (arr.size() > 0) {
            // open file
            try {
                FileWriter fw = new FileWriter("tasks.txt");
                for (TaskList.Task t : arr) {
                    switch (t.taskType) {
                        case "[T]":
                            fw.write(t.taskType + sep + t.description + sep + t.isDone + System.lineSeparator());
                            break;
                        case "[D]":
                            fw.write(t.taskType + sep + t.description + sep + t.isDone + sep + ((TaskList.Deadline) t).by + sep + ((TaskList.Deadline) t).strDate + System.lineSeparator());
                            break;
                        case "[E]":
                            fw.write(t.taskType + sep + t.description + sep + t.isDone + sep + ((TaskList.Event) t).at + System.lineSeparator());
                            break;
                    }
                }
                fw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
