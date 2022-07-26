package es.ipow.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import es.ipow.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnQuery.setOnClickListener {
            var data = ""
            db.collection("contacts") // Colección
                .get()
                .addOnSuccessListener { resultado -> // Documentos
                    for (documento in resultado){
                        data += "${documento.id}:\n ${documento.data}\n\n"
                    }
                    b.tvRequest.text = data
                }
                .addOnFailureListener { exception ->
                    b.tvRequest.text = "Error. No se ha podido conectar. $exception"
                }
        }
    }
}