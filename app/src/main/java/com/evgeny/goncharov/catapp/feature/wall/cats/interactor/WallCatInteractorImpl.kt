package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.IWallCatRepository
import javax.inject.Inject

class WallCatInteractorImpl @Inject constructor(
    private val repository: IWallCatRepository,
    private val liveDataUiEvents: LiveData<BaseEventsUi>
) : IWallCatInteractor {


}