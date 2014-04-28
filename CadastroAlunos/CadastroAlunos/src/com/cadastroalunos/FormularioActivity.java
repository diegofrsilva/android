package com.cadastroalunos;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FormularioActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		Button button = (Button) findViewById(R.id.botaoGravar);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(FormularioActivity.this, "Botão clicado", Toast.LENGTH_LONG).show();
				FormularioActivity.this.finish();
			}
		});
	}
}
