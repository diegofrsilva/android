package com.cadastroalunos;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cadastroalunos.modelo.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

	private List<Aluno> alunos;
	private Activity activity;
	
	public ListaAlunosAdapter(Activity activity, List<Aluno> alunos) {
		super();
		this.alunos = alunos;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public Object getItem(int location) {
		return alunos.get(location);
	}

	@Override
	public long getItemId(int location) {
		return alunos.get(location).getId();
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {
		Aluno aluno = alunos.get(posicao);
		View view = activity.getLayoutInflater().inflate(R.layout.item, null);
		Bitmap fotoBitmap = getFotoAluno(aluno);
		
		TextView nome = (TextView) view.findViewById(R.id.nome);
        nome.setText(aluno.getNome());
        
        ImageView imageView = (ImageView) view.findViewById(R.id.foto);
        imageView.setImageBitmap(fotoBitmap);


        if (posicao % 2 == 0) {
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        } else {
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
        }
        return view;
	}

	private Bitmap getFotoAluno(Aluno aluno) {
		Bitmap bm = aluno.getFoto() != null ?  BitmapFactory.decodeFile(aluno.getFoto()) : BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
		return Bitmap.createScaledBitmap(bm, 110, 110, true);
	}
}
