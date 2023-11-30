package com.example.oldcastellovers.UI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.oldcastellovers.network.dto.CastleDTO;

public class CastleViewModel extends ViewModel {
    private MutableLiveData<CastleDTO> castleLiveData = new MutableLiveData<>();

    public void setCastle(CastleDTO castleDTO) {
        castleLiveData.setValue(castleDTO);
    }

    public LiveData<CastleDTO> getCastle() {
        return castleLiveData;
    }
}
