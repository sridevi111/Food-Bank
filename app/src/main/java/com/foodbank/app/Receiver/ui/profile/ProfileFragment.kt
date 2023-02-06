package com.foodbank.app.Receiver.ui.profile

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.foodbank.app.databinding.FragmentProfileBinding
import com.foodbank.app.utils.PreferenceManager


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    var thiscontext: Context? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        if (container != null) {
            thiscontext = container.context
        }

        val root: View = binding.root

        setData()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setData() {

        var email = thiscontext?.let { PreferenceManager.getInstance(it).getEmail() };
        var name = thiscontext?.let { PreferenceManager.getInstance(it).getName() }
        var userType = thiscontext?.let { PreferenceManager.getInstance(it).getUserType() }

        val nameTV: TextView = binding.userName
        val emailTV: TextView = binding.email
        val userTypeTV: TextView = binding.userType

        nameTV.setText(name);
        emailTV.setText(email);
        userTypeTV.setText(userType);

        // Set a toolbar to replace the action bar.
        // Set a toolbar to replace the action bar.


    }
}