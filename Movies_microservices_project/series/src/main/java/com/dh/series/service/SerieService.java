package com.dh.series.service;

import com.dh.series.exceptions.AlreadyExistsException;
import com.dh.series.exceptions.ExceedsLimitException;
import com.dh.series.exceptions.NoResultsException;
import com.dh.series.exceptions.NotFound;
import com.dh.series.model.Serie;
import com.dh.series.repository.SerieRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    private RabbitTemplate rabbitTemplate;
    @Autowired
    public SerieService(SerieRepository serieRepository,
                        RabbitTemplate rabbitTemplate){
        this.serieRepository=serieRepository;
        this.rabbitTemplate=rabbitTemplate;
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
        save(serie);
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

    @Override
    public List<Serie> findByGenre(String genre,int from,int to) throws ExceedsLimitException, NoResultsException {
        if(to-from>8) {
            throw new ExceedsLimitException("can't ask for more than 8 elements");
        }

        Page page = serieRepository.findByGenre(genre,PageRequest.of(from,to));

        if (page.isEmpty()) {
            throw new NoResultsException("coudldn't find any results");
        }

        return page.toList();

    }

    public void save(Serie serie){
        rabbitTemplate.convertAndSend("serie-queue",serie);
    }

}
