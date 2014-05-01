package com.cadastroalunos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cadastroalunos.dao.AlunoDAO;
import com.cadastroalunos.modelo.Aluno;

public class ListagemAlunosActivity extends ActionBarActivity {

	private ListView listaAlunos;
	private Aluno alunoSelecionado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);

		this.preencherLista();
		this.listaAlunos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int index, long id) {
				Toast.makeText(ListagemAlunosActivity.this, "Selecionado na posição: " + index, Toast.LENGTH_LONG).show();
			}
		});
		this.listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int index, long id) {
				alunoSelecionado = (Aluno) adapter.getItemAtPosition(index);
				return Boolean.FALSE;
			}
		});
		this.registerForContextMenu(listaAlunos);
	}

	private void preencherLista() {
		AlunoDAO alunoDAO = new AlunoDAO(this);

		this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);
		this.listaAlunos.setAdapter(new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunoDAO.getLista()));
		
		alunoDAO.close();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_novo:
			Intent intent = new Intent(this, FormularioActivity.class);
			startActivity(intent);
			return false;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		menu.add("Ligar");
		menu.add("Enviar SMS");
		menu.add("Achar no Mapa");
		menu.add("Navegar no site");
		MenuItem menuItemDeletar = menu.add("Deletar");
		menu.add("Enviar E-mail");

		menuItemDeletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlunoDAO alunoDAO = new AlunoDAO(ListagemAlunosActivity.this);
				alunoDAO.delete(alunoSelecionado);
				preencherLista();
				return false;
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		this.preencherLista();
	}
}