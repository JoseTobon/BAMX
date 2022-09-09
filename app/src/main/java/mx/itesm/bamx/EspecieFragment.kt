package mx.itesm.bamx

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.handleCoroutineException
import mx.itesm.bamx.databinding.FragmentEspecieBinding


class EspecieFragment : Fragment(R.layout.fragment_especie) {

    lateinit var nombre : EditText
    lateinit var producto : EditText
    lateinit var productor : EditText
    lateinit var correo : EditText
    lateinit var telefono : EditText

    lateinit var boton : Button

    var vacio = false

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
        boton = view.findViewById(R.id.enviarDE)

        boton.setOnClickListener {

            editTextVacio(nombre, vacio)
            editTextVacio(producto, vacio)
            editTextVacio(productor, vacio)
            editTextVacio(correo, vacio)
            editTextVacio(telefono, vacio)

            Log.wtf("BOTON", "SI FUNCIONO")

            val persona = hashMapOf(
                "nombre" to nombre.text.toString(),
                "producto" to producto.text.toString(),
                "productor" to productor.text.toString(),
                "correo" to correo.text.toString(),
                "telefono" to telefono.text.toString()
            )

            if (vacio == false) {

                val coleccion : CollectionReference = Firebase.firestore.collection("donantesEconomicos")

                val taskAdd = coleccion.add(persona)

                taskAdd.addOnSuccessListener { documentReference ->

                    Toast.makeText(activity,"id ${documentReference.id}", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { error->

                    Toast.makeText(activity,"ERROR AL GUARDADO", Toast.LENGTH_SHORT).show()

                    Log.e("FIRESTORE", "error: $error")
                }

                nombre.setText("")
                producto.setText("")
                productor.setText("")
                correo.setText("")
                telefono.setText("")
            }

        }

        return view
    }

    fun editTextVacio ( editText: EditText, vacio : Boolean) {
        if (TextUtils.isEmpty(editText.text)) {
            editText.setError("Campo Obligatorio")
            this.vacio = true
        } else {
            this.vacio = false
        }
    }
}