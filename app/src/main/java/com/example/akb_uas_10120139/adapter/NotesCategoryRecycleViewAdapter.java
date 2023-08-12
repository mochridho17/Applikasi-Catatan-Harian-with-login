// Nim : 10120139
// Nama : Mochamad Ridho
// Kelas : IF4
package com.example.akb_uas_10120139.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akb_uas_10120139.Config;
import com.example.akb_uas_10120139.R;
import com.example.akb_uas_10120139.helper.DBHelper;
import com.example.akb_uas_10120139.model.NoteCategory;
import com.example.akb_uas_10120139.ui.notes.DetailNotesActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

public class NotesCategoryRecycleViewAdapter extends RecyclerView.Adapter<NotesCategoryRecycleViewAdapter.MyViewHolder> {

    private Context ctx;
    private ArrayList<NoteCategory> list_category;
    private DatabaseReference DB;
    private FirebaseAuth Auth;

    public NotesCategoryRecycleViewAdapter(Context context, ArrayList<NoteCategory> list_category) {
        this.ctx = context;
        this.list_category = list_category;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DB = FirebaseDatabase.getInstance(Config.getDB_URL()).getReference();
        Auth = FirebaseAuth.getInstance();
        holder.fNoteCategoryTitle.setText(list_category.get(position).title);
        holder.fNoteCategoryTotal.setText(list_category.get(position).total + " Notes");

        // View on click redirect
        holder.lNoteCategory.setOnClickListener(v -> {
            Intent intent = new Intent(ctx, DetailNotesActivity.class);
            // Pass the category
            intent.putExtra("category", list_category.get(position).title);
            ctx.startActivity(intent);
        });

        // Delete button onclick
        holder.bNoteCategoryDelete.setOnClickListener(v -> {
            // Alert notification
            AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
            alert.setTitle("Delete");
            alert.setMessage("Doing so will permanently delete the data at with this category, including all nested data");
            alert.setPositiveButton("Sure", (dialog, which) -> {
                // Delete data
                DBHelper.deleteNoteCategory(DB, Auth.getUid(), list_category.get(position).title);

                Toast.makeText(ctx, "Delete data success !!",
                        Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            });

            alert.setNegativeButton("Nope", (dialog, which) -> dialog.dismiss());

            alert.show();
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fNoteCategoryTitle;
        TextView fNoteCategoryTotal;
        Button bNoteCategoryDelete;
        LinearLayout lNoteCategory;
        public MyViewHolder(@NonNull View v) {
            super(v);
            fNoteCategoryTitle = v.findViewById(R.id.category_notes_title);
            fNoteCategoryTotal = v.findViewById(R.id.category_notes_total);
            bNoteCategoryDelete = v.findViewById(R.id.category_notes_delete);
            lNoteCategory = v.findViewById(R.id.category_notes_layout);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.category_notes, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list_category.size();
    }

}
