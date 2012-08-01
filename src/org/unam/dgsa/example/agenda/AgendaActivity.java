package org.unam.dgsa.example.agenda;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import org.unam.dgsa.example.agenda.db.Contacto;
import org.unam.dgsa.example.agenda.db.Telefono;

import java.util.ArrayList;

public class AgendaActivity extends ListActivity {
    public static final ArrayList<Contacto> contactos=
            new ArrayList<Contacto>();

    static {
        Contacto c = new Contacto("UNAM", "DGSA");
        c.getTelefonos().add(new Telefono(c, "65496849684"));
        contactos.add(c);
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ContactAdapter(this, contactos));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()!=R.id.mni_nuevo) return false;
        return true;
    }
}
