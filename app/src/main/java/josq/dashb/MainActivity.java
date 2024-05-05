package josq.dashb;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import josq.dashb.databinding.ActivityMainBinding;
import josq.lenguajes.automatas.Automata;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bParse.setOnClickListener(e -> {
            try {
                Automata.getDashbDesdeString(binding.editor.getText().toString());

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
        int id = item.getItemId();
        if(id == R.id.todas) {
            System.out.println("TODAS"); return true; }
        else if(id == R.id.barras) {
            System.out.println("BARRAS"); return true; }
        else if(id == R.id.pastel) {
            System.out.println("PASTEL"); return true; }
        else if(id == R.id.puntos) { return true; }
        else if(id == R.id.lineas) { return true; }
        else if(id == R.id.tarjeta) { return true; }
        return false;
    }


}