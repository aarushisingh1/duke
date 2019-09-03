import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;

public class Duke {
    static String sep = "@#@";

    public static class Task {
        protected String taskType;
        protected String description;
        protected boolean isDone;

        public Task() {

        }

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public Task(String description, boolean isDone) {
            this.description = description;
            this.isDone = isDone;
        }

        public String getStatusIcon() {
            return (isDone ? "[✓]" : "[X]"); // return tick or X symbols
        }

        public String toString() {
            return taskType + " " + getStatusIcon() + " " + description;
        }
    }

    public static class ToDo extends Task {

        public ToDo(String description) {
            super(description);
            this.taskType = "[T]";
        }

        public ToDo(String description, boolean isDone) {
            super(description, isDone);
            this.taskType = "[T]";
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static class Deadline extends Task {

        protected Date by;
        protected String strDate;

        public Deadline(String description, Date by, String strDate) {
            super(description);
            this.by = by;
            this.taskType = "[D]";
            this.strDate = strDate;
        }

        public Deadline(String description, Date by, boolean isDone, String strDate) {
            super(description, isDone);
            this.by = by;
            this.taskType = "[D]";
            this.strDate = strDate;
        }


        @Override
        public String toString() {
            return super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
            this.taskType = "[E]";
        }

        public Event(String description, String at, boolean isDone) {
            super(description, isDone);
            this.at = at;
            this.taskType = "[E]";
        }

        @Override
        public String toString() {
            return super.toString() + " (at: " + at + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Task> arr;
        arr = loadTasks();

        String input;
        String inputArr[];
        String tempArr[];
        Date dateToEnter;
        SimpleDateFormat formatter1=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Duke! What can I do for you today?");
        input = scanner.nextLine();
        inputArr = input.split(" ", 2);

        int num = 0;

        while (!input.equals("bye")) {
            if (!inputArr[0].equals("todo") && !inputArr[0].equals("deadline") && !inputArr[0].equals("event") && !inputArr[0].equals("done") && !inputArr[0].equals("list") && !inputArr[0].equals("find") && !inputArr[0].equals("delete")){
                System.out.println("☹ OOPS!!! I'm sorry but I don't know what that means.");
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
                continue;
            }
            if (inputArr[0].equals("todo")) {
                if (inputArr.length == 1) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                } else {
                    ToDo td = new ToDo(inputArr[1]);
                    td.isDone = false;
                    arr.add(td);
                    System.out.println(
                            "Got it! I've added this task: \n" + "[T]" + td.getStatusIcon() + " " + inputArr[1]);
                    System.out.println("You now have " + arr.size() + " tasks in the list.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                }
            }
            if (inputArr[0].equals("deadline")) {
                if (inputArr.length == 1) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                } else {
                    tempArr = inputArr[1].split("/by", 2);
                    dateToEnter = formatter1.parse(tempArr[1]);
                    Deadline dl = new Deadline(tempArr[0], dateToEnter, tempArr[1]);
                    System.out.println("Got it! I've added this task: \n" + "[D]" + dl.getStatusIcon() + " "
                            + tempArr[0] + "(by: " + tempArr[1] + ")");
                    arr.add(dl);
                    System.out.println("You now have " + arr.size() + " tasks in the list.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                }
            }
            if (inputArr[0].equals("event")) {
                if (inputArr.length == 1) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                } else {
                    tempArr = inputArr[1].split("/at", 2);
                    Event ev = new Event(tempArr[0], tempArr[1]);
                    System.out.println("Got it! I've added this task: \n" + "[E]" + ev.getStatusIcon() + " "
                            + tempArr[0] + "(at: " + tempArr[1] + ")");
                    arr.add(ev);
                    System.out.println("You now have " + arr.size() + " tasks in the list.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                }
            }
            if (inputArr[0].equals("done")) {
                if (inputArr.length == 1) {
                    System.out.println("☹ OOPS!!! The description of a done task cannot be empty.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                } else {
                    num = Integer.parseInt(inputArr[1]) - 1;
                    arr.get(num).isDone = true;
                    System.out.println("Nice! I've marked this task as done: \n" + arr.get(num).toString());
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                }
            }
            if (inputArr[0].equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                int i=0;
                for (Task t : arr) {
                    System.out.println(++i + ". " + t.toString());
                }
                input = scanner.nextLine();
                inputArr = input.split(". ", 2);
            }
            if (inputArr[0].equals("find")) {
                int i=0;
                for (Task t : arr) {
                    ++i;
                    if(t.description.contains(inputArr[1])){
                        System.out.println(i + ". " + t.toString());
                    }
                }
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
            }
            if (inputArr[0].equals("delete")) {
                if (inputArr.length == 1) {
                    System.out.println("☹ OOPS!!! The description of a delete task cannot be empty.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                } else {
                    num = Integer.parseInt(inputArr[1]) - 1;
                    System.out.println("Noted! I've marked removed this task: \n" + arr.get(num).toString());
                    System.out.println("You now have " + (arr.size() - 1) + " tasks in the list.");
                    arr.remove(num);
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                }
            }
        }

        scanner.close();
        saveTasks(arr);
        System.out.println("Bye! See you again soon!");

    }

    public static void saveTasks(ArrayList<Task> arr) {
        if (arr.size() > 0) {
            // open file
            try {
                FileWriter fw = new FileWriter("tasks.txt");
                for (Task t : arr) {
                    switch (t.taskType) {
                        case "[T]":
                            fw.write(t.taskType + sep + t.description + sep + t.isDone + System.lineSeparator());
                            break;
                        case "[D]":
                            fw.write(t.taskType + sep + t.description + sep + t.isDone + sep + ((Deadline) t).by +sep + ((Deadline) t).strDate + System.lineSeparator());
                            break;
                        case "[E]":
                            fw.write(t.taskType + sep + t.description + sep + t.isDone + sep + ((Event) t).at + System.lineSeparator());
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

    public static ArrayList<Task> loadTasks() {
        String s;
        String tempArr[];
        Date dll;
        SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        ArrayList<Task> l = new ArrayList<Task>();
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
                        ToDo td = new ToDo(tempArr[1], Boolean.parseBoolean(tempArr[2]));
                        l.add(td);
                        break;
                    case "[D]":
                        dll = formatter6.parse(tempArr[4]);
                        Deadline dl = new Deadline(tempArr[1], dll, Boolean.parseBoolean(tempArr[2]), tempArr[4]);
                        l.add(dl);
                        break;
                    case "[E]":
                        Event ev = new Event(tempArr[1], tempArr[3], Boolean.parseBoolean(tempArr[2]));
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