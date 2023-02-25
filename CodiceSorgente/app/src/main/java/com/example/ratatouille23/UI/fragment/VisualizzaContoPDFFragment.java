package com.example.ratatouille23.UI.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ratatouille23.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VisualizzaContoPDFFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VisualizzaContoPDFFragment extends Fragment {

    private View inflatedView;

    private ImageView contoPDFImageView;

    private String result;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VisualizzaContoPDFFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VisualizzaContoPDFFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VisualizzaContoPDFFragment newInstance(String param1, String param2) {
        VisualizzaContoPDFFragment fragment = new VisualizzaContoPDFFragment();
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

//        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
//                result = bundle.getString("bundleKey");
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_visualizza_conto_pdf, container, false);

        contoPDFImageView = inflatedView.findViewById(R.id.contoPDFImageView);

        creaPDF(savedInstanceState);

        return inflatedView;
    }

    void creaPDF(Bundle savedInstanceState) {

        //Crea il layout del documento

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int pageWidth = displayMetrics.widthPixels;
        int pageHeight = displayMetrics.heightPixels;

        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        //Crea il testo nel documento

        Paint textPaint = new Paint();
        Canvas canvas = myPage.getCanvas();

        //centro orizzontale
        int xPos = (canvas.getWidth() / 2);
        //centro verticale
        int yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));

        textPaint.setTextSize(100);
        textPaint.setTextAlign(Paint.Align.CENTER);

        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Ratatouille23", xPos, 100, textPaint);

        textPaint.setTextAlign(Paint.Align.LEFT);

        textPaint.setTextSize(65);
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Numero commensali:", 50, 300, textPaint);


        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        canvas.drawText(getArguments().getString("numeroCommensali"), 50, 400, textPaint);

        pdfDocument.finishPage(myPage);

        //Crea la cartella "Conti" nei documenti della memoria esterna e il file "Conto.pdf"
        //Device File Explorer/storage/emulated/0/Android/data/com.example.ratatouille23/files/Documents/Conti/Conto.pdf

        File[] externalStorageVolumes =
                ContextCompat.getExternalFilesDirs(getContext(), null);
        File primaryExternalStorage = externalStorageVolumes[0];

        File contiDirectory = new File(primaryExternalStorage.getAbsolutePath() + File.separator + "Documents", "Conti");

        if (contiDirectory.exists()) {
            File file = new File(contiDirectory, "Conto.pdf");
            try {
                if (!file.exists()) file.createNewFile();
                pdfDocument.writeTo(new FileOutputStream(file));
                Toast.makeText(getActivity(), "File PDF creato con successo", Toast.LENGTH_SHORT).show();

                PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));
                PdfRenderer.Page page = renderer.openPage(0);
                Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                contoPDFImageView.setImageBitmap(bitmap);

                page.close();
                renderer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (contiDirectory == null || !contiDirectory.mkdirs()) {
            Log.e("ContiFragment", "Directory not created");
        }

        pdfDocument.close();

    }

}