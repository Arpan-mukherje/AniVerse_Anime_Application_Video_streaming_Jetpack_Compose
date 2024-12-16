import android.net.Uri
import android.util.Log
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

    NavHost(navController = navController, startDestination = "homepage") {
        composable("homepage") {
            HomeScreen(navController = navController, )
        }
        composable(
            route = "all-episode/{id}/{url}/{index}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("index") { type = NavType.IntType },
                navArgument("url") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            val url = Uri.decode(backStackEntry.arguments?.getString("url") ?: "")
            val index = backStackEntry.arguments?.getInt("index") ?: -1

            if (id == -1 || url.isEmpty() || index == -1) {
                Log.e("NavigationError", "Invalid arguments: id=$id, url=$url, index=$index")
                navController.popBackStack()
                return@composable
            }

            AllEpisodesScreen(
                navController = navController,
                id = id,
                url = url,
                index = index,
            )
        }
    }
}
