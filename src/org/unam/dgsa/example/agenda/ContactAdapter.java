package org.unam.dgsa.example.agenda;

import android.*;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.unam.dgsa.example.agenda.db.Contacto;
import org.unam.dgsa.example.agenda.db.Telefono;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xel666
 * Date: 1/08/12
 * Time: 9:08
 * To change this template use File | Settings | File Templates.
 */
public class ContactAdapter extends ArrayAdapter<Contacto> {
    private LayoutInflater mInflater;

    public ContactAdapter(Context context) {
        super(context, 0);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ValueTag tag;
        if(convertView==null) {
            convertView=mInflater.inflate(android.R.layout.simple_list_item_2,
                    parent, false);
            tag = new ValueTag();
            tag.nombre = (TextView) convertView.findViewById(android.R.id.text1);
            tag.telefono = (TextView) convertView.findViewById(android.R.id.text2);
            convertView.setTag(tag);
        } else {
            tag= (ValueTag) convertView.getTag();
        }
        Contacto c = getItem(position);
        tag.nombre.setText(c.getNombre()+' '+c.getApellido());
        if(c.getTelefonos().isEmpty())
            tag.telefono.setText(R.string.msg_tels_empty);
        else {
            Telefono t = c.getTelefonos().iterator().next();
            tag.telefono.setText(t.getValue());
        }
        return convertView;
    }

    private class ValueTag {
        TextView nombre;
        TextView telefono;
    }
}
