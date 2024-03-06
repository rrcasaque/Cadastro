package com.casaque.cadastro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNome, etTelefone, etEmail, etCidade;
    private CheckBox cbEmailList;
    private RadioGroup rgSexo;
    private RadioButton rbSelecionado;
    private Spinner spinnerUF;
    private Button btnSalvar, btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
        etCidade = findViewById(R.id.etCidade);
        cbEmailList = findViewById(R.id.cbEmailList);
        rgSexo = findViewById(R.id.rgSexo);
        spinnerUF = findViewById(R.id.spinnerUF);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.brazil_states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUF.setAdapter(adapter);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Coleta os dados do formulário
                String nome = etNome.getText().toString();
                String telefone = etTelefone.getText().toString();
                String email = etEmail.getText().toString();
                String cidade = etCidade.getText().toString();
                String uf = spinnerUF.getSelectedItem().toString();
                boolean isInEmailList = cbEmailList.isChecked();
                int idRadioButtonSelecionado = rgSexo.getCheckedRadioButtonId();
                rbSelecionado = findViewById(idRadioButtonSelecionado);
                String sexo = rbSelecionado != null ? rbSelecionado.getText().toString() : "";

                // Cria a mensagem com os dados
                String mensagem = "Nome: " + nome +
                        "\nTelefone: " + telefone +
                        "\nE-mail: " + email +
                        "\nCidade: " + cidade +
                        "\nEstado: " + uf +
                        "\nSexo: " + sexo +
                        "\nIngressar na lista de e-mails? " + (isInEmailList ? "Sim" : "Não");

                // Mostra a mensagem em um AlertDialog
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Informações do Formulário")
                        .setMessage(mensagem)
                        .setPositiveButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }
        });


        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Limpa os dados do formulário
                etNome.setText("");
                etTelefone.setText("");
                etEmail.setText("");
                etCidade.setText("");
                cbEmailList.setChecked(false);
                rgSexo.clearCheck();
                spinnerUF.setSelection(0);
            }
        });
    }
}
