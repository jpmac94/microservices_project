package com.proyectointegrador.seriesservice.service;

import com.proyectointegrador.seriesservice.exceptions.AlreadyExistsException;
import com.proyectointegrador.seriesservice.exceptions.ExceedsLimitException;
import com.proyectointegrador.seriesservice.exceptions.NoResultsException;
import com.proyectointegrador.seriesservice.exceptions.NotFound;
import com.proyectointegrador.seriesservice.model.Serie;
import com.proyectointegrador.seriesservice.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SerieService implements SerieCRUDService<Serie>{

    private SerieRepository serieRepository;

    private Integer id;

    @Autowired
    public SerieService(SerieRepository serieRepository){
        this.serieRepository=serieRepository;
    }

    @Override
    public Serie create(Serie serie) throws AlreadyExistsException {
        if(Objects.isNull(id)){
            id=1;
        }else{
            id++;
        }
        serie.setId(id);

        if (exists(serie.getName())){
            throw new AlreadyExistsException("this element already exists");
        }
        return serieRepository.save(serie);
    }

    private boolean exists(String name){
        return !serieRepository.findByName(name).isEmpty();
    }

    @Override
    public Serie update(Serie object, String name) throws NotFound {
        object.setId(findByName(name).getId());
        return serieRepository.save(object);
    }

    @Override
    public List<Serie> list(int from, int to) throws ExceedsLimitException, NoResultsException {
        if(to-from>8) {
            throw new ExceedsLimitException("can't ask for more than 8 elements");
        }

            Page page = serieRepository.findAll(PageRequest.of(from, to));

            if (page.isEmpty()) {
                throw new NoResultsException("coudldn't find any results");
            }

                return page.toList();
    }

    @Override
    public Serie deleteName(String name) throws NotFound{
        Serie serie=findByName(name);
        serieRepository.deleteById(serie.getId());
        return serie;
    }

    @Override
    public Serie findByName(String name) throws NotFound{
        return serieRepository.findByName(name).orElseThrow(()->new NotFound("no serie has been found with that name"));
    }

}
