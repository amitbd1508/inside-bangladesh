package com.bangladesh.tourism.ui.fragment;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class QrActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    private QRCodeReaderView mydecoderview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);


        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);
        mydecoderview.getCameraManager().startPreview();
    }

    @Override
    public void onQRCodeRead(String s, PointF[] pointFs) {
        Toast.makeText(QrActivity.this, s, Toast.LENGTH_SHORT).show();

        /*Intent result = new Intent(getBaseContext(), VerifyResult.class);
        result.putExtra("link", s.substring(s.length() - 8, s.length()));
        result.putExtra("Status", "true");
        startActivity(result);*/

    }

    @Override
    public void cameraNotFound() {

    }

    @Override
    public void QRCodeNotFoundOnCamImage() {
    }

    protected void onResume() {
        super.onResume();
        mydecoderview.getCameraManager().startPreview();
    }

    protected void onPause() {
        super.onPause();
        mydecoderview.getCameraManager().stopPreview();
    }
}

