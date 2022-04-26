package com.example.ejemplo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolderPeriodico> {

    private ArrayList<Periodico> listaPeriodicos;

    public MiAdaptador(ArrayList<Periodico> listaPeriodicos) {
        this.listaPeriodicos = listaPeriodicos;
    }

    public static class ViewHolderPeriodico extends RecyclerView.ViewHolder {
        private TextView tvNombre, tvPrecio, tvTipo;

        public ViewHolderPeriodico(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvTipo = itemView.findViewById(R.id.tvTipo);
        }
    }

// CLASE INTERNA - VIEWHOLDER A USAR

    @Override
    public ViewHolderPeriodico onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cada_fila_layout, viewGroup, false);
        ViewHolderPeriodico viewHolderPeriodico = new ViewHolderPeriodico(itemView);
        return viewHolderPeriodico;
    }

    @Override
    public void onBindViewHolder(ViewHolderPeriodico viewHolder, int pos) {
        Periodico per = listaPeriodicos.get(pos);

        viewHolder.tvNombre.setText(per.getNombre());
        viewHolder.tvPrecio.setText(per.getPrecio()+"");
        viewHolder.tvTipo.setText(per.getTipo());

    }

    @Override
    public int getItemCount() {

        return listaPeriodicos.size();
    }

    @Override
    public long getItemId(int pos) {

        return pos;
    }

    @Override
    public int getItemViewType(int pos) {

        return pos;
    }

}
