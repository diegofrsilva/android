package com.cadastroalunos;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cadastroalunos.dao.AlunoDAO;
import com.cadastroalunos.modelo.Aluno;

public class FormularioActivity extends ActionBarActivity {

	protected AlunoDAO alunoDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.formulario);
		this.alunoDAO = new AlunoDAO(this);
		
		Button button = (Button) findViewById(R.id.botaoGravar);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FormularioHelper formularioHelper = new FormularioHelper(FormularioActivity.this);
				Aluno aluno = formularioHelper.pegaAlunoDoFormulario();
				
				alunoDAO = new AlunoDAO(FormularioActivity.this);
				alunoDAO.insere(aluno);
			
				finish();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.alunoDAO.close();
	}
}
