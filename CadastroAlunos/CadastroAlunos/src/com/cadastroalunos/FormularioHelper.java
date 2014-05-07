package com.cadastroalunos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.cadastroalunos.modelo.Aluno;


public class FormularioHelper {
	
	private EditText editTextNome;
	private EditText editTextSite;
	private EditText editTextEndereco;
	private EditText editTextTelefone;
	private SeekBar seekBarNota;
	private Aluno aluno;
	private ImageView foto;

	public FormularioHelper(FormularioActivity formulario) {
		this.editTextNome = (EditText) formulario.findViewById(R.id.nome);
		this.editTextSite = (EditText) formulario.findViewById(R.id.site);
		this.editTextEndereco = (EditText) formulario.findViewById(R.id.endereco);
		this.editTextTelefone = (EditText) formulario.findViewById(R.id.telefone);
		this.seekBarNota = (SeekBar) formulario.findViewById(R.id.nota);
		this.foto = (ImageView) formulario.findViewById(R.id.imagem);
		this.aluno = new Aluno();
	}
	
	public Aluno  pegaAlunoDoFormulario() {
		this.aluno.setNome(editTextNome.getEditableText().toString());
		this.aluno.setSite(editTextSite.getEditableText().toString());
		this.aluno.setEndereco(editTextEndereco.getEditableText().toString());
		this.aluno.setTelefone(editTextTelefone.getEditableText	().toString());
		this.aluno.setNota(seekBarNota.getProgress());

		return aluno;
	}

	public void colocarAlunoNoFormulario(Aluno alunoEdicao) {
		editTextNome.setText(alunoEdicao.getNome());
		editTextSite.setText(alunoEdicao.getSite());
		editTextEndereco.setText(alunoEdicao.getEndereco());
		editTextTelefone.setText(alunoEdicao.getTelefone());
		seekBarNota.setProgress(Double.valueOf(alunoEdicao.getNota()).intValue());
		
		if(alunoEdicao.getFoto() != null) {
			carregaImagem(alunoEdicao.getFoto());
		}
		this.aluno = alunoEdicao;
	}

	public ImageView getFoto() {
		return foto;
	}

	public void carregaImagem(String absolutePath) {
		this.aluno.setFoto(absolutePath);
		
		Bitmap imagemFoto = BitmapFactory.decodeFile(absolutePath);
		Bitmap imagenRedimensionada = Bitmap.createScaledBitmap(imagemFoto, 200, 200, Boolean.TRUE);

		ImageView foto2 = getFoto();
		foto2.setImageBitmap(imagenRedimensionada);
	}
}