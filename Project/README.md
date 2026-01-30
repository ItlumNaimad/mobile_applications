# PhotoJournal - Dziennik Fotograficzny
### Projekt semestralny: Programowanie urządzeń mobilnych

![logo.png](logo.png)

**Autor:** Damian Skonieczny  
**Grupa:** Grupa 1
**Data:** 30 stycznia 2026
Skonieczny\_Damian(05\_IST\_PAM-SP5)\_gr1
---

## 1. Opis aplikacji

**PhotoJournal** to aplikacja mobilna na platformę Android, służąca do tworzenia geolokalizowanego dziennika fotograficznego. Aplikacja pozwala użytkownikowi dokumentować zdarzenia poprzez wykonywanie zdjęć, które są automatycznie opisywane współrzędnymi geograficznymi oraz znacznikiem czasu.

Projekt realizuje temat **"FotoMapa" / "Rejestrator zdarzeń"** z listy tematów projektowych. Aplikacja została zbudowana w oparciu o nowoczesny stos technologiczny (Jetpack Compose) oraz architekturę **MVVM (Model-View-ViewModel)**.

### Główne funkcjonalności:
*   **Lista wpisów:** Przeglądanie wykonanych zdjęć w formie przewijanej listy.
*   **Akwizycja danych:** Wykonywanie zdjęć przy użyciu wbudowanego aparatu.
*   **Geolokalizacja:** Automatyczne pobieranie współrzędnych GPS (szerokość i długość geograficzna) w momencie robienia zdjęcia.
*   **Trwałość danych:** Zapisywanie informacji o zdjęciach w lokalnej bazie danych oraz plików graficznych w pamięci urządzenia.
*   **Szczegóły:** Podgląd powiększonego zdjęcia wraz z metadanymi.

---

## 2. Wykorzystane sensory i technologie

Zgodnie z wymaganiami projektowymi, aplikacja wykorzystuje następujące sensory i mechanizmy systemowe:

1.  **Aparat (CameraX):**
    *   Wykorzystany do akwizycji obrazu.
    *   Implementacja: ndroidx.camera:camera-core, camera-camera2, camera-lifecycle.
    *   Uzasadnienie: Nowoczesne API zastępujące przestarzałe Camera class, zapewniające lepszą obsługę cyklu życia.

2.  **Lokalizacja GPS (Fused Location Provider):**
    *   Wykorzystany do pobrania współrzędnych (latitude, longitude) urządzenia.
    *   Implementacja: com.google.android.gms:play-services-location.
    *   Tryb: PRIORITY_HIGH_ACCURACY dla zapewnienia dokładności pomiaru.

3.  **Pamięć urządzenia (File System + Room Database):**
    *   **Room:** Przechowywanie metadanych (URI, data, współrzędne, tytuł) w strukturalnej bazie danych SQL. Wykorzystuje bibliotekę **KSP (Kotlin Symbol Processing)** do generowania kodu.
    *   **Internal Storage:** Fizyczny zapis plików .jpg w prywatnym katalogu aplikacji.

**Architektura:**
*   **UI:** Jetpack Compose (Material3).
*   **Nawigacja:** Navigation Compose (Type-safe routes).
*   **Wzorzec:** MVVM (Model-View-ViewModel) z Repozytorium.
*   **Asynchroniczność:** Kotlin Coroutines & Flow.

---

## 3. Struktura projektu

Projekt bazuje na strukturze zaleconej w instrukcji laboratoryjnej (Lab 12), rozszerzonej o warstwę danych:

`	ext
app/src/main/java/pbs/edu/project/
├── data/              # Warstwa danych (Room Database, Repository, DAO)
├── model/             # Modele danych (Entity: PhotoEntry)
├── navigation/        # Konfiguracja nawigacji (NavHost)
├── screens/           # Ekrany aplikacji (UI)
│   ├── home/          # Ekran główny (Lista)
│   ├── camera/        # Ekran aparatu (Podgląd + Akcja)
│   └── details/       # Ekran szczegółów
├── ui/theme/          # Stylizacja (Material3)
├── utils/             # Klasy pomocnicze (LocationService, FileUtils)
├── viewmodel/         # Logika biznesowa (HomeViewModel)
└── MainActivity.kt    # Punkt startowy
`

---

## 4. Zrzuty ekranu / Diagramy



![Architektura MVVM](https://developer.android.com/static/topic/libraries/architecture/images/final-architecture.png)
*Rys 1. Zastosowany wzorzec architektoniczny (źródło: Android Developers)*

---

## 5. Instrukcja uruchomienia

Aby uruchomić projekt:
1.  Otwórz projekt w **Android Studio Ladybug (lub nowszym)**.
2.  Zsynchronizuj projekt z plikami Gradle (File -> Sync Project with Gradle Files).
3.  Podłącz urządzenie fizyczne z systemem Android 10+ (API 29+) lub uruchom Emulator.
4.  Upewnij się, że urządzenie ma włączony GPS i dostęp do internetu (dla dociągnięcia bibliotek).
5.  Kliknij przycisk **Run ('app')**.
6.  Przy pierwszym uruchomieniu zezwól aplikacji na dostęp do **Aparatu** i **Lokalizacji**.

---

## 6. Napotkane problemy i wyzwania techniczne (Raport z implementacji)

Mimo starannego odwzorowania architektury i logiki biznesowej, proces deweloperski napotkał na **krytyczne problemy związane z kompatybilnością narzędzi**, które uniemożliwiły płynne wdrożenie. Poniżej opisuję "piekło zależności" (dependency hell), z którym walczyłem podczas realizacji projektu:

### Problem 1: Konflikt wersji KSP i Android Gradle Plugin (AGP)
Początkowo projekt został skonfigurowany na najnowszym **AGP 9.0.0** oraz **Kotlin 2.0.21**.
*   **Błąd:** Unresolved reference 'ksp'.
*   **Przyczyna:** Wtyczka KSP (Kotlin Symbol Processing), wymagana przez bazę danych Room, nie jest jeszcze w pełni kompatybilna z eksperymentalnym/nowym API Android Gradle Plugin 9.0.
*   **Podjęta akcja:** Próba degradacji (downgrade) AGP do wersji **8.8.0**, która jest uznawana za stabilną.

### Problem 2: Konflikt bibliotek AndroidX z AGP 8.8.0
Po obniżeniu wersji AGP do 8.8.0, najnowsze wersje bibliotek ndroidx.core:core-ktx:1.17.0 oraz ndroidx.activity:activity-compose:1.12.2 przestały działać.
*   **Błąd:** Dependency 'androidx.core:core-ktx:1.17.0' requires Android Gradle plugin 8.9.1 or higher.
*   **Analiza:** Najnowsze biblioteki Jetpack "wybiegają w przyszłość" i wymuszają użycie wersji Gradle, która z kolei psuje kompilator KSP. Powstało błędne koło zależności.
*   **Podjęta akcja:** Ręczne, iteracyjne obniżanie wersji wszystkich kluczowych bibliotek w libs.versions.toml do starszych wydań (np. Core KTX 1.15.0), aby dopasować się do AGP 8.8.0.

### Problem 3: Ostrzeżenie o "16 KB Page Size" (Android 15)
Po rozwiązaniu problemów z zależnościami, kompilator zaczął blokować uruchomienie ze względu na nowe wymogi Androida 15 (API 36 - Baklava).
*   **Błąd:** Wymóg wsparcia dla 16 KB page sizes dla urządzeń targetujących Android 15+.
*   **Podjęta akcja:**
    1.  Dodanie flagi ndroid.bundle.enableUncompressedNativeLibs=true w gradle.properties.
    2.  Zmiana konfiguracji packagingOptions w uild.gradle.kts.
    3.  Ostatecznie: Zmiana compileSdk z 36 na 35 (Android 14/15 Stable), aby uniknąć problemów z wersjami Preview.

### Podsumowanie statusu
Aplikacja jest kompletna pod względem kodu (logika, UI, sensory), jednak jej uruchomienie jest uzależnione od specyficznej konfiguracji środowiska Android Studio, które w momencie pisania tego raportu przechodzi duże zmiany w systemie budowania (przejście na AGP 9.x), co powoduje liczne niestabilności w projektach studenckich wykorzystujących KSP i Compose.

---

## 7. Źródła i Bibliografia

Podczas realizacji projektu korzystano z następujących materiałów:

1.  **Materiały wykładowe:**
    *   *Lab (Nr_12) Temat: Aplikacja Kotlin cz 4* - Politechnika Bydgoska (@dokumentcje/Lab12.pdf).
    *   *Projekt semestralny: Aplikacja mobilna z akwizycją danych* (@dokumentcje/Projekt_mobilny.pdf).
2.  **Dokumentacja Android Developers:**
    *   [Guide to App Architecture](https://developer.android.com/topic/architecture) - wzorzec MVVM i repozytoria.
    *   [CameraX Overview](https://developer.android.com/media/camera/camerax) - implementacja aparatu.
    *   [Save data in a local database using Room](https://developer.android.com/training/data-storage/room) - obsługa bazy danych.
3.  **Rozwiązywanie problemów (StackOverflow / Google Issue Tracker):**
    *   Kwestie kompatybilności KSP z AGP: [Google Issue Tracker - KSP](https://github.com/google/ksp/issues).
    *   Problemy z 16 KB Page Alignment: [Android 15 Support Guide](https://developer.android.com/guide/practices/page-sizes).