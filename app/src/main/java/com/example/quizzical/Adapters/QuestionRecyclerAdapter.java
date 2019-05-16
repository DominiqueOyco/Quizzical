package com.example.quizzical.Adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizzical.R;
import com.example.quizzical.Model.Question;

import java.util.List;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionViewHolder> {

    private List<Question> listQuestion;

    public QuestionRecyclerAdapter(List<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question_recycler, parent, false);

        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        holder.textViewQuestion.setText(listQuestion.get(position).getQuestion());
        holder.textViewAnswer1.setText(listQuestion.get(position).getAnswer1());
        holder.textViewAnswer2.setText(listQuestion.get(position).getAnswer2());
        holder.textViewAnswer3.setText(listQuestion.get(position).getAnswer3());
        holder.textViewAnswer4.setText(listQuestion.get(position).getAnswer4());
        holder.textViewCorrect.setText(listQuestion.get(position).getCorrect());
    }

    @Override
    public int getItemCount() {
        Log.v(QuestionRecyclerAdapter.class.getSimpleName(),""+listQuestion.size());
        return listQuestion.size();
    }


    /**
     * ViewHolder class
     */
    public class QuestionViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewQuestion;
        public AppCompatTextView textViewAnswer1;
        public AppCompatTextView textViewAnswer2;
        public AppCompatTextView textViewAnswer3;
        public AppCompatTextView textViewAnswer4;
        public AppCompatTextView textViewCorrect;

        public QuestionViewHolder(View view) {
            super(view);
            textViewQuestion = (AppCompatTextView) view.findViewById(R.id.textViewQuestion);
            textViewAnswer1 = (AppCompatTextView) view.findViewById(R.id.textViewAnswer1);
            textViewAnswer2 = (AppCompatTextView) view.findViewById(R.id.textViewAnswer2);
            textViewAnswer3 = (AppCompatTextView) view.findViewById(R.id.textViewAnswer3);
            textViewAnswer4 = (AppCompatTextView) view.findViewById(R.id.textViewAnswer4);
            textViewCorrect = (AppCompatTextView) view.findViewById(R.id.textViewCorrect);
        }
    }

}
