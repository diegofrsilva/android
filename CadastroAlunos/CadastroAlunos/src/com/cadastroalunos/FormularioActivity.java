package com.cadastroalunos;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cadastroalunos.dao.AlunoDAO;
import com.cadastroalunos.modelo.Aluno;

public class FormularioActivity extends ActionBarActivity {

	protected AlunoDAO alunoDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.formulario);
		this.alunoDAO = new AlunoDAO(this);

		final FormularioHelper formularioHelper = new FormularioHelper(FormularioActivity.this);
		final Aluno alunoEdicao = (Aluno) getIntent().getSerializableExtra(Extras.ALUNO_EXTRA);
		Button button = (Button) findViewById(R.id.botaoGravar);
		
		if(alunoEdicao != null) {
			button.setText(R.string.alterar);
			formularioHelper.colocarAlunoNoFormulario(alunoEdicao);
		} else {
			EditText nomeEditText = (EditText) findViewById(R.id.nome);
			nomeEditText.requestFocus();
		}

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Aluno aluno = formularioHelper.pegaAlunoDoFormulario();

				if(alunoEdicao == null) {
					alunoDAO.insere(aluno);
				} else {
					alunoDAO.editar(alunoEdicao.getId(), aluno);
				}
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
