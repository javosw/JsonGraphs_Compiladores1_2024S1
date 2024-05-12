package josq.dashb;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import josq.dashb.databinding.ActivityMainBinding;
import josq.lenguajes.automatas.Automatas;
import josq.lenguajes.automatas.Ejecucion;
import josq.lenguajes.modelos.Dashb;
import josq.lenguajes.traduccion.HTMLinador;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bParse.setOnClickListener(e -> {
            try {
                Dashb miDash = Ejecucion.getDashbDesdeString(binding.editor.getText().toString());
                String html = HTMLinador.getPage(miDash);
                System.out.println(html);
            } catch (Exception ex) {
                System.out.println("@setOnClickListener: "+ex.getMessage());
            }
        });

        binding.graficos.setOnLongClickListener(myView -> {
            showPopUpMenu(myView);
            return true;
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
            binding.editor.setText(myString);
            return true;
        }
        catch (Exception e) {
            System.out.println("@onMenuItemClick: "+e.getMessage());
        }

        return false;
    }


}