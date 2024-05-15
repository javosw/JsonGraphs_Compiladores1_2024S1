package josq.dashb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import josq.dashb.databinding.ActivityMainBinding;
import josq.lenguajes.automatas.Ejecucion;
import josq.lenguajes.automatas.Registros;
import josq.lenguajes.modelos.Dashb;
import josq.lenguajes.traduccion.HTMLinador;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding miBinding;

    private String readText(Uri miUri) {
        try {
            InputStream myStream = getContentResolver().openInputStream(miUri);
            return IOUtils.toString(myStream, StandardCharsets.UTF_8);
        }
        catch(Exception e){
            Toast.makeText(this.getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    private final int INTENT_OPEN = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if(resultData != null && resultData.getData() == null) {
            Toast.makeText(this, "ACCION NO REALIZABLE",Toast.LENGTH_LONG).show();
            return;
        }

        if (requestCode == INTENT_OPEN) miBinding.editor.setText(readText(resultData.getData()));

        super.onActivityResult(requestCode, resultCode, resultData);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        miBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(miBinding.getRoot());

        miBinding.bParse.setOnClickListener(e -> {
            try {
                Registros.clearRegistros();
                Dashb miDash = Ejecucion.getDashbDesdeString(miBinding.editor.getText().toString());
                Registros.html.append(HTMLinador.getPage(miDash));

                Intent miIntent = new Intent(MainActivity.this, Resultados.class);
                startActivity(miIntent);
            }
            catch (Exception ex) { System.out.println(ex.getMessage()); }
        });
        miBinding.graficos.setOnLongClickListener(myView -> {
            showPopUpMenu(myView);
            return true;
        });
        miBinding.bOpen.setOnClickListener(e -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            startActivityForResult(intent, INTENT_OPEN);
        });
    }

    private void showPopUpMenu(View myView)
    {
        PopupMenu myMenu = new PopupMenu(this, myView);
        myMenu.setOnMenuItemClickListener(i -> { return onMenuItemClick(i); });
        myMenu.getMenuInflater().inflate(R.menu.menu_graficos, myMenu.getMenu());
        myMenu.show();
    }

    boolean onMenuItemClick(MenuItem item) {
        InputStream myStream = null;

        int id = item.getItemId();
        if(id == R.id.todas) {
            myStream = this.getResources().openRawResource(R.raw.todas);
        }
        else if(id == R.id.barras) {
            myStream = this.getResources().openRawResource(R.raw.barras);
        }
        else if(id == R.id.pastel) {
            myStream = this.getResources().openRawResource(R.raw.pastel);
        }
        else if(id == R.id.puntos) {
            myStream = this.getResources().openRawResource(R.raw.puntos);
        }
        else if(id == R.id.lineas) {
            myStream = this.getResources().openRawResource(R.raw.lineas);
        }
        else if(id == R.id.tarjeta) {
            myStream = this.getResources().openRawResource(R.raw.tarjeta);
        }

        try {
            assert myStream != null;
            String myString = IOUtils.toString(myStream, StandardCharsets.UTF_8);
            miBinding.editor.setText(myString);
            return true;
        }
        catch (Exception e) {
            System.out.println("@onMenuItemClick: "+e.getMessage());
        }

        return false;
    }
}