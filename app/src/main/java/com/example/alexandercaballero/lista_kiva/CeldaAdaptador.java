package com.example.alexandercaballero.lista_kiva;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Alexander Caballero on 16/2/2017.
 */

public class CeldaAdaptador extends ArrayAdapter<JSONObject> {

    public CeldaAdaptador (Context context, int textViewResourseId){
        super(context, textViewResourseId);
    }
    public CeldaAdaptador(Context context, int resourse, List<JSONObject> items){
        super(context,resourse,items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View celda = convertView;
        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda= layoutInflater.inflate(R.layout.celda_adaptador,null);
        }

        TextView id = (TextView) celda.findViewById(R.id.id);
        TextView nombre=(TextView) celda.findViewById(R.id.nombre);
        TextView ubicacion=(TextView) celda.findViewById(R.id.ubicacion);
        TextView monto=(TextView) celda.findViewById(R.id.monto);
        TextView posted_date=(TextView) celda.findViewById(R.id.posted_date);
        TextView status=(TextView) celda.findViewById(R.id.status);
        TextView sector=(TextView) celda.findViewById(R.id.sector);
        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.imagen);

        JSONObject elemento=this.getItem(position);
        try {
            id.setText(elemento.getString("name"));
            nombre.setText(elemento.getJSONObject("location").getString("country"));
            ubicacion.setText("Uso: "+elemento.getString("use"));
            status.setText("Estado: "+elemento.getString("status"));
            sector.setText("Sector: "+elemento.getString("sector"));
            monto.setText("Monto: "+elemento.getString("loan_amount"));
            posted_date.setText("Fecha de publicación: "+elemento.getString("posted_date"));
            String imagen=elemento.getJSONObject("image").getString("id");
            int img= Integer.parseInt(imagen);
            String url = "https://www.kiva.org/img/512/"+img+".jpg";
            niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return celda;
    }
}
