package com.example.oldcastellovers.UI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.oldcastellovers.model.Castle;

public class CastleViewModel extends ViewModel {
    private final MutableLiveData<Castle> castleLiveData = new MutableLiveData<>();

        public void setCastle(Castle newCastle) {
            castleLiveData.setValue(newCastle);
    }

    public LiveData<Castle> getCastle() {
        return castleLiveData;
    }
}
