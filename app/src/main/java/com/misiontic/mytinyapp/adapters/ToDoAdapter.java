package com.misiontic.mytinyapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.misiontic.mytinyapp.R;
import com.misiontic.mytinyapp.db.DataBase;
import com.misiontic.mytinyapp.models.ToDo;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private ArrayList<ToDo> toDos;
    private Context context;

    public ToDoAdapter(ArrayList<ToDo> toDos, Context context) {
        this.toDos = toDos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.task_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtTitulo.setText(String.valueOf(this.toDos.get(position).getTitulo()));
        holder.txtDesc.setText(String.valueOf(this.toDos.get(position).getDescripcion()));

        if(this.toDos.get(position).getEstado() == 0){
            holder.estado.setChecked(false);
            //holder.txtTitulo.setText(String.valueOf(toDos.get(position).getTitulo()));
        }else{
            holder.estado.setChecked(true);
           // holder.txtTitulo.setText(toDos.get(position).getTitulo() + " Ok");
        }

       holder.estado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase db =new DataBase(context);
                if(toDos.get(position).getEstado() == 0){
                    db.updateToDo(toDos.get(position).getId(),1);

                }else{
                    db.updateToDo(toDos.get(position).getId(),0);

                }

            }
        });



    }


    @Override
    public int getItemCount() {
        return toDos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitulo,txtDesc;
        private Switch estado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtTitulo = itemView.findViewById(R.id.txtTitulo);
            this.txtDesc   = itemView.findViewById(R.id.txtDescripcion);
            this.estado    = itemView.findViewById(R.id.swEstado);


        }
    }



}
