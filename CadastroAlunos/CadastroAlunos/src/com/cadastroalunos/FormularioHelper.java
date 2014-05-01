package com.cadastroalunos;

import android.widget.EditText;
import android.widget.SeekBar;

import com.cadastroalunos.modelo.Aluno;


public class FormularioHelper {
	
	private EditText editTextNome;
	private EditText editTextSite;
	private EditText editTextEndereco;
	private EditText editTextTelefone;
	private SeekBar seekBarNota;

	public FormularioHelper(FormularioActivity formulario) {
		this.editTextNome = (EditText) formulario.findViewById(R.id.nome);
		this.editTextSite = (EditText) formulario.findViewById(R.id.site);
		this.editTextEndereco = (EditText) formulario.findViewById(R.id.endereco);
		this.editTextTelefone = (EditText) formulario.findViewById(R.id.telefone);
		this.seekBarNota = (SeekBar) formulario.findViewById(R.id.nota);
	}
	
	public Aluno  pegaAlunoDoFormulario() {
		Aluno aluno = new Aluno();
		aluno.setNome(editTextNome.getEditableText().toString());
		aluno.setSite(editTextSite.getEditableText().toString());
		aluno.setEndereco(editTextEndereco.getEditableText().toString());
		aluno.setTelefone(editTextTelefone.getEditableText	().toString());
		aluno.setNota(seekBarNota.getProgress());

		return aluno;
	}

	public void colocarAlunoNoFormulario(Aluno alunoEdicao) {
		editTextNome.setText(alunoEdicao.getNome());
		editTextSite.setText(alunoEdicao.getSite());
		editTextEndereco.setText(alunoEdicao.getEndereco());
		editTextTelefone.setText(alunoEdicao.getTelefone());
		seekBarNota.setProgress(Double.valueOf(alunoEdicao.getNota()).intValue());
	}
}