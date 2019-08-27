package com.ruoyi.project.system.files.service;

import com.ruoyi.project.system.files.domain.Files;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FilesRepository extends ElasticsearchRepository<Files,Integer> {

}
