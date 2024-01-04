package com.horoscopeapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.horoscopeapp.databinding.ItemHoroscopeBinding
import com.horoscopeapp.domain.model.HoroscopeInfo
import com.horoscopeapp.utils.Constants.ROTATION_BY
import com.horoscopeapp.utils.Constants.ROTATION_DURATION

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        val context = binding.tvHoroscope.context

        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscope.text = context.getString(horoscopeInfo.name)

        binding.clHoroscopeItem.setOnClickListener {
            startRotationAnimation(
                view = binding.ivHoroscope,
                endAction = { onItemSelected(horoscopeInfo) })
        }
    }

    private fun startRotationAnimation(view: View, endAction: () -> Unit) {
        view.animate().apply {
            duration = ROTATION_DURATION
            interpolator = LinearInterpolator()
            rotationYBy(ROTATION_BY)
            withEndAction { endAction() }
            start()
        }
    }
}