package com.moataz.afternoonhadeeth.ui.view.fragment.offline

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moataz.afternoonhadeeth.data.source.Hadiths
import com.moataz.afternoonhadeeth.databinding.FragmentHomeOfflineBinding
import com.moataz.afternoonhadeeth.utils.helper.CounterActions
import com.moataz.afternoonhadeeth.utils.helper.Intents.copyText
import com.moataz.afternoonhadeeth.utils.helper.Intents.shareTextSnackbar
import com.moataz.afternoonhadeeth.utils.helper.Intents.sharedText
import com.moataz.afternoonhadeeth.utils.interfaces.IOnBackPressed

class HomeFragmentOffline : Fragment(), IOnBackPressed {

    private lateinit var binding: FragmentHomeOfflineBinding
    private val counter = CounterActions()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeOfflineBinding.inflate(layoutInflater)
        setupCounter()
        setupHadithText()
        getHadith()
        return binding.root
    }

    private fun setupCounter() {
        binding.buttonCounter.setOnClickListener {
            counter.addCounter(binding.buttonCounter)
            counter.vibrateOnce(requireContext())
        }

        binding.counter.setOnClickListener {
            counter.addCounter(binding.buttonCounter)
            counter.vibrateOnce(requireContext())
        }

        binding.resetButtonOnClick.setOnClickListener {
            counter.resetCounter(binding.buttonCounter)
            counter.vibrateOnce(requireContext())
        }
    }

    private fun getHadith() {
        binding.changeButtonOnClick.setOnClickListener { setupHadithText() }
    }

    private fun setupHadithText() {
        binding.hadithTextKanzhasanat.text = Hadiths().firstHadith()
        binding.hadithTextKanzhasanat.movementMethod = ScrollingMovementMethod.getInstance()

        binding.copyButtonOnClick.setOnClickListener {
            copyText(
                binding.hadithTextKanzhasanat.text.toString(),
                requireContext()
            )
            shareTextSnackbar(
                requireView(), "تم نسخ الحديث",
                binding.hadithTextKanzhasanat.text.toString(),
                requireContext()
            )
        }

        binding.shareButtonOnClick.setOnClickListener {
            copyText(binding.hadithTextKanzhasanat.text.toString(), requireContext())
            sharedText(
                requireContext(),
                binding.hadithTextKanzhasanat.text.toString(),
                "تم الإرسال من تطبيق حديث الغروب: أحاديث النبي ﷺ"
            )
        }
    }

    override fun onBackPressed(): Boolean {
        requireActivity().moveTaskToBack(true)
        requireActivity().finish()
        return true
    }
}