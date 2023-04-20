## ENDPOINT 

#### 1. Retrieve a list of all surah
```
http://localhost:8080/api/v1/quran/all
```
Sampel response :
```json
{
  "code": 200,
  "status": "OK",
  "data": [
    {
      "title": "Al Fatihah",
      "path": "al-fatihah",
      "links": [
        {
          "rel": "href",
          "href": "http://localhost:8080/api/v1/quran/al-fatihah"
        }
      ]
    },
    {
      "title": "Al Baqarah",
      "path": "al-baqarah",
      "links": [
        {
          "rel": "href",
          "href": "http://localhost:8080/api/v1/quran/al-baqarah"
        }
      ]
    }
    ....
  ]
}
```

#### 2. Retrieve surah details
```
http://localhost:8080/api/v1/quran/{path}
```

Example to retrieve surah details

```
http://localhost:8080/api/v1/quran/al-ikhlas
```
Sample response
```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "title": "Surat Al Ikhlas",
    "ayat": [
      {
        "arabic": "قُلْ هُوَ اللّٰهُ اَحَدٌۚ",
        "translate": "Qul huwallāhu aḥad(un).",
        "meaning": "Katakanlah (Nabi Muhammad), “Dialah Allah Yang Maha Esa."
      },
      {
        "arabic": "اَللّٰهُ الصَّمَدُۚ",
        "translate": "Allāhuṣ-ṣamad(u).",
        "meaning": "Allah tempat meminta segala sesuatu."
      },
      {
        "arabic": "لَمْ يَلِدْ وَلَمْ يُوْلَدْۙ",
        "translate": "Lam yalid wa lam yūlad.",
        "meaning": "Dia tidak beranak dan tidak pula diperanakkan"
      },
      {
        "arabic": "وَلَمْ يَكُنْ لَّهٗ كُفُوًا اَحَدٌ ࣖ",
        "translate": "Wa lam yakul lahū kufuwan aḥad(un).",
        "meaning": "serta tidak ada sesuatu pun yang setara dengan-Nya.”"
      }
    ]
  }
}
```
