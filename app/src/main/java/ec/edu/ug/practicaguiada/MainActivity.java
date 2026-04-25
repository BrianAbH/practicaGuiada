package ec.edu.ug.practicaguiada;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jspecify.annotations.NonNull;

public class MainActivity extends AppCompatActivity {

    EditText etNombre;
    EditText etCorreo;
    Button btnEnviar;
    private static final String STATE_NOMBRE = "STATE_NOMBRE";
    public static final String EXTRA_NOMBRE = "EXTRA_NOMBRE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.et_Nombre);
        etCorreo = findViewById(R.id.et_Correo);
        btnEnviar = findViewById(R.id.btn_Saludar);


        btnEnviar.setOnClickListener(v->{
            String nombre = etNombre.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();

            if(nombre.trim().isEmpty() || correo.trim().isEmpty()){
                Toast.makeText(this, "Campos Vacios", Toast.LENGTH_SHORT).show();
            }
        });

        etNombre.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && etNombre.getText().toString().isEmpty()) {
                etNombre.setError("No puede quedar vacío");
            }
        });

        etCorreo.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && etCorreo.getText().toString().isEmpty()) {
                etCorreo.setError("No puede quedar vacío");
            }
        });


        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_NOMBRE, etNombre.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        etNombre.setText(saveInstanceState.getString(STATE_NOMBRE,""));
    }
}