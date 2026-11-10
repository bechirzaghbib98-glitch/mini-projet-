# Douane Temporelle (Time Travel Customs)

Mini-projet DS1 — Programmation Mobile, 3ème BI, FSEGT (Android / Java).

## Structure

```
DouaneTemporelle/
├── settings.gradle / build.gradle / gradle.properties
└── app/
    ├── build.gradle
    └── src/main/
        ├── AndroidManifest.xml
        ├── java/com/example/douanetemporelle/
        │   ├── CleExtras.java            # clés putExtra préfixées (méthodes statiques)
        │   ├── MainActivity.java         # Activité 1 : formulaire
        │   ├── VerificationActivity.java # Activité 2 : scanner de paradoxe
        │   └── ResultActivity.java       # Activité 3 : verdict
        └── res/
            ├── layout/ activity_main.xml, activity_verification.xml, activity_result.xml
            ├── drawable/ ic_horloge.xml
            └── values/ strings.xml, colors.xml, themes.xml
```

## Flux

MainActivity --(Intent explicite : nom, naissance, destination, motif)--> VerificationActivity
--(Intent explicite : verdict boolean + nom)--> ResultActivity --(Nouveau voyageur)--> MainActivity

## Contraintes

| Contrainte | Où |
|---|---|
| LinearLayout horizontal racine, image 0.3 + RelativeLayout 0.7 | `activity_verification.xml` |
| Clés putExtra préfixées par les 4 derniers chiffres, via méthode statique | `CleExtras.java` (modifier `MATRICULE`) |
| Bouton « Approuver » désactivé si « Modification du passé » et (destination − naissance) premier | `VerificationActivity.java` |
| IDs avec initiales (`_bz`) | tous les layouts |
| Commentaires en arabe | tous les fichiers Java/XML |
| Retour à MainActivity en fermant les activités | `ResultActivity.java` (CLEAR_TASK) |

## À personnaliser avant le rendu

1. `CleExtras.MATRICULE` : mettre les 4 derniers chiffres de votre carte étudiant.
2. Initiales `bz` dans les IDs : remplacer par celles d'un membre du groupe si besoin.
3. Faire au moins autant de commits que de jours alloués.
