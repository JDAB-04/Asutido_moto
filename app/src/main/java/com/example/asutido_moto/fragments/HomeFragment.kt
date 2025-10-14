    package com.example.asutido_moto.fragments

    import android.content.Intent
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import androidx.fragment.app.Fragment
    import com.example.asutido_moto.R
    import com.example.asutido_moto.activities.PerfilActivity

    class HomeFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.activity_home, container, false)

            val btnComienza = view.findViewById<Button>(R.id.botoninicio)

            btnComienza.setOnClickListener {
                val intent = Intent(requireContext(), PerfilActivity::class.java)
                startActivity(intent)
            }

            return view
        }
    }