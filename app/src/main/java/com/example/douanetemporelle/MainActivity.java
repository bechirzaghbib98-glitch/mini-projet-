package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * النشاط الأول: نموذج التسجيل.
 * يجمع اسم المسافر وسنة الميلاد وسنة الوجهة وسبب السفر،
 * ثم يطلق النشاط الثاني بواسطة Intent صريح.
 */
public class MainActivity extends AppCompatActivity {

    // عناصر الواجهة
    private EditText etNomBz;
    private EditText etNaissanceBz;
    private EditText etDestinationBz;
    private RadioGroup rgMotifBz;
    private Button btnInspecterBz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ربط العناصر بالـ id الخاص بها
        etNomBz = findViewById(R.id.et_nom_bz);
        etNaissanceBz = findViewById(R.id.et_naissance_bz);
        etDestinationBz = findViewById(R.id.et_destination_bz);
        rgMotifBz = findViewById(R.id.rg_motif_bz);
        btnInspecterBz = findViewById(R.id.btn_inspecter_bz);

        // عند الضغط على زر "فحص الملف"
        btnInspecterBz.setOnClickListener(v -> inspecterProfil());
    }

    // دالة تتحقق من المدخلات وتنتقل إلى النشاط الثاني
    private void inspecterProfil() {
        String nom = etNomBz.getText().toString().trim();
        String naissanceTxt = etNaissanceBz.getText().toString().trim();
        String destinationTxt = etDestinationBz.getText().toString().trim();

        // التحقق من أن جميع الحقول ممتلئة
        if (nom.isEmpty() || naissanceTxt.isEmpty() || destinationTxt.isEmpty()) {
            Toast.makeText(this, R.string.erreur_champs, Toast.LENGTH_SHORT).show();
            return;
        }

        int naissance = Integer.parseInt(naissanceTxt);
        int destination = Integer.parseInt(destinationTxt);

        // سنة الوجهة يجب أن تكون بعد سنة الميلاد
        if (destination <= naissance) {
            Toast.makeText(this, R.string.erreur_annees, Toast.LENGTH_SHORT).show();
            return;
        }

        // قراءة سبب السفر من الزر المختار
        String motif = getMotifChoisi();

        // إنشاء Intent صريح نحو النشاط الثاني
        Intent intent = new Intent(this, VerificationActivity.class);
        String m = CleExtras.MATRICULE;
        // المفاتيح تُبنى عبر دوال static وليست مكتوبة مباشرة
        intent.putExtra(CleExtras.cleNom(m), nom);
        intent.putExtra(CleExtras.cleNaissance(m), naissance);
        intent.putExtra(CleExtras.cleDestination(m), destination);
        intent.putExtra(CleExtras.cleMotif(m), motif);
        startActivity(intent);
    }

    // دالة تعيد نص سبب السفر المختار
    private String getMotifChoisi() {
        int id = rgMotifBz.getCheckedRadioButtonId();
        if (id == R.id.rb_modification_bz) {
            return getString(R.string.motif_modification);
        } else if (id == R.id.rb_fuite_bz) {
            return getString(R.string.motif_fuite);
        }
        // القيمة الافتراضية: سياحة
        return getString(R.string.motif_tourisme);
    }
}
