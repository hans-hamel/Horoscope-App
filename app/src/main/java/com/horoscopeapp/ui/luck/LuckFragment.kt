package com.horoscopeapp.ui.luck

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.horoscopeapp.R
import com.horoscopeapp.databinding.FragmentLuckBinding
import com.horoscopeapp.ui.core.listeners.OnSwipeTouchListener
import com.horoscopeapp.ui.provider.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initPrediction()
        initListeners()
    }

    private fun initPrediction() {
        val luck = randomCardProvider.getLucky()

        luck?.let { luckyModel ->
            val luckText = getString(luckyModel.text)

            binding.tvLuckyText.text = luckText
            binding.ivLuckyCard.setImageResource(luckyModel.image)
            binding.tvShareLucky.setOnClickListener { shareResult(luckText) }
        }
    }

    private fun shareResult(prediction: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        startActivity(shareIntent)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {

        binding.ivRoulette.setOnTouchListener(object : OnSwipeTouchListener(requireContext()) {
            override fun onSwipeRight() {
                spinRoulette()
            }

            override fun onSwipeLeft() {
                spinRoulette()
            }
        })
    }

    private fun spinRoulette() {
        val random = Random()
        val degrees = random.nextInt(1440) + 360

        ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat()).apply {
            duration = 2000
            interpolator = DecelerateInterpolator()
            doOnEnd { slideCard() }
            start()
        }
    }

    private fun slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                binding.ivSmallBackCard.isVisible = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                increaseCardSize()
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        binding.ivSmallBackCard.startAnimation(slideUpAnimation)
    }

    private fun increaseCardSize() {
        val increaseSizeAnimation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.increase_size)

        increaseSizeAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.ivSmallBackCard.isVisible = false
                showPrediction()
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        binding.ivSmallBackCard.startAnimation(increaseSizeAnimation)
    }

    private fun showPrediction() {
        appearAnimation()
        disappearAnimation()
    }

    private fun appearAnimation() {
        val appearAnimation = AlphaAnimation(0.0f, 1.0f)

        appearAnimation.duration = 1000

        binding.clPrediction.startAnimation(appearAnimation)
    }

    private fun disappearAnimation() {
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)

        disappearAnimation.duration = 200
        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.clRoulette.isVisible = false
                binding.clPrediction.isVisible = true
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        binding.clRoulette.startAnimation(disappearAnimation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}