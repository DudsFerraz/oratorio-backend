package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import com.oratorio.springBackEnd.Documentos.CPF;
import com.oratorio.springBackEnd.Documentos.RG;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Endereco.EnderecoFullResponseDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Oratoriano.OratorianoCreationDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Oratoriano.OratorianoFullResponseDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Oratoriano.OratorianoShortResponseDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Responsavel.ResponsavelOratorianoDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class OratorianoService {

    private final OratorianoRepo oratorianoRepo;
    private final ResponsavelRepo responsavelRepo;

    public OratorianoService(OratorianoRepo oratorianoRepo, ResponsavelRepo responsavelRepo) {
        this.oratorianoRepo = oratorianoRepo;
        this.responsavelRepo = responsavelRepo;
    }

    private OratorianoFullResponseDTO generateFullDto(Oratoriano o){
        CPF cpf = o.getCpf();
        String cpfStr;
        if(cpf == null){
            cpfStr = "";
        }else{
            cpfStr = cpf.getCpf();
        }

        RG rg = o.getRg();
        String rgStr;
        if(rg == null){
            rgStr = "";
        }else{
            rgStr = rg.getFullRg();
        }

        Endereco e = o.getEndereco();
        EnderecoFullResponseDTO enderecoDto;
        if(e == null){
            enderecoDto = new EnderecoFullResponseDTO("","","","","");
        }else{
            enderecoDto = new EnderecoFullResponseDTO(e.getCEP(),e.getCidade(),e.getBairro(),
                    e.getRua(),e.getNumero());
        }

        List<ResponsavelOratoriano> res = o.getResponsaveisOratoriano();
        List<ResponsavelOratorianoDTO> resDto = new ArrayList<>();
        for(ResponsavelOratoriano ro : res){
            Responsavel r = ro.getResponsavel();
            resDto.add(new ResponsavelOratorianoDTO(r.getNome(),r.getCpf().getCpf(),ro.getParentesco()));
        }

        long fichaId;
        FichaMedica fichaMedica = o.getFichaMedica();
        if(fichaMedica == null) fichaId = -1;
        else fichaId = fichaMedica.getId();

        return new OratorianoFullResponseDTO(o.getNome(),o.getId(),o.getDataNascimento(),cpfStr, rgStr,enderecoDto,
                o.getTelefone(),o.getEscola(),o.getAnoEscola(),
                resDto,fichaId,o.getNumeroPresencasTotais());
    }
    private OratorianoShortResponseDTO generateShortDTO(Oratoriano o){
        return new OratorianoShortResponseDTO(o.getNome(),o.getId());
    }
    private List<Oratoriano> comparePresencas(boolean desc){
        List<Oratoriano> oratorianos = oratorianoRepo.findAll();

        if(desc){
            oratorianos.sort(Comparator.comparingInt(Oratoriano::getNumeroPresencasTotais));
        }else{
        oratorianos.sort(Comparator.comparingInt(Oratoriano::getNumeroPresencasTotais).reversed());
        }

        return oratorianos;
    }

    private List<Oratoriano> switchOnSort(String sort){
        List<Oratoriano> oratorianos;
        switch (sort) {
            case "NomeCrescente" -> oratorianos = oratorianoRepo.findAll(Sort.by("nome"));
            case "NomeDecrescente" -> oratorianos = oratorianoRepo.findAll(Sort.by("nome").descending());
            case "IDCrescente" -> oratorianos = oratorianoRepo.findAll(Sort.by("id"));
            case "IDDecrescente" -> oratorianos = oratorianoRepo.findAll(Sort.by("id").descending());
            case "IdadeCrescente" -> oratorianos = oratorianoRepo.findAll(Sort.by("dataNascimento").descending());
            case "IdadeDecrescente" -> oratorianos = oratorianoRepo.findAll(Sort.by("dataNascimento"));
            case "PresencasCrescente" -> oratorianos = comparePresencas(false);
            case "PresencasDecrescente" -> oratorianos = comparePresencas(true);
            default -> oratorianos = oratorianoRepo.findAll();
        }

        return oratorianos;
    }

    public OratorianoShortResponseDTO create(@RequestBody OratorianoCreationDTO dto) {
        Oratoriano newOratoriano;
        if(dto.dataNascimento()==null){
            newOratoriano = new Oratoriano(dto.nome());
        }else{
            newOratoriano = new Oratoriano(dto.nome(), dto.dataNascimento());
        }
        oratorianoRepo.save(newOratoriano);
        return generateShortDTO(newOratoriano);
    }

    public OratorianoShortResponseDTO getShortById(Long id) {
         Oratoriano o = oratorianoRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("nao encontrado"));

         return generateShortDTO(o);
    }

    public OratorianoFullResponseDTO getFullById(Long id) {
        Oratoriano o = oratorianoRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("nao encontrado"));

        return generateFullDto(o);
    }

    public List<OratorianoShortResponseDTO> getAllShort(String sort) {
        List<OratorianoShortResponseDTO> dtoList = new ArrayList<>();

        List<Oratoriano> oratorianos = switchOnSort(sort);

        for(Oratoriano o : oratorianos){
            dtoList.add(generateShortDTO(o));
        }

        return dtoList;
    }

    public List<OratorianoFullResponseDTO> getAllFull(String sort) {
        List<OratorianoFullResponseDTO> dtoList = new ArrayList<>();

        List<Oratoriano> oratorianos = switchOnSort(sort);

        for(Oratoriano o : oratorianos){
            dtoList.add(generateFullDto(o));
        }

        return dtoList;
    }

}
