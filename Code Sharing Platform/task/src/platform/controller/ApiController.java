package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.model.CodeInfo;
import platform.service.CodeInfoService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ApiController {

    @Autowired
    CodeInfoService codeInfoService;

    @GetMapping("/api/code/{i}")
    public CodeInfo getCodeInfo(@PathVariable String i) {
        CodeInfo codeInfo = codeInfoService.findById(i);

        if (codeInfo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return codeInfo;
        }
    }

    @GetMapping("/api/code/latest")
    public List<CodeInfo> getCodeInfos() {
        return codeInfoService.findTop10ByNonRestrictedOrderByDateDesc();
    }

    @PostMapping("/api/code/new")
    public Map<String, String> postCodeInfo(/*@Valid*/ @RequestBody CodeInfo codeInfo) {

        String id = UUID.randomUUID().toString();

        codeInfoService.save(new CodeInfo(id, codeInfo.getCode(), LocalDateTime.now(), codeInfo.getTime() <= 0 ? 0 : codeInfo.getTime(), codeInfo.getViews() <= 0 ? 0 : codeInfo.getViews()));

        return Map.of("id", id);
    }
}