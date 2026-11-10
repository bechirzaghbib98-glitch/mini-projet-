package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * النشاط الثاني: ماسح المفارقة.
 * يعرض ملخص بيانات المسافر على شكل جواز سفر،
 * ويرسل الحكم النهائي (boolean) مع اسم المسافر إلى النشاط الثالث.
 */
public class VerificationActivity extends AppCompatActivity {

    private TextView tvNomBz;
    private TextView tvAgeBz;
    private TextView tvTrajetBz;
    private TextView tvMotifBz;
    private TextView tvAlerteBz;
    private Button btnApprouverBz;
    private Button btnRefuserBz;

    // البيانات المستلمة
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        tvNomBz = findViewById(R.id.tv_nom_bz);
        tvAgeBz = findViewById(R.id.tv_age_bz);
        tvTrajetBz = findViewById(R.id.tv_trajet_bz);
        tvMotifBz = findViewById(R.id.tv_motif_bz);
        tvAlerteBz = findViewById(R.id.tv_alerte_bz);
        btnApprouverBz = findViewById(R.id.btn_approuver_bz);
        btnRefuserBz = findViewById(R.id.btn_refuser_bz);

        // قراءة البيانات القادمة من النشاط الأول بنفس المفاتيح المبنية عبر الدوال static
        String m = CleExtras.MATRICULE;
        Intent recu = getIntent();
        nom = recu.getStringExtra(CleExtras.cleNom(m));
        int naissance = recu.getIntExtra(CleExtras.cleNaissance(m), 0);
        int destination = recu.getIntExtra(CleExtras.cleDestination(m), 0);
        String motif = recu.getStringExtra(CleExtras.cleMotif(m));

        // حساب العمر الزمني = الفرق بين سنة الوجهة وسنة الميلاد
        int ageTemporel = destination - naissance;

        // عرض الملخص
        tvNomBz.setText(getString(R.string.label_nom) + " " + nom);
        tvAgeBz.setText(getString(R.string.label_age) + " " + ageTemporel + " ans");
        tvTrajetBz.setText(getString(R.string.label_trajet) + " " + naissance + " → " + destination);
        tvMotifBz.setText(getString(R.string.label_motif_court) + " " + motif);

        // القيد: إذا كان السبب "تعديل الماضي" والفرق عدد أولي، يُعطَّل زر الموافقة عند التحميل
        boolean modification = getString(R.string.motif_modification).equals(motif);
        if (modification && estPremier(ageTemporel)) {
            btnApprouverBz.setEnabled(false);
            tvAlerteBz.setVisibility(View.VISIBLE);
        }

        // الموافقة: الحكم true
        btnApprouverBz.setOnClickListener(v -> envoyerVerdict(true));
        // الرفض: الحكم false
        btnRefuserBz.setOnClickListener(v -> envoyerVerdict(false));
    }

    // إطلاق النشاط الثالث مع الحكم واسم المسافر
    private void envoyerVerdict(boolean approuve) {
        String m = CleExtras.MATRICULE;
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(CleExtras.cleVerdict(m), approuve);
        intent.putExtra(CleExtras.cleNom(m), nom);
        startActivity(intent);
    }

    // دالة اختبار العدد الأولي
    static boolean estPremier(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
