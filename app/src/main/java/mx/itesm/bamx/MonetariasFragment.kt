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
import mx.itesm.bamx.databinding.FragmentMonetariasBinding
import org.w3c.dom.Text

class MonetariasFragment : Fragment() {

    lateinit var nombre : EditText
    lateinit var apellido : EditText
    lateinit var direccion : EditText
    lateinit var correo : EditText
    lateinit var telefono : EditText
    lateinit var pais : EditText

    lateinit var boton : Button

    var vacio = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentMonetariasBinding.inflate(layoutInflater)
        var view = inflater.inflate(R.layout.fragment_monetarias, container, false)

        nombre = view.findViewById(R.id.nombreDM)
        apellido = view.findViewById(R.id.apellidoDM)
        direccion = view.findViewById(R.id.direccionDM)
        correo = view.findViewById(R.id.correoDM)
        telefono = view.findViewById(R.id.telefonoDM)
        pais = view.findViewById(R.id.paisDM)
        boton = view.findViewById(R.id.enviarDM)


        boton.setOnClickListener {

            editTextVacio(nombre, vacio)
            editTextVacio(apellido, vacio)
            editTextVacio(direccion, vacio)
            editTextVacio(correo, vacio)
            editTextVacio(telefono, vacio)
            editTextVacio(pais, vacio)

            Log.wtf("BOTON", "SI FUNCIONO")


            val persona = hashMapOf(
                "nombre" to nombre.text.toString(),
                "apellido" to apellido.text.toString(),
                "direccion" to direccion.text.toString(),
                "correo" to correo.text.toString(),
                "telefono" to telefono.text.toString(),
                "pais" to pais.text.toString()
            )

            if (vacio == false) {

                val coleccion : CollectionReference = Firebase.firestore.collection("donantesMonetarios")

                val taskAdd = coleccion.add(persona)

                taskAdd.addOnSuccessListener { documentReference ->

                    Toast.makeText(activity,"id ${documentReference.id}", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { error->

                    Toast.makeText(activity,"ERROR AL GUARDADO", Toast.LENGTH_SHORT).show()

                    Log.e("FIRESTORE", "error: $error")
                }

                nombre.setText("")
                apellido.setText("")
                direccion.setText("")
                correo.setText("")
                telefono.setText("")
                pais.setText("")
            }

        }


        // Inflate the layout for this fragment
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