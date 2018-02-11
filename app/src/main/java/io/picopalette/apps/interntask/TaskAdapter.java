package io.picopalette.apps.interntask;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aswin Sundar on 10-02-2018.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<TaskQandA> mTaskQandA= new ArrayList<TaskQandA>();
    private TaskViewHolder lastViewHolder;

    public TaskAdapter(ArrayList<TaskQandA> taskQandA){
        mTaskQandA=taskQandA;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_task, parent, false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, int position) {

        holder.bindData(mTaskQandA.get(position));
        holder.mTaskQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.mTaskAnswer.getVisibility() == View.VISIBLE)
                    holder.mTaskAnswer.setVisibility(View.GONE);
                else {
                    clicked(holder);
                    holder.mTaskAnswer.setVisibility(View.VISIBLE);
                }
            }
        });

    }
    private void clicked(TaskViewHolder viewHolder) {
        if(lastViewHolder != null) {
            lastViewHolder.mTaskAnswer.setVisibility(View.GONE);
        }
        lastViewHolder = viewHolder;
    }

    @Override
    public int getItemCount() {
        return mTaskQandA.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        public TextView mTaskQuestion;
        public TextView mTaskAnswer;
        public TaskViewHolder(View itemView) {
            super(itemView);

            mTaskQuestion = itemView.findViewById(R.id.taskQuestion);
            mTaskAnswer = itemView.findViewById(R.id.taskAnswer);
        }
        public void bindData(TaskQandA taskQandA){
            mTaskQuestion.setText(taskQandA.getQues());
            mTaskAnswer.setText(taskQandA.getAns());
        }

    }
}
