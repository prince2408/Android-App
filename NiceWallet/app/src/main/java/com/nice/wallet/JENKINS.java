package com.nice.wallet;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JENKINS.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JENKINS#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JENKINS extends Fragment {

    public WebView mWebView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public JENKINS() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JENKINS.
     */
    // TODO: Rename and change types and number of parameters
    public static JENKINS newInstance(String param1, String param2) {
        JENKINS fragment = new JENKINS();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       /* // Inflate the layout for this fragment
        View fragView=inflater.inflate(R.layout.fragment_jir, container, false);

        //TODO Getting dashboard data
        String[] row = { "53452", "53452", "12312", "23213", "12144", "123456",  "124124" };
        String[] column = { "Defect Number", "P1", "P2","P3", "P4","Status","Total" };
        int rl=row.length; int cl=column.length;

        TableLayout tl = (TableLayout)fragView.findViewById(R.id.dashboardIssue);
        TableLayout tableLayout = createTableLayout(tl,row, column,rl, cl);
        return fragView;*/




        View v=inflater.inflate(R.layout.fragment_jir, container, false);
        mWebView = (WebView) v.findViewById(R.id.webview);
        //mWebView.loadUrl("http://ua-jenkins01.rwc.na.nice.com:8088/jenkins/view/NCAT/");
        mWebView.loadUrl("http://ua-jenkins01.rwc.na.nice.com:8088/jenkins/view/NCAT/");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private TableLayout createTableLayout( TableLayout tableLayout,String [] rv, String [] cv,int rowCount, int columnCount) {
        // 1) Create a tableLayout and its params
        View rootView;
        tableLayout.setBackgroundColor(Color.WHITE);

        // 2) create tableRow params
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        tableRowParams.setMargins(1, 1, 1, 1);
        tableRowParams.weight = 1;

        for (int i = 0; i < rowCount; i++) {
            // 3) create tableRow
            TableRow tableRow = new TableRow(getActivity());
            if(i%2!=0){
                tableRow.setBackgroundColor(Color.parseColor("#1F99A2"));
            }else {
                tableRow.setBackgroundColor(Color.BLACK);
            }

            tableRow.setLayoutParams(new ViewGroup.LayoutParams( tableLayoutParams.FILL_PARENT, tableLayoutParams.WRAP_CONTENT));

            for (int j= 0; j < columnCount; j++) {
                // 4) create textView
                TextView textView = new TextView(getActivity());
                //  textView.setText(String.valueOf(j));
                textView.setBackgroundColor(Color.WHITE);
                textView.setGravity(Gravity.CENTER);

                String s1 = Integer.toString(i);
                String s2 = Integer.toString(j);
                String s3 = s1 + s2;
                int id = Integer.parseInt(s3);

                if (i ==0 && j==0){
                    textView.setText("--");
                } else if(i==0){
                    textView.setText(cv[j-1]);
                }else if( j==0){
                    textView.setText(rv[i-1]);
                }else {
                    textView.setText(id+j+"");
                    // check id=23
                }

                // 5) add textView to tableRow
                tableRow.addView(textView, tableRowParams);
            }

            // 6) add tableRow to tableLayout
            tableLayout.addView(tableRow, tableLayoutParams);
        }

        return tableLayout;
    }
}

