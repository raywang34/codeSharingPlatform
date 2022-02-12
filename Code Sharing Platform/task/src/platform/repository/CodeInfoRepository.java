package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.model.CodeInfo;

import java.util.List;

@Repository
public interface CodeInfoRepository extends CrudRepository<CodeInfo, String> {
    List<CodeInfo> findTop10ByOriginalTimeAndOriginalViewsOrderByDateDesc(long t, long v);
}