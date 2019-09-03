import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;

public class Duke {
    public static void main(String[] args) throws Exception {
        ArrayList<TaskList.Task> arr;
        arr = Storage.loadTasks();

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
                    TaskList.ToDo td = new TaskList.ToDo(inputArr[1]);
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
                    TaskList.Deadline dl = new TaskList.Deadline(tempArr[0], dateToEnter, tempArr[1]);
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
                    TaskList.Event ev = new TaskList.Event(tempArr[0], tempArr[1]);
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
                for (TaskList.Task t : arr) {
                    System.out.println(++i + ". " + t.toString());
                }
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
            }
            if (inputArr[0].equals("find")) {
                int i=0;
                for (TaskList.Task t : arr) {
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
        Storage.saveTasks(arr);
        System.out.println("Bye! See you again soon!");

    }

}