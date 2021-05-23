package com.example.spring.controller;

import com.example.spring.Service.KisiService;
import com.example.spring.dto.KisiDto;
import com.example.spring.entity.Adres;
import com.example.spring.entity.Kisi;
import lombok.RequiredArgsConstructor;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kisi")
public class KisiController {

    //openapi http://localhost:8080/swagger-ui.html

    public KisiController(KisiService kisiService, Javers javers) {
        this.kisiService = kisiService;
        this.javers = javers;
    }

    private final KisiService kisiService;
    private final Javers javers;

    @PostMapping
    public ResponseEntity<KisiDto> kaydet(@RequestBody KisiDto kisiDto){
        return ResponseEntity.ok(kisiService.save(kisiDto));
    }

    @PutMapping
    public ResponseEntity<KisiDto> put(@RequestBody KisiDto kisiDto){
        return ResponseEntity.ok(kisiService.update(kisiDto));
    }

    @GetMapping
    public ResponseEntity<List<KisiDto>> tumunuListele(){
        return ResponseEntity.ok(kisiService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KisiDto> get(@PathVariable Long id){
       return ResponseEntity.ok(kisiService.findid(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        kisiService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/snapshots")
    public String getKisiSnapshot(){
        QueryBuilder jqlQuery = QueryBuilder.byClass(Kisi.class);
        List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
        return javers.getJsonConverter().toJson(snapshots);
    }

    /**
     * burda kişinin id'sini verip bize o obje hakkında bilgi veriyor
     * @param Kisiid
     * @return
     */
    @GetMapping("/{Kisiid}/changes")
    public ResponseEntity getProductChanges(@PathVariable long Kisiid) {
        Kisi kisi = kisiService.findKisiById(Kisiid);
        QueryBuilder jqlQuery = QueryBuilder.byInstance(kisi);
        Changes changes = javers.findChanges(jqlQuery.build());
        return ResponseEntity.ok(javers.getJsonConverter().toJson(changes));
    }



}
