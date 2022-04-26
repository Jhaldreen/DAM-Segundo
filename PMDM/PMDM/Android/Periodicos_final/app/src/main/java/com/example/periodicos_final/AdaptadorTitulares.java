package com.example.periodicos_final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorTitulares extends
        RecyclerView.Adapter<AdaptadorTitulares.ViewHolder>    {

    List<Titulares> misfilas;

    OnRecyclerViewLongItemClickListener itemLongClickListener;


    public AdaptadorTitulares(List<Titulares>  datosEnviados) {
        misfilas=datosEnviados;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.filacontacto, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Titulares item = misfilas.get(position);
        holder.itemView.setTag(item);//aquí está el truco se mete el objeto en forma de tag dentro del view
        holder.nombre.setText(misfilas.get(position).getNombre());
        holder.periodico.setText(misfilas.get(position).getPeriodico());

     }


    @Override
    public int getItemCount() {
        return misfilas.size();
    }

    public void remove(Titulares t){
        misfilas.remove(t);
    }



    public void setOnItemLongClickListener(OnRecyclerViewLongItemClickListener listener) { //Long Click Listener
        this.itemLongClickListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
            implements  View.OnLongClickListener {

        TextView nombre;
        TextView periodico;


        public ViewHolder(View v) {
            super(v);
            nombre =  v.findViewById(R.id.nombre);
            periodico = v.findViewById(R.id.periodico);
            v.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (itemLongClickListener != null) {
                itemLongClickListener.onItemLongClick(v,
                        getAdapterPosition());//con getAdapterPosition le pasamos la posición dentro del array de objetos
            }
            return true;
        }

    }







}

