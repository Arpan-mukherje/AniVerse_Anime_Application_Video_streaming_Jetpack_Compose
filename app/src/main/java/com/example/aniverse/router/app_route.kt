import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aniverse.model.AnimeAllEpisodeModel
import com.example.aniverse.screens.auth.SplashScreen
import com.example.aniverse.screens.episodes.AllEpisodesScreen
import com.example.aniverse.screens.home.HomeScreen
import com.example.aniverse.viewmodel.AnimeViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val topAnimeViewModel: AnimeViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = "homepage") {
        // composable("splash_screen") { SplashScreen(navController = navController) }
        composable("homepage") { HomeScreen(navController = navController) }
        composable(
            route = "all-episode/{id}/{url}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("url") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            val url = backStackEntry.arguments?.getString("url")?.let { Uri.decode(it) } ?: ""

            AllEpisodesScreen(navController = navController, id = id, url = url)
        }


    }
}
