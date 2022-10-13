package com.example.demo.service;

import com.example.demo.clients.MovieFeignClient;
import com.example.demo.clients.SerieFeignClient;
import com.example.demo.exceptions.ExceedsLimitException;
import com.example.demo.exceptions.NoResultsException;
import com.example.demo.exceptions.NotFound;
import com.example.demo.model.Catalog;
import com.example.demo.model.MovieC;
import com.example.demo.model.SerieC;
import com.example.demo.repository.CatalogRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class CatalogServiceImp implements CatalogService{

    private String genre;

    private Integer id;

    private MovieFeignClient movieFeignClient;

    private CatalogRepository catalogRepository;

    private SerieFeignClient serieFeignClient;

    @Autowired
    public CatalogServiceImp(MovieFeignClient movieFeignClient,
                             SerieFeignClient serieFeignClient,
                             CatalogRepository catalogRepository){
        this.movieFeignClient=movieFeignClient;
        this.serieFeignClient=serieFeignClient;
        this.catalogRepository=catalogRepository;
    }



    @Override
    public List<MovieC> listMovies(int from,int to) throws NoResultsException, ExceedsLimitException {
        return movieFeignClient.li(from,to).getBody();
    }

    @Override
    public MovieC findMovieByName(String name) throws NotFound {
       return movieFeignClient.findByName(name).getBody();
    }

    @Override
    public List<MovieC> findMoviesByGenre(String genre,int from, int to) throws NoResultsException, ExceedsLimitException {
        Catalog catalog= findByGenre(genre);
        List<MovieC> movieCList=movieFeignClient.findByGenre(genre, from, to).getBody();
        if (Objects.isNull(catalog.getMovieC()) ) {
            catalog.setMovieC(movieCList);
        }else {
            catalog.getMovieC().addAll(movieCList);
        }
        createUpdate(catalog);
        return movieFeignClient.findByGenre(genre,from,to).getBody();
    }

    @Override
    public List<SerieC> listSeries(int from, int to) throws NoResultsException, ExceedsLimitException {
        return serieFeignClient.li(from, to).getBody();
    }

    @Override
    public SerieC findSerieByName(String name) throws NotFound {
        return serieFeignClient.findByName(name).getBody();
    }

    @Override
    @CircuitBreaker(name = "series", fallbackMethod = "seriesFallbackMethod")
    public List<SerieC> findSerieByGenre(String genre, int from, int to) throws ExceedsLimitException,NoResultsException{
        this.genre=genre;
        Catalog catalog= findByGenre(genre);
        List<SerieC> serieCList=serieFeignClient.findByGenre(genre, from, to).getBody();
         if (Objects.isNull(catalog.getSerieC()) ) {
             catalog.setSerieC(serieCList);
         }else {
             catalog.getSerieC().addAll(serieCList);
         }
        createUpdate(catalog);

        return serieCList;
    }

    private List<SerieC> seriesFallbackMethod(CallNotPermittedException callNotPermittedException){
        return findByGenre(this.genre).getSerieC().subList(0,2);
    }

    public Catalog findByGenre(String genre){
        return catalogRepository.findByGenre(genre);
    }

    public Catalog createUpdate(Catalog catalog){
        return catalogRepository.save(catalog);
    }

    @RabbitListener(queues = "serie-queue")
    public void saveSeries(SerieC serieC){
        if (Objects.isNull(findByGenre(serieC.getGenre()))){
            Catalog catalog1=new Catalog();
            if (id==null){
                id=1;
            }else {
                id++;
            }
            catalog1.setId(id);
            catalog1.setGenre(serieC.getGenre());
            catalog1.setSerieC(List.of(serieC));
            createUpdate(catalog1);
        }else{
            Catalog catalog=findByGenre(serieC.getGenre());
        if (Objects.isNull(catalog.getSerieC())){
            catalog.setSerieC(List.of(serieC));
        }else{
            List<SerieC> serieCList=catalog.getSerieC();
            serieCList.add(serieC);
            catalog.setSerieC(serieCList);
        }
        createUpdate(catalog);
        }
    }

    /*fue elegido el microservicio de series como microservicio de alta disponibilidad debido
    a que considero mas probable que sea necesario su uso aca, por este motivo cuenta con loadbalancers tanto para feign como
    para apigateway y resilience4j en caso de fallos que se apoya en las redundancias que hay de los datos de ese microservicio
    en este mismo microservicio(catalog-service)*/

    @RabbitListener(queues = "movie-queue")
    public void saveMovies(MovieC movieC){
        if (Objects.isNull(findByGenre(movieC.getGenre()))){
            Catalog catalog1=new Catalog();
            if (id==null){
                id=1;
            }else {
                id++;
            }
            catalog1.setId(id);
            catalog1.setGenre(movieC.getGenre());
            catalog1.setMovieC(List.of(movieC));
            createUpdate(catalog1);
        }else{
            Catalog catalog =findByGenre(movieC.getGenre());
        if (Objects.isNull(catalog.getMovieC())){
            catalog.setMovieC(List.of(movieC));
        }else{
            List<MovieC> movieCList=catalog.getMovieC();
            movieCList.add(movieC);
            catalog.setMovieC(movieCList);
        }
            createUpdate(catalog);
        }

    }
}
