package pmdm.ar.trasteandot3.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pmdm.ar.trasteandot3.R
import pmdm.ar.trasteandot3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val arrayCosas = arrayOf("unaCosa", "dosCosas", "tresCosas", "cuatroCosas", "cincoCosas")
        val arrayAdapter = ArrayAdapter(requireContext(),  R.layout.elemento ,arrayCosas)
        arrayAdapter.setDropDownViewResource(
            //android.R.layout.simple_spinner_dropdown_item
            R.layout.elemento2
        )
        binding.campoSelect.adapter = arrayAdapter
        binding.campoSelect.setSelection(0, false)
        binding.campoSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                Toast.makeText(activity,"position: $position",Toast.LENGTH_LONG).show()
                binding.textHome.text = (view as TextView).text
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(requireContext(), "onNothingSelected", Toast.LENGTH_SHORT).show()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}