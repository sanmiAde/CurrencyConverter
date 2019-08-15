package com.sanmiaderibigbe.currencyconverter


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sanmiaderibigbe.currencyconverter.data.Currency
import com.sanmiaderibigbe.currencyconverter.ui.CurrencySelectionDialogFragment
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_currency_to_convert_from.setOnClickListener {
            showCurrencySelection(it)
        }

        text_currency_to_convert_to.setOnClickListener {
            showCurrencySelection(it)
        }
    }

    private fun showCurrencySelection(textView: View){
        val currencySelectionDialogFragment = CurrencySelectionDialogFragment.newInstance()
        currencySelectionDialogFragment.onCurrencySelected = { currency: Currency ->  updateSelectedCurrency(currency, textView)}
        currencySelectionDialogFragment.show(requireFragmentManager(), "CurrencySelectionDialogFragment")

    }

    private fun updateSelectedCurrency(currency: Currency, textView: View) {
        val context = requireContext()
        val text = context.getString(R.string.currency_abbreviation, currency.flag, currency.code)
        (textView as TextView ).setText(text)
    }
}
