package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * النشاط الثالث: الحكم النهائي.
 * الخلفية خضراء عند الموافقة وحمراء عند الرفض،
 * مع رسالة مخصصة باسم المسافر وزر للعودة إلى النشاط الأول.
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        View fond = findViewById(R.id.ll_fond_bz);
        TextView tvMessageBz = findViewById(R.id.tv_message_bz);
        Button btnNouveauBz = findViewById(R.id.btn_nouveau_bz);

        // قراءة الحكم والاسم بالمفاتيح المبنية عبر الدوال static
        String m = CleExtras.MATRICULE;
        boolean approuve = getIntent().getBooleanExtra(CleExtras.cleVerdict(m), false);
        String nom = getIntent().getStringExtra(CleExtras.cleNom(m));

        if (approuve) {
            // لون أخضر + رسالة ترحيب
            fond.setBackgroundColor(ContextCompat.getColor(this, R.color.vert_approuve));
            tvMessageBz.setText("Bon voyage " + nom + " !");
        } else {
            // لون أحمر + رسالة اعتقال زمني
            fond.setBackgroundColor(ContextCompat.getColor(this, R.color.rouge_refuse));
            tvMessageBz.setText(nom + " est en état d'arrestation temporelle");
        }

        // العودة إلى النشاط الأول مع إغلاق كل الأنشطة السابقة
        btnNouveauBz.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
