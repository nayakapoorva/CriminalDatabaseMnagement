package com.example.criminal_dbms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> criminalno;
    ArrayList<String> criminalname;
    ArrayList<String > criminalhaw;
    ArrayList<String > criminalage;
    ArrayList<String> criminaladdress;
    ArrayList<String> crimetypee;
    ArrayList<String> crimedates;
    ArrayList<String> casesecriptions;


    class SearchViewHolder extends RecyclerView.ViewHolder{
        TextView criminalnumber,names;
        TextView cage,caddress;
        TextView chaww,crimetypeee;
        TextView crimedatess,casedescriptionss;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            criminalnumber = itemView.findViewById(R.id.criminalnumber);
            names = itemView.findViewById(R.id.names);
            cage = itemView.findViewById(R.id.cage);
            caddress = itemView.findViewById(R.id.caddress);
            chaww = itemView.findViewById(R.id.chaww);
            crimetypeee = itemView.findViewById(R.id.crimetypeee);
            crimedatess = itemView.findViewById(R.id.crimedatess);
            casedescriptionss = itemView.findViewById(R.id.casedescriptionss);

        }
    }

    public SearchAdapter(Context context, ArrayList<String> criminalno, ArrayList<String> criminalname, ArrayList<String> criminalhaw, ArrayList<String> criminalage, ArrayList<String> criminaladdress, ArrayList<String> crimetypee, ArrayList<String> crimedates, ArrayList<String> casesecriptions) {
        this.context = context;
        this.criminalno = criminalno;
        this.criminalname = criminalname;
        this.criminalhaw = criminalhaw;
        this.criminalage = criminalage;
        this.criminaladdress = criminaladdress;
        this.crimetypee = crimetypee;
        this.crimedates = crimedates;
        this.casesecriptions = casesecriptions;
    }

    @NonNull
    @Override



    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list,parent,false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.criminalnumber.setText(criminalno.get(position));
        holder.names.setText(criminalname.get(position));
        holder.cage.setText(criminalage.get(position));
        holder.caddress.setText(criminaladdress.get(position));
        holder.chaww.setText(criminalhaw.get(position));
        holder.crimetypeee.setText(crimetypee.get(position));
        holder.crimedatess.setText(crimedates.get(position));
        holder.casedescriptionss.setText(casesecriptions.get(position));



    }

    @Override
    public int getItemCount() {
        return criminalname.size();
    }
}
