package mx.itesm.bamx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class GalleryFragment : Fragment() {

    lateinit var izq : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        izq = view.findViewById(R.id.galeriaDer)

        val homeFragment = HomeFragment()

        izq.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.containerView, homeFragment)
                commit()
            }
        }

        return view
    }


}