package mx.itesm.bamx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class AboutUsFragment : Fragment() {

    lateinit var der : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about_us, container, false)

        der = view.findViewById(R.id.nosotrosDer)

        val homeFragment = HomeFragment()

        der.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.containerView, homeFragment)
                commit()
            }
        }

        return view
    }

}