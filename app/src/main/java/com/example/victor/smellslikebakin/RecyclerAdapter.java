package com.example.victor.smellslikebakin;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class RecyclerAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        return new ListViewHolder(view);
    }

    protected abstract int getLayoutId();

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return Recipes.names.length;
    }

private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView mImageView;
    private TextView mTextView;
    private int mIndex;

    public ListViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.itemText);
        mImageView = itemView.findViewById(R.id.itemImage);
        itemView.setOnClickListener(this);
    }

    public void bindView(int position) {
        mIndex = position;
        mTextView.setText(Recipes.names[position]);
        mImageView.setImageResource(Recipes.resourceIds[position]);
    }

    @Override
    public void onClick(View v) {
        onRecipeSelected(mIndex);
    }
}

    protected abstract void onRecipeSelected(int index);
}

