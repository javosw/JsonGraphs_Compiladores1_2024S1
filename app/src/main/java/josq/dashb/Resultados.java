package josq.dashb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import josq.dashb.databinding.ActivityMainBinding;
import josq.dashb.databinding.ActivityResultadosBinding;
import josq.lenguajes.automatas.Registros;

public class Resultados extends AppCompatActivity {

    ActivityResultadosBinding miBinding;
    private final int INTENT_SAVE = 2;

    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if(resultData != null && resultData.getData() == null) {
            Toast.makeText(this, "ACCION NO REALIZABLE",Toast.LENGTH_LONG).show();
            return;
        }

        if (requestCode == INTENT_SAVE) writeText(resultData.getData(), Registros.html.toString());

        super.onActivityResult(requestCode, resultCode, resultData);
    }
    private void writeText(Uri miUri, String miString) {
        try {
            OutputStream miStream = getContentResolver().openOutputStream(miUri);
            BufferedWriter miWriter = new BufferedWriter(new OutputStreamWriter(miStream));
            miWriter.write(miString);
            miWriter.flush();
            miWriter.close();
        }
        catch (Exception e) { Toast.makeText(this.getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show(); }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        miBinding = ActivityResultadosBinding.inflate(getLayoutInflater());
        setContentView(miBinding.getRoot());

        miBinding.reportSintactico.setMovementMethod(new ScrollingMovementMethod());
        miBinding.reportSemantico.setMovementMethod(new ScrollingMovementMethod());
        miBinding.reportSintactico.setText(Registros.sintactico.toString());
        miBinding.reportSemantico.setText(Registros.semantico.toString());

        miBinding.buttonSave.setOnClickListener(e->{
            Intent miIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            miIntent.setType("*/*");
            miIntent.putExtra(Intent.EXTRA_TITLE, "MisGraficos.html");

            startActivityForResult(miIntent, INTENT_SAVE);

        });
    }
}
