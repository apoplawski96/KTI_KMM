package co.touchlab.kampkit

import commonAppleMain.co.touchlab.kampkit.db.Breed
import touchlab.kampkit.models.BreedRepository
import touchlab.kampkit.models.BreedViewModel
import co.touchlab.kampkit.models.CallbackViewModel
import co.touchlab.kermit.Logger

@Suppress("Unused") // Members are called from Swift
class BreedCallbackViewModel(
    breedRepository: BreedRepository,
    log: Logger
) : CallbackViewModel() {

    override val viewModel = BreedViewModel(breedRepository, log)

    val breeds = viewModel.breedState.asCallbacks()

    fun refreshBreeds() {
        viewModel.refreshBreeds()
    }

    fun updateBreedFavorite(breed: Breed) {
        viewModel.updateBreedFavorite(breed)
    }
}
