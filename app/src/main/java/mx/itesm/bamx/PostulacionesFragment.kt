package mx.itesm.bamx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.bamx.databinding.FragmentPostulacionesBinding


class PostulacionesFragment : Fragment(R.layout.fragment_postulaciones) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentPostulacionesBinding.inflate(layoutInflater)
        val formularioVolFragment = FormularioVolFragment()

        bind.postulacionesBoton.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.containerView, formularioVolFragment)
                commit()
            }
        }

        return bind.root
    }
}