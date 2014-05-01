package com.cadastroalunos.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cadastroalunos.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

	private static final int VERSION = 2;
	private static final String TABELA = "Alunos";
	private static final String CADASTRO_CAELUM = "CadastroCaelum";
	private static final String[] COLUNAS = { "id", "nome", "telefone", "endereco", "site", "nota", "foto" };
	
	public AlunoDAO(Context context) {
		super(context, CADASTRO_CAELUM, null, VERSION);
	}

	public void insere(Aluno aluno) {
		getWritableDatabase().insert(TABELA, null, toContentValues(aluno));
	}

	public void delete(Aluno aluno) {
		getWritableDatabase().delete(TABELA, "id=?", new String[] { aluno.getId().toString() });
	}
	
	private ContentValues toContentValues(Aluno aluno) {
		ContentValues values = new ContentValues();
		
		values.put("nome", aluno.getNome());
		values.put("telefone", aluno.getTelefone());
		values.put("endereco", aluno.getEndereco());
		values.put("site", aluno.getSite());
		values.put("nota", aluno.getNota());
		values.put("foto", aluno.getFoto());
		
		return values;
	}
	
	@Override
	public void onCreate(SQLiteDatabase dataBase) {
		String ddl = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY," + 
				" nome TEXT UNIQUE NOT NULL," + 
				" telefone TEXT," + 
				" endereco TEXT," + 
				" site TEXT," + 
				" nota REAL," + 
				" foto TEXT" + ");";

		dataBase.execSQL(ddl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase dataBase, int version, int version1) {
		String ddl = "DROP TABLE IF EXISTS Alunos";
		dataBase.execSQL(ddl);

		this.onCreate(dataBase);
	}
	
	public List<Aluno> getLista() {
        List<Aluno> alunos = new ArrayList<Aluno>();

        Cursor c = getWritableDatabase().query(TABELA, COLUNAS, null, null, null, null, null);

        while (c.moveToNext()) {
            Aluno aluno = fromCursor(c);
            alunos.add(aluno);
        }
        c.close();

        return alunos;
    }

	private Aluno fromCursor(Cursor c) {
		Aluno aluno = new Aluno();

		aluno.setId(c.getLong(0));
		aluno.setNome(c.getString(1));
		aluno.setTelefone(c.getString(2));
		aluno.setEndereco(c.getString(3));
		aluno.setSite(c.getString(4));
		aluno.setNota(c.getDouble(5));
		aluno.setFoto(c.getString(6));
		return aluno;
	}
}