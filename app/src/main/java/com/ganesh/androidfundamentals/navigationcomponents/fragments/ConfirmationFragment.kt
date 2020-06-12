package com.ganesh.androidfundamentals.navigationcomponents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.navigationcomponents.Money
import kotlinx.android.synthetic.main.fragment_confirmation.*

class ConfirmationFragment : Fragment() {

    private lateinit var recipient: String
    private lateinit var money: Money

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConfirmationFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        recipient = arguments?.getString("recipient") as String
        money = arguments?.getParcelable("amount")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val amount = money?.amount
        val confirmationMessage = "You have sent $$amount to $recipient"
        confirmation_message?.text = confirmationMessage
    }


}