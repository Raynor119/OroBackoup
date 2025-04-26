package com.pixels.orobackoup.ViewModel.TermoCupla.Ciclo;

import android.app.Activity;
import android.os.Handler;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.orobackoup.Model.DatosEncapsulados.TermoCalor;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.TermoCupla.GraficasFragment.GraficaLineaTR;
import com.pixels.orobackoup.View.TermoCupla.TermoCupla;
import com.pixels.orobackoup.ViewModel.TermoCupla.WS.GraficaTiempoRealViewModel;

import java.util.List;

public class AutoFetchViewModel extends ViewModel {
    private boolean isFetching = false;
    private Handler handler = new Handler();
    private Runnable fetchRunnable;

    public void startFetching(TermoCupla activity, String session, FragmentManager fragmentManager) {
        isFetching = true;

        fetchRunnable = new Runnable() {
            @Override
            public void run() {
                if (!isFetching) return;

                try {
                    GraficaTiempoRealViewModel MYSQLTR = ViewModelProviders.of(activity).get(GraficaTiempoRealViewModel.class);
                    MYSQLTR.reset();
                    MYSQLTR.GraficaTiempoReal(activity, session);

                    Observer<List<TermoCalor>> observer = new Observer<List<TermoCalor>>() {
                        @Override
                        public void onChanged(List<TermoCalor> termoCalors) {
                            GraficaLineaTR graficaColumna = new GraficaLineaTR(termoCalors);
                            graficaColumna.Temperaturas = termoCalors;

                            Handler uiHandler = new Handler();
                            uiHandler.postDelayed(() -> {
                                fragmentManager.beginTransaction().replace(R.id.containerF, graficaColumna).commitAllowingStateLoss();
                            }, 500);

                            MYSQLTR.getResultado().removeObserver(this);
                        }
                    };

                    MYSQLTR.getResultado().observe((LifecycleOwner) activity, observer);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                handler.postDelayed(this, 4000); // repetir cada 4 segundos
            }
        };

        handler.post(fetchRunnable); // iniciar
    }

    public void stopFetching() {
        isFetching = false;
        handler.removeCallbacks(fetchRunnable);
    }
}