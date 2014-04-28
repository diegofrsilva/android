package com.cadastroalunos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListagemAlunosActivity extends ActionBarActivity {

	private ListView listaAlunos;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);

        String alunos[] = {"Anderson", "Filipe", "Guilherme"};
        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        this.listaAlunos.setAdapter(new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, alunos));
        this.listaAlunos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int index, long id) {
				Toast.makeText(ListagemAlunosActivity.this, 
						"Selecionado na posição: " + index, 
						Toast.LENGTH_LONG).show();
			}
		});
        this.listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int index, long id) {
				Toast.makeText(ListagemAlunosActivity.this, 
						adapter.getItemAtPosition(index).toString(), 
						Toast.LENGTH_LONG).show();
				
				return Boolean.TRUE;
			}
		});
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
        	Intent intent = new Intent(this,  FormularioActivity.class);
        	startActivity(intent);
            return false;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}