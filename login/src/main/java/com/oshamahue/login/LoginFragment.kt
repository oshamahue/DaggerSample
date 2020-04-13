package com.oshamahue.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.oshamahue.daggerpg.appComponent
import com.oshamahue.login.di.DaggerLoginComponent
import com.oshamahue.login.viewmodel.LoginViewModel
import com.oshamahue.login.viewmodel.LoginViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewmodelFactory: LoginViewModelFactory

    private val viewModel: LoginViewModel by viewModels { viewmodelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginComponent = DaggerLoginComponent.builder()
            .applicationComponent(appComponent)
            .build()
        loginComponent.inject(this)
        viewModel.loginLiveData.observe(viewLifecycleOwner) { result ->
            if (result) {
                showToast("Login successful")
            } else {
                showToast("Login not successful")
            }
        }
        buttonLogin.setOnClickListener {
            if (editTextUsername.text.isNotBlank() && editTextPassword.text.isNotBlank()) {
                viewModel.login(editTextUsername.text.toString(), editTextPassword.text.toString())
            }
        }
    }

    private fun showToast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
    }


}