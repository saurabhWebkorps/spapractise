package com.example.spapractise.ui.auth.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.viewModels

import androidx.lifecycle.lifecycleScope
import com.example.spapractise.R
import com.example.spapractise.URL.SUPPORT_MAIL
import com.example.spapractise.data.services.base.BaseFragment
import com.example.spapractise.data.services.base.DialogUtils
import com.example.spapractise.databinding.FragmentLoginBinding
import com.example.spapractise.ui.auth.dataclass.User
import com.example.spapractise.utilities.Resource
import com.example.spapractise.utilities.showMessage
import com.example.spapractise.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    var binding: FragmentLoginBinding? = null
    
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = Dialog(requireContext())
//        progressDialog!!.setMessage("Please wait...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setContentView(R.layout.dialog_loader)
        progressDialog!!.setCanceledOnTouchOutside(false)
        progressDialog!!.window?.setLayout(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        progressDialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view


        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClick()
        loginResponse()



    }
    private fun setClick() {



        binding?.buttonLogin?.setOnClickListener {

                    lifecycleScope.launchWhenCreated {
                        authViewModel.loginUser(
                            User(
                                country_code = binding!!.Phone.ccp.selectedCountryCodeWithPlus,
                                mobile_number = binding!!.Phone.editTextPhoneNumber.text.toString(),
                                password = binding!!.textInputPassword.text.toString()
                            )
                        )
                    }
                }
            }


    private fun loginResponse() {
        lifecycleScope.launchWhenCreated {
            authViewModel.loginUser.collect { result ->
                when (result) {
                    is Resource.Error -> {
                        toggleLoader(false)
                        if (result.code == 403) {
                            DialogUtils().showGeneralDialog(
                                requireActivity(),
                                message = getString(R.string.suspended_msg) + " " + SUPPORT_MAIL,
                                negativeText = getString(R.string.button_ok),
                                onNoClick = {

                                })
                        } else {
                            result.message?.let {
                                showMessage(
                                    binding!!.root,
                                    it
                                )
                            }
                        }
                    }

                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        toggleLoader(false)
                        result.data?.let { it ->
                            session.token = it.token
                            Log.e("TAG", "loginResponse: $it")
//                            lifecycleScope.launchWhenCreated {
//                                authViewModel.getUser(
//                                    it.token
//                                )
//                            }
                        }
                    }
                }
            }
        }
    }


}