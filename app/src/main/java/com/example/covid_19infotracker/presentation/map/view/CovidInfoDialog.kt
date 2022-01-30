package com.example.covid_19infotracker.presentation.map.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.covid_19infotracker.R
import com.example.covid_19infotracker.data.model.CovidInfoPerCountry
import com.example.covid_19infotracker.databinding.DialogCovidInfoBinding
import com.example.covid_19infotracker.presentation.map.viewmodel.MapViewModel

class CovidInfoDialog: DialogFragment(R.layout.dialog_covid_info) {

    private val mViewModel: MapViewModel by activityViewModels()
    private lateinit var mBinding: DialogCovidInfoBinding
    private lateinit var covidInfoPerCountry: CovidInfoPerCountry
    private val mArgs: CovidInfoDialogArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = DialogCovidInfoBinding.inflate(LayoutInflater.from(context))

        covidInfoPerCountry = mArgs.country

    /*if(covidInfoPerCountry.countryName != null)
        mBinding.tvShowArticles.visibility = View.VISIBLE*/

        with(mBinding){
            tvShowArticles.setOnClickListener{
                openCountryNewsFragment( mViewModel.countryCode.value)
            }

            country = if (covidInfoPerCountry.countryName == null) "Total" else covidInfoPerCountry.countryName
            covidRelatedInfo = covidInfoPerCountry.covidRelatedInfo

        }

        mViewModel.countryCode.observe(this.requireActivity(), {
            if(it != null){
                mBinding.tvShowArticles.visibility = View.VISIBLE
            }
        })


        val dialog = AlertDialog.Builder(requireActivity(), R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setView(mBinding.root)
            .create()

        dialog.window?.setBackgroundDrawableResource(R.drawable.rounded_dialog_background)

        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        return dialog
    }

    private fun openCountryNewsFragment(countryCode: String?) {
        if(countryCode == null) return

        val action = CovidInfoDialogDirections.actionCovidInfoDialogToCountryNewsFragment(countryCode)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewModel.countryCode.value = null
    }
}