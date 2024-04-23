package com.example.solo_leveling_api.Service;

import com.example.solo_leveling_api.Model.Mision;
import com.example.solo_leveling_api.Model.Dificultad;
import com.example.solo_leveling_api.Model.UsuarioMision;
import com.example.solo_leveling_api.Repository.MisionRepository;
import com.example.solo_leveling_api.Repository.UsuarioMisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MisionService {

    private final MisionRepository misionRepository;
    private final UsuarioMisionRepository usuarioMisionRepository;

    @Autowired
    public MisionService(MisionRepository misionRepository, UsuarioMisionRepository usuarioMisionRepository) {
        this.misionRepository = misionRepository;
        this.usuarioMisionRepository = usuarioMisionRepository;
    }

    public List<Mision> getMisionesDiariasParaUsuario(String email) {
        Date startOfDay = getStartOfDay();
        Date endOfDay = getEndOfDay();
        List<UsuarioMision> usuarioMisiones = usuarioMisionRepository.findMisionesByUsuarioEmailAndFecha(email, startOfDay, endOfDay);
        if (!usuarioMisiones.isEmpty()) {
            return usuarioMisiones.stream().map(UsuarioMision::getMision).collect(Collectors.toList());
        } else {
            return getRandomMisiones();
        }
    }

    private Date getStartOfDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Date getEndOfDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());  // Set current date
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public Mision getRandomMisionByDificultad(Dificultad dificultad) {
        List<Mision> misiones = misionRepository.findByDificultad(dificultad);
        return misiones.get(new Random().nextInt(misiones.size()));
    }

    private List<Mision> getRandomMisiones() {
        List<Mision> randomMisiones = new ArrayList<>();
        randomMisiones.add(getRandomMisionByDificultad(Dificultad.FACIL));
        randomMisiones.add(getRandomMisionByDificultad(Dificultad.MEDIA));
        randomMisiones.add(getRandomMisionByDificultad(Dificultad.DIFICIL));
        return randomMisiones;
    }
}
