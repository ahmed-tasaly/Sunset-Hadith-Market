package com.moataz.afternoonhadeeth.data.source

import android.content.Context
import android.util.Log
import com.moataz.afternoonhadeeth.utils.helper.PrefConfigNumber

class ZekrData {
    var zekr = ""

    private val zekrList = mutableListOf(
        "سبحان اللّه وبحمده سبحان اللّه العظيم",
        "اللهم قنا عذابك يوم يبعث عبادك",
        "أعوذ بكلمات اللّه التّامّات من شرّ ما خلق",
        "لا إله إلّا اللّه وحده لا شريك له",
        "اللهم أغفر لنا وأرحمنا وعافنا",
        "رضيت بالله ربا وبالإسلام دينا",
        "لا حول ولا قوة إلا بالله",
        "رَبِّ إغفر وإرحم وتجاوز عما تعلم",
        "رَبِّ إغفر وإرحم وتجاوز عما تعلم",
        "ربنا تقبل منا إنك أنت السميع العليم",
        "ربنا آتنا في الدنيا حسنة وفي الآخرة حسنة",
        "سبحان اللّه العظيم وبحمده",
        "ربنا لا تؤاخذنا إن نسينا أو أخطأنا",
        "رب هب لي حكما والحقني بالصالحين",
        "رب أوزعني أن أشكر نعمتك التي أنعمت علي",
        "سبحان اللّه والحمدلله ولا إله إلا الله",
        "اللهم علمنا ماينفعنا وأنفعنا بما علمتنا",
        "اللهم صلِّ و سلِّم على نبينا محمد",
        "أستغفر اللّه العظيم وأتوب إليه",
        "رَبِّ اشْرَحْ لِي صَدْرِي وَيَسِّرْ لِي أَمْرِي",
        "رب إغفر لي ولوالدي",
        "اللهم إني أعوذ بك من الكفر والفقر",
        "اللهم إني أعوذ بك من عذاب القبر",
        "اللهم إني أعوذ بك من العجز والكسل",
        "اللهم إني أعوذ بك من جهد البلاء",
        "ربنا آمنا فأغفر لنا وارحمنا"
    )

    fun getNewZekr(context: Context): String {
        var chosenNumber = PrefConfigNumber.restoreChosenNumber(context)
        if (chosenNumber != zekrList.size) {
            zekr = zekrList[chosenNumber]
            chosenNumber++
            PrefConfigNumber.saveChosenNumber(context, chosenNumber)
        } else {
            zekr = zekrList[0]
            chosenNumber = 1
            PrefConfigNumber.saveChosenNumber(context, chosenNumber)
        }
        return zekr
    }
}