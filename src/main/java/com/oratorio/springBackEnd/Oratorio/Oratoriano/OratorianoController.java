package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Oratoriano.OratorianoCreationDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Oratoriano.OratorianoFullResponseDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Oratoriano.OratorianoShortResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/oratoriano")
public class OratorianoController {

    private final OratorianoService oratorianoService;

    public OratorianoController(OratorianoService oratorianoService, OratorianoRepo oratorianoRepo) {
        this.oratorianoService = oratorianoService;
    }

    @PostMapping
    public OratorianoShortResponseDTO create(@RequestBody OratorianoCreationDTO dto) {
        return oratorianoService.create(dto);
    }

    @GetMapping("/getShort")
    public OratorianoShortResponseDTO getShort(@RequestBody long id){
        return oratorianoService.getShortById(id);
    }

    @GetMapping("/getFull")
    public OratorianoFullResponseDTO getFull(@RequestBody long id){
        return oratorianoService.getFullById(id);
    }

    @GetMapping("/getAllShort/{sort}")
    public List<OratorianoShortResponseDTO> getAllShort(@PathVariable String sort){
        return oratorianoService.getAllShort(sort);
    }

    @GetMapping("/getAllFull/{sort}")
    public List<OratorianoFullResponseDTO> getAllFull(@PathVariable String sort){
        return oratorianoService.getAllFull(sort);
    }

}
