package mx.itesm.bamx

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
//import mx.itesm.bamx.databinding.FragmentMonetariasBinding
import org.w3c.dom.Text

class MonetariasFragment : Fragment() {

    lateinit var monto : EditText

    lateinit var nombreTxt : TextView
    lateinit var nombre : EditText
    lateinit var apellidoTxt : TextView
    lateinit var apellido : EditText
    lateinit var direccionTxt : TextView
    lateinit var direccion : EditText
    lateinit var correoTxt : TextView
    lateinit var correo : EditText
    lateinit var telefonoTxt : TextView
    lateinit var telefono : EditText
    lateinit var paisTxt : TextView
    lateinit var pais : EditText

    lateinit var boton : Button
    lateinit var botonOut : Button
    lateinit var boton1 : Button
    lateinit var boton2 : Button
    lateinit var toggle : Switch
    lateinit var textDonacion : TextView
    lateinit var imagDonacion : ImageView

    //lateinit var formulario : ScrollView

    var vacio = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val bind = FragmentMonetariasBinding.inflate(layoutInflater)
        val view = inflater.inflate(R.layout.fragment_monetarias, container, false)

        monto = view.findViewById(R.id.montoDE)

        nombreTxt = view.findViewById(R.id.textView9)
        nombre = view.findViewById(R.id.nombreDM)
        apellidoTxt = view.findViewById(R.id.textView10)
        apellido = view.findViewById(R.id.apellidoDM)
        direccionTxt = view.findViewById(R.id.textView13)
        direccion = view.findViewById(R.id.direccionDM)
        correoTxt = view.findViewById(R.id.textView11)
        correo = view.findViewById(R.id.correoDM)
        telefonoTxt = view.findViewById(R.id.textView12)
        telefono = view.findViewById(R.id.telefonoDM)
        paisTxt = view.findViewById(R.id.textView14)
        pais = view.findViewById(R.id.paisDM)
        boton1 = view.findViewById(R.id.toggleButton)
        boton2 = view.findViewById(R.id.toggleButton2)
        imagDonacion = view.findViewById(R.id.imageView9)
        textDonacion = view.findViewById(R.id.textView18)

        boton = view.findViewById(R.id.enviarDM)
        botonOut = view.findViewById(R.id.botonOut)
        toggle = view.findViewById(R.id.donAnon)

        //formulario = view.findViewById(R.id.scroll)

        botonOut.isVisible = false

        textDonacion.isVisible=false;

        boton2.setOnClickListener {
            textDonacion.isVisible=true
            imagDonacion.isVisible=false
        }

        boton1.setOnClickListener {
            textDonacion.isVisible=false
            imagDonacion.isVisible=true
        }




        toggle.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                //formulario.isVisible = false
                nombre.isVisible = false
                nombreTxt.isVisible = false
                apellido.isVisible = false
                apellidoTxt.isVisible = false
                direccion.isVisible = false
                direccionTxt.isVisible = false
                correo.isVisible = false
                correoTxt.isVisible = false
                telefono.isVisible = false
                telefonoTxt.isVisible = false
                pais.isVisible = false
                paisTxt.isVisible = false
                boton.isVisible = false
                botonOut.isVisible = true
            } else {
                //formulario.isVisible = true
                nombre.isVisible = true
                nombreTxt.isVisible = true
                apellido.isVisible = true
                apellidoTxt.isVisible = true
                direccion.isVisible = true
                direccionTxt.isVisible = true
                correo.isVisible = true
                correoTxt.isVisible = true
                telefono.isVisible = true
                telefonoTxt.isVisible = true
                pais.isVisible = true
                paisTxt.isVisible = true
                boton.isVisible = true
                botonOut.isVisible = false
            }
        }

        botonOut.setOnClickListener {
            editTextVacio(monto, vacio)

            val personaAnonima = hashMapOf(
                "monto" to monto.text.toString()
            )

            if (!vacio) {

                val coleccion : CollectionReference = Firebase.firestore.collection("donantesAnonimos")

                val taskAdd = coleccion.add(personaAnonima)

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

        boton.setOnClickListener {

            editTextVacio(monto, vacio)
            editTextVacio(nombre, vacio)
            editTextVacio(apellido, vacio)
            editTextVacio(direccion, vacio)
            editTextVacio(correo, vacio)
            editTextVacio(telefono, vacio)
            editTextVacio(pais, vacio)

            Log.wtf("BOTON", "SI FUNCIONO")


            val persona = hashMapOf(
                "monto" to monto.text.toString(),
                "nombre" to nombre.text.toString(),
                "apellido" to apellido.text.toString(),
                "direccion" to direccion.text.toString(),
                "correo" to correo.text.toString(),
                "telefono" to telefono.text.toString(),
                "pais" to pais.text.toString()
            )

            if (!vacio) {

                val coleccion : CollectionReference = Firebase.firestore.collection("donantesMonetarios")

                val taskAdd = coleccion.add(persona)

                taskAdd.addOnSuccessListener { documentReference ->

                    Toast.makeText(activity,"id ${documentReference.id}", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { error->

                    Toast.makeText(activity,"ERROR AL GUARDADO", Toast.LENGTH_SHORT).show()

                    Log.e("FIRESTORE", "error: $error")
                }

                monto.setText("")
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