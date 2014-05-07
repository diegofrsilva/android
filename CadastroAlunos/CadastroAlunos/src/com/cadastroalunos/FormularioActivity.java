package com.cadastroalunos;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cadastroalunos.dao.AlunoDAO;
import com.cadastroalunos.modelo.Aluno;

public class FormularioActivity extends ActionBarActivity {

	private static final int CAMERA_REQUEST_CODE = 12;
	private AlunoDAO alunoDAO;
	private File storageDirectory;
	private FormularioHelper formularioHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.formulario);
		this.alunoDAO = new AlunoDAO(this);
		this.formularioHelper = new FormularioHelper(FormularioActivity.this);

		final Aluno alunoEdicao = (Aluno) getIntent().getSerializableExtra(Extras.ALUNO_EXTRA);
		Button button = (Button) findViewById(R.id.botaoGravar);
		
		
		if(alunoEdicao != null) {
			button.setText(R.string.alterar);
			formularioHelper.colocarAlunoNoFormulario(alunoEdicao);
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
		
		formularioHelper.getFoto().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				storageDirectory = new File(Environment.getExternalStorageDirectory(), "/" + System.currentTimeMillis() + ".jpg");
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(storageDirectory));
				startActivityForResult(intent, CAMERA_REQUEST_CODE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                formularioHelper.carregaImagem(storageDirectory.getAbsolutePath());
            } else {
                this.storageDirectory= null;
            }
        }
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.alunoDAO.close();
	}
}
