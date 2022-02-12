package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.model.CodeInfo;
import platform.repository.CodeInfoRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CodeInfoService {

    private final CodeInfoRepository codeInfoRepository;

    @Autowired
    public CodeInfoService(CodeInfoRepository codeInfoRepository) {
        this.codeInfoRepository = codeInfoRepository;
    }

    public CodeInfo findById(String id) {
        CodeInfo codeInfo = codeInfoRepository.findById(id).orElse(null);

        if (codeInfo != null) {
            boolean isEnd = false;

            if (codeInfo.getOriginalTime() != 0) {
                Duration duration = Duration.between(codeInfo.getDate(), LocalDateTime.now());
                long currentTime = codeInfo.getOriginalTime() - duration.getSeconds();
                codeInfo.setTime(currentTime);

                if (codeInfo.getTime() < 0) {
                    isEnd = true;
                }
            }

            if (codeInfo.getOriginalViews() != 0) {
                long currentViews = codeInfo.getViews() - 1;
                codeInfo.setViews(currentViews);

                if (codeInfo.getViews() < 0) {
                    isEnd = true;
                }
            }

            if (isEnd) {
                deleteById(id);
                codeInfo = null;
            } else {
                save(codeInfo);
            }
        }

        return codeInfo;
    }

    public List<CodeInfo> findTop10ByNonRestrictedOrderByDateDesc() {
        return codeInfoRepository.findTop10ByOriginalTimeAndOriginalViewsOrderByDateDesc(0, 0);
    }

    public CodeInfo save(CodeInfo toSave) {
        return codeInfoRepository.save(toSave);
    }

    public void deleteById(String id) {
        codeInfoRepository.deleteById(id);
    }
}