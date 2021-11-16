package com.example.tuquechua.ui.libros;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuquechua.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibrosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibrosFragment extends Fragment {
    ImageView iv1, iv2,iv3,iv4;
    TextView tv1,tv2,tv3,tv4;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LibrosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LibrosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LibrosFragment newInstance(String param1, String param2) {
        LibrosFragment fragment = new LibrosFragment();
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
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_libros, container, false);
        iv1= vista.findViewById(R.id.iv1);
        iv2= vista.findViewById(R.id.iv2);
        iv3= vista.findViewById(R.id.iv3);
        iv4= vista.findViewById(R.id.iv4);
        tv1=vista.findViewById(R.id.tv1);
        tv2=vista.findViewById(R.id.tv2);
        tv3=vista.findViewById(R.id.tv3);
        tv4=vista.findViewById(R.id.tv4);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://drive.google.com/file/d/1xf9KLXegxP5ojZvElUc4ROhDDnRUHLZl/view?usp=sharing");
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://drive.google.com/file/d/1TmFKXMBWYWYCW2Cgs1hGDNTGbBVaC7lh/view?usp=sharing");
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://drive.google.com/file/d/1enqTyTGSzGX8hbKky0Os1hg5gRP8g34N/view?usp=sharing");
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://drive.google.com/file/d/1xf9KLXegxP5ojZvElUc4ROhDDnRUHLZl/view?usp=sharing");
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://drive.google.com/file/d/1TmFKXMBWYWYCW2Cgs1hGDNTGbBVaC7lh/view?usp=sharing");
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://drive.google.com/file/d/1enqTyTGSzGX8hbKky0Os1hg5gRP8g34N/view?usp=sharing");
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return vista;
    }

    public void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));


    }
}