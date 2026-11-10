package com.example.douanetemporelle;

/**
 * فئة مساعدة لبناء مفاتيح putExtra.
 * القيد: كل مفتاح يجب أن يبدأ بآخر 4 أرقام من بطاقة الطالب (المصفوفة/المعرّف)
 * ولا يجوز كتابة المفاتيح مباشرة في الكود، بل تُبنى عبر دوال static تأخذ المعرّف كمعامل.
 * مثال: "1234_NOM_VOYAGEUR"
 */
public final class CleExtras {

    // آخر 4 أرقام من بطاقة الطالب لأحد أعضاء المجموعة (غيّرها برقمك الحقيقي)
    public static final String MATRICULE = "1234";

    // أسماء المفاتيح بدون البادئة (الجزء الثابت فقط)
    private static final String SUFFIXE_NOM = "NOM_VOYAGEUR";
    private static final String SUFFIXE_NAISSANCE = "ANNEE_NAISSANCE";
    private static final String SUFFIXE_DESTINATION = "ANNEE_DESTINATION";
    private static final String SUFFIXE_MOTIF = "MOTIF_VOYAGE";
    private static final String SUFFIXE_VERDICT = "VERDICT_FINAL";

    // منع إنشاء كائنات من هذه الفئة
    private CleExtras() {
    }

    // الدالة الأساسية: تبني المفتاح من المعرّف + اللاحقة
    public static String construire(String matricule, String suffixe) {
        return matricule + "_" + suffixe;
    }

    // مفتاح اسم المسافر
    public static String cleNom(String matricule) {
        return construire(matricule, SUFFIXE_NOM);
    }

    // مفتاح سنة الميلاد
    public static String cleNaissance(String matricule) {
        return construire(matricule, SUFFIXE_NAISSANCE);
    }

    // مفتاح سنة الوجهة
    public static String cleDestination(String matricule) {
        return construire(matricule, SUFFIXE_DESTINATION);
    }

    // مفتاح سبب السفر
    public static String cleMotif(String matricule) {
        return construire(matricule, SUFFIXE_MOTIF);
    }

    // مفتاح الحكم النهائي (قيمة منطقية boolean)
    public static String cleVerdict(String matricule) {
        return construire(matricule, SUFFIXE_VERDICT);
    }
}
