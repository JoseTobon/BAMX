package mx.itesm.bamx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.bamx.databinding.FragmentDineroBinding
import mx.itesm.bamx.databinding.FragmentHomeBinding


class DineroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentDineroBinding.inflate(layoutInflater)
        val monetariasFragment = MonetariasFragment()
        val especieFragment = EspecieFragment()

        bind.button3.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.containerView, monetariasFragment)
                commit()
            }
        }

        bind.button4.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.containerView, especieFragment)
                commit()
            }
        }

        return bind.root
    }
}