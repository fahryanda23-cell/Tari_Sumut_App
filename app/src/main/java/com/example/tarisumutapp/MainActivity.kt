package com.example.tarisumutapp

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.tarisumutapp.data.model.Tari
import com.example.tarisumutapp.ui.screens.BelajarTariScreen
import com.example.tarisumutapp.ui.screens.DashboardScreen
import com.example.tarisumutapp.ui.screens.DetailScreen
import com.example.tarisumutapp.ui.screens.HomeScreen
import com.example.tarisumutapp.ui.screens.KuisScreen
import com.example.tarisumutapp.ui.screens.MenuUtamaScreen
import com.example.tarisumutapp.ui.screens.PlayerScreen
import com.example.tarisumutapp.ui.screens.VideoScreen

enum class ScreenState {
    MENU_UTAMA,
    DASHBOARD,
    MATERI_LIST,
    DETAIL_TARI,
    KUIS,
    BELAJAR_TARI,
    VIDEO_TARI,
    PLAYER_VIDEO
}

class MainActivity : ComponentActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // FIX STATUS BAR PINK
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT

        super.onCreate(savedInstanceState)

        // 🎵 Inisialisasi Backsound Global di Level Activity
        mediaPlayer = MediaPlayer.create(this, R.raw.backsound).apply {
            isLooping = true
            start()
        }

        val dummyListTari = listOf(
            Tari(
                id = "1",
                nama = "Tari Tor-Tor",
                etnis = "Batak Toba",
                deskripsi = "Tari seremonial khas Batak yang disajikan dengan musik gondang.",
                sejarah = """
            Penjelasan & Asal-Usul:
            Tari Tor-Tor berasal dari dataran tinggi kawasan Danau Toba dan telah ada sejak zaman animisme Batak kuno. Secara historis, tarian ini diciptakan bukan sekadar sebagai hiburan, melainkan sebagai sarana komunikasi ritual antara manusia dengan Debata Mulajadi Na Bolon (Tuhan Yang Maha Esa) serta para roh leluhur (Sandiang Matua). Nama "Tor-Tor" berasal dari bunyi hentakan kaki para penari (tor... tor...) di atas papan lantai rumah adat Batak (Ruma Bolon). Tarian ini berfungsi dalam upacara sakral (kematian, penyembuhan) hingga acara adat pergaulan (Pesta Tapuran).

            Keunikan Tari:
            Struktur gerakannya bertumpu pada koordinasi Manortor (gerakan tangan mengayun secara ritmis) dan Siut-siut (hentakan telapak kaki dan tekukan lutut yang memantul). Penari juga memperagakan gerakan Somba (penghormatan) sebagai simbol penyembahan dan penghormatan hierarki adat.

            Keunikan Busana:
            Setiap penari wajib mengenakan kain tenun Ulos (seperti Ulos Ragidup, Ulos Jugia, atau Ulos Sadum) yang memiliki makna simbolis perlindungan dan restu. Laki-laki mengenakan ikat kepala Tali-tali, sedangkan perempuan mengenakan mahkota kain Sortali yang dihiasi lempengan logam keemasan.
        """.trimIndent(),
                imageResId = R.drawable.card_tortor,
                videoResId = R.raw.tortor,
                audioUrl = ""
            ),
            Tari(
                id = "2",
                nama = "Tari Serampang Duabelas",
                etnis = "Melayu Deli",
                deskripsi = "Tari tradisional Melayu yang menceritakan tentang tahap pencarian jodoh.",
                sejarah = """
            Penjelasan & Asal-Usul:
            Tarian ini diciptakan oleh Guru Sauti pada tahun 1940-an di Serdang Bedagai (wilayah budaya Melayu Deli). Awalnya tarian ini bernama Tari Pulau Sari, lalu disempurnakan menjadi Tari Serampang Duabelas karena memiliki 12 ragam gerakan mendasar. Tarian ini lahir dari dinamika sosial masyarakat Melayu sebagai media pendidikan moral bagi pemuda-pemudi dalam memahami tata cara mencari pasangan hidup yang santun dan beradab sesuai dengan norma adat Melayu Deli.

            Keunikan Tari:
            Memiliki alur cerita (narrative dance) yang terbagi sistematis dalam 12 tahap gerak berurutan, mulai dari Tari Permulaan (perkenalan), Tari Pusing (mengingat kekasih), hingga Tari Sapu Tangan (simbol persatuan dan komitmen nikah). Gerakannya memadukan langkah kaki cepat khas Melayu dan variasi pemutaran badan.

            Keunikan Busana:
            Penari pria menggunakan Baju Teluk Belanga lengkap dengan Cekak Musang dan kain Songket pendek (Samping) setinggi lutut. Penari wanita mengenakan Baju Kurung atau Baju Kebaya Laboh dari kain sutra/bludru dengan pasangan kain Songket berkilau, memancarkan estetika keagungan Melayu.
        """.trimIndent(),
                imageResId = R.drawable.card_serampang,
                videoResId = R.raw.tortor,
                audioUrl = ""
            ),
            Tari(
                id = "3",
                nama = "Tari Landek / Piso Surit",
                etnis = "Karo",
                deskripsi = "Tarian khas Karo yang menggambarkan kesetiaan dan rindu mendalam.",
                sejarah = """
            Penjelasan & Asal-Usul:
            Tari Landek adalah sebutan umum untuk tarian suku Karo, yang sering dibawakan bersama lagu Piso Surit ciptaan Djaga Depari. Piso Surit sebenarnya adalah nama sejenis burung kicau yang sering berkicau syahdu di dataran tinggi Karo. Menurut cerita rakyat, tarian ini lahir dari perumpamaan kesetiaan dan rasa rindu mendalam seorang gadis atau pemuda Karo yang menantikan kedatangan kekasihnya di ladang atau desa.

            Keunikan Tari:
            Ciri khas utamanya berada pada dinamika Ngerendek (gerakan menggoyangkan badan secara perlahan) dan Mendidang (gerakan telapak tangan diputar, dibuka, dan ditutup secara halus menyerupai gerakan kepakan sayap burung). Kecepatan gerakannya konstan dan sangat mengutamakan keluwesan bahu.

            Keunikan Busana:
            Penari mengenakan Uwis Karo (kain tenun khas Karo berwarna dominan merah kehitaman dan gelap) serta memakai penutup kepala khas yang berstruktur besar dan kokoh yang disebut Tudung Karo untuk wanita dan Kampuh/Uwis Gendang untuk pria.
        """.trimIndent(),
                imageResId = R.drawable.card_landek,
                videoResId = R.raw.tortor,
                audioUrl = ""
            ),
            Tari(
                id = "4",
                nama = "Tari Toping-Toping / Hudoq",
                etnis = "Simalungun",
                deskripsi = "Tarian adat Simalungun yang menggunakan topeng kayu khas.",
                sejarah = """
            Penjelasan & Asal-Usul:
            Berasal dari tradisi kerajaan Simalungun kuno. Secara historis, Tari Toping-Toping (sering juga disebut Tari Hudoq Simalungun) diciptakan sebagai sarana hiburan khusus untuk menghibur keluarga raja (Kharajaan Nagur/Simalungun) yang sedang dalam suasana duka atau ketika raja kehilangan anggota keluarganya. Seiring berjalannya waktu, fungsi tarian ini bergeser menjadi sarana penyambutan tamu-tamu kehormatan daerah.

            Keunikan Tari:
            Seni pertunjukan ini terbagi menjadi tiga karakter penari: penari pria (Toping-Toping dalahi), penari wanita (Toping-Toping daboru), dan penari yang mengelilingi dengan topeng raksasa (Garama). Gerakan kakinya cenderung tegap dan menghentak secara simetris.

            Keunikan Busana:
            Atribut paling mencolok adalah penggunaan Topeng Kayu (Toping-Toping) yang diukir membentuk wajah manusia atau hantu mitologis. Penari juga mengenakan kain tenun Hiou Simalungun (seperti Hiou Hatirangsang) dan hiasan kepala pria yang disebut Gotong.
        """.trimIndent(),
                imageResId = R.drawable.card_toping,
                videoResId = R.raw.tortor,
                audioUrl = ""
            ),
            Tari(
                id = "5",
                nama = "Tari Endeng-Endeng",
                etnis = "Mandailing",
                deskripsi = "Tarian dinamis perpaduan tor-tor, pencak silat, dan tradisi pesisir.",
                sejarah = """
            Penjelasan & Asal-Usul:
            Tari Endeng-Endeng berkembang di wilayah Kabupaten Mandailing Natal dan Tapanuli Selatan. Tarian ini merupakan hasil akulturasi antara kesenian tradisi lokal Mandailing (tor-tor) dengan seni pertunjukan pesisir dan pengaruh Islam. Awalnya tarian ini dibawakan sebagai ungkapan kegembiraan masyarakat seusai menyelesaikan panen raya padi (Pesta Oloan) atau acara Marpotang Cukur (syukuran kelahiran bayi).

            Keunikan Tari:
            Memiliki karakter gerak yang sangat dinamis dan mengandalkan ketangkasan fisik karena mengadopsi gerakan tangkapan, elakan, dan pasang dari Pencak Silat Tradisional Mandailing. Irama tarian diiringi oleh tempo musik yang cepat dan lagu-lagu ber lirik pantun jenaka.

            Keunikan Busana:
            Penari mengenakan Baju Godang bertabur benang emas untuk pria dan Baju Kurung Mandailing untuk wanita. Atribut utamanya adalah Ampang/Batu Zunzun, yaitu mahkota atau penutup kepala berstruktur tinggi dengan hiasan ornamen khas Mandailing.
        """.trimIndent(),
                imageResId = R.drawable.card_endeng,
                videoResId = R.raw.tortor,
                audioUrl = ""
            ),
            Tari(
                id = "6",
                nama = "Tari Tatak Garo-Garo",
                etnis = "Pakpak",
                deskripsi = "Tarian agraris menggambarkan keceriaan gotong royong muda-mudi Pakpak.",
                sejarah = """
            Penjelasan & Asal-Usul:
            Tatak Garo-Garo lahir dari kebiasaan agraris masyarakat suku Pakpak di Dairi dan Pakpak Bharat. Istilah Tatak berarti tari, sedangkan Garo-Garo merujuk pada suasana riang dan riuh. Tarian ini diciptakan untuk menggambarkan tradisi Merdang Merdem (kerja sama atau gotong royong muda-mudi di ladang), mulai dari membuka lahan, menanam benih, hingga merayakan hasil panen.

            Keunikan Tari:
            Tarian ini tergolong ke dalam tari koreografi pantomimik karena setiap gerakannya merepresentasikan secara harafiah aktivitas pertanian Pakpak, seperti gerakan menabur benih, mengusir burung (menggero), memetik padi, dan menumbuk lesung secara bergantian.

            Keunikan Busana:
            Dominan berwarna hitam yang merupakan warna kebesaran adat Pakpak. Penari mengenakan busana dari kain Oles (seperti Oles Saji atau Oles Kelang) yang dihiasi dengan manik-manik (Merjan) dan lempengan logam perak pada bagian dada serta hiasan kepala Borgot.
        """.trimIndent(),
                imageResId = R.drawable.card_tatak,
                videoResId = R.raw.tortor,
                audioUrl = ""
            ),
            Tari(
                id = "7",
                nama = "Tari Moyo / Tari Elang",
                etnis = "Nias",
                deskripsi = "Tarian sakral penyambutan tamu dengan kepakan tangan mirip burung elang.",
                sejarah = """
            Penjelasan & Asal-Usul:
            Tari Moyo berasal dari Kepulauan Nias dan telah ada sejak era masyarakat megalitikum Nias. Kata Moyo dalam bahasa Nias berarti "Burung Elang". Dalam mitologi kepercayaan Nias kuno (Fanahorö), burung elang dianggap sebagai binatang suci yang melambangkan kekuatan, keberanian, dan penghubung antara bumi dan langit. Tarian ini diciptakan khusus dibawakan oleh para putri atau wanita Nias dalam penyambutan tamu agung (Tuhenö/Raja).

            Keunikan Tari:
            Seluruh gerakan tangan penari membentang lebar ke samping lalu diayunkan naik-turun secara perlahan (membumbung) dengan ujung jemari yang meliuk-liuk secara halus, sangat mirip dengan kepakan sayap elang yang sedang melayang di udara tanpa mengepakkan sayap secara kasar.

            Keunikan Busana:
            Penari memakai busana adat Nias yang disebut Baru Orobaba dengan warna cerah mencolok (kuning emas, merah, dan hitam). Kepala penari dihiasi oleh mahkota logam keemasan yang tinggi dan berukir rumit yang dinamakan Baru Ladari atau Tugala.
        """.trimIndent(),
                imageResId = R.drawable.card_moyo,
                videoResId = R.raw.tortor,
                audioUrl = ""
            ),
            Tari(
                id = "8",
                nama = "Tari Saputangan",
                etnis = "Pesisir / Sibolga",
                deskripsi = "Tari pergaulan muda-mudi pesisir yang lincah memainkan sapu tangan.",
                sejarah = """
            Penjelasan & Asal-Usul:
            Tari Saputangan berkembang di wilayah pesisir pantai barat Sumatera Utara, khususnya Kota Sibolga dan Kabupaten Tapanuli Tengah. Tarian ini merupakan produk kultural dari masyarakat Pesisir yang terbentuk dari percampuran (akulturasi) antara suku Melayu Pesisir, Minangkabau, dan Batak. Tarian ini tumbuh sebagai tari pergaulan muda-mudi pantai saat menyambut nelayan pulang atau perayaan pesta pantai (Manggurebe Sumbal).

            Keunikan Tari:
            Fokus keunikan terletak pada kelincahan pergelangan tangan dalam memainkan properti Sapu Tangan berbentuk persegi berwarna-warni. Gerakan kaki penari sangat lincah mengikuti tempo Musik Sikambang yang cepat dengan pola langkah menyilang dan melompat kecil.

            Keunikan Busana:
            Penari wanita mengenakan Baju Kurung Encim Pesisir atau Baju Kebaya Renda berpola warna-warna terang (merah muda, hijau muda, biru) dari kain satin/sutra, dipadukan dengan kain sarung motif Pesisiran serta perhiasan pin/bros keemasan di dada.
        """.trimIndent(),
                imageResId = R.drawable.card_saputangan,
                videoResId = R.raw.tortor,
                audioUrl = ""
            )
        )

        setContent {
            MaterialTheme {
                var currentScreen by remember { mutableStateOf(ScreenState.MENU_UTAMA) }
                var selectedTari by remember { mutableStateOf<Tari?>(null) }

                // 🔊 State Audio Global
                var isMuted by remember { mutableStateOf(false) }

                // Fungsi Callback Toggle Audio
                val onToggleAudio: () -> Unit = {
                    isMuted = !isMuted
                    if (isMuted) {
                        if (mediaPlayer.isPlaying) mediaPlayer.pause()
                    } else {
                        mediaPlayer.start()
                    }
                }

                when (currentScreen) {
                    ScreenState.MENU_UTAMA -> MenuUtamaScreen(
                        isMuted = isMuted,
                        onToggleAudio = onToggleAudio,
                        onPlayClick = { currentScreen = ScreenState.DASHBOARD },
                        onSettingsClick = { Toast.makeText(this, "Menu Pengaturan", Toast.LENGTH_SHORT).show() },
                        onAboutClick = { Toast.makeText(this, "Aplikasi oleh: Nama Kamu", Toast.LENGTH_SHORT).show() }
                    )

                    ScreenState.DASHBOARD -> DashboardScreen(
                        onMateriClick = { currentScreen = ScreenState.MATERI_LIST },
                        onKuisClick = { currentScreen = ScreenState.KUIS },
                        onBelajarTariClick = { currentScreen = ScreenState.BELAJAR_TARI },
                        onVideoClick = { currentScreen = ScreenState.VIDEO_TARI },
                        onBackClick = { currentScreen = ScreenState.MENU_UTAMA }
                    )

                    ScreenState.MATERI_LIST -> HomeScreen(
                        listTari = dummyListTari,
                        onTariClick = { tari ->
                            selectedTari = tari
                            currentScreen = ScreenState.DETAIL_TARI
                        },
                        onBackClick = {
                            currentScreen = ScreenState.DASHBOARD
                        }
                    )

                    // 🎬 DETAIL TARI: Pause backsound saat masuk ke halaman detail
                    ScreenState.DETAIL_TARI -> {
                        DisposableEffect(Unit) {
                            if (mediaPlayer.isPlaying) {
                                mediaPlayer.pause()
                            }
                            onDispose {
                                if (!isMuted && !mediaPlayer.isPlaying) {
                                    mediaPlayer.start()
                                }
                            }
                        }

                        selectedTari?.let { tari ->
                            DetailScreen(
                                tari = tari,
                                onBackClick = { currentScreen = ScreenState.MATERI_LIST }
                            )
                        }
                    }

                    ScreenState.VIDEO_TARI -> VideoScreen(
                        listTari = dummyListTari,
                        onTariClick = { tari ->
                            selectedTari = tari
                            currentScreen = ScreenState.PLAYER_VIDEO
                        },
                        onBackClick = { currentScreen = ScreenState.DASHBOARD }
                    )

                    // 🎬 PEMUTAR VIDEO: Pause backsound saat masuk ke player video
                    ScreenState.PLAYER_VIDEO -> {
                        DisposableEffect(Unit) {
                            if (mediaPlayer.isPlaying) {
                                mediaPlayer.pause()
                            }
                            onDispose {
                                if (!isMuted && !mediaPlayer.isPlaying) {
                                    mediaPlayer.start()
                                }
                            }
                        }

                        selectedTari?.let { tari ->
                            PlayerScreen(
                                tari = tari,
                                onBackClick = { currentScreen = ScreenState.VIDEO_TARI }
                            )
                        }
                    }

                    ScreenState.KUIS -> KuisScreen(
                        onBackClick = { currentScreen = ScreenState.DASHBOARD }
                    )

                    ScreenState.BELAJAR_TARI -> BelajarTariScreen(
                        onBackClick = { currentScreen = ScreenState.DASHBOARD }
                    )
                }            }
        }
    }

    // 🛑 Matikan MediaPlayer saat aplikasi benar-benar ditutup
    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    // ⏸️ Pause musik sementara jika aplikasi di-minimize
    override fun onPause() {
        super.onPause()
        if (::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    // ▶️ Lanjutkan musik saat aplikasi dibuka kembali
    override fun onResume() {
        super.onResume()
        if (::mediaPlayer.isInitialized && !mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }
}