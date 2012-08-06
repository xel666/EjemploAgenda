package org.unam.dgsa.example.agenda;

import org.unam.dgsa.example.agenda.db.Contacto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created with IntelliJ IDEA.
 * User: xel666
 * Date: 1/08/12
 * Time: 12:40
 * To change this template use File | Settings | File Templates.
 */
public class EditActivity extends Activity {
    private Contacto contacto;
    private EditText txtNombre;
    private EditText txtApellido;
    private ImageView imgFoto;
    private LinearLayout lstTelefonos;
    private LinearLayout lstCorreos;
    private boolean inserting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);
        Intent in = getIntent();
        if(in.getAction().equals(Intent.ACTION_INSERT)) {
            contacto=new Contacto();
            inserting=true;
        } else if(in.getAction().equals(Intent.ACTION_EDIT)){
            inserting=false;
        } else {
            Log.e(getClass().getSimpleName(), "Unknown action: " +
                    in.getAction());
            finish();
        }
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        lstTelefonos = (LinearLayout) findViewById(R.id.lstTelefonos);
        lstCorreos = (LinearLayout) findViewById(R.id.lstEmail);
        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        findViewById(R.id.btnAddTels).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(BuildConfig.DEBUG) Log.d(EditActivity.class.getSimpleName(), 
						"btnAddTels click");
			}
        	
        });
    }

	@Override
	protected void onResume() {
		super.onResume();
		ContactosDbAdapter.open(this);
	}

    @Override
	protected void onPause() {
		super.onPause();
		ContactosDbAdapter.close();
	}

	private void save() {
        contacto.setNombre(txtNombre.getText().toString());
        contacto.setApellido(txtApellido.getText().toString());
        if(inserting)
            ContactosDbAdapter.insert(contacto);
        setResult(RESULT_OK);
        finish();
    }
}
