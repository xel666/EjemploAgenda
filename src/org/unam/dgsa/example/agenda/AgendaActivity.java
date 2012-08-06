package org.unam.dgsa.example.agenda;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import org.unam.dgsa.example.agenda.db.Contacto;
import org.unam.dgsa.example.agenda.db.Telefono;

import java.util.ArrayList;
import java.util.List;

public class AgendaActivity extends ListActivity {
/*    public static final ArrayList<Contacto> contactos=
            new ArrayList<Contacto>();

    static {
        Contacto c = new Contacto("UNAM", "DGSA");
        c.getTelefonos().add(new Telefono(c, "65496849684"));
        contactos.add(c);
    }
*/
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ContactAdapter(this));
    }

	@Override
	protected void onResume() {
		super.onResume();
		ContactosDbAdapter.open(this);
		ContactAdapter adapter = (ContactAdapter) getListAdapter();
		adapter.clear();
		new Loader().execute(adapter);
	}

    @Override
	protected void onPause() {
		super.onPause();
		ContactosDbAdapter.close();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()!=R.id.mni_nuevo) return false;
        Intent intent = new Intent(Intent.ACTION_INSERT, null, this, EditActivity.class);
        startActivity(intent);
        return true;
    }
    
    private static class Loader extends AsyncTask<ContactAdapter, Void,
    											List<Contacto>> {
    	ContactAdapter adapter;

		@Override
		protected List<Contacto> doInBackground(ContactAdapter... params) {
			adapter = params[0];
			return ContactosDbAdapter.listContactos();
		}

		@Override
		protected void onPostExecute(List<Contacto> result) {
			for(Contacto c : result)
				adapter.add(c);
		}
    	
    }
}
