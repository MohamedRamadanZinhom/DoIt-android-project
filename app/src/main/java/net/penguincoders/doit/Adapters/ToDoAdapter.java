package net.penguincoders.doit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.penguincoders.doit.AddNewTask;
import net.penguincoders.doit.MainActivity;
import net.penguincoders.doit.Model.ToDoModel;
import net.penguincoders.doit.R;
import net.penguincoders.doit.Utils.DatabaseHandler;
import net.penguincoders.doit.activity_details;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private static List<ToDoModel> todoList;
    private DatabaseHandler db;
    private static Context activity;
    private MainActivity _activity;

    public ToDoAdapter(DatabaseHandler db, Context activity,MainActivity _activity) {
        this.db = db;
        this.activity = activity;
        this._activity = _activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        db.openDatabase();

         final ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask_title());

        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.updateStatus(item.getId(), 1);
                } else {
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public Context getContext() {
        return activity;
    }

    public void setTasks(List<ToDoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        ToDoModel item = todoList.get(position);
        db.deleteTask(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        ToDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("title", item.getTask_title());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(_activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        CheckBox task;



        ViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);
        task = view.findViewById(R.id.todoCheckBox);

       }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            ToDoModel item= todoList.get(position);
            Intent intent=new Intent(activity, activity_details.class);
            intent.putExtra("task",item.getTask());
            intent.putExtra("Email",item.getTask_title() );
            activity.startActivity(intent);

            //Toast.makeText(activity, item.getTask(), Toast.LENGTH_LONG).show();
        }
    }
}
