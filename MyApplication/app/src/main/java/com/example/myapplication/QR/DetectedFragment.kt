package com.example.myapplication.QR

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentDetectedBinding


class DetectedFragment : Fragment() {
    lateinit var detecAdapter: DetectedAdapter
    lateinit var binding:FragmentDetectedBinding
    var dataList=ArrayList<DetectedInfo>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDetectedBinding.inflate(inflater,container,false)

        binding.urlText.text=arguments?.getString("url")
        var bundledata=arguments?.getSerializable("scanList")
        Log.d("bundle",bundledata.toString())

        var parse=bundledata.toString().replace("{","").replace("}","").split(",")
        for(i in parse){
            var a=i.split("=")
            dataList.add(DetectedInfo(a.get(0),a.get(1)))

        }
        init()
        return binding.root
    }

    fun init(){
        var content=binding.resultText.text.toString()
        var spannablestring= SpannableString(content)
        val word="Warn"
        val start=content.indexOf(word)
        val end=start+word.length
        spannablestring.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000")),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannablestring.setSpan( StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannablestring.setSpan( RelativeSizeSpan(1.3f), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.resultText.text=spannablestring

        var resultdata= arrayListOf<DetectedInfo>()
        detecAdapter= DetectedAdapter(dataList)
        binding.resultRecyclerview.layoutManager=
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        binding.resultRecyclerview.adapter=detecAdapter
   }



}