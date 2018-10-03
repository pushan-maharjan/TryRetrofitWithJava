package com.example.turing.retrofitinjavaexample.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.turing.retrofitinjavaexample.R;
import com.example.turing.retrofitinjavaexample.api.model.GitHubRepo;

import org.w3c.dom.Text;

import java.util.List;

public class GitHubAdapter extends ArrayAdapter<GitHubRepo> {
  private final Context context;
  private final List<GitHubRepo> repos;

  public GitHubAdapter(@NonNull Context context, @NonNull List<GitHubRepo> objects) {
    super(context, -1, objects);
    this.context = context;
    this.repos = objects;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    GitHubRepo rep = getItem(position);
    ViewHolder viewHolder;

    if(convertView == null){
      viewHolder = new ViewHolder();
      LayoutInflater inflater = LayoutInflater.from(getContext());
      convertView = inflater.inflate(R.layout.row_item, parent, false);
      viewHolder.name = convertView.findViewById(R.id.repo_name);
      convertView.setTag(viewHolder);
    }
    else{
      viewHolder = (ViewHolder) convertView.getTag();
    }
    viewHolder.name.setText(rep.getName());
    return convertView;
  }

  private class ViewHolder {
    TextView name;
  }
}
