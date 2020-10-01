package net.penguincoders.doit.Model;

public class ToDoModel {



    private int id, status;
    private String task;
    private String task_title;

    // new lines i added
    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }


    public String getTask_title() {
        return task_title;
    }

    //-----------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
