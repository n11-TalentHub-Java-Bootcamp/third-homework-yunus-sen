package com.yunussen.spring.boot.ws.controller;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.yunussen.spring.boot.ws.converter.UrunConverter;
import com.yunussen.spring.boot.ws.dto.UrunDetayDto;
import com.yunussen.spring.boot.ws.dto.UrunDto;
import com.yunussen.spring.boot.ws.entity.Urun;
import com.yunussen.spring.boot.ws.exception.UrunNotFoundException;
import com.yunussen.spring.boot.ws.service.KategoriEntityService;
import com.yunussen.spring.boot.ws.service.UrunEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/urunler/")
public class UrunController {

    @Autowired
    private UrunEntityService urunEntityService;

    @Autowired
    private KategoriEntityService kategoriEntityService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public MappingJacksonValue findAllUrunList() {

        List<Urun> urunList = urunEntityService.findAll();

        String filterName = "UrunFilter";

        SimpleFilterProvider filters = getUrunFilterProvider(filterName);

        MappingJacksonValue mapping = new MappingJacksonValue(urunList);

        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue findUrunById(@PathVariable Long id){

        Urun urun = urunEntityService.findById(id);

        if (urun == null){
            throw new UrunNotFoundException("Urun not found. id: " + id);
        }

        WebMvcLinkBuilder linkToUrun = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                        .findAllUrunList()
        );

        UrunDto urunDto = UrunConverter.INSTANCE.convertUrunToUrunDto(urun);

        String filterName = "UrunDtoFilter";

        SimpleFilterProvider filters = getUrunFilterProvider(filterName);

        EntityModel entityModel = EntityModel.of(urunDto);

        entityModel.add(linkToUrun.withRel("tum-urunler"));

        MappingJacksonValue mapping = new MappingJacksonValue(entityModel);

        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/detail/{id}")
    public UrunDetayDto findUrunDtoById(@PathVariable Long id){

        Urun urun = urunEntityService.findById(id);

        if (urun == null){
            throw new UrunNotFoundException("Urun not found. id: " + id);
        }

        UrunDetayDto urunDetayDto = UrunConverter.INSTANCE.convertUrunToUrunDetayDto(urun);

        return urunDetayDto;
    }

    @PostMapping("")
    public ResponseEntity<Object> saveUrun(@RequestBody UrunDto urunDto){

        Urun urun = UrunConverter.INSTANCE.convertUrunDtoToUrun(urunDto);

        urun = urunEntityService.save(urun);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(urun.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public void deleteUrun(@PathVariable Long id){

        urunEntityService.deleteById(id);
    }

    @GetMapping("kategoriler/{kategoriId}")
    public List<UrunDetayDto> findAllUrunByKategoriId(@PathVariable Long kategoriId){

        List<Urun> urunList = urunEntityService.findAllByKategoriOrderByIdDesc(kategoriId);

        List<UrunDetayDto> urunDetayDtoList = UrunConverter.INSTANCE.convertAllUrunListToUrunDetayDtoList(urunList);

        return urunDetayDtoList;
    }

    private SimpleFilterProvider getUrunFilterProvider(String filterName) {
        SimpleBeanPropertyFilter filter = getUrunFilter();

        return new SimpleFilterProvider().addFilter(filterName, filter);
    }

    private SimpleBeanPropertyFilter getUrunFilter() {
        return SimpleBeanPropertyFilter.filterOutAllExcept("id", "adi", "fiyat", "kayitTarihi");
    }
}