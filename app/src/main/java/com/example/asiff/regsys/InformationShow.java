package com.example.asiff.regsys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InformationShow extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_show);
        setTitle("তথ্যমালা ");

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("১০% সামুদ্রিক ও উপকূলীয় স্থান সংরক্ষণ ");
        listDataHeader.add("প্রশিক্ষণ কার্যক্রম ");
        listDataHeader.add(" সচেতনতা বৃদ্ধি ");
        listDataHeader.add("আইন অমান্যের শাস্তি ");


        // Adding child data


        List<String> top = new ArrayList<String>();
        top.add("জাতীয় ও আন্তর্জাতিক নিয়মানুসারে , যেকোনো সামুদ্রিক এলাকা যা আইন অনুসারে পরিবেশ এবং জীব বৈচিত্র্য রক্ষার জন্যে  সংরক্ষিত , তাকে Marine Protected Area বলে । ২০২০ সালের মাঝে কঠোর আইন প্রণয়ন এবং সামগ্রিক সচেতনতার মাধ্যমে ১০% সামুদ্রিক এলাকা সংরক্ষণ করতে হবে। ");


        List<String> top250 = new ArrayList<String>();
        top250.add("প্রান্তিক পর্যায়ের মৎস্যচাষীরা  সঠিক জ্ঞান  না থাকার কারণে যথেচ্ছা মাছ ধরে থাকেন যা পরিবেশের ভারসাম্যের জন্যে ক্ষতিকর। তাদেরকে শিক্ষিত করতে হবে যেভাবে ---");
        top250.add("১) মাছ ধরার প্রাথমিক জ্ঞান  অর্জন করতে হবে । সেখত্রে অভিজ্ঞ কারোর সাহায্য অবশ্যই নিতে হবে। ");
        top250.add("২) ন্যূনতম পড়ালেখা জানতে হবে যাতে মাছের প্রকারভেদ, আকৃতি ধরার অযোগ্য কিনা সেটা পরিমাপ, বিক্রি করার সময়ে টাকা পয়সার লেনদেনে প্রতারিত হচ্ছে কিনা তা ইত্যাদি বুঝতে পারার ক্ষমতা থাকে। তাদেরকে প্রাথমিক শিক্ষা দিতে হবে! ");
        top250.add("৩) কোন সময়ে কোন মাছ ধরা যাবে, কোন মাছের পোনা ছাড়তে হবে ইত্যাদি তাদেরকে খুব ভালভাবে শেখাতে হবে! উদাহরণস্বরুপ - ১ মার্চ থেকে ৩০ এপ্রিল ইলিশ ধরা সম্পূর্ণ নিষিদ্ধ । ");
        top250.add("৪) যখন মাছ ধরা বন্ধ থাকবে তখন তারা যাতে অন্যকাজে নিযুক্ত হতে পারে সেজন্যে তাদেরকে অন্য কাজ করতে উদ্বুদ্ধ করতে হবে এবং সরকারিভাবে তদারকি করতে হবে । \n");
        top250.add("৫) সহজে যাতে ব্যাঙ্ক লোন নিতে পারে তাই তাদেরকে হিসাব নিকাশ  শিখাতে হবে।");
        top250.add("৬) কয়েকজন মিলে যাতে সামগ্রিকভাবে হ্যাচারিতে চিংড়ি, মাগুর, রুই ইত্যাদি মাছ চাষ করে সহজে সফল হতে পারে সেরকম মনোভাব সৃষ্টি করতে হবে।");
        top250.add("৭) চাষকৃত মাছের রোগবালাই সম্পরকে প্রশিক্ষণ দিতে হবে এবং প্রয়োজনীয় চিকিৎসা কিভাবে পেতে পারে তা জানাতে হবে ।");
        top250.add("৮)   আবহাওয়া বারতা এবং লোকেশন বেসড সার্ভিস কিভাবে ব্যবহার করতে হয় তা খুব ভালভাবে শিখাতে হবে । \n" +
                "\n");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("১) পানিকে এবং জলজ সম্পদ কে দূষিত করে এমন জিনিসকে এড়িয়ে যেতে হবে। ");
        nowShowing.add("২) কারেন্ট জাল ব্যবহার থেকে সম্পূর্ণ বিরত থাকতে হবে। ");
        nowShowing.add("৩ ) নির্দিষ্ট আকৃতির থেকে ক্ষুদ্র মাছ অথবা অনাকাঙ্ক্ষিত মাছ ধরলে তা ছেড়ে দেয়া ।");
        nowShowing.add("৪) সব ধরনের প্রশিক্ষণ কারযক্রমে অংশগ্রহণ করতে হবে এবং এ তথ্য সবাইকে ছড়িয়ে দিতে হবে । ");
        nowShowing.add("৫) কোন এলাকা কখন মাছ ধরার যোগ্য এবং অযোগ্য তা জেনে নিয়া মাছ ধরতে যেতে হবে। ");
        nowShowing.add("৬) আবহাওয়া বারতা এবং লোকেশন বেসড সার্ভিস এর তথ্য মেনে চলার জন্যে সতর্ক হতে হবে ।");

        List<String> law = new ArrayList<String>();
        law.add("ইলিশ আমাদের জাতীয় মাছ এবং প্রচুর পরিমাণে বৈদেশিক মুদ্রা অর্জনে সহায়ক , পাশাপাশি এটি অত্যন্ত সুস্বাদু । সুতরাং এর যথেচ্ছা আহরণ এর বংশবিস্তার কে হুমকির সম্মুখীন করায় ১ মার্চ থেকে ৩০ এপ্রিল এর মধ্যে ইলিশ ধরলে ৬ মাসের জেল এবং জরিমানার ব্যবস্থা রয়েছে । এছাড়াও যেকোনো সময়ে কারেন্ট জাল ব্যবহার শাস্তিযোগ্য অপরাধ ।  এছাড়াও আরও কিছু আইন বহাল আছে। ");



        listDataChild.put(listDataHeader.get(0), top);
        listDataChild.put(listDataHeader.get(1), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(2), nowShowing);
        listDataChild.put(listDataHeader.get(3), law);


    }



}
