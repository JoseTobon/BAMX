package mx.itesm.bamx

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.itesm.bamx.databinding.FragmentEspecieBinding


class EspecieFragment : Fragment(R.layout.fragment_especie) {

    lateinit var nombre : EditText
    lateinit var producto : EditText
    lateinit var productor : EditText
    lateinit var correo : EditText
    lateinit var telefono : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentEspecieBinding.inflate(layoutInflater)
        var view = inflater.inflate(R.layout.fragment_especie, container, false)

        nombre = view.findViewById(R.id.nombreDE)
        producto = view.findViewById(R.id.productoDE)
        productor = view.findViewById(R.id.productorDE)
        correo = view.findViewById(R.id.correoDE)
        telefono = view.findViewById(R.id.telefonoDE)

        bind.button.setOnClickListener {
            val persona = hashMapOf(
                "nombre" to nombre.text.toString(),
                "producto" to producto.text.toString(),
                "productor" to productor.text.toString(),
                "correo" to correo.text.toString(),
                "telefono" to telefono.text.toString()
            )

            val coleccion : CollectionReference = Firebase.firestore.collection("donantesEconomicos")

            val taskAdd = coleccion.add(persona)

            taskAdd.addOnSuccessListener { documentReference ->

                Toast.makeText(activity,"id ${documentReference.id}", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { error->

                Toast.makeText(activity,"ERROR AL GUARDADO", Toast.LENGTH_SHORT).show()

                Log.e("FIRESTORE", "error: $error")
            }
        }

        return view
    }
}