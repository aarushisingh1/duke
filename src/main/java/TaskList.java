import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;

public class TaskList {

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

}
