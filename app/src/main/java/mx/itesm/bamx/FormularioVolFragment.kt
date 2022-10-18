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

class FormularioVolFragment : Fragment(R.layout.fragment_formulario_vol) {

    lateinit var nombre : EditText
    lateinit var empresa : EditText
    lateinit var correo : EditText
    lateinit var telefono : EditText
    lateinit var mensaje : EditText

    lateinit var boton : Button

    var vacio = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_formulario_vol, container, false)

        nombre = view.findViewById(R.id.nameVol)
        empresa = view.findViewById(R.id.empresaVoluntario)
        correo = view.findViewById(R.id.correoVoluntario)
        telefono = view.findViewById(R.id.telefonoVoluntario)
        mensaje = view.findViewById(R.id.mensajeVoluntario)

        boton = view.findViewById(R.id.enviarVoluntario)

        boton.setOnClickListener {

            editTextVacio(nombre, vacio)
            editTextVacio(empresa, vacio)
            editTextVacio(correo, vacio)
            editTextVacio(telefono, vacio)
            editTextVacio(mensaje, vacio)
            //editTextVacio(mensaje, vacio)

            Log.wtf("BOTON", "SI FUNCIONO")

            val persona = hashMapOf(
                "nombre" to nombre.text.toString(),
                "producto" to empresa.text.toString(),
                "correo" to correo.text.toString(),
                "telefono" to telefono.text.toString(),
                "mensaje" to mensaje.text.toString()
            )

            if (!vacio) {

                val coleccion : CollectionReference = Firebase.firestore.collection("voluntarios")

                val taskAdd = coleccion.add(persona)

                taskAdd.addOnSuccessListener { documentReference ->

                    Toast.makeText(activity,"ENVIADO CON ÉXITO", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { error->

                    Toast.makeText(activity,"ERROR AL GUARDADO", Toast.LENGTH_SHORT).show()

                    Log.e("FIRESTORE", "error: $error")
                }

                nombre.setText("")
                empresa.setText("")
                correo.setText("")
                telefono.setText("")
                mensaje.setText("")
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