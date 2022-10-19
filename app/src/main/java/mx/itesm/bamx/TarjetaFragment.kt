package mx.itesm.bamx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


class TarjetaFragment : Fragment() {

    lateinit var boton : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tarjeta, container, false)

        boton = view.findViewById(R.id.botonTarjeta)

        val monetariasFragment = MonetariasFragment()

        boton.setOnClickListener {
            Toast.makeText(activity,"ENVIADO CON ÉXITO", Toast.LENGTH_SHORT).show()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.containerView, monetariasFragment)
                commit()
            }
        }

        return view
    }

}