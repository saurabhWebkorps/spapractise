package com.example.spapractise.data.services.base

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import com.example.spapractise.R
import com.example.spapractise.utilities.core.Session
import javax.inject.Inject


open class BaseFragment : Fragment() {

    internal var progressDialog: Dialog? = null

    @Inject
    lateinit var session: Session
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view1=inflater.inflate(R.layout.fragment_base, container, false)

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

        progressDialog!!.show()
        return view1
    }
    fun toggleLoader(show: Boolean) {
        try {
            if (show) {
                if (!progressDialog!!.isShowing)
                    progressDialog!!.show()
            } else {
                if (progressDialog!!.isShowing)
                    progressDialog!!.dismiss()
            }
        } catch (e: Exception) {
        }
    }

}